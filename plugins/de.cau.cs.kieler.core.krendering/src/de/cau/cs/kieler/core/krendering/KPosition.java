/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPosition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Used to set an absolute Position of a single point by defining x and y coordinates of this point relative to the parent. The position can be set with absolute values or relative to the parent dimensions
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPosition#getX <em>X</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPosition#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition()
 * @model
 * @generated
 */
public interface KPosition extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the x-position relative to the parent based on absolute coordinates or relative parent width
     * <!-- end-model-doc -->
     * @return the value of the '<em>X</em>' containment reference.
     * @see #setX(KXPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition_X()
     * @model containment="true" required="true"
     * @generated
     */
    KXPosition<?> getX();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPosition#getX <em>X</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' containment reference.
     * @see #getX()
     * @generated
     */
    void setX(KXPosition<?> value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the y-position relative to the parent based on absolute coordinates or relative parent height
     * <!-- end-model-doc -->
     * @return the value of the '<em>Y</em>' containment reference.
     * @see #setY(KYPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition_Y()
     * @model containment="true" required="true"
     * @generated
     */
    KYPosition<?> getY();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPosition#getY <em>Y</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' containment reference.
     * @see #getY()
     * @generated
     */
    void setY(KYPosition<?> value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Checks the equalilty of the <code>x</code> and <code>y</code> components of <code>this</code> KPostion instance and the provided <code>other</code> one.
     * Returns false if <code>other</code> is not a KPosition.<br>
     * {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#equals(KPosition, Object) KRenderingUtil.equals(KPosition, Object)}.<br>
     * <br>
     * Hint: Equal KPositions, however, do not imply indentical points in the figure as they may have different parents!
     * 
     * @return <code>true</code> if <code>other</code> is a KPosition and its <code>x</code> and <code>y</code> components are equal to those of <code>this</code> instance, <code>false</code> otherwise
     * 
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.core.krendering.KRenderingUtil.equals(this,other);'"
     * @generated
     */
    boolean equals(Object other);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring <code>this</code> KPosition instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setPositions(KPosition, KXPosition, KYPosition) KRenderingUtil.setPositions(KPosition, KXPosition, KYPosition)}.
     * @param x the horizontal position component
     * @param y the vertical position component
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.core.krendering.KRenderingUtil.setPositions(this, x, y);'"
     * @generated
     */
    KPosition setPositions(KXPosition<?> x, KYPosition<?> y);

} // KPosition
