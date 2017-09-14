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

import org.eclipse.elk.graph.ElkLabel;

/**
 * Applies an existing label manager only to certain kinds of labels. Obtain a new instance using
 * {@link #wrap(AbstractKlighdLabelManager)} and configure it by calling
 * {@link #setLeaveLabelsUntouchedByDefault(boolean)} and the {@code applyTo*Labels(...)} methods.
 * 
 * @author cds
 */
public final class TypeConditionLabelManager extends AbstractTypeDependentLabelManager {

    /** The wrapped label manager. */
    private final AbstractKlighdLabelManager labelManager;
    
    // Whether the label manager should be applied to different kinds of labels 
    private boolean applyToEdgeEndLabels = false;
    private boolean applyToEdgeCenterLabels = false;
    private boolean applyToNodeLabels = false;
    private boolean applyToPortLabels = false;

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Creation
    
    /**
     * Creates a new instance that wraps the given label manager.
     * 
     * @param labelManager
     *            the label manager to wrap.
     */
    private TypeConditionLabelManager(final AbstractKlighdLabelManager labelManager) {
        this.labelManager = labelManager;
    }
    
    /**
     * Creates a new instance that wraps the given label manager. By default, it is not applied to
     * any label. Change that using the configuration methods.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrap(final AbstractKlighdLabelManager labelManager) {
        return new TypeConditionLabelManager(labelManager);
    }
    
    /**
     * Creates a new instance that wraps the given label manager and applies it to edge end labels.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrapForEdgeEndLabels(
            final AbstractKlighdLabelManager labelManager) {
        
        return new TypeConditionLabelManager(labelManager)
                .applyToEdgeEndLabels(true);
    }
    
    /**
     * Creates a new instance that wraps the given label manager and applies it to edge center
     * labels.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrapForEdgeCenterLabels(
            final AbstractKlighdLabelManager labelManager) {
        
        return new TypeConditionLabelManager(labelManager)
                .applyToEdgeCenterLabels(true);
    }
    
    /**
     * Creates a new instance that wraps the given label manager and applies it to both edge end
     * and edge center labels.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrapForEdgeLabels(
            final AbstractKlighdLabelManager labelManager) {
        
        return new TypeConditionLabelManager(labelManager)
                .applyToEdgeEndLabels(true)
                .applyToEdgeCenterLabels(true);
    }
    
    /**
     * Creates a new instance that wraps the given label manager and applies it to node labels.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrapForNodeLabels(
            final AbstractKlighdLabelManager labelManager) {
        
        return new TypeConditionLabelManager(labelManager)
                .applyToNodeLabels(true);
    }
    
    /**
     * Creates a new instance that wraps the given label manager and applies it to port labels.
     * 
     * @param labelManager
     *            the label manager to wrap.
     * @return the new wrapping label manager.
     */
    public static TypeConditionLabelManager wrapForPortLabels(
            final AbstractKlighdLabelManager labelManager) {
        
        return new TypeConditionLabelManager(labelManager)
                .applyToPortLabels(true);
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * {@inheritDoc}
     * 
     * <p>
     * This class overrides the method to provide a specialized return type.
     * </p>
     */
    @Override
    public TypeConditionLabelManager setLeaveLabelsUntouchedByDefault(
            final boolean leaveUntouched) {
        
        super.setLeaveLabelsUntouchedByDefault(leaveUntouched);
        return this;
    }
    
    /**
     * Configures the label manager to apply the wrapped manager to edge end labels.
     * 
     * @param apply
     *            whether the wrapped label manager should be applied to edge end labels.
     * @return this label manager for method chaining.
     */
    public TypeConditionLabelManager applyToEdgeEndLabels(final boolean apply) {
        this.applyToEdgeEndLabels = apply;
        return this;
    }
    
    /**
     * Configures the label manager to apply the wrapped manager to edge center labels.
     * 
     * @param apply
     *            whether the wrapped label manager should be applied to edge center labels.
     * @return this label manager for method chaining.
     */
    public TypeConditionLabelManager applyToEdgeCenterLabels(final boolean apply) {
        this.applyToEdgeCenterLabels = apply;
        return this;
    }
    
    /**
     * Configures the label manager to apply the wrapped manager to node labels.
     * 
     * @param apply
     *            whether the wrapped label manager should be applied to node labels.
     * @return this label manager for method chaining.
     */
    public TypeConditionLabelManager applyToNodeLabels(final boolean apply) {
        this.applyToNodeLabels = apply;
        return this;
    }
    
    /**
     * Configures the label manager to apply the wrapped manager to port labels.
     * 
     * @param apply
     *            whether the wrapped label manager should be applied to port labels.
     * @return this label manager for method chaining.
     */
    public TypeConditionLabelManager applyToPortLabels(final boolean apply) {
        this.applyToPortLabels = apply;
        return this;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Resizing

    @Override
    protected Result doResizeEdgeEndLabel(final ElkLabel label, final double targetWidth) {
        if (applyToEdgeEndLabels) {
            return labelManager.doResizeLabel(label, targetWidth);
        } else {
            return defaultResult();
        }
    }

    @Override
    protected Result doResizeEdgeCenterLabel(final ElkLabel label, final double targetWidth) {
        if (applyToEdgeCenterLabels) {
            return labelManager.doResizeLabel(label, targetWidth);
        } else {
            return defaultResult();
        }
    }

    @Override
    protected Result doResizePortLabel(final ElkLabel label, final double targetWidth) {
        if (applyToPortLabels) {
            return labelManager.doResizeLabel(label, targetWidth);
        } else {
            return defaultResult();
        }
    }

    @Override
    protected Result doResizeNodeLabel(final ElkLabel label, final double targetWidth) {
        if (applyToNodeLabels) {
            return labelManager.doResizeLabel(label, targetWidth);
        } else {
            return defaultResult();
        }
    }

}
