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
package de.cau.cs.kieler.klighd.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KCustom Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getClassName <em>Class Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getFigureObject <em>Figure Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKCustomRendering()
 * @model
 * @generated
 */
public interface KCustomRendering extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Name</em>' attribute.
     * @see #setClassName(String)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKCustomRendering_ClassName()
     * @model
     * @generated
     */
    String getClassName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getClassName <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Name</em>' attribute.
     * @see #getClassName()
     * @generated
     */
    void setClassName(String value);

    /**
     * Returns the value of the '<em><b>Bundle Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bundle Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bundle Name</em>' attribute.
     * @see #setBundleName(String)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKCustomRendering_BundleName()
     * @model
     * @generated
     */
    String getBundleName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bundle Name</em>' attribute.
     * @see #getBundleName()
     * @generated
     */
    void setBundleName(String value);

    /**
     * Returns the value of the '<em><b>Figure Object</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Figure Object</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Figure Object</em>' attribute.
     * @see #setFigureObject(Object)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKCustomRendering_FigureObject()
     * @model transient="true"
     * @generated
     */
    Object getFigureObject();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getFigureObject <em>Figure Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Figure Object</em>' attribute.
     * @see #getFigureObject()
     * @generated
     */
    void setFigureObject(Object value);

} // KCustomRendering
