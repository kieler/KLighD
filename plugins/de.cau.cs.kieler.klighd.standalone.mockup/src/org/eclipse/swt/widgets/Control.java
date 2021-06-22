/*******************************************************************************
 * Copyright (c) 2000, 2018 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stefan Xenos (Google) - bug 468854 - Add a requestLayout method to Control
 *******************************************************************************/
package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Point;

public abstract class Control {
    
    public boolean isDisposed() {
        return true;
    }
    
    public Point getSize() {
        return null;
    }
    public Display getDisplay() {
        return null;
    }
    public void dispose() {
        
    }
}
