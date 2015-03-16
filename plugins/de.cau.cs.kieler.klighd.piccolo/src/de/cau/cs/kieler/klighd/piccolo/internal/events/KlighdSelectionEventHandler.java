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

import static de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.LEFT_BUTTON;
import static de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.RIGHT_BUTTON;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PStack;

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
    private Point2D point = null;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final PInputEvent event) {
        if (event.getButton() == LEFT_BUTTON) {
            this.point = event.getCanvasPosition();
            return;
        }

        this.point = null;

        if (event.getButton() != RIGHT_BUTTON || diagramViewer.isMagnificationLensVisible()) {
            return;
        }

        // hence, the right mouse button must have been pressed right now!

        // we now consider the event to be a proper selection event
        //  it shall not be evaluated by other event handlers anymore
        event.setHandled(true);

        // fortunately, we can perform a selection update in this case and it WILL be
        //  evaluated before the menu items are created, because of:

        // On Windows the context menu shows up on releasing the mouse, on MacOSX & LinuxGTK
        //  the situation is more difficult, as context menu shows up there after pressing
        //  the right mouse button.

        // The following outline is valid for MacOSX, the LinuxGTK trace is similar!

        // The display is notified on a right click and performs a 'Widget.rightMouseDown(...)'
        //  in our case 'widget' is the canvas being a control leading to the call of
        //  'Control.mouseEvent(..., SWT.MouseDown)'
        //   -> 'Control.sendMouse(..., SWT.MouseDown, false)'
        //   -> ... -> 'Display.postEvent(SWT.MouseDown, event)' causing the scheduling of 'event'
        // control flow usually proceeds in 'Control.rightMouseDown(...)' and calls the super impl
        //  'Widget.rightMouseDown' that delegates into the native parts of the implementation.

        // The native part, in turn, causes via 'Dispay.windowProc(...)' the call of
        //  'Widget/Control.menuForEvent(...)' -> 'Menu.setVisible(true)' on top of the call stack.
        // the latter, however, does NOT request the menu to show up, but schedules the request, too!

        // Afterwards the full call stack is reduced back to 'Display.readAndDispatch()' and proceeds
        //  with 'events |= runPaint();' and 'events |= runDeferredEvents();'.
        // The latter causes the above scheduled 'SWT.MouseDown' event to be sent to listeners
        //  registered at the corresponding widget, i.e. the canvas,
        //  and eventually causes the call of this method and 'performSelection(...)'

        // The 'events' returned by 'Display.readAndDispatch()' will be 'true' causing an immediate
        //  recall of 'Display.readAndDispatch()'. Within 'events |= runPopups ();' our scheduled
        //  context menu will finally be called to show up, based on an up-to-date selection!

        performSelection(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final PInputEvent event) {
        final Point2D thePreviousPoint = this.point;
        this.point = null;

        if (diagramViewer.isMagnificationLensVisible()) {
            return;
        }

        // if other events occurred in the mean time like other button hits,
        //  movements (e.g. panning) abort the selection
        if (event.getButton() != LEFT_BUTTON
                || thePreviousPoint == null
                || !event.getCanvasPosition().equals(thePreviousPoint)
                || ((KlighdMouseEvent) event.getSourceSwingEvent()).getEvent().count != 1) {
            return;
        }

        // we now consider the event to be a proper selection event
        //  it shall not be evaluated by other event handlers anymore
        event.setHandled(true);

        performSelection(event);
    }

    private void performSelection(final PInputEvent event) {

        // in order to figure out which of the picked elements are selectable
        //  create a list iterator initialized to the top of the node stack
        // I think just "popping" elements from the stack would work, too,
        //  this way, however, there's no side effect on the node stack
        final PStack nodeStack = event.getPath().getNodeStackReference();
        final ListIterator<?> it = nodeStack.listIterator(nodeStack.size());

        // run variables
        PNode selectedNode = null;
        EObject graphElement = null;

        while (it.hasPrevious()) {
            selectedNode = (PNode) it.previous();

            // get the corresponding view model element, i.e. KText or KGraphElement 
            if (selectedNode instanceof IKlighdNode && ((IKlighdNode) selectedNode).isSelectable()) {
                graphElement = ((IKlighdNode) selectedNode).getViewModelElement();
                break;
            } else if (selectedNode instanceof IKNodeNode) {
                // in case we found a KNode that is marked to be non-selectable
                //  (and that is expected to not cover any further KGraphElements)
                //  stop here in order to keep the previous selection,
                // otherwise the diagramClip node would be selected,
                //  which seems to be not intuitive
                graphElement = ((IKNodeNode) selectedNode).isSelectable()
                        ? ((IKlighdNode) selectedNode).getViewModelElement() : null;
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
