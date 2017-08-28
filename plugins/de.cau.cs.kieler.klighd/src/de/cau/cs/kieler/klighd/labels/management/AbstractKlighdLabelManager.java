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
 * Abstract superclass for {@link ILabelManager}s to be used with KLighD. Ensures that the label is
 * in fact an {@link ElkLabel} instance and then delegates to
 * {@link #manageElkLabelSize(ElkLabel, double)}, which is the entry method that should be used
 * inside this framework (which is of particular interest to composite label managers).
 * 
 * <p>
 * A label manager first and foremost has an activity state. Its
 * {@link #doResizeLabel(ElkLabel, double)} method is only ever called if it is active, which it is
 * by default.
 * </p>
 * 
 * <p>
 * Whether that method is called for a specific label depends on whether the label is part of the
 * focus or the context. By default, label management is only ever applied to labels that are not
 * focussed, as determined by looking at the {@link KlighdOptions#LABELS_ELEMENT_IN_FOCUS} property.
 * That behavior can be changed by overriding {@link #manageElkLabelSize(ElkLabel, double)}.
 * </p>
 * 
 * <p>
 * If a label is part of the context, the label manager goes to work. Whether it actually does
 * anything depends on its {@link Mode mode of operation}. The first, {@link Mode#TARGET_WIDTH},
 * does things only if the label currently exceeds the target width. That target width can either be
 * the original target width supplied when the label manager was called, or a fixed target width
 * configured by a call to {@link #setFixedTargetWidth(double)}. The other mode,
 * {@link Mode#ALWAYS_ON}, always tries to do things, regardless of the target width. Whether a
 * label manager supports both modes depends on the label manager implementation.
 * </p>
 * 
 * 
 * <h3>Notes for Subclasses</h3>
 * 
 * <p>
 * Subclasses only need to override {@link #doResizeLabel(ElkLabel, double)} is a good idea. If
 * behavior related to the label manager's activity state or labels in focus or context should be
 * changed, override {@link #manageElkLabelSize(ElkLabel, double)} as well.
 * </p>
 * 
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
    public final KVector manageLabelSize(final Object label, final double targetWidth) {
        if (label instanceof ElkLabel) {
            ElkLabel elkLabel = (ElkLabel) label;

            double effectiveTargetWidth = fixedTargetWidth != NO_FIXED_TARGET_WIDTH
                    ? fixedTargetWidth
                    : targetWidth;
            Result managementResult = manageElkLabelSize(elkLabel, effectiveTargetWidth);
            
            // What happens next depends on the result...
            if (managementResult.isUnmodified()) {
                // The label wasn't modified, but we need to tell the layout algorithm about the
                // label's unmodified size to restore that after old label management results
                elkLabel.setProperty(KlighdOptions.LABELS_MANAGEMENT_RESULT,
                        LabelManagementResult.MANAGED_UNMODIFIED);
                return calculateFinalLabelSize(elkLabel, elkLabel.getText());
                
            } else if (managementResult.isModified()) {
                // The label was modified, so return the new size
                elkLabel.setProperty(KlighdOptions.LABELS_MANAGEMENT_RESULT,
                        LabelManagementResult.MANAGED_MODIFIED);
                elkLabel.setText(managementResult.getNewText());
                return calculateFinalLabelSize(elkLabel, elkLabel.getText());
                
            } else {
                // We didn't even want to do anything...
                // TODO: Should this case be handled just like the unmodified case?
                return null;
            }
            
        } else {
            // This isn't an ElkLabel
            return null;
        }
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


    //////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods

    /**
     * Check whether a label is in context or not.
     * 
     * @param label
     *            the label to check.
     * @return {@code true} if the label is part of the context instead of being in focus,
     *         {@code false} otherwise.
     */
    protected final boolean isInContext(final ElkLabel label) {
        return !label.getProperty(KlighdOptions.LABELS_ELEMENT_IN_FOCUS);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    // Subclass
    
    /**
     * Manages the given label. This method includes checks as to whether the label manager is
     * active (in which case subclass implementations are not called) and in focus (in which case
     * subclass implementations are not called either). If label management should be applied, this
     * method calls {@link #doResizeLabel(ElkLabel, double)}.
     * 
     * <p>
     * The method can be overridden to change that behavior, but it is recommended to always return
     * {@link Result#inactive()} if {@link #isActive()} returns {@code false}.
     * </p>
     * 
     * <p>
     * Composite label managers should call this method on their children.
     * </p>
     * 
     * @param label
     *            the label to be managed.
     * @param targetWidth
     *            the target width, either supplied by the outside or fixed on this manager.
     * @return label management result.
     */
    protected Result manageElkLabelSize(final ElkLabel label, final double targetWidth) {
        if (isActive()) {
            if (isInContext(label)) {
                return doResizeLabel(label, targetWidth);
            } else {
                return Result.unmodified();
            }
        } else {
            return Result.inactive();
        }
    }
    
    /**
     * Attempts to resize the given label.
     * 
     * <p>
     * Label managers will usually want to check which mode of operation they are in. For some, it
     * won't make any difference. For others, it will be of particular interest whether the mode is
     * {@link Mode#ALWAYS_ON} or not. If it is, they will always want to do their thing, regardless
     * of whether the label currently exceeds the desired size or not.
     * </p>
     * 
     * @implSpec The default implementation delegates to the appropriate of the more specific
     *           methods. If none seems appropriate, it returns {@code Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        // Shouldn't happen, but if it does, tell everyone that we didn't do nothin'
        return Result.unmodified();
    }
    

    //////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes
    
    /**
     * The modes a label manager can operate in.
     */
    public static enum Mode {
        /**
         * Labels are only shortened if they exceed the target width. This will either be a target
         * width computed by the layout algorithm this label manager is called by, or a fixed
         * target width. This is the default mode.
         */
        TARGET_WIDTH,
        
        /**
         * Labels are shortened regardless of whether or not they exceed any target width. The
         * target width may still be used by a label manager to determine the amount of shortening.
         */
        ALWAYS_ON;
    }
    
    /**
     * Class used to represent results produced by label managers.
     */
    public static final class Result {
        
        /** Single instance used to represent results of inactive label managers. */
        private static final Result RESULT_INACTIVE =
                new Result(LabelManagementResult.UNMANAGED, null);
        /** Single instance used to represent labels unmodified by active label managers. */
        private static final Result RESULT_UNMODIFIED =
                new Result(LabelManagementResult.MANAGED_UNMODIFIED, null);
        
        /** The label management result. */
        private final LabelManagementResult result;
        /** The new text assigned to a label, if any. */
        private final String newText;
        
        
        /**
         * Private constructor. Use the create methods.
         */
        private Result(final LabelManagementResult result, final String newText) {
            this.result = result;
            this.newText = newText;
        }
        
        
        /**
         * Creates a result representing an inactive label manager.
         * 
         * @return the result.
         */
        public static Result inactive() {
            return RESULT_INACTIVE;
        }
        
        /**
         * Creates a result representing an active label manager not having done anything to a
         * label.
         * 
         * @return the result.
         */
        public static Result unmodified() {
            return RESULT_UNMODIFIED;
        }
        
        /**
         * Creates a result representing an active label manager having modified a label's text.
         * 
         * @param text
         *            the label's new text.
         * @return the result.
         */
        public static Result modified(final String text) {
            return new Result(LabelManagementResult.MANAGED_MODIFIED, text);
        }
        
        
        /**
         * Checks if the result was produced by an inactive label manager.
         * 
         * @return {@code true} if the label manager was inactive.
         */
        public boolean isInactive() {
            return result == LabelManagementResult.UNMANAGED;
        }

        /**
         * Checks if the result was produced by an active label manager that didn't do anything to
         * a label.
         * 
         * @return {@code true} if the label manager was active, but didn't do anything.
         */
        public boolean isUnmodified() {
            return result == LabelManagementResult.MANAGED_UNMODIFIED;
        }

        /**
         * Checks if the result was produced by an active label manager that modified a label. The
         * label's new text can be retrieved by calling {@link #getNewText()}.
         * 
         * @return {@code true} if the label manager was active and has modified a label's text.
         */
        public boolean isModified() {
            return result == LabelManagementResult.MANAGED_MODIFIED;
        }
        
        /**
         * Returns a label's new text, if any. There only is a text if {@link #isModified()} returns
         * {@code true}.
         * 
         * @return the new text or {@code null} if the text was actually not modified.
         */
        public String getNewText() {
            return newText;
        }
        
    }
    
}
