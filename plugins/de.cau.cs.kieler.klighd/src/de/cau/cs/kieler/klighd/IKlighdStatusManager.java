/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Throwables;

public interface IKlighdStatusManager {
    
    // status handling styles, inspired by 'org.eclipse.ui.statushandlers.StatusManager.NONE', ...
    static int NONE = 0;
    static int LOG = 1;
    static int SHOW = 2;
    static int BLOCK = 4;
    
    void handle(IStatus status, int style);
    
    public static class Impl implements IKlighdStatusManager {

        private static Impl instance = new Impl();
        
        private Impl() {
        }
        
        static IKlighdStatusManager getInstance() {
            return instance;
        }
        
        @Override
        public void handle(IStatus status, int style) {
            if (status == null)
                throw new IllegalArgumentException("IStatusManager.Impl: Argument 'status' of 'handle(IStatus, int)' must not be 'null'!");
            
            final String prefix;
            switch (status.getSeverity()) {
            case IStatus.ERROR:
                prefix = "ERROR: ";
                break;
            case IStatus.WARNING:
                prefix = "WARNING: ";
                break;
            case IStatus.INFO:
                prefix = "INFO: ";
                break;
            default:
                return;
            }
            
            final String msg = status.getException() == null ? status.getMessage() : status.getException().getMessage();
            if (msg != null && !msg.isEmpty()) {
                System.out.println(prefix + msg);
            }
            if (status.getException() != null) {
                System.out.println(Throwables.getStackTraceAsString(status.getException()));
            }
        }
    }
}
