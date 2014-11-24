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
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;

import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.KGraphResource;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutOptionLabelProvider;

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

    @Inject
    private KGraphGrammarAccess grammarAccess;

    // CHECKSTYLEOFF MethodName

    // ---------------------------------------------------------
    //  Terminal-specific annotation proposals
    // ---------------------------------------------------------

    @Override
    public void complete_QualifiedID(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        delegate.complete_ID(model, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0(),
                context, acceptor);
    }

    @Override
    public void complete_Float(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createFloatProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 1f);
    }

    @Override
    public void complete_BOOLEAN(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        acceptor.accept(createCompletionProposal("false", "false", getImage(ruleCall), context));
        acceptor.accept(createCompletionProposal("true", "true", getImage(ruleCall), context));
    }

    // In the following methods providing a proposal for the particular special number values
    //  only '0' or '1' is mentioned, as #createIntProposal calls the terminal converter to
    //  get the required string representation.

    @Override
    public void complete_RED(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_GREEN(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_BLUE(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_ALPHA(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_FSIZE(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_DEGREES(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createFloatProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_PERCENT(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createFloatProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 0);
    }

    @Override
    public void complete_NATURAL(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        createIntProposal(context, acceptor, ruleCall, getAssignedFeature(ruleCall), 1);
    }

    @Override
    public void complete_STRING(final EObject model, final RuleCall ruleCall,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
        delegate.complete_STRING(model, ruleCall, context, acceptor);
    }

    /**
     * This method has been copied from
     * {@link org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider}.
     */
    private String getAssignedFeature(final RuleCall call) {
        final Assignment ass = GrammarUtil.containingAssignment(call);
        if (ass != null) {
            String result = ass.getFeature();
            if (result.equals(result.toLowerCase())) {
                result = Strings.toFirstUpper(result);
            }
            return result;
        }
        return null;
    }

    /**
     * This method has shamelessly been copied from
     * {@link org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider} (chsch).
     *
     * @author Xtext developers
     */
    private void createIntProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor, final RuleCall ruleCall,
            final String feature, final int i) {
        final String proposalText = getValueConverter().toString(i, ruleCall.getRule().getName());
        String displayText = proposalText + " - " + ruleCall.getRule().getName();
        if (feature != null) {
            displayText = proposalText + " - " + feature;
        }
        final ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null,
                context);
        if (proposal instanceof ConfigurableCompletionProposal) {
            final ConfigurableCompletionProposal configurable =
                    (ConfigurableCompletionProposal) proposal;
            configurable.setSelectionStart(configurable.getReplacementOffset());
            configurable.setSelectionLength(proposalText.length());
            configurable.setAutoInsertable(false);
            configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
        }
        acceptor.accept(proposal);
    }

    /**
     * This method has been copied and adapted from
     * {@link org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider}.
     *
     * @author Xtext developers
     */
    private void createFloatProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor, final RuleCall ruleCall,
            final String feature, final float i) {
        final String proposalText = getValueConverter().toString(i, ruleCall.getRule().getName());
        String displayText = proposalText + " - " + ruleCall.getRule().getName();
        if (feature != null) {
            displayText = proposalText + " - " + feature;
        }
        final ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null,
                context);
        if (proposal instanceof ConfigurableCompletionProposal) {
            final ConfigurableCompletionProposal configurable =
                    (ConfigurableCompletionProposal) proposal;
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

    // CHECKSTYLEOFF MethodName

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeProperty_Key(final EObject model, final Assignment assignment,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {

        /* call implementation of superclass */
        super.completeProperty_Key(model, assignment, context, acceptor);

        /* call modified completion */
        keyProposal(context, acceptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeProperty_Value(final EObject model, final Assignment assignment,
            final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {

        /* call implementation of superclass */
        super.completeProperty_Value(model, assignment, context, acceptor);

        /* call modified completion */
        valueProposal(context, acceptor);
    }

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
        final LayoutMetaDataService layoutServices = LayoutMetaDataService.getInstance();

        // create and register the completion proposal for every element in the list
        for (final LayoutOptionData optionData : layoutServices.getOptionData()) {
            final StyledString displayString =
                    new StyledString(optionData.toString(),
                            (optionData.isAdvanced()) ? StyledString.COUNTER_STYLER : null);
            displayString.append(" (" + optionData.getId() + ")", StyledString.QUALIFIER_STYLER);

            final String proposal = getValueConverter().toString(optionData.getId(),
                                grammarAccess.getQualifiedIDRule().getName());

            final LayoutOptionLabelProvider labelProvider = new LayoutOptionLabelProvider(optionData);
            final Image image = labelProvider.getImage(optionData.getDefault());

            handleKeyProposal(context, acceptor, optionData.getId(), proposal, displayString, image);
        }

        // additional properties as specified for the kgraph text format
        // note that we do not support suffix only here
        for (final IProperty<?> p : KGraphResource.ADDITIONAL_PROPERTIES) {
            final String proposal =
                    getValueConverter().toString(p.getId(),
                            grammarAccess.getQualifiedIDRule().getName());
            final StyledString displayString = new StyledString(proposal);
            final Image image = KimlUiPlugin.getDefault().getImages().getPropText();

            handleKeyProposal(context, acceptor, p.getId(), proposal, displayString, image);
        }

    }

    private void handleKeyProposal(final ContentAssistContext context,
            final ICompletionProposalAcceptor acceptor, final String id, final String proposal,
            final StyledString displayString, final Image image) {

        if (isValidProposal(proposal, context.getPrefix(), context)) {
            // accept the proposal with unmodified prefix
            acceptor.accept(doCreateProposal(proposal, displayString, image, getPriorityHelper()
                    .getDefaultPriority(), context));
        } else {
            final int lastDotIndex = id.lastIndexOf('.');
            if (lastDotIndex >= 0) {
                // accept the proposal with enhanced prefix
                final StringBuilder prefix =
                        new StringBuilder(id.substring(0, lastDotIndex + 1));
                prefix.append(context.getPrefix());
                // add escape characters as required
                for (int i = 0; i < proposal.length(); i++) {
                    if (i >= prefix.length()) {
                        break;
                    }
                    if (proposal.charAt(i) != prefix.charAt(i)) {
                        if (proposal.charAt(i) == '^') {
                            prefix.insert(i, '^');
                        } else {
                            break;
                        }
                    }
                }
                if (isValidProposal(proposal, prefix.toString(), context)) {
                    // accept the proposal with unmodified prefix
                    acceptor.accept(doCreateProposal(proposal, displayString, image,
                            getPriorityHelper().getDefaultPriority(), context));
                }
            }
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
        // check if the prefix is a KIELER annotation
        if (context.getCurrentModel() instanceof PersistentEntry) {

            final String annotationName = ((PersistentEntry) context.getCurrentModel()).getKey();
            if (!Strings.isEmpty(annotationName)) {

                // get the option list
                final LayoutMetaDataService layoutServices = LayoutMetaDataService.getInstance();

                // find the specific option an display all possible values
                LayoutOptionData optionData = layoutServices.getOptionData(annotationName);

                // if option data is null, try to add the kieler prefix
                if (optionData == null) {
                    optionData = layoutServices.getOptionData("de.cau.cs.kieler." + annotationName);
                }

                final Type theType = (optionData != null) ? optionData.getType() : Type.UNDEFINED;
                String proposal = null;

                switch (theType) {
                // show the available choices for boolean and enumeration/
                case BOOLEAN:
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
                        for (final LayoutAlgorithmData data : layoutServices.getAlgorithmData()) {
                            proposal = '"' + data.getId() + '"';
                            displayString = data.getName();
                            acceptor.accept(createCompletionProposal(proposal, displayString, null,
                                    context));
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
                        } catch (final InstantiationException e) {
                            proposal = "\"\"";
                        } catch (final IllegalAccessException e) {
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

}
