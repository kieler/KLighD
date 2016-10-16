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
package de.cau.cs.kieler.klighd.krendering.util;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;

import de.cau.cs.kieler.klighd.krendering.*;

import org.eclipse.elk.graph.properties.IPropertyHolder;

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
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage
 * @generated
 */
public class KRenderingSwitch<T1> extends Switch<T1> {
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
     * @param ePackage the package in question.
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
    protected T1 doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case KRenderingPackage.KPOSITION: {
                KPosition kPosition = (KPosition)theEObject;
                T1 result = caseKPosition(kPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING: {
                KRendering kRendering = (KRendering)theEObject;
                T1 result = caseKRendering(kRendering);
                if (result == null) result = caseKGraphData(kRendering);
                if (result == null) result = caseKStyleHolder(kRendering);
                if (result == null) result = caseEMapPropertyHolder(kRendering);
                if (result == null) result = caseIPropertyHolder(kRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KELLIPSE: {
                KEllipse kEllipse = (KEllipse)theEObject;
                T1 result = caseKEllipse(kEllipse);
                if (result == null) result = caseKContainerRendering(kEllipse);
                if (result == null) result = caseKRendering(kEllipse);
                if (result == null) result = caseKGraphData(kEllipse);
                if (result == null) result = caseKStyleHolder(kEllipse);
                if (result == null) result = caseEMapPropertyHolder(kEllipse);
                if (result == null) result = caseIPropertyHolder(kEllipse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRECTANGLE: {
                KRectangle kRectangle = (KRectangle)theEObject;
                T1 result = caseKRectangle(kRectangle);
                if (result == null) result = caseKContainerRendering(kRectangle);
                if (result == null) result = caseKRendering(kRectangle);
                if (result == null) result = caseKGraphData(kRectangle);
                if (result == null) result = caseKStyleHolder(kRectangle);
                if (result == null) result = caseEMapPropertyHolder(kRectangle);
                if (result == null) result = caseIPropertyHolder(kRectangle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KROUNDED_RECTANGLE: {
                KRoundedRectangle kRoundedRectangle = (KRoundedRectangle)theEObject;
                T1 result = caseKRoundedRectangle(kRoundedRectangle);
                if (result == null) result = caseKContainerRendering(kRoundedRectangle);
                if (result == null) result = caseKRendering(kRoundedRectangle);
                if (result == null) result = caseKGraphData(kRoundedRectangle);
                if (result == null) result = caseKStyleHolder(kRoundedRectangle);
                if (result == null) result = caseEMapPropertyHolder(kRoundedRectangle);
                if (result == null) result = caseIPropertyHolder(kRoundedRectangle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOLYLINE: {
                KPolyline kPolyline = (KPolyline)theEObject;
                T1 result = caseKPolyline(kPolyline);
                if (result == null) result = caseKContainerRendering(kPolyline);
                if (result == null) result = caseKRendering(kPolyline);
                if (result == null) result = caseKGraphData(kPolyline);
                if (result == null) result = caseKStyleHolder(kPolyline);
                if (result == null) result = caseEMapPropertyHolder(kPolyline);
                if (result == null) result = caseIPropertyHolder(kPolyline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOLYGON: {
                KPolygon kPolygon = (KPolygon)theEObject;
                T1 result = caseKPolygon(kPolygon);
                if (result == null) result = caseKPolyline(kPolygon);
                if (result == null) result = caseKContainerRendering(kPolygon);
                if (result == null) result = caseKRendering(kPolygon);
                if (result == null) result = caseKGraphData(kPolygon);
                if (result == null) result = caseKStyleHolder(kPolygon);
                if (result == null) result = caseEMapPropertyHolder(kPolygon);
                if (result == null) result = caseIPropertyHolder(kPolygon);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KIMAGE: {
                KImage kImage = (KImage)theEObject;
                T1 result = caseKImage(kImage);
                if (result == null) result = caseKContainerRendering(kImage);
                if (result == null) result = caseKRendering(kImage);
                if (result == null) result = caseKGraphData(kImage);
                if (result == null) result = caseKStyleHolder(kImage);
                if (result == null) result = caseEMapPropertyHolder(kImage);
                if (result == null) result = caseIPropertyHolder(kImage);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA: {
                KDecoratorPlacementData kDecoratorPlacementData = (KDecoratorPlacementData)theEObject;
                T1 result = caseKDecoratorPlacementData(kDecoratorPlacementData);
                if (result == null) result = caseKPlacementData(kDecoratorPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCONTAINER_RENDERING: {
                KContainerRendering kContainerRendering = (KContainerRendering)theEObject;
                T1 result = caseKContainerRendering(kContainerRendering);
                if (result == null) result = caseKRendering(kContainerRendering);
                if (result == null) result = caseKGraphData(kContainerRendering);
                if (result == null) result = caseKStyleHolder(kContainerRendering);
                if (result == null) result = caseEMapPropertyHolder(kContainerRendering);
                if (result == null) result = caseIPropertyHolder(kContainerRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KARC: {
                KArc kArc = (KArc)theEObject;
                T1 result = caseKArc(kArc);
                if (result == null) result = caseKContainerRendering(kArc);
                if (result == null) result = caseKRendering(kArc);
                if (result == null) result = caseKGraphData(kArc);
                if (result == null) result = caseKStyleHolder(kArc);
                if (result == null) result = caseEMapPropertyHolder(kArc);
                if (result == null) result = caseIPropertyHolder(kArc);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTYLE: {
                KStyle kStyle = (KStyle)theEObject;
                T1 result = caseKStyle(kStyle);
                if (result == null) result = caseEMapPropertyHolder(kStyle);
                if (result == null) result = caseIPropertyHolder(kStyle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING_LIBRARY: {
                KRenderingLibrary kRenderingLibrary = (KRenderingLibrary)theEObject;
                T1 result = caseKRenderingLibrary(kRenderingLibrary);
                if (result == null) result = caseKGraphData(kRenderingLibrary);
                if (result == null) result = caseEMapPropertyHolder(kRenderingLibrary);
                if (result == null) result = caseIPropertyHolder(kRenderingLibrary);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRENDERING_REF: {
                KRenderingRef kRenderingRef = (KRenderingRef)theEObject;
                T1 result = caseKRenderingRef(kRenderingRef);
                if (result == null) result = caseKRendering(kRenderingRef);
                if (result == null) result = caseKGraphData(kRenderingRef);
                if (result == null) result = caseKStyleHolder(kRenderingRef);
                if (result == null) result = caseEMapPropertyHolder(kRenderingRef);
                if (result == null) result = caseIPropertyHolder(kRenderingRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCHILD_AREA: {
                KChildArea kChildArea = (KChildArea)theEObject;
                T1 result = caseKChildArea(kChildArea);
                if (result == null) result = caseKRendering(kChildArea);
                if (result == null) result = caseKGraphData(kChildArea);
                if (result == null) result = caseKStyleHolder(kChildArea);
                if (result == null) result = caseEMapPropertyHolder(kChildArea);
                if (result == null) result = caseIPropertyHolder(kChildArea);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTEXT: {
                KText kText = (KText)theEObject;
                T1 result = caseKText(kText);
                if (result == null) result = caseKRendering(kText);
                if (result == null) result = caseKGraphData(kText);
                if (result == null) result = caseKStyleHolder(kText);
                if (result == null) result = caseEMapPropertyHolder(kText);
                if (result == null) result = caseIPropertyHolder(kText);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPLACEMENT: {
                KPlacement kPlacement = (KPlacement)theEObject;
                T1 result = caseKPlacement(kPlacement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KGRID_PLACEMENT: {
                KGridPlacement kGridPlacement = (KGridPlacement)theEObject;
                T1 result = caseKGridPlacement(kGridPlacement);
                if (result == null) result = caseKPlacement(kGridPlacement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPLACEMENT_DATA: {
                KPlacementData kPlacementData = (KPlacementData)theEObject;
                T1 result = caseKPlacementData(kPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KGRID_PLACEMENT_DATA: {
                KGridPlacementData kGridPlacementData = (KGridPlacementData)theEObject;
                T1 result = caseKGridPlacementData(kGridPlacementData);
                if (result == null) result = caseKAreaPlacementData(kGridPlacementData);
                if (result == null) result = caseKPlacementData(kGridPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KAREA_PLACEMENT_DATA: {
                KAreaPlacementData kAreaPlacementData = (KAreaPlacementData)theEObject;
                T1 result = caseKAreaPlacementData(kAreaPlacementData);
                if (result == null) result = caseKPlacementData(kAreaPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCUSTOM_RENDERING: {
                KCustomRendering kCustomRendering = (KCustomRendering)theEObject;
                T1 result = caseKCustomRendering(kCustomRendering);
                if (result == null) result = caseKContainerRendering(kCustomRendering);
                if (result == null) result = caseKRendering(kCustomRendering);
                if (result == null) result = caseKGraphData(kCustomRendering);
                if (result == null) result = caseKStyleHolder(kCustomRendering);
                if (result == null) result = caseEMapPropertyHolder(kCustomRendering);
                if (result == null) result = caseIPropertyHolder(kCustomRendering);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCOLOR: {
                KColor kColor = (KColor)theEObject;
                T1 result = caseKColor(kColor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_WIDTH: {
                KLineWidth kLineWidth = (KLineWidth)theEObject;
                T1 result = caseKLineWidth(kLineWidth);
                if (result == null) result = caseKStyle(kLineWidth);
                if (result == null) result = caseEMapPropertyHolder(kLineWidth);
                if (result == null) result = caseIPropertyHolder(kLineWidth);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_STYLE: {
                KLineStyle kLineStyle = (KLineStyle)theEObject;
                T1 result = caseKLineStyle(kLineStyle);
                if (result == null) result = caseKStyle(kLineStyle);
                if (result == null) result = caseEMapPropertyHolder(kLineStyle);
                if (result == null) result = caseIPropertyHolder(kLineStyle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KVERTICAL_ALIGNMENT: {
                KVerticalAlignment kVerticalAlignment = (KVerticalAlignment)theEObject;
                T1 result = caseKVerticalAlignment(kVerticalAlignment);
                if (result == null) result = caseKStyle(kVerticalAlignment);
                if (result == null) result = caseEMapPropertyHolder(kVerticalAlignment);
                if (result == null) result = caseIPropertyHolder(kVerticalAlignment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT: {
                KHorizontalAlignment kHorizontalAlignment = (KHorizontalAlignment)theEObject;
                T1 result = caseKHorizontalAlignment(kHorizontalAlignment);
                if (result == null) result = caseKStyle(kHorizontalAlignment);
                if (result == null) result = caseEMapPropertyHolder(kHorizontalAlignment);
                if (result == null) result = caseIPropertyHolder(kHorizontalAlignment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KX_POSITION: {
                KXPosition<?> kxPosition = (KXPosition<?>)theEObject;
                T1 result = caseKXPosition(kxPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KY_POSITION: {
                KYPosition<?> kyPosition = (KYPosition<?>)theEObject;
                T1 result = caseKYPosition(kyPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLEFT_POSITION: {
                KLeftPosition kLeftPosition = (KLeftPosition)theEObject;
                T1 result = caseKLeftPosition(kLeftPosition);
                if (result == null) result = caseKXPosition(kLeftPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KRIGHT_POSITION: {
                KRightPosition kRightPosition = (KRightPosition)theEObject;
                T1 result = caseKRightPosition(kRightPosition);
                if (result == null) result = caseKXPosition(kRightPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTOP_POSITION: {
                KTopPosition kTopPosition = (KTopPosition)theEObject;
                T1 result = caseKTopPosition(kTopPosition);
                if (result == null) result = caseKYPosition(kTopPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KBOTTOM_POSITION: {
                KBottomPosition kBottomPosition = (KBottomPosition)theEObject;
                T1 result = caseKBottomPosition(kBottomPosition);
                if (result == null) result = caseKYPosition(kBottomPosition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSPLINE: {
                KSpline kSpline = (KSpline)theEObject;
                T1 result = caseKSpline(kSpline);
                if (result == null) result = caseKPolyline(kSpline);
                if (result == null) result = caseKContainerRendering(kSpline);
                if (result == null) result = caseKRendering(kSpline);
                if (result == null) result = caseKGraphData(kSpline);
                if (result == null) result = caseKStyleHolder(kSpline);
                if (result == null) result = caseEMapPropertyHolder(kSpline);
                if (result == null) result = caseIPropertyHolder(kSpline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFOREGROUND: {
                KForeground kForeground = (KForeground)theEObject;
                T1 result = caseKForeground(kForeground);
                if (result == null) result = caseKColoring(kForeground);
                if (result == null) result = caseKStyle(kForeground);
                if (result == null) result = caseEMapPropertyHolder(kForeground);
                if (result == null) result = caseIPropertyHolder(kForeground);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KCOLORING: {
                KColoring<?> kColoring = (KColoring<?>)theEObject;
                T1 result = caseKColoring(kColoring);
                if (result == null) result = caseKStyle(kColoring);
                if (result == null) result = caseEMapPropertyHolder(kColoring);
                if (result == null) result = caseIPropertyHolder(kColoring);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KBACKGROUND: {
                KBackground kBackground = (KBackground)theEObject;
                T1 result = caseKBackground(kBackground);
                if (result == null) result = caseKColoring(kBackground);
                if (result == null) result = caseKStyle(kBackground);
                if (result == null) result = caseEMapPropertyHolder(kBackground);
                if (result == null) result = caseIPropertyHolder(kBackground);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFONT_BOLD: {
                KFontBold kFontBold = (KFontBold)theEObject;
                T1 result = caseKFontBold(kFontBold);
                if (result == null) result = caseKStyle(kFontBold);
                if (result == null) result = caseEMapPropertyHolder(kFontBold);
                if (result == null) result = caseIPropertyHolder(kFontBold);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFONT_ITALIC: {
                KFontItalic kFontItalic = (KFontItalic)theEObject;
                T1 result = caseKFontItalic(kFontItalic);
                if (result == null) result = caseKStyle(kFontItalic);
                if (result == null) result = caseEMapPropertyHolder(kFontItalic);
                if (result == null) result = caseIPropertyHolder(kFontItalic);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFONT_NAME: {
                KFontName kFontName = (KFontName)theEObject;
                T1 result = caseKFontName(kFontName);
                if (result == null) result = caseKStyle(kFontName);
                if (result == null) result = caseEMapPropertyHolder(kFontName);
                if (result == null) result = caseIPropertyHolder(kFontName);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KFONT_SIZE: {
                KFontSize kFontSize = (KFontSize)theEObject;
                T1 result = caseKFontSize(kFontSize);
                if (result == null) result = caseKStyle(kFontSize);
                if (result == null) result = caseEMapPropertyHolder(kFontSize);
                if (result == null) result = caseIPropertyHolder(kFontSize);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KROUNDED_BENDS_POLYLINE: {
                KRoundedBendsPolyline kRoundedBendsPolyline = (KRoundedBendsPolyline)theEObject;
                T1 result = caseKRoundedBendsPolyline(kRoundedBendsPolyline);
                if (result == null) result = caseKPolyline(kRoundedBendsPolyline);
                if (result == null) result = caseKContainerRendering(kRoundedBendsPolyline);
                if (result == null) result = caseKRendering(kRoundedBendsPolyline);
                if (result == null) result = caseKGraphData(kRoundedBendsPolyline);
                if (result == null) result = caseKStyleHolder(kRoundedBendsPolyline);
                if (result == null) result = caseEMapPropertyHolder(kRoundedBendsPolyline);
                if (result == null) result = caseIPropertyHolder(kRoundedBendsPolyline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KROTATION: {
                KRotation kRotation = (KRotation)theEObject;
                T1 result = caseKRotation(kRotation);
                if (result == null) result = caseKStyle(kRotation);
                if (result == null) result = caseEMapPropertyHolder(kRotation);
                if (result == null) result = caseIPropertyHolder(kRotation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_CAP: {
                KLineCap kLineCap = (KLineCap)theEObject;
                T1 result = caseKLineCap(kLineCap);
                if (result == null) result = caseKStyle(kLineCap);
                if (result == null) result = caseEMapPropertyHolder(kLineCap);
                if (result == null) result = caseIPropertyHolder(kLineCap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KACTION: {
                KAction kAction = (KAction)theEObject;
                T1 result = caseKAction(kAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KPOINT_PLACEMENT_DATA: {
                KPointPlacementData kPointPlacementData = (KPointPlacementData)theEObject;
                T1 result = caseKPointPlacementData(kPointPlacementData);
                if (result == null) result = caseKPlacementData(kPointPlacementData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTYLE_HOLDER: {
                KStyleHolder kStyleHolder = (KStyleHolder)theEObject;
                T1 result = caseKStyleHolder(kStyleHolder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KINVISIBILITY: {
                KInvisibility kInvisibility = (KInvisibility)theEObject;
                T1 result = caseKInvisibility(kInvisibility);
                if (result == null) result = caseKStyle(kInvisibility);
                if (result == null) result = caseEMapPropertyHolder(kInvisibility);
                if (result == null) result = caseIPropertyHolder(kInvisibility);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSHADOW: {
                KShadow kShadow = (KShadow)theEObject;
                T1 result = caseKShadow(kShadow);
                if (result == null) result = caseKStyle(kShadow);
                if (result == null) result = caseEMapPropertyHolder(kShadow);
                if (result == null) result = caseIPropertyHolder(kShadow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTEXT_UNDERLINE: {
                KTextUnderline kTextUnderline = (KTextUnderline)theEObject;
                T1 result = caseKTextUnderline(kTextUnderline);
                if (result == null) result = caseKStyle(kTextUnderline);
                if (result == null) result = caseEMapPropertyHolder(kTextUnderline);
                if (result == null) result = caseIPropertyHolder(kTextUnderline);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KSTYLE_REF: {
                KStyleRef kStyleRef = (KStyleRef)theEObject;
                T1 result = caseKStyleRef(kStyleRef);
                if (result == null) result = caseKStyle(kStyleRef);
                if (result == null) result = caseEMapPropertyHolder(kStyleRef);
                if (result == null) result = caseIPropertyHolder(kStyleRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KTEXT_STRIKEOUT: {
                KTextStrikeout kTextStrikeout = (KTextStrikeout)theEObject;
                T1 result = caseKTextStrikeout(kTextStrikeout);
                if (result == null) result = caseKStyle(kTextStrikeout);
                if (result == null) result = caseEMapPropertyHolder(kTextStrikeout);
                if (result == null) result = caseIPropertyHolder(kTextStrikeout);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KRenderingPackage.KLINE_JOIN: {
                KLineJoin kLineJoin = (KLineJoin)theEObject;
                T1 result = caseKLineJoin(kLineJoin);
                if (result == null) result = caseKStyle(kLineJoin);
                if (result == null) result = caseEMapPropertyHolder(kLineJoin);
                if (result == null) result = caseIPropertyHolder(kLineJoin);
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
    public T1 caseKPosition(KPosition object) {
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
    public T1 caseKRendering(KRendering object) {
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
    public T1 caseKEllipse(KEllipse object) {
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
    public T1 caseKRectangle(KRectangle object) {
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
    public T1 caseKRoundedRectangle(KRoundedRectangle object) {
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
    public T1 caseKPolyline(KPolyline object) {
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
    public T1 caseKPolygon(KPolygon object) {
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
    public T1 caseKImage(KImage object) {
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
    public T1 caseKDecoratorPlacementData(KDecoratorPlacementData object) {
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
    public T1 caseKContainerRendering(KContainerRendering object) {
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
    public T1 caseKArc(KArc object) {
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
    public T1 caseKStyle(KStyle object) {
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
    public T1 caseKRenderingLibrary(KRenderingLibrary object) {
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
    public T1 caseKRenderingRef(KRenderingRef object) {
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
    public T1 caseKChildArea(KChildArea object) {
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
    public T1 caseKText(KText object) {
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
    public T1 caseKPlacement(KPlacement object) {
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
    public T1 caseKGridPlacement(KGridPlacement object) {
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
    public T1 caseKPlacementData(KPlacementData object) {
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
    public T1 caseKGridPlacementData(KGridPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KArea Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KArea Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKAreaPlacementData(KAreaPlacementData object) {
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
    public T1 caseKCustomRendering(KCustomRendering object) {
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
    public T1 caseKColor(KColor object) {
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
    public T1 caseKLineWidth(KLineWidth object) {
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
    public T1 caseKLineStyle(KLineStyle object) {
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
    public T1 caseKVerticalAlignment(KVerticalAlignment object) {
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
    public T1 caseKHorizontalAlignment(KHorizontalAlignment object) {
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
    public <T extends KXPosition<T>> T1 caseKXPosition(KXPosition<T> object) {
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
    public <T extends KYPosition<T>> T1 caseKYPosition(KYPosition<T> object) {
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
    public T1 caseKLeftPosition(KLeftPosition object) {
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
    public T1 caseKRightPosition(KRightPosition object) {
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
    public T1 caseKTopPosition(KTopPosition object) {
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
    public T1 caseKBottomPosition(KBottomPosition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KSpline</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KSpline</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKSpline(KSpline object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KForeground</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KForeground</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKForeground(KForeground object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KColoring</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KColoring</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T extends KColoring<T>> T1 caseKColoring(KColoring<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KBackground</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KBackground</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKBackground(KBackground object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KFont Bold</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KFont Bold</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKFontBold(KFontBold object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KFont Italic</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KFont Italic</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKFontItalic(KFontItalic object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KFont Name</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KFont Name</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKFontName(KFontName object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KFont Size</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KFont Size</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKFontSize(KFontSize object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRounded Bends Polyline</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRounded Bends Polyline</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKRoundedBendsPolyline(KRoundedBendsPolyline object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KRotation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KRotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKRotation(KRotation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLine Cap</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLine Cap</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKLineCap(KLineCap object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KAction</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KAction</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKAction(KAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPoint Placement Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPoint Placement Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKPointPlacementData(KPointPlacementData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KStyle Holder</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KStyle Holder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKStyleHolder(KStyleHolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KInvisibility</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KInvisibility</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKInvisibility(KInvisibility object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KShadow</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KShadow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKShadow(KShadow object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KText Underline</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KText Underline</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKTextUnderline(KTextUnderline object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KStyle Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KStyle Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKStyleRef(KStyleRef object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KText Strikeout</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KText Strikeout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKTextStrikeout(KTextStrikeout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLine Join</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLine Join</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseKLineJoin(KLineJoin object) {
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
    public T1 caseIPropertyHolder(IPropertyHolder object) {
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
    public T1 caseEMapPropertyHolder(EMapPropertyHolder object) {
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
    public T1 caseKGraphData(KGraphData object) {
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
    public T1 defaultCase(EObject object) {
        return null;
    }

} //KRenderingSwitch
