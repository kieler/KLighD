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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.KGraphStandaloneSetup;
import de.cau.cs.kieler.core.test.runners.ModelCollectionTestRunner;
import de.cau.cs.kieler.core.test.runners.ModelCollectionTestRunner.BundleId;
import de.cau.cs.kieler.core.test.runners.ModelCollectionTestRunner.ModelFilter;
import de.cau.cs.kieler.core.test.runners.ModelCollectionTestRunner.ModelPath;

/**
 * @author chsch
 */
@RunWith(ModelCollectionTestRunner.class)
public class SizeEstimationTest {
    

    /**
     * Provides the id of the bundle containing the test models.
     * 
     * @return the bundle id
     */
    @BundleId
    public static String getBundleId() {
        return "de.cau.cs.kieler.klighd.test";
    }
    
    /**
     * Provides the path to the models within the bundle indicated in {@link #getBundleId()}.
     * 
     * @return the model path
     */
    @ModelPath
    public static String getModelPath() {
        return "sizeEstimationTests/";
    }
    
    /**
     * Provides a file pattern to filter the test models. See
     * {@link org.osgi.framework.Bundle#findEntries(String, String, boolean)} for details on valid
     * patters.
     * 
     * @return the model filter pattern
     */
    @ModelFilter
    public static String getModelFilter() {
        return "*.kgt";
    }
    
    /**
     * Provides a {@link ResourceSet} in order to load the models properly.
     * 
     * @return the required {@link ResourceSet}
     */
    @ModelCollectionTestRunner.ResourceSet
    public static ResourceSet getResourceSet() {
        return KGraphStandaloneSetup.doSetup().getInstance(XtextResourceSet.class);
    }
    
    /**
     * The test method.
     * 
     * @param model the test input model
     */
    @Test
    public void test(final EObject model) {
        System.out.println(((KNode) model).getData().get(0));
    }
    
    /**
     * The test method.
     * 
     * @param model the test input model
     */
    @Test
    public void test2(final EObject model) {
        System.out.println(((KNode) model).getData().get(0));
    }
    
}
