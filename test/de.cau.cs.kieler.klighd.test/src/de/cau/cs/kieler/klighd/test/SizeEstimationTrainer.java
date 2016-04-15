/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.test;

import java.util.Iterator;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.internal.LayoutOptionProxy;
import org.eclipse.elk.graph.KGraphData;
import org.eclipse.elk.graph.KGraphFactory;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.PersistentEntry;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Class provides a method to train {@link KNode KNodes} for the {@link SizeEstimationTest}, i.e.
 * attaches required size properties as required by the test, as well as to add an 'ignore' tag.
 * 
 * @author chsch
 */
public final class SizeEstimationTrainer {

    /**
     * Hidden default constructor.
     */
    private SizeEstimationTrainer() {
    }
    
    /**
     * Method marks the given KNode as ignored by size estimation tests.
     * 
     * @param node
     *            the KNode to be ignored
     */
    public static void ignore(final KNode node) {
        PersistentEntry pe = KGraphFactory.eINSTANCE.createPersistentEntry();
        pe.setKey(KlighdInternalProperties.KLIGHD_TESTING_IGNORE.getId());
        pe.setValue(Boolean.valueOf(true).toString());
        node.getData(KShapeLayout.class).getPersistentEntries().add(pe);
    }

    /**
     * Method trains the given KNode, i.e. attaches required size properties as required by the
     * {@link SizeEstimationTest}.
     * 
     * @param node
     *            the KNode to train.
     */
    public static void train(final KNode node) {

        boolean textsPresent = false;

        for (KText text : new Iterable<KText>() {
            public Iterator<KText> iterator() {
                return Iterators.concat(Iterators.transform(node.getData().iterator(),
                        new Function<KGraphData, Iterator<KText>>() {
                            public Iterator<KText> apply(final KGraphData data) {
                                return Iterators.filter(
                                        Iterators.concat(Iterators.singletonIterator(data),
                                                data.eAllContents()), KText.class);
                            }
                        }));
            }
        }) {
            textsPresent = true;

            LayoutOptionProxy.setProxyValue(text, KlighdInternalProperties.KLIGHD_TESTING_HEIGHT.getId(),
                    "0.0");
            LayoutOptionProxy.setProxyValue(text, KlighdInternalProperties.KLIGHD_TESTING_WIDTH.getId(),
                    "0.0");

            Bounds b = PlacementUtil.estimateTextSize(text);

            getPE(text, KlighdInternalProperties.KLIGHD_TESTING_HEIGHT.getId()).setValue(
                    Float.toString(b.getHeight()));
            getPE(text, KlighdInternalProperties.KLIGHD_TESTING_WIDTH.getId()).setValue(
                    Float.toString(b.getWidth()));
        }

        if (textsPresent) {
            KShapeLayout sl = node.getData(KShapeLayout.class);
            if (sl != null) {
                node.getData().remove(sl);
                ElkUtil.validate(node);
                sl = node.getData(KShapeLayout.class);
            }
            Bounds b = PlacementUtil.estimateSize(node);
            getPE(sl, KlighdInternalProperties.KLIGHD_TESTING_EXPECTED_HEIGHT.getId()).setValue(
                    Float.toString(b.getHeight()));
            getPE(sl, KlighdInternalProperties.KLIGHD_TESTING_EXPECTED_WIDTH.getId()).setValue(
                    Float.toString(b.getWidth()));
        }
    }

    private static PersistentEntry getPE(final KGraphData data, final String id) {
        PersistentEntry pe = Iterables.find(data.getPersistentEntries(),
                new Predicate<PersistentEntry>() {
                    public boolean apply(final PersistentEntry pe) {
                        return pe.getKey().equals(id);
                    }
                }, null);
        if (pe == null) {
            pe = KGraphFactory.eINSTANCE.createPersistentEntry();
            pe.setKey(id);
            data.getPersistentEntries().add(pe);
        }
        return pe;
    }
}
