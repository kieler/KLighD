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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A simple handle for the selection of a Piccolo node. Does not provide any user interaction.
 * 
 * @author mri
 */
public class PSWTSimpleSelectionHandle extends PNode {

    private static final long serialVersionUID = -384380852177313656L;

    /** the width and height of the handle squares. */
    private static final float SQUARE_WH = 5;
    /** the line width of the handle lines. */
    private static final float LINE_W = 1;
    /** the distance between the center of the handle and the referenced node. */
    private static final double MARGIN = 5;

    /**
     * Adds a simple selection handle to the given node.
     * 
     * @param node
     *            the node
     */
    public static void addSelectionHandle(final PNode node) {
        PSWTSimpleSelectionHandle handle = new PSWTSimpleSelectionHandle();
        node.addChild(handle);
        handle.createHandle();
        handle.setPickable(false);
    }

    /**
     * Removes all simple selection handles from the given node.
     * 
     * @param node
     *            the node
     */
    @SuppressWarnings("unchecked")
    public static final void removeSelectionHandle(final PNode node) {
        List<PNode> handles = new LinkedList<PNode>();
        for (PNode child : (List<PNode>) node.getChildrenReference()) {
            if (child instanceof PSWTSimpleSelectionHandle) {
                handles.add(child);
            }
        }
        node.removeChildren(handles);
    }

    private void createHandle() {
        if (getParent() != null) {
            PBounds bounds = getParent().getBoundsReference();
            double leftX = -MARGIN;
            double rightX = bounds.getWidth() + MARGIN + 1;
            double topY = -MARGIN;
            double bottomY = bounds.getHeight() + MARGIN;
            // create the corner squares
            addChild(createSquareCentered(leftX, topY));
            addChild(createSquareCentered(rightX, topY));
            addChild(createSquareCentered(leftX, bottomY));
            addChild(createSquareCentered(rightX, bottomY));
            // create the connecting lines
            addChild(createLine(leftX, topY + MARGIN, leftX, bottomY - MARGIN));
            addChild(createLine(rightX, topY + MARGIN, rightX, bottomY - MARGIN));
            addChild(createLine(leftX + MARGIN, topY, rightX - MARGIN, topY));
            addChild(createLine(leftX + MARGIN, bottomY, rightX - MARGIN, bottomY));
        }
    }

    private PNode createLine(final double x0, final double y0, final double x1, final double y1) {
        float[] xps = { (float) x0, (float) x1 };
        float[] yps = { (float) y0, (float) y1 };
        PSWTAdvancedPath line = PSWTAdvancedPath.createPolyline(xps, yps);
        line.setPickable(false);
        line.setLineWidth(LINE_W);
        line.setLineStyle(PSWTAdvancedPath.LineStyle.DOT);
        line.setStrokeColor(Color.black);
        return line;
    }

    private PNode createSquareCentered(final double x, final double y) {
        PSWTAdvancedPath square =
                PSWTAdvancedPath.createRectangle((float) x - SQUARE_WH / 2.0f, (float) y
                        - SQUARE_WH / 2.0f, SQUARE_WH, SQUARE_WH);
        square.setPickable(false);
        square.setPaint(Color.black);
        square.setStrokeColor(Color.black);
        return square;
    }

}
