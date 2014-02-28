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

import com.google.common.collect.AbstractIterator;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

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
        private final Rectangle2D visibleViewPort;
        
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
            this.visibleViewPort = viewPort;
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
        
        /**
         * TODO Incorporation of scaling is missing !!
         * 
         * @return a {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} 
         */
        public Iterator<KGraphElement> visibleElements() {
            final KNode clip = activeViewer.getClip();
            final KVector absPos = KimlUtil.toAbsolute(
                    new KVector(visibleViewPort.getCenterX(), visibleViewPort.getY()), clip);
            
            final Rectangle2D.Double absoluteViewPort =
                    new Rectangle2D.Double(absPos.x, absPos.y,
                            visibleViewPort.getWidth(), visibleViewPort.getHeight());
            
            return new AbstractTreeIterator<KGraphElement>(clip) {
                private static final long serialVersionUID = 1021356500841593549L;

//                private Stack<KNode> parents = new Stack<KNode>();
                private KVector singleVec = new KVector();
                private Rectangle2D.Float singleRect = new Rectangle2D.Float();
                
                @Override
                protected Iterator<? extends KGraphElement> getChildren(final Object object) {

                    final KNode node = (KNode) object;
                    /* return new MyIterator((KNode) object, parents) {
                     * 
                        @Override
                       } */
                    
//                    while (!parents.isEmpty() && parents.peek() != node.eContainer()) {
//                        parents.pop();
//                    }
//                    
//                    parents.push(node);
                    
                    final Iterator<KNode> children = node.getChildren().iterator();
                    
                    return new AbstractIterator<KGraphElement>() {
                        
                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        protected KGraphElement computeNext() {
                            KNode node;
                            while (children.hasNext()) {
                                node = children.next();
                                if (intersects(node)) {
                                    return node;
                                }
                            }
                            endOfData();
                            return null;
                        }
                        
                        protected boolean intersects(final KNode node) {
                            final KShapeLayout layout = node.getData(KShapeLayout.class);
                            singleVec.x = layout.getXpos();
                            singleVec.y = layout.getYpos();
                            KimlUtil.toAbsolute(singleVec, node);

                            singleRect.setRect(singleVec.x, singleVec.y, layout.getWidth(),
                                    layout.getHeight());

                            final boolean res = absoluteViewPort.intersects(singleRect);
                            return res;
                        }
                    };
                }
            };
        }
    }
    
//    /**
//     * package protected since private is prohibited.
//     *  
//     * @author chsch
//     */
//    abstract static class MyIterator implements Iterator<KGraphElement> {
//        private KNode next = null;
//        private boolean initial = true;
//        private boolean finished = false;
//        private final Stack<KNode> parents;
//        private final KNode parent;
//        private final Iterator<KNode> children;
//        
//        /**
//         * Constructor.
//         */
//        public MyIterator(final KNode parent, final Stack<KNode> parents) {
//            this.parents = parents;
//            this.parent = parent;
//            this.children = parent.getChildren().iterator();
//        }
//        
//        /**
//         * a.
//         * @param node b
//         * @return c
//         */
//        protected abstract boolean intersects(KNode node);
//
//        public boolean hasNext() {            
//            if (next != null) {
//                // hastNext() has already been called after the most recent call of 'next()'
//                return true;
//                
//            } else if (!children.hasNext()) {
//                // there're no elements any more
//                if (!finished) {
//                    parents.pop();
//                    finished = true;
//                }
//                return false;
//
//            } else {
//                // first call either after initialization or a call of 'next()'                
//                if (initial) {
//                    parents.push(parent);
//                    initial = false;
//                }
//                
//                                
//                next = children.next();
//                
//                while (!intersects(next)) {
//                    if (children.hasNext()) {
//                        next = children.next();
//                    } else {
//                        next = null;
//                        return hasNext();
//                    }
//                }
//                return true;
//            }
//        }
//
//        public KGraphElement next() {
//            if (this.hasNext()) {
//                final KNode res = next;
//                next = null;
//                return res;
//            } else {
//                throw new NoSuchElementException();
//            }
//        }
//
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//    }
}
