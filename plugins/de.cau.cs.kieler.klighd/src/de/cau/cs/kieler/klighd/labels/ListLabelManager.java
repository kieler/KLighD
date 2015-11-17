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
package de.cau.cs.kieler.klighd.labels;

import de.cau.cs.kieler.core.kgraph.KLabel;

/**
 * Takes a list of label managers that do the work of resizing labels. The label managers are asked to
 * manage a given label in the order they appear in in the list. If a label manager actually shortens a
 * label, what happens next depends on the configuration of this label manager. Either, we use that
 * result and return immediately, or the rest of the label managers are still given the chance to
 * further process the intermediate result.
 * 
 * @author ybl
 */
public class ListLabelManager extends AbstractKlighdLabelManager {
    
    /** The label managers used to manage the labels in the graph. */
    private final AbstractKlighdLabelManager[] labelManagers;
    /** Whether only the first label manager that actually changes the label's text is executed. */
    private final boolean stopOnFirstHit;
    

    /**
     * Create a new instance with a list of LabelManagers.
     * 
     * @param labelManagers
     *            a list of {@link AbstractKlighdLabelManager} which are used to manage the labels
     *            in the graph
     * @param stopOnFirstHit
     *            if {@code true}, we stop after the first label manager that actually shortens the
     *            label
     */
    public ListLabelManager(final boolean stopOnFirstHit,
            final AbstractKlighdLabelManager... labelManagers) {
        
        this.labelManagers = labelManagers;
        this.stopOnFirstHit = stopOnFirstHit;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final KLabel label, final double targetWidth) {
        String newLabelText = null;
        
        for (AbstractKlighdLabelManager manager : labelManagers) {
            newLabelText = manager.resizeLabel(label, targetWidth);
            
            if (newLabelText != null) {
                label.setText(newLabelText);
                
                if (stopOnFirstHit) {
                    break;
                }
            }
        }
        
        return newLabelText;
    }

}
