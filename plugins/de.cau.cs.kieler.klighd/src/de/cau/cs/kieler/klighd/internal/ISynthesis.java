/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal;

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * Internal interface of diagram synthesis implementations. It has been introduced to simplify the
 * internal handling of diagram synthesis implementations, e.g. avoid annoying type casts in order
 * to satisfy the constraints imposed by the type parameter of
 * {@link de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis AbstractDiagramSynthesis}.<br>
 * <br>
 * Clients shall not implement this interface but extend
 * {@link de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis AbstractDiagramSynthesis} as
 * required by the extension point <code>de.cau.cs.kieler.klighd.diagramSyntheses</code> and in
 * order to benefit from the convenience features of that class!
 * 
 * @author chsch
 */
public interface ISynthesis {

    /**
     * Returns the class of the source model.
     * 
     * @return the class of the source model
     */
    Class<?> getSourceClass();

    /**
     * Method for checking whether <code>this</code> diagram synthesis implementation supports
     * the given <code>model</code>.
     * 
     * @param model
     *            the model to test
     * @param viewContext
     *            the related {@link ViewContext}
     * @return <code>true</code> if <code>this</code> diagram synthesis supports <code>model</code>,
     *         and <code>false</code> otherwise
     */
    boolean supports(Object model, ViewContext viewContext);
    
    /**
     * Translates of the source <code>model</code> into an instance of KGraph/KRendering view model
     * format.
     * 
     * @param model
     *            the source model
     * @param viewContext
     *            the related {@link ViewContext}
     * @return the desired view model
     */
    KNode transform(Object model, ViewContext viewContext);

    /**
     * Contributes the set of {@link SynthesisOption SynthesisOptions} provided
     * in the UI in order to allow users to influence steer the diagram synthesis.
     * 
     * @return the set of {@link SynthesisOption SynthesisOptions}
     */
    List<SynthesisOption> getDisplayedSynthesisOptions();


    /**
     * Returns a list of layout options that will be displayed in the diagram side bar and that are
     * directly manipulatable by the user. For each layout option a, possibly restricted, set of
     * allowed input values can be specified.
     * 
     * @return a {@link List} of {@link Pair Pairs} where each pair specifies a recommended layout
     * option.
     */
    List<Pair<IProperty<?>, List<?>>> getDisplayedLayoutOptions();
}
