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
package de.cau.cs.kieler.core.kgraph.text.validation;

import org.eclipse.xtext.validation.Check;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KText;

/**
 * 
 * @author chsch
 */
public class KGraphJavaValidator extends AbstractKGraphJavaValidator {

    /**
     * Constant for relating marker and fix.
     */
    public static final String TRAIN_KNODE_INFO = "train.knode.info";
    
    /**
     * 
     * @param node the node to attach the marker
     */
    @Check
    public void addTrainTest(final KNode node) {
        try {
            Class.forName("de.cau.cs.kieler.klighd.test.SizeEstimationTrainer");
            KRendering rendering  = node.getData(KRendering.class);
            boolean valid = rendering != null
                    && Iterators.filter(rendering.eAllContents(), KText.class).hasNext();
            if (valid) {
                info("'Train KNode' marker", KGraphPackage.eINSTANCE.getKNode_Parent(),
                        TRAIN_KNODE_INFO);
            }
        } catch (ClassNotFoundException e) {
            // in case the size estimation trainer is not available don't add the marker
        }
    }

}
