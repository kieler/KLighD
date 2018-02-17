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
package de.cau.cs.kieler.klighd.labels.decoration;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.KAreaPlacementData;
import de.cau.cs.kieler.klighd.krendering.KBottomPosition;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KInvisibility;
import de.cau.cs.kieler.klighd.krendering.KLeftPosition;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.KRightPosition;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.KTopPosition;

/**
 * Central configuration class for center edge label decorations. Decorations can be used to
 * configure specific ways of rendering labels, to add directional decorators that point towards
 * an edge's head, and to configure inline label placement (which usually requires special
 * rendering).
 * 
 * <p>
 * To use the label decorations framework, create a new instance of this class using
 * {@link #create()}, configure it using the {@code with...} methods and
 * {@link #addDecoratorRenderingProvider(IDecoratorRenderingProvider)}, and either apply it to
 * {@link #applyTo(KLabel)} for a single label or {@link #applyToAll(KNode, boolean)} for all labels
 * in a graph.
 * 
 * <p>
 * The configuration consists of several parts, each of which configured with sensible defaults.
 * </p>
 * 
 * 
 * <h3>Layout Mode</h3>
 * 
 * <p>
 * This setting decides whether the resulting label renderings should only work with horizontal or
 * vertical layout directions or with both. This may influence the amount of padding between a
 * label's border and its text inserted by decorators (see below). It may also influence the amount
 * of renderings added to the label: if vertical layout directions are never used, no renderings
 * need to be added that are only used for such directions.
 * </p>
 * 
 * <p>
 * By default, everything gets set up for arbitrary layout directions.
 * </p>
 * 
 * 
 * <h3>Decorator Rendering Providers</h3>
 * 
 * <p>
 * Inline labels will usually require some sort of special rendering since simply displaying its
 * text would result in the label's edge crossing the text out. The least one would want is to add
 * some kind of a background that hides the edge behind the label, but an arbitrary number of
 * decorators can be added to each label. Decorations can be shown or hidden depending on whether
 * the label ends up being placed on a horizontal or on a vertical edge segment, as controlled by
 * the {@link EdgeLabelStyleModifier}.
 * </p>
 * 
 * <p>
 * By default, labels are decorated with a slightly translucent white background.
 * </p>
 * 
 * 
 * <h3>Text Rendering Provider</h3>
 * 
 * <p>
 * The label text must be rendered using a {@link KText}, which is provided by a text rendering
 * provider.
 * </p>
 * 
 * <p>
 * By default, an empty {@link KText} is used to render a label.
 * </p>
 * 
 * @author cds
 * @see IDecoratorRenderingProvider
 * @see ITextRenderingProvider
 * @see EdgeLabelStyleModifier
 */
public final class LabelDecorationConfigurator {
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /** The layout mode to use. */
    private LayoutMode layoutMode = LayoutMode.BOTH;
    /** Rendering providers that contribute decorations. */
    private List<IDecoratorRenderingProvider> decoratorRenderingProviders = Lists.newArrayList();
    /** Default decoration if there's nothing else available. */
    private IDecoratorRenderingProvider defaultDecorator = RectangleDecorator.create();
    /** Rendering provider for the actual label text. */
    private ITextRenderingProvider labelTextRenderingProvider = this::createDefaultTextRenderer;
    /** Whether labels should be configured to be inline. */
    private boolean inlineLabels = false;
    
    // Just rendering things...
    private KRenderingFactory renderingFactory = KRenderingFactory.eINSTANCE;

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Creation and Configuration
    
    /**
     * Use static creation methods.
     */
    private LabelDecorationConfigurator() {
    }
    
    /**
     * Creates a new configurator.
     * 
     * @return the new configurator.
     */
    public static LabelDecorationConfigurator create() {
        return new LabelDecorationConfigurator();
    }
    
    /**
     * Configures the configurator to assume the given layout mode. The layout mode defaults to
     * {@link LayoutMode#BOTH}.
     * 
     * @param mode
     *            the mode to assume.
     * @return this configurator for method chaining.
     */
    public LabelDecorationConfigurator withLayoutMode(final LayoutMode mode) {
        Objects.requireNonNull(mode, "mode cannot be null");
        
        layoutMode = mode;
        return this;
    }
    
    /**
     * Adds a decorator rendering provider to provide decorations for the label. The providers are
     * called in the order in which they are added to the configurator. They should thus be added
     * according to the desired Z order of the renderings. If no decorator is added, we default to
     * a slightly translucent white background.
     * 
     * @param provider
     *            the provider to add.
     * @return this configurator for method chaining.
     */
    public LabelDecorationConfigurator addDecoratorRenderingProvider(
            final IDecoratorRenderingProvider provider) {
        
        Objects.requireNonNull(provider, "provider cannot be null");
        
        decoratorRenderingProviders.add(provider);
        return this;
    }
    
    /**
     * Configures the configurator to use the given rendering provider to provide the actual label
     * text rendering. The default simply creates an empty, unconfigured {@link KText}.
     * 
     * @param provider
     *            the provider to use.
     * @return this configurator for method chaining.
     */
    public LabelDecorationConfigurator withLabelTextRenderingProvider(
            final ITextRenderingProvider provider) {
        
        Objects.requireNonNull(provider, "provider cannot be null");
        
        labelTextRenderingProvider = provider;
        return this;
    }
    
    /**
     * Whether to configure the configurator to configure labels to be configured for inline label
     * placement.
     * 
     * @param inline
     *            {@code true} if labels should be configured to be inline labels.
     * @return this configurator for method chaining.
     */
    public LabelDecorationConfigurator withInlineLabels(final boolean inline) {
        inlineLabels = inline;
        return this;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Application
    
    /**
     * Applies inline label configurations to all labels in the given graph and optionally its
     * subgraphs.
     * 
     * @param graph
     *            the graph.
     * @param subgraphs
     *            {@code true} if inline label configurations should also be applied to labels in
     *            subgraphs of the given graph.
     */
    public void applyToAll(final KNode graph, final boolean subgraphs) {
        for (KNode child : graph.getChildren()) {
            for (KEdge outgoingEdge : child.getOutgoingEdges()) {
                outgoingEdge.getLabels().stream().forEach(l -> applyTo(l));
            }
            
            if (subgraphs && !child.getChildren().isEmpty()) {
                applyToAll(child, true);
            }
        }
    }
    
    /**
     * Turns the given label into an inline edge label, provided it is a center edge label. For
     * inline edge labels, this method will replace any existing rendering information.
     * 
     * @param label
     *            the label to turn into an inline edge label.
     */
    public void applyTo(final KLabel label) {
        if (!isAffectedLabel(label)) {
            return;
        }
        
        removeExistingRenderings(label);
        setupLayoutProperties(label);
        
        KContainerRendering container = createAndAddContainerRendering(label);
        
        ElkPadding padding = createAndAddDecoratorRenderings(label, container);
        createAndAddTextRendering(label, container, padding);
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////
    // Application Utility Methods
    
    /**
     * Checks if the given label is a center edge label and thus should be turned into an inline
     * label.
     */
    private boolean isAffectedLabel(final KLabel label) {
        if (!(label.eContainer() instanceof KEdge)) {
            return false;
        }

        EdgeLabelPlacement placement = label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
        if (placement != EdgeLabelPlacement.CENTER && placement != EdgeLabelPlacement.UNDEFINED) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Clears any existing renderings from the given label.
     */
    private void removeExistingRenderings(final KLabel label) {
        ListIterator<KGraphData> dataIterator = label.getData().listIterator();
        while (dataIterator.hasNext()) {
            if (dataIterator.next() instanceof KRendering) {
                dataIterator.remove();
            }
        }
    }
    
    /**
     * Sets any properties necessary to define the given label as an inline edge label.
     */
    private void setupLayoutProperties(final KLabel label) {
        label.setProperty(CoreOptions.EDGE_LABELS_INLINE, inlineLabels);
    }
    
    /**
     * Creates a container rendering and adds it to the given label. The container will be
     * completely invisible.
     */
    private KContainerRendering createAndAddContainerRendering(final KLabel label) {
        KContainerRendering container = renderingFactory.createKRectangle();
        label.getData().add(container);
        
        // Make the rectangle's foreground and background invisible
        KInvisibility invisibility = renderingFactory.createKInvisibility();
        container.getStyles().add(invisibility);
        invisibility.setInvisible(true);
        
        return container;
    }
    
    /**
     * Asks all the decorator rendering providers to provide their renderings and accumulates the
     * padding necessary for the text rendering later.
     */
    private ElkPadding createAndAddDecoratorRenderings(final KLabel label,
            final KContainerRendering container) {
        
        boolean needDefaultDecorator = decoratorRenderingProviders.isEmpty();
        if (needDefaultDecorator) {
            decoratorRenderingProviders.add(defaultDecorator);
        }
        
        // Iterate over the registered rendering providers and update the padding
        ElkPadding padding = new ElkPadding();
        for (IDecoratorRenderingProvider decoratorProvider : decoratorRenderingProviders) {
            ElkPadding newPadding = decoratorProvider.createDecoratorRendering(
                    container, label, layoutMode);
            
            padding.left = Math.max(padding.left, newPadding.left);
            padding.right = Math.max(padding.right, newPadding.right);
            padding.top = Math.max(padding.top, newPadding.top);
            padding.bottom = Math.max(padding.bottom, newPadding.bottom);
        }
        
        if (needDefaultDecorator) {
            // Remove the default decorator again
            decoratorRenderingProviders.clear();
        }
        
        return padding;
    }
    
    /**
     * Creates and sets up the text rendering for the label. Asks the rendering provider to do so,
     * and setups up the layout information in accordance with the provided paddings.
     */
    private void createAndAddTextRendering(final KLabel label, final KContainerRendering container,
            final ElkPadding padding) {
        
        // Create the rendering (has already been added to the container)
        KRendering textRendering = labelTextRenderingProvider.createTextRendering(container, label);
        
        // Make sure the required padding is adhered to
        KLeftPosition left = renderingFactory.createKLeftPosition()
                .setPosition((float) padding.left, 0);
        KRightPosition right = renderingFactory.createKRightPosition()
                .setPosition((float) padding.right, 0);
        KTopPosition top = renderingFactory.createKTopPosition()
                .setPosition((float) padding.top, 0);
        KBottomPosition bottom = renderingFactory.createKBottomPosition()
                .setPosition((float) padding.bottom, 0);
        
        KPosition topLeft = renderingFactory.createKPosition().setPositions(left, top);
        KPosition bottomRight = renderingFactory.createKPosition().setPositions(right, bottom);
        
        KAreaPlacementData placementData = renderingFactory.createKAreaPlacementData();
        placementData.setTopLeft(topLeft);
        placementData.setBottomRight(bottomRight);
        
        textRendering.setPlacementData(placementData);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Rendering Providers
    
    /**
     * Default text rendering provider.
     */
    private KRendering createDefaultTextRenderer(final KContainerRendering container,
            final KLabel label) {
        
        KText ktext = renderingFactory.createKText();
        container.getChildren().add(ktext);
        return ktext;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Inner Classes
    
    /**
     * Enumeration of possible layout modes. Limiting layouts to only one mode may allow decorators
     * to optimize the amount of insets they require for the actual label text.
     */
    public static enum LayoutMode {
        /** The layouts will only ever be horizontal. */
        HORIZONTAL,
        /** The layouts will only ever be vertical. */
        VERTICAL,
        /** The layouts may be both horizontal and vertical. */
        BOTH;
    }
    
}
