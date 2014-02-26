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
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PRoot;

// CHECKSTYLEOFF Javadoc|MagicNumber

/**
 * 
 * Testing the klighd piccolo interface.
 * 
 * @author ckru
 * 
 */
public class KlighdTest {

    /**
     * To test: - PNode structure - all adapters set when adding stuff (expanded/collapsed) -
     * responsive to layoutdata changes
     */

    /*
     * public void test() {
     * 
     * KlighdMainCamera camera = new KlighdMainCamera(); PRoot pRoot = new PRoot();
     * pRoot.addChild(camera);
     * 
     * KNode root = KimlUtil.createInitializedNode(); KLabel l =
     * KimlUtil.createInitializedLabel(root); l.setText("rootnode");
     * 
     * // create a controller for the graph DiagramController controller = new
     * DiagramController(root, camera, true); INode topNode = controller.getNode();
     * 
     * controller.collapse(root);
     * 
     * 
     * controller.expand(root);
     * 
     * //for testing animation controller.startRecording();
     * 
     * 
     * 
     * controller.stopRecording(ZoomStyle.NONE, 0);
     * 
     * }
     */

    /**
     * Generate a kgraph that has every feature to test against.
     * 
     * @param root
     *            root node of the graph to fill.
     * @return first child of testgraph
     */
    private KNode makeTestGraph(final KNode root) {
        KNode next = this.addchild(root);
        this.addport(next);
        this.addport(next);
        this.addport(next);
        KNode a = this.addchild(next);
        KNode b = this.addchild(next);
        this.connectNodes(a, b, null, null);
        return next;
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
        makeTestGraph(root);
        // create a controller for the graph
        DiagramController controller = new DiagramController(root, camera, true);
        INode topNode = controller.getNode();
        Assert.assertTrue(checkStructure(root, topNode));
    }

    /**
     * Test adding of elements while node is collapsed.
     */
    @Test
    public void nodeTestCollapsed() {
        KlighdMainCamera camera = new KlighdMainCamera();
        PRoot pRoot = new PRoot();
        pRoot.addChild(camera);

        KNode root = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(root);
        l.setText("rootnode");
        DiagramController controller = new DiagramController(root, camera, true);
        controller.collapse(root);
        makeTestGraph(root);

        // create a controller for the graph
        INode topNode = controller.getNode();
        Assert.assertFalse(checkStructure(root, topNode));
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
        DiagramController controller = new DiagramController(root, camera, true);
        controller.collapse(root);
        makeTestGraph(root);
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
        DiagramController controller = new DiagramController(root, camera, true);
        KNode child = makeTestGraph(root);
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
        DiagramController controller = new DiagramController(root, camera, true);
        KNode child = makeTestGraph(root);
        controller.collapse(child);
        KNode cc = this.addchild(child);
        this.addport(cc);
        this.addport(cc);
        controller.expand(child);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(checkAdapters(child));
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
        DiagramController controller = new DiagramController(root, camera, true);
        KNode child = makeTestGraph(root);
        controller.collapse(child);
        KNode cc = this.addchild(child);
       // this.addport(cc);
       // this.addport(cc);
        // create a controller for the graph
        controller.getNode();
        Assert.assertTrue(checkAdaptersCollapsed(child));
    }
    
    /**
     * Check if adapters are correctly applied in case of collapsed nodes. i.e.: collapsed node looses
     * ChildSyncAdapter, its children have no adapters installed.
     * @param kgraph the kgraph to check
     * @return true if all adapters are applied correctly
     */
    private boolean checkAdaptersCollapsedChildren(final KNode kgraph) {
        List<Adapter> nodeadapters = kgraph.eAdapters();
        if (nodeadapters.size() != 1) {
            return false;
        }
        for (KNode child: kgraph.getChildren()) {
            if (!checkAdaptersCollapsedChildren(child)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check recursively if given KNode has the correct adapters installed for a collapsed node.
     * @param kgraph the model whoose adapters to check
     * @return false if not all adapters set.
     */
    private boolean checkAdaptersCollapsed(final KNode kgraph) {
        List<Adapter> nodeadapters = kgraph.eAdapters();
        if (nodeadapters.size() != 5) {
            return false;
        }
        if (nodeadapters.get(0) == null || 
                !Iterables.any(nodeadapters, this.getCondition("KGEShapeLayoutPNodeUpdater")) ||
                !Iterables.any(nodeadapters, this.getCondition("EdgeSyncAdapter")) ||
                !Iterables.any(nodeadapters, this.getCondition("PortSyncAdapter")) ||
                !Iterables.any(nodeadapters, this.getCondition("LabelSyncAdapter"))) {
            return false;
        }
        
      //check recursively for children
        for (KNode child: kgraph.getChildren()) {
            if (!checkAdaptersCollapsedChildren(child)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Generate a predicate that checks if an adapters classname matches the given string.
     * @param name The string thats supposed to be the class name of the adapter.
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
    
    /**
     * Check recursively if given KNode has all its adapters added correctly.
     * @param kgraph the model whoose adapters to check
     * @return false if not all adapters set.
     */
    private boolean checkAdapters(final KNode kgraph) {
        List<Adapter> nodeadapters = kgraph.eAdapters();
        if (nodeadapters.size() != 6) {
            return false;
        }
        
        //check adapters on node
        if (nodeadapters.get(0) == null || !Iterables.any(nodeadapters, this.getCondition("ChildrenSyncAdapter")) || 
                !Iterables.any(nodeadapters, this.getCondition("KGEShapeLayoutPNodeUpdater")) ||
                !Iterables.any(nodeadapters, this.getCondition("EdgeSyncAdapter")) ||
                !Iterables.any(nodeadapters, this.getCondition("PortSyncAdapter")) ||
                !Iterables.any(nodeadapters, this.getCondition("LabelSyncAdapter"))) {
            return false;
        }
        
        //check adapters on ports
        for (KPort p: kgraph.getPorts()) {
            List<Adapter> portadapters = p.eAdapters();
            if (portadapters.get(0) == null || !Iterables.any(portadapters, this.getCondition("KGEShapeLayoutPNodeUpdater")) || 
                    !Iterables.any(portadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }
        }
        //check adapters on labels
        for (KLabel l: kgraph.getLabels()) {
            List<Adapter> labeladapters = l.eAdapters();
            if (labeladapters.get(0) == null || !Iterables.any(labeladapters, this.getCondition("KGEShapeLayoutPNodeUpdater")) || 
                    !Iterables.any(labeladapters, this.getCondition("TextSyncAdapter"))) {
                return false;
            }
        }
        
        //check adapters on edges
        for (KEdge e: kgraph.getOutgoingEdges()) {
            List<Adapter> edgeadapters = e.eAdapters();
            if (edgeadapters.get(0) == null || !Iterables.any(edgeadapters, this.getCondition("KEdgeLayoutEdgeNodeUpdater")) || 
                    !Iterables.any(edgeadapters, this.getCondition("LabelSyncAdapter"))) {
                return false;
            }
       
            
        }
        
        //check recursively for children
        for (KNode child: kgraph.getChildren()) {
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
     * Add a port to the given KNode.
     * 
     * @param node
     *            the KNode to add a port to.
     * @return the added port
     */
    private KPort addport(final KNode node) {
        KPort p = KimlUtil.createInitializedPort();
        KLabel l = KimlUtil.createInitializedLabel(p);
        l.setText("port" + node.getPorts().size());
        node.getPorts().add(p);
        return p;
    }

    /**
     * Connect two KNode with an edge. Optinally connect them via ports.
     * 
     * @param source
     *            source node
     * @param target
     *            target node
     * @param sourcePort
     *            sourceport, may be null
     * @param targetPort
     *            targetport, may be null
     * @return the edge that connects the given nodes
     */
    private KEdge connectNodes(final KNode source, final KNode target, final KPort sourcePort,
            final KPort targetPort) {
        KEdge e = KimlUtil.createInitializedEdge();
        e.setSource(source);
        e.setTarget(target);
        e.setSourcePort(sourcePort);
        e.setTargetPort(targetPort);
        KLabel l = KimlUtil.createInitializedLabel(e);
        l.setText(source.toString() + " to " + target.toString());
        return e;
    }

    /**
     * Adds a child KNode to the given node.
     * 
     * @param node
     *            the node to add a child to
     * @return the new child node
     */
    private KNode addchild(final KNode node) {
        KNode child = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(child);
        l.setText(node.toString() + "child" + node.getLabels().size());
        // child.getLabels().add(l);
        node.getChildren().add(child);
        return child;
    }

}
