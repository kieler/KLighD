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
        private final double diagramScale;
        
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
         * @param diagramScale
         *            the zoom factor of the currently visible diagram area
         */
        public ViewChange(final IViewer<?> viewer, final ViewChangeType type,
                final KGraphElement element, final Rectangle2D viewPort, final double diagramScale) {
            this.activeViewer = viewer;
            this.changeType = type;
            this.affectedElement = element;
            this.diagramScale = diagramScale;
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
         * Provides the corresponding {@link ViewContext} for convenience.
         *  
         * @return the {@link ViewContext} associated to the diagram the change took place in.
         */
        public ViewContext getViewContext() {
            if (this.activeViewer != null) {
                return this.activeViewer.getViewContext();
            } else {
                return null;
            }
        }

        /**
         * Provides the {@link KGraphElement} affected during the view change being notified.
         * 
         * @return the {@link KGraphElement} affected during the view change being notified
         */
        public KGraphElement getAffectedElement() {
            return affectedElement;
        }

        /**
         * Provides the zoom factor of the currently visible diagram area.
         * The value is normalized to 1.0, i.e. 1.0 denotes original size.
         * 
         * @return the zoom factor of the currently visible diagram area
         */
        public double getDiagramZoomScale() {
            return diagramScale;
        }

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KNode KNodes} visible at the moment of iterating (lazy evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons. 
         * 
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KNode> visibleDiagramNodes() {
            return activeViewer.getVisibleDiagramNodes();
        }

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KGraphElement KGraphElements} visible at the moment of iterating (lazy
         * evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons. {@link de.cau.cs.kieler.core.kgraph.KEdge KEdges} are likely to be returned
         * twice, as both outgoing as well as incoming edges of a {@link KNode} must be considered.
         * 
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KGraphElement> visibleDiagramElements() {
            return activeViewer.getVisibleDiagramElements();
        }
    }    
}
