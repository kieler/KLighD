/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2021 by
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
package de.cau.cs.kieler.klighd.piccolo.test;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.emf.common.notify.Adapter;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeAbstractNode;
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
public class AdapterTest {

    @Test
    public void layoutTest() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());
        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final KNode next = KlighdTestUtil.makeTestGraph(root);
        final KPort nextport = KlighdTestUtil.addport(next);
        final KLabel nextlabel = next.getLabels().get(0);
        final KEdge edge = KlighdTestUtil.connectNodes(next, next, nextport, null);

        // create a controller for the graph
        final DiagramController controller = createDiagramController(root, camera);

        // node layout
        next.setPos(12, 32);
        next.setSize(40, 40);
        // port layout
        nextport.setPos(6, 7);
        nextport.setSize(5, 5);
        // label layout
        nextlabel.setPos(2, 3);
        nextlabel.setSize(4, 5);
        // edge layout
        edge.applyVectorChain(
            new KVectorChain(
                new KVector(1,1), new KVector(1, 10), new KVector(10, 1)
            )
        );

        final KNodeAbstractNode node = controller.getNode();
        final PLayer nodeLayer = node.getChildAreaNode().getNodeLayer();
        final PNode pnext = nodeLayer.getChild(0);
        final PLayer portlayer = ((KNodeNode) pnext).getPortLayer();
        final PNode pport = portlayer.getChild(3);
        final PLayer labellayer = ((KNodeNode) pnext).getLabelLayer();
        final PNode plabel = labellayer.getChild(0);
        final PLayer edgeLayer = node.getChildAreaNode().getEdgeLayer();
        final KEdgeNode edgeNode = (KEdgeNode) edgeLayer.getChild(0);

        final PAffineTransform porttransform = pport.getTransform();
        final PAffineTransform labeltransform = plabel.getTransform();
        final PAffineTransform nodetransform = pnext.getTransform();

        Assert.assertEquals(12, nodetransform.getTranslateX(), 0);
        Assert.assertEquals(32, nodetransform.getTranslateY(), 0);
        Assert.assertEquals(40, pnext.getBoundsReference().height, 0);
        Assert.assertEquals(40, pnext.getBoundsReference().width, 0);

        Assert.assertEquals(2, labeltransform.getTranslateX(), 0);
        Assert.assertEquals(3, labeltransform.getTranslateY(), 0);
        Assert.assertEquals(5, plabel.getBoundsReference().height, 0);
        Assert.assertEquals(4, plabel.getBoundsReference().width, 0);

        Assert.assertEquals(6, porttransform.getTranslateX(), 0);
        Assert.assertEquals(7, porttransform.getTranslateY(), 0);
        Assert.assertEquals(5, pport.getBoundsReference().height, 0);
        Assert.assertEquals(5, pport.getBoundsReference().width, 0);

        Assert.assertEquals(  3, edgeNode.getBendPoints().length);
        Assert.assertEquals( 1d, edgeNode.getBendPoints()[0].getX(), 0);
        Assert.assertEquals( 1d, edgeNode.getBendPoints()[0].getY(), 0);
        Assert.assertEquals( 1d, edgeNode.getBendPoints()[1].getX(), 0);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[1].getY(), 0);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[2].getX(), 0);
        Assert.assertEquals( 1d, edgeNode.getBendPoints()[2].getY(), 0);

        final KPoint point = KGraphFactory.eINSTANCE.createKPoint();
        point.setPos(10, 10);
        edge.getBendPoints().add(point);

        Assert.assertEquals(  4, edgeNode.getBendPoints().length);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[2].getX(), 0);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[2].getY(), 0);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[3].getX(), 0);
        Assert.assertEquals( 1d, edgeNode.getBendPoints()[3].getY(), 0);

        point.setPos(10, 20);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[2].getX(), 0);
        Assert.assertEquals(20d, edgeNode.getBendPoints()[2].getY(), 0);

        edge.getBendPoints().remove(0);
        edge.getTargetPoint().setY(7);

        Assert.assertEquals(  3, edgeNode.getBendPoints().length);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[1].getX(), 0);
        Assert.assertEquals(20d, edgeNode.getBendPoints()[1].getY(), 0);
        Assert.assertEquals(10d, edgeNode.getBendPoints()[2].getX(), 0);
        Assert.assertEquals( 7d, edgeNode.getBendPoints()[2].getY(), 0);
    }

    /**
     * Test for checking if all kgraph elements are also existing in the pgraph.
     */
    @Test
    public void nodeTest() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());
        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        KlighdTestUtil.makeTestGraph(root);
        // create a controller for the graph
        final DiagramController controller = createDiagramController(root, camera);
        final KNodeAbstractNode topNode = controller.getNode();
        Assert.assertTrue(checkStructure(root, topNode));
    }

    /**
     * Test adding of elements was correct after expansion.
     */
    @Test
    public void nodeTestExpanded() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());

        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final DiagramController controller = createDiagramController(root, camera);
        controller.collapse(root);
        KlighdTestUtil.makeTestGraph(root);
        controller.expand(root);
        // create a controller for the graph
        final KNodeAbstractNode topNode = controller.getNode();
        Assert.assertTrue(checkStructure(root, topNode));
    }

    /**
     * Test adding of elements while node is collapsed.
     */
    @Test
    public void nodeTestCollapsed() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());
        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final DiagramController controller = createDiagramController(root, camera);
        controller.collapse(root);
        KlighdTestUtil.makeTestGraph(root);

        // create a controller for the graph
        final KNodeAbstractNode topNode = controller.getNode();
        Assert.assertFalse(checkStructure(root, topNode));
    }

    /**
     * Test if all adapters are added correctly.
     */
    @Test
    public void adapterTest() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());

        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final DiagramController controller = createDiagramController(root, camera);
        final KNode child = KlighdTestUtil.makeTestGraph(root);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(checkAdapters(child));
    }

    /**
     * Test if all adapters are added correctly.
     */
    @Test
    public void adapterTestExpanded() {
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());

        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final DiagramController controller = createDiagramController(root, camera);
        final KNode child = KlighdTestUtil.makeTestGraph(root);
        controller.collapse(child);
        final KNode cc = KlighdTestUtil.addchild(child);
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
        final List<Adapter> nodeadapters = kgraph.eAdapters();
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
        for (final KNode child : kgraph.getChildren()) {
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
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());

        final KNode root = KGraphUtil.createInitializedNode();
        final KLabel l = KGraphUtil.createInitializedLabel(root);
        l.setText("rootnode");
        final DiagramController controller = createDiagramController(root, camera);
        final KNode child = KlighdTestUtil.makeTestGraph(root);
        controller.collapse(child);
        final KNode cc = KlighdTestUtil.addchild(child);
        KlighdTestUtil.addport(cc);
        KlighdTestUtil.addport(cc);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(this.checkAdaptersCollapsed(cc));
    }

    private DiagramController createDiagramController(final KNode root, final KlighdMainCamera camera) {
        return new DiagramController(root, camera, true, null);
    }

    /**
     * Check recursively if given KNode has all its adapters added correctly.
     *
     * @param kgraph
     *            the model whoose adapters to check
     * @return false if not all adapters set.
     */
    private boolean checkAdapters(final KNode kgraph) {
        final List<Adapter> nodeadapters = kgraph.eAdapters();
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
        for (final KPort p : kgraph.getPorts()) {
            final List<Adapter> portadapters = p.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables
                            .any(portadapters, this.getCondition("KGEShapeLayoutPNodeUpdater"))
                    || !Iterables.any(portadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }
        }
        // check adapters on labels
        for (final KLabel l : kgraph.getLabels()) {
            final List<Adapter> labeladapters = l.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables.any(labeladapters,
                            this.getCondition("KGEShapeLayoutPNodeUpdater"))
                    || !Iterables.any(labeladapters, this.getCondition("TextSyncAdapter"))) {
                return false;
            }
        }

        // check adapters on edges
        for (final KEdge e : kgraph.getOutgoingEdges()) {
            final List<Adapter> edgeadapters = e.eAdapters();
            if (Iterables.any(nodeadapters,
                    this.getCondition("AbstractKGERenderingController$ElementAdapter"))
                    || !Iterables
                            .any(edgeadapters, this.getCondition("KEdgeLayoutEdgeNodeUpdater"))
                    || !Iterables.any(edgeadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }

        }

        // check recursively for children
        for (final KNode child : kgraph.getChildren()) {
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
    private boolean checkStructure(final KNode kgraph, final KNodeAbstractNode piccoloTree) {

        // check if knode is included in piccolo structure
        if (!piccoloTree.getViewModelElement().toString().equals(kgraph.toString())) {
            return false;
        }
        final PLayer nodeLayer = piccoloTree.getChildAreaNode().getNodeLayer() != null
                ? piccoloTree.getChildAreaNode().getNodeLayer() : new PLayer();
                    // chsch: added some hotfix here

        PLayer edgeLayer = null;
        if (piccoloTree.getParentKNodeNode() != null) {
            edgeLayer = piccoloTree.getParentKNodeNode().getChildAreaNode().getEdgeLayer() != null
                ? piccoloTree.getParentKNodeNode().getChildAreaNode().getEdgeLayer() : new PLayer();
                    // chsch: added some hotfix here
        }
        for (int i = 0; i < kgraph.getChildren().size(); i++) {
            if (piccoloTree instanceof KNodeNode) {

                // check if all ports are included in the piccolo structure
                final PLayer portLayer = ((KNodeNode) piccoloTree).getPortLayer() != null
                        ? ((KNodeNode) piccoloTree).getPortLayer() : new PLayer();
                            // chsch: added some hotfix here

                for (int j = 0; j < kgraph.getPorts().size(); j++) {
                    if (!(kgraph.getPorts().get(j) == ((KlighdNode) portLayer.getChild(j))
                            .getViewModelElement())) {
                        return false;
                    }
                }

                // check if all labels are included in the piccolo structure
                final PLayer labelLayer = ((KNodeNode) piccoloTree).getLabelLayer() != null
                        ?  ((KNodeNode) piccoloTree).getLabelLayer() : new PLayer();
                            // chsch: added some hotfix here

                for (int j = 0; j < kgraph.getLabels().size(); j++) {
                    if (!(kgraph.getLabels().get(j) == ((KlighdNode) labelLayer.getChild(j))
                            .getViewModelElement())) {
                        return false;
                    }
                }

            }

            // check if all edges are included
            if (edgeLayer != null) {
                for (final KEdge edge : kgraph.getOutgoingEdges()) {
                    boolean edgematch = false;
                    @SuppressWarnings("unchecked")
                    final
                    ListIterator<KEdgeNode> iter = edgeLayer.getChildrenIterator();
                    while (iter.hasNext()) {
                        if (iter.next().getViewModelElement() == edge) {
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
            if (!checkStructure(kgraph.getChildren().get(i), (KNodeAbstractNode) nodeLayer.getChild(i))) {
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
            public boolean apply(final Adapter arg0) {
                return arg0.getClass().getSimpleName().equals(name);
            }

        };
    }

}
