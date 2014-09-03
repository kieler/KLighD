package de.cau.cs.kieler.klighd.piccolo.test;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Adapter;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PAffineTransform;

/**
 * 
 * Testing of klighd piccolo adapters are correctly set/remembered on
 * creating, collapsing and expanding nodes.
 * 
 * @author ckru
 * 
 */
public class AdapterTests {

    @Test
    public void layoutTest() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);
        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        KNode next = KlighdTestUtil.makeTestGraph(root);
        KPort nextport = KlighdTestUtil.addport(next);
        KLabel nextlabel = next.getLabels().get(0);
        // create a controller for the graph
        DiagramController controller = new DiagramController(root, camera, true, false);

        // node layout
        KShapeLayout nodelayout = next.getData(KShapeLayout.class);
        nodelayout.setPos(12, 32);
        nodelayout.setSize(40, 40);
        // port layout
        KShapeLayout portlayout = nextport.getData(KShapeLayout.class);
        portlayout.setPos(6, 7);
        portlayout.setSize(5, 5);
        // label layout
        KShapeLayout labellayout = nextlabel.getData(KShapeLayout.class);
        labellayout.setPos(2, 3);
        labellayout.setSize(4, 5);

        INode node = controller.getNode();
        PLayer nodeLayer = node.getChildAreaNode().getNodeLayer();
        PNode pnext = nodeLayer.getChild(0);
        PLayer portlayer = ((KNodeNode) pnext).getPortLayer();
        PNode pport = portlayer.getChild(3);
        PLayer labellayer = ((KNodeNode) pnext).getLabelLayer();
        PNode plabel = labellayer.getChild(0);

        PAffineTransform porttransform = pport.getTransform();
        PAffineTransform labeltransform = plabel.getTransform();
        PAffineTransform nodetransform = pnext.getTransform();

        Assert.assertTrue(nodetransform.getTranslateX() == 12
                && nodetransform.getTranslateY() == 32);
        Assert.assertTrue(pnext.getBounds().height == 40 && pnext.getBounds().width == 40);

        Assert.assertTrue(labeltransform.getTranslateX() == 2
                && labeltransform.getTranslateY() == 3);
        Assert.assertTrue(plabel.getBounds().height == 5 && plabel.getBounds().width == 4);

        Assert.assertTrue(porttransform.getTranslateX() == 6 && porttransform.getTranslateY() == 7);
        Assert.assertTrue(pport.getBounds().height == 5 && pport.getBounds().width == 5);
    }
  
    /**
     * Test for checking if all kgraph elements are also existing in the pgraph.
     */
    @Test
    public void nodeTest() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);
        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        KlighdTestUtil.makeTestGraph(root);
        // create a controller for the graph
        DiagramController controller = new DiagramController(root, camera, true, false);
        INode topNode = controller.getNode();
        Assert.assertTrue(checkStructure(root, topNode));
    }
    
    /**
     * Test adding of elements was correct after expansion.
     */
    @Test
    public void nodeTestExpanded() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        DiagramController controller = new DiagramController(root, camera, true, false);
        controller.collapse(root);
        KlighdTestUtil.makeTestGraph(root);
        controller.expand(root);
        // create a controller for the graph
        INode topNode = controller.getNode();
        Assert.assertTrue(checkStructure(root, topNode));
    }
    
    /**
     * Test if all adapters are added correctly.
     */
    @Test
    public void adapterTest() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        DiagramController controller = new DiagramController(root, camera, true, false);
        KNode child = KlighdTestUtil.makeTestGraph(root);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(checkAdapters(child));
    }
    
    /**
     * Test if all adapters are added correctly.
     */
    @Test
    public void adapterTestExpanded() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        DiagramController controller = new DiagramController(root, camera, true, false);
        KNode child = KlighdTestUtil.makeTestGraph(root);
        controller.collapse(child);
        KNode cc = KlighdTestUtil.addchild(child);
        KlighdTestUtil.addport(cc);
        KlighdTestUtil.addport(cc);
        controller.expand(child);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(checkAdapters(child));
    }
    
    /**
     * Check recursively if given KNode has the correct adapters installed for a collapsed node.
     * 
     * @param kgraph
     *            the model whoose adapters to check
     * @return false if not all adapters set.
     */
    private boolean checkAdaptersCollapsed(final KNode kgraph) {
        List<Adapter> nodeadapters = kgraph.eAdapters();
        if (nodeadapters.size() != 5) {
            return false;
        }
        if (Iterables.any(nodeadapters,
                this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("KGEShapeLayoutPNodeUpdater"))
                || !Iterables.any(nodeadapters, this.getCondition("EdgeSyncAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("PortSyncAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("LabelSyncAdapter"))) {
            return false;
        }

        // check recursively for children
        for (KNode child : kgraph.getChildren()) {
            if (!checkAdapters(child)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Test if all adapters are added correctly.
     */
    //@Test
    public void adapterTestCollapsed() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        DiagramController controller = new DiagramController(root, camera, true, false);
        KNode child = KlighdTestUtil.makeTestGraph(root);
        controller.collapse(child);
        KNode cc = KlighdTestUtil.addchild(child);
        KlighdTestUtil.addport(cc);
        KlighdTestUtil.addport(cc);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(this.checkAdaptersCollapsed(cc));
    }
    
    /**
     * Check recursively if given KNode has all its adapters added correctly.
     * 
     * @param kgraph
     *            the model whoose adapters to check
     * @return false if not all adapters set.
     */
    private boolean checkAdapters(final KNode kgraph) {
        List<Adapter> nodeadapters = kgraph.eAdapters();
        if (nodeadapters.size() != 6) {
            return false;
        }

        // check adapters on node
        if (Iterables.any(nodeadapters,
                this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("ChildrenSyncAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("KGEShapeLayoutPNodeUpdater"))
                || !Iterables.any(nodeadapters, this.getCondition("EdgeSyncAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("PortSyncAdapter"))
                || !Iterables.any(nodeadapters, this.getCondition("LabelSyncAdapter"))) {
            return false;
        }

        // check adapters on ports
        for (KPort p : kgraph.getPorts()) {
            List<Adapter> portadapters = p.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables
                            .any(portadapters, this.getCondition("KGEShapeLayoutPNodeUpdater"))
                    || !Iterables.any(portadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }
        }
        // check adapters on labels
        for (KLabel l : kgraph.getLabels()) {
            List<Adapter> labeladapters = l.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables.any(labeladapters,
                            this.getCondition("KGEShapeLayoutPNodeUpdater"))
                    || !Iterables.any(labeladapters, this.getCondition("TextSyncAdapter"))) {
                return false;
            }
        }

        // check adapters on edges
        for (KEdge e : kgraph.getOutgoingEdges()) {
            List<Adapter> edgeadapters = e.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables
                            .any(edgeadapters, this.getCondition("KEdgeLayoutEdgeNodeUpdater"))
                    || !Iterables.any(edgeadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }

        }

        // check recursively for children
        for (KNode child : kgraph.getChildren()) {
            if (!checkAdapters(child)) {
                return false;
            }
        }
        return true;
    }

    
    /**
     * Recusively check if every given element of the input kgraph is also present in the Piccolo
     * structure.
     * 
     * @param kgraph
     *            the input model
     * @param piccoloTree
     *            the translated piccolo representation
     * @return true if all elements in kgraph are also present in piccolo
     */
    private boolean checkStructure(final KNode kgraph, final INode piccoloTree) {

        // check if knode is included in piccolo structure
        if (!piccoloTree.getGraphElement().toString().equals(kgraph.toString())) {
            return false;
        }
        PLayer nodeLayer = piccoloTree.getChildAreaNode().getNodeLayer();
        PLayer edgeLayer = null;
        if (piccoloTree.getParentNode() != null) {
            edgeLayer = piccoloTree.getParentNode().getChildAreaNode().getEdgeLayer();
        }
        for (int i = 0; i < kgraph.getChildren().size(); i++) {
            if (piccoloTree instanceof KNodeNode) {

                // check if all ports are included in the piccolo structure
                PLayer portLayer = ((KNodeNode) piccoloTree).getPortLayer();
                for (int j = 0; j < kgraph.getPorts().size(); j++) {
                    if (!(kgraph.getPorts().get(j) == ((ITracingElement<?>) portLayer.getChild(j))
                            .getGraphElement())) {
                        return false;
                    }
                }

                // check if all labels are included in the piccolo structure
                PLayer labelLayer = ((KNodeNode) piccoloTree).getLabelLayer();
                for (int j = 0; j < kgraph.getLabels().size(); j++) {
                    if (!(kgraph.getLabels().get(j) == ((ITracingElement<?>) labelLayer.getChild(j))
                            .getGraphElement())) {
                        return false;
                    }
                }

            }

            // check if all edges are included
            if (edgeLayer != null) {
                for (KEdge edge : kgraph.getOutgoingEdges()) {
                    boolean edgematch = false;
                    @SuppressWarnings("unchecked")
                    ListIterator<KEdgeNode> iter = edgeLayer.getChildrenIterator();
                    while (iter.hasNext()) {
                        if (((KEdgeNode) iter.next()).getGraphElement() == edge) {
                            edgematch = true;
                            break;
                        }
                    }
                    if (!edgematch) {
                        return false;
                    }

                }
            }
            // check recursively for children
            if (kgraph.getChildren() != null && nodeLayer.getChildrenCount() == 0) {
                return false;
            }
            if (!checkStructure(kgraph.getChildren().get(i), (INode) nodeLayer.getChild(i))) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Generate a predicate that checks if an adapters classname matches the given string.
     * 
     * @param name
     *            The string thats supposed to be the class name of the adapter.
     * @return true if name matches classname
     */
    private Predicate<Adapter> getCondition(final String name) {
        return new Predicate<Adapter>() {
            public boolean apply(Adapter arg0) {
                // TODO Auto-generated method stub
                return arg0.getClass().getSimpleName().equals(name);
            }

        };
    }
    
}
