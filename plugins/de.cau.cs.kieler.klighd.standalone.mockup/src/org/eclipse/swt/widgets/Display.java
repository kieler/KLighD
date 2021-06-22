/*******************************************************************************
 * Copyright (c) 2000, 2020 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;

public class Display {
    
    private static Display currentDisplay = new Display();
    
    public static Display getCurrent () {
        return currentDisplay;
    }
    
    public Point getDPI () {
        return null;
    }
    
    public void syncExec(Runnable r) {
        
    }
    
    public void asyncExec(Runnable r) {
        
    }
    
    public Color getSystemColor(int i) {
        return null;
    }
    
    public void wake() {
        
    }
    
    public boolean readAndDispatch() {
        return false;
    }
    
    public void sleep() {
        
    }
}
