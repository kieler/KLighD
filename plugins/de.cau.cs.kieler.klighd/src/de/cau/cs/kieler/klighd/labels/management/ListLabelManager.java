/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015, 2017 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels.management;

import java.util.List;
import java.util.Objects;

import org.eclipse.elk.graph.ElkLabel;

import com.google.common.collect.Lists;

/**
 * Takes a list of label managers that do the work of resizing labels. The label managers are asked
 * to manage a given label in the order they appear in in the list. If a label manager actually does
 * anything to a label, what happens next depends on the configuration of this label manager.
 * Either, we use that result and return immediately (default), or the rest of the label managers
 * are still given the chance to further process the intermediate result.
 * 
 * <p>
 * The list label manager doesn't care about modes. This is left to the child label managers.
 * </p>
 * 
 * @author ybl
 * @author cds
 */
public class ListLabelManager extends AbstractKlighdLabelManager {
    
    /** The label managers used to manage the labels in the graph. */
    private final List<AbstractKlighdLabelManager> labelManagers = Lists.newArrayList();
    /** Whether only the first label manager that actually changes the label's text is executed. */
    private boolean stopOnFirstHit = true;
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Configures whether this label manager stops after one of its children has changed a label or
     * whether it continues.
     * 
     * @param stop
     *            {@code true} if we should stop once a label manager became active.
     * @return this label manager for method chaining.
     */
    public ListLabelManager withStopOnFirstHit(final boolean stop) {
        this.stopOnFirstHit = stop;
        return this;
    }
    
    /**
     * Adds a label manager to the end of the list.
     * 
     * @param manager
     *            the label manager to add. Must not be {@code null}.
     * @return this label manager for method chaining.
     * @throws IllegalArgumentException
     *             if {@code manager == null}.
     */
    public ListLabelManager addLabelManager(final AbstractKlighdLabelManager manager) {
        Objects.requireNonNull(manager, "The label manager cannot be null");
        
        labelManagers.add(manager);
        return this;
    }
    
    /**
     * Returns the list of label managers.
     * 
     * @return list of managers. Can be modified.
     */
    public List<AbstractKlighdLabelManager> getLabelManagers() {
        return labelManagers;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Label Management
    
    @Override
    public Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        String newLabelText = null;
        boolean foundActiveLabelManager = false;
        
        for (AbstractKlighdLabelManager manager : labelManagers) {
            Result result = manager.manageElkLabelSize(label, targetWidth);
            
            if (result.isUnmodified()) {
                // The label manager was active, but didn't do anything; we'll continue
                foundActiveLabelManager = true;
                
            } else if (result.isModified()) {
                // The manager actually did stuff!
                foundActiveLabelManager = true;
                
                newLabelText = result.getNewText();
                label.setText(newLabelText);
                
                // Whether we'll continue depends on the settings
                if (stopOnFirstHit) {
                    break;
                }
            }
        }
        
        // What we return depends on what has happened
        if (foundActiveLabelManager) {
            if (newLabelText == null) {
                // None of the managers touched the text, although at least one was active
                return Result.unmodified();
                
            } else {
                // At least one manager modified the text
                return Result.modified(newLabelText);
            }
            
        } else {
            // We haven't found a single active label manager, so pretend that we're inactive, too
            return Result.inactive();
        }
    }

}
