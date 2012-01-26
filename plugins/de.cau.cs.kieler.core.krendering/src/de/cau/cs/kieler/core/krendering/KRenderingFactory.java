/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage
 * @generated
 */
public interface KRenderingFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KRenderingFactory eINSTANCE = de.cau.cs.kieler.core.krendering.impl.KRenderingFactoryImpl.init();

    /**
     * Returns a new object of class '<em>KPosition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPosition</em>'.
     * @generated
     */
    KPosition createKPosition();

    /**
     * Returns a new object of class '<em>KEllipse</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KEllipse</em>'.
     * @generated
     */
    KEllipse createKEllipse();

    /**
     * Returns a new object of class '<em>KRectangle</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KRectangle</em>'.
     * @generated
     */
    KRectangle createKRectangle();

    /**
     * Returns a new object of class '<em>KRounded Rectangle</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KRounded Rectangle</em>'.
     * @generated
     */
    KRoundedRectangle createKRoundedRectangle();

    /**
     * Returns a new object of class '<em>KPolyline</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPolyline</em>'.
     * @generated
     */
    KPolyline createKPolyline();

    /**
     * Returns a new object of class '<em>KPolygon</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPolygon</em>'.
     * @generated
     */
    KPolygon createKPolygon();

    /**
     * Returns a new object of class '<em>KImage</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KImage</em>'.
     * @generated
     */
    KImage createKImage();

    /**
     * Returns a new object of class '<em>KDecorator Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KDecorator Placement Data</em>'.
     * @generated
     */
    KDecoratorPlacementData createKDecoratorPlacementData();

    /**
     * Returns a new object of class '<em>KArc</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KArc</em>'.
     * @generated
     */
    KArc createKArc();

    /**
     * Returns a new object of class '<em>KStyle</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStyle</em>'.
     * @generated
     */
    KStyle createKStyle();

    /**
     * Returns a new object of class '<em>Library</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Library</em>'.
     * @generated
     */
    KRenderingLibrary createKRenderingLibrary();

    /**
     * Returns a new object of class '<em>Ref</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ref</em>'.
     * @generated
     */
    KRenderingRef createKRenderingRef();

    /**
     * Returns a new object of class '<em>KChild Area</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KChild Area</em>'.
     * @generated
     */
    KChildArea createKChildArea();

    /**
     * Returns a new object of class '<em>KText</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KText</em>'.
     * @generated
     */
    KText createKText();

    /**
     * Returns a new object of class '<em>KGrid Placement</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KGrid Placement</em>'.
     * @generated
     */
    KGridPlacement createKGridPlacement();

    /**
     * Returns a new object of class '<em>KStack Placement</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStack Placement</em>'.
     * @generated
     */
    KStackPlacement createKStackPlacement();

    /**
     * Returns a new object of class '<em>KGrid Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KGrid Placement Data</em>'.
     * @generated
     */
    KGridPlacementData createKGridPlacementData();

    /**
     * Returns a new object of class '<em>KStack Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStack Placement Data</em>'.
     * @generated
     */
    KStackPlacementData createKStackPlacementData();

    /**
     * Returns a new object of class '<em>KDirect Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KDirect Placement Data</em>'.
     * @generated
     */
    KDirectPlacementData createKDirectPlacementData();

    /**
     * Returns a new object of class '<em>KPolyline Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPolyline Placement Data</em>'.
     * @generated
     */
    KPolylinePlacementData createKPolylinePlacementData();

    /**
     * Returns a new object of class '<em>KCustom Rendering</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KCustom Rendering</em>'.
     * @generated
     */
    KCustomRendering createKCustomRendering();

    /**
     * Returns a new object of class '<em>KForeground Color</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KForeground Color</em>'.
     * @generated
     */
    KForegroundColor createKForegroundColor();

    /**
     * Returns a new object of class '<em>KBackground Color</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KBackground Color</em>'.
     * @generated
     */
    KBackgroundColor createKBackgroundColor();

    /**
     * Returns a new object of class '<em>KLine Width</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLine Width</em>'.
     * @generated
     */
    KLineWidth createKLineWidth();

    /**
     * Returns a new object of class '<em>KVisibility</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KVisibility</em>'.
     * @generated
     */
    KVisibility createKVisibility();

    /**
     * Returns a new object of class '<em>KLine Style</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLine Style</em>'.
     * @generated
     */
    KLineStyle createKLineStyle();

    /**
     * Returns a new object of class '<em>KVertical Alignment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KVertical Alignment</em>'.
     * @generated
     */
    KVerticalAlignment createKVerticalAlignment();

    /**
     * Returns a new object of class '<em>KHorizontal Alignment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KHorizontal Alignment</em>'.
     * @generated
     */
    KHorizontalAlignment createKHorizontalAlignment();

    /**
     * Returns a new object of class '<em>KLeft Position</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLeft Position</em>'.
     * @generated
     */
    KLeftPosition createKLeftPosition();

    /**
     * Returns a new object of class '<em>KRight Position</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KRight Position</em>'.
     * @generated
     */
    KRightPosition createKRightPosition();

    /**
     * Returns a new object of class '<em>KTop Position</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KTop Position</em>'.
     * @generated
     */
    KTopPosition createKTopPosition();

    /**
     * Returns a new object of class '<em>KBottom Position</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KBottom Position</em>'.
     * @generated
     */
    KBottomPosition createKBottomPosition();

    /**
     * Returns a new object of class '<em>KSpline</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KSpline</em>'.
     * @generated
     */
    KSpline createKSpline();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    KRenderingPackage getKRenderingPackage();

} //KRenderingFactory
