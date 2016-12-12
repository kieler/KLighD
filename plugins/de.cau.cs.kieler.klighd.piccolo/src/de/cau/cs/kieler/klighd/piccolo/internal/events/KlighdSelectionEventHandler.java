/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
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
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.DefaultSelectionIterator;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KEdgeRenderingController.JunctionPointCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PPickPath;
import edu.umd.cs.piccolo.util.PStack;

/**
 * A simple selection event handler supporting click-based selections (no rubber band selection).<br>
 * It supports the selection of multiple elements if the CTRL/COMMAND key is pressed.<br>
 * In order to not interfere with the diagram panning the selection is done on mouse up events (this
 * is in contrast to the usual selection) if and only if the mouse pointer has not been moved
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
        this.includePortsWithinConnectedEdges =
                viewer.getViewContext().getProperty(
                        KlighdSynthesisProperties.INCLUDE_PORTS_IN_CONNECTED_EDGES_SELECTIONS);
    }

    private final IViewer viewer;
    private final PiccoloViewer diagramViewer;
    private final boolean multiSelection;
    private final boolean includePortsWithinConnectedEdges;

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

    /**
     * Implements the actual selection routine.
     *
     * @param event
     *            the input event causing a selection update
     */
    private void performSelection(final PInputEvent event) {
        final PNode pickedNode = event.getPickedNode();
        final PPickPath pickPath;

        // first of all check whether a junction point (-camera) has been picked
        if (pickedNode instanceof JunctionPointCamera) {
            // if so we compute a new pick path with the pick area being the area
            //  of the junction figure (which is the area of the junction cam);
            // the rationale is to pick like with a pick punch the whole area of
            //  the junction point figure in order to identify diverging rounded
            //  bend point polylines!

            // Of course, this approach is somehow costly!

            // Thus, start with computing the junction cam's canvas-based bounds
            //  by applying all transforms of the pickPath's transform stack,
            //  i.e. an accumulated transform, to the cam's local bounds.
            final Rectangle2D junctionCamBounds =
                    event.getPath().getPathTransformTo(pickedNode).transform(
                            pickedNode.getBounds(), null);

            // Determine the pick halo to be half of the maximum of the junction cam's
            //  width and height; the halo value will be applied twice for x- and y-direction,
            //  see implementation of 'PCamera.pick(double, double, double)',
            //  esp. the called 'PBounds' constructor 'PBounds(Point2D, double, double)'
            final double halo = Math.max(
                    junctionCamBounds.getWidth(), junctionCamBounds.getHeight()) / 2;

            // Now compute the punch pick path ...
            pickPath = event.getTopCamera().pick(
                    junctionCamBounds.getCenterX(), junctionCamBounds.getCenterY(), halo);

            // and drop the top and may be further picked junction paint cameras
            PNode picked = pickPath.getPickedNode();
            while (picked instanceof JunctionPointCamera) {
                picked = pickPath.nextPickedNode();
            }

        } else {
            // otherwise just evaluate the pick path provided by 'event'
            pickPath = event.getPath();
        }

        Set<EObject> selectedElements = null;
        do {
            // in order to figure out which of the picked elements are selectable
            //  create a list iterator initialized to the top of the node stack
            // I think just "popping" elements from the stack would work, too,
            //  this way, however, there's no side effect on the node stack
            final PStack nodeStack = pickPath.getNodeStackReference();
            final ListIterator<?> it = nodeStack.listIterator(nodeStack.size());

            // run variables
            PNode selectedNode = null;
            EObject viewModelElement = null;

            while (it.hasPrevious()) {
                selectedNode = (PNode) it.previous();

                // get the corresponding view model element, i.e. KText or KGraphElement
                if (selectedNode instanceof IKlighdNode && ((IKlighdNode) selectedNode).isSelectable()) {
                    viewModelElement = ((IKlighdNode) selectedNode).getViewModelElement();
                    break;
                } else if (selectedNode instanceof IKNodeNode) {
                    // in case we found a KNode that is marked to be non-selectable
                    //  (and that is expected to not cover any further KGraphElements)
                    //  stop here in order to keep the previous selection,
                    // otherwise the diagramClip node would be selected,
                    //  which seems to be not intuitive
                    viewModelElement = ((IKNodeNode) selectedNode).isSelectable()
                            ? ((IKlighdNode) selectedNode).getViewModelElement() : null;
                    break;
                }
            }

            if (viewModelElement == null) {
                return;
            }

            if (viewModelElement instanceof KEdge) {
                // in case a KEdge has been identified that edge might occlude another one that
                //  shall be selected as well (mostly happens if edges are routed in orthogonal fashion);
                selectedElements = performEdgeSelection(selectedElements, (KEdge) viewModelElement);

                // ... start a new "pick" run ('nextPickedNode' takes care
                // about ignoring the previously found ones), ...
                pickPath.nextPickedNode();

                // ... and evaluate the updated pick path by starting the loop again
                continue;

            } else if (selectedElements != null) {
                // the fact that 'selectedElements' is non-null implies that the above case
                //  must have been executed beforehand meaning we're selecting one or multiple KEdges
                // We modified the pickPath in the process, resulting in subsequent handlers not 
                // receiving a proper node stack. So first reset the pickPath.
                pickPath.reset();

                // the execution of the above statements now gave a selectable view element
                //  that is not a KEdge so we have to stop and ignore 'viewModelElement' here
                break;

            } else if (viewModelElement instanceof KNode) {
                // We selected a KNode. By default this should be the only selected Node,
                // but certain circumstances might require to specialize the selection behaviour.
                selectedElements = performNodeSelection((KNode) viewModelElement);

                // Stop the loop after handling the node
                break;

            } else {
                // we identified a selectable view model element not being a KEdge or KNode, and
                //  the current loop iteration must be the first one since 'selectedElemets' is 'null'
                // we just initialize 'selectedElements', put 'viewModelElement' in there, ...
                selectedElements = Collections.singleton(viewModelElement);

                //  ... and stop the loop!
                break;
            }
        } while (true);


        if (multiSelection && event.isControlDown()) {
            this.viewer.toggleSelectionOfDiagramElements(selectedElements);
        } else {
            this.viewer.resetSelectionToDiagramElements(selectedElements);
        }
    }

    /**
     * Handles the selection of a single edge.
     * 
     * @param selectedElements
     *            The set of already selected elements
     * @param edge
     *            The selected edge
     * @return The updated set of selected elements
     */
    protected Set<EObject> performEdgeSelection(final Set<EObject> selectedElements,
            final KEdge edge) {
        final Set<EObject> returnedElements =
                selectedElements == null ? Sets.newHashSet() : selectedElements;

        // add the currently found edge and its connected ones
        // to the set of elements to be selected,
        // adding ports if selected by KlighdProperty...
        Iterators.addAll(returnedElements, getConnectedElements(edge));
        return returnedElements;
    }

    /**
     * Provides an iterator for all {@link KGraphElement KGraphElements} connected to the selected
     * edge. Can be overridden to use own customized
     * {@link de.cau.cs.kieler.kiml.util.selection.SelectionIterator SelectionIterators}.
     * 
     * @param edge
     *            the selected edge
     * @return the iterator
     */
    protected Iterator<KGraphElement> getConnectedElements(final KEdge edge) {
        return KGraphUtil.getConnectedElements(edge, new DefaultSelectionIterator(edge, 
                includePortsWithinConnectedEdges, false), new DefaultSelectionIterator(edge, 
                includePortsWithinConnectedEdges, true));
    }

    /**
     * Handles the selection of a single node.
     * 
     * @param node
     *            The selected node
     * @return The updated set of selected elements
     */
    protected Set<EObject> performNodeSelection(final KNode node) {
        return Collections.singleton(node);
    }

}
