/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015, 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels.management;

import org.eclipse.elk.core.labels.ILabelManager;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.ElkLabel;

import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Abstract superclass for {@link ILabelManager}s to be used with KLighD. All a subclass needs to to
 * is to implement {@link #resizeLabel(ElkLabel, double)}, everything else is taken care of by this
 * class.
 * 
 * <p>
 * A label manager first and foremost has an activity state. Its
 * {@link #resizeLabel(ElkLabel, double)} method is only ever called if it is active, which it is by
 * default.
 * </p>
 * 
 * <p>
 * Whether that method is called for a specific label depends on whether the label is in the focus
 * or in the context. Label management is only ever applied to labels that are not in the focus, as
 * determined by looking at the {@link KlighdOptions#LABELS_ELEMENT_IN_FOCUS} property.
 * </p>
 * 
 * <p>
 * If a label is part of the context, the label manager goes to work. Whether it actually does
 * anything depends on its {@link Mode mode of operation}. The first, {@link Mode#TARGET_WIDTH},
 * does things only if the label currently exceeds the target width supplied to the label manager.
 * The second, {@link Mode#FIXED_TARGET_WIDTH}, does things only if the label currently exceeds a
 * fixed target width set on the label manager by calling {@link #setFixedTargetWidth(double)}. The
 * third, {@link Mode#ALWAYS_ON}, always tries to do things, regardless of the target width. Whether
 * a label manager supports all three modes depends on the label manager implementation.
 * </p>
 * 
 * <h3>Technical Remarks</h3>
 * 
 * <p>
 * The label passed to this manager is the one from the layout ElkGraph fed to the layout algorithm,
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

    //////////////////////////////////////////////////////////////////////////////////////////
    // Modes of Operation
    
    /**
     * The modes a label manager can operate in.
     */
    public static enum Mode {
        /**
         * Labels are only shortened if they exceed the target width received from the outside.
         * This will usually be a target width computed by the layout algorithm this label manager
         * is called by. This is the default mode.
         */
        TARGET_WIDTH,
        
        /**
         * Labels are shortened if they exceed a fixed target width set on the label manager. The
         * target width computed by layout algorithms is ignored. For this to make sense the fixed
         * target width needs to be configured by calling
         * {@link AbstractKlighdLabelManager#setFixedTargetWidth(double)}.
         */
        FIXED_TARGET_WIDTH,
        
        /**
         * Labels are shortened regardless of whether or not they exceed any target width. The
         * target width may still be used by a label manager to determine the amount of shortening.
         */
        ALWAYS_ON;
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /** Constant indicating that no valid fixed target width is set. */
    public static final double NO_FIXED_TARGET_WIDTH = -1;
    
    /** Whether the label manager is currently active or not. */
    private boolean active = true;
    /** Our mode of operation. */
    private Mode mode = Mode.TARGET_WIDTH;
    /** The width to try and shorten labels to. */
    private double fixedTargetWidth = NO_FIXED_TARGET_WIDTH;


    //////////////////////////////////////////////////////////////////////////////////////////
    // Label Manager Configuration
    
    /**
     * Activates or deactivates this label manager.
     * 
     * @param isActive
     *            {@code true} if the label manager should potentially do things to labels,
     *            {@code false} if it should leave them alone. This method call can be chained with
     *            other configuration method calls.
     * @return this label manager.
     */
    public final AbstractKlighdLabelManager setActive(final boolean isActive) {
        this.active = isActive;
        
        return this;
    }
    
    /**
     * Checks whether this label manager is currently active.
     * 
     * @return {@code true} if this label manager is currently active.
     */
    public final boolean isActive() {
        return active;
    }
    
    /**
     * Returns the mode this label manager is configured to operate in.
     * 
     * @return the label manager's mode.
     */
    public final Mode getMode() {
        return mode;
    }
    
    /**
     * Sets the mode this label manager should operate in. Note that if the mode is set to
     * {@link Mode#FIXED_TARGET_WIDTH}, a fixed target width needs to be configured by calling
     * {@link #setFixedTargetWidth(double)}.This method call can be chained with other configuration
     * method calls.
     * 
     * @param newMode
     *            the new mode.
     * @return this label manager.
     */
    public final AbstractKlighdLabelManager setMode(final Mode newMode) {
        mode = newMode;
        
        return this;
    }
    
    /**
     * Returns the fixed target width this label manager is configured with, if any.
     * 
     * @return fixed target width or {@link #NO_FIXED_TARGET_WIDTH} if none is configured.
     */
    public final double getFixedTargetWidth() {
        return fixedTargetWidth;
    }
    
    /**
     * Set the fixed non-negative target width to be used. The fixed target width is only meaningful
     * if the label manager's state is {@link Mode#FIXED_TARGET_WIDTH} or {@link Mode#ALWAYS_ON}. In
     * the latter case, the fixed target width is used as the target width which label managers may
     * or may not pay attention to. To stop that from happening, call this method with a negative
     * value.
     * 
     * <p>
     * This method call can be chained with other configuration method calls.
     * </p>
     * 
     * @param targetWidth
     *            the new target width to shorten labels to or a negative value if no fixed target
     *            width should be used.
     * @return this label manager.
     */
    public final AbstractKlighdLabelManager setFixedTargetWidth(final double targetWidth) {
        if (targetWidth < 0) {
            fixedTargetWidth = NO_FIXED_TARGET_WIDTH;
        } else {
            this.fixedTargetWidth = targetWidth;
        }
        
        return this;
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    // Label Resizing

    @Override
    public final KVector manageLabelSize(final Object label, final double processorTargetWidth) {
        if (label instanceof ElkLabel) {
            ElkLabel elkLabel = (ElkLabel) label;

            KVector newLabelSize = null;
            String newLabelText = elkLabel.getText();

            if (isActive()) {
                if (isInContext(elkLabel)) {
                    // The label is not in the focus right now, so shorten it
                    double effectiveTargetWidth =
                            determineEffectiveTargetWidth(processorTargetWidth);
                    
                    newLabelText = resizeLabel(elkLabel, effectiveTargetWidth);
                    
                    if (newLabelText != null) {
                        elkLabel.setText(newLabelText);
                        newLabelSize = calculateFinalLabelSize(elkLabel, newLabelText);
                    }

                    // Make sure KLighD knows if we shortened the label
                    if (newLabelSize == null) {
                        elkLabel.setProperty(KlighdOptions.LABELS_MANAGEMENT_RESULT,
                                LabelManagementResult.MANAGED_UNMODIFIED);
                    } else {
                        elkLabel.setProperty(KlighdOptions.LABELS_MANAGEMENT_RESULT,
                                LabelManagementResult.MANAGED_MODIFIED);
                    }
                } else {
                    // We won't modify the label's text, but we need to tell the layout algorithm
                    // about its size when it's unshortened. If we don't, word-wrapped labels won't
                    // have their height reset if they move into focus and thus consist of their
                    // original text
                    newLabelSize = calculateFinalLabelSize(elkLabel, elkLabel.getText());
                    elkLabel.setProperty(KlighdOptions.LABELS_MANAGEMENT_RESULT,
                            LabelManagementResult.MANAGED_UNMODIFIED);
                }
            }

            return newLabelSize;
            
        } else {
            // This isn't an ElkLabel
            return null;
        }
    }
    
    /**
     * Determines which target width to pass on to the label manager implementation. Depends on the
     * mode and on whether a fixed target width was set.
     * 
     * @param suppliedTargetWidth
     *            the target width passed to {@link #manageLabelSize(Object, double)}.
     * @return the target width to use.
     */
    private double determineEffectiveTargetWidth(final double suppliedTargetWidth) {
        switch (mode) {
        case TARGET_WIDTH:
            return suppliedTargetWidth;
            
        case FIXED_TARGET_WIDTH:
            return fixedTargetWidth;
            
        case ALWAYS_ON:
            return fixedTargetWidth != NO_FIXED_TARGET_WIDTH
                ? fixedTargetWidth
                : suppliedTargetWidth;
        }
    
        // This should never happen
        assert false;
        return suppliedTargetWidth;
    }
    
    /**
     * Calculates the final size of the given label if it were to display the given text.
     * 
     * @param elkLabel
     *            the label.
     * @param text
     *            the text.
     * @return the label's new size.
     */
    private KVector calculateFinalLabelSize(final ElkLabel elkLabel, final String text) {
        // Find the label's rendering
        KRendering rootRendering = elkLabel.getProperty(KlighdOptions.K_RENDERING);
        if (rootRendering instanceof KRenderingRef) {
            rootRendering = ((KRenderingRef) rootRendering).getRendering();
        }
        
        Bounds newSize = null;
        
        if (rootRendering instanceof KText) {
            // This is the easy case that we can handle quickly
            newSize = PlacementUtil.estimateTextSize(((KText) rootRendering), text);
        } else {
            // Employ the big guns! Find the KText hidden in the rendering hierarchy, temporarily
            // set its text override, estimate the size of the whole rendering, and kill the
            // override again
            KText kText = LabelManagementUtil.ktextFor(elkLabel);
            kText.setProperty(KlighdOptions.LABELS_TEXT_OVERRIDE, text);
            
            newSize = PlacementUtil.estimateSize(rootRendering, new Bounds(0, 0));
            
            kText.setProperty(KlighdOptions.LABELS_TEXT_OVERRIDE, null);
        }
        
        if (newSize != null) {
            return new KVector(newSize.getWidth(), newSize.getHeight());
        } else {
            return new KVector();
        }
    }

    /**
     * Check whether a label is in context or not.
     * 
     * @param label
     *            the label to check.
     * @return {@code true} if the label is part of the context instead of being in focus,
     *         {@code false} otherwise.
     */
    private boolean isInContext(final ElkLabel label) {
        return !label.getProperty(KlighdOptions.LABELS_ELEMENT_IN_FOCUS);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    // Subclass

    /**
     * Does the actual work of resizing the given label, which is guaranteed to be an
     * {@link ElkLabel}. Apart from this minor detail, this method should pretty much adhere to the
     * contract specified on the {@link #manageLabelSize(Object, double)} method.
     * 
     * <p>
     * Label managers will usually want to check which mode of operation they are in. For some, it
     * won't make any difference. For others, it will be of particular interest whether the mode is
     * {@link Mode#ALWAYS_ON} or not. If it is, they will always want to do their thing, regardless
     * of whether the label currently exceeds the desired size or not.
     * </p>
     * 
     * <p>
     * This method should not be called directly by label management clients. The only reason for it
     * to be public is that compound label managers need to call this method on child label
     * managers.
     * </p>
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the shortened text of the label as a string
     */
    public abstract String resizeLabel(ElkLabel label, double targetWidth);
    
}
