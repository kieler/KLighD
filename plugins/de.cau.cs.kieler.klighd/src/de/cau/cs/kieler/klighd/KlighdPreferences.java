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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Widget;


/**
 * IDs and default values of preferences defined by KLighD and stored in the preference store.
 * 
 * @author cds
 * @author chsch
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

    /** ID of the magnification lens width preference. */
    public static final String MAGNIFICATION_LENS_WIDTH =
            "de.cau.cs.kieler.klighd.preferences.magnificationLensWidth";
    
    /** Default magnification lens width value. */
    public static final int MAGNIFICATION_LENS_WIDTH_DEFAULT = 400;
    
    /** ID of the magnification lens height preference. */
    public static final String MAGNIFICATION_LENS_HEIGHT =
            "de.cau.cs.kieler.klighd.preferences.magnificationLensHeight";
    
    /** Default magnification lens height value. */
    public static final int MAGNIFICATION_LENS_HEIGHT_DEFAULT = 400;
    
    /** ID of the magnification lens scale preference, unit is percent. */
    public static final String MAGNIFICATION_LENS_SCALE =
            "de.cau.cs.kieler.klighd.preferences.magnificationLensScale";
    
    /** Default magnification lens scale value in percent. */
    public static final int MAGNIFICATION_LENS_SCALE_DEFAULT = 200;
    
    /**
     * ID of the advanced panning mode preference; advanced panning means diagram panning continues
     * if cursor leaves diagram area until in re-enters or button is released.
     */
    public static final String ADVANCED_PANNING_MODE =
            "de.cau.cs.kieler.klighd.preferences.advancedPanning";

    /** Default advanced panning mode setting. */
    public static final boolean ADVANCED_PANNING_MODE_DEFAULT = false;


    /** The {@link IPreferenceStore} used for KLighD-specific preferences. */
    public static final IPreferenceStore STORE = KlighdPlugin.getDefault().getPreferenceStore();

    /**
     * This class cannot be instantiated.
     */
    private KlighdPreferences() {
        
    }
    
    /**
     * Getter.
     * 
     * @return <code>true</code> if advanced panning is active, <code>false</code> otherwise.
     */
    public static boolean isAdvancedPanningMode() {
        return STORE.getBoolean(ADVANCED_PANNING_MODE);
    }


    /**
     * 
     * @param widget
     *            an SWT {@link Widget} onto which a {@link DisposeListener} is installed to for
     *            properly removing the preference change listener once it is not required anymore,
     *            may be <code>null</code>
     * @param listener
     *            an {@link IPropertyChangeListener} performing internal updates
     */
    public static void registerPrefChangeListener(final Widget widget,
            final IPropertyChangeListener listener) {
        KlighdPreferences.STORE.addPropertyChangeListener(listener);
        
        if (widget == null) {
            return;
        }
        
        widget.addDisposeListener(new DisposeListener() {
            
            public void widgetDisposed(final DisposeEvent e) {
                KlighdPreferences.STORE.removePropertyChangeListener(listener);
                e.widget.removeDisposeListener(this);
            }
        });
    }
}
