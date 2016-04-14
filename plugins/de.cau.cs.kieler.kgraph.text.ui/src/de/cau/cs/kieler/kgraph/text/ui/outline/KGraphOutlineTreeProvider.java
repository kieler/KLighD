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
package de.cau.cs.kieler.kgraph.text.ui.outline;

import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KIdentifier;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.graph.KGraphData;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.PersistentEntry;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

import de.cau.cs.kieler.klighd.krendering.KPlacement;
import de.cau.cs.kieler.klighd.krendering.KPlacementData;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KStyle;

/**
 * Customization of the default outline structure.
 * 
 * @author msp
 */
public class KGraphOutlineTreeProvider extends DefaultOutlineTreeProvider {
    
    // CHECKSTYLEOFF MethodName
    
    /**
     * Create children for graph data instances.
     * 
     * @param parentNode the parent outline node
     * @param graphData a graph data instance
     */
    protected void _createChildren(final IOutlineNode parentNode, final KGraphData graphData) {
        for (EObject childElement : graphData.eContents()) {
            // persistent entries are parsed to the properties map, so they don't need to be displayed
            if (!(childElement instanceof PersistentEntry)) {
                createNode(parentNode, childElement);
            }
        }
    }
    
    /**
     * If a layout data instance has no properties, it is regarded as a leaf.
     * 
     * @param layoutData a shape layout or edge layout
     * @return true if the layout data has no properties
     */
    protected boolean _isLeaf(final KLayoutData layoutData) {
        return layoutData.getProperties().isEmpty();
    }
    
    /**
     * Create children for edge layouts.
     * 
     * @param parentNode the parent outline node
     * @param edgeLayout an edge layout
     */
    protected void _createChildren(final IOutlineNode parentNode, final KEdgeLayout edgeLayout) {
        for (EObject childElement : edgeLayout.eContents()) {
            // persistent entries are parsed to the properties map, so they don't need to be displayed
            if (!(childElement instanceof PersistentEntry)
                    // points are already displayed in the text of the edge layout
                    && !(childElement instanceof KPoint)) {
                createNode(parentNode, childElement);
            }
        }
    }
    
    /**
     * Create children for graph elements.
     * 
     * @param parentNode the parent outline node
     * @param graphElement a graph element
     */
    protected void _createChildren(final IOutlineNode parentNode, final KGraphElement graphElement) {
        for (EObject childElement : graphElement.eContents()) {
            // the identifier is shown in the element's text, so it doesn't need to be displayed
            if (!(childElement instanceof KIdentifier)) {
                createNode(parentNode, childElement);
            }
        }
    }
    
    /**
     * The details of placements are not shown as separate tree elements.
     * 
     * @param placement a placement
     * @return true
     */
    protected boolean _isLeaf(final KPlacement placement) {
        return true;
    }
    
    /**
     * The details of placement data are not shown as separate tree elements.
     * 
     * @param placementData a placement data instance
     * @return true
     */
    protected boolean _isLeaf(final KPlacementData placementData) {
        return true;
    }
    
    /**
     * The details of styles are not shown as separate tree elements.
     * 
     * @param style a style
     * @return true
     */
    protected boolean _isLeaf(final KStyle style) {
        return true;
    }
    
    /**
     * The details of positions are not shown as separate tree elements.
     * 
     * @param position a position
     * @return true
     */
    protected boolean _isLeaf(final KPosition position) {
        return true;
    }
    
}
