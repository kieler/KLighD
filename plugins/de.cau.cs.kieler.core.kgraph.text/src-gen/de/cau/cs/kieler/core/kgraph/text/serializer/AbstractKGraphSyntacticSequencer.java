package de.cau.cs.kieler.core.kgraph.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("restriction")
public class AbstractKGraphSyntacticSequencer extends AbstractSyntacticSequencer {

	protected KGraphGrammarAccess grammarAccess;
	protected AbstractElementAlias match_KArc_ColonKeyword_5_1_1_q;
	protected AbstractElementAlias match_KArc_CommaKeyword_3_q;
	protected AbstractElementAlias match_KArc_CommaKeyword_5_1_3_0_q;
	protected AbstractElementAlias match_KArc_CommaKeyword_5_3_3_0_q;
	protected AbstractElementAlias match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q;
	protected AbstractElementAlias match_KChildArea_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KChildArea_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q;
	protected AbstractElementAlias match_KCustomRendering_ColonKeyword_2_5_1_q;
	protected AbstractElementAlias match_KCustomRendering_CommaKeyword_2_5_3_0_q;
	protected AbstractElementAlias match_KCustomRendering_CommaKeyword_2_7_3_0_q;
	protected AbstractElementAlias match_KDirectPlacementData_CommaKeyword_4_q;
	protected AbstractElementAlias match_KEdgeLayout_CommaKeyword_6_3_0_q;
	protected AbstractElementAlias match_KEdgeLayout_CommaKeyword_7_3_0_q;
	protected AbstractElementAlias match_KEdge_ColonKeyword_5_1_q;
	protected AbstractElementAlias match_KEdge_ColonKeyword_6_1_q;
	protected AbstractElementAlias match_KEdge_CommaKeyword_5_3_0_q;
	protected AbstractElementAlias match_KEdge_CommaKeyword_6_3_0_q;
	protected AbstractElementAlias match_KEllipse_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KEllipse_ColonKeyword_2_2_1_q;
	protected AbstractElementAlias match_KEllipse_ColonKeyword_2_3_1_q;
	protected AbstractElementAlias match_KEllipse_ColonKeyword_2_4_1_q;
	protected AbstractElementAlias match_KEllipse_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KEllipse_CommaKeyword_2_4_3_0_q;
	protected AbstractElementAlias match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KImage_ColonKeyword_4_1_1_q;
	protected AbstractElementAlias match_KImage_CommaKeyword_4_1_3_0_q;
	protected AbstractElementAlias match_KImage_CommaKeyword_4_3_3_0_q;
	protected AbstractElementAlias match_KImage___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_5__q;
	protected AbstractElementAlias match_KLabel_ColonKeyword_3_1_q;
	protected AbstractElementAlias match_KLabel_CommaKeyword_3_3_0_q;
	protected AbstractElementAlias match_KNode_ColonKeyword_4_1_q;
	protected AbstractElementAlias match_KNode_ColonKeyword_5_1_q;
	protected AbstractElementAlias match_KNode_CommaKeyword_3_2_0_q;
	protected AbstractElementAlias match_KNode_CommaKeyword_4_3_0_q;
	protected AbstractElementAlias match_KNode_CommaKeyword_5_3_0_q;
	protected AbstractElementAlias match_KNode_CommaKeyword_7_1_0_q;
	protected AbstractElementAlias match_KPolygon_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KPolygon_ColonKeyword_2_2_1_q;
	protected AbstractElementAlias match_KPolygon_ColonKeyword_2_3_1_q;
	protected AbstractElementAlias match_KPolygon_ColonKeyword_2_4_1_q;
	protected AbstractElementAlias match_KPolygon_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KPolygon_CommaKeyword_2_4_3_0_q;
	protected AbstractElementAlias match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KPolylinePlacementData_ColonKeyword_3_q;
	protected AbstractElementAlias match_KPolylinePlacementData_CommaKeyword_5_0_q;
	protected AbstractElementAlias match_KPolyline_Impl_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KPolyline_Impl_ColonKeyword_2_2_1_q;
	protected AbstractElementAlias match_KPolyline_Impl_ColonKeyword_2_3_1_q;
	protected AbstractElementAlias match_KPolyline_Impl_ColonKeyword_2_4_1_q;
	protected AbstractElementAlias match_KPolyline_Impl_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KPolyline_Impl_CommaKeyword_2_4_3_0_q;
	protected AbstractElementAlias match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KPort_ColonKeyword_3_1_q;
	protected AbstractElementAlias match_KPort_ColonKeyword_4_1_q;
	protected AbstractElementAlias match_KPort_ColonKeyword_5_1_q;
	protected AbstractElementAlias match_KPort_CommaKeyword_3_3_0_q;
	protected AbstractElementAlias match_KPort_CommaKeyword_4_3_0_q;
	protected AbstractElementAlias match_KPort_CommaKeyword_5_3_0_q;
	protected AbstractElementAlias match_KRectangle_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KRectangle_ColonKeyword_2_2_1_q;
	protected AbstractElementAlias match_KRectangle_ColonKeyword_2_3_1_q;
	protected AbstractElementAlias match_KRectangle_ColonKeyword_2_4_1_q;
	protected AbstractElementAlias match_KRectangle_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KRectangle_CommaKeyword_2_4_3_0_q;
	protected AbstractElementAlias match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KRenderingLibrary_CommaKeyword_3_1_0_q;
	protected AbstractElementAlias match_KRenderingRef_CommaKeyword_3_2_3_0_q;
	protected AbstractElementAlias match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_KRoundedRectangle_ColonKeyword_5_1_1_q;
	protected AbstractElementAlias match_KRoundedRectangle_ColonKeyword_5_2_1_q;
	protected AbstractElementAlias match_KRoundedRectangle_ColonKeyword_5_3_1_q;
	protected AbstractElementAlias match_KRoundedRectangle_ColonKeyword_5_4_1_q;
	protected AbstractElementAlias match_KRoundedRectangle_CommaKeyword_3_q;
	protected AbstractElementAlias match_KRoundedRectangle_CommaKeyword_5_1_3_0_q;
	protected AbstractElementAlias match_KRoundedRectangle_CommaKeyword_5_4_3_0_q;
	protected AbstractElementAlias match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q;
	protected AbstractElementAlias match_KShapeLayout_CommaKeyword_8_3_0_q;
	protected AbstractElementAlias match_KSpline_ColonKeyword_2_1_1_q;
	protected AbstractElementAlias match_KSpline_CommaKeyword_2_1_3_0_q;
	protected AbstractElementAlias match_KSpline_CommaKeyword_2_3_3_0_q;
	protected AbstractElementAlias match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KText_ColonKeyword_3_2_1_q;
	protected AbstractElementAlias match_KText_CommaKeyword_3_2_3_0_q;
	protected AbstractElementAlias match_KText_CommaKeyword_3_4_3_0_q;
	protected AbstractElementAlias match_KText___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_6__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (KGraphGrammarAccess) access;
		match_KArc_ColonKeyword_5_1_1_q = new TokenAlias(false, true, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
		match_KArc_CommaKeyword_3_q = new TokenAlias(false, true, grammarAccess.getKArcAccess().getCommaKeyword_3());
		match_KArc_CommaKeyword_5_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
		match_KArc_CommaKeyword_5_3_3_0_q = new TokenAlias(false, true, grammarAccess.getKArcAccess().getCommaKeyword_5_3_3_0());
		match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_5()));
		match_KChildArea_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
		match_KChildArea_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
		match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_2_3()));
		match_KCustomRendering_ColonKeyword_2_5_1_q = new TokenAlias(false, true, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
		match_KCustomRendering_CommaKeyword_2_5_3_0_q = new TokenAlias(false, true, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
		match_KCustomRendering_CommaKeyword_2_7_3_0_q = new TokenAlias(false, true, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_7_3_0());
		match_KDirectPlacementData_CommaKeyword_4_q = new TokenAlias(false, true, grammarAccess.getKDirectPlacementDataAccess().getCommaKeyword_4());
		match_KEdgeLayout_CommaKeyword_6_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_6_3_0());
		match_KEdgeLayout_CommaKeyword_7_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_7_3_0());
		match_KEdge_ColonKeyword_5_1_q = new TokenAlias(false, true, grammarAccess.getKEdgeAccess().getColonKeyword_5_1());
		match_KEdge_ColonKeyword_6_1_q = new TokenAlias(false, true, grammarAccess.getKEdgeAccess().getColonKeyword_6_1());
		match_KEdge_CommaKeyword_5_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeAccess().getCommaKeyword_5_3_0());
		match_KEdge_CommaKeyword_6_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeAccess().getCommaKeyword_6_3_0());
		match_KEllipse_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getColonKeyword_2_1_1());
		match_KEllipse_ColonKeyword_2_2_1_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getColonKeyword_2_2_1());
		match_KEllipse_ColonKeyword_2_3_1_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getColonKeyword_2_3_1());
		match_KEllipse_ColonKeyword_2_4_1_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getColonKeyword_2_4_1());
		match_KEllipse_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getCommaKeyword_2_1_3_0());
		match_KEllipse_CommaKeyword_2_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKEllipseAccess().getCommaKeyword_2_4_3_0());
		match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_2_5()));
		match_KImage_ColonKeyword_4_1_1_q = new TokenAlias(false, true, grammarAccess.getKImageAccess().getColonKeyword_4_1_1());
		match_KImage_CommaKeyword_4_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKImageAccess().getCommaKeyword_4_1_3_0());
		match_KImage_CommaKeyword_4_3_3_0_q = new TokenAlias(false, true, grammarAccess.getKImageAccess().getCommaKeyword_4_3_3_0());
		match_KImage___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_5()));
		match_KLabel_ColonKeyword_3_1_q = new TokenAlias(false, true, grammarAccess.getKLabelAccess().getColonKeyword_3_1());
		match_KLabel_CommaKeyword_3_3_0_q = new TokenAlias(false, true, grammarAccess.getKLabelAccess().getCommaKeyword_3_3_0());
		match_KNode_ColonKeyword_4_1_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getColonKeyword_4_1());
		match_KNode_ColonKeyword_5_1_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getColonKeyword_5_1());
		match_KNode_CommaKeyword_3_2_0_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getCommaKeyword_3_2_0());
		match_KNode_CommaKeyword_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getCommaKeyword_4_3_0());
		match_KNode_CommaKeyword_5_3_0_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getCommaKeyword_5_3_0());
		match_KNode_CommaKeyword_7_1_0_q = new TokenAlias(false, true, grammarAccess.getKNodeAccess().getCommaKeyword_7_1_0());
		match_KPolygon_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getColonKeyword_2_1_1());
		match_KPolygon_ColonKeyword_2_2_1_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getColonKeyword_2_2_1());
		match_KPolygon_ColonKeyword_2_3_1_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getColonKeyword_2_3_1());
		match_KPolygon_ColonKeyword_2_4_1_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getColonKeyword_2_4_1());
		match_KPolygon_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getCommaKeyword_2_1_3_0());
		match_KPolygon_CommaKeyword_2_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKPolygonAccess().getCommaKeyword_2_4_3_0());
		match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_2_5()));
		match_KPolylinePlacementData_ColonKeyword_3_q = new TokenAlias(false, true, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_3());
		match_KPolylinePlacementData_CommaKeyword_5_0_q = new TokenAlias(false, true, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
		match_KPolyline_Impl_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_1_1());
		match_KPolyline_Impl_ColonKeyword_2_2_1_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2_1());
		match_KPolyline_Impl_ColonKeyword_2_3_1_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_3_1());
		match_KPolyline_Impl_ColonKeyword_2_4_1_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_4_1());
		match_KPolyline_Impl_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_1_3_0());
		match_KPolyline_Impl_CommaKeyword_2_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_4_3_0());
		match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_2_5()));
		match_KPort_ColonKeyword_3_1_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getColonKeyword_3_1());
		match_KPort_ColonKeyword_4_1_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getColonKeyword_4_1());
		match_KPort_ColonKeyword_5_1_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getColonKeyword_5_1());
		match_KPort_CommaKeyword_3_3_0_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getCommaKeyword_3_3_0());
		match_KPort_CommaKeyword_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getCommaKeyword_4_3_0());
		match_KPort_CommaKeyword_5_3_0_q = new TokenAlias(false, true, grammarAccess.getKPortAccess().getCommaKeyword_5_3_0());
		match_KRectangle_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1());
		match_KRectangle_ColonKeyword_2_2_1_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1());
		match_KRectangle_ColonKeyword_2_3_1_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1());
		match_KRectangle_ColonKeyword_2_4_1_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1());
		match_KRectangle_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getCommaKeyword_2_1_3_0());
		match_KRectangle_CommaKeyword_2_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKRectangleAccess().getCommaKeyword_2_4_3_0());
		match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_2_5()));
		match_KRenderingLibrary_CommaKeyword_3_1_0_q = new TokenAlias(false, true, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_1_0());
		match_KRenderingRef_CommaKeyword_3_2_3_0_q = new TokenAlias(false, true, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_2_3_0());
		match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_3()));
		match_KRoundedRectangle_ColonKeyword_5_1_1_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1_1());
		match_KRoundedRectangle_ColonKeyword_5_2_1_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_2_1());
		match_KRoundedRectangle_ColonKeyword_5_3_1_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_3_1());
		match_KRoundedRectangle_ColonKeyword_5_4_1_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_4_1());
		match_KRoundedRectangle_CommaKeyword_3_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3());
		match_KRoundedRectangle_CommaKeyword_5_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_1_3_0());
		match_KRoundedRectangle_CommaKeyword_5_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_4_3_0());
		match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_5_5()));
		match_KShapeLayout_CommaKeyword_8_3_0_q = new TokenAlias(false, true, grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0());
		match_KSpline_ColonKeyword_2_1_1_q = new TokenAlias(false, true, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
		match_KSpline_CommaKeyword_2_1_3_0_q = new TokenAlias(false, true, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
		match_KSpline_CommaKeyword_2_3_3_0_q = new TokenAlias(false, true, grammarAccess.getKSplineAccess().getCommaKeyword_2_3_3_0());
		match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_5()));
		match_KText_ColonKeyword_3_2_1_q = new TokenAlias(false, true, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
		match_KText_CommaKeyword_3_2_3_0_q = new TokenAlias(false, true, grammarAccess.getKTextAccess().getCommaKeyword_3_2_3_0());
		match_KText_CommaKeyword_3_4_3_0_q = new TokenAlias(false, true, grammarAccess.getKTextAccess().getCommaKeyword_3_4_3_0());
		match_KText___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_6__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_6()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_KArc_ColonKeyword_5_1_1_q.equals(syntax))
				emit_KArc_ColonKeyword_5_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KArc_CommaKeyword_3_q.equals(syntax))
				emit_KArc_CommaKeyword_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KArc_CommaKeyword_5_1_3_0_q.equals(syntax))
				emit_KArc_CommaKeyword_5_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KArc_CommaKeyword_5_3_3_0_q.equals(syntax))
				emit_KArc_CommaKeyword_5_3_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q.equals(syntax))
				emit_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KChildArea_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KChildArea_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KChildArea_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KChildArea_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q.equals(syntax))
				emit_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KCustomRendering_ColonKeyword_2_5_1_q.equals(syntax))
				emit_KCustomRendering_ColonKeyword_2_5_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KCustomRendering_CommaKeyword_2_5_3_0_q.equals(syntax))
				emit_KCustomRendering_CommaKeyword_2_5_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KCustomRendering_CommaKeyword_2_7_3_0_q.equals(syntax))
				emit_KCustomRendering_CommaKeyword_2_7_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KDirectPlacementData_CommaKeyword_4_q.equals(syntax))
				emit_KDirectPlacementData_CommaKeyword_4_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdgeLayout_CommaKeyword_6_3_0_q.equals(syntax))
				emit_KEdgeLayout_CommaKeyword_6_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdgeLayout_CommaKeyword_7_3_0_q.equals(syntax))
				emit_KEdgeLayout_CommaKeyword_7_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdge_ColonKeyword_5_1_q.equals(syntax))
				emit_KEdge_ColonKeyword_5_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdge_ColonKeyword_6_1_q.equals(syntax))
				emit_KEdge_ColonKeyword_6_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdge_CommaKeyword_5_3_0_q.equals(syntax))
				emit_KEdge_CommaKeyword_5_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdge_CommaKeyword_6_3_0_q.equals(syntax))
				emit_KEdge_CommaKeyword_6_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KEllipse_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_ColonKeyword_2_2_1_q.equals(syntax))
				emit_KEllipse_ColonKeyword_2_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_ColonKeyword_2_3_1_q.equals(syntax))
				emit_KEllipse_ColonKeyword_2_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_ColonKeyword_2_4_1_q.equals(syntax))
				emit_KEllipse_ColonKeyword_2_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KEllipse_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse_CommaKeyword_2_4_3_0_q.equals(syntax))
				emit_KEllipse_CommaKeyword_2_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KImage_ColonKeyword_4_1_1_q.equals(syntax))
				emit_KImage_ColonKeyword_4_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KImage_CommaKeyword_4_1_3_0_q.equals(syntax))
				emit_KImage_CommaKeyword_4_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KImage_CommaKeyword_4_3_3_0_q.equals(syntax))
				emit_KImage_CommaKeyword_4_3_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KImage___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_5__q.equals(syntax))
				emit_KImage___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KLabel_ColonKeyword_3_1_q.equals(syntax))
				emit_KLabel_ColonKeyword_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KLabel_CommaKeyword_3_3_0_q.equals(syntax))
				emit_KLabel_CommaKeyword_3_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_ColonKeyword_4_1_q.equals(syntax))
				emit_KNode_ColonKeyword_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_ColonKeyword_5_1_q.equals(syntax))
				emit_KNode_ColonKeyword_5_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_CommaKeyword_3_2_0_q.equals(syntax))
				emit_KNode_CommaKeyword_3_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_CommaKeyword_4_3_0_q.equals(syntax))
				emit_KNode_CommaKeyword_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_CommaKeyword_5_3_0_q.equals(syntax))
				emit_KNode_CommaKeyword_5_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNode_CommaKeyword_7_1_0_q.equals(syntax))
				emit_KNode_CommaKeyword_7_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KPolygon_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_ColonKeyword_2_2_1_q.equals(syntax))
				emit_KPolygon_ColonKeyword_2_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_ColonKeyword_2_3_1_q.equals(syntax))
				emit_KPolygon_ColonKeyword_2_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_ColonKeyword_2_4_1_q.equals(syntax))
				emit_KPolygon_ColonKeyword_2_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KPolygon_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon_CommaKeyword_2_4_3_0_q.equals(syntax))
				emit_KPolygon_CommaKeyword_2_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolylinePlacementData_ColonKeyword_3_q.equals(syntax))
				emit_KPolylinePlacementData_ColonKeyword_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolylinePlacementData_CommaKeyword_5_0_q.equals(syntax))
				emit_KPolylinePlacementData_CommaKeyword_5_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KPolyline_Impl_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_ColonKeyword_2_2_1_q.equals(syntax))
				emit_KPolyline_Impl_ColonKeyword_2_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_ColonKeyword_2_3_1_q.equals(syntax))
				emit_KPolyline_Impl_ColonKeyword_2_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_ColonKeyword_2_4_1_q.equals(syntax))
				emit_KPolyline_Impl_ColonKeyword_2_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KPolyline_Impl_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl_CommaKeyword_2_4_3_0_q.equals(syntax))
				emit_KPolyline_Impl_CommaKeyword_2_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_ColonKeyword_3_1_q.equals(syntax))
				emit_KPort_ColonKeyword_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_ColonKeyword_4_1_q.equals(syntax))
				emit_KPort_ColonKeyword_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_ColonKeyword_5_1_q.equals(syntax))
				emit_KPort_ColonKeyword_5_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_CommaKeyword_3_3_0_q.equals(syntax))
				emit_KPort_CommaKeyword_3_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_CommaKeyword_4_3_0_q.equals(syntax))
				emit_KPort_CommaKeyword_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPort_CommaKeyword_5_3_0_q.equals(syntax))
				emit_KPort_CommaKeyword_5_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KRectangle_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_ColonKeyword_2_2_1_q.equals(syntax))
				emit_KRectangle_ColonKeyword_2_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_ColonKeyword_2_3_1_q.equals(syntax))
				emit_KRectangle_ColonKeyword_2_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_ColonKeyword_2_4_1_q.equals(syntax))
				emit_KRectangle_ColonKeyword_2_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KRectangle_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle_CommaKeyword_2_4_3_0_q.equals(syntax))
				emit_KRectangle_CommaKeyword_2_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRenderingLibrary_CommaKeyword_3_1_0_q.equals(syntax))
				emit_KRenderingLibrary_CommaKeyword_3_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRenderingRef_CommaKeyword_3_2_3_0_q.equals(syntax))
				emit_KRenderingRef_CommaKeyword_3_2_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_ColonKeyword_5_1_1_q.equals(syntax))
				emit_KRoundedRectangle_ColonKeyword_5_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_ColonKeyword_5_2_1_q.equals(syntax))
				emit_KRoundedRectangle_ColonKeyword_5_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_ColonKeyword_5_3_1_q.equals(syntax))
				emit_KRoundedRectangle_ColonKeyword_5_3_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_ColonKeyword_5_4_1_q.equals(syntax))
				emit_KRoundedRectangle_ColonKeyword_5_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_CommaKeyword_3_q.equals(syntax))
				emit_KRoundedRectangle_CommaKeyword_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_CommaKeyword_5_1_3_0_q.equals(syntax))
				emit_KRoundedRectangle_CommaKeyword_5_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle_CommaKeyword_5_4_3_0_q.equals(syntax))
				emit_KRoundedRectangle_CommaKeyword_5_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q.equals(syntax))
				emit_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_CommaKeyword_8_3_0_q.equals(syntax))
				emit_KShapeLayout_CommaKeyword_8_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSpline_ColonKeyword_2_1_1_q.equals(syntax))
				emit_KSpline_ColonKeyword_2_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSpline_CommaKeyword_2_1_3_0_q.equals(syntax))
				emit_KSpline_CommaKeyword_2_1_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSpline_CommaKeyword_2_3_3_0_q.equals(syntax))
				emit_KSpline_CommaKeyword_2_3_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KText_ColonKeyword_3_2_1_q.equals(syntax))
				emit_KText_ColonKeyword_3_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KText_CommaKeyword_3_2_3_0_q.equals(syntax))
				emit_KText_CommaKeyword_3_2_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KText_CommaKeyword_3_4_3_0_q.equals(syntax))
				emit_KText_CommaKeyword_3_4_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KText___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_6__q.equals(syntax))
				emit_KText___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_6__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KArc_ColonKeyword_5_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KArc_CommaKeyword_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KArc_CommaKeyword_5_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KArc_CommaKeyword_5_3_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KChildArea_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KChildArea_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KCustomRendering_ColonKeyword_2_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KCustomRendering_CommaKeyword_2_5_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KCustomRendering_CommaKeyword_2_7_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KDirectPlacementData_CommaKeyword_4_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEdgeLayout_CommaKeyword_6_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEdgeLayout_CommaKeyword_7_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEdge_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEdge_ColonKeyword_6_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEdge_CommaKeyword_5_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEdge_CommaKeyword_6_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEllipse_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEllipse_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEllipse_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KEllipse_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEllipse_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KEllipse_CommaKeyword_2_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KImage_ColonKeyword_4_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KImage_CommaKeyword_4_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KImage_CommaKeyword_4_3_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KImage___LeftCurlyBracketKeyword_4_0_RightCurlyBracketKeyword_4_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KLabel_ColonKeyword_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KLabel_CommaKeyword_3_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KNode_ColonKeyword_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KNode_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KNode_CommaKeyword_3_2_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KNode_CommaKeyword_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KNode_CommaKeyword_5_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KNode_CommaKeyword_7_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolygon_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolygon_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolygon_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolygon_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPolygon_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPolygon_CommaKeyword_2_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolylinePlacementData_ColonKeyword_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPolylinePlacementData_CommaKeyword_5_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolyline_Impl_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolyline_Impl_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolyline_Impl_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPolyline_Impl_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPolyline_Impl_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPolyline_Impl_CommaKeyword_2_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPort_ColonKeyword_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPort_ColonKeyword_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KPort_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPort_CommaKeyword_3_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPort_CommaKeyword_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KPort_CommaKeyword_5_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRectangle_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRectangle_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRectangle_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRectangle_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRectangle_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRectangle_CommaKeyword_2_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRenderingLibrary_CommaKeyword_3_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRenderingRef_CommaKeyword_3_2_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRoundedRectangle_ColonKeyword_5_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRoundedRectangle_ColonKeyword_5_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRoundedRectangle_ColonKeyword_5_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KRoundedRectangle_ColonKeyword_5_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRoundedRectangle_CommaKeyword_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRoundedRectangle_CommaKeyword_5_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KRoundedRectangle_CommaKeyword_5_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KShapeLayout_CommaKeyword_8_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KSpline_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KSpline_CommaKeyword_2_1_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KSpline_CommaKeyword_2_3_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_KText_ColonKeyword_3_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KText_CommaKeyword_3_2_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','?
	 */
	protected void emit_KText_CommaKeyword_3_4_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_KText___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_6__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
