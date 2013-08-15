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
package de.cau.cs.kieler.klighd.piccolo.internal;

/**
 * A pool of (internally used) constants.
 * 
 * @author chsch
 */
public final class Constants {

    /**
     * Standard hidden constructor.
     */
    private Constants() {
    }

    /**
     * An attribute identifier being used to enable the {@link edu.umd.cs.piccolo.PNode PNode} to
     * access the current {@link org.eclipse.swt.graphics.Device Device}. To that end a respective
     * attribute is defined on the diagram's {@link import edu.umd.cs.piccolo.PRoot root element},
     * which is accessible by each {@link edu.umd.cs.piccolo.PNode PNode}. This required in
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdImage KlighdImage}, for example.
     */
    public static final Object DEVICE = new Object();
    /**
     * An attribute identifier being used to enable the {@link edu.umd.cs.piccolo.PNode PNode} to
     * access the current main {@link edu.umd.cs.piccolo.PCamera PCamera}, i.e. that one that is
     * used to draw the diagram into the view part. To that end a respective attribute is defined on
     * the diagram's {@link import edu.umd.cs.piccolo.PRoot root element}, which is accessible by
     * each {@link edu.umd.cs.piccolo.PNode PNode}.
     */
    public static final Object MAIN_CAMERA = new Object();
    
    /**
     * The full qualified name of the class realizing an SVG export canvas.
     */
    public static final String KLIGHD_SVG_CANVAS = "de.cau.cs.kieler.klighd.piccolo.svg.KlighdSVGCanvas";
    
    /**
     * The name of the (static root) method in {@link #KLIGHD_SVG_CANVAS} to be called via
     * reflection in order to avoid cyclic dependencies.
     */
    public static final String KLIGHD_SVG_RENDER_METHOD = "staticRenderStream";

}
