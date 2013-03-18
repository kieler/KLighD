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
package de.cau.cs.kieler.klighd.util;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * @author chsch
 */
public class LimitedKGraphContentAdapter extends EContentAdapter {
    
    private Class<? extends KGraphData> layoutDataClass = null;
    
    /**
     * Constructor.
     * 
     * @param theLayoutDataClass the data class this adapter is limited to
     */
    public LimitedKGraphContentAdapter(final Class<? extends KGraphData> theLayoutDataClass) {
        this.layoutDataClass = theLayoutDataClass;
    }

    /**
     * {@inheritDoc}
     */
    protected void setTarget(final EObject target) {
        if (target instanceof KNode) {
            // check if a shape layout is exists
            KNode node = (KNode) target;
            KGraphData layoutData = node.getData(this.layoutDataClass);

            if (layoutData != null) {
                // add the adapter to all contents of the shape layout
                basicSetTarget(target);
                Iterator<? extends Notifier> i = resolve() ? layoutData.eContents().iterator()
                        : ((InternalEList<? extends Notifier>) layoutData.eContents())
                                .basicIterator();
                Iterator<? extends Notifier> allElements = Iterators.concat(
                        Iterators.singletonIterator(layoutData), i);
                while (allElements.hasNext()) {
                    addAdapter(allElements.next());
                }
            }
        } else {
            super.setTarget(target);
        }
    }
}
