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
package de.cau.cs.kieler.core.kgraph.text.ui.quickfix;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.validation.KGraphJavaValidator;

/**
 * The KGraph/KRendering-specific
 * {@link org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider IssueResolutionProvider}.<br>
 * <br>
 * Currently, it provides 'Train KNode' and 'Add ignore tag' quick fixes. 
 * 
 * @author chsch
 */
public class KGraphQuickfixProvider extends DefaultQuickfixProvider {

    private Class<?> c = null;
    
    { // static part executed after the class has been loaded
        try {
            this.c = Class.forName("de.cau.cs.kieler.klighd.test.SizeEstimationTrainer");
        } catch (ClassNotFoundException e) {
            // in case the size estimation trainer is not available don't try
            // anything
        }
    }
    
    /**
     * Provide semantic modification that trains a KNode for being used as test case of the size
     * estimation test in de.cau.cs.kieler.klighd.test.
     * 
     * @param issue
     *            the issue to be fixed
     * @param acceptor
     *            the acceptor taking the fix
     */
    @Fix(KGraphJavaValidator.TRAIN_KNODE_INFO)
    public void trainKNode(final Issue issue, final IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Train KNode", "Train KNode", null,
                new ISemanticModification() {

                    public void apply(final EObject element, final IModificationContext context)
                            throws Exception {
                        if (c != null) {
                            c.getMethod("train", KNode.class).invoke(null, (KNode) element);
                        }
                    }
                });
    }
    
    @Fix(KGraphJavaValidator.IGNORE_KNODE_INFO)
    public void addIgnoreTag(final Issue issue, final IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Add ignore tag", "Add ignore tag", null,
                new ISemanticModification() {

                    public void apply(EObject element, IModificationContext context)
                            throws Exception {
                        if (c != null) {
                            c.getMethod("ignore", KNode.class).invoke(null, (KNode) element);
                        }
                    }
                });
    }
}
