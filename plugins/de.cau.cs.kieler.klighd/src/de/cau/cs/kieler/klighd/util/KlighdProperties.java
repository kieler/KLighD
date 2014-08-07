/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.RunnableWithResult;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klighd.KlighdConstants;

/**
 * A collection of KLighD-specific {@link de.cau.cs.kieler.core.properties.IProperty IProperties}
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
     * The {@link de.cau.cs.kieler.kiml.options.LayoutOptions#MIN_WIDTH LayoutOptions#MIN_WIDTH}/
     * {@link de.cau.cs.kieler.kiml.options.LayoutOptions#MIN_HEIGHT LayoutOptions#MIN_HEIGHT}
     * properties are not sufficient as they have to be modified for hierarchical diagrams before
     * each automatic layout run.<br>
     * <br>
     * <b>Caution</b>: This property has been defined in
     * {@link de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions KNodeExtensions}, too, in
     * order to enable the independence of both bundles. This is possible as {@link IProperty
     * IProperties} are determined to be equal or unequal based on their id's.<br>
     * <br>
     * Besides, it is registered as a layout option in plugin.xml in order to get values of
     * persisted diagrams loaded properly, see
     * {@link de.cau.cs.kieler.kiml.util.KimlUtil#loadDataElements(de.cau.cs.kieler.core.kgraph.KNode)
     * KimlUtil#loadDataElements(de.cau.cs.kieler.core.kgraph.KNode)}.
     */
    public static final IProperty<KVector> MINIMAL_NODE_SIZE = new Property<KVector>(
            "de.cau.cs.kieler.klighd.minimalNodeSize", new KVector(
                    KlighdConstants.MINIMAL_NODE_BOUNDS.getWidth(),
                    KlighdConstants.MINIMAL_NODE_BOUNDS.getHeight()));

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} objects of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} is to be shown in the collapsed state of the node.
     */
    public static final IProperty<Boolean> COLLAPSED_RENDERING = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.collapsedRendering", false);

    /**
     * Property to be attached to root {@link de.cau.cs.kieler.core.krendering.KRendering
     * KRendering} objects of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} during the view
     * synthesis process indicating that the {@link de.cau.cs.kieler.core.krendering.KRendering
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
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} (kge) into the corresponding
     * diagram if the value is true.<br>
     * This property is currently to be attached to the kge's
     * {@link de.cau.cs.kieler.kiml.klayoutdata.KLayoutData } data during the view synthesis process.
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
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.core.krendering.KText KText}. If it is set to <code>true</code> that
     * particular diagram element cannot be selected, and the (geometrically) underlying element will
     * be selected if possible.<br>
     * If a {@link KNode} is not selectable the selection attempt is stopped!
     */
    public static final IProperty<Boolean> NOT_SELECTABLE = new Property<Boolean>(
            "de.cau.cs.kieler.klighd.suppressSelectability", false);

    /**
     * Property determining the upper visibility bound of a certain
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} wrt. the diagram scale/zoom.
     * If the diagram is shown is in strictly higher scale (>) than the determined value the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_SCALE_UPPER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityScaleUpperBound", -1);

    /**
     * Property determining the lower visibility bound of a certain
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} or
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} wrt. the diagram scale/zoom.
     * If the diagram is shown is in strictly lower scale (<) than the determined value the
     * corresponding diagram or figure element is not visible anymore.
     */
    public static final IProperty<Number> VISIBILITY_SCALE_LOWER_BOUND = new Property<Number>(
            "de.cau.cs.kieler.klighd.visibilityScaleLowerBound", 0);

//    /**
//     * Property providing a URI to semantic elements to be depicted but that are to be loaded lazily.
//     * This is property is currently to be attached to the nodes shape layout data during the view
//     * synthesis process. 
//     */
//    public static final IProperty<URI> CHILD_URI = new Property<URI>("klighd.childURI");
//
//    /**
//     * Property of {@link de.cau.cs.kieler.core.kgraph.KNode KNodes} indicating that the containing
//     * node is not pickable in a KLighD diagram. Can be used to mask auxiliary encapsulating nodes.
//     * 
//     * @deprecated use {@link #NOT_SELECTABLE}
//     */
//    public static final IProperty<Boolean> KLIGHD_SELECTION_UNPICKABLE = new Property<Boolean>(
//            "klighd.selection.unpickable", false);

    /**
     * A pre-defined property to be used for handing over an {@link RunnableWithResult} to the
     * KLighD view allowing to update the represented model, e.g. from UI contributions of the
     * KLighD view.
     */
    public static final IProperty<RunnableWithResult<?>> MODEL_ACCESS =
            new Property<RunnableWithResult<?>>("klighd.modelAccess");

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
     */
    public static final IProperty<KlighdSemanticDiagramData> SEMANTIC_DATA =
            new Property<KlighdSemanticDiagramData>("klighd.semanticData", null);

    /**
     * Property for globally determining whether edges should be drawn before nodes, leading to
     * the effect of overpainting edges by nodes with background color.
     */
    public static final IProperty<Boolean> EDGES_FIRST = new Property<Boolean>(
            "klighd.edgesFirst", false);
}
