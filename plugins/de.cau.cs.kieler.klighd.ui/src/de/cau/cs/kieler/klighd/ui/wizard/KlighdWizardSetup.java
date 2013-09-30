package de.cau.cs.kieler.klighd.ui.wizard;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class KlighdWizardSetup {

	private KlighdWizardSetup() {
	}
	
	public static final String SRC_FOLDER = "src/";
	
	public static final String DEFAULT_PROJECT = "de.cau.cs.kieler.klighd.example";
	
	public static final String DEFAULT_TRANSFORMATION_NAME = "MyTransformation";

	public static final List<String> REQUIRED_BULDES = ImmutableList.of(
			"com.google.guava", "com.google.inject", "org.eclipse.xtend.lib",
			"org.eclipse.xtext.xbase.lib", "de.cau.cs.kieler.klighd",
			"de.cau.cs.kieler.core.krendering",
			"de.cau.cs.kieler.core.krendering.extensions",
			"de.cau.cs.kieler.kiml");

}
