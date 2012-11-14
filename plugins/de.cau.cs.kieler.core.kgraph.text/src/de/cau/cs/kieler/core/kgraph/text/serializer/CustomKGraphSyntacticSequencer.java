/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.serializer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;

import com.google.common.collect.Iterables;

/**
 * A specialized {@link org.eclipse.xtext.serializer.sequencer.ISyntacticSequencer} forcing optional
 * keywords to be serialized.
 * 
 * @author chsch
 */
@SuppressWarnings("restriction")
public class CustomKGraphSyntacticSequencer extends KGraphSyntacticSequencer {
    
    /**
     * This method overrides
     * {@link KGraphSyntacticSequencer#emitUnassignedTokens(EObject, ISynTransition, INode, INode)}
     * for the sake of instructing the serializer to always dump out optional colon keywords. Other
     * optional keywords are omitted. This realization avoids the implementation of a huge amount
     * of methods each of them dedicated to a certain occurrence of the colon in the grammar(s).<br> 
     * <br>
     * Note: Method is partially copied from the super implementation.
     * 
     * @param semanticObject
     *            serializer data
     * @param transition
     *            serializer data
     * @param fromNode
     *            serializer data
     * @param toNode
     *            serializer data
     */
    protected void emitUnassignedTokens(final EObject semanticObject,
            final ISynTransition transition, final INode fromNode, final INode toNode) {
        // the copied part
        if (transition.getAmbiguousSyntaxes().isEmpty()) return;
        List<INode> transitionNodes = collectNodes(fromNode, toNode);
        for (GrammarAlias.TokenAlias syntax : Iterables.filter(transition.getAmbiguousSyntaxes(),
                GrammarAlias.TokenAlias.class)) {
            List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
            // the custom part:
            if (XtextPackage.eINSTANCE.getKeyword().isInstance(syntax.getToken())) {
                // if we're examining a keyword ...
                Keyword k = (Keyword) syntax.getToken();
                if (k.getValue().equals(":")) {
                    // ... and the keyword is a colon dump out the keyword!
                    acceptUnassignedKeyword(k, k.getValue(), null);
                } else {
                    // do the default stuff for other key words 
                    acceptNodes(transition, syntaxNodes);
                }
            } else {
                // do the default stuff otherwise 
                acceptNodes(transition, syntaxNodes);
            }
        }
    }    
}
