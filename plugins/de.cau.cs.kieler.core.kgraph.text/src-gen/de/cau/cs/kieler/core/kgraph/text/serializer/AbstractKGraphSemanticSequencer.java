package de.cau.cs.kieler.core.kgraph.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
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
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.KVisibility;
import de.cau.cs.kieler.core.krendering.text.serializer.KRenderingSemanticSequencer;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("restriction")
public class AbstractKGraphSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected KGraphGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	@Inject
	protected Provider<KRenderingSemanticSequencer> superSequencerProvider;
	 
	protected KRenderingSemanticSequencer superSequencer; 
	
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
			case KGraphPackage.KEDGE:
				if(context == grammarAccess.getKEdgeRule()) {
					sequence_KEdge(context, (KEdge) semanticObject); 
					return; 
				}
				else break;
			case KGraphPackage.KLABEL:
				if(context == grammarAccess.getKLabelRule()) {
					sequence_KLabel(context, (KLabel) semanticObject); 
					return; 
				}
				else break;
			case KGraphPackage.KNODE:
				if(context == grammarAccess.getKNodeRule()) {
					sequence_KNode(context, (KNode) semanticObject); 
					return; 
				}
				else break;
			case KGraphPackage.KPORT:
				if(context == grammarAccess.getKPortRule()) {
					sequence_KPort(context, (KPort) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KGraphPackage.PERSISTENT_ENTRY:
				if(context == grammarAccess.getPersistentEntryRule()) {
					sequence_PersistentEntry(context, (PersistentEntry) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KLayoutDataPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KLayoutDataPackage.KEDGE_LAYOUT:
				if(context == grammarAccess.getKEdgeLayoutRule() ||
				   context == grammarAccess.getKGraphDataRule()) {
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKShapeLayoutRule()) {
					sequence_KShapeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KRenderingPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KRenderingPackage.KARC:
				if(context == grammarAccess.getKArcRule() ||
				   context == grammarAccess.getKGraphDataRule() ||
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
			case KRenderingPackage.KBOTTOM_POSITION:
				if(context == grammarAccess.getKBottomPositionRule() ||
				   context == grammarAccess.getKYPositionRule()) {
					sequence_KBottomPosition(context, (KBottomPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCHILD_AREA:
				if(context == grammarAccess.getKChildAreaRule() ||
				   context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KChildArea(context, (KChildArea) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCUSTOM_RENDERING:
				if(context == grammarAccess.getKCustomRenderingRule() ||
				   context == grammarAccess.getKGraphDataRule() ||
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
				   context == grammarAccess.getKGraphDataRule() ||
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKImageRule() ||
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKPolygonRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolygon(context, (KPolygon) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOLYLINE:
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKPolyline_ImplRule() ||
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRectangleRule() ||
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingRule() ||
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKRoundedRectangleRule()) {
					sequence_KRoundedRectangle(context, (KRoundedRectangle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSPLINE:
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingRule() ||
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
			case KRenderingPackage.KSTYLE:
				if(context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKStyle_ImplRule()) {
					sequence_KStyle_Impl(context, (KStyle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KTEXT:
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingRule() ||
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
			case KRenderingPackage.KVISIBILITY:
				if(context == grammarAccess.getKStyleRule() ||
				   context == grammarAccess.getKVisibilityRule()) {
					sequence_KVisibility(context, (KVisibility) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         startAngle=EFloat? 
	 *         arcAngle=EFloat? 
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KArc(EObject context, KArc semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' red=EInt green=EInt blue=EInt)
	 */
	protected void sequence_KBackgroundColor(EObject context, KBackgroundColor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat? relative=EFloat?)
	 */
	protected void sequence_KBottomPosition(EObject context, KBottomPosition semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? placementData=KPlacementData? (styles+=KStyle styles+=KStyle*)?)
	 */
	protected void sequence_KChildArea(EObject context, KChildArea semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         className=EString 
	 *         bundleName=EString 
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KCustomRendering(EObject context, KCustomRendering semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (relative?='relative' location=EFloat xOffset=EFloat? yOffset=EFloat?)
	 */
	protected void sequence_KDecoratorPlacementData(EObject context, KDecoratorPlacementData semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (topLeft=KPosition bottomRight=KPosition)
	 */
	protected void sequence_KDirectPlacementData(EObject context, KDirectPlacementData semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((bendPoints+=KPoint bendPoints+=KPoint*)? sourcePoint=KPoint targetPoint=KPoint)
	 */
	protected void sequence_KEdgeLayout(EObject context, KEdgeLayout semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         target=[KNode|EString] 
	 *         sourcePort=[KPort|EString]? 
	 *         targetPort=[KPort|EString]? 
	 *         (data+=KGraphData data+=KGraphData*)? 
	 *         (labels+=KLabel labels+=KLabel*)?
	 *     )
	 */
	protected void sequence_KEdge(EObject context, KEdge semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KEllipse(EObject context, KEllipse semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' red=EInt green=EInt blue=EInt)
	 */
	protected void sequence_KForegroundColor(EObject context, KForegroundColor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (widthHint=EFloat heightHint=EFloat horizontalIndent=EFloat verticalIndent=EFloat)
	 */
	protected void sequence_KGridPlacementData(EObject context, KGridPlacementData semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     numColumns=EInt
	 */
	protected void sequence_KGridPlacement(EObject context, KGridPlacement semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' horizontalAlignment=HorizontalAlignment)
	 */
	protected void sequence_KHorizontalAlignment(EObject context, KHorizontalAlignment semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         bundleName=EString 
	 *         imagePath=EString 
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KImage(EObject context, KImage semanticObject) {
		superSequencer.createSequence(context, semanticObject);
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
	 *     (text=EString (data+=KGraphData data+=KGraphData*)?)
	 */
	protected void sequence_KLabel(EObject context, KLabel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat? relative=EFloat?)
	 */
	protected void sequence_KLeftPosition(EObject context, KLeftPosition semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' lineStyle=LineStyle)
	 */
	protected void sequence_KLineStyle(EObject context, KLineStyle semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' lineWidth=EInt)
	 */
	protected void sequence_KLineWidth(EObject context, KLineWidth semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (incomingEdges+=[KEdge|EString] incomingEdges+=[KEdge|EString]*)? 
	 *         (data+=KGraphData data+=KGraphData*)? 
	 *         (labels+=KLabel labels+=KLabel*)? 
	 *         (children+=KNode children+=KNode*)? 
	 *         (ports+=KPort ports+=KPort*)? 
	 *         (outgoingEdges+=KEdge outgoingEdges+=KEdge*)?
	 *     )
	 */
	protected void sequence_KNode(EObject context, KNode semanticObject) {
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
	 *     (x=EFloat? y=EFloat?)
	 */
	protected void sequence_KPoint(EObject context, KPoint semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KPolygon(EObject context, KPolygon semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (points+=KPosition points+=KPosition*)
	 */
	protected void sequence_KPolylinePlacementData(EObject context, KPolylinePlacementData semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KPolyline_Impl(EObject context, KPolyline semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((edges+=[KEdge|EString] edges+=[KEdge|EString]*)? (data+=KGraphData data+=KGraphData*)? (labels+=KLabel labels+=KLabel*)?)
	 */
	protected void sequence_KPort(EObject context, KPort semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x=KXPosition y=KYPosition)
	 */
	protected void sequence_KPosition(EObject context, KPosition semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KRectangle(EObject context, KRectangle semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((renderings+=KRendering renderings+=KRendering*)?)
	 */
	protected void sequence_KRenderingLibrary(EObject context, KRenderingLibrary semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         rendering=[KRendering|EString] 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)?
	 *     )
	 */
	protected void sequence_KRenderingRef(EObject context, KRenderingRef semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat? relative=EFloat?)
	 */
	protected void sequence_KRightPosition(EObject context, KRightPosition semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         cornerWidth=EFloat 
	 *         cornerHeight=EFloat 
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KRoundedRectangle(EObject context, KRoundedRectangle semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xpos=EFloat? ypos=EFloat? width=EFloat? height=EFloat? insets=KInsets?)
	 */
	protected void sequence_KShapeLayout(EObject context, KShapeLayout semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KSpline(EObject context, KSpline semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (insetRight=EFloat insetBottom=EFloat insetLeft=EFloat insetTop=EFloat)
	 */
	protected void sequence_KStackPlacementData(EObject context, KStackPlacementData semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     propagateToChildren?='propagateToChildren'
	 */
	protected void sequence_KStyle_Impl(EObject context, KStyle semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         clip?='clip' 
	 *         text=EString? 
	 *         (references+=[KRenderingRef|EString] references+=[KRenderingRef|EString]*)? 
	 *         placementData=KPlacementData? 
	 *         (styles+=KStyle styles+=KStyle*)? 
	 *         (children+=KRendering children+=KRendering*)? 
	 *         childPlacement=KPlacement?
	 *     )
	 */
	protected void sequence_KText(EObject context, KText semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (absolute=EFloat? relative=EFloat?)
	 */
	protected void sequence_KTopPosition(EObject context, KTopPosition semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' verticalAlignment=VerticalAlignment)
	 */
	protected void sequence_KVerticalAlignment(EObject context, KVerticalAlignment semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagateToChildren' lineVisible?='lineVisible' filled?='filled')
	 */
	protected void sequence_KVisibility(EObject context, KVisibility semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (key=EString value=EString?)
	 */
	protected void sequence_PersistentEntry(EObject context, PersistentEntry semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
}
