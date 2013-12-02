package de.cau.cs.kieler.klighd.examples.ecore;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClassifier;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.viewers.KlighdTreeSelection;

/**
 * Experimental/exemplary {@link PropertyTester} used for limiting the visibility of Ecore-related
 * menu contributions. Contributes the property "isEClassifier" to the visibility expressions of the
 * org.eclipse.ui.menus extension point.<br>
 * <br>
 * It is registered and referenced in the plugin.xml.
 * 
 * @author chsch
 */
public class EcorePropertyTester extends PropertyTester {

    private final static String All_ARE_ECLASSIFIERS = "AllAreEClassifiers";
    
    /**
     * {@inheritDoc}
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof KlighdTreeSelection)) {
            return false;
        }
        
        final KlighdTreeSelection selection = (KlighdTreeSelection) receiver;
        
        if (All_ARE_ECLASSIFIERS.equals(property)) {
            return Iterators.all(selection.sourceElementIterator(), Predicates.instanceOf(EClassifier.class));
        }
        
        return false;
    }
}
