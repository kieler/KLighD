package de.cau.cs.kieler.klighd.piccolo.svg;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class KlighdPiccoloSVGPlugin implements BundleActivator {

    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.piccolo.svg";

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        KlighdPiccoloSVGPlugin.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        KlighdPiccoloSVGPlugin.context = null;
    }

}
