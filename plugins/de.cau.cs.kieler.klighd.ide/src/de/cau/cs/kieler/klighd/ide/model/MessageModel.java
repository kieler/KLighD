/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
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

/**
 * A simple message model. Provides a title, message and icon.
 * 
 * @author als
 */
// CHECKSTYLEOFF VisibilityModifier
public class MessageModel {

    /** The title. */
    protected String title;
    /** The message. */
    protected String message;
    /** The icon path. */
    protected String iconPath;
    /** The icon plugin. */
    protected String iconPlugin;
    /** The minimal icon size. */
    protected int iconSize;

    // -- Constructors
    // -------------------------------------------------------------------------

    /**
     * Constructs a model with given message.
     * 
     * @param message
     *            the message
     */
    public MessageModel(final String message) {
        this(null, message, null, null, 0);
    }

    /**
     * Constructs a model with given title and message.
     * 
     * @param title
     *            the title
     * @param message
     *            the message
     */
    public MessageModel(final String title, final String message) {
        this(title, message, null, null, 0);
    }

    /**
     * Constructs a model with given title, message and icon.
     * 
     * @param title
     *            the title
     * @param message
     *            the message
     * @param iconPath
     *            the icon path
     * @param iconPlugin
     *            the icon plugin
     * @param iconSize
     *            the minimal icon size
     */
    public MessageModel(final String title, final String message, final String iconPlugin,
            final String iconPath, final int iconSize) {
        this.title = title;
        this.message = message;
        this.iconPath = iconPath;
        this.iconPlugin = iconPlugin;
        this.iconSize = iconSize;
    }

    // -- Getters
    // -------------------------------------------------------------------------

    /**
     * @return the title
     */
    public String getTitle() {
        return title != null && title.isEmpty() ? null : title;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message != null && message.isEmpty() ? null : message;
    }

    /**
     * @return if icon specified
     */
    public boolean hasIcon() {
        return iconPlugin != null && iconPath != null;
    }

    /**
     * @return the iconPlugin
     */
    public String getIconPlugin() {
        return iconPlugin == null || iconPlugin.isEmpty() ? null : iconPlugin;
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath == null || iconPath.isEmpty() ? null : iconPath;
    }

    /**
     * @return the iconSize
     */
    public int getIconSize() {
        return iconSize;
    }

    // -- toString
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        String newline = System.getProperty("line.separator");

        if (title != null) {
            text.append(title);
            text.append(newline);
        }
        if (message != null) {
            text.append(message);
        }

        return text.toString();
    }

}
