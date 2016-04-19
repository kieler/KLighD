/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.linking.impl.Linker;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parsetree.reconstr.ITransientValueService;
import org.eclipse.xtext.resource.XtextResource;

import de.cau.cs.kieler.kgraph.text.scoping.KGraphQualifiedNameProvider;
import de.cau.cs.kieler.kgraph.text.serializer.CustomKGraphSemanticSequencer;
import de.cau.cs.kieler.kgraph.text.serializer.CustomKGraphSyntacticSequencer;
import de.cau.cs.kieler.kgraph.text.serializer.KGraphSemanticSequencer;
import de.cau.cs.kieler.kgraph.text.serializer.KGraphSyntacticSequencer;
import de.cau.cs.kieler.kgraph.text.serializer.KGraphTransientValueService;
import de.cau.cs.kieler.kgraph.text.validation.KGraphJavaValidator;
import de.cau.cs.kieler.kgraph.text.validation.KGraphValidator;

/**
 * This class defines some customizations on the textual KGraph editing tooling.
 * 
 * @author chsch
 * @author msp
 */
public class KGraphRuntimeModule extends AbstractKGraphRuntimeModule {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return KGraphQualifiedNameProvider.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IValueConverterService> bindIValueConverterService() {
        return KGraphValueConverters.class;
    }
    
    /**
     * Registers a customized {@link ITransientValueService}. Although the serialization is
     * configured in the
     * {@link de.cau.cs.kieler.kgraph.text.serializer.CustomKGraphSemanticSequencer
     * KGraphSemanticSequencer} the transient value service is obviously involved while serializing
     * portions of models after apply semantic modification (quick fixes). This feature is used in
     * combination with my test case trainer, see
     * {@link de.cau.cs.kieler.kgraph.text.validation.KGraphJavaValidator KGraphJavaValidator}
     * & quick fix provider.
     * 
     * @author chsch
     * 
     * @return the {@link KGraphTransientValueService} class
     */
    @Override
    public Class<? extends ITransientValueService> bindITransientValueService() {
        return KGraphTransientValueService.class;
    }

    /**
     * Method registers a customized {@link org.eclipse.xtext.linking.lazy.LazyLinkingResource} in
     * order to handle the (de-) serialization of {@link de.cau.cs.kieler.core.properties.IProperty}s
     * correctly.
     * 
     * @return the {@link KGraphResource} class
     */
    public Class<? extends XtextResource> bindXtextResource() {
        return KGraphResource.class;
    }
    
    /**
     * Method registers the non-lazy linking Linker since the default
     * {@link org.eclipse.xtext.linking.lazy.LazyLinker} doesn't work properly with EOpposite
     * references. (Produces error markers in editor.)
     * 
     * @return the {@link Linker} class
     */
    @Override
    public Class<? extends ILinker> bindILinker() {
        return MyLinker.class;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends KGraphValidator> bindKGraphValidator() {
        return KGraphJavaValidator.class;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends KGraphSemanticSequencer> bindISemanticSequencer() {
        return CustomKGraphSemanticSequencer.class;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends KGraphSyntacticSequencer> bindISyntacticSequencer() {
        return CustomKGraphSyntacticSequencer.class;
    }

    /**
     * FIXME
     * Temporary fix for an issue where KEdge#target is set to null again
     * _after_ it was successfully linked. 
     */
    private static class MyLinker extends Linker {
    	
    	protected boolean isClearAllReferencesRequired(Resource resource) {
    		return false;
    	}
    	
//    	protected void ensureModelLinked(EObject model, final IDiagnosticProducer producer) {
//    		boolean clearAllReferencesRequired = isClearAllReferencesRequired(model.eResource());
//    		TreeIterator<EObject> iterator = getAllLinkableContents(model);
//    		
//    		// first clear all (possibly invalid) references 
//    		while(iterator.hasNext()) {
//    			EObject next = iterator.next();
//    			if (clearAllReferencesRequired) {
//    				clearReferences(next);
//    			}
//    		}
//    		
//    		// re-link
//    		iterator = getAllLinkableContents(model);
//    		while(iterator.hasNext()) {
//    			EObject next = iterator.next();
//    			ensureLinked(next, producer);
//    		}
//    	}
    }
    
}
