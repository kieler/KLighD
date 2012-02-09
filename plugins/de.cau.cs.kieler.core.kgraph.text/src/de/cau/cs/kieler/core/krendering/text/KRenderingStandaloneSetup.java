
package de.cau.cs.kieler.core.krendering.text;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KRenderingStandaloneSetup extends KRenderingStandaloneSetupGenerated{

	public static void doSetup() {
		new KRenderingStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

