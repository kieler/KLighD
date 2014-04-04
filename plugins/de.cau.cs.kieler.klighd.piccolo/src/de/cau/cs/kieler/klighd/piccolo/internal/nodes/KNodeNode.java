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

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KNodeRenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The Piccolo2D node for representing a {link KNode}.
 * 
 * @author mri
 * @author chsch
 */
public class KNodeNode extends KDisposingLayer implements INode, ILabeledGraphElement<KNode> {

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
    private final KChildAreaNode childArea;
    
    /**
     * This camera is used if the diagram is clipped to this node and this node's child area is part
     * of the composite node figure. In this and only this particular case, the camera observing the
     * child area is set visible, since the node's figure shall not be shown on the main diagram.
     * The latter requirement is implemented in {@link #fullPaint(PPaintContext)} as setting the
     * figure node to invisible will also hide it in the outline diagram.
     */
    private final PCamera childAreaCamera; 

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
        this.portLayer = new KDisposingLayer();
        this.labelLayer = new KDisposingLayer();
        this.childArea = new KChildAreaNode(this);
        
        this.childAreaCamera = new PCamera();

        this.childAreaCamera.setPickable(true);
        this.childAreaCamera.setVisible(false);
        this.childAreaCamera.addLayer(this.childArea);
        
        this.addChild(childAreaCamera);
        this.addChild(portLayer);
        this.addChild(labelLayer);
        
        final Boolean b = node.getData(KLayoutData.class).getProperty(
                KlighdProperties.KLIGHD_SELECTION_UNPICKABLE);
        this.setPickable(b != null && b.equals(Boolean.TRUE) ? false : true);
        
        this.addPropertyChangeListener(PLayer.PROPERTY_CAMERAS, new PropertyChangeListener() {
            // this property change listener reacts on changes in the cameras list
            
            public void propertyChange(final PropertyChangeEvent evt) {
                final KNodeNode thisNode = KNodeNode.this;
                if (evt.getNewValue() instanceof List<?>) {

                    @SuppressWarnings("unchecked")
                    final List<PCamera> newCameras = (List<PCamera>) evt.getNewValue();
                    
                    // if there is a KlighdMainCamera in the list of observing cameras
                    //  that one is supposed to be the diagram main camera and, thus,
                    //  the diagram is assumed to be clipped to this node
                    final boolean isRoot =
                            Iterables.any(newCameras, Predicates.instanceOf(KlighdMainCamera.class));
                    thisNode.isRootLayer = isRoot;

                    final PNode childAreaParent = thisNode.childArea.getParent();
                    
                    if (isRoot && childAreaParent != null && childAreaParent != thisNode) {
                        // ... i.e. 'childArea' is somehow buried in the rendering nodes
                        //  set the helper 'childAreaCamera' visible and adjust its view transform
                        //  ... if that's not the case yet

                        if (thisNode.childAreaCamera.getVisible()) {
                            // if the helper camera is already visible
                            //  we're done as nothing will change
                            return;
                        }

                        thisNode.childAreaCamera.setViewTransform(NodeUtil.localToParent(
                                thisNode.childArea.getParent(), thisNode));
                        
                        thisNode.childAreaCamera.setVisible(true);
                    } else {
                        // otherwise switch the helper camera off be setting it invisible 
                        thisNode.childAreaCamera.setVisible(false);
                    }
                }
            }
        });

        this.addPropertyChangeListener(PNode.PROPERTY_BOUNDS, new PropertyChangeListener() {
            // this property change listeners is simply in charge of synchronizing the
            //  helper camera's bounds with those of this KNodeNode
            // the view transform/bounds is set by the above listener
            //  a listener on rendering figure changes requiring the (de-)activation of the
            //  camera while the the diagram is clipped to this node is sill missing (TODO)
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Rectangle2D) {
                    KNodeNode.this.childAreaCamera.setBounds((Rectangle2D) evt.getNewValue());
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
     * {@inheritDoc}
     */
    public INode getParentNode() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildAreaNode() {
        return childArea;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final PNode child) {
        // this method has been specialized in order to implement some special child ordering:
        //  1: the node's rendering figure node: a (nested) KlighdPath or KlighdStyledText;
        //  2: the node's child area node if it is not already contained in 1;
        //  3: the node's child area camera
        //  4: the node's port layer
        //  5: the node's label layer
        // the ordering or 3-5 is build up in the constructor
        
        if (child == childArea) {
            if (getChild(0) == childAreaCamera) {
                // in this case a KChildArea is the only KRendering of the KNode
                this.addChild(0, child);
                
            } else {
                // in this case no KChildArea exists in the KNode's KRendering
                //  thus another pnode made it already to position zero
                //  and the child area node is added directly to the node afterwards
                this.addChild(1, child);
            }

        } else if (child instanceof PLayer) {
            // this happens during the initialization (constructor)
            super.addChild(child);

        } else {
            // this case occurs while constructing the PNodes from the current KRendering
            
            // Since there is only one rendering child supposed to be attached to KNodeNodes
            //  the following addition at position zero is justified.
            super.addChild(0, child);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fullPick(final PPickPath pickPath) {
        final boolean fullPick = super.fullPick(pickPath);
        
        if (!fullPick && isRootLayer) {
            pickPath.pushNode(this);
            pickPath.pushTransform(getTransform());
            
            return true;
        }
        return fullPick;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        // Unfortunately I had to copy the whole method just for
        //  introducing the filter in the loop below, since 'PNode#fullPaint(...)'
        //  accesses the child list directly rather via 'getChildrenReference()'.
        // I guess it's worth a related API change in some future Piccolo2D version. 
        
        // The filter is in charge of masking out the rendering while the diagram is
        //  clipped to this node and it's being drawn via the diagram's main camera!
        // In contrast, the rendering figure is supposed to be drawn at all times
        //  while the diagram is drawn via the outline view's camera!
        
        if (getVisible() && fullIntersects(paintContext.getLocalClip())) {
            paintContext.pushTransform(getTransformReference(false));
            paintContext.pushTransparency(getTransparency());

            if (!getOccluded()) {
                paint(paintContext);
            }

            final int count = getChildrenCount();
            for (int i = 0; i < count; i++) {
                final PNode each = (PNode) getChildrenReference().get(i);
                if (i == 0 && this.isRootLayer && each != this.childArea
                        && this.getCamerasReference().contains(paintContext.getCamera())) {
                    // do not draw the node's figure on the main diagram if it is clipped to this node
                    continue;
                }
                if (each == this.childAreaCamera && each.getVisible() // implies isRootLayer == true
                        && !this.getCamerasReference().contains(paintContext.getCamera())) {
                    // do not draw the childAreaCamera on the outline view if the diagram is clipped
                    //  to this node and the paint camera is unequal to that of the main diagram.
                    //  Hence, it must be that of the outline diagram or an further one.
                    continue;
                }
                each.fullPaint(paintContext);
            }

            paintAfterChildren(paintContext);

            paintContext.popTransparency(getTransparency());
            paintContext.popTransform(getTransformReference(false));
        }
    }
    
    /**
     * Get the PortLayer.
     * @return a dedicated layer accommodating all attached {@link KPortNode KPortNodes}.
     */
    public PLayer getPortLayer() {
        return this.portLayer;
    }
    
    /**
     * Get the LabelLayer.
     * @return a dedicated layer accommodating all attached {@link KLabelNode KLabelNodes}.
     */
    public PLayer getLabelLayer() {
        return this.labelLayer;
    }
    
}
