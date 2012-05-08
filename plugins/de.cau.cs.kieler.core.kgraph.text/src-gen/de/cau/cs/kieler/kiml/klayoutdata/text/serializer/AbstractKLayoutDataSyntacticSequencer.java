package de.cau.cs.kieler.kiml.klayoutdata.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.kiml.klayoutdata.text.services.KLayoutDataGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("restriction")
public class AbstractKLayoutDataSyntacticSequencer extends AbstractSyntacticSequencer {

	protected KLayoutDataGrammarAccess grammarAccess;
	protected AbstractElementAlias match_KEdgeLayout_CommaKeyword_6_3_0_q;
	protected AbstractElementAlias match_KEdgeLayout_CommaKeyword_7_3_0_q;
	protected AbstractElementAlias match_KShapeLayout_CommaKeyword_8_3_0_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (KLayoutDataGrammarAccess) access;
		match_KEdgeLayout_CommaKeyword_6_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_6_3_0());
		match_KEdgeLayout_CommaKeyword_7_3_0_q = new TokenAlias(false, true, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_7_3_0());
		match_KShapeLayout_CommaKeyword_8_3_0_q = new TokenAlias(false, true, grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0());
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
			if(match_KEdgeLayout_CommaKeyword_6_3_0_q.equals(syntax))
				emit_KEdgeLayout_CommaKeyword_6_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KEdgeLayout_CommaKeyword_7_3_0_q.equals(syntax))
				emit_KEdgeLayout_CommaKeyword_7_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_KShapeLayout_CommaKeyword_8_3_0_q.equals(syntax))
				emit_KShapeLayout_CommaKeyword_8_3_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
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
	 *     ','?
	 */
	protected void emit_KShapeLayout_CommaKeyword_8_3_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
