/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.incremental;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.compare.match.engine.IMatchScopeProvider;
import org.eclipse.emf.compare.match.filter.IResourceFilter;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.ImmutableMap;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;

/**
 * The incremental update strategy for KGraph models with attached KRendering data that is based on
 * EMF Compare.
 * 
 * @author mri
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class UpdateStrategy implements IUpdateStrategy<KNode> {

    /** The id used at registration of this strategy in the plugin.xml. */
    public static final String ID = UpdateStrategy.class.getCanonicalName();
    
    /** This plugin's id. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.incremental";
    
    /** the priority for this update strategy. */
    public static final int PRIORITY = 20;
    
    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return KNode.class;
    }
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    private Resource updateResource = null;
    
    private SimpleUpdateStrategy fallbackDelegate = null;
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Beyond the view model's root element two resources are created within the same resource set.
     * They are required by EMF Compare as that uses fragmentURIs for identifying similar elements,
     * for example.
     */
    public KNode getInitialBaseModel(final ViewContext viewContext) {
        final KNode baseModel = KimlUtil.createInitializedNode();
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createURI("baseModel.kgraph"));
        updateResource = resourceSet.createResource(URI.createURI("newModel.kgraph"));
        resource.getContents().add(baseModel);
        return baseModel;
    }


    /** The match option definition, they lead to reduced number of incorporated similarity checkers. */
    private static final Map<String, Object> MATCH_OPTIONS = ImmutableMap.<String, Object>of(
            MatchOptions.OPTION_DISTINCT_METAMODELS, false,
            MatchOptions.OPTION_IGNORE_XMI_ID, true,
            MatchOptions.OPTION_IGNORE_ID, true,
            MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, new KGraphMatchScopeProvider());


    /**
     * {@inheritDoc}
     */
    public void update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {
        boolean ok = false;
        
        try {
            updateResource.getContents().add(newModel);

            // match the base and the new model
            MatchModel match = MatchService.doMatch(baseModel, newModel, MATCH_OPTIONS);
            
            // compute differences
            DiffModel diff = DiffService.doDiff(match, false);
            
            // DEBUG           
            // serialize("/test/diff.xmi", diff);
            
            // merge differences
            MergeService.merge(diff.getOwnedElements(), false);
            
            updateResource.getContents().clear();
            ok = true;

        } catch (InterruptedException e) {
            final String msg = "KLighD: Incremental update of diagram by means of the EMF"
                    + "Compare-based update strategy failed due to some unexpected execution"
                    + "interruption.";
            StatusManager.getManager().handle(new Status(IStatus.ERROR, PLUGIN_ID, msg, e),
                    StatusManager.LOG);
        } catch (RuntimeException e) {
            final String msg = "KLighD: Incremental update of diagram by means of the EMF"
                    + "Compare-based update strategy failed.";
            StatusManager.getManager().handle(new Status(IStatus.ERROR, PLUGIN_ID, msg, e),
                    StatusManager.LOG);
            e.printStackTrace();
        }
        
        // if incremental updating failed, apply the SimpleUpdateStrategy
        if (!ok) {
            if (this.fallbackDelegate == null) {
                this.fallbackDelegate = new SimpleUpdateStrategy();                
            }
            this.fallbackDelegate.update(baseModel, newModel, viewContext);
        }
    }


    /**
     * A match scope provider contributing the {@link KGraphMatchScope}.
     */
    private static class KGraphMatchScopeProvider implements IMatchScopeProvider {

        /** the match scope. */
        private IMatchScope scope = new KGraphMatchScope();
        
        /**
         * {@inheritDoc}
         */
        public IMatchScope getLeftScope() {
            return scope;
        }

        /**
         * {@inheritDoc}
         */
        public IMatchScope getRightScope() {
            return scope;
        }

        /**
         * {@inheritDoc}
         */
        public IMatchScope getAncestorScope() {
            // no three-way matching
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public void applyResourceFilter(final IResourceFilter filter) {
            // no resources involved
        }
    }


    /**
     * A KGraph-specific {@link IMatchScope}, which behaves like a white list of
     * {@link org.eclipse.emf.ecore.EClass EClasses} that are to be incorporated while matching two
     * KGraph model with potentially attached KRendering and KLayoutData data.<br>
     * <br>
     * Currently, pure {@link de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl KGraphDataImpls} or
     * Java subclasses like {@link de.cau.cs.kieler.klighd.util.RenderingContextData
     * RenderingContextData} are dropped by this filter.
     */
    private static class KGraphMatchScope implements IMatchScope {

        /**
         * {@inheritDoc}
         */        
        public boolean isInScope(final EObject eObject) {
            final EPackage ePackage = eObject.eClass().getEPackage();
            final int id = eObject.eClass().getClassifierID();
            
            if (ePackage == KGraphPackage.eINSTANCE) {

                switch (id) {
                case KGraphPackage.KLABEL:
                case KGraphPackage.KNODE:
                case KGraphPackage.KEDGE:
                case KGraphPackage.KPORT:
                    return true;
                case KGraphPackage.IPROPERTY_TO_OBJECT_MAP:
                    return true;
                default:
                    // e.g. instances of RenderingContextData,
                    //  which is a pure Java subclass of KGraphDataImpl
                    return false;
                }

            } else if (ePackage == KLayoutDataPackage.eINSTANCE) {

                switch (id) {
                case KLayoutDataPackage.KSHAPE_LAYOUT:
                case KLayoutDataPackage.KEDGE_LAYOUT:
                case KLayoutDataPackage.KINSETS:
                    return true;
                case KLayoutDataPackage.KPOINT:
                    return true;
                default:
                    return false;
                }

            } else if (ePackage == KRenderingPackage.eINSTANCE) {
                // SUPPRESS CHECKSTYLE PREVIOUS SimplifyBooleanReturn
                //  That hint is valid but I want to have the code in a canonical form here.
                return true;

            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        public boolean isInScope(final Resource resource) {
            return true;
        }
    }


    /** A helper for debugging purposes! */
    @SuppressWarnings("unused")
    private void serialize(final String path, final EObject... objects) {
        URI fileURI = URI.createPlatformResourceURI(path, true);
        
        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.createResource(fileURI);

        for (EObject object : objects) {
            if (object instanceof KNode) {
                KimlUtil.persistDataElements((KNode) object);
            }
            resource.getContents().add(object);
        }
        
        try {
            resource.save(System.out, Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resource.unload();
    }
}
