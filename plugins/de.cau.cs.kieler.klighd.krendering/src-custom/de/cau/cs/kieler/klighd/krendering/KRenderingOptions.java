/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
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
package de.cau.cs.kieler.klighd.krendering;

import java.util.EnumSet;

import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

public class KRenderingOptions implements ILayoutMetaDataProvider {

    public static final IProperty<KRendering> K_RENDERING =
            new Property<KRendering>("de.cau.cs.kieler.klighd.krendering.kRendering");

    public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.krendering.kRendering").group("")
                .name("KlighD KRendering").description(null).type(LayoutOptionData.Type.OBJECT)
                .optionClass(KRendering.class)
                .targets(EnumSet.of(LayoutOptionData.Target.EDGES, LayoutOptionData.Target.LABELS,
                        LayoutOptionData.Target.NODES, LayoutOptionData.Target.PORTS))
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());
    }
}
