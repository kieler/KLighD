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

import org.eclipse.elk.core.labels.ILabelManager;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Abstract superclass for {@link ILabelManager}s to be used with KLighD. All a subclass needs to to
 * is to implement {@link #resizeLabel(ElkLabel, double)}, everything else is taken care of by this
 * class.
 * 
 * <p>
 * This class manages an activity state. Label managers inheriting from this class can be switched
 * on or off, as required. If a label manager is switched off, {@link #resizeLabel(ElkLabel, double)}
 * is not called.
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
 * @author ybl
 */
public abstract class AbstractKlighdLabelManager implements ILabelManager {
    
    /** Whether the label manager is currently active or not. */
    private boolean active = true;
    /**
     * Whether the manager's target width should be used or the one provided by the call to
     * {@link #manageLabelSize(Object, double)}.
     */
    private boolean useFixedTargetWidth = false;
    /** The width to try and shorten labels to. */
    private double fixedTargetWidth;


    //////////////////////////////////////////////////////////////////////////////////////////
    // Label Manager Configuration
    
    /**
     * Checks whether this label manager is currently active.
     * 
     * @return {@code true} if this label manager is currently active.
     */
    public final boolean isActive() {
        return active;
    }
    
    /**
     * Activates or deactivates this label manager.
     * 
     * @param isActive
     *            {@code true} if the label manager should shorten labels, {@code false} if it
     *            should leave them alone. This method call can be chained with other configuration
     *            method calls.
     * @return this label manager.
     */
    public final AbstractKlighdLabelManager setActive(final boolean isActive) {
        this.active = isActive;
        
        return this;
    }
    
    /**
     * Override the target width provided by each call to {@link #manageLabelSize(Object, double)}
     * with a fixed target width. This method call can be chained with other configuration method calls.
     * 
     * @param targetWidth
     *            the new target width to shorten labels to.
     * @return this label manager.
     */
    public final AbstractKlighdLabelManager fixTargetWidth(final double targetWidth) {
        this.fixedTargetWidth = targetWidth;
        useFixedTargetWidth = true;
        
        return this;
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    // Label Resizing

    /**
     * {@inheritDoc}
     */
    public final KVector manageLabelSize(final Object label, final double processorTargetWidth) {
        if (label instanceof ElkLabel) {
            ElkLabel elkLabel = (ElkLabel) label;

            KVector newLabelSize = null;
            String newLabelText = elkLabel.getText();

            if (isActive()) {
                if (isInContext(elkLabel)) {
                    // The label is not in the focus right now, so shorten it
                    double sizeToResizeTo = useFixedTargetWidth
                            ? fixedTargetWidth
                            : processorTargetWidth;
                    newLabelText = resizeLabel(elkLabel, sizeToResizeTo);
                    
                    if (newLabelText != null) {
                        elkLabel.setText(newLabelText);
                        
                        // calculate the new Bounds of the text
                        final FontData font = LabelManagementUtil.fontDataFor(elkLabel);
                        Bounds newSize = PlacementUtil.estimateTextSize(font, newLabelText);
                        newLabelSize = new KVector(newSize.getWidth(), newSize.getHeight());
                    }

                    // Make sure KLighD knows if we shortened the label
                    if (newLabelSize == null) {
                        elkLabel.setProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT,
                                LabelManagementResult.MANAGED_UNMODIFIED);
                    } else {
                        elkLabel.setProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT,
                                LabelManagementResult.MANAGED_MODIFIED);
                    }
                } else {
                    // We won't modify the label's text, but we need to tell the layout algorithm
                    // about its size when it's unshortened. If we don't, word-wrapped labels won't
                    // have their height reset if they move into focus and thus consist of a single
                    // line of text
                    final FontData font = LabelManagementUtil.fontDataFor(elkLabel);
                    Bounds newSize = PlacementUtil.estimateTextSize(font, elkLabel.getText());
                    newLabelSize = new KVector(newSize.getWidth(), newSize.getHeight());

                    elkLabel.setProperty(KlighdLabelProperties.LABEL_MANAGEMENT_RESULT,
                            LabelManagementResult.MANAGED_UNMODIFIED);
                }
            }

            return newLabelSize;
        }

        // This isn't a KLabel or this label manager is not active...
        return null;
    }

    /**
     * Does the actual work of resizing the given label, which is guaranteed to be an
     * {@link ElkLabel}. Apart from this minor detail, this method should adhere to the contract
     * specified on the {@link #manageLabelSize(Object, double)} method.
     * 
     * <p>
     * This method should not be called directly by label management clients. The only reason for it
     * to be public is that compound label managers need to call this method on child label managers.
     * </p>
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed.
     * @return the shortened text of the label as a string
     */
    public abstract String resizeLabel(ElkLabel label, double targetWidth);

    /**
     * Check whether a label is in context or not.
     * 
     * @param label
     *            the label to check.
     * @return {@code true} if the label is part of the context instead of being in focus,
     *         {@code false} otherwise.
     */
    private boolean isInContext(final ElkLabel label) {
        return !label.getProperty(KlighdLabelProperties.ELEMENT_IN_FOCUS);
    }
}
