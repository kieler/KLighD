/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.Trigger;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Common interface of classes providing implementations of
 * {@link de.cau.cs.kieler.core.krendering.KAction KActions}.<br>
 * <br>
 * {@link IAction IActions} are currently assumed to be stateless and, thus, a singleton instance is
 * created only.
 * 
 * @author chsch
 */
public interface IAction {
    
    /**
     * This method performs the intended action on a diagram.
     * 
     * @param context
     *            an {@link ActionContext} instance providing various useful data.
     * @return a specific {@link ILayoutConfig} to be incorporated while updating the layout after
     *         the action has been performed, or <code>null</code>.
     */
    ILayoutConfig execute(ActionContext context);

    /**
     * This class comprises various useful data required for performing things in diagrams.
     *
     * @author chsch
     */
    public static class ActionContext {
        
        private IViewer<?> viewer = null;
        private Trigger trigger = null;
        private KNode node = null;
        private KRendering rendering = null;
        
        /**
         * Standard constructor.
         * 
         * @param v
         *            the viewer the action was triggered in
         * @param t
         *            the trigger type that fired
         * @param n
         *            the node the action is invoked on
         * @param r
         *            the rendering the action is invoked on
         */
        public ActionContext(final IViewer<?> v, final Trigger t, final KNode n, final KRendering r) {
            this.viewer = v;
            this.trigger = t;
            this.node = n;
            this.rendering = r;
        }

        /**
         * @return the viewer
         */
        public IViewer<?> getViewer() {
            return viewer;
        }

        /**
         * @return the trigger
         */
        public Trigger getTrigger() {
            return trigger;
        }
        
        /**
         * @return the node
         */
        public KNode getNode() {
            if (node == null) {
                node = (KNode) ModelingUtil.eContainerOfType(rendering,
                        KGraphPackage.eINSTANCE.getKNode());
            }
            return node;
        }

        /**
         * @return the rendering
         */
        public KRendering getRendering() {
            return rendering;
        }


        /**
         * Reveals the source (domain) element associated with the given <code>node</code>
         * if any exists, or <code>null</code>.
         * 
         * @param <T> the expected return type
         * @param viewElement the {@link KNode} to get the associated source element for
         * @return the related source (domain) element if any associated one exists, or <code>null</code>
         */
        @SuppressWarnings("unchecked")
        public <T> T getDomainElement(final KNode viewElement) {
            return (T) this.viewer.getContextViewer().getCurrentViewContext()
                    .getSourceElement(viewElement);
        }
    }
}
