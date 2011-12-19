/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.transformations;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.internal.xtend.expression.ast.Identifier;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.internal.xtend.xtend.ast.Extension;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtend.XtendFacade;
import org.eclipse.xtend.XtendResourceParser;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.ITransformation;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An implementation of {@link ITransformation} enabling Xtend-based model transformations.
 * 
 * This class realizes the integration of transformations that synthesize a certain view model
 * to be shown in the KLighD view part ({@link de.cau.cs.kieler.klighd.views.DiagramViewPart})
 * and are implemented in the m2m language Xtend.
 * 
 * Such transformation can registered by means of the extension point
 * de.cau.cs.kieler.klighd.modelTransformations. In order to infer the meta models (Ecore packages)
 * referenced by this transformation all (root) Ecore packages have to be imported in the
 * root file of the transformation.
 * 
 * @author chsch
 * @author mri
 * 
 * TODO this should propably be reworked using the ITransformationContext from ...core.model
 *   chsch: what does this imply?
 */
public class XtendBasedTransformation implements ITransformation<EObject, EObject> {

    private static final String TRANSFORMATION_EXTENSION_NAME = "transform";
    private static final String DEFAULT_TRANSFORMATION_EXTENSION_NAME = "onError";

    /** A lookup table for optimizing the source-target-type-resolution for the transformation. */
    private static final HashMap<String, ENamedElement> NAME_ELEMENT_LOOK_UP
                         = new HashMap<String, ENamedElement>();
    
    // chsch: the sense of these both declarations is not obvious to me!
    //  TODO please clarify this and comment it.
    
    /** the property for the element mapping. */
    private static final Property<BiMap<EObject, EObject>> ELEMENT_MAPPING =
            new Property<BiMap<EObject, EObject>>("klighd.elementMapping");
    
    private static BiMap<Object, Object> elementMapping = null;

    /**
     * adds a element to the source model and graphical target mapping.
     * 
     * @param viewId
     *            id of the view
     * @param source
     *            the source model element
     * @param target
     *            the graphical element
     * @deprecated used in pkl's xtend1 tr
     */
    public static void addViewElement(final String viewId, final EObject source,
            final EObject target) {
        elementMapping.put(source, target);
    }

    private URL extfile;
    private String extension;
    private Set<EPackage> metamodels = Sets.newHashSet();
    private int countParams = 1;

    private boolean triedToInferClasses = false;
    private Class<?> sourceModelClass = null;
    private Class<?> targetModelClass = null;

    /**
     * The constructor.
     * 
     * @param extFileURL
     *            the extend file
     * @param theExtension
     *            the root extend function
     * @param theMetamodels
     *            the involved meta models
     */
    public XtendBasedTransformation(final URL extFileURL, final String theExtension,
            final List<EPackage> theMetamodels) {
        this.extfile = extFileURL;
        this.metamodels.addAll(theMetamodels);

        if (theExtension != null && !theExtension.equals("")) {
            this.extension = theExtension;
        } else {
            this.extension = TRANSFORMATION_EXTENSION_NAME;
        }
    }

    /** the length of the string ".ext". */
    private static final int ENDING_OFFSET = 4;

    /**
     * Fires an instance of {@link XtendFacade} to execute the transformation.
     * 
     * @param model
     *            the source model
     * @param transformationContext
     *            the transformation context
     * @return the transformation result
     */
    public EObject transform(final EObject model,
            final TransformationContext<EObject, EObject> transformationContext) {
        
        // prepare the element mapping
        elementMapping = HashBiMap.create();
        transformationContext.setProperty(ELEMENT_MAPPING, elementMapping);
        
        String url = this.extfile.getFile();
        if (url.endsWith(".ext")) {
            url = url.substring(0, url.length() - ENDING_OFFSET);
        }

        XtendFacade facade = XtendFacade.create(url);
        facade.registerMetaModel(new EmfMetaModel(EcorePackage.eINSTANCE));
        for (EPackage mm : this.metamodels/*.values() */) {
            facade.registerMetaModel(new EmfMetaModel(mm));
        }

        Object[] transformationParams = new Object[this.countParams];
        transformationParams[0] = model;

        EObject result = null;

        // the following allows to handle erroneous transformations while developing those
        // transformations; by this a valid model is returned anyway;
        // due to the performance optimization the light diagram service
        // will most likely mark the initial input model to be not supported
        // if "null" is returned by the transformation
        try {
            result = (EObject) facade.call(DEFAULT_TRANSFORMATION_EXTENSION_NAME, new Object[0]);
        } catch (Exception e) {
            /* nothing */
        }

        try {
            result = (EObject) facade.call(this.extension, transformationParams);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "Xtend-based transformation execution failed (" + this.extension
                                    + " of " + url + ")", e));
        }
        elementMapping = null;
        return result;
    }

    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        /* chsch: do we still need this dummy? */
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<EObject, EObject> transformationContext) {
        return transformationContext.getProperty(ELEMENT_MAPPING).inverse().get(element);
    }

    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<EObject, EObject> transformationContext) {
        return transformationContext.getProperty(ELEMENT_MAPPING).get(element);
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return sourceModelClass;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return targetModelClass;
    }

    
    /******************************************
     *                                        *
     *          internal part                 *
     *                                        *
     ******************************************/
    
    /**
     * Infers concrete information about the interface classes of a transformation specified in Xtend.
     * This implementation assumes that the Xtend file is correct and all referenced types are available.
     * 
     * @author chsch, mri
     */
    private void inferSourceAndTargetModelClass() {

        triedToInferClasses = true;
        try {

            /* load the Xtend file */
            XtendFile ext =
                    (XtendFile) new XtendResourceParser().parse(
                            new InputStreamReader(extfile.openStream()), extfile.getFile());

            EPackage ePackage = null;
            // inspect the imported packages at the top of the Xtend file
            //  fills the list meta models to be loaded by the XtendFacade, too
            for (String importedPackageName : ext.getImportedNamespaces()) {
                // try to get the EPackage from the cache ...
                ePackage = (EPackage) NAME_ELEMENT_LOOK_UP.get(importedPackageName);
                if (ePackage == null) {
                    // ... of infer it if necessary
                    ePackage = inferEPackage(importedPackageName);
                    // here, I assume that we will find the denoted package
                    cacheENamedElement(ePackage);
                }
                // add the package to the list meta models to be loaded by the XtendFacade
                this.metamodels.add(ePackage);
            }

            /* search the fitting extension */
            for (Extension e : ext.getExtensions()) {
                // by name...
                if (e.getName().equals(this.extension)) {

                    // reveal the class of the first parameter
                    sourceModelClass =
                            inferClassFromIdentifier(e.getFormalParameters().get(0).getType(), ext);
                    // reveal the class of the return type
                    targetModelClass = inferClassFromIdentifier(e.getReturnTypeIdentifier(), ext);
                    // check if both classes could be found
                    if (sourceModelClass != null && targetModelClass != null) {
                        // remember the actual number of parameters for convenience, needed during the
                        // execution below
                        countParams = e.getFormalParameters().size();
                        break;
                    } else {
                        // reset
                        sourceModelClass = null;
                        targetModelClass = null;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new WrappedException(e, "An error occured while infering source and target class"
                    + " of extension " + this.extension
                    + "of Xtend file " + this.extfile.getFile().toString() + ".\n"
                    + "Make sure that the necessary EPackages are imported and available.");
        } catch (PackageNotFoundException e) {
            throw new WrappedException(e, "An error occured while infering imported packages "
                    + "of Xtend file " + this.extfile.getFile().toString() + ".\n"
                    + "Make sure that the denoted packages are available.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /** the length of the string "::". */
    private static final int SEP_OFFSET = 2;

    
    /**
     * Infers a class denoted by an Ecore-based FQN or by means of the imported Ecore packages.
     * 
     * @author chsch, mri
     * @param identifier
     *            the class's identifier
     * @param ext
     *            the AST of the Xtend file currently processed
     * @return the inferred class or null if no class is found
     * @throws PackageNotFoundException
     *             if an {@link EPackage} could not be resolved in the meantime.
     */
    private Class<?> inferClassFromIdentifier(final Identifier identifier, final XtendFile ext)
            throws ClassNotFoundException {

        String type = identifier.getValue();
        EClassifier clazz = (EClassifier) NAME_ELEMENT_LOOK_UP.get(type);
        
        if (clazz != null) {
            // if the class is already known we're fine :-)
            this.metamodels.add(clazz.getEPackage());
            return clazz.getInstanceClass();
        }
        
        // split the type id in package and class, if possible 
        int pos = type.lastIndexOf("::");
        String className = (pos == -1 ? type : type.substring(pos + SEP_OFFSET));
        String packageName = (pos == -1 ? "" : type.substring(0, pos));
                
        // if the classifier has not been found and is given without package hint ...
        if (clazz == null && Strings.isNullOrEmpty(packageName)) {
            // ... look into the registered meta models ...
            for (EPackage ePackage : this.metamodels) {
                // ... try to infer it ...
                clazz = ePackage.getEClassifier(className);
                if (clazz != null) {
                    // ... and keep it in mind if found
                    cacheENamedElement(clazz);
                    break;
                }
            }
        }

        try {
            // if the denoted class is still unknown and is given in full qualified manner ...
            if (clazz == null && !Strings.isNullOrEmpty(packageName)) {
                // try to fetch the package from the cache...
                EPackage ePackage = (EPackage) NAME_ELEMENT_LOOK_UP.get(packageName);
                if (ePackage == null) {
                    // ... or infer it if it is unknown
                    ePackage = inferEPackage(packageName);
                    // here, I assume that we will find the denoted package
                    cacheENamedElement(ePackage);
                    this.metamodels.add(ePackage);
                }
                // get the class ...
                clazz = ePackage.getEClassifier(className);
                // ... and keep it in mind
                cacheENamedElement(clazz);

            }
        } catch (PackageNotFoundException e) {
            /* will be handled by last line of method */
        }
        // if the classifier has been revealed...
        if (clazz != null) {
            // ... return the related Class<?> instance 
            return clazz.getInstanceClass();
        }
        throw new ClassNotFoundException("The denoted Ecore Class " + type + "could not be found.");
    }
    
    
    /**
     * Infer {@link EPackage} denoted by the packageName. The inference is based on the registered
     * meta models and the EMF package registry. Implementation sucks. Does anybody know something
     * better??<br>
     * Don't come up with {@link Arrays#copyOf(original, newLength, newType)}
     * 
     * @author chsch
     * @param packageName
     *            concatenation of Ecore package names separated by "::", NO java package name
     * @return {@link EPackage} denoted by packageName
     * @throws PackageNotFoundException
     *             if the denoted {@link EPackage} cannot be inferred.
     */
    private EPackage inferEPackage(final String packageName) throws PackageNotFoundException {
        /*
         * Collection of Elements of the EMF Package Registry is transformed s.t. the EPackages are
         * forwarded, other elements are masked with null. The resulting projective collection is
         * filtered s.t. null-elements are skipped. Subsequently, a defensive copy in shape of an
         * ImmutableList based on the registered meta models revealed EPackages is created. Finally,
         * the EPackage inference based on the revealed list is invoked.
         */
        ImmutableList<EPackage> ePackages = ImmutableList.<EPackage>builder()
                .addAll(this.metamodels)
                .addAll(
                    Collections2.filter(
                        Collections2.transform(
                            // take EPackate registry values
                            EPackage.Registry.INSTANCE.values(),
                            // apply the cast and mask function
                            new Function<Object, EPackage>() {
                                public EPackage apply(final Object input) {
                                    if (EcorePackage.eINSTANCE.getEPackage().isInstance(input)) {
                                    return (EPackage) input;
                                } else {
                                    return null;
                                }
                            }
                        }),
                        // apply the null element filter
                        Predicates.notNull()))
                .build();
        try {
            return inferEPackage(packageName, ePackages);
        } catch (IllegalArgumentException e) {
            throw new PackageNotFoundException(packageName, "", 0, 0);
        }
    }
    

    /**
     * @author chsch
     * @param packageName
     *            concatenation of ecore package names separated by "::", NO java package name
     * @param ePackages
     *            collection of ecore packages to look into
     * @return {@link EPackage} denoted by packageName or null if no related {@link EPackage} is
     *         available.
     */
    private EPackage inferEPackage(final String packageName, final List<EPackage> ePackages) {
        if (ePackages.isEmpty()) {
            throw new IllegalArgumentException("(sub)package list is empty and should not be so");
        }
        
        // split the package identifier into first segment and remainder
        int firstSepPos = packageName.indexOf("::");
        String firstSegment = (firstSepPos == -1 ? packageName : packageName.substring(0, firstSepPos));
        String remainder = (firstSepPos == -1 ? null : packageName.substring(firstSepPos + SEP_OFFSET));
        
        for (EPackage ePackage : ePackages) {
            // if a package with the name in firstSegment has been found...
            if (ePackage.getName().equals(firstSegment)) {
                // ... and there is no remainder ...
                if (Strings.isNullOrEmpty(remainder)) {
                    // ... the needed package is likely to be found
                    return ePackage;
                } else {
                    // ... if there is still a remainder descend into the package's sub packages
                    return inferEPackage(remainder, ePackage.getESubpackages());
                }
            }
        }
        return null;
    }
    
    
    private String composeFullName(final ENamedElement element) {
        if (EcorePackage.eINSTANCE.getEClassifier().isInstance(element)) {
            EClassifier clazz = (EClassifier) element;
            return composeFullName(clazz.getEPackage()) + "::" + clazz.getName();
        }
        if (EcorePackage.eINSTANCE.getEPackage().isInstance(element)) {
            return composeFullName((EPackage) element);
        }
        return null;
    }

    private String composeFullName(final EPackage ePackage) {
        return (ePackage.getESuperPackage() == null ? "" : composeFullName(ePackage
                .getESuperPackage()) + "::") + ePackage.getName();
    }
    
    /**
     * @author chsch
     * @param name
     *            the elements name
     * @param namedElement
     *            the element to be kept in mind
     */
    private synchronized void cacheENamedElement(final ENamedElement namedElement) {
        NAME_ELEMENT_LOOK_UP.put(composeFullName(namedElement), namedElement);
    }
}
