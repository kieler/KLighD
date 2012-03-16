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
package de.cau.cs.kieler.klighd.krendering;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The update strategy for KGraph models with attached KRendering data.
 * 
 * @author mri
 */
public class UpdateStrategy implements IUpdateStrategy<KNode> {

    /**
     * {@inheritDoc}
     */
    public KNode getInitialBaseModel(final ViewContext viewContext) {
        return KimlUtil.createInitializedNode();
    }

    /**
     * {@inheritDoc}
     */
    public void update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {
        // TODO only a temporary solution
        // FIXME not working yet

        // reset the base model
        baseModel.getChildren().clear();
        baseModel.getData().clear();
        
        // make a copy of the new model
        KNode copyModel = EcoreUtil.copy(newModel);
        
        // merge the copy into the base model
        List<KGraphData> copyData = Lists.newLinkedList();
        copyData.addAll(copyModel.getData());
        copyModel.getData().clear();
        List<KNode> copyNodes = Lists.newLinkedList();
        copyNodes.addAll(copyModel.getChildren());
        copyModel.getChildren().clear();
        baseModel.getData().addAll(copyData);
        baseModel.getChildren().addAll(copyNodes);
     
//        try {
//            MatchModel match =
//                    MatchService.doMatch(baseModel, newModel, Collections.<String, Object> emptyMap());
//        } catch (InterruptedException e) {
//        }
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return KNode.class;
    }

}
