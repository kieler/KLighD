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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KImage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getBundleName <em>Bundle Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getImagePath <em>Image Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage()
 * @model
 * @generated
 */
public interface KImage extends KContainerRendering {
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
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_BundleName()
     * @model required="true"
     * @generated
     */
    String getBundleName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KImage#getBundleName <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bundle Name</em>' attribute.
     * @see #getBundleName()
     * @generated
     */
    void setBundleName(String value);

    /**
     * Returns the value of the '<em><b>Image Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Image Path</em>' attribute.
     * @see #setImagePath(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_ImagePath()
     * @model required="true"
     * @generated
     */
    String getImagePath();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KImage#getImagePath <em>Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Image Path</em>' attribute.
     * @see #getImagePath()
     * @generated
     */
    void setImagePath(String value);

} // KImage
