// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
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
package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * Utility class to provide common layout functionality.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
/**
 * @author csp
 * 
 */
public final class DialogUtil {

    private DialogUtil() {
        // Prevent instantiation.
    }

    /** the vertical indent. */
    private static final int VERTICAL_INDENT = 15;
    /** the horizontal indent. */
    private static final int HORIZONTAL_INDENT = 15;

    /**
     * Sets a {@link GridLayout} with given number of columns on the composite.
     * the following uniform margins are set:
     * <li>left: 6
     * <li>right: 6
     * <li>top: 6
     * <li>bottom: 3
     * 
     * @param composite
     *            the composite to set the layout on
     * @param columns
     *            the number of columns
     * @return the given composite
     */
    public static Composite layout(final Composite composite, final int columns) {
        GridLayout g = new GridLayout(columns, false);
        // SUPPRESS CHECKSTYLE NEXT 4 MagicNumber
        g.marginLeft = 6;
        g.marginRight = 6;
        g.marginTop = 6;
        g.marginBottom = 3;
        composite.setLayout(g);

        return composite;
    }

    /**
     * Returns the layout data of the given control. If its {@code null}, new GridData is created.
     * 
     * @param control
     *            the control to get the layout data from.
     * @return the layout data
     */
    public static GridData getLayoutData(final Control control) {
        GridData result = (GridData) control.getLayoutData();

        if (result == null) {
            result = new GridData();
            control.setLayoutData(result);
        }

        return result;
    }

    /**
     * Set the layout data of the control to horizontal fill.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the horizontal fill on
     * @param grab
     *            whether to grab excess horizontal space
     * @return the given control
     */
    public static Control layoutFillHorizontal(final Control control, final boolean grab) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.FILL;
        data.grabExcessHorizontalSpace = grab;

        return control;
    }

    /**
     * Set the layout data of the control to vertical fill and grabs excess vertical space.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the vertical fill on
     * @return the given control
     */
    public static Control layoutFillVertical(final Control control) {
        GridData data = getLayoutData(control);

        data.verticalAlignment = SWT.FILL;
        data.grabExcessVerticalSpace = true;

        return control;
    }

    /**
     * Aligns the control left.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to align left
     * @return the given control
     */
    public static Control layoutAlignLeft(final Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.BEGINNING;
        data.grabExcessHorizontalSpace = false;

        return control;
    }

    /**
     * Aligns the control right.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to align right
     * @return the given control
     */
    public static Control layoutAlignRight(final Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.END;
        data.grabExcessHorizontalSpace = false;

        return control;
    }

    /**
     * Set the layout data of the control to horizontal and vertical fill and grabs excess
     * horizontal and vertical space.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the fill on
     * @return the given control
     */
    public static Control layoutFillBoth(final Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.FILL;
        data.grabExcessHorizontalSpace = true;
        data.verticalAlignment = SWT.FILL;
        data.grabExcessVerticalSpace = true;

        return control;
    }

    /**
     * Set the horizontal span.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the span on
     * @param span
     *            the number of columns to span
     * @return the given control
     */
    public static Control layoutSpanHorizontal(final Control control, final int span) {
        GridData data = getLayoutData(control);

        data.horizontalSpan = span;

        return control;
    }

    /**
     * Set the horizontal default indent.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the indent on
     * @return the given control
     */
    public static Control layoutHorizontalIndent(final Control control) {
        layoutHorizontalIndent(control, HORIZONTAL_INDENT);

        return control;
    }

    /**
     * Set the horizontal indent.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the indent on
     * @param indent
     *            the indent to set
     * @return the given control
     */
    public static Control layoutHorizontalIndent(final Control control, final int indent) {
        GridData data = getLayoutData(control);
        data.horizontalIndent = indent;

        return control;
    }

    /**
     * Set the vertical default indent.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the indent on
     * @return the given control
     */
    public static Control layoutVerticalIndent(final Control control) {
        layoutVerticalIndent(control, VERTICAL_INDENT);

        return control;
    }

    /**
     * Set the vertical indent.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the indent on
     * @param indent
     *            the indent to set
     * @return the given control
     */
    public static Control layoutVerticalIndent(final Control control, final int indent) {
        GridData data = getLayoutData(control);
        data.verticalIndent = indent;

        return control;
    }

    /**
     * Set the width.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the width on
     * @param width
     *            the width to set
     * @return the given control
     */
    public static Control layoutWidth(final Control control, final int width) {
        if (width > 0) {
            GridData data = getLayoutData(control);
            data.widthHint = width;
        }

        return control;
    }

    /**
     * Set the height.
     * If no layout data is set, a new is created.
     * 
     * @param control
     *            the control to set the height on
     * @param height
     *            the height to set
     * @return the given control
     */
    public static Control layoutHeight(final Control control, final int height) {
        if (height > 0) {
            GridData data = getLayoutData(control);
            data.heightHint = height;
        }

        return control;
    }

    /**
     * Create a group with the given title and fill both vertical and horizontal.
     * 
     * @param parent
     *            the parent
     * @param text
     *            the title
     * @return the group
     */
    public static Group group(final Composite parent, final String text) {
        Group result = new Group(parent, SWT.NONE);
        result.setText(text);
        layoutFillBoth(result);
        return result;
    }

    /**
     * Creates a label with the given text.
     * 
     * @param parent
     *            the parent
     * @param text
     *            the label text
     * @return the label
     */
    public static Label label(final Composite parent, final String text) {
        Label result = new Label(parent, SWT.NONE);
        result.setText(text);
        return result;
    }

    /**
     * Creates a text field with given width.
     * 
     * @param parent
     *            the parent
     * @param width
     *            the width
     * @return the text field
     */
    public static Text text(final Composite parent, final int width) {
        Text result = new Text(parent, SWT.SINGLE | SWT.BORDER);
        layoutFillHorizontal(result, false);
        layoutWidth(result, width);

        return result;
    }

    /**
     * Creates a combo selection box.
     * 
     * @param parent
     *            the parent
     * @return the combobox
     */
    public static ComboViewer combo(final Composite parent) {
        ComboViewer result = new ComboViewer(parent);
        return result;
    }

    /**
     * Creates a scale slider.
     * 
     * @param parent
     *            the parent
     * @param min
     *            the minimum value
     * @param max
     *            the maximum value
     * @return the scale
     */
    public static Scale scale(final Composite parent, final int min, final int max) {
        Scale result = new Scale(parent, SWT.HORIZONTAL);
        result.setMinimum(min);
        result.setMaximum(max);
        return result;
    }

    /**
     * Creates a push button with the given text.
     * 
     * @param parent
     *            the parent
     * @param text
     *            the text
     * @return the button
     */
    public static Button button(final Composite parent, final String text) {
        Button result = new Button(parent, SWT.PUSH);
        result.setText(text);
        return result;
    }

    /**
     * Creates a radio button with the given text.
     * 
     * @param parent
     *            the parent
     * @param text
     *            the text
     * @return the button
     */
    public static Button radio(final Composite parent, final String text) {
        Button result = new Button(parent, SWT.RADIO);
        result.setText(text);
        return result;
    }

    /**
     * Creates a checkbox with the given text.
     * 
     * @param parent
     *            the parent
     * @param text
     *            the text
     * @return the checkbox
     */
    public static Button check(final Composite parent, final String text) {
        Button result = new Button(parent, SWT.CHECK);
        result.setText(text);
        return result;
    }

    /**
     * Creates a spinner.
     * 
     * @param parent
     *            the parent
     * @param min
     *            the minimum value
     * @param max
     *            the maximum value
     * @return the spinner
     */
    public static Spinner spinner(final Composite parent, final int min, final int max) {
        Spinner result = new Spinner(parent, SWT.BORDER);
        result.setMinimum(min);
        result.setMaximum(max);

        return result;
    }

    /**
     * Creates a blank label.
     * 
     * @param parent
     *            the parent
     * @return the label
     */
    public static Control blank(final Composite parent) {
        Label result = new Label(parent, SWT.NONE);
        return result;
    }
}
