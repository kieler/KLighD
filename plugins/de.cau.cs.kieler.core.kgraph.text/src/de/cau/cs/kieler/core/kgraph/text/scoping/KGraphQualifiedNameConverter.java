package de.cau.cs.kieler.core.kgraph.text.scoping;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.IQualifiedNameConverter.DefaultImpl;

/**
 * 
 * @author chsch
 *
 */
public class KGraphQualifiedNameConverter extends DefaultImpl implements IQualifiedNameConverter {

    public QualifiedName toQualifiedName(String qualifiedNameAsString) {
        return QualifiedName.create(qualifiedNameAsString);
    }
    
}
