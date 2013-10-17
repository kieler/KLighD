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
package de.cau.cs.kieler.klighd.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Test class testing certain methods in ModelingUtil.
 * 
 * @author chsch
 */
public class ModelingUtilTest {

    private static KNode eAllContainerTestObject;
    
    // CHECKSTYLEOFF Javadoc|MagicNumber
    
    @BeforeClass
    public static void initialize() {
        final KNode a = KimlUtil.createInitializedNode();
        final KNode b = KimlUtil.createInitializedNode();
        final KNode c = KimlUtil.createInitializedNode();
        
        a.getChildren().add(b);
        b.getChildren().add(c);
        
        eAllContainerTestObject = c;
    }
    
    @Test
    public void eAllContainerTest() {
        final List<?> containers = Lists.newArrayList(
                ModelingUtil.eAllContainers(eAllContainerTestObject));
        if (containers.size() != 2) {
            Assert.fail();
        }
    }

    @Test
    public void selfAndEAllContainerTest() {
        final List<?> containers = Lists.newArrayList(
                ModelingUtil.selfAndEAllContainers(eAllContainerTestObject));
        if (containers.size() != 3) {
            Assert.fail();
        }
    }
}
