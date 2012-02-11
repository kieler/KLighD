package de.cau.cs.kieler.core.kgraph.text.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import com.google.inject.Inject;

/**
 * 
 * @author chsch
 */
public class KGraphQualifiedNameProvider extends SimpleNameProvider implements
        IQualifiedNameProvider {
    
    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;
    
    public QualifiedName getFullyQualifiedName(EObject obj) {
        return qualifiedNameConverter.toQualifiedName(obj.eResource().getURIFragment(obj));
    }
}