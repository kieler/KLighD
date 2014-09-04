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

package de.cau.cs.kieler.klighd.ui.printing.internal;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.IViewer;

/**
 * PageData is used during the JPS printing process to cache
 * information that will be used later when printing is invoked
 * through a call-back mechanism.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * 
 */

public class PageData {

    final int index;
    final int row;
    final int column;

    final IViewer diagram;
    final Rectangle bounds;
    final PageMargins margins;
    final FontData font;
    final IPreferenceStore preferences;

    PageData(int index, int row, int column, IViewer diagram, Rectangle bounds,
            PageMargins margins, FontData font, IPreferenceStore preferences) {

        this.index = index;
        this.row = row;
        this.column = column;
        this.diagram = diagram;
        this.bounds = bounds;
        this.margins = margins;
        this.font = font;
        this.preferences = preferences;
    }

    public static final class PageMargins {
        public int left;
        public int right;
        public int top;
        public int bottom;
    }
}
