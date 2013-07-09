package de.cau.cs.kieler.core.kgraph.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public abstract class AbstractKGraphSyntacticSequencer extends AbstractSyntacticSequencer {

	protected KGraphGrammarAccess grammarAccess;
	protected AbstractElementAlias match_KContainerRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_KContainerRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q;
	protected AbstractElementAlias match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a;
	protected AbstractElementAlias match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a;
	protected AbstractElementAlias match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p;
	protected AbstractElementAlias match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a;
	protected AbstractElementAlias match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p;
	protected AbstractElementAlias match_KEdgeLayout___PropertiesKeyword_1_0_ColonKeyword_1_1__q;
	protected AbstractElementAlias match_KIdentifier___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q;
	protected AbstractElementAlias match_KNodeLayout_____PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a;
	protected AbstractElementAlias match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__a;
	protected AbstractElementAlias match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__p;
	protected AbstractElementAlias match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__a;
	protected AbstractElementAlias match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__p;
	protected AbstractElementAlias match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__a;
	protected AbstractElementAlias match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__p;
	protected AbstractElementAlias match_KPolyline___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_RightCurlyBracketKeyword_3_4__q;
	protected AbstractElementAlias match_KPolyline___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q;
	protected AbstractElementAlias match_KPolyline_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a;
	protected AbstractElementAlias match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a;
	protected AbstractElementAlias match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p;
	protected AbstractElementAlias match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p;
	protected AbstractElementAlias match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__a;
	protected AbstractElementAlias match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__p;
	protected AbstractElementAlias match_KRenderingLibrary___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q;
	protected AbstractElementAlias match_KShapeLayout_____PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a;
	protected AbstractElementAlias match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__a;
	protected AbstractElementAlias match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__p;
	protected AbstractElementAlias match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__a;
	protected AbstractElementAlias match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__p;
	protected AbstractElementAlias match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__a;
	protected AbstractElementAlias match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__p;
	protected AbstractElementAlias match_KSimpleRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_2__q;
	protected AbstractElementAlias match_KSimpleRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q;
	protected AbstractElementAlias match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a;
	protected AbstractElementAlias match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a;
	protected AbstractElementAlias match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p;
	protected AbstractElementAlias match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a;
	protected AbstractElementAlias match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p;
	protected AbstractElementAlias match_ParentKNode_KgraphKeyword_1_0_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (KGraphGrammarAccess) access;
		match_KContainerRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getLeftCurlyBracketKeyword_3_0()), new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1()))), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getRightCurlyBracketKeyword_3_3()));
		match_KContainerRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getLeftSquareBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getRightSquareBracketKeyword_2_2()));
		match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1())));
		match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a = new GroupAlias(true, true, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1())), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1()));
		match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p = new GroupAlias(true, false, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1())), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1()));
		match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a = new GroupAlias(true, true, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1())), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1()));
		match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p = new GroupAlias(true, false, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1())), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1()));
		match_KEdgeLayout___PropertiesKeyword_1_0_ColonKeyword_1_1__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKEdgeLayoutAccess().getPropertiesKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_1_1()));
		match_KIdentifier___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKIdentifierAccess().getLeftSquareBracketKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getKIdentifierAccess().getRightSquareBracketKeyword_1_2()));
		match_KNodeLayout_____PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1())));
		match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()));
		match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()));
		match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1()));
		match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1()));
		match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1()));
		match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1()))), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0()), new TokenAlias(false, false, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1()));
		match_KPolyline___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_RightCurlyBracketKeyword_3_4__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getLeftCurlyBracketKeyword_3_0()), new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getRightCurlyBracketKeyword_3_4()));
		match_KPolyline___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getLeftSquareBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getRightSquareBracketKeyword_2_2()));
		match_KPolyline_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1())));
		match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()));
		match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()));
		match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1()));
		match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1()));
		match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0()), new TokenAlias(false, false, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1()));
		match_KRenderingLibrary___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRenderingLibraryAccess().getLeftSquareBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKRenderingLibraryAccess().getRightSquareBracketKeyword_2_2()));
		match_KShapeLayout_____PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1())));
		match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()));
		match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()));
		match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1()));
		match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1()));
		match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__a = new GroupAlias(true, true, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1()));
		match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__p = new GroupAlias(true, false, new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1()))), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1()));
		match_KSimpleRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getLeftCurlyBracketKeyword_3_0()), new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1()))), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getRightCurlyBracketKeyword_3_2()));
		match_KSimpleRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getLeftSquareBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getRightSquareBracketKeyword_2_2()));
		match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1())));
		match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a = new GroupAlias(true, true, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1())), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1()));
		match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p = new GroupAlias(true, false, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1())), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1()));
		match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a = new GroupAlias(true, true, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1())), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1()));
		match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p = new GroupAlias(true, false, new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1())), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1()));
		match_ParentKNode_KgraphKeyword_1_0_q = new TokenAlias(false, true, grammarAccess.getParentKNodeAccess().getKgraphKeyword_1_0());
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
			if(match_KContainerRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_KContainerRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q.equals(syntax))
				emit_KContainerRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a.equals(syntax))
				emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a.equals(syntax))
				emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p.equals(syntax))
				emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a.equals(syntax))
				emit_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p.equals(syntax))
				emit_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdgeLayout___PropertiesKeyword_1_0_ColonKeyword_1_1__q.equals(syntax))
				emit_KEdgeLayout___PropertiesKeyword_1_0_ColonKeyword_1_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KIdentifier___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q.equals(syntax))
				emit_KIdentifier___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_____PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a.equals(syntax))
				emit_KNodeLayout_____PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__a.equals(syntax))
				emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__p.equals(syntax))
				emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__a.equals(syntax))
				emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__p.equals(syntax))
				emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__a.equals(syntax))
				emit_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__p.equals(syntax))
				emit_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_RightCurlyBracketKeyword_3_4__q.equals(syntax))
				emit_KPolyline___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_RightCurlyBracketKeyword_3_4__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q.equals(syntax))
				emit_KPolyline___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a.equals(syntax))
				emit_KPolyline_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a.equals(syntax))
				emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p.equals(syntax))
				emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p.equals(syntax))
				emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__a.equals(syntax))
				emit_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__p.equals(syntax))
				emit_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRenderingLibrary___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q.equals(syntax))
				emit_KRenderingLibrary___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_____PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a.equals(syntax))
				emit_KShapeLayout_____PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__a.equals(syntax))
				emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__p.equals(syntax))
				emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__a.equals(syntax))
				emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__p.equals(syntax))
				emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__a.equals(syntax))
				emit_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__p.equals(syntax))
				emit_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_2__q.equals(syntax))
				emit_KSimpleRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q.equals(syntax))
				emit_KSimpleRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a.equals(syntax))
				emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a.equals(syntax))
				emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p.equals(syntax))
				emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a.equals(syntax))
				emit_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p.equals(syntax))
				emit_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ParentKNode_KgraphKeyword_1_0_q.equals(syntax))
				emit_ParentKNode_KgraphKeyword_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     ('{' (('actions' ':') | ('styles' ':'))* '}')?
	 */
	protected void emit_KContainerRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' ']')?
	 */
	protected void emit_KContainerRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('actions' ':') | ('styles' ':'))*
	 */
	protected void emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('actions' ':')* 'styles' ':')*
	 */
	protected void emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('actions' ':')* 'styles' ':')+
	 */
	protected void emit_KContainerRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':')* 'actions' ':')*
	 */
	protected void emit_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':')* 'actions' ':')+
	 */
	protected void emit_KContainerRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('properties' ':')?
	 */
	protected void emit_KEdgeLayout___PropertiesKeyword_1_0_ColonKeyword_1_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' ']')?
	 */
	protected void emit_KIdentifier___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('properties' ':') | ('pos' ':') | ('size' ':'))*
	 */
	protected void emit_KNodeLayout_____PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('pos' ':'))* 'size' ':')*
	 */
	protected void emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('pos' ':'))* 'size' ':')+
	 */
	protected void emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___PropertiesKeyword_0_2_0_ColonKeyword_0_2_1____a_SizeKeyword_0_1_0_ColonKeyword_0_1_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('pos' ':') | ('size' ':'))* 'properties' ':')*
	 */
	protected void emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('pos' ':') | ('size' ':'))* 'properties' ':')+
	 */
	protected void emit_KNodeLayout_______PosKeyword_0_0_0_ColonKeyword_0_0_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PropertiesKeyword_0_2_0_ColonKeyword_0_2_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('size' ':'))* 'pos' ':')*
	 */
	protected void emit_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('size' ':'))* 'pos' ':')+
	 */
	protected void emit_KNodeLayout_______PropertiesKeyword_0_2_0_ColonKeyword_0_2_1___or___SizeKeyword_0_1_0_ColonKeyword_0_1_1____a_PosKeyword_0_0_0_ColonKeyword_0_0_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' (('styles' ':') | ('points' ':') | ('actions' ':'))* '}')?
	 */
	protected void emit_KPolyline___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_RightCurlyBracketKeyword_3_4__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' ']')?
	 */
	protected void emit_KPolyline___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':') | ('points' ':') | ('actions' ':'))*
	 */
	protected void emit_KPolyline_____ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('points' ':') | ('actions' ':'))* 'styles' ':')*
	 */
	protected void emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('points' ':') | ('actions' ':'))* 'styles' ':')+
	 */
	protected void emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('styles' ':') | ('actions' ':'))* 'points' ':')+
	 */
	protected void emit_KPolyline_______ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('styles' ':') | ('points' ':'))* 'actions' ':')*
	 */
	protected void emit_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('styles' ':') | ('points' ':'))* 'actions' ':')+
	 */
	protected void emit_KPolyline_______PointsKeyword_3_1_0_0_ColonKeyword_3_1_0_1___or___StylesKeyword_3_1_1_0_ColonKeyword_3_1_1_1____a_ActionsKeyword_3_1_2_0_ColonKeyword_3_1_2_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' ']')?
	 */
	protected void emit_KRenderingLibrary___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('properties' ':') | ('size' ':') | ('pos' ':'))*
	 */
	protected void emit_KShapeLayout_____PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('pos' ':'))* 'size' ':')*
	 */
	protected void emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('pos' ':'))* 'size' ':')+
	 */
	protected void emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___PropertiesKeyword_1_2_0_ColonKeyword_1_2_1____a_SizeKeyword_1_1_0_ColonKeyword_1_1_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('size' ':') | ('pos' ':'))* 'properties' ':')*
	 */
	protected void emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('size' ':') | ('pos' ':'))* 'properties' ':')+
	 */
	protected void emit_KShapeLayout_______PosKeyword_1_0_0_ColonKeyword_1_0_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PropertiesKeyword_1_2_0_ColonKeyword_1_2_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('size' ':') | ('properties' ':'))* 'pos' ':')*
	 */
	protected void emit_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ((('properties' ':') | ('size' ':'))* 'pos' ':')+
	 */
	protected void emit_KShapeLayout_______PropertiesKeyword_1_2_0_ColonKeyword_1_2_1___or___SizeKeyword_1_1_0_ColonKeyword_1_1_1____a_PosKeyword_1_0_0_ColonKeyword_1_0_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' (('styles' ':') | ('actions' ':'))* '}')?
	 */
	protected void emit_KSimpleRendering___LeftCurlyBracketKeyword_3_0_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a_RightCurlyBracketKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' ']')?
	 */
	protected void emit_KSimpleRendering___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':') | ('actions' ':'))*
	 */
	protected void emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1___or___StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('actions' ':')* 'styles' ':')*
	 */
	protected void emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('actions' ':')* 'styles' ':')+
	 */
	protected void emit_KSimpleRendering_____ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a_StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':')* 'actions' ':')*
	 */
	protected void emit_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('styles' ':')* 'actions' ':')+
	 */
	protected void emit_KSimpleRendering_____StylesKeyword_3_1_0_0_ColonKeyword_3_1_0_1__a_ActionsKeyword_3_1_1_0_ColonKeyword_3_1_1_1__p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'kgraph'?
	 */
	protected void emit_ParentKNode_KgraphKeyword_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
