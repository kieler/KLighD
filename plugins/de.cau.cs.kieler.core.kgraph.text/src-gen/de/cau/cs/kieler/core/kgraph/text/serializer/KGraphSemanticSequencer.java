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
import de.cau.cs.kieler.core.krendering.KAction;
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
import de.cau.cs.kieler.core.krendering.KFontUnderlined;
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
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class KGraphSemanticSequencer extends AbstractDelegatingSemanticSequencer {

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
				else if(context == grammarAccess.getParentKNodeRule()) {
					sequence_ParentKNode(context, (KNode) semanticObject); 
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
				if(context == grammarAccess.getEmptyKEdgeLayoutRule()) {
					sequence_EmptyKEdgeLayout(context, (KEdgeLayout) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKEdgeLayoutRule()) {
					sequence_KEdgeLayout(context, (KEdgeLayout) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KIDENTIFIER:
				if(context == grammarAccess.getKIdentifierRule()) {
					sequence_KIdentifier(context, (KIdentifier) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KINSETS:
				if(context == grammarAccess.getEmptyKInsetsRule()) {
					sequence_EmptyKInsets(context, (KInsets) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKInsetsRule()) {
					sequence_KInsets(context, (KInsets) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KPOINT:
				if(context == grammarAccess.getEmptyKPointRule()) {
					sequence_EmptyKPoint(context, (KPoint) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKPointRule()) {
					sequence_KPoint(context, (KPoint) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KSHAPE_LAYOUT:
				if(context == grammarAccess.getEmptyKNodeLayoutRule()) {
					sequence_EmptyKNodeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getEmptyKShapeLayoutRule()) {
					sequence_EmptyKShapeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKNodeLayoutRule()) {
					sequence_KNodeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKShapeLayoutRule()) {
					sequence_KShapeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KRenderingPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KRenderingPackage.KACTION:
				if(context == grammarAccess.getKActionRule()) {
					sequence_KAction(context, (KAction) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KARC:
				if(context == grammarAccess.getKArcRule()) {
					sequence_KArc(context, (KArc) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KArc_KContainerRendering(context, (KArc) semanticObject); 
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
				if(context == grammarAccess.getKBackgroundRule()) {
					sequence_KBackground(context, (KBackground) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKColoringRule()) {
					sequence_KBackground_KColoring(context, (KBackground) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KBackground_KColoring_KStyle(context, (KBackground) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KBOTTOM_POSITION:
				if(context == grammarAccess.getKBottomPositionRule()) {
					sequence_KBottomPosition(context, (KBottomPosition) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKYPositionRule()) {
					sequence_KYPosition(context, (KBottomPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KCHILD_AREA:
				if(context == grammarAccess.getKChildAreaRule()) {
					sequence_KChildArea(context, (KChildArea) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKSimpleRenderingRule()) {
					sequence_KSimpleRendering(context, (KChildArea) semanticObject); 
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
				if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KContainerRendering_KCustomRendering(context, (KCustomRendering) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKCustomRenderingRule()) {
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
				if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KContainerRendering(context, (KEllipse) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKEllipseRule()) {
					sequence_KEllipse(context, (KEllipse) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_BOLD:
				if(context == grammarAccess.getKFontBoldRule()) {
					sequence_KFontBold(context, (KFontBold) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KFontBold_KStyle(context, (KFontBold) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_ITALIC:
				if(context == grammarAccess.getKFontItalicRule()) {
					sequence_KFontItalic(context, (KFontItalic) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KFontItalic_KStyle(context, (KFontItalic) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_NAME:
				if(context == grammarAccess.getKFontNameRule()) {
					sequence_KFontName(context, (KFontName) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KFontName_KStyle(context, (KFontName) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_SIZE:
				if(context == grammarAccess.getKFontSizeRule()) {
					sequence_KFontSize(context, (KFontSize) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KFontSize_KStyle(context, (KFontSize) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFONT_UNDERLINED:
				if(context == grammarAccess.getKFontUnderlinedRule()) {
					sequence_KFontUnderlined(context, (KFontUnderlined) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KFontUnderlined_KStyle(context, (KFontUnderlined) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KFOREGROUND:
				if(context == grammarAccess.getKColoringRule()) {
					sequence_KColoring_KForeground(context, (KForeground) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KColoring_KForeground_KStyle(context, (KForeground) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKForegroundRule()) {
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
				if(context == grammarAccess.getKHorizontalAlignmentRule()) {
					sequence_KHorizontalAlignment(context, (KHorizontalAlignment) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KHorizontalAlignment_KStyle(context, (KHorizontalAlignment) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KIMAGE:
				if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KContainerRendering_KImage(context, (KImage) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKImageRule()) {
					sequence_KImage(context, (KImage) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KINVISIBILITY:
				if(context == grammarAccess.getKInvisibilityRule()) {
					sequence_KInvisibility(context, (KInvisibility) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KInvisibility_KStyle(context, (KInvisibility) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLEFT_POSITION:
				if(context == grammarAccess.getKLeftPositionRule()) {
					sequence_KLeftPosition(context, (KLeftPosition) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKXPositionRule()) {
					sequence_KXPosition(context, (KLeftPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_CAP:
				if(context == grammarAccess.getKLineCapRule()) {
					sequence_KLineCap(context, (KLineCap) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KLineCap_KStyle(context, (KLineCap) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_STYLE:
				if(context == grammarAccess.getKLineStyleRule()) {
					sequence_KLineStyle(context, (KLineStyle) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KLineStyle_KStyle(context, (KLineStyle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KLINE_WIDTH:
				if(context == grammarAccess.getKLineWidthRule()) {
					sequence_KLineWidth(context, (KLineWidth) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KLineWidth_KStyle(context, (KLineWidth) semanticObject); 
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
				if(context == grammarAccess.getKPolygonRule()) {
					sequence_KPolygon(context, (KPolygon) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKPolylineRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolyline(context, (KPolygon) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KPOLYLINE:
				if(context == grammarAccess.getKPolylineRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolyline(context, (KPolyline) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKSimplePolylineRule()) {
					sequence_KSimplePolyline(context, (KPolyline) semanticObject); 
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
				if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KContainerRendering(context, (KRectangle) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKRectangleRule()) {
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
				if(context == grammarAccess.getKRenderingRefRule()) {
					sequence_KRenderingRef(context, (KRenderingRef) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKSimpleRenderingRule()) {
					sequence_KRenderingRef_KSimpleRendering(context, (KRenderingRef) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KRIGHT_POSITION:
				if(context == grammarAccess.getKRightPositionRule()) {
					sequence_KRightPosition(context, (KRightPosition) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKXPositionRule()) {
					sequence_KXPosition(context, (KRightPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROTATION:
				if(context == grammarAccess.getKRotationRule()) {
					sequence_KRotation(context, (KRotation) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KRotation_KStyle(context, (KRotation) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROUNDED_BENDS_POLYLINE:
				if(context == grammarAccess.getKPolylineRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolyline_KRoundedBendsPolyline(context, (KRoundedBendsPolyline) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKRoundedBendsPolylineRule()) {
					sequence_KRoundedBendsPolyline(context, (KRoundedBendsPolyline) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KROUNDED_RECTANGLE:
				if(context == grammarAccess.getKContainerRenderingRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KContainerRendering_KRoundedRectangle(context, (KRoundedRectangle) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKRoundedRectangleRule()) {
					sequence_KRoundedRectangle(context, (KRoundedRectangle) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSHADOW:
				if(context == grammarAccess.getKShadowRule()) {
					sequence_KShadow(context, (KShadow) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KShadow_KStyle(context, (KShadow) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSPLINE:
				if(context == grammarAccess.getKPolylineRule() ||
				   context == grammarAccess.getKRenderingRule()) {
					sequence_KPolyline(context, (KSpline) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKSplineRule()) {
					sequence_KSpline(context, (KSpline) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSTYLE_HOLDER:
				if(context == grammarAccess.getKStyleHolderRule()) {
					sequence_KStyleHolder(context, (KStyleHolder) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KSTYLE_REF:
				if(context == grammarAccess.getKStyleRefRule()) {
					sequence_KStyleRef(context, (KStyleRef) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKStyleRule()) {
					sequence_KStyle_KStyleRef(context, (KStyleRef) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KTEXT:
				if(context == grammarAccess.getKRenderingRule() ||
				   context == grammarAccess.getKSimpleRenderingRule()) {
					sequence_KSimpleRendering_KText(context, (KText) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKTextRule()) {
					sequence_KText(context, (KText) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KTOP_POSITION:
				if(context == grammarAccess.getKTopPositionRule()) {
					sequence_KTopPosition(context, (KTopPosition) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKYPositionRule()) {
					sequence_KYPosition(context, (KTopPosition) semanticObject); 
					return; 
				}
				else break;
			case KRenderingPackage.KVERTICAL_ALIGNMENT:
				if(context == grammarAccess.getKStyleRule()) {
					sequence_KStyle_KVerticalAlignment(context, (KVerticalAlignment) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getKVerticalAlignmentRule()) {
					sequence_KVerticalAlignment(context, (KVerticalAlignment) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (sourcePoint=EmptyKPoint targetPoint=EmptyKPoint)
	 */
	protected void sequence_EmptyKEdgeLayout(EObject context, KEdgeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KInsets}
	 */
	protected void sequence_EmptyKInsets(EObject context, KInsets semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     insets=EmptyKInsets
	 */
	protected void sequence_EmptyKNodeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KPoint}
	 */
	protected void sequence_EmptyKPoint(EObject context, KPoint semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KShapeLayout}
	 */
	protected void sequence_EmptyKShapeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (trigger=KTrigger id=QualifiedID)
	 */
	protected void sequence_KAction(EObject context, KAction semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KACTION__ID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KACTION__ID));
			if(transientValues.isValueTransient(semanticObject, KRenderingPackage.Literals.KACTION__TRIGGER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KRenderingPackage.Literals.KACTION__TRIGGER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKActionAccess().getTriggerKTriggerEnumRuleCall_0_0(), semanticObject.getTrigger());
		feeder.accept(grammarAccess.getKActionAccess().getIdQualifiedIDParserRuleCall_2_0(), semanticObject.getId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (startAngle=DEGREES arcAngle=DEGREES)
	 */
	protected void sequence_KArc(EObject context, KArc semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         startAngle=DEGREES 
	 *         arcAngle=DEGREES 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KArc_KContainerRendering(EObject context, KArc semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (topLeft=KPosition? bottomRight=KPosition?)
	 */
	protected void sequence_KAreaPlacementData(EObject context, KAreaPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'?)
	 */
	protected void sequence_KBackground(EObject context, KBackground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? color=KColor alpha=ALPHA? (targetColor=KColor targetAlpha=ALPHA? gradientAngle=Float?)?)
	 */
	protected void sequence_KBackground_KColoring(EObject context, KBackground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         propagateToChildren?='propagate'? 
	 *         color=KColor 
	 *         alpha=ALPHA? 
	 *         (targetColor=KColor targetAlpha=ALPHA? gradientAngle=Float?)? 
	 *         functionId=QualifiedID?
	 *     )
	 */
	protected void sequence_KBackground_KColoring_KStyle(EObject context, KBackground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KBottomPosition}
	 */
	protected void sequence_KBottomPosition(EObject context, KBottomPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KChildArea}
	 */
	protected void sequence_KChildArea(EObject context, KChildArea semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((red=RED green=GREEN? blue=BLUE?) | (green=GREEN blue=BLUE?) | blue=BLUE)
	 */
	protected void sequence_KColor(EObject context, KColor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? color=KColor alpha=ALPHA? (targetColor=KColor targetAlpha=ALPHA? gradientAngle=Float?)?)
	 */
	protected void sequence_KColoring_KForeground(EObject context, KForeground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         propagateToChildren?='propagate'? 
	 *         color=KColor 
	 *         alpha=ALPHA? 
	 *         (targetColor=KColor targetAlpha=ALPHA? gradientAngle=Float?)? 
	 *         functionId=QualifiedID?
	 *     )
	 */
	protected void sequence_KColoring_KForeground_KStyle(EObject context, KForeground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (bundleName=QualifiedID? className=QualifiedID)? 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KContainerRendering_KCustomRendering(EObject context, KCustomRendering semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KContainerRendering(EObject context, KEllipse semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (bundleName=QualifiedID? imagePath=STRING)? 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KContainerRendering_KImage(EObject context, KImage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KContainerRendering(EObject context, KRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (cornerWidth=Float cornerHeight=Float)? 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData? childPlacement=KPlacement? children+=KRendering*)?
	 *     )
	 */
	protected void sequence_KContainerRendering_KRoundedRectangle(EObject context, KRoundedRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((bundleName=QualifiedID? className=QualifiedID)?)
	 */
	protected void sequence_KCustomRendering(EObject context, KCustomRendering semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xOffset=Float? 
	 *         yOffset=Float? 
	 *         width=Float? 
	 *         height=Float? 
	 *         relative=Float? 
	 *         absolute=Float? 
	 *         rotateWithLine=BOOLEAN?
	 *     )
	 */
	protected void sequence_KDecoratorPlacementData(EObject context, KDecoratorPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             (sourcePoint=KPoint ((bendPoints+=KPoint* targetPoint=KPoint) | targetPoint=EmptyKPoint)) | 
	 *             (sourcePoint=EmptyKPoint targetPoint=EmptyKPoint) | 
	 *             (sourcePoint=EmptyKPoint targetPoint=EmptyKPoint)
	 *         ) 
	 *         persistentEntries+=PersistentEntry*
	 *     )
	 */
	protected void sequence_KEdgeLayout(EObject context, KEdgeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         data+=KIdentifier? 
	 *         sourcePort=[KPort|QualifiedID]? 
	 *         target=[KNode|QualifiedID] 
	 *         targetPort=[KPort|QualifiedID]? 
	 *         ((data+=KEdgeLayout (labels+=KLabel | data+=KRendering)*) | data+=EmptyKEdgeLayout)
	 *     )
	 */
	protected void sequence_KEdge(EObject context, KEdge semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KEllipse}
	 */
	protected void sequence_KEllipse(EObject context, KEllipse semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? bold=BOOLEAN)
	 */
	protected void sequence_KFontBold(EObject context, KFontBold semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? bold=BOOLEAN functionId=QualifiedID?)
	 */
	protected void sequence_KFontBold_KStyle(EObject context, KFontBold semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? italic=BOOLEAN)
	 */
	protected void sequence_KFontItalic(EObject context, KFontItalic semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? italic=BOOLEAN functionId=QualifiedID?)
	 */
	protected void sequence_KFontItalic_KStyle(EObject context, KFontItalic semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? name=STRING)
	 */
	protected void sequence_KFontName(EObject context, KFontName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? name=STRING functionId=QualifiedID?)
	 */
	protected void sequence_KFontName_KStyle(EObject context, KFontName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? scaleWithZoom?='scale'? size=FSIZE)
	 */
	protected void sequence_KFontSize(EObject context, KFontSize semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? scaleWithZoom?='scale'? size=FSIZE functionId=QualifiedID?)
	 */
	protected void sequence_KFontSize_KStyle(EObject context, KFontSize semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? underlineStyle=UnderlineStyle)
	 */
	protected void sequence_KFontUnderlined(EObject context, KFontUnderlined semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? underlineStyle=UnderlineStyle functionId=QualifiedID?)
	 */
	protected void sequence_KFontUnderlined_KStyle(EObject context, KFontUnderlined semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'?)
	 */
	protected void sequence_KForeground(EObject context, KForeground semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         topLeft=KPosition? 
	 *         bottomRight=KPosition? 
	 *         minCellWidth=Float? 
	 *         minCellHeight=Float? 
	 *         maxCellWidth=Float? 
	 *         maxCellHeight=Float?
	 *     )
	 */
	protected void sequence_KGridPlacementData(EObject context, KGridPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (topLeft=KPosition? bottomRight=KPosition? numColumns=NATURAL?)
	 */
	protected void sequence_KGridPlacement(EObject context, KGridPlacement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? horizontalAlignment=HorizontalAlignment)
	 */
	protected void sequence_KHorizontalAlignment(EObject context, KHorizontalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? horizontalAlignment=HorizontalAlignment functionId=QualifiedID?)
	 */
	protected void sequence_KHorizontalAlignment_KStyle(EObject context, KHorizontalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=QualifiedID persistentEntries+=PersistentEntry*)
	 */
	protected void sequence_KIdentifier(EObject context, KIdentifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((bundleName=QualifiedID? imagePath=STRING)?)
	 */
	protected void sequence_KImage(EObject context, KImage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (top=Float? bottom=Float? left=Float? right=Float?)
	 */
	protected void sequence_KInsets(EObject context, KInsets semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? invisible=BOOLEAN)
	 */
	protected void sequence_KInvisibility(EObject context, KInvisibility semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? invisible=BOOLEAN functionId=QualifiedID?)
	 */
	protected void sequence_KInvisibility_KStyle(EObject context, KInvisibility semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (data+=KIdentifier? text=STRING? ((data+=KShapeLayout data+=KRendering*) | data+=EmptyKShapeLayout))
	 */
	protected void sequence_KLabel(EObject context, KLabel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KLeftPosition}
	 */
	protected void sequence_KLeftPosition(EObject context, KLeftPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineCap=LineCap)
	 */
	protected void sequence_KLineCap(EObject context, KLineCap semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineCap=LineCap functionId=QualifiedID?)
	 */
	protected void sequence_KLineCap_KStyle(EObject context, KLineCap semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineStyle=LineStyle)
	 */
	protected void sequence_KLineStyle(EObject context, KLineStyle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineStyle=LineStyle functionId=QualifiedID?)
	 */
	protected void sequence_KLineStyle_KStyle(EObject context, KLineStyle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineWidth=Float)
	 */
	protected void sequence_KLineWidth(EObject context, KLineWidth semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? lineWidth=Float functionId=QualifiedID?)
	 */
	protected void sequence_KLineWidth_KStyle(EObject context, KLineWidth semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((xpos=Float? ypos=Float?)? (width=Float? height=Float?)? persistentEntries+=PersistentEntry* (insets=KInsets | insets=EmptyKInsets))
	 */
	protected void sequence_KNodeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         data+=KIdentifier? 
	 *         (
	 *             (
	 *                 data+=KNodeLayout 
	 *                 (
	 *                     labels+=KLabel | 
	 *                     children+=KNode | 
	 *                     ports+=KPort | 
	 *                     outgoingEdges+=KEdge | 
	 *                     data+=KRendering | 
	 *                     data+=KRenderingLibrary
	 *                 )*
	 *             ) | 
	 *             data+=EmptyKNodeLayout
	 *         )
	 *     )
	 */
	protected void sequence_KNode(EObject context, KNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         referencePoint=KPosition? 
	 *         horizontalAlignment=HorizontalAlignment? 
	 *         verticalAlignment=VerticalAlignment? 
	 *         horizontalMargin=Float? 
	 *         verticalMargin=Float?
	 *     )
	 */
	protected void sequence_KPointPlacementData(EObject context, KPointPlacementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x=Float y=Float)
	 */
	protected void sequence_KPoint(EObject context, KPoint semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KLayoutDataPackage.Literals.KPOINT__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KLayoutDataPackage.Literals.KPOINT__X));
			if(transientValues.isValueTransient(semanticObject, KLayoutDataPackage.Literals.KPOINT__Y) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KLayoutDataPackage.Literals.KPOINT__Y));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKPointAccess().getXFloatParserRuleCall_0_0(), semanticObject.getX());
		feeder.accept(grammarAccess.getKPointAccess().getYFloatParserRuleCall_2_0(), semanticObject.getY());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     {KPolygon}
	 */
	protected void sequence_KPolygon(EObject context, KPolygon semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             styles+=KStyle* 
	 *             actions+=KAction* 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             jointPointRendering=KRendering? 
	 *             children+=KRendering*
	 *         )?
	 *     )
	 */
	protected void sequence_KPolyline(EObject context, KPolygon semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             styles+=KStyle* 
	 *             actions+=KAction* 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             jointPointRendering=KRendering? 
	 *             children+=KRendering*
	 *         )?
	 *     )
	 */
	protected void sequence_KPolyline(EObject context, KPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         bendRadius=Float? 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             styles+=KStyle* 
	 *             actions+=KAction* 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             jointPointRendering=KRendering? 
	 *             children+=KRendering*
	 *         )?
	 *     )
	 */
	protected void sequence_KPolyline_KRoundedBendsPolyline(EObject context, KRoundedBendsPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (
	 *             (points+=KPosition points+=KPosition*)? 
	 *             styles+=KStyle* 
	 *             actions+=KAction* 
	 *             placementData=KPlacementData? 
	 *             childPlacement=KPlacement? 
	 *             jointPointRendering=KRendering? 
	 *             children+=KRendering*
	 *         )?
	 *     )
	 */
	protected void sequence_KPolyline(EObject context, KSpline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (data+=KIdentifier? ((data+=KShapeLayout (labels+=KLabel | data+=KRendering)*) | data+=EmptyKShapeLayout))
	 */
	protected void sequence_KPort(EObject context, KPort semanticObject) {
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
	 *     {KRectangle}
	 */
	protected void sequence_KRectangle(EObject context, KRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (persistentEntries+=PersistentEntry* (renderings+=KRendering | renderings+=KStyleHolder)*)
	 */
	protected void sequence_KRenderingLibrary(EObject context, KRenderingLibrary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     rendering=[KRendering|QualifiedID]
	 */
	protected void sequence_KRenderingRef(EObject context, KRenderingRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         rendering=[KRendering|QualifiedID] 
	 *         id=QualifiedID? 
	 *         persistentEntries+=PersistentEntry* 
	 *         (styles+=KStyle* actions+=KAction* placementData=KPlacementData?)?
	 *     )
	 */
	protected void sequence_KRenderingRef_KSimpleRendering(EObject context, KRenderingRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KRightPosition}
	 */
	protected void sequence_KRightPosition(EObject context, KRightPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? rotation=Float)
	 */
	protected void sequence_KRotation(EObject context, KRotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? rotation=Float functionId=QualifiedID?)
	 */
	protected void sequence_KRotation_KStyle(EObject context, KRotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (bendRadius=Float?)
	 */
	protected void sequence_KRoundedBendsPolyline(EObject context, KRoundedBendsPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((cornerWidth=Float cornerHeight=Float)?)
	 */
	protected void sequence_KRoundedRectangle(EObject context, KRoundedRectangle semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? color=KColor? (xOffset=Float yOffset=Float blur=Float?)?)
	 */
	protected void sequence_KShadow(EObject context, KShadow semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? color=KColor? (xOffset=Float yOffset=Float blur=Float?)? functionId=QualifiedID?)
	 */
	protected void sequence_KShadow_KStyle(EObject context, KShadow semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((xpos=Float? ypos=Float?)? (width=Float? height=Float?)? persistentEntries+=PersistentEntry*)
	 */
	protected void sequence_KShapeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KPolyline}
	 */
	protected void sequence_KSimplePolyline(EObject context, KPolyline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=QualifiedID? persistentEntries+=PersistentEntry* (styles+=KStyle* actions+=KAction* placementData=KPlacementData?)?)
	 */
	protected void sequence_KSimpleRendering(EObject context, KChildArea semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (text=STRING? id=QualifiedID? persistentEntries+=PersistentEntry* (styles+=KStyle* actions+=KAction* placementData=KPlacementData?)?)
	 */
	protected void sequence_KSimpleRendering_KText(EObject context, KText semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KSpline}
	 */
	protected void sequence_KSpline(EObject context, KSpline semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=QualifiedID? styles+=KStyle*)
	 */
	protected void sequence_KStyleHolder(EObject context, KStyleHolder semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     styleHolder=[KStyleHolder|QualifiedID]
	 */
	protected void sequence_KStyleRef(EObject context, KStyleRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (styleHolder=[KStyleHolder|QualifiedID] functionId=QualifiedID?)
	 */
	protected void sequence_KStyle_KStyleRef(EObject context, KStyleRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? verticalAlignment=VerticalAlignment functionId=QualifiedID?)
	 */
	protected void sequence_KStyle_KVerticalAlignment(EObject context, KVerticalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (text=STRING?)
	 */
	protected void sequence_KText(EObject context, KText semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {KTopPosition}
	 */
	protected void sequence_KTopPosition(EObject context, KTopPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (propagateToChildren?='propagate'? verticalAlignment=VerticalAlignment)
	 */
	protected void sequence_KVerticalAlignment(EObject context, KVerticalAlignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((relative=PERCENT? absolute=Float?) | relative=PERCENT)
	 */
	protected void sequence_KXPosition(EObject context, KLeftPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((relative=PERCENT? absolute=Float?) | relative=PERCENT)
	 */
	protected void sequence_KXPosition(EObject context, KRightPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((relative=PERCENT? absolute=Float?) | relative=PERCENT)
	 */
	protected void sequence_KYPosition(EObject context, KBottomPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((relative=PERCENT? absolute=Float?) | relative=PERCENT)
	 */
	protected void sequence_KYPosition(EObject context, KTopPosition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         data+=KIdentifier? 
	 *         data+=KNodeLayout 
	 *         (
	 *             labels+=KLabel | 
	 *             children+=KNode | 
	 *             ports+=KPort | 
	 *             outgoingEdges+=KEdge | 
	 *             data+=KRendering | 
	 *             data+=KRenderingLibrary
	 *         )*
	 *     )
	 */
	protected void sequence_ParentKNode(EObject context, KNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (key=QualifiedID value=PropertyValue)
	 */
	protected void sequence_PersistentEntry(EObject context, PersistentEntry semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__KEY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__KEY));
			if(transientValues.isValueTransient(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0(), semanticObject.getKey());
		feeder.accept(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
}
