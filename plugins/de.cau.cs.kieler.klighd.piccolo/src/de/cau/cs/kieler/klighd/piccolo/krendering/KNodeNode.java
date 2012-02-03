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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.nodes.PZIndexNode;
import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.util.PAffineTransform;

/**
 * The Piccolo node for representing a {@code KNode}.
 * 
 * @author mri
 */
public class KNodeNode extends PZIndexNode implements INode, IWrapper<KNode> {

    private static final long serialVersionUID = 6311105654943173693L;

    /** the number of z-layers (rendering and ports). */
    private static final int Z_LAYERS = 2;
    /** the z-index for the port layer. */
    private static final int PORT_LAYER = 1;

    /** the encapsulated {@code KNode}. */
    private KNode node;
    /** the shape layout associated with this node. */
    private KShapeLayout shapeLayout = null;

    /** the parent node. */
    private INode parent;

    /** the rendering controller. */
    private RenderingController renderingController;

    /**
     * Constructs a Piccolo node for representing a {@code KNode}.
     * 
     * @param node
     *            the node
     * @param parent
     *            the parent node
     */
    public KNodeNode(final KNode node, final INode parent) {
        super(Z_LAYERS);
        this.node = node;
        this.parent = parent;
        setPickable(true);
        RenderingContextData.get(node).setProperty(INode.PREPRESENTATION, this);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getWrapped() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public void expand() {
        if (renderingController != null) {
            renderingController.getChildAreaNode().populate(node);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void collapse() {
        // TODO implement this
    }

    /**
     * Creates the rendering.
     */
    public void updateRendering() {
        if (renderingController == null) {
            renderingController = new RenderingController(this);
            renderingController.initialize();
        }
    }

    /**
     * Updates the layout.
     */
    public void updateLayout() {
        // try to get the shape layout
        if (shapeLayout == null) {
            shapeLayout = node.getData(KShapeLayout.class);

            // register adapter on the shape layout to stay in sync
            shapeLayout.eAdapters().add(new AdapterImpl() {
                public void notifyChanged(final Notification notification) {
                    switch (notification.getFeatureID(KShapeLayout.class)) {
                    case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                PAffineTransform transform = getTransformReference(true);
                                double oldX = transform.getTranslateX();
                                translate(shapeLayout.getXpos() - oldX, 0);
                            }
                        }, false);
                        break;
                    case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                PAffineTransform transform = getTransformReference(true);
                                double oldY = transform.getTranslateY();
                                translate(0, shapeLayout.getYpos() - oldY);
                            }
                        }, false);
                        break;
                    case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                setWidth(shapeLayout.getWidth());
                            }
                        }, false);
                        break;
                    case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                setHeight(shapeLayout.getHeight());
                            }
                        }, false);
                        break;
                    }
                }
            });
        }

        // apply the layout
        if (shapeLayout != null) {
            NodeUtil.applySmartBounds(this, shapeLayout.getXpos(), shapeLayout.getYpos(),
                    shapeLayout.getWidth(), shapeLayout.getHeight());

        }
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
        return renderingController.getChildAreaNode();
    }

}
