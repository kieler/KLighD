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

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Shell;

/**
 * @author uru
 * 
 */
public class BrowseServerApplication implements IApplication {

    // A dummy shell is required to obey to piccolos class hierarchy.
    private final Shell shell = new Shell();

    private String rootFolder = null;

    /**
     * {@inheritDoc}
     */
    public Object start(IApplicationContext context) throws Exception {

        // parse arguments
        String[] args = Platform.getCommandLineArgs();

        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-rootFolder")) {
                i++;
                File f = new File(args[i]);
                if (f.exists()) {
                    rootFolder = f.getAbsolutePath();
                } else {
                    throw new IllegalArgumentException("Cannot locate specified root folder " + f
                            + ".");
                }
            }
            i++;
        }
        
        if(rootFolder == null) {
            throw new IllegalArgumentException("No -rootFolder specified.");
        }

        new BrowsingSVGServer(shell, rootFolder, 8081);

        // start the shell's event loop
        while (!shell.isDisposed()) {
            if (!shell.getDisplay().readAndDispatch()) {
                shell.getDisplay().sleep();
            }
        }

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
    public void stop() {
        shell.dispose();
    }

}
