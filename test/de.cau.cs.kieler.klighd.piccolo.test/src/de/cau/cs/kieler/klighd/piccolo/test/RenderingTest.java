/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.test;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.KGraphStandaloneSetup;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IKGraphElementNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.BundleId;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelFilter;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelPath;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;

/**
 *
 * Tests that check whether styles and shapes that are defined in the KGraph also show up
 * equivalently in the underlying piccolo graph.
 *
 * @author ckru
 *
 */
@RunWith(ModelCollectionTestRunner.class)
@BundleId("de.cau.cs.kieler.klighd.piccolo.test")
@ModelPath("testModels/")
@ModelFilter("KlighdRenderingTestModel.kgt")
public class RenderingTest {

    private KNode graph;

    private DiagramController controller;

    /**
     * Provides a {@link ResourceSet} in order to load the models properly.
     *
     * @return the required {@link ResourceSet}
     */
    @ModelCollectionTestRunner.ResourceSet
    public static ResourceSet getResourceSet() {
        return new KGraphStandaloneSetup().createInjectorAndDoEMFRegistration().getInstance(
                XtextResourceSet.class);
    }

    /**
     * Some initialization to insert input KGraph into some Klighd structures.
     *
     * @param n
     *            KNode representing the input KGraph
     */
    private void initialize(final KNode n) {
        graph = n;

        final KlighdMainCamera camera = new KlighdMainCamera();
        final PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        controller = new DiagramController(graph, camera, true, false);
    }

    /**
     * Test whether the test graphs styles are the same in KGraph notation as well as in the piccolo
     * results.
     *
     * @param node
     *            test kgraph
     */
    @Test
    public void renderingStyleTest(final KNode node) {
        initialize(node);

        final String id = getKNodeId(node);

        final IKNodeNode targetNode = findPNodeById(id, controller.getNode());
        final KlighdPath path = getKlighdPath(targetNode);
        final KRendering ren = node.getData(KRendering.class);
        if (path != null && ren != null) {
            compareStyle(path, ren, node);
        }
        for (final KNode child: node.getChildren()) {
            renderingStyleTest(child);
        }
    }

    /**
     * Test whether the test graphs shapes are the same in KGraph notation as well as in the piccolo
     * results.
     *
     * @param node
     *            test kgraph
     */
    @Test
    public void renderingShapeTest(final KNode node) {
        initialize(node);

        final String id = getKNodeId(node);
        final IKNodeNode targetNode = findPNodeById(id, controller.getNode());
        final KlighdPath path = getKlighdPath(targetNode);
        final KRendering ren = node.getData(KRendering.class);
        if (path != null && ren != null) {
            compareShape(path, ren, node);
        }
        for (final KNode child: node.getChildren()) {
            renderingShapeTest(child);
        }
    }

    private String getKNodeId(final KNode node) {
        final KIdentifier ki = node.getData(KIdentifier.class);
        final String id;
        if (ki != null) {
            id = ki.getId();
        } else {
            id = node.toString();
        }
        return id;
    }

    /**
     * Compare the shape of given renderings from a KNode and a PNode.
     *
     * @param path
     *            piccolo object
     * @param ren
     *            KGraph object
     */
    private void compareShape(final KlighdPath path, final KRendering ren, final KNode node) {
        final String id = getKNodeId(node);
        Assert.assertTrue(
                "Shape mismatched on node " + id,
                (ren instanceof KRectangle && path.getShape() instanceof Rectangle2D)
                        || (ren instanceof KEllipse && path.getShape() instanceof Ellipse2D)
                        || (ren instanceof KRoundedRectangle
                                && path.getShape() instanceof RoundRectangle2D)
                        || (ren instanceof KPolygon && path.getShape() instanceof Path2D));
    }

    /**
     * Compare the styles of given renderings from a KNode and a PNode.
     *
     * @param path
     *            object holding styles for klighd piccolo
     * @param ren
     *            object holding styles for KGraph
     */
    private void compareStyle(final KlighdPath path, final KRendering ren, final KNode node) {
        final List<KStyle> styles = ren.getStyles();
        final KBackground bg = (KBackground) getStyle(styles, KBackground.class);
        final KForeground fg = (KForeground) getStyle(styles, KForeground.class);
        final KShadow sh = (KShadow) getStyle(styles, KShadow.class);
        final RGB shadow = path.getShadow();
        final RGB paint = path.getSWTPaint();
        final RGBGradient paintGradient = path.getSWTPaintGradient();
        final RGB strokepaint = path.getStrokePaint();
        final RGBGradient strokeGradient = path.getStrokePaintGradient();

        final String id = getKNodeId(node);
        if (!(bg != null && paint == null)) {
            Assert.assertTrue("Background color mismatched on node " + id,
                    (bg == null && paint == null)
                            || ((bg.getColor().getRed() == paint.red)
                                    && (bg.getColor().getBlue() == paint.blue) && (bg.getColor()
                                    .getGreen() == paint.green)));
        } else {
            Assert.assertTrue("Background gradient color missing on node " + id, (paintGradient != null));
            Assert.assertTrue("Gradient color mismatch on node " + id, ((bg.getColor().getRed() == paintGradient.getColor1().red)
                                    && (bg.getColor().getBlue() == paintGradient.getColor1().blue) && (bg.getColor()
                                    .getGreen() == paintGradient.getColor1().green)));
            Assert.assertTrue("Gradient target color mismatch on node " + id, ((bg.getTargetColor().getRed() == paintGradient.getColor2().red)
                    && (bg.getTargetColor().getBlue() == paintGradient.getColor2().blue) && (bg.getTargetColor()
                    .getGreen() == paintGradient.getColor2().green)));
        }
        if (!(fg != null && strokepaint == null)) {
            Assert.assertTrue("Foreground color mismatched on node " + id,
                    (fg == null && strokepaint == KlighdConstants.BLACK)
                            || ((fg.getColor().getRed() == strokepaint.red)
                                    && (fg.getColor().getBlue() == strokepaint.blue) && (fg.getColor()
                                    .getGreen() == strokepaint.green)));
        } else {
            Assert.assertTrue("Background gradient color missing on node " + id, (strokeGradient != null));
            Assert.assertTrue("Gradient stroke color mismatch on node " + id, ((fg.getColor().getRed() == strokeGradient.getColor1().red)
                    && (fg.getColor().getBlue() == strokeGradient.getColor1().blue) && (fg.getColor()
                    .getGreen() == strokeGradient.getColor1().green)));
            Assert.assertTrue("Gradient stroke target color mismatch on node " + id, ((fg.getTargetColor().getRed() == strokeGradient.getColor2().red)
                && (fg.getTargetColor().getBlue() == strokeGradient.getColor2().blue) && (fg.getTargetColor()
                        .getGreen() == strokeGradient.getColor2().green)));
        }
        Assert.assertTrue("Shadow mismatched on node " + id,
                (sh == null && shadow == null)
                        || ((sh.getColor().getRed() == shadow.red)
                                && (sh.getColor().getBlue() == shadow.blue) && (sh.getColor()
                                .getGreen() == shadow.green)));

    }

    /**
     * Fetch the style of a certain type out of list of styles usually attached to a KRendering. If
     * multiple of the same type are present it will return the bottom most one which should also be
     * the one that is actually displayed.
     *
     * @param styles
     *            a list of styles, usually gotten from a KRendering
     * @param type
     *            the type of style to look for
     * @return the style of given type or null if none found
     */
    private KStyle getStyle(final List<KStyle> styles, final Class<?> type) {
        for (int i = (styles.size() - 1); i >= 0; i--) {
            if (type.isInstance(styles.get(i))) {
                return styles.get(i);
            }
        }
        return null;
    }

    /**
     * Gets the KlighdPath from the given node.
     *
     * @param node
     *            the node whose path to get
     * @return the KlighdPath attached to the given node
     */
    private KlighdPath getKlighdPath(final IKNodeNode node) {
        if (node instanceof PNode) {
            for (int i = 0; i < ((PNode) node).getChildrenCount(); i++) {
                final PNode pn = ((PNode) node).getChild(i);
                if (pn instanceof KlighdPath) {
                    return (KlighdPath) pn;
                }
            }
        }
        return null;
    }

    /**
     * Finds a node with the given id in a given piccolo graph.
     *
     * @id the id to search for
     * @param graph
     *            the piccolo graph in which to search
     * @return the node if found or null
     */
    private IKNodeNode findPNodeById(final String id, final IKNodeNode node) {
        KIdentifier nodeID = null;
        if (node instanceof KNodeNode) {
            nodeID = ((KNodeNode) node).getGraphElement().getData(KIdentifier.class);
        }
        if ((nodeID != null) && nodeID.getId().equals(id)) {
            return node;
        } else {
            IKNodeNode result = null;
            final KChildAreaNode kcan = node.getChildAreaNode();
            final PLayer nlay = kcan.getNodeLayer();
            if (nlay != null) {
                for (int i = 0; i < nlay.getChildrenCount(); i++) {
                    final IKNodeNode n = (IKNodeNode) nlay.getChild(i);
                    result = findPNodeById(id, n);
                    if (result != null) {
                        return result;
                    }
                }
            }
            return null;
        }
    }
}
