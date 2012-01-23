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
package de.cau.cs.kieler.core.krendering.util;

import de.cau.cs.kieler.core.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.core.kgraph.KGraphData;

import de.cau.cs.kieler.core.krendering.*;

import de.cau.cs.kieler.core.properties.IPropertyHolder;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage
 * @generated
 */
public class KRenderingSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KRenderingPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingSwitch() {
        if (modelPackage == null) {
            modelPackage = KRenderingPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case KRenderingPackage.KPOSITION: {
                KPosition kPosition = (KPosition)theEObject;
                T result = caseKPosition(kPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING: {
                KRendering kRendering = (KRendering)theEObject;
                T result = caseKRendering(kRendering);
                if (result == null) result = caseKGraphData(kRendering);
                if (result == null) result = caseEMapPropertyHolder(kRendering);
                if (result == null) result = caseIPropertyHolder(kRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KELLIPSE: {
                KEllipse kEllipse = (KEllipse)theEObject;
                T result = caseKEllipse(kEllipse);
                if (result == null) result = caseKContainerRendering(kEllipse);
                if (result == null) result = caseKRendering(kEllipse);
                if (result == null) result = caseKGraphData(kEllipse);
                if (result == null) result = caseEMapPropertyHolder(kEllipse);
                if (result == null) result = caseIPropertyHolder(kEllipse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRECTANGLE: {
                KRectangle kRectangle = (KRectangle)theEObject;
                T result = caseKRectangle(kRectangle);
                if (result == null) result = caseKContainerRendering(kRectangle);
                if (result == null) result = caseKRendering(kRectangle);
                if (result == null) result = caseKGraphData(kRectangle);
                if (result == null) result = caseEMapPropertyHolder(kRectangle);
                if (result == null) result = caseIPropertyHolder(kRectangle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KROUNDED_RECTANGLE: {
                KRoundedRectangle kRoundedRectangle = (KRoundedRectangle)theEObject;
                T result = caseKRoundedRectangle(kRoundedRectangle);
                if (result == null) result = caseKContainerRendering(kRoundedRectangle);
                if (result == null) result = caseKRendering(kRoundedRectangle);
                if (result == null) result = caseKGraphData(kRoundedRectangle);
                if (result == null) result = caseEMapPropertyHolder(kRoundedRectangle);
                if (result == null) result = caseIPropertyHolder(kRoundedRectangle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOLYLINE: {
                KPolyline kPolyline = (KPolyline)theEObject;
                T result = caseKPolyline(kPolyline);
                if (result == null) result = caseKContainerRendering(kPolyline);
                if (result == null) result = caseKRendering(kPolyline);
                if (result == null) result = caseKGraphData(kPolyline);
                if (result == null) result = caseEMapPropertyHolder(kPolyline);
                if (result == null) result = caseIPropertyHolder(kPolyline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOLYGON: {
                KPolygon kPolygon = (KPolygon)theEObject;
                T result = caseKPolygon(kPolygon);
                if (result == null) result = caseKPolyline(kPolygon);
                if (result == null) result = caseKContainerRendering(kPolygon);
                if (result == null) result = caseKRendering(kPolygon);
                if (result == null) result = caseKGraphData(kPolygon);
                if (result == null) result = caseEMapPropertyHolder(kPolygon);
                if (result == null) result = caseIPropertyHolder(kPolygon);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KIMAGE: {
                KImage kImage = (KImage)theEObject;
                T result = caseKImage(kImage);
                if (result == null) result = caseKContainerRendering(kImage);
                if (result == null) result = caseKRendering(kImage);
                if (result == null) result = caseKGraphData(kImage);
                if (result == null) result = caseEMapPropertyHolder(kImage);
                if (result == null) result = caseIPropertyHolder(kImage);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA: {
                KDecoratorPlacementData kDecoratorPlacementData = (KDecoratorPlacementData)theEObject;
                T result = caseKDecoratorPlacementData(kDecoratorPlacementData);
                if (result == null) result = caseKPlacementData(kDecoratorPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCONTAINER_RENDERING: {
                KContainerRendering kContainerRendering = (KContainerRendering)theEObject;
                T result = caseKContainerRendering(kContainerRendering);
                if (result == null) result = caseKRendering(kContainerRendering);
                if (result == null) result = caseKGraphData(kContainerRendering);
                if (result == null) result = caseEMapPropertyHolder(kContainerRendering);
                if (result == null) result = caseIPropertyHolder(kContainerRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KARC: {
                KArc kArc = (KArc)theEObject;
                T result = caseKArc(kArc);
                if (result == null) result = caseKContainerRendering(kArc);
                if (result == null) result = caseKRendering(kArc);
                if (result == null) result = caseKGraphData(kArc);
                if (result == null) result = caseEMapPropertyHolder(kArc);
                if (result == null) result = caseIPropertyHolder(kArc);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTYLE: {
                KStyle kStyle = (KStyle)theEObject;
                T result = caseKStyle(kStyle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING_LIBRARY: {
                KRenderingLibrary kRenderingLibrary = (KRenderingLibrary)theEObject;
                T result = caseKRenderingLibrary(kRenderingLibrary);
                if (result == null) result = caseKGraphData(kRenderingLibrary);
                if (result == null) result = caseEMapPropertyHolder(kRenderingLibrary);
                if (result == null) result = caseIPropertyHolder(kRenderingLibrary);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING_REF: {
                KRenderingRef kRenderingRef = (KRenderingRef)theEObject;
                T result = caseKRenderingRef(kRenderingRef);
                if (result == null) result = caseKRendering(kRenderingRef);
                if (result == null) result = caseKGraphData(kRenderingRef);
                if (result == null) result = caseEMapPropertyHolder(kRenderingRef);
                if (result == null) result = caseIPropertyHolder(kRenderingRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCHILD_AREA: {
                KChildArea kChildArea = (KChildArea)theEObject;
                T result = caseKChildArea(kChildArea);
                if (result == null) result = caseKRendering(kChildArea);
                if (result == null) result = caseKGraphData(kChildArea);
                if (result == null) result = caseEMapPropertyHolder(kChildArea);
                if (result == null) result = caseIPropertyHolder(kChildArea);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTEXT: {
                KText kText = (KText)theEObject;
                T result = caseKText(kText);
                if (result == null) result = caseKContainerRendering(kText);
                if (result == null) result = caseKRendering(kText);
                if (result == null) result = caseKGraphData(kText);
                if (result == null) result = caseEMapPropertyHolder(kText);
                if (result == null) result = caseIPropertyHolder(kText);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPLACEMENT: {
                KPlacement kPlacement = (KPlacement)theEObject;
                T result = caseKPlacement(kPlacement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KGRID_PLACEMENT: {
                KGridPlacement kGridPlacement = (KGridPlacement)theEObject;
                T result = caseKGridPlacement(kGridPlacement);
                if (result == null) result = caseKPlacement(kGridPlacement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTACK_PLACEMENT: {
                KStackPlacement kStackPlacement = (KStackPlacement)theEObject;
                T result = caseKStackPlacement(kStackPlacement);
                if (result == null) result = caseKPlacement(kStackPlacement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPLACEMENT_DATA: {
                KPlacementData kPlacementData = (KPlacementData)theEObject;
                T result = caseKPlacementData(kPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KGRID_PLACEMENT_DATA: {
                KGridPlacementData kGridPlacementData = (KGridPlacementData)theEObject;
                T result = caseKGridPlacementData(kGridPlacementData);
                if (result == null) result = caseKPlacementData(kGridPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTACK_PLACEMENT_DATA: {
                KStackPlacementData kStackPlacementData = (KStackPlacementData)theEObject;
                T result = caseKStackPlacementData(kStackPlacementData);
                if (result == null) result = caseKPlacementData(kStackPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KDIRECT_PLACEMENT_DATA: {
                KDirectPlacementData kDirectPlacementData = (KDirectPlacementData)theEObject;
                T result = caseKDirectPlacementData(kDirectPlacementData);
                if (result == null) result = caseKPlacementData(kDirectPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA: {
                KPolylinePlacementData kPolylinePlacementData = (KPolylinePlacementData)theEObject;
                T result = caseKPolylinePlacementData(kPolylinePlacementData);
                if (result == null) result = caseKPlacementData(kPolylinePlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCUSTOM_RENDERING: {
                KCustomRendering kCustomRendering = (KCustomRendering)theEObject;
                T result = caseKCustomRendering(kCustomRendering);
                if (result == null) result = caseKContainerRendering(kCustomRendering);
                if (result == null) result = caseKRendering(kCustomRendering);
                if (result == null) result = caseKGraphData(kCustomRendering);
                if (result == null) result = caseEMapPropertyHolder(kCustomRendering);
                if (result == null) result = caseIPropertyHolder(kCustomRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFOREGROUND_COLOR: {
                KForegroundColor kForegroundColor = (KForegroundColor)theEObject;
                T result = caseKForegroundColor(kForegroundColor);
                if (result == null) result = caseKColor(kForegroundColor);
                if (result == null) result = caseKStyle(kForegroundColor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCOLOR: {
                KColor kColor = (KColor)theEObject;
                T result = caseKColor(kColor);
                if (result == null) result = caseKStyle(kColor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KBACKGROUND_COLOR: {
                KBackgroundColor kBackgroundColor = (KBackgroundColor)theEObject;
                T result = caseKBackgroundColor(kBackgroundColor);
                if (result == null) result = caseKColor(kBackgroundColor);
                if (result == null) result = caseKStyle(kBackgroundColor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_WIDTH: {
                KLineWidth kLineWidth = (KLineWidth)theEObject;
                T result = caseKLineWidth(kLineWidth);
                if (result == null) result = caseKStyle(kLineWidth);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KVISIBILITY: {
                KVisibility kVisibility = (KVisibility)theEObject;
                T result = caseKVisibility(kVisibility);
                if (result == null) result = caseKStyle(kVisibility);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_STYLE: {
                KLineStyle kLineStyle = (KLineStyle)theEObject;
                T result = caseKLineStyle(kLineStyle);
                if (result == null) result = caseKStyle(kLineStyle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KVERTICAL_ALIGNMENT: {
                KVerticalAlignment kVerticalAlignment = (KVerticalAlignment)theEObject;
                T result = caseKVerticalAlignment(kVerticalAlignment);
                if (result == null) result = caseKStyle(kVerticalAlignment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT: {
                KHorizontalAlignment kHorizontalAlignment = (KHorizontalAlignment)theEObject;
                T result = caseKHorizontalAlignment(kHorizontalAlignment);
                if (result == null) result = caseKStyle(kHorizontalAlignment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KX_POSITION: {
                KXPosition kxPosition = (KXPosition)theEObject;
                T result = caseKXPosition(kxPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KY_POSITION: {
                KYPosition kyPosition = (KYPosition)theEObject;
                T result = caseKYPosition(kyPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLEFT_POSITION: {
                KLeftPosition kLeftPosition = (KLeftPosition)theEObject;
                T result = caseKLeftPosition(kLeftPosition);
                if (result == null) result = caseKXPosition(kLeftPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRIGHT_POSITION: {
                KRightPosition kRightPosition = (KRightPosition)theEObject;
                T result = caseKRightPosition(kRightPosition);
                if (result == null) result = caseKXPosition(kRightPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTOP_POSITION: {
                KTopPosition kTopPosition = (KTopPosition)theEObject;
                T result = caseKTopPosition(kTopPosition);
                if (result == null) result = caseKYPosition(kTopPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KBOTTOM_POSITION: {
                KBottomPosition kBottomPosition = (KBottomPosition)theEObject;
                T result = caseKBottomPosition(kBottomPosition);
                if (result == null) result = caseKYPosition(kBottomPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPosition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPosition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPosition(KPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRendering</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRendering</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRendering(KRendering object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KEllipse</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KEllipse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKEllipse(KEllipse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRectangle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRectangle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRectangle(KRectangle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRounded Rectangle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRounded Rectangle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRoundedRectangle(KRoundedRectangle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPolyline</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPolyline</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPolyline(KPolyline object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPolygon</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPolygon</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPolygon(KPolygon object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KImage</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KImage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKImage(KImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KDecorator Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KDecorator Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKDecoratorPlacementData(KDecoratorPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KContainer Rendering</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KContainer Rendering</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKContainerRendering(KContainerRendering object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KArc</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KArc</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKArc(KArc object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KStyle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KStyle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKStyle(KStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Library</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Library</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRenderingLibrary(KRenderingLibrary object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRenderingRef(KRenderingRef object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KChild Area</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KChild Area</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKChildArea(KChildArea object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KText</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KText</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKText(KText object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPlacement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPlacement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPlacement(KPlacement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KGrid Placement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KGrid Placement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKGridPlacement(KGridPlacement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KStack Placement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KStack Placement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKStackPlacement(KStackPlacement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPlacement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPlacement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPlacementData(KPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KGrid Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KGrid Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKGridPlacementData(KGridPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KStack Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KStack Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKStackPlacementData(KStackPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KDirect Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KDirect Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKDirectPlacementData(KDirectPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPolyline Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPolyline Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPolylinePlacementData(KPolylinePlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KCustom Rendering</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KCustom Rendering</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKCustomRendering(KCustomRendering object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KForeground Color</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KForeground Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKForegroundColor(KForegroundColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KColor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KColor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKColor(KColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KBackground Color</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KBackground Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKBackgroundColor(KBackgroundColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLine Width</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLine Width</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKLineWidth(KLineWidth object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KVisibility</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KVisibility</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKVisibility(KVisibility object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLine Style</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLine Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKLineStyle(KLineStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KVertical Alignment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KVertical Alignment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKVerticalAlignment(KVerticalAlignment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KHorizontal Alignment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KHorizontal Alignment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKHorizontalAlignment(KHorizontalAlignment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KX Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KX Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKXPosition(KXPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KY Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KY Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKYPosition(KYPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLeft Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLeft Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKLeftPosition(KLeftPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRight Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRight Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKRightPosition(KRightPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KTop Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KTop Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKTopPosition(KTopPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KBottom Position</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KBottom Position</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKBottomPosition(KBottomPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IProperty Holder</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IProperty Holder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIPropertyHolder(IPropertyHolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EMap Property Holder</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EMap Property Holder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEMapPropertyHolder(EMapPropertyHolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKGraphData(KGraphData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //KRenderingSwitch
