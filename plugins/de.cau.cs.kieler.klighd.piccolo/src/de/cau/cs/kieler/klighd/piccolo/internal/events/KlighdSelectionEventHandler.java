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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IKGraphElementNode.INode;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;


/**
 * A simple selection event handler supporting click-based selections (no rubber band selection).<br>
 * It supports the selection of multiple elements if the CTRL/COMMAND key is pressed.<br>
 * In order to not interfere with the diagram panning the selection is done on mouse up events
 * (this is in contrast to the usual selection) if and only if the mouse pointer has not been moved
 * between mouse down and mouse up. 
 * 
 * @author chsch
 */
public class KlighdSelectionEventHandler extends KlighdBasicInputEventHandler {

    /**
     * Constructor.
     * 
     * @param theDiagramViewer
     *            the {@link IViewer} to set the selection on
     */
    public KlighdSelectionEventHandler(final PiccoloViewer theDiagramViewer) {
        this.viewer = theDiagramViewer.getContextViewer();
        this.diagramViewer = theDiagramViewer;
        this.multiSelection =
                viewer.getViewContext().getProperty(KlighdSynthesisProperties.MULTI_SELECTION);
    }
    
    private final IViewer viewer;
    private final PiccoloViewer diagramViewer;
    private final boolean multiSelection; 
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

        if (diagramViewer.isMagnificationLensVisible()) {
            return;
        }

        // if other events occurred in the mean time like other button hits,
        //  movements (e.g. panning) abort the selection
        if (!event.isLeftMouseButton()
                || !event.getCanvasPosition().equals(thePreviousPoint)
                || !(event.getPickedNode() == thePressedNode)
                || ((KlighdMouseEvent) event.getSourceSwingEvent()).getEvent().count != 1) {
            return;
        }

        // now we consider the event to be a proper selection event
        //  it shall not be evaluated by other event handlers anymore
        event.setHandled(true);

        // in order to figure out which of the picked elements are selectable
        //  copy the node stack and reverse the copy
        final List<?> pickStack = Lists.newArrayList((List<?>) event.getPath().getNodeStackReference());
        Collections.reverse(pickStack);
        final Iterator<?> it = pickStack.iterator();
        
        // run variables
        PNode selectedNode = thePressedNode;
        EObject graphElement = null;

        while (it.hasNext()) {
            selectedNode = (PNode) it.next();

            // get the corresponding view model element, i.e. KText or KGraphElement 
            if (selectedNode instanceof IKlighdNode && ((IKlighdNode) selectedNode).isSelectable()) {
                graphElement = ((IKlighdNode) selectedNode).getViewModelElement();
                break;
            } else if (selectedNode instanceof INode) {
                // in case we found a KNode that is marked to be non-selectable
                //  (and that is expected to not cover any further KGraphElements)
                //  stop here in order to keep the previous selection,
                // otherwise the diagramClip node would be selected,
                //  which seems to be not intuitive
                graphElement = ((INode) selectedNode).isSelectable()
                        ? ((INode) selectedNode).getViewModelElement() : null;
                break;
            }            
        }

        if (graphElement == null) {
            return;
        }

        if (multiSelection && event.isControlDown()) {
            this.viewer.toggleSelectionOfDiagramElements(Collections.singleton(graphElement));
        } else {
            this.viewer.resetSelectionToDiagramElements(Collections.singleton(graphElement));
        }
    }
}
