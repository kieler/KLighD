// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.printing.options;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class is used as the model in the MVC infrastructure required for databindings used with the
 * KlighD dialog.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
abstract class PrintModelElement {
    private final PropertyChangeSupport bean = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        bean.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener) {
        bean.addPropertyChangeListener(propertyName, listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return bean.getPropertyChangeListeners();
    }

    public PropertyChangeListener[] getPropertyChangeListeners(final String propertyName) {
        return bean.getPropertyChangeListeners(propertyName);
    }

    public boolean hasListeners(final String propertyName) {
        return bean.hasListeners(propertyName);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        bean.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener) {
        bean.removePropertyChangeListener(propertyName, listener);
    }

    protected void firePropertyChange(final String propertyName, final Object oldValue,
            final Object newValue) {
        bean.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected void firePropertyChange(final String propertyName, final int oldValue,
            final int newValue) {
        bean.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected void firePropertyChange(final String propertyName, final boolean oldValue,
            final boolean newValue) {
        bean.firePropertyChange(propertyName, oldValue, newValue);
    }
}
