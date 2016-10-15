/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.util;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.PortSide;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * Utility methods to operate on KGraphs.
 */
public final class KGraphUtil {

    /**
     * @return
     */
    public static KNode createInitializedNode() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param targetNode
     * @param sourceNode
     * @return
     */
    public static boolean isDescendant(KNode targetNode, KNode sourceNode) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param point
     * @param parent
     */
    public static void toAbsolute(KVector point, de.cau.cs.kieler.klighd.kgraph.KNode parent) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param point
     * @param parent
     */
    public static void toRelative(KVector point, de.cau.cs.kieler.klighd.kgraph.KNode parent) {
        // TODO Auto-generated method stub
        
    }

    public static KEdge createInitializedEdge() {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

    public static KLabel createInitializedLabel(KLabeledGraphElement element) {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

    public static KPort createInitializedPort() {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

    /**
     * @param p
     * @param dir
     * @return
     */
    public static PortSide calcPortSide(KPort p, Direction dir) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param copy
     */
    public static void persistDataElements(KGraphElement copy) {
        // TODO Auto-generated method stub
        
    }

    public static void configureWithDefaultValues(KNode node) {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

    public static void configureWithDefaultValues(KPort port) {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

    public static void configureWithDefaultValues(KEdge edge) {
      throw new UnsupportedOperationException("TODO: auto-generated method stub");
    }

}
