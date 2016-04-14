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
package de.cau.cs.kieler.klighd.krendering.impl;

import de.cau.cs.kieler.klighd.krendering.*;

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
            KRenderingFactory theKRenderingFactory = (KRenderingFactory)EPackage.Registry.INSTANCE.getEFactory(KRenderingPackage.eNS_URI);
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
            case KRenderingPackage.KRENDERING_LIBRARY: return createKRenderingLibrary();
            case KRenderingPackage.KRENDERING_REF: return createKRenderingRef();
            case KRenderingPackage.KCHILD_AREA: return createKChildArea();
            case KRenderingPackage.KTEXT: return createKText();
            case KRenderingPackage.KGRID_PLACEMENT: return createKGridPlacement();
            case KRenderingPackage.KGRID_PLACEMENT_DATA: return createKGridPlacementData();
            case KRenderingPackage.KAREA_PLACEMENT_DATA: return createKAreaPlacementData();
            case KRenderingPackage.KCUSTOM_RENDERING: return createKCustomRendering();
            case KRenderingPackage.KCOLOR: return createKColor();
            case KRenderingPackage.KLINE_WIDTH: return createKLineWidth();
            case KRenderingPackage.KLINE_STYLE: return createKLineStyle();
            case KRenderingPackage.KVERTICAL_ALIGNMENT: return createKVerticalAlignment();
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT: return createKHorizontalAlignment();
            case KRenderingPackage.KLEFT_POSITION: return createKLeftPosition();
            case KRenderingPackage.KRIGHT_POSITION: return createKRightPosition();
            case KRenderingPackage.KTOP_POSITION: return createKTopPosition();
            case KRenderingPackage.KBOTTOM_POSITION: return createKBottomPosition();
            case KRenderingPackage.KSPLINE: return createKSpline();
            case KRenderingPackage.KFOREGROUND: return createKForeground();
            case KRenderingPackage.KBACKGROUND: return createKBackground();
            case KRenderingPackage.KFONT_BOLD: return createKFontBold();
            case KRenderingPackage.KFONT_ITALIC: return createKFontItalic();
            case KRenderingPackage.KFONT_NAME: return createKFontName();
            case KRenderingPackage.KFONT_SIZE: return createKFontSize();
            case KRenderingPackage.KROUNDED_BENDS_POLYLINE: return createKRoundedBendsPolyline();
            case KRenderingPackage.KROTATION: return createKRotation();
            case KRenderingPackage.KLINE_CAP: return createKLineCap();
            case KRenderingPackage.KACTION: return createKAction();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA: return createKPointPlacementData();
            case KRenderingPackage.KSTYLE_HOLDER: return createKStyleHolder();
            case KRenderingPackage.KINVISIBILITY: return createKInvisibility();
            case KRenderingPackage.KSHADOW: return createKShadow();
            case KRenderingPackage.KTEXT_UNDERLINE: return createKTextUnderline();
            case KRenderingPackage.KSTYLE_REF: return createKStyleRef();
            case KRenderingPackage.KTEXT_STRIKEOUT: return createKTextStrikeout();
            case KRenderingPackage.KLINE_JOIN: return createKLineJoin();
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
            case KRenderingPackage.LINE_CAP:
                return createLineCapFromString(eDataType, initialValue);
            case KRenderingPackage.TRIGGER:
                return createTriggerFromString(eDataType, initialValue);
            case KRenderingPackage.UNDERLINE:
                return createUnderlineFromString(eDataType, initialValue);
            case KRenderingPackage.LINE_JOIN:
                return createLineJoinFromString(eDataType, initialValue);
            case KRenderingPackage.ARC:
                return createArcFromString(eDataType, initialValue);
            case KRenderingPackage.COLORS:
                return createColorsFromString(eDataType, initialValue);
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
            case KRenderingPackage.LINE_CAP:
                return convertLineCapToString(eDataType, instanceValue);
            case KRenderingPackage.TRIGGER:
                return convertTriggerToString(eDataType, instanceValue);
            case KRenderingPackage.UNDERLINE:
                return convertUnderlineToString(eDataType, instanceValue);
            case KRenderingPackage.LINE_JOIN:
                return convertLineJoinToString(eDataType, instanceValue);
            case KRenderingPackage.ARC:
                return convertArcToString(eDataType, instanceValue);
            case KRenderingPackage.COLORS:
                return convertColorsToString(eDataType, instanceValue);
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
    public KGridPlacementData createKGridPlacementData() {
        KGridPlacementDataImpl kGridPlacementData = new KGridPlacementDataImpl();
        return kGridPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KAreaPlacementData createKAreaPlacementData() {
        KAreaPlacementDataImpl kAreaPlacementData = new KAreaPlacementDataImpl();
        return kAreaPlacementData;
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
    public KColor createKColor() {
        KColorImpl kColor = new KColorImpl();
        return kColor;
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
    public KForeground createKForeground() {
        KForegroundImpl kForeground = new KForegroundImpl();
        return kForeground;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KBackground createKBackground() {
        KBackgroundImpl kBackground = new KBackgroundImpl();
        return kBackground;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KFontBold createKFontBold() {
        KFontBoldImpl kFontBold = new KFontBoldImpl();
        return kFontBold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KFontItalic createKFontItalic() {
        KFontItalicImpl kFontItalic = new KFontItalicImpl();
        return kFontItalic;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KFontName createKFontName() {
        KFontNameImpl kFontName = new KFontNameImpl();
        return kFontName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KFontSize createKFontSize() {
        KFontSizeImpl kFontSize = new KFontSizeImpl();
        return kFontSize;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRoundedBendsPolyline createKRoundedBendsPolyline() {
        KRoundedBendsPolylineImpl kRoundedBendsPolyline = new KRoundedBendsPolylineImpl();
        return kRoundedBendsPolyline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRotation createKRotation() {
        KRotationImpl kRotation = new KRotationImpl();
        return kRotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLineCap createKLineCap() {
        KLineCapImpl kLineCap = new KLineCapImpl();
        return kLineCap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KAction createKAction() {
        KActionImpl kAction = new KActionImpl();
        return kAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPointPlacementData createKPointPlacementData() {
        KPointPlacementDataImpl kPointPlacementData = new KPointPlacementDataImpl();
        return kPointPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleHolder createKStyleHolder() {
        KStyleHolderImpl kStyleHolder = new KStyleHolderImpl();
        return kStyleHolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KInvisibility createKInvisibility() {
        KInvisibilityImpl kInvisibility = new KInvisibilityImpl();
        return kInvisibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KShadow createKShadow() {
        KShadowImpl kShadow = new KShadowImpl();
        return kShadow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KTextUnderline createKTextUnderline() {
        KTextUnderlineImpl kTextUnderline = new KTextUnderlineImpl();
        return kTextUnderline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleRef createKStyleRef() {
        KStyleRefImpl kStyleRef = new KStyleRefImpl();
        return kStyleRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KTextStrikeout createKTextStrikeout() {
        KTextStrikeoutImpl kTextStrikeout = new KTextStrikeoutImpl();
        return kTextStrikeout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLineJoin createKLineJoin() {
        KLineJoinImpl kLineJoin = new KLineJoinImpl();
        return kLineJoin;
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
    public Trigger createTriggerFromString(EDataType eDataType, String initialValue) {
        Trigger result = Trigger.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTriggerToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineCap createLineCapFromString(EDataType eDataType, String initialValue) {
        LineCap result = LineCap.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLineCapToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Underline createUnderlineFromString(EDataType eDataType, String initialValue) {
        Underline result = Underline.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertUnderlineToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineJoin createLineJoinFromString(EDataType eDataType, String initialValue) {
        LineJoin result = LineJoin.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLineJoinToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Arc createArcFromString(EDataType eDataType, String initialValue) {
        Arc result = Arc.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertArcToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Colors createColorsFromString(EDataType eDataType, String initialValue) {
        return (Colors)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertColorsToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
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
