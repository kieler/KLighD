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
package de.cau.cs.kieler.klighd.krendering;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage
 * @generated
 */
public interface KRenderingFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KRenderingFactory eINSTANCE = de.cau.cs.kieler.klighd.krendering.impl.KRenderingFactoryImpl.init();

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
     * Returns a new object of class '<em>KGrid Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KGrid Placement Data</em>'.
     * @generated
     */
    KGridPlacementData createKGridPlacementData();

    /**
     * Returns a new object of class '<em>KArea Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KArea Placement Data</em>'.
     * @generated
     */
    KAreaPlacementData createKAreaPlacementData();

    /**
     * Returns a new object of class '<em>KCustom Rendering</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KCustom Rendering</em>'.
     * @generated
     */
    KCustomRendering createKCustomRendering();

    /**
     * Returns a new object of class '<em>KColor</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KColor</em>'.
     * @generated
     */
    KColor createKColor();

    /**
     * Returns a new object of class '<em>KLine Width</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLine Width</em>'.
     * @generated
     */
    KLineWidth createKLineWidth();

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
     * Returns a new object of class '<em>KForeground</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KForeground</em>'.
     * @generated
     */
    KForeground createKForeground();

    /**
     * Returns a new object of class '<em>KBackground</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KBackground</em>'.
     * @generated
     */
    KBackground createKBackground();

    /**
     * Returns a new object of class '<em>KFont Bold</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KFont Bold</em>'.
     * @generated
     */
    KFontBold createKFontBold();

    /**
     * Returns a new object of class '<em>KFont Italic</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KFont Italic</em>'.
     * @generated
     */
    KFontItalic createKFontItalic();

    /**
     * Returns a new object of class '<em>KFont Name</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KFont Name</em>'.
     * @generated
     */
    KFontName createKFontName();

    /**
     * Returns a new object of class '<em>KFont Size</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KFont Size</em>'.
     * @generated
     */
    KFontSize createKFontSize();

    /**
     * Returns a new object of class '<em>KRounded Bends Polyline</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KRounded Bends Polyline</em>'.
     * @generated
     */
    KRoundedBendsPolyline createKRoundedBendsPolyline();

    /**
     * Returns a new object of class '<em>KRotation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KRotation</em>'.
     * @generated
     */
    KRotation createKRotation();

    /**
     * Returns a new object of class '<em>KLine Cap</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLine Cap</em>'.
     * @generated
     */
    KLineCap createKLineCap();

    /**
     * Returns a new object of class '<em>KAction</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KAction</em>'.
     * @generated
     */
    KAction createKAction();

    /**
     * Returns a new object of class '<em>KPoint Placement Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KPoint Placement Data</em>'.
     * @generated
     */
    KPointPlacementData createKPointPlacementData();

    /**
     * Returns a new object of class '<em>KStyle Holder</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStyle Holder</em>'.
     * @generated
     */
    KStyleHolder createKStyleHolder();

    /**
     * Returns a new object of class '<em>KInvisibility</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KInvisibility</em>'.
     * @generated
     */
    KInvisibility createKInvisibility();

    /**
     * Returns a new object of class '<em>KShadow</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KShadow</em>'.
     * @generated
     */
    KShadow createKShadow();

    /**
     * Returns a new object of class '<em>KText Underline</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KText Underline</em>'.
     * @generated
     */
    KTextUnderline createKTextUnderline();

    /**
     * Returns a new object of class '<em>KStyle Ref</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStyle Ref</em>'.
     * @generated
     */
    KStyleRef createKStyleRef();

    /**
     * Returns a new object of class '<em>KText Strikeout</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KText Strikeout</em>'.
     * @generated
     */
    KTextStrikeout createKTextStrikeout();

    /**
     * Returns a new object of class '<em>KLine Join</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KLine Join</em>'.
     * @generated
     */
    KLineJoin createKLineJoin();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    KRenderingPackage getKRenderingPackage();

} //KRenderingFactory
