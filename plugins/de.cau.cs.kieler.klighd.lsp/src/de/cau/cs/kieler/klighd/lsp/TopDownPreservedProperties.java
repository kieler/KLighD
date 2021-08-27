/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp;

import java.util.Arrays;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.properties.IProperty;

import de.cau.cs.kieler.klighd.IPreservedProperties;

/**
 * @author mka
 *
 */
public class TopDownPreservedProperties implements IPreservedProperties {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IProperty<?>> getProperties() {
        return Arrays.asList(CoreOptions.TOP_DOWN_LAYOUT_RENDER_SCALE);
    }

}
