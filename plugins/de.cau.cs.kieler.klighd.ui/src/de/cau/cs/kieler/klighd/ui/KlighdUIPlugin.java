package de.cau.cs.kieler.klighd.ui;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Maps;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import de.cau.cs.kieler.klighd.ui.wizard.KlighdNewProjectWizard;
import de.cau.cs.kieler.klighd.ui.wizard.KlighdProjectCreator;

/**
 * The activator class controls the plug-in life cycle
 */
public class KlighdUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.ui"; //$NON-NLS-1$

	// The shared instance
	private static KlighdUIPlugin plugin;

	/**
	 * The constructor
	 */
	public KlighdUIPlugin() {
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static KlighdUIPlugin getDefault() {
		return plugin;
	}

	private static final Logger logger = Logger.getLogger(KlighdUIPlugin.class);

	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps
			.<String, Injector> newHashMapWithExpectedSize(1));

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		injectors.clear();
		plugin = null;
		super.stop(context);
	}

	public static KlighdUIPlugin getInstance() {
		return plugin;
	}

	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}
	
	
	public Provider<IWorkspace> provideIWorkspace() {
		return new Provider<IWorkspace>() {
			public IWorkspace get() {
				return ResourcesPlugin.getWorkspace();
			}
		};
	}
	
	public void configureIWorkbench(Binder binder) {
		if (PlatformUI.isWorkbenchRunning()) {
			binder.bind(IWorkbench.class).toProvider(new Provider<IWorkbench>() {
				public IWorkbench get() {
					return (PlatformUI.isWorkbenchRunning()) ? PlatformUI.getWorkbench() : null;
				}
			});
		}
	}

	protected Injector createInjector(String language) {
		try {
			Module runtimeModule = new AbstractGenericModule() {
				@Override
				public void configure(Binder binder) {
					super.configure(binder);

					binder.bind(IProjectCreator.class).to(
							KlighdProjectCreator.class);
					binder.bind(IWorkspace.class).toProvider(new Provider<IWorkspace>() {
						public IWorkspace get() {
							return ResourcesPlugin.getWorkspace();
						}
					});
				}

			};
			Module sharedStateModule = getSharedStateModule();
			Module mergedModule = Modules2.mixin(runtimeModule,
					sharedStateModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for "
					+ language, e);
		}
	}

	protected Module getSharedStateModule() {
		return new SharedStateModule();
	}
}
