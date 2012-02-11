package de.cau.cs.kieler.core.kgraph.text;

import org.eclipse.xtext.linking.impl.Linker;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;

import de.cau.cs.kieler.core.kgraph.text.scoping.KGraphQualifiedNameConverter;
import de.cau.cs.kieler.core.kgraph.text.scoping.KGraphQualifiedNameProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class KGraphRuntimeModule extends de.cau.cs.kieler.core.kgraph.text.AbstractKGraphRuntimeModule {

    public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
        return KGraphQualifiedNameConverter.class;
    }
    
    @Override
    public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return KGraphQualifiedNameProvider.class;
    }
    
    @Override
    public Class<? extends org.eclipse.xtext.linking.ILinker> bindILinker() {
        return Linker.class;
    }

}
