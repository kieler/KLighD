
package de.cau.cs.kieler.core.kgraph.text;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KGraphStandaloneSetup extends KGraphStandaloneSetupGenerated{

	public static void doSetup() {
		new KGraphStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

