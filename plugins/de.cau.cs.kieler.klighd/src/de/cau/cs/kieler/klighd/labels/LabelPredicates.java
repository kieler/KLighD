/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels;

import java.util.function.Predicate;

import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.KPort;
import org.eclipse.emf.ecore.EObject;




/**
 * Utility class that can build many commonly used label filter predicates.
 * 
 * @see ConditionLabelManager
 * @author cds
 */
public final class LabelPredicates {
    
    /**
     * Utility class, not to be instantiated.
     */
    private LabelPredicates() {
        
    }
    

    /**
     * Returns a predicate that matches everything.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> matchAll() {
        return (EObject eo) -> true;
    }

    /**
     * Returns a predicate that doesn't match anything.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> matchNone() {
        return (EObject eo) -> false;
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a label.
     * 
     * @return the predicate.
     */
    private static Predicate<EObject> isLabel() {
        return (EObject eo) -> KLabel.class.isInstance(eo);
    }
    
    /**
     * Returns a condition that checks if the container of an {@link EObject} is an instance of the
     * given class.
     * 
     * @param containerClass
     *            the class containers must be an instance of to pass.
     * @return the predicate.
     */
    public static Predicate<EObject> emfContainerInstanceOf(
            final Class<? extends EObject> containerClass) {
        
        return (EObject eo) -> containerClass.isInstance(eo.eContainer());
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a node label.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> nodeLabel() {
        return isLabel().and(emfContainerInstanceOf(KNode.class));
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a node label.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> portLabel() {
        return isLabel().and(emfContainerInstanceOf(KPort.class));
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a node label.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> edgeLabel() {
        return isLabel().and(emfContainerInstanceOf(KEdge.class));
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a centered edge label. Edge labels with
     * undefined edge label placement are treated as centered edge labels.
     * 
     * @return the predicate.
     */
    public static Predicate<EObject> centerEdgeLabel() {
        return centerEdgeLabel(true);
    }
    
    /**
     * Returns a predicate that checks if an {@link EObject} is a centered edge label.
     * 
     * @param includeUndefined
     *            {@code true} if edge labels with undefined edge label placement should be treated
     *            as center edge labels as well.
     * @return the predicate.
     */
    public static Predicate<EObject> centerEdgeLabel(final boolean includeUndefined) {
        return isLabel().and(emfContainerInstanceOf(KEdge.class)).and(
                (EObject label) -> {
                    KLayoutData ld = ((KLabel) label).getData(KLayoutData.class);
                    EdgeLabelPlacement elp = ld.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
                    return elp == EdgeLabelPlacement.CENTER
                            || (includeUndefined && elp == EdgeLabelPlacement.UNDEFINED);
                });
    }
    
}
