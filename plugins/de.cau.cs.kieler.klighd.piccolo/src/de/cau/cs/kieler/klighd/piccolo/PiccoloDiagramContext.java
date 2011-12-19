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
package de.cau.cs.kieler.klighd.piccolo;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.klighd.piccolo.graph.IGraphParent;
import edu.umd.cs.piccolo.PNode;

/**
 * Instances of this class can be displayed in a Piccolo viewer by providing the required Piccolo
 * root nodes for the diagram layers and a semantic root node.
 * 
 * @author mri
 */
public class PiccoloDiagramContext {

    /** the Piccolo root nodes for the diagram layers. */
    private List<PNode> layerRoots = new LinkedList<PNode>();
    /** the Piccolo node that serves as the semantic root node for the diagram. */
    private IGraphParent rootNode = null;

    /**
     * Adds a Piccolo node as root for a new diagram layer.
     * 
     * @param root
     *            the root node
     */
    public void addLayerRoot(final PNode root) {
        layerRoots.add(root);
    }

    /**
     * Returns the Piccolo root nodes for the layers in the diagram.
     * 
     * @return the root nodes
     */
    public List<PNode> getLayerRoots() {
        return layerRoots;
    }

    /**
     * Sets the semantic root node for the diagram.
     * 
     * @param root
     *            the root node
     */
    public void setRootNode(final IGraphParent root) {
        this.rootNode = root;
    }

    /**
     * Returns the semantic root node for the diagram.
     * 
     * @return the root node
     */
    public IGraphParent getRootNode() {
        return rootNode;
    }

}
