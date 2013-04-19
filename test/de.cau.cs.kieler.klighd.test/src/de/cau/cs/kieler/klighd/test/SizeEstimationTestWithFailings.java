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
package de.cau.cs.kieler.klighd.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.BundleId;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelFilter;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.ModelPath;
import de.cau.cs.kieler.pragmatics.test.common.runners.ModelCollectionTestRunner.StopOnFailure;

/**
 * This test is pretty much the same as {@link SizeEstimationTest} except the inclusion
 * of the failing test cases.
 *  
 * @author chsch, akoc
 */
@RunWith(ModelCollectionTestRunner.class)
@BundleId("de.cau.cs.kieler.klighd.test")
@ModelPath("sizeEstimationTests/**")
@ModelFilter("*.kgt")
public class SizeEstimationTestWithFailings extends SizeEstimationTest {

    /**
     * This test acts as a precondition test, i.e. checks the presence of the required properties
     * for each {@link KNode} found in the model.
     * 
     * @param node the test input model
     */
    @Test
    @StopOnFailure
    public void sizeDataPresentTest(final KNode node) {
        super.sizeDataPresentTest(node);
    }
    
    /**
     * This test is the actual size estimation calculation test.
     * 
     * @param node the test input model
     */
    @Test
    public void sizeEstimationTest(final KNode node) {
        super.sizeEstimationTest(node);
    }
    
    /**
     * This test checks the stability of the result by simply running the estimation a second time.
     * 
     * @param node the test input model
     */
    @Test
    public void sizeEstimationTest2nd(final KNode node) {
        super.sizeEstimationTest2nd(node);
    }
}
