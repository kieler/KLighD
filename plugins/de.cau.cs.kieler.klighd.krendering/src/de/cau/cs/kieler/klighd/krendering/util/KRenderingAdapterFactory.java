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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage
 * @generated
 */
public class KRenderingAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KRenderingPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRenderingAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = KRenderingPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRenderingSwitch<Adapter> modelSwitch =
        new KRenderingSwitch<Adapter>() {
            @Override
            public Adapter caseKPosition(KPosition object) {
                return createKPositionAdapter();
            }
            @Override
            public Adapter caseKRendering(KRendering object) {
                return createKRenderingAdapter();
            }
            @Override
            public Adapter caseKEllipse(KEllipse object) {
                return createKEllipseAdapter();
            }
            @Override
            public Adapter caseKRectangle(KRectangle object) {
                return createKRectangleAdapter();
            }
            @Override
            public Adapter caseKRoundedRectangle(KRoundedRectangle object) {
                return createKRoundedRectangleAdapter();
            }
            @Override
            public Adapter caseKPolyline(KPolyline object) {
                return createKPolylineAdapter();
            }
            @Override
            public Adapter caseKPolygon(KPolygon object) {
                return createKPolygonAdapter();
            }
            @Override
            public Adapter caseKImage(KImage object) {
                return createKImageAdapter();
            }
            @Override
            public Adapter caseKDecoratorPlacementData(KDecoratorPlacementData object) {
                return createKDecoratorPlacementDataAdapter();
            }
            @Override
            public Adapter caseKContainerRendering(KContainerRendering object) {
                return createKContainerRenderingAdapter();
            }
            @Override
            public Adapter caseKArc(KArc object) {
                return createKArcAdapter();
            }
            @Override
            public Adapter caseKStyle(KStyle object) {
                return createKStyleAdapter();
            }
            @Override
            public Adapter caseKRenderingLibrary(KRenderingLibrary object) {
                return createKRenderingLibraryAdapter();
            }
            @Override
            public Adapter caseKRenderingRef(KRenderingRef object) {
                return createKRenderingRefAdapter();
            }
            @Override
            public Adapter caseKChildArea(KChildArea object) {
                return createKChildAreaAdapter();
            }
            @Override
            public Adapter caseKText(KText object) {
                return createKTextAdapter();
            }
            @Override
            public Adapter caseKPlacement(KPlacement object) {
                return createKPlacementAdapter();
            }
            @Override
            public Adapter caseKGridPlacement(KGridPlacement object) {
                return createKGridPlacementAdapter();
            }
            @Override
            public Adapter caseKPlacementData(KPlacementData object) {
                return createKPlacementDataAdapter();
            }
            @Override
            public Adapter caseKGridPlacementData(KGridPlacementData object) {
                return createKGridPlacementDataAdapter();
            }
            @Override
            public Adapter caseKAreaPlacementData(KAreaPlacementData object) {
                return createKAreaPlacementDataAdapter();
            }
            @Override
            public Adapter caseKCustomRendering(KCustomRendering object) {
                return createKCustomRenderingAdapter();
            }
            @Override
            public Adapter caseKColor(KColor object) {
                return createKColorAdapter();
            }
            @Override
            public Adapter caseKLineWidth(KLineWidth object) {
                return createKLineWidthAdapter();
            }
            @Override
            public Adapter caseKLineStyle(KLineStyle object) {
                return createKLineStyleAdapter();
            }
            @Override
            public Adapter caseKVerticalAlignment(KVerticalAlignment object) {
                return createKVerticalAlignmentAdapter();
            }
            @Override
            public Adapter caseKHorizontalAlignment(KHorizontalAlignment object) {
                return createKHorizontalAlignmentAdapter();
            }
            @Override
            public <T extends KXPosition<T>> Adapter caseKXPosition(KXPosition<T> object) {
                return createKXPositionAdapter();
            }
            @Override
            public <T extends KYPosition<T>> Adapter caseKYPosition(KYPosition<T> object) {
                return createKYPositionAdapter();
            }
            @Override
            public Adapter caseKLeftPosition(KLeftPosition object) {
                return createKLeftPositionAdapter();
            }
            @Override
            public Adapter caseKRightPosition(KRightPosition object) {
                return createKRightPositionAdapter();
            }
            @Override
            public Adapter caseKTopPosition(KTopPosition object) {
                return createKTopPositionAdapter();
            }
            @Override
            public Adapter caseKBottomPosition(KBottomPosition object) {
                return createKBottomPositionAdapter();
            }
            @Override
            public Adapter caseKSpline(KSpline object) {
                return createKSplineAdapter();
            }
            @Override
            public Adapter caseKForeground(KForeground object) {
                return createKForegroundAdapter();
            }
            @Override
            public <T extends KColoring<T>> Adapter caseKColoring(KColoring<T> object) {
                return createKColoringAdapter();
            }
            @Override
            public Adapter caseKBackground(KBackground object) {
                return createKBackgroundAdapter();
            }
            @Override
            public Adapter caseKFontBold(KFontBold object) {
                return createKFontBoldAdapter();
            }
            @Override
            public Adapter caseKFontItalic(KFontItalic object) {
                return createKFontItalicAdapter();
            }
            @Override
            public Adapter caseKFontName(KFontName object) {
                return createKFontNameAdapter();
            }
            @Override
            public Adapter caseKFontSize(KFontSize object) {
                return createKFontSizeAdapter();
            }
            @Override
            public Adapter caseKRoundedBendsPolyline(KRoundedBendsPolyline object) {
                return createKRoundedBendsPolylineAdapter();
            }
            @Override
            public Adapter caseKRotation(KRotation object) {
                return createKRotationAdapter();
            }
            @Override
            public Adapter caseKLineCap(KLineCap object) {
                return createKLineCapAdapter();
            }
            @Override
            public Adapter caseKAction(KAction object) {
                return createKActionAdapter();
            }
            @Override
            public Adapter caseKPointPlacementData(KPointPlacementData object) {
                return createKPointPlacementDataAdapter();
            }
            @Override
            public Adapter caseKStyleHolder(KStyleHolder object) {
                return createKStyleHolderAdapter();
            }
            @Override
            public Adapter caseKInvisibility(KInvisibility object) {
                return createKInvisibilityAdapter();
            }
            @Override
            public Adapter caseKShadow(KShadow object) {
                return createKShadowAdapter();
            }
            @Override
            public Adapter caseKTextUnderline(KTextUnderline object) {
                return createKTextUnderlineAdapter();
            }
            @Override
            public Adapter caseKStyleRef(KStyleRef object) {
                return createKStyleRefAdapter();
            }
            @Override
            public Adapter caseKTextStrikeout(KTextStrikeout object) {
                return createKTextStrikeoutAdapter();
            }
            @Override
            public Adapter caseKLineJoin(KLineJoin object) {
                return createKLineJoinAdapter();
            }
            @Override
            public Adapter caseIPropertyHolder(IPropertyHolder object) {
                return createIPropertyHolderAdapter();
            }
            @Override
            public Adapter caseEMapPropertyHolder(EMapPropertyHolder object) {
                return createEMapPropertyHolderAdapter();
            }
            @Override
            public Adapter caseKGraphData(KGraphData object) {
                return createKGraphDataAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPosition <em>KPosition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPosition
     * @generated
     */
    public Adapter createKPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRendering <em>KRendering</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRendering
     * @generated
     */
    public Adapter createKRenderingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KEllipse <em>KEllipse</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KEllipse
     * @generated
     */
    public Adapter createKEllipseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRectangle <em>KRectangle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRectangle
     * @generated
     */
    public Adapter createKRectangleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle <em>KRounded Rectangle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
     * @generated
     */
    public Adapter createKRoundedRectangleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPolyline <em>KPolyline</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPolyline
     * @generated
     */
    public Adapter createKPolylineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPolygon <em>KPolygon</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPolygon
     * @generated
     */
    public Adapter createKPolygonAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KImage <em>KImage</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KImage
     * @generated
     */
    public Adapter createKImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData <em>KDecorator Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData
     * @generated
     */
    public Adapter createKDecoratorPlacementDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering <em>KContainer Rendering</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KContainerRendering
     * @generated
     */
    public Adapter createKContainerRenderingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KArc <em>KArc</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KArc
     * @generated
     */
    public Adapter createKArcAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KStyle <em>KStyle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KStyle
     * @generated
     */
    public Adapter createKStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRenderingLibrary <em>Library</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
     * @generated
     */
    public Adapter createKRenderingLibraryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRenderingRef <em>Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingRef
     * @generated
     */
    public Adapter createKRenderingRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KChildArea <em>KChild Area</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KChildArea
     * @generated
     */
    public Adapter createKChildAreaAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KText <em>KText</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KText
     * @generated
     */
    public Adapter createKTextAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPlacement <em>KPlacement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPlacement
     * @generated
     */
    public Adapter createKPlacementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacement <em>KGrid Placement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacement
     * @generated
     */
    public Adapter createKGridPlacementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPlacementData <em>KPlacement Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPlacementData
     * @generated
     */
    public Adapter createKPlacementDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData <em>KGrid Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData
     * @generated
     */
    public Adapter createKGridPlacementDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData <em>KArea Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KAreaPlacementData
     * @generated
     */
    public Adapter createKAreaPlacementDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering <em>KCustom Rendering</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KCustomRendering
     * @generated
     */
    public Adapter createKCustomRenderingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KColor <em>KColor</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KColor
     * @generated
     */
    public Adapter createKColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KLineWidth <em>KLine Width</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KLineWidth
     * @generated
     */
    public Adapter createKLineWidthAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle <em>KLine Style</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KLineStyle
     * @generated
     */
    public Adapter createKLineStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KVerticalAlignment <em>KVertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KVerticalAlignment
     * @generated
     */
    public Adapter createKVerticalAlignmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment <em>KHorizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment
     * @generated
     */
    public Adapter createKHorizontalAlignmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KXPosition <em>KX Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KXPosition
     * @generated
     */
    public Adapter createKXPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KYPosition <em>KY Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KYPosition
     * @generated
     */
    public Adapter createKYPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KLeftPosition <em>KLeft Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KLeftPosition
     * @generated
     */
    public Adapter createKLeftPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRightPosition <em>KRight Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRightPosition
     * @generated
     */
    public Adapter createKRightPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KTopPosition <em>KTop Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KTopPosition
     * @generated
     */
    public Adapter createKTopPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KBottomPosition <em>KBottom Position</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KBottomPosition
     * @generated
     */
    public Adapter createKBottomPositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KSpline <em>KSpline</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KSpline
     * @generated
     */
    public Adapter createKSplineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KForeground <em>KForeground</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KForeground
     * @generated
     */
    public Adapter createKForegroundAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KColoring <em>KColoring</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring
     * @generated
     */
    public Adapter createKColoringAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KBackground <em>KBackground</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KBackground
     * @generated
     */
    public Adapter createKBackgroundAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KFontBold <em>KFont Bold</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KFontBold
     * @generated
     */
    public Adapter createKFontBoldAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KFontItalic <em>KFont Italic</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KFontItalic
     * @generated
     */
    public Adapter createKFontItalicAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KFontName <em>KFont Name</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KFontName
     * @generated
     */
    public Adapter createKFontNameAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KFontSize <em>KFont Size</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KFontSize
     * @generated
     */
    public Adapter createKFontSizeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline <em>KRounded Bends Polyline</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline
     * @generated
     */
    public Adapter createKRoundedBendsPolylineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KRotation <em>KRotation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KRotation
     * @generated
     */
    public Adapter createKRotationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KLineCap <em>KLine Cap</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KLineCap
     * @generated
     */
    public Adapter createKLineCapAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KAction <em>KAction</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KAction
     * @generated
     */
    public Adapter createKActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData <em>KPoint Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData
     * @generated
     */
    public Adapter createKPointPlacementDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KStyleHolder <em>KStyle Holder</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleHolder
     * @generated
     */
    public Adapter createKStyleHolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KInvisibility <em>KInvisibility</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KInvisibility
     * @generated
     */
    public Adapter createKInvisibilityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KShadow <em>KShadow</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow
     * @generated
     */
    public Adapter createKShadowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline <em>KText Underline</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KTextUnderline
     * @generated
     */
    public Adapter createKTextUnderlineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KStyleRef <em>KStyle Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleRef
     * @generated
     */
    public Adapter createKStyleRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KTextStrikeout <em>KText Strikeout</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KTextStrikeout
     * @generated
     */
    public Adapter createKTextStrikeoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin <em>KLine Join</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.krendering.KLineJoin
     * @generated
     */
    public Adapter createKLineJoinAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.elk.graph.properties.IPropertyHolder <em>IProperty Holder</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.elk.graph.properties.IPropertyHolder
     * @generated
     */
    public Adapter createIPropertyHolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder <em>EMap Property Holder</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
     * @generated
     */
    public Adapter createEMapPropertyHolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.klighd.kgraph.KGraphData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphData
     * @generated
     */
    public Adapter createKGraphDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //KRenderingAdapterFactory
