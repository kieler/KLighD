/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

/**
 * The class representing an object dependency.
 * 
 * @author mri
 * 
 * @param <T>
 *            a comparable identifier type (commonly String or Integer)
 */
public class Dependency<T extends Comparable<T>> {

    /** the dependency identifier. */
    private T dependency;
    /** is the dependency weak? */
    private boolean weak;

    /**
     * Constructs a dependency.
     * 
     * @param id
     *            the dependency identifier
     * @param weakDep
     *            true if the depedency is weak
     */
    public Dependency(final T id, final boolean weakDep) {
        dependency = id;
        weak = weakDep;
    }

    /**
     * Constructs a strong dependency.
     * 
     * @param id
     *            the dependency identifier
     */
    public Dependency(final T id) {
        dependency = id;
        weak = false;
    }

    /**
     * Returns the dependency identifier.
     * 
     * @return the depdency identifier.
     */
    public T getID() {
        return dependency;
    }

    /**
     * Returns whether the dependency is weak or not.
     * 
     * @return true if the depdendency is weak
     */
    public boolean isWeak() {
        return weak;
    }

    /**
     * Returns whether the depdency is strong or not.
     * 
     * @return true if the dependecy is strong
     */
    public boolean isStrong() {
        return !weak;
    }
}
