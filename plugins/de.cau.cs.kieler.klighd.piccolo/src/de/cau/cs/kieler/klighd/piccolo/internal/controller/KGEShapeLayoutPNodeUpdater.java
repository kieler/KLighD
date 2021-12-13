/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.AffineTransform;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdDiagramLayoutConnector;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * An EMF adapter which is in charge of synchronizing the
 * position the PNode that represents of the {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement
 * KGraphElement} that this adapter is attached to. It is intended to be attached to
 * {@link de.cau.cs.kieler.klighd.kgraph.KNode KNodes},
 * {@link de.cau.cs.kieler.klighd.kgraph.KPort KPorts}, and
 * {@link de.cau.cs.kieler.klighd.kgraph.KLabel KLabels}.
 *
 * @author chsch
 */
class KGEShapeLayoutPNodeUpdater extends AdapterImpl {

    KGEShapeLayoutPNodeUpdater(final PNode theRepNode, final DiagramController theController) {
        this.controller = theController;
        this.nodeRep = theRepNode;
    }

    private DiagramController controller = null;
    private PNode nodeRep = null;

    @Override
    public void notifyChanged(final Notification notification) {
        super.notifyChanged(notification);

        // this method is supposed to be as fast as possible
        //  so please excuse its confusing structure ;-)

        switch (notification.getEventType()) {
        case Notification.ADD:
        case Notification.SET:
            // good cases - continue executing this method
            break;
        case Notification.ADD_MANY:
        case Notification.MOVE:
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
        case Notification.REMOVING_ADAPTER:
        case Notification.RESOLVE:
        case Notification.UNSET:
        default:
            // uninteresting cases - stop executing here
            return;
        }

        final KShapeLayout shL;
        final boolean dataCompletelyChanged;
        final boolean unchanged;

        if (notification.getNotifier() instanceof KShapeLayout) {
            shL = (KShapeLayout) notification.getNotifier();

            final Object newValue = notification.getNewValue();

            if (newValue == KlighdDiagramLayoutConnector.LAYOUT_DATA_CHANGED_VALUE) {
                dataCompletelyChanged = true;
                unchanged = false;

            } else if (newValue == KlighdDiagramLayoutConnector.LAYOUT_DATA_UNCHANGED_VALUE) {
                dataCompletelyChanged = false;
                unchanged = true;

            } else if (notification.getFeature() == KGraphPackage.eINSTANCE
                    .getEMapPropertyHolder_Properties()) {
                return;

            } else if (newValue instanceof Number) {
                dataCompletelyChanged = false;
                unchanged = false;

                switch (((EStructuralFeature) notification.getFeature()).getFeatureID()) {
                case KGraphPackage.KSHAPE_LAYOUT__XPOS:
                case KGraphPackage.KSHAPE_LAYOUT__YPOS:
                case KGraphPackage.KSHAPE_LAYOUT__WIDTH:
                case KGraphPackage.KSHAPE_LAYOUT__HEIGHT:
                    break;
                default:
                    return;
                }
            } else {
                return;
            }

        } else {
            return;
        }

        if (controller.isRecording()) {
            if (unchanged) {
                // if the layout data did not change, provide that information anyway,
                //  since nodeRep may be invisible and is set visible by updating the layout data
                controller.recordChange((IKGraphElementNode) nodeRep,
                        KlighdDiagramLayoutConnector.LAYOUT_DATA_UNCHANGED_VALUE);
            } else {
                controller.recordChange((IKGraphElementNode) nodeRep, getBounds(shL));
            }
            return;

        } else if (unchanged) {
            return;

        } else {
            if (dataCompletelyChanged) {
                NodeUtil.applyBounds(nodeRep, shL);

            } else {
                final AffineTransform localTransform = nodeRep.getTransformReference(true);
                final double offsetX = localTransform.getTranslateX();
                final double offsetY = localTransform.getTranslateY();

                switch (notification.getFeatureID(KShapeLayout.class)) {
                case KGraphPackage.KSHAPE_LAYOUT__XPOS: {
                    final double newX = shL.getXpos();
                    if (newX != offsetX) {
                        nodeRep.setOffset(newX, offsetY);
                    }
                    break;
                }
                case KGraphPackage.KSHAPE_LAYOUT__YPOS: {
                    final double oldY = offsetY;
                    final double newY = shL.getYpos();
                    if (newY != oldY) {
                        nodeRep.setOffset(offsetX, newY);
                    }
                    break;
                }
                case KGraphPackage.KSHAPE_LAYOUT__WIDTH: {
                    final double oldWidth = nodeRep.getWidth();
                    final double newWidth = shL.getWidth();
                    if (oldWidth != newWidth) {
                        nodeRep.setWidth(newWidth);
                    }
                    break;
                }
                case KGraphPackage.KSHAPE_LAYOUT__HEIGHT: {
                    final double oldHeight = nodeRep.getHeight();
                    final double newHeight = shL.getHeight();
                    if (oldHeight != newHeight) {
                        nodeRep.setHeight(newHeight);
                    }
                    break;
                }
                default:
                    break;
                }

                shL.resetModificationFlag();
            }

            final AbstractKGERenderingController<?, ?> nodeController =
                    NodeUtil.asKGENode(nodeRep).getRenderingController();
            if (nodeController != null) {
                nodeController.modifyStyles();
            }

            nodeRep.firePropertyChange(0, IKlighdNode.PROPERTY_BOUNDS_FINISHED, null, Boolean.TRUE);
        }
    }

    /**
     * Returns bounds from the given {@code KShapeLayout}.
     *
     * @param shapeLayout
     *            the shape layout
     * @return the bounds
     */
    private static PBounds getBounds(final KShapeLayout shapeLayout) {
        final PBounds bounds = new PBounds();

        bounds.setRect(shapeLayout.getXpos(), shapeLayout.getYpos(), shapeLayout.getWidth(),
                shapeLayout.getHeight());
        return bounds;
    }

}