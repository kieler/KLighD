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
package de.cau.cs.kieler.klighd;


/**
 * IDs and default values of preferences defined by KLighD and stored in the preference store.
 * 
 * @author cds
 */
public final class KlighdPreferences {
    
    /** ID of the Animate Layout preference. */
    public static final String ANIMATE_LAYOUT =
            "de.cau.cs.kieler.klighd.preferences.animateLayout"; //$NON-NLS-1$
    
    /** Default value of the Animate Layout preference. */
    public static final boolean ANIMATE_LAYOUT_DEFAULT = true;
    
    /** ID of the Zoom-style preference. */
    public static final String ZOOM_STYLE =
            "de.cau.cs.kieler.klighd.preferences.zoomStyle"; //$NON-NLS-1$
    
    /** Default zoom style value. */
    public static final String ZOOM_STYLE_DEFAULT = ZoomStyle.ZOOM_TO_FIT.name();
    
    /**
     * This class cannot be instantiated.
     */
    private KlighdPreferences() {
        
    }
}
