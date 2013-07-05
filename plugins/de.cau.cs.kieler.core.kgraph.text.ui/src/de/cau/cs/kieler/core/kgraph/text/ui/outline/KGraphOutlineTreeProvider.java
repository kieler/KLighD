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
package de.cau.cs.kieler.core.kgraph.text.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Customization of the default outline structure.
 * 
 * @author msp
 */
public class KGraphOutlineTreeProvider extends DefaultOutlineTreeProvider {
    
    /**
     * Create children for graph data instances.
     * 
     * @param parentNode the parent outline node
     * @param graphData a graph data instance
     */
    protected void _createChildren(IOutlineNode parentNode, KGraphData graphData) {
        for (EObject childElement : graphData.eContents()) {
            // persistent entries are parsed to the properties map, so they don't need to be displayed
            if (!(childElement instanceof PersistentEntry)) {
                createNode(parentNode, childElement);
            }
        }
    }
    
    /**
     * If a shape layout has no properties, it is regarded as a leaf.
     * 
     * @param shapeLayout a shape layout
     * @return true if the shape layout has no properties
     */
    protected boolean _isLeaf(final KShapeLayout shapeLayout) {
        return shapeLayout.getProperties().isEmpty();
    }
    
    /**
     * Create children for edge layouts.
     * 
     * @param parentNode the parent outline node
     * @param edgeLayout an edge layout
     */
    protected void _createChildren(IOutlineNode parentNode, KEdgeLayout edgeLayout) {
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
     * If an edge layout has no properties, it is regarded as a leaf.
     * 
     * @param edgeLayout an edge layout
     * @return true if the edge layout has no properties
     */
    protected boolean _isLeaf(final KEdgeLayout edgeLayout) {
        return edgeLayout.getProperties().isEmpty();
    }
    
    /**
     * Create children for graph elements.
     * 
     * @param parentNode the parent outline node
     * @param graphElement a graph element
     */
    protected void _createChildren(IOutlineNode parentNode, KGraphElement graphElement) {
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
