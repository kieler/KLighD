/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 *
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd;

import java.util.List;

import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.IPropertyHolder;

import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * Configuration object for {@link LightDiagramServices} methods
 * {@link LightDiagramServices#layoutDiagram(LightDiagramLayoutConfig)} and
 * {@link LightDiagramServices#updateDiagram(LightDiagramLayoutConfig). These configuration holders
 * should be disposed of after use. For this reason there are no public getters available.
 * 
 * @author enbewe
 */
public class LightDiagramLayoutConfig {

    /**
     * The {@link IDiagramWorkbenchPart} containing the view model.
     */
    private IDiagramWorkbenchPart workbenchPart;

    /**
     * The {@link ViewContext} for this layout.
     */
    private ViewContext viewContext;

    /**
     * Flag whether the layout should be animated.
     */
    private Boolean animate;

    /**
     * The {@link ZoomStyle} to be employed.
     */
    private ZoomStyle zoomStyle;

    /**
     * The {@link KNode} to focus on, if {@link ZoomStyle#ZOOM_TO_FOCUS} is active.
     */
    private KNode focusNode;

    /**
     * List of {@link LayoutConfigurator LayoutConfigurators} to override default layout
     * configuration.
     */
    private List<LayoutConfigurator> options;

    /**
     * {@link IPropertyHolder} to influence the way the model update is handled.
     */
    private IPropertyHolder properties;

    /**
     * Model to be used to update the existing model in the {@link ViewContext}.
     */
    private Object model;

    /**
     * Creates a configuration for a {@link ViewContext}.
     * 
     * @param viewContext
     *            the {@link ViewContext} to be used in the configuration.
     */
    public LightDiagramLayoutConfig(final ViewContext viewContext) {
        this.viewContext = viewContext;
    }

    /**
     * Creates a configuration for a {@link IDiagramWorkbenchPart}.
     * 
     * @param workbenchPart
     *            the {@link IDiagramWorkbenchPart} to be used in the configuration.
     */
    public LightDiagramLayoutConfig(final IDiagramWorkbenchPart workbenchPart) {
        this.workbenchPart = workbenchPart;
    }

    ///////////////////////////////////////////////////////////
    // Modification of the configuration

    /**
     * Update the model in the configuration. Will be used when calling {@link #update()}.
     * 
     * @param model
     *            the new model to be used in the configuration.
     * @return the configuration
     */
    public LightDiagramLayoutConfig model(final Object model) {
        this.model = model;
        return this;
    }

    /**
     * Configure the animation of the layout.
     * 
     * @param animate
     *            flag to determine if the layout should be animated
     * @return the configuration
     */
    public LightDiagramLayoutConfig animate(final Boolean animate) {
        this.animate = animate;
        return this;
    }

    /**
     * Configuration for the {@link ZoomStyle} of the layout.
     * 
     * @param zoomStyle
     *            the {@link ZoomStyle} to be used.
     * @return the configuration
     */
    public LightDiagramLayoutConfig zoomStyle(final ZoomStyle zoomStyle) {
        this.zoomStyle = zoomStyle;
        return this;
    }

    /**
     * Sets the node to focus on.
     * 
     * @param focusNode
     *            the {@link KNode} to focus on if {@link ZoomStyle#ZOOM_TO_FOCUS} is set.
     * @return the configuration
     */
    public LightDiagramLayoutConfig focusNode(final KNode focusNode) {
        this.focusNode = focusNode;
        return this;
    }

    /**
     * List of {@link LayoutConfigurator LayoutConfigurators} that should override the default
     * configuration.
     * 
     * @param options
     *            the list of {@link LayoutConfigurator LayoutConfigurators}
     * @return the configuration
     */
    public LightDiagramLayoutConfig options(final List<LayoutConfigurator> options) {
        this.options = options;
        return this;
    }

    /**
     * {@link IPropertyHolder} to modify the update behaviour of this layout.
     * 
     * @param properties
     *            the {@link IPropertyHolder} containing the settings.
     * @return the configuration
     */
    public LightDiagramLayoutConfig properties(final IPropertyHolder properties) {
        this.properties = properties;
        return this;
    }

    ///////////////////////////////////////////////////////////
    // Reading the configuration in LightDiagramServices

    /**
     * Returns the new model to be used during {@link #update()}.
     * 
     * @return the new model.
     */
    Object model() {
        return this.model;
    }

    /**
     * Flag to determine the animation of the layout.
     * 
     * @return <code>true</code> if the layout should be animated.
     */
    Boolean animate() {
        return this.animate;
    }

    /**
     * Returns the configured {@link ZoomStyle} of the configuration.
     * 
     * @return the {@link ZoomStyle}.
     */
    ZoomStyle zoomStyle() {
        return this.zoomStyle;
    }

    /**
     * The node to focus on if {@link ZoomStyle#ZOOM_TO_FOCUS} is configured.
     * 
     * @return the focusNode
     */
    KNode focusNode() {
        return this.focusNode;
    }

    /**
     * List of {@link LayoutConfigurator LayoutConfigurators} to override the default config with.
     * 
     * @return the list of {@link LayoutConfigurator LayoutConfigurators}.
     */
    List<LayoutConfigurator> options() {
        return this.options;
    }

    /**
     * {@link IPropertyHolder} to configure the update strategy.
     * 
     * @return the {@link IPropertyHolder} containing properties like
     *         {@link KlighdSynthesisProperties#REQUESTED_UPDATE_STRATEGY}.
     */
    IPropertyHolder properties() {
        return this.properties;
    }

    /**
     * The {@link ViewContext} this layout is related to.
     * 
     * @return the {@link ViewContext} or <code>null</code> if the configuration is based on a
     *         {@link IDiagramWorkbenchPart}.
     */
    ViewContext viewContext() {
        return this.viewContext;
    }

    /**
     * The {@link IDiagramWorkbenchPart} this layout is related to.
     * 
     * @return the {@link IDiagramWorkbenchPart} or <code>null</code> if a {@link ViewContext} was
     *         used to create this configuration.
     */
    IDiagramWorkbenchPart workbenchPart() {
        return this.workbenchPart;
    }

    ///////////////////////////////////////////////////////////
    // Convenience aliases

    /**
     * Use this configuration and start a layout run. Convenient shorthand for
     * <code>LightDiagramServices.layoutDiagram(config)</code>.
     */
    public void layout() {
        LightDiagramServices.layoutDiagram(this);
    }

    /**
     * Use this configuration and update the layout. Convenient shorthand for
     * <code>LightDiagramServices.updateDiagram(config)</code>.
     * 
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     */
    public boolean update() {
        return LightDiagramServices.updateDiagram(this);
    }

}
