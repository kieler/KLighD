/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import de.cau.cs.kieler.core.kgraph.EMapPropertyHolder;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStyle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Adds additional StyleInformation to a rendering.
 * can be set to propagate to children to make redefinining styles unneccessary
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#getModifierId <em>Modifier Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#isSelection <em>Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle()
 * @model abstract="true"
 * @generated
 */
public interface KStyle extends EMapPropertyHolder {
    /**
     * Returns the value of the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Propagate To Children</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * defines whether child elements of the rendering should inherit this style
     * <!-- end-model-doc -->
     * @return the value of the '<em>Propagate To Children</em>' attribute.
     * @see #setPropagateToChildren(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_PropagateToChildren()
     * @model required="true"
     * @generated
     */
    boolean isPropagateToChildren();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Propagate To Children</em>' attribute.
     * @see #isPropagateToChildren()
     * @generated
     */
    void setPropagateToChildren(boolean value);

    /**
     * Returns the value of the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * id of the function that should be called when layout is finished to change this style
     * <!-- end-model-doc -->
     * @return the value of the '<em>Modifier Id</em>' attribute.
     * @see #setModifierId(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_ModifierId()
     * @model
     * @generated
     */
    String getModifierId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#getModifierId <em>Modifier Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Modifier Id</em>' attribute.
     * @see #getModifierId()
     * @generated
     */
    void setModifierId(String value);

    /**
     * Returns the value of the '<em><b>Selection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selection</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Selection</em>' attribute.
     * @see #setSelection(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_Selection()
     * @model default="false"
     * @generated
     */
    boolean isSelection();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#isSelection <em>Selection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Selection</em>' attribute.
     * @see #isSelection()
     * @generated
     */
    void setSelection(boolean value);

} // KStyle
