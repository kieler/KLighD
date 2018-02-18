/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.actions;

import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * This action puts elements into the focus when clicked. Focussed elements usually have their
 * labels fully displayed, while unfocussed elements are usually subject to the current label
 * management strategy. Focussed / unfocussed elements can be distinguished by checking their
 * {@link KlighdLabelProperties#ELEMENT_IN_FOCUS}.
 * 
 * <p>This class does not set the focussed state on the clicked element only, but also determines
 * which other elements should change their focus state along with it. A simple example would be
 * the ports of a node: if the node is focussed, labels of ports should usually be focussed as
 * well. Subclasses can override the {@link #determineFocussedElements(KGraphElement)} method to
 * determine what should change focus with a given element.</p>
 * 
 * @author ybl
 * @author cds
 */
public class FocusAndContextAction implements IAction {

    /** This action's ID as registered with KLighD. */
    public static final String ID = "de.cau.cs.kieler.klighd.actions.FocusAndContextAction";
    
    /**
     * The graph element that was most recently selected. Once a new element is selected, this one will
     * be moved out of focus again.
     */
    private KGraphElement lastSelectedElement = null;
    
    
    @Override
    public final ActionResult execute(final ActionContext context) {
        KGraphElement selectedElement = context.getKGraphElement();
        
        // We need to check if the selection has changed at all
        if (selectedElement == lastSelectedElement) {
            return ActionResult.createResult(false);
        } else {
            // It's important to unfocus the last element first, because the newly focussed element
            // may well be a part of the old focussed one
            focusElement(lastSelectedElement, false);
            focusElement(selectedElement, true);
            lastSelectedElement = selectedElement;

            return ActionResult.createResult(true);
        }
    }
    
    /**
     * Focusses or unfocusses the given element. We basically just call
     * {@link #focusGraphElementLabels(KLabeledGraphElement, boolean)}, except for when it's a
     * node. In that case, we want to put the ports into or out of focus, but leave the node's
     * labels untouched.
     * 
     * @param element
     *            the element to focus or unfocus.
     * @param focus
     *            whether it should be in focus ({@code true}) or not.
     */
    private void focusElement(final KGraphElement element, final boolean focus) {
        if (element != null) {
            // Focus / unfocus the element itself
            focusGraphElement(element, focus);
            
            // Focus / unfocus any of the element's labels, depending on what element we're
            // dealing with
            if (element instanceof KLabeledGraphElement) {
                focusGraphElementLabels((KLabeledGraphElement) element, focus);
            }
            
            List<KGraphElement> furtherFocussedElements = determineFocussedElements(element);
            if (furtherFocussedElements != null) {
                furtherFocussedElements.stream().forEach(e -> focusElement(e, focus));
            }
        }
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Focus Selection
    
    /**
     * Given a graph element about to change its focus state, returns a list of further elements
     * that need to change their focus state as well. This method should not return labels, since
     * all labels of the returned elements will have their state changed automatically.
     * 
     * <p>Care has to be taken when implementing this method to avoid endless loops since it will
     * be called again with each of the elements it returns.</p>
     *
     * @implSpec The default implementation returns {@code null} for everything except nodes. Nodes
     *           pass their focus state on to ports and to comment boxes attached to them.
     * 
     * @param element
     *            the element that changes its focus state.
     * @return list of elements that have to change state as well, or {@code null} if there are
     *         none.
     */
    protected List<KGraphElement> determineFocussedElements(final KGraphElement element) {
        if (element instanceof KNode) {
            KNode node = (KNode) element;
            
            List<KGraphElement> result = Lists.newArrayList();
            
            // Include all ports
            result.addAll(node.getPorts());
            
            // Include any connected comments. By convention, they are connected
            // to a node through edges that run from the comment to the node
            for (KEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getProperty(CoreOptions.COMMENT_BOX)) {
                    result.add(edge.getSource());
                }
            }
            
            return result;
            
        } else {
            return null;
        }
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Sets the focus property on the given graph element.
     * 
     * @param element
     *            the element to change focus on.
     * @param focus
     *            whether the element is now focussed or not.
     */
    private void focusGraphElement(final KGraphElement element, final boolean focus) {
        element.setProperty(KlighdOptions.LABELS_ELEMENT_IN_FOCUS, focus);
    }
    
    /**
     * Sets the focus property on the given graph element's labels.
     * 
     * @param element
     *            the element on whose labels to change focus.
     * @param focus
     *            whether the labels are now focussed or not.
     */
    private void focusGraphElementLabels(final KLabeledGraphElement element, final boolean focus) {
        for (KLabel label : element.getLabels()) {
            focusGraphElement(label, focus);
        }
    }
}
