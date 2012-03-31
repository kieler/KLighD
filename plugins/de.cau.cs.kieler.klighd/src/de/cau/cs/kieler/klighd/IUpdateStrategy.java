/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

/**
 * The interface for classes implementing an update strategy for a specific model class.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the model class
 */
public interface IUpdateStrategy<T> {

    /**
     * Returns the priority for this update strategy. Higher value means higher priority.
     * 
     * @return the priority
     */
    int getPriority();
    
    /**
     * Returns the initial base model for the incremental update process.
     * 
     * @param viewContext
     *            the view context
     * @return the initial base model
     */
    T getInitialBaseModel(ViewContext viewContext);

    /**
     * Performs an incremental update of the base model using the new iteration of the model.
     * 
     * @param baseModel
     *            the base model
     * @param newModel
     *            the new model
     * @param viewContext
     *            the view context
     */
    void update(T baseModel, T newModel, ViewContext viewContext);

    /**
     * Returns the class of the models supported by this update strategy.
     * 
     * @return the class of the supported models
     */
    Class<?> getModelClass();

}
