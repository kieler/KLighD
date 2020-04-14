/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020 by TypeFox GmbH (typefox.io) and others.
 *
 * This code is provided under the terms of the Eclipse Public License 1.0 (EPL-1.0).
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * A collection of beneficial facts to ask for as well as convenience methods.
 * Find more client related APIs {@link LightDiagramServices}.
 * 
 * @author chsch
 */
public class Klighd {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd";

    /** the UI plug-in ID. */
    public static final String UI_PLUGIN_ID = PLUGIN_ID + ".ui";

    /** A definition place of the platform-specific line separator. */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** A Boolean flag indicating that the tool is running on a linux system. */
    public static final boolean IS_LINUX;

    /** A Boolean flag indicating that the tool is running on a MacOSX system. */
    public static final boolean IS_MACOSX;

    /** A Boolean flag indicating that the tool is running on a Windows system. */
    public static final boolean IS_WINDOWS;
    
    /** A Boolean flag indicating that the tool is running in an equinox setup. */
    public static final boolean IS_PLATFORM_RUNNING;
    
    private static IKlighdStatusManager statusManager;
    
    private static boolean suppressDisplayScaleCompensationWhileHandlingText = false;
    
    static {
        boolean isLinux = false;
        boolean isOSX = false;
        boolean isWindows = false;
        boolean isPlatformRunning = false;
        
        try {
            isLinux = Platform.getOS().equals(Platform.OS_LINUX);
            isOSX = Platform.getOS().equals(Platform.OS_MACOSX);
            isWindows = Platform.getOS().equals(Platform.OS_WIN32);
            isPlatformRunning = Platform.isRunning();
            
        } catch (Throwable t) {
            String os = System.getProperties().get("os.name").toString().toLowerCase();
            isLinux = os.startsWith("unix");
            isOSX = os.startsWith("osx");
            isWindows = os.startsWith("win");
        }
        
        IS_LINUX = isLinux;
        IS_MACOSX = isOSX;
        IS_WINDOWS = isWindows;
        IS_PLATFORM_RUNNING = isPlatformRunning;
    }
    
    /**
     * This is a utility class providing just static methods; hence instantiation is prohibited.
     */
    private Klighd() {
    }
    
    public static IKlighdStatusManager getStatusManager() {
        if (statusManager == null)
            statusManager = IKlighdStatusManager.Impl.getInstance();
        return statusManager;
    }
    
    public static void setStatusManager(IKlighdStatusManager impl) {
        statusManager = impl;
    }
    
    public static void handle(IStatus status) {
        getStatusManager().handle(status, IKlighdStatusManager.NONE);
    }
    
    public static void log(IStatus status) {
        getStatusManager().handle(status, IKlighdStatusManager.LOG);
    }
    
    public static void show(IStatus status) {
        getStatusManager().handle(status, IKlighdStatusManager.SHOW);
    }
    
    public static void handle(IStatus status, int style) {
        getStatusManager().handle(status, style);
    }
    
    public static void handle(CoreException coreException, String pluginId) {
        getStatusManager().handle(new Status(coreException.getStatus().getSeverity(), pluginId,
                coreException.getLocalizedMessage(), coreException), IKlighdStatusManager.NONE);
    }

    /**
     * Returns the (global) setting on adjusting text sizes wrt. the system wide display scale
     * setting.
     * 
     * @return <code>true</code> if compensation shall be suppress, <code>false</code> by default.
     */
    public static boolean isSuppressDisplayScaleCompensationWhileHandlingText() {
        return suppressDisplayScaleCompensationWhileHandlingText;
    }

    /**
     * Changes the (global) setting on adjusting text sizes wrt. the system wide display scale
     * setting. This setting is not supposed to be changed in UI applications, but may be altered
     * in off-screen rendering applications.
     *
     * @param suppress
     *            <code>true</code> if compensation shall be suppressed, <code>false</code> reverts
     *            to the default.
     */
    public static void setSuppressDisplayScaleCompensationWhileHandlingText(boolean suppress) {
        suppressDisplayScaleCompensationWhileHandlingText = suppress;
    }
}
