/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import com.google.common.base.Function;

/**
 * Interface of proxy objects handed over to KLighD if the source model is protected by any kind of
 * transaction mechanism and, thus, cannot be access directly. Implementations of this interfaces
 * are supposed to be handed over to KLighD during diagram requests, e.g. via
 * <code>DiagramViewManager.createView(...)</code> (offered by ... kieler.klighd.ui), while creating
 * instances of {@link ViewContext}, or while updating them via {@link ViewContext#update(Object)}.<br>
 * <br>
 * If the source model of a diagram needs to be accessed (offered via
 * {@link ViewContext#getInputModel()}) and the returned object is an instance of this interface,
 * the required application logic must be wrapped by a {@link Function}. Calling than
 * {@link #execute(Function)} with the afore-created {@link Function} will the return the result of
 * the function.<br>
 * <br>
 * <b>Caution:</b> By contract {@link Function}'s result must not be an part/element of the actual
 * model itself!</b>
 * 
 * @author chsch
 */
public interface ISourceProxy {

    /**
     * Method must be implemented by application code to enable the access of the
     * source/business/domain model.<br>
     * In more detail, the provided <code>function</code> must be called with source model being
     * assigned to the function's parameter. The result returned by implementations of this method
     * must be the function's return value.
     *
     * @param <T>
     *            <code>function</code>'s return type
     * @param function
     *            a {@link Function} to be executed within the required transactional context,
     *            provided by KLighD (should never by <code>null</code>)
     * @return <code>function</code>'s return value
     */
    <T> T execute(final Function<Object, T> function);
}
