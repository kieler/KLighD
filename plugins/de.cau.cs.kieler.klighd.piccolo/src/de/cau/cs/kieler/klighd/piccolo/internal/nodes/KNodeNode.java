/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KNodeRenderingController;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * The Piccolo2D node for representing a {@code KNode}.
 * 
 * @author mri
 * @author chsch
 */
public class KNodeNode extends PLayer implements INode, ILabeledGraphElement<KNode> {

    private static final long serialVersionUID = 6311105654943173693L;
    
    /** the parent {@link INode}. */
    private INode parent;

    /** the represented {@link KNode}. */
    private KNode node;

    /** the node rendering controller deployed to manage the rendering of {@link #node}. */
    private KNodeRenderingController renderingController;

    /** a dedicated layer accommodating all attached {@link KPortNode KPortNodes}.*/
    private final PLayer portLayer;
    
    /** a dedicated layer accommodating all attached {@link KLabelNode KLabelNodes}.*/
    private final PLayer labelLayer;
    
    /** the child area for this node. */
    private KChildAreaNode childArea = null;

    /** this flag indicates whether this node is currently observed by the {@link KlighdMainCamera}. */
    private boolean isRootLayer = false;


    /**
     * Constructs a Piccolo2D node for representing a {@code KNode}.
     * 
     * @param node
     *            the node
     * @param parent
     *            the parent node
     */
    public KNodeNode(final KNode node, final INode parent) {
        super();        
        this.node = node;
        this.parent = parent;
        this.portLayer = new PLayer();
        this.labelLayer = new PLayer();
        
        this.addChild(portLayer);
        this.addChild(labelLayer);
        
        Boolean b = node.getData(KShapeLayout.class).getProperty(
                KlighdProperties.KLIGHD_SELECTION_UNPICKABLE);
        setPickable(b != null && b.equals(Boolean.TRUE) ? false : true);
        
        this.addPropertyChangeListener(PLayer.PROPERTY_CAMERAS, new PropertyChangeListener() {
            
            public void propertyChange(final PropertyChangeEvent evt) {
                final KNodeNode thisNode = KNodeNode.this;
                if (evt.getNewValue() instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    final List<PCamera> newCameras = (List<PCamera>) evt.getNewValue();
                    boolean isRoot =
                            Iterables.any(newCameras, Predicates.instanceOf(KlighdMainCamera.class));
                    thisNode.getChild(0).setVisible(!isRoot);
                    thisNode.isRootLayer = isRoot;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public KNode getGraphElement() {
        return node;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KNode, ? extends IGraphElement<KNode>> controller) {
        if (controller == null || controller instanceof KNodeRenderingController) {
            this.renderingController = (KNodeRenderingController) controller;
        } else {
            String s = "KLighD: Fault occured while building up a concrete KNode rendering: KNodeNodes"
                    + " are supposed to be controlled by KNodeRenderingControllers rather than "
                    + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KNodeRenderingController getRenderingController() {
        return this.renderingController;
    }
    
    /**
     * Adds the representation of a port to this node.
     * 
     * @param port
     *            the port representation
     */
    public void addPort(final KPortNode port) {
        portLayer.addChild(port);
    }

    /**
     * Adds the representation of a label to this node.
     * 
     * @param label
     *            the label representation
     */
    public void addLabel(final KLabelNode label) {
        labelLayer.addChild(label);
    }
    
    /**
     * Sets the child area for this node.
     * 
     * @param childArea
     *            the child area
     */
    public void setChildArea(final KChildAreaNode childArea) {
        this.childArea = childArea;
    }

    /**
     * {@inheritDoc}
     */
    public INode getParentNode() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildArea() {
        return childArea;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final PNode child) {
        if (child instanceof PLayer) {
            super.addChild(child);
        } else {
            // There is only one figure child supposed to be attached to KNodeNodes
            //  so the following is justified
            if (this.isRootLayer) {
                child.setVisible(false);
            }
            super.addChild(0, child);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        super.fullPaint(paintContext);
    }
}
