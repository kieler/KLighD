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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * A simple update strategy for KGraph with KRendering which merges by copying the new model.
 *
 * @author mri
 * @author chsch
 */
public class SimpleUpdateStrategy implements IUpdateStrategy<KNode> {

    /** The id used at registration of the strategy in the plugin.xml. */
    public static final String ID = SimpleUpdateStrategy.class.getCanonicalName();
    
    /** The priority for this update strategy. */
    public static final int PRIORITY = 0;

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public KNode getInitialBaseModel(final ViewContext viewContext) {
        KNode baseModel = KimlUtil.createInitializedNode();
        return baseModel;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {

        List<KNode> newChildren = Lists.newArrayList(newModel.getChildren());
        List<KGraphData> newData = Lists.newArrayList(newModel.getData());
        newModel.getChildren().clear();
        newModel.getData().clear();
        
        // compose a collection of the baseModel's data that are to be replaced by those of newModel
        List<KGraphData> obsoleteData =
                Lists.newArrayList(Iterables.concat(
                        Iterables.filter(baseModel.getData(), KShapeLayout.class),
                        Iterables.filter(baseModel.getData(), KRendering.class),
                        Iterables.filter(baseModel.getData(), KRenderingLibrary.class)));

        // reset the clip (here the transform difference can still be computed properly)
        viewContext.getViewer().clip(baseModel);

        // ... and remove the diagram elements afterwards
        baseModel.getChildren().clear();
        baseModel.getData().removeAll(obsoleteData);
        baseModel.getData().addAll(newData);
        baseModel.getChildren().addAll(newChildren);

        // redirect any source view element association from the 'newModel' root to the 'baseModel'
        //  as 'baseModel' remains in place over the diagram's life time.
        viewContext.associateSourceTargetPair(viewContext.getSourceElement(newModel), baseModel);

    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return KNode.class;
    }

}
