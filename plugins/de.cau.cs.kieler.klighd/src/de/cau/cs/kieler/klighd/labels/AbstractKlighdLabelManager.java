/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
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

/**
 * Abstract superclass for {@link ILabelManager}s to be used with KLighD. All a subclass needs to to is
 * to implement {@link #doResizeLabelToWidth(KLabel, double)}, everything else is taken care of by this
 * class.
 * 
 * <p>
 * This class manages an activity state. Label managers inheriting from this class can be switched
 * on or off, as required. If a label manager is switched off,
 * {@link #doResizeLabelToWidth(KLabel, double)} is not called.
 * </p>
 * 
 * <h3>Technical Remarks</h3>
 * 
 * <p>
 * The label passed to this manager is the one from the layout KGraph fed to the layout algorithm,
 * not the one used in KLighD's view model. This means that we need to remember the label's new text
 * somewhere. We actually remember it by modifying the text of the layout graph's label. When
 * applying the layout results,
 * {@link de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager KlighdLayoutManager}
 * checks if the layout graph's label has a {@link LabelManagementResult} attached to it that
 * indicates that a label manager was active. If so, it applies the label's new text to a property
 * set on the label ({@link KlighdLabelProperties#LABEL_TEXT_OVERRIDE}) which is then used as the
 * label's text when displaying the label. Setting a proper {@link LabelManagementResult} on a given
 * label is something subclasses do not need to worry about. The necessary logic is completely
 * encapsulated in this base class.
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
                labelLayout.setProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT,
                        LabelManagementResult.MANAGED_UNMODIFIED);
            } else {
                labelLayout.setProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT,
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
