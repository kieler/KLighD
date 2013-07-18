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
package de.cau.cs.kieler.klighd.piccolo.svg.browsing;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Shell;

/**
 * @author uru
 * 
 */
public class BrowseServerApplication implements IApplication {

    // private boolean termRequested = false;
    // private Object termSync = new Object();

    private final Shell shell = new Shell();

    String rootFolder = "E:/Uni/ma/models";

    /**
     * {@inheritDoc}
     */
    @Override
    public Object start(IApplicationContext context) throws Exception {

        new BrowsingSVGServer(shell, rootFolder, 8081);

        while (!shell.isDisposed()) {
            if (!shell.getDisplay().readAndDispatch()) {
                shell.getDisplay().sleep();
            }
        }

        // synchronized (termSync) {
        // while (!termRequested) {
        // try {
        // termSync.wait();
        // } catch (InterruptedException e) {
        // // Nothing to do, simply wait on synchronization
        // // object again.
        // }
        // }
        // }

        try {
            BrowsingSVGServer.getInstance().stop();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return IApplication.EXIT_OK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        shell.dispose();
        // termRequested = true;
        // synchronized (termSync) {
        // termSync.notify();
        // }
    }

}
