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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRenderingUtil;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.krendering.KCustomRenderingWrapperFactory;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomConnectionFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdImage;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode.HAlignment;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode.VAlignment;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PolylineUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;
import edu.umd.cs.piccolo.PNode;

/**
 * Collection of KRendering-related figure creation methods.<br>
 * These methods are outsourced from {@link AbstractKGERenderingController} in order
 * to reduce the size of that class.
 *
 * @author chsch
 */
final class KGERenderingControllerHelper {

    /**
     * Standard constructor.
     */
    private KGERenderingControllerHelper() {
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KEllipse}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param ellipse
     *            the ellipse rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createEllipse(
            final AbstractKGERenderingController<?, ?> controller, final KEllipse ellipse,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        final KlighdPath path = new KlighdPath(ellipse);
        path.setPathToEllipse(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (ellipse.getChildren().size() > 0) {
            controller.handleChildren(ellipse.getChildren(), ellipse.getChildPlacement(),
                    propagatedStyles, path);
        }

        // return a controller for the ellipse
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToEllipse(0, 0, bounds.getWidth(), bounds.getHeight());

                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRectangle}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param rect
     *            the rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createRectangle(
            final AbstractKGERenderingController<?, ?> controller, final KRectangle rect,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        // create the rectangle
        final KlighdPath path = new KlighdPath(rect);
        path.setPathToRectangle(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            controller.handleChildren(rect.getChildren(), rect.getChildPlacement(),
                    propagatedStyles, path);
        }

        // create a controller for the rectangle and return it
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToRectangle(0, 0, bounds.getWidth(), bounds.getHeight());

                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRoundedRectangle}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param rect
     *            the rounded rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createRoundedRectangle(
            final AbstractKGERenderingController<?, ?> controller, final KRoundedRectangle rect,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        final float cornerWidth = 2 * rect.getCornerWidth();
        final float cornerHeight = 2 * rect.getCornerHeight();

        // create the rounded rectangle
        final KlighdPath path = new KlighdPath(rect);
        path.setPathToRoundRectangle(0, 0, initialBounds.getWidth(), initialBounds.getHeight(),
                cornerWidth, cornerHeight);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            controller.handleChildren(rect.getChildren(), rect.getChildPlacement(),
                    propagatedStyles, path);
        }

        // create a controller for the rounded rectangle and return it
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToRoundRectangle(0, 0, bounds.getWidth(), bounds.getHeight(),
                        cornerWidth, cornerHeight);

                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KArc}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param arc
     *            the arc rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createArc(
            final AbstractKGERenderingController<?, ?> controller, final KArc arc,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        // create the arc
        final KlighdPath path = new KlighdPath(arc);
        path.setPathToArc(0, 0, initialBounds.getWidth(), initialBounds.getHeight(),
                arc.getStartAngle(), arc.getArcAngle(), arc.getArcType().getValue());
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (arc.getChildren().size() > 0) {
            controller.handleChildren(arc.getChildren(), arc.getChildPlacement(), propagatedStyles,
                    path);
        }

        // create a controller for the rounded rectangle and return it
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToArc(0, 0, bounds.getWidth(), bounds.getHeight(),
                        arc.getStartAngle(), arc.getArcAngle(), arc.getArcType().getValue());

                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a {@code PSWTText} representation for the {@code KText}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param text
     *            the text rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo2D node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo2D node
     */
    static PNodeController<KlighdStyledText> createText(
            final AbstractKGERenderingController<?, ?> controller, final KText text,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        // create the text
        final KlighdStyledText textNode = new KlighdStyledText(text);

        // create the alignment node wrapping the text
        final PAlignmentNode alignmentNode = new PAlignmentNode();
        alignmentNode.translate(initialBounds.getX(), initialBounds.getY());
        alignmentNode.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        alignmentNode.addChild(textNode);
        alignmentNode.setHorizontalAlignment(textNode, HAlignment.CENTER);
        alignmentNode.setVerticalAlignment(textNode, VAlignment.CENTER);
        parent.addChild(alignmentNode);

        // create a controller for the text and return it
        return new KlighdTextController(textNode) {

            @Override
            public PNode getTransformedNode() {
                return alignmentNode;
            }

            @Override
            public void setBounds(final Bounds bounds) {
                NodeUtil.applyBounds(this, bounds);
            }

            @Override
            public void setHorizontalAlignment(final HAlignment alignment) {
                alignmentNode.setHorizontalAlignment(getNode(), alignment);
            }

            @Override
            public void setVerticalAlignment(final VAlignment alignment) {
                alignmentNode.setVerticalAlignment(getNode(), alignment);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolyline} or
     * {@code KSpline}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param line
     *            the polyline or spline rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createLine(
            final AbstractKGERenderingController<?, ?> controller, final KPolyline line,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        final KlighdPath path = new KlighdPath(line);
        final Point2D[] points = PiccoloPlacementUtil.evaluatePolylinePlacement(line, initialBounds);

        // use the additional variable while updating the line later on in order to avoid 'instanceof'
        final int type;

        if (line instanceof KSpline) {
            // create the spline
            path.setPathToSpline(points);
            type = KRenderingPackage.KSPLINE;
        } else if (line instanceof KRoundedBendsPolyline) {
            // create the rounded bends polyline
            path.setPathToRoundedBendPolyline(points, ((KRoundedBendsPolyline) line).getBendRadius());
            type = KRenderingPackage.KROUNDED_BENDS_POLYLINE;
        } else {
            // create the polyline
            path.setPathToPolyline(points);
            type = KRenderingPackage.KPOLYLINE;
        }

        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (line.getChildren().size() > 0) {
            final List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : line.getChildren()) {
                if (PiccoloPlacementUtil.getDecoratorPlacementData(rendering) != null) {
                    controller.handleDecoratorPlacementRendering(rendering, propagatedStyles, path);
                } else {
                    restChildren.add(rendering);
                }
            }

            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // chsch: Why is that proxy node needed. Don't see the point...
                //
                // create a proxy parent for the children without decorator placement data
                // final PNode proxyParent = new KlighdNode();
                // path.addChild(proxyParent);
                // NodeUtil.applyBounds(proxyParent, path.getBoundsReference());
                // controller.addListener(PNode.PROPERTY_BOUNDS, path, proxyParent,
                //        new PropertyChangeListener() {
                //            public void propertyChange(final PropertyChangeEvent arg0) {
                //                NodeUtil.applyBounds(proxyParent, path.getBoundsReference());
                //            }
                //        });

                controller.handleChildren(restChildren, line.getChildPlacement(), propagatedStyles,
                        path); //proxyParent);
            }
        }

        // create a controller for the polyline and return it
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds

                final Point2D[] points = PiccoloPlacementUtil.evaluatePolylinePlacement(line, bounds);

                if (type == KRenderingPackage.KSPLINE) {
                    // update spline
                    getNode().setPathToSpline(points);
                } else if (type == KRenderingPackage.KROUNDED_BENDS_POLYLINE) {
                    // update rounded bend polyline
                    getNode().setPathToRoundedBendPolyline(points,
                            ((KRoundedBendsPolyline) line).getBendRadius());
                } else {
                    // update polyline
                    getNode().setPathToPolyline(points);
                }

                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolygon}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param polygon
     *            the polygon rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createPolygon(
            final AbstractKGERenderingController<?, ?> controller, final KPolygon polygon,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        // create the polygon
        final KlighdPath path = new KlighdPath(polygon);
        path.setPathToPolygon(PiccoloPlacementUtil.evaluatePolylinePlacement(polygon, initialBounds));
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (polygon.getChildren().size() > 0) {
            final List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : polygon.getChildren()) {
                if (PiccoloPlacementUtil.getDecoratorPlacementData(rendering) != null) {
                    controller.handleDecoratorPlacementRendering(rendering, propagatedStyles, path);
                } else {
                    restChildren.add(rendering);
                }
            }

            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // chsch: Why is that proxy node needed. Don't see the point...
                //
                // create a proxy parent for the children without decorator placement data
                // final PNode proxyParent = new PEmptyNode();
                // path.addChild(proxyParent);
                // NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                // controller.addListener(PNode.PROPERTY_BOUNDS, path, proxyParent,
                //         new PropertyChangeListener() {
                //             public void propertyChange(final PropertyChangeEvent arg0) {
                //                 NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                //             }
                //         });

                controller.handleChildren(restChildren, polygon.getChildPlacement(), propagatedStyles,
                        path); //proxyParent);
            }
        }

        // create a controller for the polyline and return it
        return new KlighdPathController(path) {
            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToPolygon(
                        (PiccoloPlacementUtil.evaluatePolylinePlacement(polygon, bounds)));
                NodeUtil.applyTranslation(this, bounds);
            }
        };
    }

    /**
     * Creates a representation for the {@code KRenderingRef}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param renderingReference
     *            the rendering reference
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createRenderingReference(
            final AbstractKGERenderingController<?, ?> controller,
            final KRenderingRef renderingReference, final List<KStyle> propagatedStyles,
            final PNode parent, final Bounds initialBounds) {

        final KRendering rendering = renderingReference.getRendering();
        if (rendering == null) {
            // create a dummy node
            return createDummy(parent, initialBounds);
        }

        final List<KStyle> renderingStyles = renderingReference.getStyles();

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles =
                Lists.newLinkedList(Iterables.concat(renderingStyles, propagatedStyles));

        // dispatch the rendering
        final PNodeController<?> pnodeController = controller.createRendering(rendering,
                childPropagatedStyles, parent, initialBounds);

        // remember the KRendering-controller pair in the controller's 'pnodeControllers' map
        controller.addPNodeController(rendering, pnodeController);

        // return a controller for the reference which sets the bounds of the referenced node
        return new PNodeController<PNode>(pnodeController.getNode()) {

            @Override
            public void applyChanges(final Styles styles) {
                // delegate to the controller of the KlighdNode representing the referenced rendering
                //  this is required in order to react on styling changes of the renderingRef or
                //  (propagated) styling changes of the renderingRefs' parents
                pnodeController.applyChanges(styles);
            }

            @Override
            public void setBounds(final Bounds bounds) {
                pnodeController.setBounds(bounds);
            }
        };
    }

    /**
     * Creates a representation for the {@link KImage}.
     *
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param image
     *            the image rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createImage(final AbstractKGERenderingController<?, ?> controller,
            final KImage image, final List<KStyle> propagatedStyles, final PNode parent,
            final Bounds initialBounds) {

        final KlighdImage imageNode;

        // create the image, the bounds of imageNode are set within the KlighdImage implementation
        if (image.getImageObject() instanceof Image) {
            imageNode = new KlighdImage((Image) image.getImageObject());

        } else if (image.getImageObject() instanceof ImageData) {
            imageNode = new KlighdImage((ImageData) image.getImageObject());

        } else if (image.getBundleName() == null) {
            final String msg =
                    "KLighD: Error occurred while loading an image from a bundle "
                    + "('imageObject' == null): 'bundleName' is null, too, which is not expected!";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg), StatusManager.LOG);
            return createDummy(parent, initialBounds);

        } else {
            // determine the containing bundle,
            // trim potentially leading and trailing quotation marks
            final String bundleName = image.getBundleName().replace("\"", "");

            final Bundle bundle = Platform.getBundle(bundleName);

            if (bundle == null) {
                final String msg = "KLighD: Error occurred while loading an image from bundle "
                        + image.getBundleName()
                        + " : Bundle is not available!";
                StatusManager.getManager().handle(new Status(
                        IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg), StatusManager.LOG);
                return createDummy(parent, initialBounds);

            } else if (image.getImagePath() == null) {
                final String msg = "KLighD: Error occurred while loading an image from bundle "
                        + bundleName + " : 'imagePath' is null!" + KlighdPlugin.LINE_SEPARATOR
                        + "Provide a valid bundle relative path of the image!";
                StatusManager.getManager().handle(new Status(
                        IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg), StatusManager.LOG);
                return createDummy(parent, initialBounds);

            } else {
                final String imagePath = image.getImagePath().replace("\"", "");
                final URL entry = bundle.getEntry(imagePath);

                if (entry == null) {
                    final String msg = "KLighD: Error occurred while loading an image from bundle "
                            + bundleName + " : No entry could be found on path " + imagePath
                            + KlighdPlugin.LINE_SEPARATOR
                            + "Provide a valid bundle relative path of the image!";
                    StatusManager.getManager().handle(new Status(
                            IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg), StatusManager.LOG);
                    return createDummy(parent, initialBounds);

                } else {
                    imageNode = new KlighdImage(bundleName, imagePath);
                }
            }
        }

        // initialize the node
        imageNode.translate(initialBounds.getX(), initialBounds.getY());
        imageNode.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        parent.addChild(imageNode);

        if (image.getClipShape() != null) {
            imageNode.setClip(updateClipShape(image.getClipShape(), initialBounds, null));
        }

        // handle children
        if (image.getChildren().size() > 0) {
            controller.handleChildren(image.getChildren(), image.getChildPlacement(),
                    propagatedStyles, imageNode);
        }

        // create a standard default node controller
        return new PNodeController<KlighdImage>(imageNode) {

            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                NodeUtil.applyBounds(this, bounds);

                final KRendering clip = image.getClipShape();
                if (clip != null) {
                    updateClipShape(image.getClipShape(), bounds, getNode().getClip());
                }
            }
        };
    }

    /**
     * Constructs an AWT {@link Shape} being used for configuring the clip while drawing the
     * corresponding {@link KlighdImage}.
     *
     * @param rendering
     *            a {@link KRectangle}, {@link KEllipse}, or {@link KRenderingRef} pointing to a
     *            rendering of the former types
     * @param imageBounds
     *            the computed bounds of the image to be drawn on the diagram
     * @return the desired clip denoting {@link Shape}
     */
    private static Shape updateClipShape(final KRendering rendering, final Bounds imageBounds,
            final RectangularShape currentClip) {
        // resolve the KRendering (if 'rendering' is a KRenderingRef)
        final KRendering clipRendering = KRenderingUtil.dereference(rendering);

        final KPlacementData pcd = KRenderingUtil.getPlacementData(clipRendering);
        final KAreaPlacementData apd = KRenderingUtil.asAreaPlacementData(pcd);
        final KPointPlacementData ppd = KRenderingUtil.asPointPlacementData(pcd);

        // calculate the clip's bounds based on the image's bounds and the provided placement data
        final Bounds bounds;

        if (ppd != null) {
            bounds = PlacementUtil.evaluatePointPlacement(ppd,
                    PlacementUtil.estimateSize(clipRendering, Bounds.of(0, 0)),
                    imageBounds);

        } else if (apd != null) {
            bounds = PlacementUtil.evaluateAreaPlacement(apd, imageBounds);

        } else {
            bounds = Bounds.of(imageBounds.getWidth(), imageBounds.getHeight());
        }

        // now build up the clip shape based on the revealed KRendering's type
        final Shape clipShape;

        if (currentClip != null) {
            return bounds.setBoundsOf(currentClip);

        } else if (clipRendering instanceof KRectangle) {
            clipShape = bounds.toRectangle2D();

        } else if (clipRendering instanceof KEllipse) {
            clipShape = bounds.toEllipse2D();

        } else if (clipRendering instanceof KPolygon) {
            final Point2D[] points = PiccoloPlacementUtil.evaluatePolylinePlacement(
                    (KPolygon) clipRendering, imageBounds);
            clipShape = PolylineUtil.createPolygonPath(null, points);

        } else {
            clipShape = null;
        }

        return clipShape;
    }

    /**
     * Creates a representation for the {@code KCustomRendering}.
     *
     * @param customRendering
     *            the custom rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createCustomRendering(
            final AbstractKGERenderingController<?, ?> controller,
            final KCustomRendering customRendering, final List<KStyle> propagatedStyles,
            final PNode parent, final Bounds initialBounds) {

        // get a wrapping PNode containing the actual figure by means of the
        //  KCustomRenderingWrapperFactory
        final KCustomFigureNode node;
        if (customRendering.getFigureObject() != null) {
            if (parent instanceof KEdgeNode) {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getFigureObject(), KCustomConnectionFigureNode.class);
            } else {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getFigureObject(), KCustomFigureNode.class);
            }

        } else {
            if (parent instanceof KEdgeNode) {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getBundleName(), customRendering.getClassName(),
                        KCustomConnectionFigureNode.class);
            } else {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getBundleName(), customRendering.getClassName(),
                        KCustomFigureNode.class);
            }
        }
        if (node == null) {
            return createDummy(parent, initialBounds);
        }
        // initialize the bounds of the node
        node.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());

        // initialize the node
        node.setRendering(customRendering);
        node.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(node);

        // handle children
        if (customRendering.getChildren().size() > 0) {
            controller.handleChildren(customRendering.getChildren(),
                    customRendering.getChildPlacement(), propagatedStyles, node);
        }

        // create a standard default node controller
        return new KCustomFigureController(node) {

            @Override
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                NodeUtil.applyBounds(this, bounds);
            }
        };
    }

    /**
     * Creates a dummy node.
     *
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createDummy(final PNode parent, final Bounds initialBounds) {
        final PNode dummyChild = new KlighdNode.KlighdFigureNode<KRendering>();
        NodeUtil.applyBounds(dummyChild, initialBounds);
        parent.addChild(dummyChild);
        return new PNodeController<PNode>(dummyChild) {

            @Override
            public void setBounds(final Bounds bounds) {
                NodeUtil.applyBounds(this, bounds);
            }
        };
    }
}
