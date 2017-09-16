/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.labels.management;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.klighd.labels.management.AbstractKlighdLabelManager.Result;

/**
 * A label manager that delegates label management to methods specialized for different kinds of
 * labels distinguished by which kind of graph element they label. To use this class, extend it and
 * provide an implementation for an arbitrary subset of the following methods:
 * 
 * <ul>
 *   <li>{@link #doResizeEdgeCenterLabel(ElkLabel, double)}</li>
 *   <li>{@link #doResizeEdgeEndLabel(ElkLabel, double)}</li>
 *   <li>{@link #doResizeNodeLabel(ElkLabel, double)}</li>
 *   <li>{@link #doResizePortLabel(ElkLabel, double)}</li>
 *   <li>{@link #doResizeCommentLabel(ElkLabel, double)}</li>
 * </ul>
 * 
 * <p>
 * Left to their own devices, the methods by default return whatever {@link #defaultResult()}
 * returns. That defaults to {@link Result#unmodified()}, but can be changed to
 * {@link Result#modified(String)} with an empty string, which effectively filters out labels no
 * special management code is implemented for. Call
 * {@link #setLeaveLabelsUntouchedByDefault(boolean)} to configure this behavior.
 * </p>
 * 
 * @author cds
 */
public abstract class AbstractTypeDependentLabelManager extends AbstractKlighdLabelManager {
    
    /**
     * Whether labels we don't have special code for should be left untouched or be filtered out
     * by killing their text.
     */
    private boolean leaveLabelsUntouchedByDefault = true;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Sets whether labels that we don't treat explicitly are to be left untouched or not.
     * 
     * @param leaveUntouched
     *            if {@code true} labels will stay unmodified if we don't do anything special to
     *            them. Otherwise, their text will be set to the empty string.
     * @return this label manager for method chaining.
     */
    public AbstractTypeDependentLabelManager setLeaveLabelsUntouchedByDefault(
            final boolean leaveUntouched) {
        
        leaveLabelsUntouchedByDefault = leaveUntouched;
        return this;
    }
    
    /**
     * Returns the default result we use when we don't do anything special for a label.
     * 
     * @return the default result.
     */
    protected Result defaultResult() {
        if (leaveLabelsUntouchedByDefault) {
            return Result.unmodified();
        } else {
            return Result.modified("");
        }
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Resizing

    /**
     * Delegates label resizing to one of the specialized methods in this class.
     */
    @Override
    protected Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        // Check what kind of a label this is
        ElkGraphElement labeledElement = ElkGraphUtil.elementLabeledBy(label);
        
        if (labeledElement instanceof ElkEdge) {
            switch (label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT)) {
            case CENTER:
            case UNDEFINED:
                return doResizeEdgeCenterLabel(label, targetWidth);
                
            case HEAD:
            case TAIL:
                return doResizeEdgeEndLabel(label, targetWidth);
            }
        } else if (labeledElement instanceof ElkPort) {
            return doResizePortLabel(label, targetWidth);
        } else if (labeledElement instanceof ElkNode) {
            if (labeledElement.getProperty(CoreOptions.COMMENT_BOX)) {
                return doResizeCommentLabel(label, targetWidth);
            } else {
                return doResizeNodeLabel(label, targetWidth);
            }
        }
        
        // Shouldn't happen, but if it does, tell everyone that we didn't do nothin'
        return defaultResult();
    }

    /**
     * Implements {@link #doResizeLabel(ElkLabel, double)} for edge end labels. 
     * 
     * @implSpec
     * The default implementation simply returns {@link Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizeEdgeEndLabel(final ElkLabel label, final double targetWidth) {
        return defaultResult();
    }

    /**
     * Implements {@link #doResizeLabel(ElkLabel, double)} for edge center labels.
     * 
     * @implSpec
     * The default implementation simply returns {@link Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizeEdgeCenterLabel(final ElkLabel label, final double targetWidth) {
        return defaultResult();
    }

    /**
     * Implements {@link #doResizeLabel(ElkLabel, double)} for port labels.
     * 
     * @implSpec
     * The default implementation simply returns {@link Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizePortLabel(final ElkLabel label, final double targetWidth) {
        return defaultResult();
    }

    /**
     * Implements {@link #doResizeLabel(ElkLabel, double)} for non-comment node labels.
     * 
     * @implSpec
     * The default implementation simply returns {@link Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizeNodeLabel(final ElkLabel label, final double targetWidth) {
        return defaultResult();
    }

    /**
     * Implements {@link #doResizeLabel(ElkLabel, double)} for comment node labels.
     * 
     * @implSpec
     * The default implementation simply returns {@link Result#unmodified()}.
     * 
     * @param label
     *            the label to shorten.
     * @param targetWidth
     *            the width the label's new dimensions should try not to exceed. This can be the
     *            target width supplied to label management from the outside or a fixed width set on
     *            the label manager.
     * @return the result of doing things to the label.
     */
    protected Result doResizeCommentLabel(final ElkLabel label, final double targetWidth) {
        return defaultResult();
    }

}
