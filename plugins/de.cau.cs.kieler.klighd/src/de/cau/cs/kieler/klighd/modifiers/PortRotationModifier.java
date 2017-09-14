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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.modifiers;

import org.eclipse.elk.core.options.PortSide;

import de.cau.cs.kieler.klighd.IStyleModifier;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.krendering.KRotation;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * This custom {@link IStyleModifier} is employed to correct the rotation of port figures that are
 * moved by the layouter, e.g. from the north to the west side of a node. Thus it is supposed to be
 * only associated to {@link de.cau.cs.kieler.klighd.krendering.KRendering KRenderings} describing the
 * figures of {@link KPorts}<br>
 * <br>
 * It updates the {@link KRotation#getRotation() rotation} value of the {@link KRotation} style this
 * class is associated with (by entering {@link #ID} in the styles
 * {@link de.cau.cs.kieler.klighd.krendering.KStyle#getModifierId() modifier} field) after each update
 * of the diagram layout.<br>
 * <br>
 * This class is also registered in the plugin.xml.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public class PortRotationModifier implements IStyleModifier {

    /** The Id of this modifier's registration in the plugin.xml. */
    public static final String ID = "de.cau.cs.kieler.klighd.modifiers.PortRotationModifier";

    /** Constant denoting the value of 90. */
    public static final int NINETY_DEGREE = 90;

    /**
     * {@inheritDoc}
     */
    public boolean modify(final StyleModificationContext context) {

        // Determine the containing KPort ...
        final KPort port = ModelingUtil.eContainerOfType(context.getStyle(), KPort.class);
        if (port == null) {
            // ... and stop if the style to be modified is not contained in a port.
            return false;
        }

        final KRotation rotation = (KRotation) context.getStyle();

        // If the current port side differs from the original side ...
        if (port.getProperty(KlighdProperties.LAYOUT_PORT_SIDE)
                != port.getProperty(KlighdProperties.ORIGINAL_PORT_SIDE)) {

            final PortSide original = port.getProperty(KlighdProperties.ORIGINAL_PORT_SIDE);
            final PortSide layouted = port.getProperty(KlighdProperties.LAYOUT_PORT_SIDE);

            // ... determine the angle the port figure must be rotated.

            if (original == PortSide.NORTH && layouted == PortSide.WEST
                    || original == PortSide.SOUTH && layouted == PortSide.EAST) {
                // if the port has been moved from the bottom side to the right one,
                // e.g. due to port constraints 'FREE' ...
                rotation.setRotation(-NINETY_DEGREE);
                return true;

            } else if (original == PortSide.NORTH && layouted == PortSide.EAST
                    || original == PortSide.SOUTH && layouted == PortSide.WEST) {
                rotation.setRotation(NINETY_DEGREE);
                return true;

            } else if (original == PortSide.NORTH && layouted == PortSide.SOUTH
                    || original == PortSide.SOUTH && layouted == PortSide.NORTH
                    || original == PortSide.EAST && layouted == PortSide.WEST
                    || original == PortSide.WEST && layouted == PortSide.EAST) {
                rotation.setRotation(NINETY_DEGREE + NINETY_DEGREE);
                return true;

                // Further cases should be added here if they are necessary!
            } else if (rotation.getRotation() != 0) {
                rotation.setRotation(0);
                return true;

            } else {
                return false;
            }

        } else if (rotation.getRotation() != 0) {
            // otherwise reset the rotation if unequal to zero
            rotation.setRotation(0);
            return true;

        } else {
            return false;
        }
    }
}
