/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.ui.contentassist;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;

import de.cau.cs.kieler.core.kgraph.text.ui.contentassist.AbstractKGraphProposalProvider;

/**
 * Customized content assist proposal provider contributing proposals for terminal values.
 * 
 * @author chsch
 */
public class KGraphProposalProvider extends AbstractKGraphProposalProvider {

    @Inject
    private TerminalsProposalProvider terminals;
    
    public void complete_ID(EObject model, RuleCall ruleCall, final ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        terminals.complete_ID(model, ruleCall, context, acceptor);        
    }
    
    public void complete_QualifiedID(EObject model, RuleCall ruleCall, final ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        terminals.complete_ID(model, ruleCall, context, acceptor);        
    }
    
    public void complete_STRING(EObject model, RuleCall ruleCall, final ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        terminals.complete_STRING(model, ruleCall, context, acceptor);        
    }
    
    public void complete_BOOLEAN(EObject model, RuleCall ruleCall, final ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        String proposalText = "true";
        String displayText = proposalText + " - " + ruleCall.getRule().getName();
        ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null, context);
        if (proposal instanceof ConfigurableCompletionProposal) {
                ConfigurableCompletionProposal configurable = (ConfigurableCompletionProposal) proposal;
                configurable.setSelectionStart(configurable.getReplacementOffset());
                configurable.setSelectionLength(proposalText.length());
                configurable.setAutoInsertable(false);
                configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
        }
        acceptor.accept(proposal);
    }
    
    // In the following methods providing a proposal for the particular special number value
    //  only '0' is zero is mentioned, as #createIntProposal calls the terminal converter to
    //  get the required string representation.
    
    public void complete_RED(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_GREEN(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    public void complete_BLUE(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_ALPHA(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_F_SIZE(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_DEGREES(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_PERCENT(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }
    
    public void complete_NATURAL(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 1);
    }
    
    public void complete_Float(EObject model, RuleCall ruleCall, ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 1);
    }
    
    
    /**
     * Shamelessly copied from {@link TerminalsProposalProvider} (chsch).
     * 
     * @author Xtext devs
     */
    private String getAssignedFeature(RuleCall call) {
        Assignment ass = GrammarUtil.containingAssignment(call);
        if (ass != null) {
                String result = ass.getFeature();
                if (result.equals(result.toLowerCase()))
                        result = Strings.toFirstUpper(result);
                return result;
        }
        return null;
    }

    /**
     * Shamelessly copied from {@link TerminalsProposalProvider} (chsch).
     * 
     * @author Xtext devs
     */
    private void createIntProposal(ContentAssistContext context,
            ICompletionProposalAcceptor acceptor, RuleCall ruleCall, String feature, int i) {
        String proposalText = getValueConverter().toString(i, ruleCall.getRule().getName());
        String displayText = proposalText + " - " + ruleCall.getRule().getName();
        if (feature != null)
            displayText = proposalText + " - " + feature;
        ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null,
                context);
        if (proposal instanceof ConfigurableCompletionProposal) {
            ConfigurableCompletionProposal configurable = (ConfigurableCompletionProposal) proposal;
            configurable.setSelectionStart(configurable.getReplacementOffset());
            configurable.setSelectionLength(proposalText.length());
            configurable.setAutoInsertable(false);
            configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
        }
        acceptor.accept(proposal);
    }

    
}
