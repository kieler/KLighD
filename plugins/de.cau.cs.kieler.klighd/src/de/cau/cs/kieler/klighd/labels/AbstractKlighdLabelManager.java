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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.labels.ILabelManager;
import de.cau.cs.kieler.kiml.labels.LabelManagementOptions;
import de.cau.cs.kieler.kiml.labels.LabelManagementResult;

/**
 * Abstract superclass for {@link ILabelManager}s to be used with KLighD. All a subclass needs to to is
 * to implement {@link #doResizeLabelToWidth(KLabel, double)}, everything else is taken care of by this
 * class.
 * 
 * <p>
 * This class also manages an activity state. Label managers inheriting from this class can be switched
 * on or off, as required. If a label manager is switched off,
 * {@link #doResizeLabelToWidth(KLabel, double)} is not called.
 * </p>
 * 
 * <p>
 * On a technical note, what happens here is that KLighD needs to know if a label was managed or not
 * through the {@link LabelManagementOptions#LABEL_MANAGEMENT_RESULT} property. This class lets clients
 * abstract away from this technical detail.
 * </p>
 * 
 * @author cds
 */
public abstract class AbstractKlighdLabelManager implements ILabelManager {
    
    /** Whether this label manager is currently active or not. */
    private boolean active;

    
    //////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    
    /**
     * Creates a new instance that is initially active.
     */
    public AbstractKlighdLabelManager() {
        this(true);
    }
    
    /**
     * Create a new instance that is either initially active or not.
     * 
     * @param active whether the new label manager shall be initially active or not.
     */
    public AbstractKlighdLabelManager(final boolean active) {
        this.active = active;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////
    // Active State
    
    /**
     * Checks whether this label manager is currently managing labels or not.
     * 
     * @return {@code true} if this label manager is currently managing labels.
     */
    public final boolean isActive() {
        return active;
    }
    
    /**
     * Switches this label manager on or off as it was previously off or on, respectively.
     */
    public final void toggleActive() {
        active = !active;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////
    // Label Resizing

    /**
     * {@inheritDoc}
     */
    public final KVector resizeLabelToWidth(final Object label, final double targetWidth) {
        if (label instanceof KLabel) {
            KLabel kLabel = (KLabel) label;
            final KShapeLayout labelLayout = kLabel.getData(KShapeLayout.class);
            
            KVector newLabelSize = null;
            if (isActive()) {
                // Label exceeds target width, so shorten it
                newLabelSize = doResizeLabelToWidth(kLabel, targetWidth);
            }
            
            // Make sure KLighD knows if we shortened the label
            if (newLabelSize == null) {
                labelLayout.setProperty(LabelManagementOptions.LABEL_MANAGEMENT_RESULT,
                        LabelManagementResult.MANAGED_UNMODIFIED);
            } else {
                labelLayout.setProperty(LabelManagementOptions.LABEL_MANAGEMENT_RESULT,
                        LabelManagementResult.MANAGED_MODIFIED);
            }
            
            return newLabelSize;
        }
        
        // This isn't a KLabel...
        return null;
    }
    
    /**
     * Does the actual work of resizing the given label, which is guaranteed to be a {@link KLabel}.
     * Apart from this minor detail, this method should adhere to the contract specified on the
     * {@link #resizeLabelToWidth(Object, double)} method.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed.
     * @return the label's dimensions after shortening or {@code null} if the label has not been
     *         shortened.
     */
    protected abstract KVector doResizeLabelToWidth(final KLabel label, final double targetWidth);

}
