/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.klighd.krendering.PlacementUtil.Bounds;
import de.cau.cs.kieler.klighd.piccolo.krendering.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.PiccoloPlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;

/**
 * An {@link AbstractRenderingController} for KNodes generating the rendering PNodes according to
 * the related KRendering rendering description.
 * 
 * 
 * @author mri
 */
public class KNodeRenderingController extends AbstractRenderingController<KNode, KNodeNode> {

    /** the Piccolo node representing the child area. */
    private KChildAreaNode childAreaNode;

    /**
     * Constructs a rendering controller for a node.
     * 
     * @param node
     *            the Piccolo node representing a node
     */
    public KNodeRenderingController(final KNodeNode node) {
        super(node.getGraphElement(), node);
        this.childAreaNode = new KChildAreaNode(node);
        initializeRenderingNode(childAreaNode);
    }

    /**
     * Returns the Piccolo node representing the child area.
     * 
     * @return the Piccolo node representing the child area
     */
    public KChildAreaNode getChildAreaNode() {
        return childAreaNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        PNode repNode = getRepresentation();

        // detach the child area before updating the rendering
        childAreaNode.removeFromParent();

        // evaluate the rendering data
        KRendering currentRendering = getCurrentRendering();
        PNode renderingNode;
        // KPlacementData pd;
        if (currentRendering != null) {
            renderingNode = handleAreaPlacementRendering(currentRendering,
                    Collections.<KStyle>emptyList(), repNode);
        } else {
            renderingNode = handleAreaPlacementRendering(createDefaultNodeRendering(),
                    Collections.<KStyle>emptyList(), repNode);
        }

        // make sure the child area is attached to something
        if (childAreaNode.getParent() == null) {
            // if the childArea is not part of the above created PNode rendering tree
            // let the whole figure be the child area
            createDefaultChildArea(getRepresentation());
        }
        return renderingNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNodeController<?> createChildArea(final PNode parent, final Bounds initialBounds) {
        // there can only be none or one child area
        if (childAreaNode.getParent() != null) {
            throw new RuntimeException("More then one child area found in graph element: "
                    + getGraphElement());
        }

        parent.addChild(childAreaNode);

        // configure the child area
        //  Caution: Changing the bounds of a childArea must not happen if the childArea is
        //  not contained in any other PNode as this will influence the positioning of KEdgeNodes,
        //  which synchronize on the container childAreas and their parents 
        NodeUtil.applySmartBounds(childAreaNode, initialBounds);

        // create a controller for the child area and return it
        return new PNodeController<PNode>(childAreaNode) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                NodeUtil.applySmartBounds(getNode(), bounds);
            }
        };
    }

    /**
     * Creates the Piccolo node for the parent Piccolo node using direct placement.
     * 
     * @param parent
     *            the parent Piccolo node
     */
    private void createDefaultChildArea(final PNode parent) {
        // determine the initial bounds
        Bounds bounds = PiccoloPlacementUtil.evaluateAreaPlacement(null,
                parent.getBoundsReference());

        // configure the child area
        final PNodeController<?> controller = createChildArea(parent, bounds);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        Bounds bounds = PiccoloPlacementUtil.evaluateAreaPlacement(null,
                                parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });
    }

    /**
     * Creates a default rendering for nodes without attached rendering data.
     * 
     * @return the rendering
     */
    private static KRendering createDefaultNodeRendering() {
        // create the default rendering model
        return KRenderingFactory.eINSTANCE.createKRectangle();
    }
}
