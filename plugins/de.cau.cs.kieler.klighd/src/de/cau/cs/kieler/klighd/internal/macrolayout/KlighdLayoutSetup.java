/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.internal.macrolayout;

import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.ILayoutSetup;
import org.eclipse.elk.graph.KGraphElement;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * Defines how KLighD connects to the Eclipse Layout Kernel.
 * 
 * @author cds
 */
public final class KlighdLayoutSetup implements ILayoutSetup {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        // KGraph instances are supported
        //   Tests here for KGraphElement rather than KNode since this method is invoked while
        //   populating the layout view, which provides also port, edge, and label properties.
        if (object instanceof KGraphElement) {
            return true;
        } else if (object instanceof ViewContext) {
            return true;
        } else if (object instanceof IViewer) {
            return true;
        } else if (object instanceof IDiagramWorkbenchPart) {
            return true;
        }
        
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Injector createInjector(final Module defaultModule) {
        return Guice.createInjector(Modules.override(defaultModule).with(new KlighdLayoutModule()));
    }
    
    /**
     * Guice module for the generic GMF connector.
     */
    public static class KlighdLayoutModule implements Module {

        @Override
        public void configure(final Binder binder) {
            binder.bind(IDiagramLayoutConnector.class).to(KlighdDiagramLayoutConnector.class);
            
            // MIGRATE Create a proper configuration store for KLighD
//            binder.bind(ILayoutConfigurationStore.Provider.class)
//            .to(GmfLayoutConfigurationStore.Provider.class);
        }
        
    }

}
