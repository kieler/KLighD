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
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Point2D;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;


/**
 * A simple selection event handler supporting click-based selections (no rubber band selection).
 * Allows selection of multiple elements if the CTRL/COMMAND key is pressed.
 * 
 * @author chsch
 */
public class KlighdSelectionEventHandler extends KlighdBasicInputEventHandler {

    /**
     * Constructor.
     * 
     * @param theContextViewer
     *            the {@link IViewer} to set the selection on
     */
    public KlighdSelectionEventHandler(final IViewer<?> theContextViewer) {
        this.viewer = theContextViewer;
    }
    
    private final IViewer<?> viewer;
    private PNode pressedNode = null;
    private Point2D point = null;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final PInputEvent event) {
        if (event.isLeftMouseButton()) {
            this.pressedNode = event.getPickedNode();
            this.point = event.getCanvasPosition();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final PInputEvent event) {
        final PNode thePressedNode = this.pressedNode;
        final Point2D thePreviousPoint = this.point;
        this.pressedNode = null;
        this.point = null;

        // if other events occurred in the mean time like other button hits, movements (e.g. panning)
        //  abort the selection
        if (!event.isLeftMouseButton()
                || !event.getCanvasPosition().equals(thePreviousPoint)
                || !(event.getPickedNode() == thePressedNode)) {
            return;
        }
        
        final ITracingElement<?> graphNode;
        if (thePressedNode instanceof ITracingElement<?>) {
            graphNode = (ITracingElement<?>) thePressedNode;
        } else {
            graphNode = Iterables.getLast(Iterables.filter(
                    event.getPath().getNodeStackReference(), ITracingElement.class), null);
            if (graphNode == null) {
                return;
            }
        }
           
        final EObject graphElement = graphNode.getGraphElement();
        if (event.isControlDown()) {
            this.viewer.toggleSelectionOfDiagramElements(Collections.singleton(graphElement));
        } else {
            this.viewer.resetSelectionToDiagramElements(Collections.singleton(graphElement));
        }
    }
}
