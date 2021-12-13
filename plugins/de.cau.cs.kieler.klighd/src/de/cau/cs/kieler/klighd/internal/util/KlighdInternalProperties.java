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
package de.cau.cs.kieler.klighd.internal.util;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;

/**
 * A collection of internal KLighD-specific {@link de.cau.cs.kieler.klighd.properties.IProperty
 * IProperties}.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public final class KlighdInternalProperties {

    /**
     * Standard hidden constructor.
     */
    private KlighdInternalProperties() {
    }

    /**
     * Property for preserving the domain model element being represented by a diagram element or
     * diagram figure element. Is to be attached to {@link de.cau.cs.kieler.klighd.kgraph.KGraphData
     * KGraphData}, for {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElements} it is to
     * be attached to their layout data.
     */
    public static final IProperty<Object> MODEL_ELEMEMT = new Property<Object>("klighd.modelElement");

    /**
     * Property indicating that the node has been populated. A node is populated, if and only if the
     * node's child nodes are visible in the diagram.<br>
     * <br>
     * <b>It is intended for KLighD internal use only!</b><br>
     * The property declaration has been moved here from klighd.piccolo's
     * AbstractRenderingController.
     */
    public static final IProperty<Boolean> POPULATED = new Property<Boolean>("klighd.populated",
            false);
    
    /**
     * A property indicating whether a {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement
     * KGraphElement} is currently active. This is the case if and only if it is represented in the
     * diagram.<br>
     * <br>
     * <b>It is intended for KLighD internal use only!</b><br>
     * The property declaration has been moved here from klighd.piccolo's
     * AbstractRenderingController.
     */
    public static final IProperty<Boolean> ACTIVE = new Property<Boolean>("klighd.active", false);
    
    /**
     * A property indicating the selection of a {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement
     * KGraphElement} or a {@link de.cau.cs.kieler.klighd.krendering.KText KText}. It is used for
     * communicating the 'selected' state of such an element from the
     * {@link de.cau.cs.kieler.klighd.viewers.ContextViewer ContextViewer}, which sets this
     * property, to concrete viewers, usually the PiccoloViewer and, more specifically, the
     * (Abstract)KGERenderingControllers. Those viewers will then have to update the respective
     * diagram figures and incorporate the {@link de.cau.cs.kieler.klighd.krendering.KStyle KStyles}
     * with flag 'selection' set.
     */
    public static final IProperty<Boolean> SELECTED = new Property<Boolean>("klighd.selected", false);

    /** the viewer visualizing the graph. */
    public static final IProperty<IViewer> VIEWER = new Property<IViewer>(
            "klighd.layout.viewer");
    
    /** the layout recorder responsible for diagram animation. */
    public static final IProperty<ILayoutRecorder> RECORDER = new Property<ILayoutRecorder>(
            "klighd.layout.recorder");
    
    /** the zoom style to be applied during upcoming diagram layout. */
    public static final IProperty<ZoomStyle> NEXT_ZOOM_STYLE = new Property<ZoomStyle>(
            "klighd.zoom.nextZoomStyle");
    
    /**
     * The {@link KGraphElement} to be focus during upcoming diagram layout if {@link #NEXT_ZOOM_STYLE} is
     * {@link ZoomStyle#ZOOM_TO_FOCUS} or {@link ZoomStyle#ZOOM_TO_STAY_SELECTED}.
     */
    public static final IProperty<KGraphElement> NEXT_FOCUS_ELEMENT = new Property<KGraphElement>(
            "klighd.zoom.nextFocusElement");
    
    /**
     * The position the selected element had in the previous layout that is included in the new layout 
     * if the {@link ZoomStyle} is {@link ZoomStyle#ZOOM_TO_STAY_SELECTED}.
     */
    public static final IProperty<KVector> PREVIOUS_POSITION = new Property<KVector>(
            "klighd.zoom.previousPosition");
    
    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes for properly performing regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_EXPECTED_HEIGHT = new Property<Float>(
            "klighd.testing.expected.height");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes for properly performing regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_EXPECTED_WIDTH = new Property<Float>(
            "klighd.testing.expected.width");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
     * KShapeLayout} of a view model's nodes in order to ignore those nodes.<br>
     * <br>
     * The id is also hard-coded in KGraphJavaValidator!
     */
    public static final IProperty<Boolean> KLIGHD_TESTING_IGNORE = new Property<Boolean>(
            "klighd.testing.ignore", false);

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.klighd.krendering.KText KTexts} of a
     * view model for properly performing various regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_HEIGHT = new Property<Float>(
            "klighd.testing.height");

    /**
     * Property to be attached to the {@link de.cau.cs.kieler.klighd.krendering.KText KTexts} of a
     * view model for properly performing various regression tests.
     */
    public static final IProperty<Float> KLIGHD_TESTING_WIDTH = new Property<Float>(
            "klighd.testing.width");
    
    /**
     * Predicate definition that simplifies the testing for {@link #KLIGHD_TESTING_HEIGHT}
     * in {@link de.cau.cs.kieler.klighd.krendering.KText KText} data.
     */
    // SUPPRESS CHECKSTYLE NEXT LineLength
    public static final Predicate<PersistentEntry> PRED_TESTING_HEIGHT
        = new Predicate<PersistentEntry>() {
        
        public boolean apply(final PersistentEntry pe) {
            return pe.getKey().equals(KLIGHD_TESTING_HEIGHT.getId());
        }
    };
    
    /**
     * Predicate definition that simplifies the testing for {@link #KLIGHD_TESTING_WIDTH}
     * in {@link de.cau.cs.kieler.klighd.krendering.KText KText} data.
     */
    // SUPPRESS CHECKSTYLE NEXT LineLength
    public static final Predicate<PersistentEntry> PRED_TESTING_WIDTH
        = new Predicate<PersistentEntry>() {
        
        public boolean apply(final PersistentEntry pe) {
            return pe.getKey().equals(KLIGHD_TESTING_WIDTH.getId());
        }
    };
}
