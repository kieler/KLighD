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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.AffineTransform;

import org.eclipse.elk.core.klayoutdata.KLayoutDataPackage;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.graph.KGraphPackage;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdDiagramLayoutConnector;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.util.LimitedKGraphContentAdapter;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A specialized {@link LimitedKGraphContentAdapter}, which is in charge of synchronizing the
 * position the PNode that represents of the {@link de.cau.cs.kieler.core.kgraph.KGraphElement
 * KGraphElement} that this adapter is attached to. It is intended to be attached to {@link KNode
 * KNodes}, {@link de.cau.cs.kieler.core.kgraph.KPort KPorts}, and
 * {@link de.cau.cs.kieler.core.kgraph.KLabel KLabels}.<br>
 * <br>
 * Due to the fact that EMF Compare's standard mergers replace whole instances of
 * {@link KShapeLayout} when some of the attribute values have changed, such updaters are attached
 * to the {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}. They propagate
 * themselves to the available {@link KShapeLayout KShapeLayouts} or those that are added
 * afterwards.
 *
 * @author chsch
 */
class KGEShapeLayoutPNodeUpdater extends LimitedKGraphContentAdapter {

    KGEShapeLayoutPNodeUpdater(final PNode theRepNode, final DiagramController theController) {
        super(KShapeLayout.class);
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

        if (notification.getNotifier() instanceof KNode
                && notification.getNewValue() instanceof KShapeLayout) {
            shL = (KShapeLayout) notification.getNewValue();
            dataCompletelyChanged = true;
            unchanged = false;

        } else if (notification.getNotifier() instanceof KShapeLayout) {
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
                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS:
                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS:
                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH:
                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT:
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
                case KLayoutDataPackage.KSHAPE_LAYOUT__XPOS: {
                    final double newX = shL.getXpos();
                    if (newX != offsetX) {
                        nodeRep.setOffset(newX, offsetY);
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__YPOS: {
                    final double oldY = offsetY;
                    final double newY = shL.getYpos();
                    if (newY != oldY) {
                        nodeRep.setOffset(offsetX, newY);
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH: {
                    final double oldWidth = nodeRep.getWidth();
                    final double newWidth = shL.getWidth();
                    if (oldWidth != newWidth) {
                        nodeRep.setWidth(newWidth);
                    }
                    break;
                }
                case KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT: {
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