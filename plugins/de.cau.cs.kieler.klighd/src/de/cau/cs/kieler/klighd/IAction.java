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
import de.cau.cs.kieler.core.krendering.KTrigger;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Common interface of classes providing implementations of
 * {@link de.cau.cs.kieler.core.krendering.KAction KActions}.
 * 
 * @author chsch
 */
public interface IAction {
    
    /**
     * This method performs the intended action on a diagram.
     * 
     * @param context
     *            an {@link ActionContext} instance providing various useful data.
     * @return true of the action could be performed successful, false otherwise
     */
    boolean execute(ActionContext context);

    /**
     * This class comprises various useful data required for performing things in diagrams.
     *
     * @author chsch
     */
    public static class ActionContext {
        
        private IViewer<?> viewer = null;
        private KTrigger trigger = null;
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
        public ActionContext(final IViewer<?> v, final KTrigger t, final KNode n, final KRendering r) {
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
        public KTrigger getTrigger() {
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
    }
}
