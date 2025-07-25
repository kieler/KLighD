/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.Spacing;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterRule;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.KColor;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.microlayout.Bounds;

/**
 * A collection of KLighD-specific {@link IProperty IProperties}
 * that may be used while interacting with KLighD, e.g. in custom diagram synthesis or action
 * implementations.
 *
 * @author chsch
 */
public final class KlighdProperties {

    /**
     * Standard hidden constructor.
     */
    private KlighdProperties() {
    }

    /**
     * Property to determine the minimal size of a node that has to hold for the node's whole
     * "life time".<br>
     * The {@link org.eclipse.elk.core.options.CoreOptions#NODE_SIZE_MINIMUM CoreOptions#NODE_SIZE_MINIMUM}
     * property is not sufficient as it has to be modified for hierarchical diagrams before
     * each automatic layout run.<br>
     * <br>
     * <b>Caution</b>: This property has been defined in
     * {@code de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions}, too, in
     * order to enable the independence of both bundles. This is possible as {@link IProperty
     * IProperties} are determined to be equal or unequal based on their id's.<br>
     * <br>
     * Besides, it is registered as a layout option in plugin.xml in order to get values of
     * persisted diagrams loaded properly, see
     * {@link de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil#loadDataElements(KNode, IProperty...)}.
     */
    public static final IProperty<KVector> MINIMAL_NODE_SIZE = new Property<KVector>(
            "de.cau.cs.kieler.klighd.minimalNodeSize", new KVector(
                    KlighdConstants.MINIMAL_NODE_BOUNDS.getWidth(),
                    KlighdConstants.MINIMAL_NODE_BOUNDS.getHeight()));

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.klighd.krendering.KRendering
     * KRendering} objects of {@link KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.klighd.krendering.KRendering
     * KRendering} is to be shown in the collapsed state of the node.
     */
    public static final IProperty<Boolean> COLLAPSED_RENDERING = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.collapsedRendering", false);

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.klighd.krendering.KRendering
     * KRendering} objects of {@link KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.klighd.krendering.KRendering
     * KRendering} is to be shown in the expanded state of the node.
     */
    public static final IProperty<Boolean> EXPANDED_RENDERING = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.expandedRendering", false);

    /**
     * Property indicating the auto expansion of a node if the value is true.<br>
     * This property is currently to be attached to the node's shape layout data during the view
     * synthesis process. If it is absent the node gets expanded, anyway.
     */
    public static final IProperty<Boolean> EXPAND = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.expand", true);

    /**
     * Property indicating the auto incorporation of a
     * {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} (kge) into the corresponding
     * diagram if the value is true.<br>
     * This property is currently to be attached to the kge's
     * {@link de.cau.cs.kieler.klighd.kgraph.KLayoutData KLayoutData} data during the view synthesis process.
     * If it is absent the kge is incorporated, anyway.
     */
    public static final IProperty<Boolean> SHOW = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.show", true);

    /**
     * Property indicating the initialization of the diagram clip to the associated node (property
     * value).<br>
     * This property is to be attached to the {@link de.cau.cs.kieler.klighd.ViewContext
     * ViewContext} being used during the view synthesis process.
     */
    public static final IProperty<KNode> CLIP = new Property<KNode>(
            "de.cau.cs.kieler.klighd.clip");


    /**
     * Property determining the selectability of a certain
     * {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement},
     * {@link de.cau.cs.kieler.klighd.krendering.KText KText} or
     * decorator {@link de.cau.cs.kieler.klighd.krendering.KRendering}. If it is set to <code>true</code> that
     * particular diagram element cannot be selected, and the (geometrically) underlying element will
     * be selected if possible.<br>
     * If a {@link KNode} is not selectable the selection attempt is stopped!
     */
    public static final IProperty<Boolean> NOT_SELECTABLE = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.suppressSelectability", false);

    /**
     * Convenience method determining the selectability of the given {@link KGraphElement}.
     *
     * @param kge
     *            the {@link KGraphElement} element to check for selectability.
     *
     * @return the selectability of the given {@link KGraphElement}
     */
    public static boolean isSelectable(final KGraphElement kge) {
        return !kge.getProperty(KlighdProperties.NOT_SELECTABLE);
    }

    /**
     * Convenience method determining the selectability of the given {@link KText}.
     *
     * @param kText
     *            the {@link KText} element to check for selectability.
     *
     * @return the selectability of the given {@link KText} rendering
     */
    public static boolean isSelectable(final KText kText) {
        return kText == null ? false : !kText.getProperty(KlighdProperties.NOT_SELECTABLE);
    }

    /**
     * Convenience method determining the selectability of the given view model element, either a
     * {@link KGraphElement} or a {@link KText}.
     *
     * @param viewElement
     *            the view model element to check for selectability.
     *
     * @return the selectability of the given view model element, or <code>null</code> is no valid
     *         view element is provided
     */
    public static boolean isSelectable(final EObject viewElement) {
        return (viewElement instanceof KGraphElement && isSelectable((KGraphElement) viewElement))
                || (viewElement instanceof KText && isSelectable((KText) viewElement));
    }


    /**
     * Property determining the visibility of a certain
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} in the outline diagram view.
     * If it is set to <code>true</code> that particular rendering element is not shown in the
     * outline.
     */
    public static final IProperty<Boolean> OUTLINE_INVISIBLE = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.outlineInvisible", false);

    /**
     * Property determining the visibility of a certain
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} in exported diagram diagram
     * images. If it is set to <code>true</code> that particular rendering element is not shown in
     * exported images.
     */
    public static final IProperty<Boolean> EXPORTED_IMAGE_INVISIBLE = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.exportedImageInvisible", false);

    /**
     * Property determining the visibility of a certain
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} in diagram printouts. If it is
     * set to <code>true</code> that particular rendering element is not shown in diagram printouts.
     */
    public static final IProperty<Boolean> PRINTOUT_INVISIBLE = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.printoutInvisible", false);

    /**
     * Property determining the upper visibility bound of a certain
     * {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} wrt. the diagram scale/zoom.
     * If the diagram is shown in equal or higher scale (&gt;=) than the determined value the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_SCALE_UPPER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityScaleUpperBound", -1);

    /**
     * Property determining the lower visibility bound of a certain
     * {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} wrt. the diagram scale/zoom.
     * If the diagram is shown in strictly lower scale (&lt;) than the determined value the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_SCALE_LOWER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityScaleLowerBound", 0);

    /**
     * Property determining the upper visibility bound in terms of an absolute height value of a
     * certain {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}. If the diagram is shown in a
     * zoom scale leading to a height equal or higher (&gt;=) than the determined value, the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_HEIGHT_UPPER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityHeightUpperBound", -1);

    /**
     * Property determining the lower visibility bound in terms of an absolute height value of a
     * certain {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}. If the diagram is shown in a
     * zoom scale leading to a strictly lower height (&lt;) than the determined value, the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_HEIGHT_LOWER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityHeightLowerBound", 0);

    /**
     * Property determining the upper visibility bound in terms of an absolute width value of a
     * certain {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}. If the diagram is shown in a
     * zoom scale leading to a width equal or higher (&gt;=) than the determined value, the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_WIDTH_UPPER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityWidthUpperBound", -1);

    /**
     * Property determining the lower visibility bound in terms of an absolute width value of a
     * certain {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}. If the diagram is shown in a
     * zoom scale leading to a strictly lower width (&lt;) than the determined value, the corresponding
     * diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_WIDTH_LOWER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityWidthLowerBound", 0);

    /**
     * Property determining whether the diagram zoom scale-based visibility configurations of the
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} it is defined on shall apply
     * to its children, as well. Configuring this property on {@link KGraphElement KGraphElements}
     * (' {@link de.cau.cs.kieler.klighd.kgraph.KLayoutData KLayoutData}) will have no effect,
     * {@link KGraphElement KGraphElements'} children are automatically skipped by default.
     */
    public static final IProperty<Boolean> VISIBILITY_PROPAGATE_TO_CHILDREN = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.visibilityPropagateToChildren", false);

    /**
     * Property that is used to keep original port side data during the whole life cycle of a port,
     * the values must not be changed once it is set. This information may be used in order to
     * examine whether the automatic layout moved the port to another side that might require a
     * modification of the port's rendering.<br>
     * <br>
     * This property must be set in implementations of
     * {@link de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis AbstractDiagramSynthesis}
     * if it is needed, it is not set by KLighD automatically.
     */
    public static final IProperty<PortSide> ORIGINAL_PORT_SIDE = new Property<PortSide>(
            "klighd.original.port.side");

    /**
     * Property that is used to provide the port side data determined by the layouter during the
     * most recent layout computation. This value must not be manipulated by others than the layout
     * manager.<br>
     * It is automatically set.
     */
    public static final IProperty<PortSide> LAYOUT_PORT_SIDE = new Property<PortSide>(
            "klighd.layout.port.side");

    /**
     * The property holds a tooltip that is displayed upon a mouse hover of the respective element.
     */
    public static final IProperty<String> TOOLTIP = new Property<String>("klighd.tooltip", null);

    /**
     * A map of string/string pairs that will be added to the generated rendering. Note that not all
     * rendering mechanisms support this feature.
     * An example would be to add 'id' elements to svg elements.
     *
     * @see KlighdConstants#SEMANTIC_DATA_ID
     * @see KlighdConstants#SEMANTIC_DATA_CLASS
     * @see KlighdConstants#SEMANTIC_DATA_RAW
     */
    public static final IProperty<KlighdSemanticDiagramData> SEMANTIC_DATA =
            new Property<KlighdSemanticDiagramData>("klighd.semanticData", null);

    /**
     * Property for globally determining whether edges should be drawn before nodes, leading to
     * the effect of overpainting edges by nodes with background color.
     */
    public static final IProperty<Boolean> EDGES_FIRST = new Property<Boolean>(
            "klighd.edgesFirst", false);

    /**
     * Property for globally determining whether figure descriptions of
     * {@link de.cau.cs.kieler.klighd.kgraph.KLabel KLabel}s may contain multiple KTexts. Enabling
     * this has implications on the applicability of layout algorithms performing the computation of
     * the label size on their own, as well as the on the applicability of
     * {@link org.eclipse.elk.core.labels.ILabelManager ILabelManager}s.
     */
    public static final IProperty<Boolean> MULTIPLE_KTEXTS_PER_KLABEL = new Property<Boolean>(
            "klighd.multipleKTextsPerKLabel", false);

    /**
     * Determines whether the ports and port labels of clipped nodes should be shown or not.
     * By default the ports are shown, preventing issues with links to external ports.
     */
    public static final IProperty<Boolean> SHOW_CLIPPED_PORTS =
            new Property<Boolean>("klighd.showClippedNodesPorts", true);

    /**
     * Determines whether the labels of clipped nodes should be shown or not.
     * By default the labels are shown.
     */
    public static final IProperty<Boolean> SHOW_CLIPPED_LABELS =
            new Property<Boolean>("klighd.showClippedNodesLabels", true);

    /**
     * Property denoting additional spacing to the zoom to fit content bounds of a (nested) diagram.
     * The particular values are added (subtracted) to the bounds if the particular bound is
     * determined by a child node or one of its connecting edges. The spacings are not applied to
     * bounds that are determined by the clip node's ports or labels, which expected to be located
     * directly at the viewport bounds.
     */
    public static final IProperty<Spacing> ZOOM_TO_FIT_CONTENT_SPACING =
            new Property<Spacing>("klighd.zoomToFitContentSpacing", null);

    /**
     * Property to define the color of the canvas.
     */
    public static final IProperty<Color> CANVAS_COLOR =
            new Property<Color>("klighd.canvas.color", Color.WHITE);
    
    /**
     * Property determining the precalculated bounds of a {@link KText}. If this property is set the
     * text size estimation will not call any other size estimation and use the bounds given by this
     * property instead.
     */
    public static final IProperty<Bounds> CALCULATED_TEXT_BOUNDS =
            new Property<Bounds>("klighd.calculated.text.bounds", null);
    
    /**
     * Property determining the pre-calculated widths of each line in a {@link KText}.
     */
    public static final IProperty<float[]> CALCULATED_TEXT_LINE_WIDTHS = 
        new Property<float[]>("klighd.calculated.text.line.widths", null);
    
    /**
     * Property determining the pre-calculated heights of each line in a {@link KText}.
     */
    public static final IProperty<float[]> CALCULATED_TEXT_LINE_HEIGHTS = 
        new Property<float[]>("klighd.calculated.text.line.heights", null);
    
    /**
     * Determines whether a sub-KRendering can be interpreted as a title for a node.
     * By default no rendering is seen as a title.
     * 
     * @deprecated Since version 3.1.1. Replaced by semantic tag {@link KlighdTags.IS_NODE_TITLE}
     */
    public static final IProperty<Boolean> IS_NODE_TITLE =
        new Property<Boolean>("klighd.isNodeTitle", false);
    
    /**
     * Property determining the custom color theme to be used by syntheses.
     * Null means no preferences could be detected and the diagram background will most likely be white.
     */
    public static final IProperty<ColorPreferences> COLOR_PREFERENCES =
            new Property<ColorPreferences>("klighd.colorPreferences", null);
    
    /**
     * Property determining whether this node should be rendered as a proxy.
     */
    public static final IProperty<Boolean> PROXY_VIEW_RENDER_NODE_AS_PROXY =
            new Property<Boolean>("de.cau.cs.kieler.klighd.proxyView.renderNodeAsProxy", true);
    
    /**
     * Property determining how a proxy of this node should be rendered.
     */
    public static final IProperty<List<KGraphData>> PROXY_VIEW_PROXY_RENDERING =
            new Property<List<KGraphData>>("de.cau.cs.kieler.klighd.proxyView.proxyRendering", null);
    
    /**
     * Property determining depth of going into hierarchical off-screen nodes.
     * Only relevant for the root. <br>
     * Choose {@code 0} for default, showing only the outermost node as a proxy. <br>
     * Choose a value {@code x>0} to show proxies up to x layers deep inside a hierarchical node. <br>
     * Choose a value {@code x<0} to always show proxies for all layers.
     */
    public static final IProperty<Integer> PROXY_VIEW_HIERARCHICAL_OFF_SCREEN_DEPTH =
            new Property<Integer>("de.cau.cs.kieler.klighd.proxyView.hierarchicalOffScreenDepth", 0);
    
    /**
     * Property determining an element's semantic filter tags.
     */
    public static final IProperty<List<SemanticFilterTag>> SEMANTIC_FILTER_TAGS = 
            new Property<List<SemanticFilterTag>>("de.cau.cs.kieler.klighd.semanticFilter.tags",
                    // ArrayList is cloneable, no problem here
                    new ArrayList<>());

    /**
     * Property determining an element's semantic filter rules.
     * Only relevant for the root.
     */
    public static final IProperty<List<SemanticFilterRule>> SEMANTIC_FILTER_RULES = 
            new Property<List<SemanticFilterRule>>("de.cau.cs.kieler.klighd.semanticFilter.rules",
                    new ArrayList<>());

    /**
     * Automatically computes the padding required to fit the content of a node within the bounds of a 
     * rounded rectangle. The x and y corner radii are specified as x and y of a KVector. If this property
     * is set, the ElkPadding set on the graph will be overridden internally. This property should only be 
     * used if no padding is manually set on the graph element.
     */
    public static final IProperty<KVector> ROUNDED_RECTANGLE_AUTOPADDING = 
            new Property<KVector>("klighd.roundedRectangle.autopadding", null);
}
