/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.microlayout;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

/**
 * Wrapper object for {@link GC}s to allow thread local storage. The GC is disposed upon
 * garbage-collection.
 * 
 * @author nbw
 */
public class GCContainer {

    /** The contained {@link GC}. */
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
    public final GC gc;

    /** 
     * Constructor for the new container as well as the new {@link GC}. 
     * 
     * @param display The {@link Display} to work on.
     */
    public GCContainer(final Display display) {
        this.gc = new GC(display);
    }

    @Override
    protected void finalize() throws Throwable {
        gc.dispose();
    }
}
