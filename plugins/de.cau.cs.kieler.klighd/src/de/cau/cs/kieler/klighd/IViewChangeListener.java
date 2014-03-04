/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Defines the notification API to be implemented by concrete listeners being informed about changes
 * of the corresponding diagram view, e.g. changes in visible area, expansion of elements, the
 * current clip, ...
 * 
 * @author chsch
 */
public interface IViewChangeListener {

    /**
     * Called by KLighD viewers in order to notify <code>this</code> {@link IViewChangeListener}.
     * 
     * @param change a {@link ViewChange} notification providing information on the occurred change
     */
    void viewChanged(ViewChange change);

    
    /**
     * Describes notification objects providing information of the view change to be broadcasted to
     * {@link IViewChangeListener IViewChangeListeners}.
     * 
     * @author chsch
     */
    public static class ViewChange {
        
        private final IViewer<?> activeViewer;
        private final ViewChangeType changeType;
        private final KGraphElement affectedElement;
//        private final Rectangle2D visibleViewPort;
        
        /**
         * Constructor.
         * 
         * @param viewer
         *            the viewer the change took place in
         * @param type
         *            an element of {@link ViewChangeType} denoting the view change type.
         * @param element
         *            the element affected during the view change being notified
         * @param viewPort
         *            a {@link Rectangle2D} denoting the visible area of the displayed diagram view
         */
        public ViewChange(final IViewer<?> viewer, final ViewChangeType type,
                final KGraphElement element, final Rectangle2D viewPort) {
            this.activeViewer = viewer;
            this.changeType = type;
            this.affectedElement = element;
//            this.visibleViewPort = viewPort;
        }
        
        /**
         * Provides the view change type.
         * 
         * @return a element of {@link ViewChangeType} denoting view change type.  
         */
        public ViewChangeType getType() {
            return this.changeType;
        }

        /**
         * Provides the {@link KGraphElement} affected during the view change being notified.
         * 
         * @return the {@link KGraphElement} affected during the view change being notified
         */
        public KGraphElement getAffectedElement() {
            return affectedElement;
        }
        
        private static final String MSG =
                "KLighD: Application attempted to traverse an Iterator provided by "
                + "ViewChange#visibleDiagramsElements. Evaluations of those Iterators must be "
                + "performed by the display (UI) thread for integrity reasons.";

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KNode KNodes} visible at the moment of iterating (lazy evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons. 
         * 
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KGraphElement> visibleDiagramNodes() {
            final KNode clip = activeViewer.getClip();
            
            return new AbstractTreeIterator<KGraphElement>(clip) {
                private static final long serialVersionUID = 1021356500841593549L;

                @Override
                protected Iterator<? extends KGraphElement> getChildren(final Object object) {
                    if (PlatformUI.isWorkbenchRunning()
                            && Display.getCurrent() == null) {
                        throw new RuntimeException(MSG);
                    }
                    return Iterators.filter(((KNode) object).getChildren().iterator(),
                            new Predicate<KNode>() {
                        /**
                         * {@inheritDoc}
                         */
                        public boolean apply(final KNode input) {
                            return activeViewer.isVisible(input);
                        }
                    });
                }
            };
        }

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KGraphElement KGraphElements} visible at the moment of iterating (lazy
         * evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons. {@link de.cau.cs.kieler.core.kgraph.KEdge KEdges} are likely to be returned
         * twice as both outgoing as well as incoming edges of a {@link KNode} must be considered.
         * 
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KGraphElement> visibleDiagramsElements() {
            final KNode clip = activeViewer.getClip();
            
            return new AbstractTreeIterator<KGraphElement>(clip) {
                private static final long serialVersionUID = 1021356500841593549L;

                @Override
                protected Iterator<? extends KGraphElement> getChildren(final Object object) {
                    if (PlatformUI.isWorkbenchRunning() && Display.getCurrent() == null) {
                        throw new RuntimeException(MSG);
                    }
                    
                    final Iterator<EObject> candidates;
                    if (object instanceof KNode) {
                        candidates =
                                Iterators.concat(((EObject) object).eContents().iterator(),
                                        ((KNode) object).getIncomingEdges().iterator());
                    } else {
                        candidates = ((EObject) object).eContents().iterator();
                    }

                    @SuppressWarnings("unchecked")
                    Iterator<? extends KGraphElement> res = (Iterator<KGraphElement>) (Iterator<?>)
                            Iterators.filter(candidates, filter);
                    
                    return res; 
                }
                    
                private Predicate<EObject> filter = new Predicate<EObject>() {

                    /**
                     * {@inheritDoc}
                     */
                    public boolean apply(final EObject input) {
                        return input instanceof KGraphElement
                                && activeViewer.isVisible((KGraphElement) input);
                    }
                };
            };
        }
    }    
}
