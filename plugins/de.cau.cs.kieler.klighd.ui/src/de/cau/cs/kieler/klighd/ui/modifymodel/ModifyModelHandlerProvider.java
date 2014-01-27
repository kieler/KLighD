/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.modifymodel;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.IModifyModelHandler;

/**
 * 
 * Class is used to get fitting IModifyModelHandlers that can handle the given IWorkbenchPart.
 * ExtensionPoint will be lazily read when its first called.
 * 
 * @author ckru
 *
 */
public final class ModifyModelHandlerProvider {

    /**
     * singleton instance.
     */
    private static ModifyModelHandlerProvider instance = new ModifyModelHandlerProvider();
    
    private List<IModifyModelHandler> handlers = null;
    
    private ModifyModelHandlerProvider() {
        
    }
    
    /**
     * 
     * @param part the workbenchpart the handlers should be compatible with.
     * @return the _first_ matching {@link IModifyModelHandler} for the passed parameters.
     */
    public IModifyModelHandler getFittingHandler(final IWorkbenchPart part) {

        if (handlers == null) {
            readHandlerExtensionPoint();
        }
        
        for (IModifyModelHandler handler : handlers) {
            if (handler.canHandle(part)) {
                return handler;
            }
        }

        return null;
    }
    
    private void readHandlerExtensionPoint() {
        handlers = Lists.newLinkedList();
        // read extension point
        IConfigurationElement[] elements =
                RegistryFactory.getRegistry().getConfigurationElementsFor(
                        "de.cau.cs.kieler.klighd.extensions");
        // get all ksbase handlers
        for (IConfigurationElement element : elements) {
            if (element.getName().equals("handler")) {
                try {
                    IModifyModelHandler handler =
                            (IModifyModelHandler) element.createExecutableExtension("class");
                    handlers.add(handler);
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * this.
     * @return Singleton instance 
     */
    public static ModifyModelHandlerProvider getInstance() {
        return instance;
    }
}
