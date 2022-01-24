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
package de.cau.cs.kieler.kgraph.text.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Use this class to register components to be used within the IDE.
 * 
 * @author msp
 */
public class KGraphUiModule extends AbstractKGraphUiModule {
    
    /** Identifier of the KGraph Text UI plugin. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kgraph.text.ui";

    /**
     * Create a KGraph UI module.
     * 
     * @param plugin the plugin
     */
    public KGraphUiModule(final AbstractUIPlugin plugin) {
        super(plugin);
    }

}
