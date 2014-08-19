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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface for classes implementing an update strategy for a specific model class. These
 * update strategies are used for the purpose of updating a view model (KGraph/KRendering) instance
 * that is currently displayed according to a newer version of the view model.
 * 
 * @author mri
 * @author chsch
 */
public interface IUpdateStrategy {

    /**
     * Returns the priority for this update strategy. Higher value means higher priority.
     * 
     * @return the priority
     */
    int getPriority();

    /**
     * This method is called by KLighD for deciding whether a new diagram view model is required for
     * updating the diagram as intended. It is supposed return <code>true</code> in the common case.
     * However, specialized implementations dedicated to certain forms of diagram update (e.g. just
     * removal of elements) may return <code>false</code> if no view model re-synthesis is required.
     * 
     * @param viewContext
     *            the current {@link ViewContext} that may be asked during coming to the decision
     * @return <code>true</code> if a new view model shall be synthesized (common case),
     *         <code>false</code> if the current one shall be kept
     */
    boolean requiresDiagramSynthesisReRun(ViewContext viewContext);

    /**
     * Performs an update of the base view model (the view model that is currently being displayed)
     * by equalizing it the (updated) <code>newModel</code>. Implementations of this method may
     * assume, that <code>newModel</code> has been synthesized by a transformation or is at least a
     * deep copy of a model maintained by an editor, e.g. an Xtext editor.<br>
     * Hence, the update strategy need not fear any changes of 'newModel' by any other tooling.
     * 
     * @param baseModel
     *            the base model
     * @param newModel
     *            the new model
     * @param viewContext
     *            the view context
     */
    void update(KNode baseModel, KNode newModel, ViewContext viewContext);
}
