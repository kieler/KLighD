/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
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
package de.cau.cs.kieler.klighd;

import java.util.Iterator;
import java.util.List;

import org.eclipse.elk.graph.properties.IProperty;

/**
 * Interface to register properties that cannot be serialized to json and should not be sent to the client.
 * 
 * @author mka
 *
 */
public interface IBlacklistedProperties extends Iterable<IProperty<?>>{
    
    /**
     * A list of all properties that should be skipped when copying properties to the SGraph.
     * @return The list of forbidden properties.
     */
    public List<IProperty<?>> getProperties();
    
    default Iterator<IProperty<?>> iterator() {
        return getProperties().iterator();
    }
    
}
