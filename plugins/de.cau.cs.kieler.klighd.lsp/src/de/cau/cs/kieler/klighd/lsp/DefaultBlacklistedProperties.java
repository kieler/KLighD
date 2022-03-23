/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp;

import java.util.Arrays;
import java.util.List;

import org.eclipse.elk.graph.properties.IProperty;

import de.cau.cs.kieler.klighd.IBlacklistedProperties;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
//import de.cau.cs.kieler.sccharts.ui.synthesis.SCChartsDiagramProperties;

/**
 * @author mka
 *
 */
public class DefaultBlacklistedProperties implements IBlacklistedProperties {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IProperty<?>> getProperties() {
        return Arrays.asList(KlighdInternalProperties.MODEL_ELEMEMT);
    }

}
