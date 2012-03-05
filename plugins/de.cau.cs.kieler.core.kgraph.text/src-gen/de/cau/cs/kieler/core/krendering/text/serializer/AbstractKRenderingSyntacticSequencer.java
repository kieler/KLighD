package de.cau.cs.kieler.core.krendering.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.core.krendering.text.services.KRenderingGrammarAccess;
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
public class AbstractKRenderingSyntacticSequencer extends AbstractSyntacticSequencer {

	protected KRenderingGrammarAccess grammarAccess;
	protected AbstractElementAlias match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q;
	protected AbstractElementAlias match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q;
	protected AbstractElementAlias match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KImage___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q;
	protected AbstractElementAlias match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	protected AbstractElementAlias match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q;
	protected AbstractElementAlias match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (KRenderingGrammarAccess) access;
		match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_5()));
		match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_2_3()));
		match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_2_5()));
		match_KImage___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_5_5()));
		match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_2_5()));
		match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_2_5()));
		match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_2_5()));
		match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_3()));
		match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_5_5()));
		match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_5()));
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
			if(match_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q.equals(syntax))
				emit_KArc___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q.equals(syntax))
				emit_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KEllipse___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KImage___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q.equals(syntax))
				emit_KImage___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KPolygon___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KRectangle___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q.equals(syntax))
				emit_KRoundedRectangle___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q.equals(syntax))
				emit_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
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
	 *     ('{' '}')?
	 */
	protected void emit_KChildArea___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
	 *     ('{' '}')?
	 */
	protected void emit_KImage___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
	 *     ('{' '}')?
	 */
	protected void emit_KPolyline_Impl___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
	 *     ('{' '}')?
	 */
	protected void emit_KRenderingRef___LeftCurlyBracketKeyword_3_0_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
	 *     ('{' '}')?
	 */
	protected void emit_KSpline___LeftCurlyBracketKeyword_2_0_RightCurlyBracketKeyword_2_5__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
