package de.cau.cs.kieler.core.kgraph.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.krendering.serializer.KRenderingSemanticSequencer;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
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
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("all")
public abstract class AbstractKGraphSemanticSequencer extends KRenderingSemanticSequencer {

	@Inject
	private KGraphGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KGraphPackage.KEDGE:
				if(context == grammarAccess.getKEdgeRule()) {
					sequence_KEdge(context, (KEdge) semanticObject); 
					return; 
				}
				else break;
			case KGraphPackage.KGRAPH_DATA:
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKGraphDataImplRule()) {
					sequence_KGraphDataImpl(context, (KGraphData) semanticObject); 
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
				if(context == grammarAccess.getKGraphDataRule() ||
				   context == grammarAccess.getKRenderingLibraryRule()) {
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
					sequence_KStackPlacement(context, (KStackPlacement) semanticObject); 
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
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
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
	 *     ((persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?)
	 */
	protected void sequence_KGraphDataImpl(EObject context, KGraphData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
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
	 *     (
	 *         (ports+=KPort ports+=KPort*)? 
	 *         (data+=KGraphData data+=KGraphData*)? 
	 *         (children+=KNode children+=KNode*)? 
	 *         (outgoingEdges+=KEdge outgoingEdges+=KEdge*)?
	 *     )
	 */
	protected void sequence_KNode(EObject context, KNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((edges+=[KEdge|EString] edges+=[KEdge|EString]*)? (data+=KGraphData data+=KGraphData*)? (labels+=KLabel labels+=KLabel*)?)
	 */
	protected void sequence_KPort(EObject context, KPort semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
