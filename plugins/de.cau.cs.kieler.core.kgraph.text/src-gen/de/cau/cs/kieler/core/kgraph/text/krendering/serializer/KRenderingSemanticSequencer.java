package de.cau.cs.kieler.core.kgraph.text.krendering.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.klayoutdata.serializer.KLayoutDataSemanticSequencer;
import de.cau.cs.kieler.core.kgraph.text.krendering.services.KRenderingGrammarAccess;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineCap;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRotation;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class KRenderingSemanticSequencer extends KLayoutDataSemanticSequencer {

	@Inject
	private KRenderingGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KGraphPackage.PERSISTENT_ENTRY:
				if(context == grammarAccess.getPersistentEntryRule()) {
					sequence_PersistentEntry(context, (PersistentEntry) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KLayoutDataPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KLayoutDataPackage.KEDGE_LAYOUT:
				if(context == grammarAccess.getKEdgeLayoutRule()) {
					sequence_KEdgeLayout(context, (KEdgeLayout) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KINSETS:
				if(context == grammarAccess.getKInsetsRule()) {
					sequence_KInsets(context, (KInsets) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KPOINT:
				if(context == grammarAccess.getKPointRule()) {
					sequence_KPoint(context, (KPoint) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KSHAPE_LAYOUT:
				if(context == grammarAccess.getKShapeLayoutRule()) {
					sequence_KShapeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KRenderingPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KRenderingPackage.KARC:
				if(context == grammarAccess.getKArcRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KArc(context, (KArc) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KAREA_PLACEMENT_DATA:
				if(context == grammarAccess.getKAreaPlacementDataRule() ||
				   context == grammarAccess.getKPlacementDataRule()) {
					sequence_KAreaPlacementData(context, (KAreaPlacementData) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KBACKGROUND:
				if(context == grammarAccess.getKBackgroundRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KBackground(context, (KBackground) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KBOTTOM_POSITION:
				if(context == grammarAccess.getKBottomPositionRule() ||
				   context == grammarAccess.getKYPositionRule()) {
					sequence_KBottomPosition(context, (KBottomPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCHILD_AREA:
				if(context == grammarAccess.getKChildAreaRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KChildArea(context, (KChildArea) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCOLOR:
				if(context == grammarAccess.getKColorRule()) {
					sequence_KColor(context, (KColor) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCUSTOM_RENDERING:
				if(context == grammarAccess.getKCustomRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KCustomRendering(context, (KCustomRendering) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KDECORATOR_PLACEMENT_DATA:
				if(context == grammarAccess.getKDecoratorPlacementDataRule() ||
				   context == grammarAccess.getKPlacementDataRule()) {
					sequence_KDecoratorPlacementData(context, (KDecoratorPlacementData) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KELLIPSE:
				if(context == grammarAccess.getKEllipseRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KEllipse(context, (KEllipse) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_BOLD:
				if(context == grammarAccess.getKFontBoldRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KFontBold(context, (KFontBold) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_ITALIC:
				if(context == grammarAccess.getKFontItalicRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KFontItalic(context, (KFontItalic) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_NAME:
				if(context == grammarAccess.getKFontNameRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KFontName(context, (KFontName) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_SIZE:
				if(context == grammarAccess.getKFontSizeRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KFontSize(context, (KFontSize) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFOREGROUND:
				if(context == grammarAccess.getKForegroundRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KForeground(context, (KForeground) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KGRID_PLACEMENT:
				if(context == grammarAccess.getKGridPlacementRule() ||
				   context == grammarAccess.getKPlacementRule()) {
					sequence_KGridPlacement(context, (KGridPlacement) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KGRID_PLACEMENT_DATA:
				if(context == grammarAccess.getKGridPlacementDataRule() ||
				   context == grammarAccess.getKPlacementDataRule()) {
					sequence_KGridPlacementData(context, (KGridPlacementData) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KHORIZONTAL_ALIGNMENT:
				if(context == grammarAccess.getKHorizontalAlignmentRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KHorizontalAlignment(context, (KHorizontalAlignment) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KIMAGE:
				if(context == grammarAccess.getKImageRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KImage(context, (KImage) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KINVISIBILITY:
				if(context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKVisibilityRule()) {
					sequence_KVisibility(context, (KInvisibility) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLEFT_POSITION:
				if(context == grammarAccess.getKLeftPositionRule() ||
				   context == grammarAccess.getKXPositionRule()) {
					sequence_KLeftPosition(context, (KLeftPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_CAP:
				if(context == grammarAccess.getKLineCapRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KLineCap(context, (KLineCap) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_STYLE:
				if(context == grammarAccess.getKLineStyleRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KLineStyle(context, (KLineStyle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_WIDTH:
				if(context == grammarAccess.getKLineWidthRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KLineWidth(context, (KLineWidth) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOINT_PLACEMENT_DATA:
				if(context == grammarAccess.getKPlacementDataRule() ||
				   context == grammarAccess.getKPointPlacementDataRule()) {
					sequence_KPointPlacementData(context, (KPointPlacementData) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOLYGON:
				if(context == grammarAccess.getKPolygonRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolygon(context, (KPolygon) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOLYLINE:
				if(context == grammarAccess.getKPolyline_ImplRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolyline_Impl(context, (KPolyline) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOSITION:
				if(context == grammarAccess.getKPositionRule()) {
					sequence_KPosition(context, (KPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KRECTANGLE:
				if(context == grammarAccess.getKRectangleRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KRectangle(context, (KRectangle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KRENDERING_LIBRARY:
				if(context == grammarAccess.getKRenderingLibraryRule()) {
					sequence_KRenderingLibrary(context, (KRenderingLibrary) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KRENDERING_REF:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKRenderingRefRule()) {
					sequence_KRenderingRef(context, (KRenderingRef) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KRIGHT_POSITION:
				if(context == grammarAccess.getKRightPositionRule() ||
				   context == grammarAccess.getKXPositionRule()) {
					sequence_KRightPosition(context, (KRightPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROTATION:
				if(context == grammarAccess.getKRotationRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KRotation(context, (KRotation) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROUNDED_BENDS_POLYLINE:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKRoundedBendsPolylineRule()) {
					sequence_KRoundedBendsPolyline(context, (KRoundedBendsPolyline) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROUNDED_RECTANGLE:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKRoundedRectangleRule()) {
					sequence_KRoundedRectangle(context, (KRoundedRectangle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSHADOW:
				if(context == grammarAccess.getKShadowRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KShadow(context, (KShadow) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSPLINE:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKSplineRule()) {
					sequence_KSpline(context, (KSpline) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSTYLE_REF:
				if(context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKStyleRefRule()) {
					sequence_KStyleRef(context, (KStyleRef) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KTEXT:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKTextRule()) {
					sequence_KText(context, (KText) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KTOP_POSITION:
				if(context == grammarAccess.getKTopPositionRule() ||
				   context == grammarAccess.getKYPositionRule()) {
					sequence_KTopPosition(context, (KTopPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KVERTICAL_ALIGNMENT:
				if(context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKVerticalAlignmentRule()) {
					sequence_KVerticalAlignment(context, (KVerticalAlignment) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         startAngle=EFloat 
	 *         arcAngle=EFloat 
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?
	 *     )
	 */
	protected void sequence_KArc(EObject context, KArc semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (topLeft=KPosition bottomRight=KPosition)
	 */
	protected void sequence_KAreaPlacementData(EObject context, KAreaPlacementData semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KAREA_PLACEMENT_DATA__TOP_LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KAREA_PLACEMENT_DATA__TOP_LEFT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KAREA_PLACEMENT_DATA__BOTTOM_RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KAREA_PLACEMENT_DATA__BOTTOM_RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKAreaPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0(), semanticObject.getTopLeft());
		feeder.accept(grammarAccess.getKAreaPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0(), semanticObject.getBottomRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         color=KColor? 
	 *         targetColor=KColor? 
	 *         alpha=EInt? 
	 *         targetAlpha=EInt? 
	 *         gradientAngle=EFloat? 
	 *         propagateToChildren?='!'?
	 *     )
	 */
	protected void sequence_KBackground(EObject context, KBackground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat relative=EFloat)
	 */
	protected void sequence_KBottomPosition(EObject context, KBottomPosition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KY_POSITION__ABSOLUTE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KY_POSITION__ABSOLUTE));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KY_POSITION__RELATIVE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KY_POSITION__RELATIVE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0(), semanticObject.getAbsolute());
		feeder.accept(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0(), semanticObject.getRelative());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData?)?)
	 */
	protected void sequence_KChildArea(EObject context, KChildArea semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (red=EInt green=EInt blue=EInt)
	 */
	protected void sequence_KColor(EObject context, KColor semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KCOLOR__RED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KCOLOR__RED));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KCOLOR__GREEN) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KCOLOR__GREEN));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KCOLOR__BLUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KCOLOR__BLUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKColorAccess().getRedEIntParserRuleCall_1_0(), semanticObject.getRed());
		feeder.accept(grammarAccess.getKColorAccess().getGreenEIntParserRuleCall_2_0(), semanticObject.getGreen());
		feeder.accept(grammarAccess.getKColorAccess().getBlueEIntParserRuleCall_3_0(), semanticObject.getBlue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             className=EString 
	 *             bundleName=EString 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             (children+=KRendering children+=KRendering*)?
	 *         )?
	 *     )
	 */
	protected void sequence_KCustomRendering(EObject context, KCustomRendering semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         rotateWithLine?='rotateWithLine'? 
	 *         absolute=EFloat 
	 *         relative=EFloat? 
	 *         xOffset=EFloat? 
	 *         yOffset=EFloat? 
	 *         width=EFloat? 
	 *         height=EFloat?
	 *     )
	 */
	protected void sequence_KDecoratorPlacementData(EObject context, KDecoratorPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?)
	 */
	protected void sequence_KEllipse(EObject context, KEllipse semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (bold?='bold' propagateToChildren?='!'?)
	 */
	protected void sequence_KFontBold(EObject context, KFontBold semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (italic?='italic' propagateToChildren?='!'?)
	 */
	protected void sequence_KFontItalic(EObject context, KFontItalic semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=EString propagateToChildren?='!'?)
	 */
	protected void sequence_KFontName(EObject context, KFontName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (size=EInt propagateToChildren?='!'?)
	 */
	protected void sequence_KFontSize(EObject context, KFontSize semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         color=KColor? 
	 *         targetColor=KColor? 
	 *         alpha=EInt? 
	 *         targetAlpha=EInt? 
	 *         gradientAngle=EFloat? 
	 *         propagateToChildren?='!'?
	 *     )
	 */
	protected void sequence_KForeground(EObject context, KForeground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         minCellWidth=EFloat? 
	 *         minCellHeight=EFloat? 
	 *         maxCellWidth=EFloat? 
	 *         maxCellHeight=EFloat? 
	 *         topLeft=KPosition? 
	 *         bottomRight=KPosition?
	 *     )
	 */
	protected void sequence_KGridPlacementData(EObject context, KGridPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (numColumns=EInt (topLeft=KPosition bottomRight=KPosition)?)
	 */
	protected void sequence_KGridPlacement(EObject context, KGridPlacement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (horizontalAlignment=HorizontalAlignment propagateToChildren?='!'?)
	 */
	protected void sequence_KHorizontalAlignment(EObject context, KHorizontalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         bundleName=EString? 
	 *         imagePath=EString 
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?
	 *     )
	 */
	protected void sequence_KImage(EObject context, KImage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat relative=EFloat)
	 */
	protected void sequence_KLeftPosition(EObject context, KLeftPosition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KX_POSITION__ABSOLUTE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KX_POSITION__ABSOLUTE));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KX_POSITION__RELATIVE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KX_POSITION__RELATIVE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0(), semanticObject.getAbsolute());
		feeder.accept(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0(), semanticObject.getRelative());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (lineCap=LineCap propagateToChildren?='!'?)
	 */
	protected void sequence_KLineCap(EObject context, KLineCap semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (lineStyle=LineStyle propagateToChildren?='!'?)
	 */
	protected void sequence_KLineStyle(EObject context, KLineStyle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (lineWidth=EFloat propagateToChildren?='!'? functionId=EString?)
	 */
	protected void sequence_KLineWidth(EObject context, KLineWidth semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         referencePoint=KPosition 
	 *         verticalAlignment=VerticalAlignment? 
	 *         horizontalAlignment=HorizontalAlignment? 
	 *         horizontalMargin=EFloat? 
	 *         verticalMargin=EFloat? 
	 *         minWidth=EFloat? 
	 *         minHeight=EFloat?
	 *     )
	 */
	protected void sequence_KPointPlacementData(EObject context, KPointPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             (children+=KRendering children+=KRendering*)?
	 *         )?
	 *     )
	 */
	protected void sequence_KPolygon(EObject context, KPolygon semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             (children+=KRendering children+=KRendering*)?
	 *         )?
	 *     )
	 */
	protected void sequence_KPolyline_Impl(EObject context, KPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x=KXPosition y=KYPosition)
	 */
	protected void sequence_KPosition(EObject context, KPosition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KPOSITION__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KPOSITION__X));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KPOSITION__Y) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KPOSITION__Y));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0(), semanticObject.getX());
		feeder.accept(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0(), semanticObject.getY());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?)
	 */
	protected void sequence_KRectangle(EObject context, KRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((renderings+=KRendering renderings+=KRendering*)?)
	 */
	protected void sequence_KRenderingLibrary(EObject context, KRenderingLibrary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (rendering=[KRendering|EString] ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData?)?)
	 */
	protected void sequence_KRenderingRef(EObject context, KRenderingRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat relative=EFloat)
	 */
	protected void sequence_KRightPosition(EObject context, KRightPosition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KX_POSITION__ABSOLUTE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KX_POSITION__ABSOLUTE));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KX_POSITION__RELATIVE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KX_POSITION__RELATIVE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0(), semanticObject.getAbsolute());
		feeder.accept(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0(), semanticObject.getRelative());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (rotation=EFloat propagateToChildren?='!'?)
	 */
	protected void sequence_KRotation(EObject context, KRotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         bendRadius=EFloat 
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             (children+=KRendering children+=KRendering*)?
	 *         )?
	 *     )
	 */
	protected void sequence_KRoundedBendsPolyline(EObject context, KRoundedBendsPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         cornerWidth=EFloat 
	 *         cornerHeight=EFloat 
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?
	 *     )
	 */
	protected void sequence_KRoundedRectangle(EObject context, KRoundedRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (color=KColor propagateToChildren?='!'?)
	 */
	protected void sequence_KShadow(EObject context, KShadow semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?)
	 */
	protected void sequence_KSpline(EObject context, KSpline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (styleHolder=[KStyleHolder|EString] propagateToChildren?='!'?)
	 */
	protected void sequence_KStyleRef(EObject context, KStyleRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         text=EString? 
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?)?
	 *     )
	 */
	protected void sequence_KText(EObject context, KText semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat relative=EFloat)
	 */
	protected void sequence_KTopPosition(EObject context, KTopPosition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KY_POSITION__ABSOLUTE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KY_POSITION__ABSOLUTE));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KY_POSITION__RELATIVE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KY_POSITION__RELATIVE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0(), semanticObject.getAbsolute());
		feeder.accept(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0(), semanticObject.getRelative());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (verticalAlignment=VerticalAlignment propagateToChildren?='!'?)
	 */
	protected void sequence_KVerticalAlignment(EObject context, KVerticalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (invisible?='invisible' functionId=EString?)
	 */
	protected void sequence_KVisibility(EObject context, KInvisibility semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
