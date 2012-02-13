/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import de.cau.cs.kieler.core.krendering.HorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBold;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFilled;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KItalic;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineVisible;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.krendering.VerticalAlignment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KRenderingPackageImpl extends EPackageImpl implements KRenderingPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRenderingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kEllipseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRectangleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRoundedRectangleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPolylineEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPolygonEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kImageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kDecoratorPlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kContainerRenderingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kArcEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kStyleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRenderingLibraryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRenderingRefEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kChildAreaEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kTextEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPlacementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kGridPlacementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kStackPlacementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kGridPlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kStackPlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kDirectPlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPolylinePlacementDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kCustomRenderingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kForegroundColorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kColorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kBackgroundColorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kLineWidthEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kLineVisibleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kLineStyleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kVerticalAlignmentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kHorizontalAlignmentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kxPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kyPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kLeftPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kRightPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kTopPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kBottomPositionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kSplineEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kFilledEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kItalicEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kBoldEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kFontNameEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kFontSizeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum lineStyleEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum horizontalAlignmentEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum verticalAlignmentEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private KRenderingPackageImpl() {
        super(eNS_URI, KRenderingFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link KRenderingPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static KRenderingPackage init() {
        if (isInited) return (KRenderingPackage)EPackage.Registry.INSTANCE.getEPackage(KRenderingPackage.eNS_URI);

        // Obtain or create and register package
        KRenderingPackageImpl theKRenderingPackage = (KRenderingPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KRenderingPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KRenderingPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        KGraphPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theKRenderingPackage.createPackageContents();

        // Initialize created meta-data
        theKRenderingPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theKRenderingPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(KRenderingPackage.eNS_URI, theKRenderingPackage);
        return theKRenderingPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPosition() {
        return kPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKPosition_X() {
        return (EReference)kPositionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKPosition_Y() {
        return (EReference)kPositionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRendering() {
        return kRenderingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRendering_Parent() {
        return (EReference)kRenderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRendering_References() {
        return (EReference)kRenderingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRendering_PlacementData() {
        return (EReference)kRenderingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRendering_Styles() {
        return (EReference)kRenderingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKEllipse() {
        return kEllipseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRectangle() {
        return kRectangleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRoundedRectangle() {
        return kRoundedRectangleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKRoundedRectangle_CornerWidth() {
        return (EAttribute)kRoundedRectangleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKRoundedRectangle_CornerHeight() {
        return (EAttribute)kRoundedRectangleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPolyline() {
        return kPolylineEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPolygon() {
        return kPolygonEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKImage() {
        return kImageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKImage_BundleName() {
        return (EAttribute)kImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKImage_ImagePath() {
        return (EAttribute)kImageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKDecoratorPlacementData() {
        return kDecoratorPlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKDecoratorPlacementData_Location() {
        return (EAttribute)kDecoratorPlacementDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKDecoratorPlacementData_XOffset() {
        return (EAttribute)kDecoratorPlacementDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKDecoratorPlacementData_YOffset() {
        return (EAttribute)kDecoratorPlacementDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKDecoratorPlacementData_Relative() {
        return (EAttribute)kDecoratorPlacementDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKContainerRendering() {
        return kContainerRenderingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKContainerRendering_Children() {
        return (EReference)kContainerRenderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKContainerRendering_ChildPlacement() {
        return (EReference)kContainerRenderingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKArc() {
        return kArcEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKArc_StartAngle() {
        return (EAttribute)kArcEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKArc_ArcAngle() {
        return (EAttribute)kArcEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKStyle() {
        return kStyleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKStyle_Rendering() {
        return (EReference)kStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStyle_PropagateToChildren() {
        return (EAttribute)kStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRenderingLibrary() {
        return kRenderingLibraryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRenderingLibrary_Renderings() {
        return (EReference)kRenderingLibraryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRenderingRef() {
        return kRenderingRefEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKRenderingRef_Rendering() {
        return (EReference)kRenderingRefEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKChildArea() {
        return kChildAreaEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKText() {
        return kTextEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKText_Text() {
        return (EAttribute)kTextEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKText_Clip() {
        return (EAttribute)kTextEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPlacement() {
        return kPlacementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKGridPlacement() {
        return kGridPlacementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKGridPlacement_NumColumns() {
        return (EAttribute)kGridPlacementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKStackPlacement() {
        return kStackPlacementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPlacementData() {
        return kPlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKGridPlacementData() {
        return kGridPlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKGridPlacementData_WidthHint() {
        return (EAttribute)kGridPlacementDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKGridPlacementData_HeightHint() {
        return (EAttribute)kGridPlacementDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKGridPlacementData_HorizontalIndent() {
        return (EAttribute)kGridPlacementDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKGridPlacementData_VerticalIndent() {
        return (EAttribute)kGridPlacementDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKStackPlacementData() {
        return kStackPlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStackPlacementData_InsetRight() {
        return (EAttribute)kStackPlacementDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStackPlacementData_InsetBottom() {
        return (EAttribute)kStackPlacementDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStackPlacementData_InsetLeft() {
        return (EAttribute)kStackPlacementDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStackPlacementData_InsetTop() {
        return (EAttribute)kStackPlacementDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKDirectPlacementData() {
        return kDirectPlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKDirectPlacementData_TopLeft() {
        return (EReference)kDirectPlacementDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKDirectPlacementData_BottomRight() {
        return (EReference)kDirectPlacementDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPolylinePlacementData() {
        return kPolylinePlacementDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKPolylinePlacementData_Points() {
        return (EReference)kPolylinePlacementDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKCustomRendering() {
        return kCustomRenderingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKCustomRendering_ClassName() {
        return (EAttribute)kCustomRenderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKCustomRendering_BundleName() {
        return (EAttribute)kCustomRenderingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKForegroundColor() {
        return kForegroundColorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKColor() {
        return kColorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKColor_Red() {
        return (EAttribute)kColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKColor_Green() {
        return (EAttribute)kColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKColor_Blue() {
        return (EAttribute)kColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKBackgroundColor() {
        return kBackgroundColorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKLineWidth() {
        return kLineWidthEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKLineWidth_LineWidth() {
        return (EAttribute)kLineWidthEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKLineVisible() {
        return kLineVisibleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKLineVisible_LineVisible() {
        return (EAttribute)kLineVisibleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKLineStyle() {
        return kLineStyleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKLineStyle_LineStyle() {
        return (EAttribute)kLineStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKVerticalAlignment() {
        return kVerticalAlignmentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKVerticalAlignment_VerticalAlignment() {
        return (EAttribute)kVerticalAlignmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKHorizontalAlignment() {
        return kHorizontalAlignmentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKHorizontalAlignment_HorizontalAlignment() {
        return (EAttribute)kHorizontalAlignmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKXPosition() {
        return kxPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKXPosition_Absolute() {
        return (EAttribute)kxPositionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKXPosition_Relative() {
        return (EAttribute)kxPositionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKYPosition() {
        return kyPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKYPosition_Absolute() {
        return (EAttribute)kyPositionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKYPosition_Relative() {
        return (EAttribute)kyPositionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKLeftPosition() {
        return kLeftPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKRightPosition() {
        return kRightPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKTopPosition() {
        return kTopPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKBottomPosition() {
        return kBottomPositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKSpline() {
        return kSplineEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKFilled() {
        return kFilledEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKFilled_Filled() {
        return (EAttribute)kFilledEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKItalic() {
        return kItalicEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKItalic_Italic() {
        return (EAttribute)kItalicEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKBold() {
        return kBoldEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKBold_Bold() {
        return (EAttribute)kBoldEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKFontName() {
        return kFontNameEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKFontName_Name() {
        return (EAttribute)kFontNameEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKFontSize() {
        return kFontSizeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKFontSize_Size() {
        return (EAttribute)kFontSizeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getLineStyle() {
        return lineStyleEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getHorizontalAlignment() {
        return horizontalAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getVerticalAlignment() {
        return verticalAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingFactory getKRenderingFactory() {
        return (KRenderingFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        kPositionEClass = createEClass(KPOSITION);
        createEReference(kPositionEClass, KPOSITION__X);
        createEReference(kPositionEClass, KPOSITION__Y);

        kRenderingEClass = createEClass(KRENDERING);
        createEReference(kRenderingEClass, KRENDERING__PARENT);
        createEReference(kRenderingEClass, KRENDERING__REFERENCES);
        createEReference(kRenderingEClass, KRENDERING__PLACEMENT_DATA);
        createEReference(kRenderingEClass, KRENDERING__STYLES);

        kEllipseEClass = createEClass(KELLIPSE);

        kRectangleEClass = createEClass(KRECTANGLE);

        kRoundedRectangleEClass = createEClass(KROUNDED_RECTANGLE);
        createEAttribute(kRoundedRectangleEClass, KROUNDED_RECTANGLE__CORNER_WIDTH);
        createEAttribute(kRoundedRectangleEClass, KROUNDED_RECTANGLE__CORNER_HEIGHT);

        kPolylineEClass = createEClass(KPOLYLINE);

        kPolygonEClass = createEClass(KPOLYGON);

        kImageEClass = createEClass(KIMAGE);
        createEAttribute(kImageEClass, KIMAGE__BUNDLE_NAME);
        createEAttribute(kImageEClass, KIMAGE__IMAGE_PATH);

        kDecoratorPlacementDataEClass = createEClass(KDECORATOR_PLACEMENT_DATA);
        createEAttribute(kDecoratorPlacementDataEClass, KDECORATOR_PLACEMENT_DATA__LOCATION);
        createEAttribute(kDecoratorPlacementDataEClass, KDECORATOR_PLACEMENT_DATA__XOFFSET);
        createEAttribute(kDecoratorPlacementDataEClass, KDECORATOR_PLACEMENT_DATA__YOFFSET);
        createEAttribute(kDecoratorPlacementDataEClass, KDECORATOR_PLACEMENT_DATA__RELATIVE);

        kContainerRenderingEClass = createEClass(KCONTAINER_RENDERING);
        createEReference(kContainerRenderingEClass, KCONTAINER_RENDERING__CHILDREN);
        createEReference(kContainerRenderingEClass, KCONTAINER_RENDERING__CHILD_PLACEMENT);

        kArcEClass = createEClass(KARC);
        createEAttribute(kArcEClass, KARC__START_ANGLE);
        createEAttribute(kArcEClass, KARC__ARC_ANGLE);

        kStyleEClass = createEClass(KSTYLE);
        createEReference(kStyleEClass, KSTYLE__RENDERING);
        createEAttribute(kStyleEClass, KSTYLE__PROPAGATE_TO_CHILDREN);

        kRenderingLibraryEClass = createEClass(KRENDERING_LIBRARY);
        createEReference(kRenderingLibraryEClass, KRENDERING_LIBRARY__RENDERINGS);

        kRenderingRefEClass = createEClass(KRENDERING_REF);
        createEReference(kRenderingRefEClass, KRENDERING_REF__RENDERING);

        kChildAreaEClass = createEClass(KCHILD_AREA);

        kTextEClass = createEClass(KTEXT);
        createEAttribute(kTextEClass, KTEXT__TEXT);
        createEAttribute(kTextEClass, KTEXT__CLIP);

        kPlacementEClass = createEClass(KPLACEMENT);

        kGridPlacementEClass = createEClass(KGRID_PLACEMENT);
        createEAttribute(kGridPlacementEClass, KGRID_PLACEMENT__NUM_COLUMNS);

        kStackPlacementEClass = createEClass(KSTACK_PLACEMENT);

        kPlacementDataEClass = createEClass(KPLACEMENT_DATA);

        kGridPlacementDataEClass = createEClass(KGRID_PLACEMENT_DATA);
        createEAttribute(kGridPlacementDataEClass, KGRID_PLACEMENT_DATA__WIDTH_HINT);
        createEAttribute(kGridPlacementDataEClass, KGRID_PLACEMENT_DATA__HEIGHT_HINT);
        createEAttribute(kGridPlacementDataEClass, KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT);
        createEAttribute(kGridPlacementDataEClass, KGRID_PLACEMENT_DATA__VERTICAL_INDENT);

        kStackPlacementDataEClass = createEClass(KSTACK_PLACEMENT_DATA);
        createEAttribute(kStackPlacementDataEClass, KSTACK_PLACEMENT_DATA__INSET_RIGHT);
        createEAttribute(kStackPlacementDataEClass, KSTACK_PLACEMENT_DATA__INSET_BOTTOM);
        createEAttribute(kStackPlacementDataEClass, KSTACK_PLACEMENT_DATA__INSET_LEFT);
        createEAttribute(kStackPlacementDataEClass, KSTACK_PLACEMENT_DATA__INSET_TOP);

        kDirectPlacementDataEClass = createEClass(KDIRECT_PLACEMENT_DATA);
        createEReference(kDirectPlacementDataEClass, KDIRECT_PLACEMENT_DATA__TOP_LEFT);
        createEReference(kDirectPlacementDataEClass, KDIRECT_PLACEMENT_DATA__BOTTOM_RIGHT);

        kPolylinePlacementDataEClass = createEClass(KPOLYLINE_PLACEMENT_DATA);
        createEReference(kPolylinePlacementDataEClass, KPOLYLINE_PLACEMENT_DATA__POINTS);

        kCustomRenderingEClass = createEClass(KCUSTOM_RENDERING);
        createEAttribute(kCustomRenderingEClass, KCUSTOM_RENDERING__CLASS_NAME);
        createEAttribute(kCustomRenderingEClass, KCUSTOM_RENDERING__BUNDLE_NAME);

        kForegroundColorEClass = createEClass(KFOREGROUND_COLOR);

        kColorEClass = createEClass(KCOLOR);
        createEAttribute(kColorEClass, KCOLOR__RED);
        createEAttribute(kColorEClass, KCOLOR__GREEN);
        createEAttribute(kColorEClass, KCOLOR__BLUE);

        kBackgroundColorEClass = createEClass(KBACKGROUND_COLOR);

        kLineWidthEClass = createEClass(KLINE_WIDTH);
        createEAttribute(kLineWidthEClass, KLINE_WIDTH__LINE_WIDTH);

        kLineVisibleEClass = createEClass(KLINE_VISIBLE);
        createEAttribute(kLineVisibleEClass, KLINE_VISIBLE__LINE_VISIBLE);

        kLineStyleEClass = createEClass(KLINE_STYLE);
        createEAttribute(kLineStyleEClass, KLINE_STYLE__LINE_STYLE);

        kVerticalAlignmentEClass = createEClass(KVERTICAL_ALIGNMENT);
        createEAttribute(kVerticalAlignmentEClass, KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT);

        kHorizontalAlignmentEClass = createEClass(KHORIZONTAL_ALIGNMENT);
        createEAttribute(kHorizontalAlignmentEClass, KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT);

        kxPositionEClass = createEClass(KX_POSITION);
        createEAttribute(kxPositionEClass, KX_POSITION__ABSOLUTE);
        createEAttribute(kxPositionEClass, KX_POSITION__RELATIVE);

        kyPositionEClass = createEClass(KY_POSITION);
        createEAttribute(kyPositionEClass, KY_POSITION__ABSOLUTE);
        createEAttribute(kyPositionEClass, KY_POSITION__RELATIVE);

        kLeftPositionEClass = createEClass(KLEFT_POSITION);

        kRightPositionEClass = createEClass(KRIGHT_POSITION);

        kTopPositionEClass = createEClass(KTOP_POSITION);

        kBottomPositionEClass = createEClass(KBOTTOM_POSITION);

        kSplineEClass = createEClass(KSPLINE);

        kFilledEClass = createEClass(KFILLED);
        createEAttribute(kFilledEClass, KFILLED__FILLED);

        kItalicEClass = createEClass(KITALIC);
        createEAttribute(kItalicEClass, KITALIC__ITALIC);

        kBoldEClass = createEClass(KBOLD);
        createEAttribute(kBoldEClass, KBOLD__BOLD);

        kFontNameEClass = createEClass(KFONT_NAME);
        createEAttribute(kFontNameEClass, KFONT_NAME__NAME);

        kFontSizeEClass = createEClass(KFONT_SIZE);
        createEAttribute(kFontSizeEClass, KFONT_SIZE__SIZE);

        // Create enums
        lineStyleEEnum = createEEnum(LINE_STYLE);
        horizontalAlignmentEEnum = createEEnum(HORIZONTAL_ALIGNMENT);
        verticalAlignmentEEnum = createEEnum(VERTICAL_ALIGNMENT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        KGraphPackage theKGraphPackage = (KGraphPackage)EPackage.Registry.INSTANCE.getEPackage(KGraphPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        kRenderingEClass.getESuperTypes().add(theKGraphPackage.getKGraphData());
        kEllipseEClass.getESuperTypes().add(this.getKContainerRendering());
        kRectangleEClass.getESuperTypes().add(this.getKContainerRendering());
        kRoundedRectangleEClass.getESuperTypes().add(this.getKContainerRendering());
        kPolylineEClass.getESuperTypes().add(this.getKContainerRendering());
        kPolygonEClass.getESuperTypes().add(this.getKPolyline());
        kImageEClass.getESuperTypes().add(this.getKContainerRendering());
        kDecoratorPlacementDataEClass.getESuperTypes().add(this.getKPlacementData());
        kContainerRenderingEClass.getESuperTypes().add(this.getKRendering());
        kArcEClass.getESuperTypes().add(this.getKContainerRendering());
        kRenderingLibraryEClass.getESuperTypes().add(theKGraphPackage.getKGraphData());
        kRenderingRefEClass.getESuperTypes().add(this.getKRendering());
        kChildAreaEClass.getESuperTypes().add(this.getKRendering());
        kTextEClass.getESuperTypes().add(this.getKContainerRendering());
        kGridPlacementEClass.getESuperTypes().add(this.getKPlacement());
        kStackPlacementEClass.getESuperTypes().add(this.getKPlacement());
        kGridPlacementDataEClass.getESuperTypes().add(this.getKPlacementData());
        kStackPlacementDataEClass.getESuperTypes().add(this.getKPlacementData());
        kDirectPlacementDataEClass.getESuperTypes().add(this.getKPlacementData());
        kPolylinePlacementDataEClass.getESuperTypes().add(this.getKPlacementData());
        kCustomRenderingEClass.getESuperTypes().add(this.getKContainerRendering());
        kForegroundColorEClass.getESuperTypes().add(this.getKColor());
        kColorEClass.getESuperTypes().add(this.getKStyle());
        kBackgroundColorEClass.getESuperTypes().add(this.getKColor());
        kLineWidthEClass.getESuperTypes().add(this.getKStyle());
        kLineVisibleEClass.getESuperTypes().add(this.getKStyle());
        kLineStyleEClass.getESuperTypes().add(this.getKStyle());
        kVerticalAlignmentEClass.getESuperTypes().add(this.getKStyle());
        kHorizontalAlignmentEClass.getESuperTypes().add(this.getKStyle());
        kLeftPositionEClass.getESuperTypes().add(this.getKXPosition());
        kRightPositionEClass.getESuperTypes().add(this.getKXPosition());
        kTopPositionEClass.getESuperTypes().add(this.getKYPosition());
        kBottomPositionEClass.getESuperTypes().add(this.getKYPosition());
        kSplineEClass.getESuperTypes().add(this.getKPolyline());
        kFilledEClass.getESuperTypes().add(this.getKStyle());
        kItalicEClass.getESuperTypes().add(this.getKStyle());
        kBoldEClass.getESuperTypes().add(this.getKStyle());
        kFontNameEClass.getESuperTypes().add(this.getKStyle());
        kFontSizeEClass.getESuperTypes().add(this.getKStyle());

        // Initialize classes and features; add operations and parameters
        initEClass(kPositionEClass, KPosition.class, "KPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKPosition_X(), this.getKXPosition(), null, "x", null, 1, 1, KPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKPosition_Y(), this.getKYPosition(), null, "y", null, 1, 1, KPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kRenderingEClass, KRendering.class, "KRendering", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKRendering_Parent(), this.getKContainerRendering(), this.getKContainerRendering_Children(), "parent", null, 0, 1, KRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKRendering_References(), this.getKRenderingRef(), this.getKRenderingRef_Rendering(), "references", null, 0, -1, KRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKRendering_PlacementData(), this.getKPlacementData(), null, "placementData", null, 0, 1, KRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKRendering_Styles(), this.getKStyle(), this.getKStyle_Rendering(), "styles", null, 0, -1, KRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kEllipseEClass, KEllipse.class, "KEllipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kRectangleEClass, KRectangle.class, "KRectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kRoundedRectangleEClass, KRoundedRectangle.class, "KRoundedRectangle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKRoundedRectangle_CornerWidth(), ecorePackage.getEFloat(), "cornerWidth", null, 1, 1, KRoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKRoundedRectangle_CornerHeight(), ecorePackage.getEFloat(), "cornerHeight", null, 1, 1, KRoundedRectangle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kPolylineEClass, KPolyline.class, "KPolyline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kPolygonEClass, KPolygon.class, "KPolygon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kImageEClass, KImage.class, "KImage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKImage_BundleName(), ecorePackage.getEString(), "bundleName", null, 1, 1, KImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKImage_ImagePath(), ecorePackage.getEString(), "imagePath", null, 1, 1, KImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kDecoratorPlacementDataEClass, KDecoratorPlacementData.class, "KDecoratorPlacementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKDecoratorPlacementData_Location(), ecorePackage.getEFloat(), "location", null, 1, 1, KDecoratorPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKDecoratorPlacementData_XOffset(), ecorePackage.getEFloat(), "xOffset", null, 0, 1, KDecoratorPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKDecoratorPlacementData_YOffset(), ecorePackage.getEFloat(), "yOffset", null, 0, 1, KDecoratorPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKDecoratorPlacementData_Relative(), ecorePackage.getEBoolean(), "relative", null, 1, 1, KDecoratorPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kContainerRenderingEClass, KContainerRendering.class, "KContainerRendering", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKContainerRendering_Children(), this.getKRendering(), this.getKRendering_Parent(), "children", null, 0, -1, KContainerRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKContainerRendering_ChildPlacement(), this.getKPlacement(), null, "childPlacement", null, 0, 1, KContainerRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kArcEClass, KArc.class, "KArc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKArc_StartAngle(), ecorePackage.getEFloat(), "startAngle", null, 0, 1, KArc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKArc_ArcAngle(), ecorePackage.getEFloat(), "arcAngle", null, 0, 1, KArc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kStyleEClass, KStyle.class, "KStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKStyle_Rendering(), this.getKRendering(), this.getKRendering_Styles(), "rendering", null, 0, 1, KStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStyle_PropagateToChildren(), ecorePackage.getEBoolean(), "propagateToChildren", null, 1, 1, KStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kRenderingLibraryEClass, KRenderingLibrary.class, "KRenderingLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKRenderingLibrary_Renderings(), this.getKRendering(), null, "renderings", null, 0, -1, KRenderingLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kRenderingRefEClass, KRenderingRef.class, "KRenderingRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKRenderingRef_Rendering(), this.getKRendering(), this.getKRendering_References(), "rendering", null, 1, 1, KRenderingRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kChildAreaEClass, KChildArea.class, "KChildArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kTextEClass, KText.class, "KText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKText_Text(), ecorePackage.getEString(), "text", null, 0, 1, KText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKText_Clip(), ecorePackage.getEBoolean(), "clip", null, 1, 1, KText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kPlacementEClass, KPlacement.class, "KPlacement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kGridPlacementEClass, KGridPlacement.class, "KGridPlacement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKGridPlacement_NumColumns(), ecorePackage.getEInt(), "numColumns", null, 1, 1, KGridPlacement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kStackPlacementEClass, KStackPlacement.class, "KStackPlacement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kPlacementDataEClass, KPlacementData.class, "KPlacementData", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kGridPlacementDataEClass, KGridPlacementData.class, "KGridPlacementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKGridPlacementData_WidthHint(), ecorePackage.getEFloat(), "widthHint", null, 1, 1, KGridPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKGridPlacementData_HeightHint(), ecorePackage.getEFloat(), "heightHint", null, 1, 1, KGridPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKGridPlacementData_HorizontalIndent(), ecorePackage.getEFloat(), "horizontalIndent", null, 1, 1, KGridPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKGridPlacementData_VerticalIndent(), ecorePackage.getEFloat(), "verticalIndent", null, 1, 1, KGridPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kStackPlacementDataEClass, KStackPlacementData.class, "KStackPlacementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKStackPlacementData_InsetRight(), ecorePackage.getEFloat(), "insetRight", null, 1, 1, KStackPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStackPlacementData_InsetBottom(), ecorePackage.getEFloat(), "insetBottom", null, 1, 1, KStackPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStackPlacementData_InsetLeft(), ecorePackage.getEFloat(), "insetLeft", null, 1, 1, KStackPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStackPlacementData_InsetTop(), ecorePackage.getEFloat(), "insetTop", null, 1, 1, KStackPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kDirectPlacementDataEClass, KDirectPlacementData.class, "KDirectPlacementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKDirectPlacementData_TopLeft(), this.getKPosition(), null, "topLeft", null, 1, 1, KDirectPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKDirectPlacementData_BottomRight(), this.getKPosition(), null, "bottomRight", null, 1, 1, KDirectPlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kPolylinePlacementDataEClass, KPolylinePlacementData.class, "KPolylinePlacementData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKPolylinePlacementData_Points(), this.getKPosition(), null, "points", null, 1, -1, KPolylinePlacementData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kCustomRenderingEClass, KCustomRendering.class, "KCustomRendering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKCustomRendering_ClassName(), ecorePackage.getEString(), "className", null, 1, 1, KCustomRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKCustomRendering_BundleName(), ecorePackage.getEString(), "bundleName", null, 1, 1, KCustomRendering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kForegroundColorEClass, KForegroundColor.class, "KForegroundColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kColorEClass, KColor.class, "KColor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKColor_Red(), ecorePackage.getEInt(), "red", null, 1, 1, KColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKColor_Green(), ecorePackage.getEInt(), "green", null, 1, 1, KColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKColor_Blue(), ecorePackage.getEInt(), "blue", null, 1, 1, KColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kBackgroundColorEClass, KBackgroundColor.class, "KBackgroundColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kLineWidthEClass, KLineWidth.class, "KLineWidth", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKLineWidth_LineWidth(), ecorePackage.getEInt(), "lineWidth", null, 1, 1, KLineWidth.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kLineVisibleEClass, KLineVisible.class, "KLineVisible", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKLineVisible_LineVisible(), ecorePackage.getEBoolean(), "lineVisible", null, 1, 1, KLineVisible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kLineStyleEClass, KLineStyle.class, "KLineStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKLineStyle_LineStyle(), this.getLineStyle(), "lineStyle", null, 1, 1, KLineStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kVerticalAlignmentEClass, KVerticalAlignment.class, "KVerticalAlignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKVerticalAlignment_VerticalAlignment(), this.getVerticalAlignment(), "verticalAlignment", null, 1, 1, KVerticalAlignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kHorizontalAlignmentEClass, KHorizontalAlignment.class, "KHorizontalAlignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKHorizontalAlignment_HorizontalAlignment(), this.getHorizontalAlignment(), "horizontalAlignment", null, 1, 1, KHorizontalAlignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kxPositionEClass, KXPosition.class, "KXPosition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKXPosition_Absolute(), ecorePackage.getEFloat(), "absolute", null, 0, 1, KXPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKXPosition_Relative(), ecorePackage.getEFloat(), "relative", null, 0, 1, KXPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kyPositionEClass, KYPosition.class, "KYPosition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKYPosition_Absolute(), ecorePackage.getEFloat(), "absolute", null, 0, 1, KYPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKYPosition_Relative(), ecorePackage.getEFloat(), "relative", null, 0, 1, KYPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kLeftPositionEClass, KLeftPosition.class, "KLeftPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kRightPositionEClass, KRightPosition.class, "KRightPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kTopPositionEClass, KTopPosition.class, "KTopPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kBottomPositionEClass, KBottomPosition.class, "KBottomPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kSplineEClass, KSpline.class, "KSpline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(kFilledEClass, KFilled.class, "KFilled", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKFilled_Filled(), ecorePackage.getEBoolean(), "filled", null, 1, 1, KFilled.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kItalicEClass, KItalic.class, "KItalic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKItalic_Italic(), ecorePackage.getEBoolean(), "italic", null, 1, 1, KItalic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kBoldEClass, KBold.class, "KBold", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKBold_Bold(), ecorePackage.getEBoolean(), "bold", null, 1, 1, KBold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kFontNameEClass, KFontName.class, "KFontName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKFontName_Name(), ecorePackage.getEString(), "name", null, 1, 1, KFontName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kFontSizeEClass, KFontSize.class, "KFontSize", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKFontSize_Size(), ecorePackage.getEInt(), "size", null, 1, 1, KFontSize.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(lineStyleEEnum, LineStyle.class, "LineStyle");
        addEEnumLiteral(lineStyleEEnum, LineStyle.SOLID);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASH);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DOT);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOT);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASHDOTDOT);

        initEEnum(horizontalAlignmentEEnum, HorizontalAlignment.class, "HorizontalAlignment");
        addEEnumLiteral(horizontalAlignmentEEnum, HorizontalAlignment.LEFT);
        addEEnumLiteral(horizontalAlignmentEEnum, HorizontalAlignment.CENTER);
        addEEnumLiteral(horizontalAlignmentEEnum, HorizontalAlignment.RIGHT);

        initEEnum(verticalAlignmentEEnum, VerticalAlignment.class, "VerticalAlignment");
        addEEnumLiteral(verticalAlignmentEEnum, VerticalAlignment.TOP);
        addEEnumLiteral(verticalAlignmentEEnum, VerticalAlignment.CENTER);
        addEEnumLiteral(verticalAlignmentEEnum, VerticalAlignment.BOTTOM);

        // Create resource
        createResource(eNS_URI);
    }

} //KRenderingPackageImpl
