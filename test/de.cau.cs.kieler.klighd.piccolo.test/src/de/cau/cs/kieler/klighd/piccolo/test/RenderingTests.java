package de.cau.cs.kieler.klighd.piccolo.test;

import java.awt.geom.Ellipse2D;
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
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.BundleId;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelFilter;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelPath;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;


@RunWith(ModelCollectionTestRunner.class)
@BundleId("de.cau.cs.kieler.klighd.piccolo.test")
@ModelPath("testmodels/")
@ModelFilter("*.kgt")
public class RenderingTests {

    private static KNode graph;

    private static DiagramController controller;

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
    
    private static void initialize(KNode n) {
        //graph = KlighdTestUtil.loadModel("testmodels", "test1.kgt");
        graph = n;
        
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        controller = new DiagramController(graph, camera, true, false);
    }

    /**
     * Test whether the test graphs styles are the same in KGraph notation as well as in the piccolo
     * results.
     */
    @Test
    public void renderingStyleTest(final KNode node) {
        initialize(node);
        KNode target = findKNodeById("child2", graph);
        INode targetNode = findPNodeById("child2", controller.getNode());
        KlighdPath path = getKlighdPath(targetNode);
        KRendering ren = target.getData(KRendering.class);
        compareStyle(path, ren);

    }

    /**
     * Test whether the test graphs shapes are the same in KGraph notation as well as in the piccolo
     * results.
     */
    @Test
    public void renderingShapeTest(final KNode node) {
        initialize(node);
        compareShape(getKlighdPath(findPNodeById("child2", controller.getNode())),
                findKNodeById("child2", graph).getData(KRendering.class));
        compareShape(getKlighdPath(findPNodeById("sourcenode2", controller.getNode())),
                findKNodeById("sourcenode2", graph).getData(KRendering.class));
        compareShape(getKlighdPath(findPNodeById("sourcenode1", controller.getNode())),
                findKNodeById("sourcenode1", graph).getData(KRendering.class));
    }

    /**
     * Compare the shape of given renderings from a KNode and a PNode.
     * 
     * @param path
     *            piccolo object
     * @param ren
     *            KGraph object
     */
    private void compareShape(final KlighdPath path, final KRendering ren) {
        Assert.assertTrue(
                "Shape mismatched",
                (ren instanceof KRectangle && path.getShape() instanceof Rectangle2D)
                        || (ren instanceof KEllipse && path.getShape() instanceof Ellipse2D)
                        || (ren instanceof KRoundedRectangle && path.getShape() instanceof RoundRectangle2D));
    }

    /**
     * Compare the styles of given renderings from a KNode and a PNode.
     * 
     * @param path
     *            object holding styles for klighd piccolo
     * @param ren
     *            object holding styles for KGraph
     */
    private void compareStyle(final KlighdPath path, final KRendering ren) {
        List<KStyle> styles = ren.getStyles();
        KBackground bg = (KBackground) getStyle(styles, KBackground.class);
        KForeground fg = (KForeground) getStyle(styles, KForeground.class);
        KShadow sh = (KShadow) getStyle(styles, KShadow.class);
        RGB shadow = path.getShadow();
        RGB paint = path.getSWTPaint();
        RGB strokepaint = path.getStrokePaint();
        
        Assert.assertTrue("Background color mismatched", (bg == null && paint == null) 
                || ((bg.getColor().getRed() == paint.red)
                && (bg.getColor().getBlue() == paint.blue)
                && (bg.getColor().getGreen() == paint.green)));
        Assert.assertTrue("Foreground color mismatched", (fg == null && strokepaint == KlighdConstants.BLACK) || ((fg.getColor().getRed() == strokepaint.red)
                && (fg.getColor().getBlue() == strokepaint.blue)
                && (fg.getColor().getGreen() == strokepaint.green)));
        Assert.assertTrue("Shadow mismatched", (sh == null && shadow == null) || ((sh.getColor().getRed() == shadow.red)
                && (sh.getColor().getBlue() == shadow.blue)
                && (sh.getColor().getGreen() == shadow.green)));
        
    }

    /**
     * Fetch the style of a certain type out of list of styles usually attached to a KRendering. If
     * multiple of the same type are present it will return the bottom most one which should also be
     * the one that is actually displayed-
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
     * Gets the KlighdPath from the given node
     * 
     * @param node
     *            the node whose path to get
     * @return the KlighdPath attached to the given node
     */
    private KlighdPath getKlighdPath(final INode node) {
        if (node instanceof PNode) {
            for (int i = 0; i < ((PNode) node).getChildrenCount(); i++) {
                PNode pn = ((PNode) node).getChild(i);
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
    private INode findPNodeById(final String id, final INode node) {
        KIdentifier nodeID = null;
        if (node instanceof KNodeNode) {
            nodeID = ((KNodeNode) node).getGraphElement().getData(KIdentifier.class);
        }
        if ((nodeID != null) && nodeID.getId().equals(id)) {
            return node;
        } else {
            INode result = null;
            for (int i = 0; i < node.getChildAreaNode().getNodeLayer().getChildrenCount(); i++) {
                INode n = (INode) node.getChildAreaNode().getNodeLayer().getChild(i);
                result = findPNodeById(id, n);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }

    /**
     * Finds a knode with the given id in a given kgraph.
     * 
     * @param id
     *            the id to search for
     * @param graph
     *            the kgraph in which to search
     * @return the knode if found or null
     */
    private KNode findKNodeById(final String id, final KNode graph) {
        KIdentifier nodeID = graph.getData(KIdentifier.class);
        if ((nodeID != null) && nodeID.getId().equals(id)) {
            return graph;
        } else {
            KNode result = null;
            for (KNode n : graph.getChildren()) {
                result = findKNodeById(id, n);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }

}
