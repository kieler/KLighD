package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;

public class KlighdExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return KlighdUIPlugin.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return KlighdUIPlugin.getInstance().getInjector("bar");
	}
	
}