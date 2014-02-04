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

import de.cau.cs.kieler.core.krendering.Colors;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KColoring;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KColoring</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KColoringImpl#getColor <em>Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KColoringImpl#getAlpha <em>Alpha</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KColoringImpl#getTargetColor <em>Target Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KColoringImpl#getTargetAlpha <em>Target Alpha</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KColoringImpl#getGradientAngle <em>Gradient Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KColoringImpl<T extends KColoring<T>> extends KStyleImpl implements KColoring<T> {
    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected KColor color;

    /**
     * The default value of the '{@link #getAlpha() <em>Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlpha()
     * @generated
     * @ordered
     */
    protected static final int ALPHA_EDEFAULT = 255;

    /**
     * The cached value of the '{@link #getAlpha() <em>Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlpha()
     * @generated
     * @ordered
     */
    protected int alpha = ALPHA_EDEFAULT;

    /**
     * The cached value of the '{@link #getTargetColor() <em>Target Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetColor()
     * @generated
     * @ordered
     */
    protected KColor targetColor;

    /**
     * The default value of the '{@link #getTargetAlpha() <em>Target Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetAlpha()
     * @generated
     * @ordered
     */
    protected static final int TARGET_ALPHA_EDEFAULT = 255;

    /**
     * The cached value of the '{@link #getTargetAlpha() <em>Target Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetAlpha()
     * @generated
     * @ordered
     */
    protected int targetAlpha = TARGET_ALPHA_EDEFAULT;

    /**
     * The default value of the '{@link #getGradientAngle() <em>Gradient Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGradientAngle()
     * @generated
     * @ordered
     */
    protected static final float GRADIENT_ANGLE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getGradientAngle() <em>Gradient Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGradientAngle()
     * @generated
     * @ordered
     */
    protected float gradientAngle = GRADIENT_ANGLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KColoringImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KCOLORING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetColor(KColor newColor, NotificationChain msgs) {
        KColor oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__COLOR, oldColor, newColor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setColor(KColor newColor) {
        if (newColor != color) {
            NotificationChain msgs = null;
            if (color != null)
                msgs = ((InternalEObject)color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCOLORING__COLOR, null, msgs);
            if (newColor != null)
                msgs = ((InternalEObject)newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCOLORING__COLOR, null, msgs);
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__COLOR, newColor, newColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAlpha(int newAlpha) {
        int oldAlpha = alpha;
        alpha = newAlpha;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__ALPHA, oldAlpha, alpha));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getTargetColor() {
        return targetColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetColor(KColor newTargetColor, NotificationChain msgs) {
        KColor oldTargetColor = targetColor;
        targetColor = newTargetColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__TARGET_COLOR, oldTargetColor, newTargetColor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetColor(KColor newTargetColor) {
        if (newTargetColor != targetColor) {
            NotificationChain msgs = null;
            if (targetColor != null)
                msgs = ((InternalEObject)targetColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCOLORING__TARGET_COLOR, null, msgs);
            if (newTargetColor != null)
                msgs = ((InternalEObject)newTargetColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCOLORING__TARGET_COLOR, null, msgs);
            msgs = basicSetTargetColor(newTargetColor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__TARGET_COLOR, newTargetColor, newTargetColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getTargetAlpha() {
        return targetAlpha;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetAlpha(int newTargetAlpha) {
        int oldTargetAlpha = targetAlpha;
        targetAlpha = newTargetAlpha;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__TARGET_ALPHA, oldTargetAlpha, targetAlpha));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getGradientAngle() {
        return gradientAngle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGradientAngle(float newGradientAngle) {
        float oldGradientAngle = gradientAngle;
        gradientAngle = newGradientAngle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLORING__GRADIENT_ANGLE, oldGradientAngle, gradientAngle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor(final int red, final int green, final int blue) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, red, green, blue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor(final Colors color) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, color);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor(final int red, final int green, final int blue, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, red, green, blue, alpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor(final Colors color, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, color, alpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor2(final KColor color) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        this.setColor(color);
        return it;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColor2(final KColor color, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        this.setColor(color);
        this.setAlpha(alpha);
        return it;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorCopyOf(final KColor kColor) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColorCopyOf(it, kColor);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorCopyOf(final KColor kColor, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setColorCopyOf(it, kColor, alpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorCopiedFrom(final KColoring<?> coloring) {
        return this.setColorCopyOf(coloring.getColor());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorAndAlphaCopiedFrom(final KColoring<?> coloring) {
        return this.setColorCopyOf(coloring.getColor(), coloring.getAlpha());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor(final int red, final int green, final int blue) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, red, green, blue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor(final Colors color) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, color);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor(final int red, final int green, final int blue, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, red, green, blue, alpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor(final Colors color, final int alpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, color, alpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor2(final KColor targetColor) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        this.setTargetColor(targetColor);
        return it;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColor2(final KColor targetColor, final int targetAlpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        this.setTargetColor(targetColor);
        this.setTargetAlpha(targetAlpha);
        return it;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColorCopyOf(final KColor targetColor) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColorCopyOf(it, targetColor);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColorCopyOf(final KColor targetColor, final int targetAlpha) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColorCopyOf(it, targetColor, targetAlpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColorCopiedFrom(final KColoring<?> coloring) {
        return this.setTargetColorCopyOf(coloring.getTargetColor());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setTargetColorAndAlphaCopiedFrom(final KColoring<?> coloring) {
        return this.setTargetColorCopyOf(coloring.getTargetColor(), coloring.getTargetAlpha());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setGradientAngle2(final float angle) {
        @SuppressWarnings("unchecked")
        final T it = (T) this;
        this.setGradientAngle(angle);
        return it;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColors(final KColor color, final KColor targetColor) {
        return this.setColor2(color).setTargetColor2(targetColor);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColors(final KColor color, final int alpha, final KColor targetColor, final int targetAlpha) {
        return this.setColor2(color, alpha).setTargetColor2(targetColor, targetAlpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorsCopiesOf(final KColor color, final KColor targetColor) {
        return this.setColorCopyOf(color).setTargetColorCopyOf(targetColor);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorsCopiesOf(final KColor color, final int alpha, final KColor targetColor, final int targetAlpha) {
        return this.setColorCopyOf(color, alpha).setTargetColorCopyOf(targetColor, targetAlpha);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorsCopiedFrom(final KColoring<?> coloring) {
        return this.setColorsCopiesOf(coloring.getColor(), coloring.getTargetColor());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public T setColorsAlphasGradientAngleCopiedFrom(final KColoring<?> coloring) {
        return this.setColorsCopiesOf(coloring.getColor(), coloring.getAlpha(), coloring.getTargetColor(), coloring.getTargetAlpha()).setGradientAngle2(coloring.getGradientAngle());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean equals(final Object other) {
        return de.cau.cs.kieler.core.krendering.KRenderingUtil.equals(this,other);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KCOLORING__COLOR:
                return basicSetColor(null, msgs);
            case KRenderingPackage.KCOLORING__TARGET_COLOR:
                return basicSetTargetColor(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KCOLORING__COLOR:
                return getColor();
            case KRenderingPackage.KCOLORING__ALPHA:
                return getAlpha();
            case KRenderingPackage.KCOLORING__TARGET_COLOR:
                return getTargetColor();
            case KRenderingPackage.KCOLORING__TARGET_ALPHA:
                return getTargetAlpha();
            case KRenderingPackage.KCOLORING__GRADIENT_ANGLE:
                return getGradientAngle();
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
            case KRenderingPackage.KCOLORING__COLOR:
                setColor((KColor)newValue);
                return;
            case KRenderingPackage.KCOLORING__ALPHA:
                setAlpha((Integer)newValue);
                return;
            case KRenderingPackage.KCOLORING__TARGET_COLOR:
                setTargetColor((KColor)newValue);
                return;
            case KRenderingPackage.KCOLORING__TARGET_ALPHA:
                setTargetAlpha((Integer)newValue);
                return;
            case KRenderingPackage.KCOLORING__GRADIENT_ANGLE:
                setGradientAngle((Float)newValue);
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
            case KRenderingPackage.KCOLORING__COLOR:
                setColor((KColor)null);
                return;
            case KRenderingPackage.KCOLORING__ALPHA:
                setAlpha(ALPHA_EDEFAULT);
                return;
            case KRenderingPackage.KCOLORING__TARGET_COLOR:
                setTargetColor((KColor)null);
                return;
            case KRenderingPackage.KCOLORING__TARGET_ALPHA:
                setTargetAlpha(TARGET_ALPHA_EDEFAULT);
                return;
            case KRenderingPackage.KCOLORING__GRADIENT_ANGLE:
                setGradientAngle(GRADIENT_ANGLE_EDEFAULT);
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
            case KRenderingPackage.KCOLORING__COLOR:
                return color != null;
            case KRenderingPackage.KCOLORING__ALPHA:
                return alpha != ALPHA_EDEFAULT;
            case KRenderingPackage.KCOLORING__TARGET_COLOR:
                return targetColor != null;
            case KRenderingPackage.KCOLORING__TARGET_ALPHA:
                return targetAlpha != TARGET_ALPHA_EDEFAULT;
            case KRenderingPackage.KCOLORING__GRADIENT_ANGLE:
                return gradientAngle != GRADIENT_ANGLE_EDEFAULT;
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
        result.append(" (alpha: ");
        result.append(alpha);
        result.append(", targetAlpha: ");
        result.append(targetAlpha);
        result.append(", gradientAngle: ");
        result.append(gradientAngle);
        result.append(')');
        return result.toString();
    }

} //KColoringImpl
