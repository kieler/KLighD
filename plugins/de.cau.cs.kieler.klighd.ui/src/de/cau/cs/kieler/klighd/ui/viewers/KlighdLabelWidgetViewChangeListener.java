/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.viewers;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.custom.StyledText;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IViewChangeListener;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IKGraphElementNode;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * A. 
 * @author chsch
 */
class KlighdLabelWidgetViewChangeListener implements IViewChangeListener {
    
    private final PiccoloViewerUI viewer;
    private StyledText labelWidget;


    /**
     * Constructor that just calls super.
     * 
     * @param viewer
     *            the employed {@link PiccoloViewerUI}
     * @param labelWidget
     *            the employed labelWidget
     */
    public KlighdLabelWidgetViewChangeListener(final PiccoloViewerUI viewer,
            final StyledText labelWidget) {
        this.viewer = viewer;
        this.labelWidget = labelWidget;
    }

    public void viewChanged(final ViewChange change) {
        if (change.getType() == ViewChangeType.VIEW_PORT
                || labelWidget.isDisposed()
                || !labelWidget.isVisible()) {
            return;
        }

        @SuppressWarnings("unchecked")
        final List<IKGraphElementNode<?>> graphNodes = (List<IKGraphElementNode<?>>) labelWidget.getData(
                KlighdLabelWidgetEventHandler.STYLED_TEXT_PARENTS_KEY);

        switch (change.getType()) {
        case VIEW_PORT:
            // this case is handled by the PropertyChangeListener above so do nothing here
            break;
        case COLLAPSE:
            handleCollapse(change, graphNodes);
            break;
        case HIDE:
            handleHide(change, graphNodes);
            break;
        case CLIP:
            handleClip(change, graphNodes);
            break;
        // to be continued...
        default:            
        }
    }


    private void handleCollapse(final ViewChange change, final List<IKGraphElementNode<?>> graphNodes) {
        final KNode collapsedElement = (KNode) change.getAffectedElement();

        // the last graphNode is supposed to be a KNodeNode pointing to the related KNode
        final KNode parentKNode = (KNode) Iterables.getLast(graphNodes).getViewModelElement();

        // create an iterator providing parentKNode's containers ...
        final Iterator<KNode> it =
                Iterators.filter(ModelingUtil.eAllContainers(parentKNode), KNode.class);

        final KNode clip = change.getViewer().getClip();
        
        // ... and traverse them until the current diagram clip node
        for (KNode node = it.next(); node != clip && it.hasNext(); node = it.next()) {
            if (node == collapsedElement) {
                // if we meet the hidden node hide the labelWidget and stop
                viewer.deactivateLabelWidget();
                return;
            }
        }
    }
    
    
    private void handleHide(final ViewChange change, final List<IKGraphElementNode<?>> graphNodes) {
        final KGraphElement hiddenElement = change.getAffectedElement();

        // check whether the potentially container KLabel, KPort/KEdge, and/or KNode got hidden
        for (final IKGraphElementNode<?> node : graphNodes) {
            if (node.getViewModelElement() == hiddenElement) {
                viewer.deactivateLabelWidget();
                return;
            }
        }

        // if any other KLabel, KPort, or KEdge got hidden stop here
        if (!(hiddenElement instanceof KNode)) {
            return;
        }

        // the last graphNode is supposed to be a KNodeNode pointing to the related KNode
        final KNode parentKNode = (KNode) Iterables.getLast(graphNodes).getViewModelElement();

        // create an iterator providing parentKNode's containers ...
        final Iterator<KNode> it =
                Iterators.filter(ModelingUtil.eAllContainers(parentKNode), KNode.class);

        final KNode clip = change.getViewer().getClip();
        
        // ... and traverse them until the current diagram clip node
        for (KNode node = it.next(); node != clip && it.hasNext(); node = it.next()) {
            if (node == hiddenElement) {
                // if we meet the hidden node hide the labelWidget and stop
                viewer.deactivateLabelWidget();
                return;
            }
        }
    }
    
    private void handleClip(final ViewChange change, final List<IKGraphElementNode<?>> graphNodes) {
        final KNode newClip = (KNode) change.getAffectedElement();

        // the last graphNode is supposed to be a KNodeNode pointing to the related KNode
        final KNode parentKNode = (KNode) Iterables.getLast(graphNodes).getViewModelElement();

        // create an iterator providing parentKNode's containers ...
        final Iterator<KNode> it =
                Iterators.filter(ModelingUtil.eAllContainers(parentKNode), KNode.class);

        // ... and traverse them until the current diagram clip node
        for (KNode node = it.next(); it.hasNext(); node = it.next()) {
            if (node == newClip) {
                // if we meet the hidden node hide the labelWidget and stop
                viewer.updateWidgetBounds(null);
                return;
            }
        }

        // if was not found, i.e. is not a (transitive) parent of parentKNode,
        //  the displayed label is not visible any, so ...
        viewer.deactivateLabelWidget();
    }
}