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
package de.cau.cs.kieler.core.kgraph.text;

import org.eclipse.xtext.linking.impl.Linker;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.XtextResource;

import de.cau.cs.kieler.core.kgraph.text.scoping.KGraphQualifiedNameConverter;
import de.cau.cs.kieler.core.kgraph.text.scoping.KGraphQualifiedNameProvider;
import de.cau.cs.kieler.core.kgraph.text.serializer.KGraphTransientValueService;

/**
 * This class defines some customizations on the textual KGraph editing tooling.
 * 
 * @author chsch 
 */
public class KGraphRuntimeModule extends de.cau.cs.kieler.core.kgraph.text.AbstractKGraphRuntimeModule {

    /**
     * Method registers a custom {@link IQualifiedNameConverter} handling fragmentURIs for
     * referencing nodes correctly. For instance, it is used in the
     * {@link org.eclipse.xtext.linking.impl.DefaultLinkingService} for resolving the given
     * reference identifier, here the fragmentURIs.
     * 
     * @return the {@link KGraphQualifiedNameConverter} class
     */
    public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
        return KGraphQualifiedNameConverter.class;
    }
    
    /**
     * Method registers a custom (trivial) {@link IQualifiedNameProvider} converting fragmentURIs
     * into {@link org.eclipse.xtext.naming.QualifiedName}s. without splitting the former on '.'
     * (dot) characters. It is used in the
     * {@link de.cau.cs.kieler.core.kgraph.text.scoping.KGraphScopeProvider}, for instance.
     * 
     * @return the {@link KGraphQualifiedNameProvider} class
     */
    @Override
    public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return KGraphQualifiedNameProvider.class;
    }
    
    /**
     * Method registers the non-lazy linking Linker since the default
     * {@link org.eclipse.xtext.linking.lazy.LazyLinker} doesn't work properly with EOpposite
     * references. (Produces error markers in editor.)
     * 
     * @return the {@link Linker} class
     */
    @Override
    public Class<? extends org.eclipse.xtext.linking.ILinker> bindILinker() {
        return Linker.class;
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
     * Method registers a customized
     * {@link org.eclipse.xtext.serializer.sequencer.ITransientValueService} in order to serialize
     * KGraph specifications correctly (e.g. suppresses EOpposite relations).
     * 
     * @return the {@link KGraphTransientValueService} class
     */
    @SuppressWarnings("restriction")
    public Class<? extends org.eclipse.xtext.serializer.sequencer.ITransientValueService>
        bindNewITransientValueService() {
        return KGraphTransientValueService.class;
    }

}
