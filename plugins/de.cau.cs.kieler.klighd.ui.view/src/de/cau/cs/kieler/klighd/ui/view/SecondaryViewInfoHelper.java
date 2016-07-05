/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.ui.view;

/**
 * This class provides the infrastructure to enhance the {@link DiagramView} with additional user
 * information about the behavior of secondary (forked) diagram views. Only the first secondary view
 * opened should show the info bar. If the user has closed the info bar in the past, it won't be
 * shown again.
 * 
 * <p>
 * Clients call {@link #shouldShowInfoBar(DiagramView)} to check whether or not they should show the
 * info bar. Also, they must call {@link #secondaryViewDisposed(DiagramView)} when they are in the
 * process of being disposed to allow this class to do some bookkeeping. Also, when the user decides
 * to dismiss the info bar, {@link #infoBarDismissed()} must be called to allow the class to persist
 * this decision.
 * </p>
 * 
 * @author als
 */
final class SecondaryViewInfoHelper {

    /** The key for storing the user decision to suppress info box. */
    private static final String SUPPRESS_INFO_KEY =
            "de.cau.cs.kieler.klighd.ui.view.suppressForkedViewInfo";
    /** The view currently showing the info box. */
    private static DiagramView viewShowingInfo = null;
    /** The flag indicating whether the info box should be shown or not. */
    private static boolean suppressInfo =
            KlighdViewPlugin.getDefault().getDialogSettings().getBoolean(SUPPRESS_INFO_KEY);

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private SecondaryViewInfoHelper() {
    }

    /**
     * Returns whether the given (secondary) diagram view should show the info bar or not.
     * 
     * @param view
     *            the calling {@link DiagramView}
     * @return {@code true} if the info bar should be shown.
     */
    public static boolean shouldShowInfoBar(final DiagramView view) {
        if (!suppressInfo && viewShowingInfo == null) {
            // Remember this view as the view showing the info box
            viewShowingInfo = view;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles the closing of a {@link DiagramView} which might show the box.
     * 
     * @param view
     *            the closed {@link DiagramView}
     */
    public static void secondaryViewDisposed(final DiagramView view) {
        if (view == viewShowingInfo) {
            viewShowingInfo = null;
        }
    }

    /**
     * Informs the class that the user does not want to see the info bar again. Ever.
     */
    public static void infoBarDismissed() {
        suppressInfo = true;
        KlighdViewPlugin.getDefault().getDialogSettings().put(SUPPRESS_INFO_KEY, true);
    }

}
