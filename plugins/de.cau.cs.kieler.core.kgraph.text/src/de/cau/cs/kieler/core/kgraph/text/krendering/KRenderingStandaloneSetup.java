
package de.cau.cs.kieler.core.kgraph.text.krendering;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KRenderingStandaloneSetup extends KRenderingStandaloneSetupGenerated{

	public static void doSetup() {
		new KRenderingStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

