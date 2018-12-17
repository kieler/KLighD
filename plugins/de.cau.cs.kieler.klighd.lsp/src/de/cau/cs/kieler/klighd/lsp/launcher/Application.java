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
package de.cau.cs.kieler.klighd.lsp.launcher;

import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution.
 * Executes {@link RunSocketServer#main} as a Eclipse application.
 * Run this class as an Eclipse application to launch a language server with only diagram features
 * and basic Xtext features.
 */
public class Application implements IApplication {

    @Override
    public Object start(final IApplicationContext context) throws Exception {
        final Map<?,?> args = context.getArguments();
        final String[] appArgs = (String[]) args.get("application.args");
        RunSocketServer.main(appArgs);

        return IApplication.EXIT_OK;
    }

    @Override
    public void stop() {
//    nothing to do
    }
}