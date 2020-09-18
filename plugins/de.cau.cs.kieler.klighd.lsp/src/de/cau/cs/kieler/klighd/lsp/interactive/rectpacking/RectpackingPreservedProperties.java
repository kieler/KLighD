/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking;

import java.util.Arrays;
import java.util.List;

import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions;
import org.eclipse.elk.graph.properties.IProperty;

import de.cau.cs.kieler.klighd.IPreservedProperties;

/**
 * The position of a node shall be preserved from the layout run.
 * This enables us to use this position and layer for interactive layout handles.
 * 
 * @author sdo
 */
public class RectpackingPreservedProperties implements IPreservedProperties {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IProperty<?>> getProperties() {
        return Arrays.asList(RectPackingOptions.CURRENT_POSITION);
    }

}
