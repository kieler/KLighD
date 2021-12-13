/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
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
package de.cau.cs.kieler.klighd.ui.wizard;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Hold some default values for the new KLighD project wizard.
 * 
 * @author uru
 * @author chsch
 */
public final class KlighdWizardSetup {

    private KlighdWizardSetup() {
    }

    /** the source folder. */
    public static final String SRC_FOLDER = "src/";

    /** the settings folder. */
    public static final String SETTINGS_FOLDER = ".settings";

    /** the settings folder. */
    @SuppressWarnings("restriction")
    public static final String JDT_PREFS_FILE =
            org.eclipse.jdt.internal.core.JavaProject.JAVA_CORE_PREFS_FILE;
            // "org.eclipse.jdt.core.prefs";

    /** default project name shown in the wizard. */
    public static final String DEFAULT_PROJECT = "my.klighd.diagrams.example";

    /** default transformation name shown in the wizard. */
    public static final String DEFAULT_TRANSFORMATION_NAME = "DiagramSynthesis";

    /** required bundles for each klighd project. */
    public static final List<String> REQUIRED_BUNDLES = ImmutableList.of(
            "javax.inject", 
            "com.google.guava", 
            "com.google.inject", 
            "org.eclipse.xtext.xbase.lib",
            "org.eclipse.elk.core",
            "org.eclipse.elk.alg.layered",
            "org.eclipse.elk.alg.graphviz.layouter;resolution:=optional",
            "de.cau.cs.kieler.klighd",
            "de.cau.cs.kieler.klighd.krendering",
            "de.cau.cs.kieler.klighd.krendering.extensions",
            "de.cau.cs.kieler.klighd.piccolo",
            "de.cau.cs.kieler.klighd.ui", 
            "de.cau.cs.kieler.klighd.ui.contrib3x",
            "de.cau.cs.kieler.klighd.ui.view;resolution:=optional",
            "org.eclipse.ui", // required for the menu contribution extension points
            "org.eclipse.ui.ide.application", 
            "org.eclipse.ui.navigator.resources",
            "org.eclipse.ui.views.log", 
            "org.eclipse.core.resources", 
            "org.eclipse.emf",
            "org.eclipse.xtext.ui;resolution:=optional"); // required for accessing XtextEditors' models

}
