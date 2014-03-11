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
 * Enum containing possible zoom styles. A possible zoom style might be 'zoomToFit' where the
 * viewport is adapted, translated and scaled, such that the whole diagram is visible.
 * 
 * @author uru
 */
public enum ZoomStyle {

    /** Apply no zooming at all. */
    NONE,
    /** Fit the whole diagram into the viewport, translating and scaling. */
    ZOOM_TO_FIT,
    /**
     * Center the current viewport on a certain element that is the 'focus', possibly zoom if the
     * focus is bigger than the current viewport.
     */
    ZOOM_TO_FOCUS;

    /**
     * 'zoomToFit' has higher priority than 'zoomToFocus'.
     * 
     * @param zoomToFit
     *            {@link #ZOOM_TO_FIT}
     * @param zoomToFocus
     *            {@link #ZOOM_TO_FOCUS}
     * @return a {@link ZoomStyle} depending on the parameters.
     */
    public static ZoomStyle create(final boolean zoomToFit, final boolean zoomToFocus) {
        if (zoomToFit) {
            return ZOOM_TO_FIT;
        } else if (zoomToFocus) {
            return ZOOM_TO_FOCUS;
        }
        return NONE;
    }

    /**
     * @param value
     *            String representation of the requested ZoomStyle
     * @param defaultStyle
     *            the default value to return, eg if the 'value' is null
     * @return either the result of {@link ZoomStyle#valueOf(String)} or, if this does not yield a
     *         proper result, the specified 'defaultStyle'.
     */
    public static ZoomStyle valueOf(final String value, final ZoomStyle defaultStyle) {
        ZoomStyle res = ZoomStyle.valueOf(value);
        if (res == null) {
            return defaultStyle;
        } else {
            return res;
        }
    }
}
