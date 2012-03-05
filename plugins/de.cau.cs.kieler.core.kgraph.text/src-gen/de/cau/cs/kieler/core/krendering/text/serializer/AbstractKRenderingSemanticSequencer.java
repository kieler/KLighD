package de.cau.cs.kieler.core.krendering.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KForegroundVisibility;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.text.services.KRenderingGrammarAccess;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.text.serializer.KLayoutDataSemanticSequencer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("restriction")
public class AbstractKRenderingSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected KRenderingGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	@Inject
	protected Provider<KLayoutDataSemanticSequencer> superSequencerProvider;
	 
	protected KLayoutDataSemanticSequencer superSequencer; 
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.superSequencer = superSequencerProvider.get();
		this.superSequencer.init(sequencer, sequenceAcceptor, errorAcceptor); 
	}
	
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
			case KRenderingPackage.KBACKGROUND_COLOR:
				if(context == grammarAccess.getKBackgroundColorRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KBackgroundColor(context, (KBackgroundColor) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KBACKGROUND_VISIBILITY:
				if(context == grammarAccess.getKBackgroundVisibilityRule() ||
				   context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKVisibilityRule()) {
					sequence_KBackgroundVisibility(context, (KBackgroundVisibility) semanticObject); 
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
			case KRenderingPackage.KDIRECT_PLACEMENT_DATA:
				if(context == grammarAccess.getKDirectPlacementDataRule() ||
				   context == grammarAccess.getKPlacementDataRule()) {
					sequence_KDirectPlacementData(context, (KDirectPlacementData) semanticObject); 
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
			case KRenderingPackage.KFOREGROUND_COLOR:
				if(context == grammarAccess.getKForegroundColorRule() ||
				   context == grammarAccess.getKStyleRule()) {
					sequence_KForegroundColor(context, (KForegroundColor) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFOREGROUND_VISIBILITY:
				if(context == grammarAccess.getKForegroundVisibilityRule() ||
				   context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKVisibilityRule()) {
					sequence_KForegroundVisibility(context, (KForegroundVisibility) semanticObject); 
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
			case KRenderingPackage.KLEFT_POSITION:
				if(context == grammarAccess.getKLeftPositionRule() ||
				   context == grammarAccess.getKXPositionRule()) {
					sequence_KLeftPosition(context, (KLeftPosition) semanticObject); 
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
			case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA:
				if(context == grammarAccess.getKPlacementDataRule() ||
				   context == grammarAccess.getKPolylinePlacementDataRule()) {
					sequence_KPolylinePlacementData(context, (KPolylinePlacementData) semanticObject); 
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
			case KRenderingPackage.KROUNDED_RECTANGLE:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKRoundedRectangleRule()) {
					sequence_KRoundedRectangle(context, (KRoundedRectangle) semanticObject); 
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
			case KRenderingPackage.KSTACK_PLACEMENT:
				if(context == grammarAccess.getKPlacementRule() ||
				   context == grammarAccess.getKStackPlacementRule()) {
					sequence_KPlacement(context, (KStackPlacement) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSTACK_PLACEMENT_DATA:
				if(context == grammarAccess.getKPlacementDataRule() ||
				   context == grammarAccess.getKStackPlacementDataRule()) {
					sequence_KStackPlacementData(context, (KStackPlacementData) semanticObject); 
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
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? (children+=KRendering children+=KRendering*)? childPlacement=KPlacement?)?
	 *     )
	 */
	protected void sequence_KArc(EObject context, KArc semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (red=EInt green=EInt blue=EInt propagateToChildren?='!'?)
	 */
	protected void sequence_KBackgroundColor(EObject context, KBackgroundColor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (visible=EBoolean propagateToChildren?='!'?)
	 */
	protected void sequence_KBackgroundVisibility(EObject context, KBackgroundVisibility semanticObject) {
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
		feeder.accept(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_4_0(), semanticObject.getRelative());
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
	 *     (
	 *         (
	 *             className=EString 
	 *             bundleName=EString 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             (children+=KRendering children+=KRendering*)? 
	 *             childPlacement=KPlacement?
	 *         )?
	 *     )
	 */
	protected void sequence_KCustomRendering(EObject context, KCustomRendering semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         relative?='relative' 
	 *         location=EFloat 
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
	 *     (topLeft=KPosition bottomRight=KPosition)
	 */
	protected void sequence_KDirectPlacementData(EObject context, KDirectPlacementData semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KDIRECT_PLACEMENT_DATA__TOP_LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KDIRECT_PLACEMENT_DATA__TOP_LEFT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KDIRECT_PLACEMENT_DATA__BOTTOM_RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KDIRECT_PLACEMENT_DATA__BOTTOM_RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0(), semanticObject.getTopLeft());
		feeder.accept(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_5_0(), semanticObject.getBottomRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         sourcePoint=KPoint 
	 *         targetPoint=KPoint 
	 *         (bendPoints+=KPoint bendPoints+=KPoint*)? 
	 *         (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?
	 *     )
	 */
	protected void sequence_KEdgeLayout(EObject context, KEdgeLayout semanticObject) {
		superSequencer.createSequence(context, semanticObject);
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
	 *     (red=EInt green=EInt blue=EInt propagateToChildren?='!'?)
	 */
	protected void sequence_KForegroundColor(EObject context, KForegroundColor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (visible=EBoolean propagateToChildren?='!'?)
	 */
	protected void sequence_KForegroundVisibility(EObject context, KForegroundVisibility semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (widthHint=EFloat heightHint=EFloat horizontalIndent=EFloat verticalIndent=EFloat)
	 */
	protected void sequence_KGridPlacementData(EObject context, KGridPlacementData semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__WIDTH_HINT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__WIDTH_HINT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__HEIGHT_HINT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__HEIGHT_HINT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__VERTICAL_INDENT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT_DATA__VERTICAL_INDENT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0(), semanticObject.getWidthHint());
		feeder.accept(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0(), semanticObject.getHeightHint());
		feeder.accept(grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentEFloatParserRuleCall_7_0(), semanticObject.getHorizontalIndent());
		feeder.accept(grammarAccess.getKGridPlacementDataAccess().getVerticalIndentEFloatParserRuleCall_9_0(), semanticObject.getVerticalIndent());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     numColumns=EInt
	 */
	protected void sequence_KGridPlacement(EObject context, KGridPlacement semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT__NUM_COLUMNS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KGRID_PLACEMENT__NUM_COLUMNS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0(), semanticObject.getNumColumns());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (horizontalAlignment=HorizontalAlignment propagateToChildren?='!')
	 */
	protected void sequence_KHorizontalAlignment(EObject context, KHorizontalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         bundleName=EString? 
	 *         imagePath=EString 
	 *         ((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? (children+=KRendering children+=KRendering*)? childPlacement=KPlacement?)?
	 *     )
	 */
	protected void sequence_KImage(EObject context, KImage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (top=EFloat? bottom=EFloat? left=EFloat? right=EFloat?)
	 */
	protected void sequence_KInsets(EObject context, KInsets semanticObject) {
		superSequencer.createSequence(context, semanticObject);
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
		feeder.accept(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_4_0(), semanticObject.getRelative());
		feeder.finish();
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
	 *     (lineWidth=EInt propagateToChildren?='!'?)
	 */
	protected void sequence_KLineWidth(EObject context, KLineWidth semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KStackPlacement}
	 */
	protected void sequence_KPlacement(EObject context, KStackPlacement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x=EFloat y=EFloat)
	 */
	protected void sequence_KPoint(EObject context, KPoint semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?)
	 */
	protected void sequence_KPolygon(EObject context, KPolygon semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (points+=KPosition points+=KPosition* detailPlacementData=KPlacementData?)
	 */
	protected void sequence_KPolylinePlacementData(EObject context, KPolylinePlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? childPlacement=KPlacement? (children+=KRendering children+=KRendering*)?)?)
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
	 *     (rendering=[KRendering|EString] (placementData=KPlacementData? (styles+=KStyle styles+=KStyle*)?)?)
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
		feeder.accept(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_4_0(), semanticObject.getRelative());
		feeder.finish();
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
	 *     (
	 *         xpos=EFloat? 
	 *         ypos=EFloat? 
	 *         width=EFloat? 
	 *         height=EFloat? 
	 *         insets=KInsets? 
	 *         (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?
	 *     )
	 */
	protected void sequence_KShapeLayout(EObject context, KShapeLayout semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (((styles+=KStyle styles+=KStyle*)? placementData=KPlacementData? (children+=KRendering children+=KRendering*)? childPlacement=KPlacement?)?)
	 */
	protected void sequence_KSpline(EObject context, KSpline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (insetRight=EFloat insetBottom=EFloat insetLeft=EFloat insetTop=EFloat)
	 */
	protected void sequence_KStackPlacementData(EObject context, KStackPlacementData semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_RIGHT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_BOTTOM) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_BOTTOM));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_LEFT));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_TOP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KSTACK_PLACEMENT_DATA__INSET_TOP));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0(), semanticObject.getInsetRight());
		feeder.accept(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0(), semanticObject.getInsetBottom());
		feeder.accept(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0(), semanticObject.getInsetLeft());
		feeder.accept(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0(), semanticObject.getInsetTop());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         text=EString? 
	 *         (
	 *             clip?='clip' 
	 *             (styles+=KStyle styles+=KStyle*)? 
	 *             placementData=KPlacementData? 
	 *             (children+=KRendering children+=KRendering*)? 
	 *             childPlacement=KPlacement?
	 *         )?
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
		feeder.accept(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_4_0(), semanticObject.getRelative());
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
	 *     (key=EString value=EString?)
	 */
	protected void sequence_PersistentEntry(EObject context, PersistentEntry semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
}
