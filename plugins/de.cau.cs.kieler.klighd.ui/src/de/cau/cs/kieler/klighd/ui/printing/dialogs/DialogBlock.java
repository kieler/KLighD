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

package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * Intended to be used as a base for other specializing dialog blocks. Provides common layout
 * functionality
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
abstract class DialogBlock {
    private final IDialogUnitConverter dluConverter;

    DialogBlock(IDialogUnitConverter dluConverter) {
        this.dluConverter = dluConverter;
    }

    protected Shell getShell() {
        return dluConverter.getShell();
    }

    static Composite layout(Composite composite, int columns) {
        GridLayout g = new GridLayout(columns, false);
        g.marginLeft = 6;
        g.marginRight = 6;
        g.marginTop = 6;
        g.marginBottom = 3;
        composite.setLayout(g);

        return composite;
    }

    GridData getLayoutData(Control control) {
        GridData result = (GridData) control.getLayoutData();

        if (result == null) {
            result = new GridData();
            control.setLayoutData(result);
        }

        return result;
    }

    Control layoutFillHorizontal(Control control) {
        layoutFillHorizontal(control, true);

        return control;
    }

    Control layoutFillHorizontal(Control control, boolean grab) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.FILL;
        data.grabExcessHorizontalSpace = grab;

        return control;
    }

    Control layoutFillVertical(Control control) {
        GridData data = getLayoutData(control);

        data.verticalAlignment = SWT.FILL;
        data.grabExcessVerticalSpace = true;

        return control;
    }

    Control layoutAlignLeft(Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.BEGINNING;
        data.grabExcessHorizontalSpace = false;

        return control;
    }

    Control layoutAlignRight(Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.END;
        data.grabExcessHorizontalSpace = false;

        return control;
    }

    Control layoutFillBoth(Control control) {
        GridData data = getLayoutData(control);

        data.horizontalAlignment = SWT.FILL;
        data.grabExcessHorizontalSpace = true;
        data.verticalAlignment = SWT.FILL;
        data.grabExcessVerticalSpace = true;

        return control;
    }

    Control layoutSpanHorizontal(Control control, int span) {
        GridData data = getLayoutData(control);

        data.horizontalSpan = span;

        return control;
    }

    Control layoutSpanVertical(Control control, int span) {
        GridData data = getLayoutData(control);

        data.verticalSpan = span;

        return control;
    }

    Control layoutHorizontalIndent(Control control) {
        layoutHorizontalIndent(control, 15);

        return control;
    }

    Control layoutHorizontalIndent(Control control, int inset) {
        GridData data = getLayoutData(control);
        data.horizontalIndent = inset;

        return control;
    }

    Control layoutVerticalIndent(Control control) {
        layoutVerticalIndent(control, 15);

        return control;
    }

    Control layoutVerticalIndent(Control control, int inset) {
        GridData data = getLayoutData(control);
        data.verticalIndent = inset;

        return control;
    }

    Control layoutWidth(Control control, int dlus) {
        if (dlus > 0) {
            GridData data = getLayoutData(control);
            data.widthHint = dluConverter.convertHorizontalDLUsToPixels(dlus);
        }

        return control;
    }

    Control layoutHeight(Control control, int dlus) {
        if (dlus > 0) {
            GridData data = getLayoutData(control);
            data.heightHint = dluConverter.convertHorizontalDLUsToPixels(dlus);
        }

        return control;
    }

    Group group(Composite parent, String text) {
        Group result = new Group(parent, SWT.NONE);
        result.setText(text);
        layoutFillBoth(result);
        return result;
    }

    Label label(Composite parent, String text) {
        Label result = new Label(parent, SWT.NONE);
        result.setText(text);
        return result;
    }

    Text text(Composite parent, int width) {
        Text result = new Text(parent, SWT.SINGLE | SWT.BORDER);
        layoutFillHorizontal(result, false);
        layoutWidth(result, width);

        return result;
    }

    ComboViewer combo(Composite parent) {
        ComboViewer result = new ComboViewer(parent);
        return result;
    }

    Scale scale(Composite parent, int min, int max) {
        Scale result = new Scale(parent, SWT.HORIZONTAL);
        result.setMinimum(min);
        result.setMaximum(max);
        return result;
    }

    ListViewer list(Composite parent) {
        ListViewer result = new ListViewer(parent);
        return result;
    }

    Button button(Composite parent, String text) {
        Button result = new Button(parent, SWT.PUSH);
        result.setText(text);
        return result;
    }

    Button radio(Composite parent, String text) {
        Button result = new Button(parent, SWT.RADIO);
        result.setText(text);
        return result;
    }

    Button check(Composite parent, String text) {
        Button result = new Button(parent, SWT.CHECK);
        result.setText(text);
        return result;
    }

    Spinner spinner(Composite parent, int min, int max) {
        Spinner result = new Spinner(parent, SWT.BORDER);
        result.setMinimum(min);
        result.setMaximum(max);

        return result;
    }

    Control blank(Composite parent) {
        Label result = new Label(parent, SWT.NONE);
        return result;
    }

    public abstract Control createContents(Composite parent);

    public abstract void dispose();

    public static interface IDialogUnitConverter {
        int convertHorizontalDLUsToPixels(int dlus);

        Shell getShell();
    }
}
