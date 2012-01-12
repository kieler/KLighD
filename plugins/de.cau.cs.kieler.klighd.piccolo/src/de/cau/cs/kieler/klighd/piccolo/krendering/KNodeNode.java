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
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;
import edu.umd.cs.piccolo.util.PAffineTransform;

/**
 * The Piccolo node for representing a {@code KNode}.
 * 
 * @author mri
 */
public class KNodeNode extends PEmptyNode implements IParent, IWrapper<KNode> {

    private static final long serialVersionUID = 6311105654943173693L;

    /** the encapsulated {@code KNode}. */
    private KNode node;
    /** the shape layout associated with this node. */
    private KShapeLayout shapeLayout = null;

    /** the rendering controller. */
    private RenderingController renderingController;

    /**
     * Constructs a Piccolo node for representing a {@code KNode}.
     * 
     * @param node
     *            the KNode
     */
    public KNodeNode(final KNode node) {
        this.node = node;
        setPickable(true);
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
            KChildAreaNode childAreaNode = renderingController.getChildAreaNode();
            for (KNode child : node.getChildren()) {
                // create the Piccolo node for the child node
                KNodeNode nodeNode = new KNodeNode(child);
                nodeNode.updateLayout();
                childAreaNode.addChild(nodeNode);

                // create the node's rendering
                nodeNode.createRendering();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void collapse() {
        // TODO implement this
    }

    /**
     * Updates the rendering.
     */
    public void createRendering() {
        if (renderingController == null) {
            renderingController = new RenderingController(node, this);
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
                    Object notifier = notification.getNotifier();
                    if (notifier == shapeLayout) {
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
                }
            });
        }

        if (shapeLayout != null) {
            // get the old translation
            PAffineTransform transform = getTransformReference(true);
            double oldX = transform.getTranslateX();
            double oldY = transform.getTranslateY();

            // apply the layout
            translate(shapeLayout.getXpos() - oldX, shapeLayout.getYpos() - oldY);
            setBounds(0, 0, shapeLayout.getWidth(), shapeLayout.getHeight());

        }
    }

}
