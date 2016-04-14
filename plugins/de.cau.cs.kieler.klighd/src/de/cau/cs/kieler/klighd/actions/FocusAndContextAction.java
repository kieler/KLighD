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

import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KLabeledGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.KPort;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.labels.KlighdLabelProperties;

/**
 * This action puts elements into the focus when clicked. Focussed elements usually have their labels
 * fully displayed, while unfocussed elements are usually subject to the current label management
 * strategy. Focussed / unfocussed elements can be distinguished by checking their
 * {@link KlighdLabelProperties#ELEMENT_IN_FOCUS}.
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
    
    
    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        KGraphElement selectedElement = context.getKGraphElement();
        
        // We need to check if the selection has changed at all
        if (selectedElement == lastSelectedElement) {
            return ActionResult.createResult(false);
        } else {
            // It's important to unfocus the last element first, because the newly focussed element may
            // well be a part of the old focussed one
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
            
            // Focus / unfocus any of the element's labels, depending on what element we're dealing with
            if (element instanceof KNode) {
                for (KPort port : ((KNode) element).getPorts()) {
                    focusGraphElementLabels(port, focus);
                }
            } else if (element instanceof KLabeledGraphElement) {
                focusGraphElementLabels((KLabeledGraphElement) element, focus);
            }
        }
    }
    
    /**
     * Sets the focus property on the given graph element.
     * 
     * @param element
     *            the element to change focus on.
     * @param focus
     *            whether the element is now focussed or not.
     */
    private void focusGraphElement(final KGraphElement element, final boolean focus) {
        KLayoutData elementLayout = element.getData(KLayoutData.class);
        elementLayout.setProperty(KlighdLabelProperties.ELEMENT_IN_FOCUS, focus);
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
