
package de.cau.cs.kieler.kiml.klayoutdata.text;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KLayoutDataStandaloneSetup extends KLayoutDataStandaloneSetupGenerated{

	public static void doSetup() {
		new KLayoutDataStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

