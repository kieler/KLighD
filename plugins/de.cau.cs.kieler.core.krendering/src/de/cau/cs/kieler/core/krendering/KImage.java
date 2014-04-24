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
 * <!-- begin-model-doc -->
 * Use an image instead of defining the renderings completely by yourself
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getBundleName <em>Bundle Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getImagePath <em>Image Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getImageObject <em>Image Object</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KImage#getClipShape <em>Clip Shape</em>}</li>
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
     * <!-- begin-model-doc -->
     * Name of the bundle where the image can be found. Note that the imagePath is relative to this bundle. 
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bundle Name</em>' attribute.
     * @see #setBundleName(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_BundleName()
     * @model
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
     * <!-- begin-model-doc -->
     * the path to the image on the filesystem relative to the bundleName (and its according position)
     * <!-- end-model-doc -->
     * @return the value of the '<em>Image Path</em>' attribute.
     * @see #setImagePath(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_ImagePath()
     * @model
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

    /**
     * Returns the value of the '<em><b>Image Object</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Object</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the image to be used as JavaObject
     * <!-- end-model-doc -->
     * @return the value of the '<em>Image Object</em>' attribute.
     * @see #setImageObject(Object)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_ImageObject()
     * @model transient="true"
     * @generated
     */
    Object getImageObject();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KImage#getImageObject <em>Image Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Image Object</em>' attribute.
     * @see #getImageObject()
     * @generated
     */
    void setImageObject(Object value);

    /**
     * Returns the value of the '<em><b>Clip Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This {@link KRendering} will determine the clip applied to the image. It will not be drawn!
     * Valid {@link KRendering KRenderings} are {@link KRectangle KRectangles}, {@link KEllipse KEllipses},
     * {@link KPolygon KPolygons}, and {@link KRenderingRef KRenderingRefs} pointing to a rendering of the former types.
     * Other values are ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Clip Shape</em>' containment reference.
     * @see #setClipShape(KRendering)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKImage_ClipShape()
     * @model containment="true"
     * @generated
     */
    KRendering getClipShape();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KImage#getClipShape <em>Clip Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Clip Shape</em>' containment reference.
     * @see #getClipShape()
     * @generated
     */
    void setClipShape(KRendering value);

} // KImage
