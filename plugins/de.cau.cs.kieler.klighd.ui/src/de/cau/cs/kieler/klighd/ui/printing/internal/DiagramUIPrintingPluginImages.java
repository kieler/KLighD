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

package de.cau.cs.kieler.klighd.ui.printing.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;

/**
 * Bundle of all images used by this plugin. Image descriptors can be retrieved by referencing the
 * public image descriptor variable directly.
 * 
 * @author cmahoney
 */
public class DiagramUIPrintingPluginImages {

    // prefixes

    /**
     * The icons root directory.
     */
    private static final String PREFIX_ROOT = "icons/"; //$NON-NLS-1$

    /**
     * Enabled subdirectory off of root icon directory
     */
    private static final String PREFIX_ENABLED = PREFIX_ROOT + "etool16/"; //$NON-NLS-1$

    /**
     * Disabled subdirectory off of root icon directory
     */
    private static final String PREFIX_DISABLED = PREFIX_ROOT + "dtool16/"; //$NON-NLS-1$

    // Image descriptors.

    public static final ImageDescriptor DESC_PRINT = create(PREFIX_ENABLED
            + "print_preview_print.gif"); //$NON-NLS-1$

    public static final ImageDescriptor DESC_PRINT_DISABLED = create(PREFIX_DISABLED
            + "print_preview_print.gif"); //$NON-NLS-1$

//    public static final ImageDescriptor DESC_PAGE = create(PREFIX_ENABLED
//            + "print_preview_pages.gif"); //$NON-NLS-1$

//    public static final ImageDescriptor DESC_LEFT = create(PREFIX_ENABLED
//            + "print_preview_left.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_LEFT_DISABLED = create(PREFIX_DISABLED
//            + "print_preview_left.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_RIGHT = create(PREFIX_ENABLED
//            + "print_preview_right.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_RIGHT_DISABLED = create(PREFIX_DISABLED
//            + "print_preview_right.gif"); //$NON-NLS-1$

//    public static final ImageDescriptor DESC_UP = create(PREFIX_ENABLED
//            + "print_preview_up.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_UP_DISABLED = create(PREFIX_DISABLED
//            + "print_preview_up.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_DOWN = create(PREFIX_ENABLED
//            + "print_preview_down.gif"); //$NON-NLS-1$
//
//    public static final ImageDescriptor DESC_DOWN_DISABLED = create(PREFIX_DISABLED
//            + "print_preview_down.gif"); //$NON-NLS-1$

    public static final ImageDescriptor DESC_CLOSE = create(PREFIX_ENABLED
            + "print_preview_close.gif"); //$NON-NLS-1$

    public static final ImageDescriptor COLLATE_ON = create(PREFIX_ENABLED
            + "collate.gif"); //$NON-NLS-1$

    public static final ImageDescriptor COLLATE_OFF = create(PREFIX_ENABLED
            + "no_collate.gif"); //$NON-NLS-1$

    /**
     * Creates the image descriptor from the filename given.
     * 
     * @param imageName
     *            the full filename of the image
     * @return the new image descriptor
     */
    private static ImageDescriptor create(String imageName) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(KlighdUIPlugin.PLUGIN_ID, imageName);
    }

}
