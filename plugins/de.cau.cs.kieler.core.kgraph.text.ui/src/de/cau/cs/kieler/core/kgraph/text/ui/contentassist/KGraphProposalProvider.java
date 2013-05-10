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
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;

import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.ui.contentassist.AbstractKGraphProposalProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Custom proposal provider contributing KIELER Layout configuration proposals.
 * 
 * @author sgu
 * @author chsch
 * @author msp
 */
public class KGraphProposalProvider extends AbstractKGraphProposalProvider {

    /**
     * Need this delegate in order to call methods of that class that are also generated
     * into {@link AbstractAnnotationsProposalProvider} (due to some terminal re-definitions).
     */
    @Inject
    private TerminalsProposalProvider delegate;    
    
    // ---------------------------------------------------------
    //  Terminal-specific annotation proposals
    // ---------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void complete_QualifiedID(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        delegate.complete_ID(model, ruleCall, context, acceptor);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void complete_Float(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        createFloatProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 1f);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void complete_BOOLEAN(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        acceptor.accept(createCompletionProposal("false", "false", getImage(ruleCall), context));
        acceptor.accept(createCompletionProposal("true", "true", getImage(ruleCall), context));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void complete_NATURAL(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        delegate.complete_INT(model, ruleCall, context, acceptor);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void complete_STRING(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        delegate.complete_STRING(model, ruleCall, context, acceptor);
    }    
    
    /**
     * This method has been copied from
     * {@link org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider}.
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
     * This method has been copied and adapted from
     * {@link org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider}.
     */
    private void createFloatProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor, final RuleCall ruleCall, final String feature, float i) {
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
    
    
    // ---------------------------------------------------------
    //  Layout option proposals provided by KIML
    // ---------------------------------------------------------
    
    /**
     * Computes the property key proposals based on available layout options.
     * 
     * @param context Xtext API
     * @param acceptor Xtext API
     * 
     * @author sgu, chsch
     */
    private void keyProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor) {
        /* declare the plain proposal and get the option list */
        String proposal;
        StyledString.Styler theStyle;
        StyledString displayString;
        ICompletionProposal completeProposal;

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        /* create and register the completion proposal for every element in the list */
        for (LayoutOptionData<?> optionData : layoutServices.getOptionData()) {
            theStyle = (optionData.isAdvanced()) ? StyledString.COUNTER_STYLER : null;
            displayString = new StyledString(optionData.toString(), theStyle);
            displayString.append(" - " + optionData.getType().toString(),
                    StyledString.QUALIFIER_STYLER);
            proposal = optionData.getId();
            completeProposal = createCompletionProposal(proposal, displayString, null,
                    getPriorityHelper().getDefaultPriority(),
                    "de.cau.cs.kieler." + context.getPrefix(), context);
            acceptor.accept((completeProposal != null) ? completeProposal
                    : createCompletionProposal(proposal, displayString, null, context));
        }
    }

    /**
     * Computes the annotation value proposals based on a foregoing layout parameter.
     * 
     * @param context Xtext API
     * @param acceptor Xtext API
     * 
     * @author sgu, chsch
     */
    private void valueProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor) {
        /* check if the prefix is a KIELER annotation */
        if (context.getCurrentModel() instanceof PersistentEntry) {

            String annotationName = ((PersistentEntry) context.getCurrentModel()).getKey();
            if (!Strings.isEmpty(annotationName)) {

                // get the option list
                LayoutDataService layoutServices = LayoutDataService.getInstance();

                // find the specific option an display all possible values
                LayoutOptionData<?> optionData = layoutServices.getOptionData(annotationName);
                Type theType = (optionData != null) ? optionData.getType() : Type.UNDEFINED;
                String proposal = null;
                
                switch (theType) {
                // show the available choices for boolean and enumeration/
                case BOOLEAN:
                case REMOTE_ENUM:
                case REMOTE_ENUMSET:
                case ENUM:
                case ENUMSET:
                    for (int j = 0; j < optionData.getChoices().length; j++) {
                        proposal = optionData.getChoices()[j];
                        acceptor.accept(createCompletionProposal(proposal, context));
                    }
                    break;

                // for string, float, integer and object show the type of the value and give a
                //  corresponding default value
                 
                case STRING:
                    if (annotationName.equals(LayoutOptions.ALGORITHM.getId())) {
                        String displayString = null;
                        for (LayoutAlgorithmData data : layoutServices.getAlgorithmData()) {
                            proposal = '"' + data.getId() + '"';
                            displayString = data.getName();
                            acceptor.accept(createCompletionProposal(proposal, displayString, null, context));
                        }
                        break;
                    }
                case FLOAT:
                case INT:
                case OBJECT:

                    // chose the corresponding default value
                    switch (theType) {
                    case STRING:
                        proposal = "\"\"";
                        break;
                    case FLOAT:
                        proposal = "0.0";
                        break;
                    case INT:
                        proposal = "0";
                        break;
                    case OBJECT:
                        try {
                            proposal = "\""
                                    + optionData.getOptionClass().newInstance().toString()
                                    + "\"";
                        } catch (InstantiationException e) {
                            proposal = "\"\"";
                        } catch (IllegalAccessException e) {
                            proposal = "\"\"";
                        }
                        break;

                    default:
                        break;
                    }
                    acceptor.accept(createCompletionProposal(proposal, optionData.getType()
                            .toString(), null, context));
                    break;

                default:
                    break;
                }
            }
        }
    }
    
    
    // CHECKSTYLEOFF MethodName

    /**
     * {@inheritDoc}
     */
    @Override
    public void completePersistentEntry_Key(final EObject model, final Assignment assignment,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {

        /* call implementation of superclass */
        super.completePersistentEntry_Key(model, assignment, context, acceptor);

        /* call modified completion */
        keyProposal(context, acceptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completePersistentEntry_Value(final EObject model, final Assignment assignment,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {

        /* call implementation of superclass */
        super.completePersistentEntry_Value(model, assignment, context, acceptor);

        /* call modified completion */
        valueProposal(context, acceptor);
    }

}
