/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015-2025 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ide.model;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.klighd.ide.KlighdIdePlugin;

/**
 * Represents errors and exceptions. Displayed as error message with a huge error sign.
 * 
 * @author als
 */
class ErrorModel extends MessageModel {

    /** The error stack trace. */
    final String stacktrace;

    /**
     * Constructs an error model given message.
     * 
     * @param message
     *            error message
     */
    new(String message) {
        this(message, "Unkown", null);
    }

    /**
     * Constructs an error model given message and details.
     * 
     * @param message
     *            error message
     * @param reason
     *            the reason
     * @param stacktrace
     *            the stacktrace
     */
    new(String message, String reason, String stacktrace) {
        // CHECKSTYLEOFF MagicNumber NEXT
        super(message, reason, KlighdIdePlugin.PLUGIN_ID, "icons/full/model/error_sign.png", 250);
        // reason
        var String reasonToSet = "Unkown";
        if (reason === null) {
            // Parse reason from stacktrace
            if (stacktrace !== null && !stacktrace.isEmpty()) {
                val int newline = stacktrace.indexOf('\n');
                val int startReason = stacktrace.indexOf(':');
                if (newline != -1) {
                    if (startReason != -1 && startReason + 2 < newline) {
                        reasonToSet = stacktrace.substring(startReason + 2, newline);
                    } else {
                        reasonToSet = stacktrace.substring(0, newline);
                    }
                }
            }
        } else {
            reasonToSet = reason;
        }
        this.message = reasonToSet;
        // stacktrace
        var String stacktraceToSet = stacktrace;
        // Fix newlines
        // TODO still not working under windows, maybe the error dialog is evil
        if (!Platform.getOS().equals(Platform.OS_WIN32)) {
            // Fix newlines
            val String newline = System.getProperty("line.separator");
            if (newline !== null) {
                stacktraceToSet = stacktrace.replaceAll("\n", newline);
            }
        }
        this.stacktrace = stacktraceToSet;
    }

    /**
     * Constructs an error model given message and exception.
     * 
     * @param message
     *            error message
     * @param exception
     *            the exception
     */
    new(String message, Exception exception) {
        this(message, exception.getMessage(), getStackTraceString(exception));
    }

    // -- Utility Functions
    // -------------------------------------------------------------------------

    /**
     * Prints the stack trace of an Exception into String.
     * 
     * @param exception
     *            Exception
     * @return StackTrace as String
     */
    def private static String getStackTraceString(Exception exception) {
        val StringWriter traceReader = new StringWriter();
        exception.printStackTrace(new PrintWriter(traceReader));
        return traceReader.toString();
    }

    // -- Getters
    // -------------------------------------------------------------------------

    /**
     * @return the exception
     */
    def String getStackTrace() {
        return stacktrace;
    }
    

    // -- toString
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    override String toString() {
        val StringBuilder text = new StringBuilder();
        val String newline = System.getProperty("line.separator");
        
        if (title !== null) {
            text.append(title);
            text.append(newline);
        }
        if (message !== null) {
            text.append(message);
            text.append(newline);
        }
        if (stacktrace !== null) {
            text.append(stacktrace);
        }
        
        return text.toString();
    }

}
