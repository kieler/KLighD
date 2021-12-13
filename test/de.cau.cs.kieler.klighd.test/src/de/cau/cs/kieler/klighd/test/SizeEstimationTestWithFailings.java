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
package de.cau.cs.kieler.klighd.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.test.runners.ModelCollectionTestRunner;
import de.cau.cs.kieler.klighd.test.runners.ModelCollectionTestRunner.BundleId;
import de.cau.cs.kieler.klighd.test.runners.ModelCollectionTestRunner.ModelFilter;
import de.cau.cs.kieler.klighd.test.runners.ModelCollectionTestRunner.ModelPath;
import de.cau.cs.kieler.klighd.test.runners.ModelCollectionTestRunner.StopOnFailure;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
