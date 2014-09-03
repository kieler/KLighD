package de.cau.cs.kieler.klighd.piccolo.test;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * 
 * Some utility methods for creating or loading test models.
 * 
 * @author ckru
 * 
 */
public class KlighdTestUtil {
    /**
     * Generate a kgraph that has every feature to test against.
     * 
     * @param root
     *            root node of the graph to fill.
     * @return first child of testgraph
     */
    public static KNode makeTestGraph(final KNode root) {
        KNode next = KlighdTestUtil.addchild(root);
        KlighdTestUtil.addport(next);
        KlighdTestUtil.addport(next);
        KlighdTestUtil.addport(next);
        KNode a = KlighdTestUtil.addchild(next);
        KNode b = KlighdTestUtil.addchild(next);
        KlighdTestUtil.connectNodes(a, b, null, null);
        return next;
    }
    
    /**
     * Adds a child KNode to the given node.
     * 
     * @param node
     *            the node to add a child to
     * @return the new child node
     */
    public static KNode addchild(final KNode node) {
        KNode child = KimlUtil.createInitializedNode();
        KLabel l = KimlUtil.createInitializedLabel(child);
        l.setText(node.toString() + "child" + node.getChildren().size());
        // child.getLabels().add(l);
        node.getChildren().add(child);
        return child;
    }
    
    /**
     * Connect two KNode with an edge. Optionally connect them via ports.
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
    public static KEdge connectNodes(final KNode source, final KNode target, final KPort sourcePort,
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
     * Add a port to the given KNode.
     * 
     * @param node
     *            the KNode to add a port to.
     * @return the added port
     */
    public static KPort addport(final KNode node) {
        KPort p = KimlUtil.createInitializedPort();
        KLabel l = KimlUtil.createInitializedLabel(p);
        l.setText("port" + node.getPorts().size());
        node.getPorts().add(p);
        return p;
    }
    
}
