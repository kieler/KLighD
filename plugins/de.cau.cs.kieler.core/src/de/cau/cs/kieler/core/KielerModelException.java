/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.core;

/**
 * An Exception that indicates problems with certain model elements. It carries
 * a reference to an arbitrary object that is supposed to be the causing model part.
 * 
 * @author haf
 * @kieler.rating 2010-01-19 proposed yellow proposed by haf
 */
public class KielerModelException extends Exception {

    /** the serial version UID. */
    private static final long serialVersionUID = 6763552087561256313L;
    /** the model object. */
    private Object causingModelObject;
    
    /**
     * @param message exception message
     * @param thecausingModelObject the model object that has caused the exception.
     *        Likely an EObject, if the metamodel is EMF.
     */
    public KielerModelException(final String message, final Object thecausingModelObject) {
        super(message);
        this.causingModelObject = thecausingModelObject;
    }

    /**
     * @param message exception message
     * @param thecausingModelObject the model object that has caused the exception.
     *        Likely an EObject, if the metamodel is EMF.
     * @param cause Causing Exception
     */
    public KielerModelException(final String message, final Object thecausingModelObject,
            final Exception cause) {
        super(message, cause);
        this.causingModelObject = thecausingModelObject;
    }
    
    /**
     * @return the model object that has caused this Exception. Likely to be an
     *         EMF EObject if the used metamodeling framework is EMF.
     */
    public Object getModelObject() {
        return causingModelObject;
    }
    
}
