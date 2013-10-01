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
package de.cau.cs.kieler.klighd.ui.wizard;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Hold some default values for the new klighd project wizard.
 * 
 * @author uru
 */
public final class KlighdWizardSetup {

    private KlighdWizardSetup() {
    }

    /** the source folder. */
    public static final String SRC_FOLDER = "src/";

    /** default project name shown in the wizard. */
    public static final String DEFAULT_PROJECT = "de.cau.cs.kieler.klighd.example";

    /** default transformation name shown in the wizard. */
    public static final String DEFAULT_TRANSFORMATION_NAME = "MyTransformation";

    /** required bundles for each klighd project. */
    public static final List<String> REQUIRED_BULDES = ImmutableList.of("com.google.guava",
            "com.google.inject", "org.eclipse.xtend.lib", "org.eclipse.xtext.xbase.lib",
            "de.cau.cs.kieler.klighd", "de.cau.cs.kieler.core.krendering",
            "de.cau.cs.kieler.core.krendering.extensions", "de.cau.cs.kieler.kiml");

}
