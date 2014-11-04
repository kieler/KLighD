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

import de.cau.cs.kieler.klighd.IAction.ActionResult;

/**
 * Enumeration providing possible zoom styles. A possible zoom style is, e.g., 'zoomToFit' that causes
 * an update of the viewport by translating and scaling such that the whole diagram is visible.
 *
 * @author uru
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public enum ZoomStyle {

    /** Apply no zooming at all. */
    NONE,

    /** Apply revert zooming to the actual size of the diagram. */
    ZOOM_TO_ACTUAL_SIZE,

    /** Fit the whole diagram into the viewport, translating and scaling. */
    ZOOM_TO_FIT,

    /**
     * Center the current viewport on a certain element that is the 'focus', possibly zoom if the
     * focus is bigger than the current viewport.
     */
    ZOOM_TO_FOCUS,

    /**
     * Similar to {@link #ZOOM_TO_FOCUS} except in case the (clipped) diagram (in 100% size) fits
     * completely into the canvas area. Requests {@link #ZOOM_TO_FIT} for the (clipped) diagram (not
     * the configured focus node) in such cases.
     */
    ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT;

    /**
     * The 'zoom to focus' style configured by the employing application,
     * {@link #ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT} by default.
     */
    private static ZoomStyle zoomToFocusStyle = null;

    /**
     * Provides the 'zoom to focus' style configured by the employing application, which is
     * {@link #ZOOM_TO_FOCUS_OR_INCREASE_TO_FIT} by default.
     *
     * @return the 'zoom to focus' style configured by the employing application
     */
    public static ZoomStyle getZoomToFocusStyle() {
        if (zoomToFocusStyle == null) {
            zoomToFocusStyle = KlighdPreferences.getZoomToFocusStyle();
        }

        return zoomToFocusStyle;
    }

    /**
     * Dispatching method determining a {@link ZoomStyle} value based on the given flags.
     * 'zoomToFit' has higher priority than 'zoomToFocus'.
     *
     * @param zoomToActualSize
     *            request of zoom to actual size
     * @param zoomToFit
     *            request of zoom to fit
     * @param zoomToFocus
     *            request of zoom to focus
     *
     * @return a {@link ZoomStyle} depending on the parameters.
     */
    public static ZoomStyle create(final boolean zoomToActualSize, final boolean zoomToFit,
            final boolean zoomToFocus) {

        if (zoomToActualSize) {
            return ZOOM_TO_ACTUAL_SIZE;

        } else if (zoomToFit) {
            return ZOOM_TO_FIT;

        } else if (zoomToFocus) {
            return KlighdPreferences.getZoomToFocusStyle();

        } else {
            return NONE;
        }
    }

    /**
     * Dispatching method determining a {@link ZoomStyle} value based on the given flags.
     * 'zoomToFit' has higher priority than 'zoomToFocus'.
     *
     * @param actionResult
     *            the {@link ActionResult} to evaluate
     * @param viewContext
     *            the corresponding {@link ViewContext} to fall back to if no settings are made in
     *            the {@link ActionResult}
     *
     * @return the {@link ZoomStyle} based on the given {@link ActionResult}'s and
     *         {@link ViewContext}'s configuration.
     */
    public static ZoomStyle create(final ActionResult actionResult, final ViewContext viewContext) {
        final boolean zoomToActualSize = actionResult.getZoomToActualSize() != null
                ? actionResult.getZoomToActualSize() : false;

        final boolean zoomToFit = actionResult.getZoomToFit() != null
                ? actionResult.getZoomToFit() : viewContext.isZoomToFit();

        final boolean zoomToFocus = actionResult.getZoomToFocus() != null
                ? actionResult.getZoomToFocus() : viewContext.isZoomToFocus();

        return create(zoomToActualSize, zoomToFit, zoomToFocus);
    }

    /**
     * @param value
     *            String representation of the requested ZoomStyle
     * @param defaultStyle
     *            the default value to return, e.g., if the 'value' is null
     * @return either the result of {@link ZoomStyle#valueOf(String)} or, if this does not yield a
     *         proper result, the specified 'defaultStyle'.
     */
    public static ZoomStyle valueOf(final String value, final ZoomStyle defaultStyle) {
        final ZoomStyle res = ZoomStyle.valueOf(value);
        if (res == null) {
            return defaultStyle;
        } else {
            return res;
        }
    }
}
