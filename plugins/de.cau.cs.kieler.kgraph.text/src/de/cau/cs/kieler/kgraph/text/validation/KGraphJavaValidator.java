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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.validation;

import java.util.Iterator;

import org.eclipse.xtext.validation.Check;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KText;

/**
 * An {@link org.eclipse.emf.ecore.EValidator EValidator} providing marker annotations in the
 * textual KGraph/KRendering editor.<br>
 * <br>
 * Currently, it provides 'train' info markers for KNodes whose KRendering contains KTexts (as
 * KTexts are the only atomic elements that require width and height), 'ignore' info markers for
 * those without any KText, and 'ignored' info markers for those that are ignored.
 * 
 * @author chsch
 */
public class KGraphJavaValidator extends KGraphBasicStructureValidator {

    /**
     * Constant for relating marker and fix.
     */
    public static final String TRAIN_KNODE_INFO = "train.knode.info";
    
    /**
     * Constant for relating marker and fix.
     */
    public static final String IGNORE_KNODE_INFO = "ignore.knode.info";
    
    /**
     * Internal flag to remember the trainer's availability.
     */
    private static boolean active = false;
    
    { // static part executed after the class has been loaded
        try {
            Class.forName("de.cau.cs.kieler.klighd.test.SizeEstimationTrainer");
            active = true;
        } catch (ClassNotFoundException e) {
            // in case the size estimation trainer is not available don't add the marker
        }
    }
    
    /**
     * The check attaching the 'train' info markers for KNodes whose KRendering contains KTexts (as
     * KTexts are the only atomic elements that require width and height.
     * 
     * @param node
     *            the node to attach the marker
     */
    @Check
    public void addTrainTestMarker(final KNode node) {
        if (!active) {
            return;
        }

        // ignore the root node
        if (node.eContainer() == null) {
            return;
        }
        
        Iterator<KRendering> renderings = Iterators.concat(Iterators.transform(
                // for each KRendering in the node's 'data' field ...
                Iterators.filter(node.getData().iterator(), KRendering.class),
                // ... construct an iterator providing the KRendering and all its children and
                // children's children of type KRendering
                new Function<KRendering, Iterator<KRendering>>() {
                    public Iterator<KRendering> apply(final KRendering r) {
                        return Iterators.concat(Iterators.singletonIterator(r),
                                Iterators.filter(r.eAllContents(), KRendering.class));
                    }
                })
                // the resulting Iterator of Iterators is finally concatenated to a flat Iterator
        );
        
        // check whether that Iterator of KRenderings finds a KText element ...
        boolean valid = Iterators.filter(renderings, KText.class).hasNext();
        
        if (valid) {
            // if so provide the 'train' info marker 
            info("'Train KNode' marker", KGraphPackage.eINSTANCE.getKNode_Parent(),
                    TRAIN_KNODE_INFO);
        } else {
            // otherwise provide the 'ignore' info marker
            boolean ignored = Iterables.any(node.getPersistentEntries(),
                    new Predicate<PersistentEntry>() {
                        public boolean apply(final PersistentEntry pe) {
                            return pe.getKey().equals("klighd.testing.ignore");
                        }
                    });
            
            if (!ignored) {
                info("'Ignore KNode' marker", KGraphPackage.eINSTANCE.getKNode_Parent(),
                        IGNORE_KNODE_INFO);
            } else {
                info("KNode is ignored", KGraphPackage.eINSTANCE.getKNode_Parent());
            }
        }
    }
}
