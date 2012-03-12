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
import java.util.ArrayList;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.klighd.piccolo.krendering.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
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
        super(node.getWrapped(), node);
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
        if (currentRendering != null) {
                renderingNode =
                        handleDirectPlacementRendering(currentRendering, new ArrayList<KStyle>(0),
                                repNode, repNode);
        } else {
                renderingNode =
                        handleDirectPlacementRendering(createDefaultNodeRendering(),
                                new ArrayList<KStyle>(0), repNode, repNode);
        }
        
        // make sure the child area is attached to something
        if (childAreaNode.getParent() == null) {
            createDefaultChildArea(getRepresentation());
        }
        
        return renderingNode;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected PNodeController<?> createChildArea(final PNode parent, final PBounds initialBounds) {
        // there can only be none or one child area
        if (childAreaNode.getParent() != null) {
            throw new RuntimeException("More then one child area found in graph element: "
                    + getGraphElement());
        }

        // configure the child area
        NodeUtil.applySmartBounds(childAreaNode, initialBounds);

        parent.addChild(childAreaNode);

        // create a controller for the child area and return it
        return new PNodeController<PNode>(childAreaNode) {
            public void setBounds(final PBounds bounds) {
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
        PBounds bounds = evaluateDirectPlacement(null, parent.getBoundsReference());

        // configure the child area
        final PNodeController<?> controller = createChildArea(parent, bounds);

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        PBounds bounds = evaluateDirectPlacement(null, parent.getBoundsReference());
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
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rect = factory.createKRectangle();
        KForegroundColor color = factory.createKForegroundColor();
        color.setRed(0);
        color.setGreen(0);
        color.setBlue(0);
        rect.getStyles().add(color);
        return rect;
    }
    
}
