package de.cau.cs.kieler.klighd.piccolo.svg;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.klighd.piccolo.svg.browsing.BrowsingSVGServer;

public class KlighdPiccoloSVGPlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		KlighdPiccoloSVGPlugin.context = bundleContext;
		
//		BrowsingSVGServer.getInstance();
//		SVGServer.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		KlighdPiccoloSVGPlugin.context = null;
	}

}
