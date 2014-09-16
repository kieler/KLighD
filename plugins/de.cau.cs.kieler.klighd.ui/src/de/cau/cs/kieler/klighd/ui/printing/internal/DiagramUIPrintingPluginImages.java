// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.printing.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;

/**
 * Bundle of all images used by this plugin. Image descriptors can be retrieved
 * by referencing the public image descriptor variable directly.
 * 
 * @author cmahoney
 * @author csp
 */
public final class DiagramUIPrintingPluginImages {

    private DiagramUIPrintingPluginImages() {
        // do not instantiate
    }

    // prefixes

    /**
     * The icons root directory.
     */
    private static final String PREFIX_ROOT = "icons/printing/"; //$NON-NLS-1$

    // Image descriptors.

    /** Image for collate on. */
    public static final ImageDescriptor COLLATE_ON = create(PREFIX_ROOT + "collate.gif"); //$NON-NLS-1$
    /** Image for collate off. */
    public static final ImageDescriptor COLLATE_OFF = create(PREFIX_ROOT 
            + "no_collate.gif"); //$NON-NLS-1$

    /**
     * Creates the image descriptor from the filename given.
     * 
     * @param imageName
     *            the full filename of the image
     * @return the new image descriptor
     */
    private static ImageDescriptor create(final String imageName) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(KlighdUIPlugin.PLUGIN_ID, imageName);
    }

}
