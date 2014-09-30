/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal;

import org.eclipse.core.resources.IResource;

import de.cau.cs.kieler.klighd.ViewContext;

/**
 * Interface of the KLighD-KIVi-connector class in <code>de.cau.cs.kieler.klighd.kivi</code>.<br>
 * Its aim is to decouple the concrete KIVi binding from classes of this plug-in in order to let
 * them work without KIVi.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public interface IKlighdTrigger {

    /** 
     * Enumeration of possible diagram handling statuses.
     */
    public enum Status {
        /** visualization created successfully. */
        CREATE_SUCCESS,
        
        /** failed to create visualization. */
        CREATE_FAILURE,
        
        /** visualization updated. */
        UPDATE,
        
        /** visualization closed. */
        CLOSE,
        
        /** nothing special. */
        NOTHING
    }

    /**
     * Fires a KLighD status state connected with a status, view, viewer and a view context
     * in which the trigger that lead to this trigger state fired.
     * 
     * @param status
     *            the status
     * @param viewContext
     *            the view context
     */
   void triggerStatus(final Status status, final ViewContext viewContext);

   /**
    * Fires a resource drop state with a given view identifier and a resource.
    * 
    * @param viewId
    *            the identifier of the view the resource has been dropped on
    * @param resource
    *            the resource which has been dropped on the view
    */
   void triggerDrop(final String viewId, final IResource resource);
   
   
    /**
     * Empty implementation of {@link IKlighdTrigger}, is used if fragment
     * <code>de.cau.cs.kieler.klighd.kivi</code> is not available.
     * 
     * @author chsch
     */
   public class NullTrigger implements IKlighdTrigger {

        /**
         * {@inheritDoc}
         */
        public void triggerStatus(final Status status, final ViewContext viewContext) {
            // do nothing
        }
        
        /**
         * {@inheritDoc}
         */
        public void triggerDrop(final String viewId, final IResource resource) {
            // do nothing            
        }
    }
}
