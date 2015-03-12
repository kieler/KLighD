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
package de.cau.cs.kieler.klighd.syntheses;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A duplicating diagram synthesis performing à la {@link org.eclipse.emf.ecore.util.EcoreUtil#copy
 * EcoreUtil#copy} preserving the source-target-mapping.<br>
 * <br> 
 * Its aim is to decouple the model access performed e.g. by the model editor and those performed by
 * {@link de.cau.cs.kieler.klighd.IUpdateStrategy IUpdateStrategys} and KLighD
 * {@link de.cau.cs.kieler.klighd.IViewer IViewers}.<br>
 * <br>
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public class DuplicatingDiagramSynthesis extends AbstractDiagramSynthesis<KNode> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KNode transform(final KNode model) {

        // 3 lines are taken from EcoreUtil.copy()
        final Copier currentCopier = new Copier();
        final EObject result = currentCopier.copy(model);
        currentCopier.copyReferences();

        for (final Map.Entry<EObject, EObject> entry : currentCopier.entrySet()) {
            getUsedContext().associateSourceTargetPair(entry.getKey(), entry.getValue());
        }

        return (KNode) result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getInputDataType() {
        return KNode.class;
    }
}
