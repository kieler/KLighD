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
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KImage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KImageImpl#getBundleName <em>Bundle Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KImageImpl#getImagePath <em>Image Path</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KImageImpl#getImageObject <em>Image Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KImageImpl extends KContainerRenderingImpl implements KImage {
    /**
     * The default value of the '{@link #getBundleName() <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBundleName()
     * @generated
     * @ordered
     */
    protected static final String BUNDLE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBundleName() <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBundleName()
     * @generated
     * @ordered
     */
    protected String bundleName = BUNDLE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImagePath()
     * @generated
     * @ordered
     */
    protected static final String IMAGE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImagePath() <em>Image Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImagePath()
     * @generated
     * @ordered
     */
    protected String imagePath = IMAGE_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getImageObject() <em>Image Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImageObject()
     * @generated
     * @ordered
     */
    protected static final Object IMAGE_OBJECT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImageObject() <em>Image Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImageObject()
     * @generated
     * @ordered
     */
    protected Object imageObject = IMAGE_OBJECT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KImageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KIMAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBundleName() {
        return bundleName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBundleName(String newBundleName) {
        String oldBundleName = bundleName;
        bundleName = newBundleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KIMAGE__BUNDLE_NAME, oldBundleName, bundleName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImagePath(String newImagePath) {
        String oldImagePath = imagePath;
        imagePath = newImagePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KIMAGE__IMAGE_PATH, oldImagePath, imagePath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getImageObject() {
        return imageObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImageObject(Object newImageObject) {
        Object oldImageObject = imageObject;
        imageObject = newImageObject;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KIMAGE__IMAGE_OBJECT, oldImageObject, imageObject));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KIMAGE__BUNDLE_NAME:
                return getBundleName();
            case KRenderingPackage.KIMAGE__IMAGE_PATH:
                return getImagePath();
            case KRenderingPackage.KIMAGE__IMAGE_OBJECT:
                return getImageObject();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KIMAGE__BUNDLE_NAME:
                setBundleName((String)newValue);
                return;
            case KRenderingPackage.KIMAGE__IMAGE_PATH:
                setImagePath((String)newValue);
                return;
            case KRenderingPackage.KIMAGE__IMAGE_OBJECT:
                setImageObject(newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KIMAGE__BUNDLE_NAME:
                setBundleName(BUNDLE_NAME_EDEFAULT);
                return;
            case KRenderingPackage.KIMAGE__IMAGE_PATH:
                setImagePath(IMAGE_PATH_EDEFAULT);
                return;
            case KRenderingPackage.KIMAGE__IMAGE_OBJECT:
                setImageObject(IMAGE_OBJECT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KIMAGE__BUNDLE_NAME:
                return BUNDLE_NAME_EDEFAULT == null ? bundleName != null : !BUNDLE_NAME_EDEFAULT.equals(bundleName);
            case KRenderingPackage.KIMAGE__IMAGE_PATH:
                return IMAGE_PATH_EDEFAULT == null ? imagePath != null : !IMAGE_PATH_EDEFAULT.equals(imagePath);
            case KRenderingPackage.KIMAGE__IMAGE_OBJECT:
                return IMAGE_OBJECT_EDEFAULT == null ? imageObject != null : !IMAGE_OBJECT_EDEFAULT.equals(imageObject);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (bundleName: ");
        result.append(bundleName);
        result.append(", imagePath: ");
        result.append(imagePath);
        result.append(", imageObject: ");
        result.append(imageObject);
        result.append(')');
        return result.toString();
    }

} //KImageImpl
