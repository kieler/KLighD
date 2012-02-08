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

import de.cau.cs.kieler.core.krendering.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KRenderingFactoryImpl extends EFactoryImpl implements KRenderingFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static KRenderingFactory init() {
        try {
            KRenderingFactory theKRenderingFactory = (KRenderingFactory)EPackage.Registry.INSTANCE.getEFactory("http://kieler.cs.cau.de/KRendering"); 
            if (theKRenderingFactory != null) {
                return theKRenderingFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new KRenderingFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case KRenderingPackage.KPOSITION: return createKPosition();
            case KRenderingPackage.KELLIPSE: return createKEllipse();
            case KRenderingPackage.KRECTANGLE: return createKRectangle();
            case KRenderingPackage.KROUNDED_RECTANGLE: return createKRoundedRectangle();
            case KRenderingPackage.KPOLYLINE: return createKPolyline();
            case KRenderingPackage.KPOLYGON: return createKPolygon();
            case KRenderingPackage.KIMAGE: return createKImage();
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA: return createKDecoratorPlacementData();
            case KRenderingPackage.KARC: return createKArc();
            case KRenderingPackage.KSTYLE: return createKStyle();
            case KRenderingPackage.KRENDERING_LIBRARY: return createKRenderingLibrary();
            case KRenderingPackage.KRENDERING_REF: return createKRenderingRef();
            case KRenderingPackage.KCHILD_AREA: return createKChildArea();
            case KRenderingPackage.KTEXT: return createKText();
            case KRenderingPackage.KGRID_PLACEMENT: return createKGridPlacement();
            case KRenderingPackage.KSTACK_PLACEMENT: return createKStackPlacement();
            case KRenderingPackage.KGRID_PLACEMENT_DATA: return createKGridPlacementData();
            case KRenderingPackage.KSTACK_PLACEMENT_DATA: return createKStackPlacementData();
            case KRenderingPackage.KDIRECT_PLACEMENT_DATA: return createKDirectPlacementData();
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA: return createKPolylinePlacementData();
            case KRenderingPackage.KCUSTOM_RENDERING: return createKCustomRendering();
            case KRenderingPackage.KFOREGROUND_COLOR: return createKForegroundColor();
            case KRenderingPackage.KBACKGROUND_COLOR: return createKBackgroundColor();
            case KRenderingPackage.KLINE_WIDTH: return createKLineWidth();
            case KRenderingPackage.KVISIBILITY: return createKVisibility();
            case KRenderingPackage.KLINE_STYLE: return createKLineStyle();
            case KRenderingPackage.KVERTICAL_ALIGNMENT: return createKVerticalAlignment();
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT: return createKHorizontalAlignment();
            case KRenderingPackage.KLEFT_POSITION: return createKLeftPosition();
            case KRenderingPackage.KRIGHT_POSITION: return createKRightPosition();
            case KRenderingPackage.KTOP_POSITION: return createKTopPosition();
            case KRenderingPackage.KBOTTOM_POSITION: return createKBottomPosition();
            case KRenderingPackage.KSPLINE: return createKSpline();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case KRenderingPackage.LINE_STYLE:
                return createLineStyleFromString(eDataType, initialValue);
            case KRenderingPackage.HORIZONTAL_ALIGNMENT:
                return createHorizontalAlignmentFromString(eDataType, initialValue);
            case KRenderingPackage.VERTICAL_ALIGNMENT:
                return createVerticalAlignmentFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case KRenderingPackage.LINE_STYLE:
                return convertLineStyleToString(eDataType, instanceValue);
            case KRenderingPackage.HORIZONTAL_ALIGNMENT:
                return convertHorizontalAlignmentToString(eDataType, instanceValue);
            case KRenderingPackage.VERTICAL_ALIGNMENT:
                return convertVerticalAlignmentToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition createKPosition() {
        KPositionImpl kPosition = new KPositionImpl();
        return kPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KEllipse createKEllipse() {
        KEllipseImpl kEllipse = new KEllipseImpl();
        return kEllipse;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRectangle createKRectangle() {
        KRectangleImpl kRectangle = new KRectangleImpl();
        return kRectangle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRoundedRectangle createKRoundedRectangle() {
        KRoundedRectangleImpl kRoundedRectangle = new KRoundedRectangleImpl();
        return kRoundedRectangle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPolyline createKPolyline() {
        KPolylineImpl kPolyline = new KPolylineImpl();
        return kPolyline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPolygon createKPolygon() {
        KPolygonImpl kPolygon = new KPolygonImpl();
        return kPolygon;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KImage createKImage() {
        KImageImpl kImage = new KImageImpl();
        return kImage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KDecoratorPlacementData createKDecoratorPlacementData() {
        KDecoratorPlacementDataImpl kDecoratorPlacementData = new KDecoratorPlacementDataImpl();
        return kDecoratorPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KArc createKArc() {
        KArcImpl kArc = new KArcImpl();
        return kArc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyle createKStyle() {
        KStyleImpl kStyle = new KStyleImpl();
        return kStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingLibrary createKRenderingLibrary() {
        KRenderingLibraryImpl kRenderingLibrary = new KRenderingLibraryImpl();
        return kRenderingLibrary;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingRef createKRenderingRef() {
        KRenderingRefImpl kRenderingRef = new KRenderingRefImpl();
        return kRenderingRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KChildArea createKChildArea() {
        KChildAreaImpl kChildArea = new KChildAreaImpl();
        return kChildArea;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KText createKText() {
        KTextImpl kText = new KTextImpl();
        return kText;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGridPlacement createKGridPlacement() {
        KGridPlacementImpl kGridPlacement = new KGridPlacementImpl();
        return kGridPlacement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStackPlacement createKStackPlacement() {
        KStackPlacementImpl kStackPlacement = new KStackPlacementImpl();
        return kStackPlacement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGridPlacementData createKGridPlacementData() {
        KGridPlacementDataImpl kGridPlacementData = new KGridPlacementDataImpl();
        return kGridPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStackPlacementData createKStackPlacementData() {
        KStackPlacementDataImpl kStackPlacementData = new KStackPlacementDataImpl();
        return kStackPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KDirectPlacementData createKDirectPlacementData() {
        KDirectPlacementDataImpl kDirectPlacementData = new KDirectPlacementDataImpl();
        return kDirectPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPolylinePlacementData createKPolylinePlacementData() {
        KPolylinePlacementDataImpl kPolylinePlacementData = new KPolylinePlacementDataImpl();
        return kPolylinePlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KCustomRendering createKCustomRendering() {
        KCustomRenderingImpl kCustomRendering = new KCustomRenderingImpl();
        return kCustomRendering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KForegroundColor createKForegroundColor() {
        KForegroundColorImpl kForegroundColor = new KForegroundColorImpl();
        return kForegroundColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KBackgroundColor createKBackgroundColor() {
        KBackgroundColorImpl kBackgroundColor = new KBackgroundColorImpl();
        return kBackgroundColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLineWidth createKLineWidth() {
        KLineWidthImpl kLineWidth = new KLineWidthImpl();
        return kLineWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KVisibility createKVisibility() {
        KVisibilityImpl kVisibility = new KVisibilityImpl();
        return kVisibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLineStyle createKLineStyle() {
        KLineStyleImpl kLineStyle = new KLineStyleImpl();
        return kLineStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KVerticalAlignment createKVerticalAlignment() {
        KVerticalAlignmentImpl kVerticalAlignment = new KVerticalAlignmentImpl();
        return kVerticalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KHorizontalAlignment createKHorizontalAlignment() {
        KHorizontalAlignmentImpl kHorizontalAlignment = new KHorizontalAlignmentImpl();
        return kHorizontalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLeftPosition createKLeftPosition() {
        KLeftPositionImpl kLeftPosition = new KLeftPositionImpl();
        return kLeftPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRightPosition createKRightPosition() {
        KRightPositionImpl kRightPosition = new KRightPositionImpl();
        return kRightPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KTopPosition createKTopPosition() {
        KTopPositionImpl kTopPosition = new KTopPositionImpl();
        return kTopPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KBottomPosition createKBottomPosition() {
        KBottomPositionImpl kBottomPosition = new KBottomPositionImpl();
        return kBottomPosition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KSpline createKSpline() {
        KSplineImpl kSpline = new KSplineImpl();
        return kSpline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyle createLineStyleFromString(EDataType eDataType, String initialValue) {
        LineStyle result = LineStyle.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLineStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment createHorizontalAlignmentFromString(EDataType eDataType, String initialValue) {
        HorizontalAlignment result = HorizontalAlignment.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertHorizontalAlignmentToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VerticalAlignment createVerticalAlignmentFromString(EDataType eDataType, String initialValue) {
        VerticalAlignment result = VerticalAlignment.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertVerticalAlignmentToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingPackage getKRenderingPackage() {
        return (KRenderingPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static KRenderingPackage getPackage() {
        return KRenderingPackage.eINSTANCE;
    }

} //KRenderingFactoryImpl
