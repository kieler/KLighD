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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * 
 * This class provides the infrastructure to enhance the {@link DiagramView} with additional user
 * information about the behavior of secondary (forked) diagram views.
 * 
 * @author als
 *
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
     * Handles the opening of a forked {@link DiagramView} and enhances it with an info box to
     * inform the user about forked view, if necessary.
     * 
     * @param view
     *            the closed {@link DiagramView}
     */
    public static void secondaryViewCreated(final DiagramView view, final Composite viewComposite) {
        if (!suppressInfo && viewShowingInfo == null) {
            // Add info box
            Composite infoBox = new Composite(viewComposite, SWT.NONE);
            infoBox.setLayout(new RowLayout());
            GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
            infoBox.setLayoutData(layoutData);

            final Label infoText = new Label(infoBox, SWT.NONE);
            infoText.setText("This is a forked view.");

            final Link infoLink = new Link(infoBox, SWT.NONE);
            infoLink.setText("What does it mean?");
            infoLink.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    // TODO open up some kind of info dialog or documentation web page
                }
            });

            final Button suppressButton = new Button(infoBox, SWT.PUSH);
            suppressButton.setText("Don't show again");
            suppressButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    // Hide info box
                    infoBox.setVisible(false);
                    // Save the decision that the user does not want to see the message again
                    suppressInfo = true;
                    KlighdViewPlugin.getDefault().getDialogSettings().put(SUPPRESS_INFO_KEY, true);
                }
            });

            // Remember this view as the view showing the info box
            viewShowingInfo = view;
        }
    }
}
