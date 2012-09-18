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
 * These update strategies are used for the purpose of updating a view model (KGraph)
 * that is currently displayed according to a newer version of the view model.
 * 
 * @author mri, chsch
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

    // SUPPRESS CHECKSTYLE NEXT 10 LineLength
    /**
     * Performs an incremental update of the base view model, the view model that is currently being
     * displayed, using another (updated) view model. Implementations of this method may assume,
     * that 'newModel' has been synthesized by a transformation or is at least a deep copy of a
     * model maintained by an editor, e.g. an Xtext editor. (see line 20ff of
     * {@link TransformationsGraph#configureViewContext(ViewContext, java.util.List, Object, IUpdateStrategy)}
     * <br>
     * Hence, the update strategy need not care on any side effects caused by massive model changes
     * performed by the editor.
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
