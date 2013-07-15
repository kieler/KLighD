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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.RunnableWithResult;
import de.cau.cs.kieler.kiml.options.PortSide;

/**
 * A collection of KLighD-specific {@link de.cau.cs.kieler.core.properties.IProperty IProperties}.
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
     * {@link de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
     * AbstractDiagramSynthesis} if it is needed, it is not set by KLighD automatically.
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
}
