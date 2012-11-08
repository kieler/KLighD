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
 * 
 * @author chsch
 */
public class KGraphQuickfixProvider extends DefaultQuickfixProvider {

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
    public void capitalizeName(final Issue issue, final IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Train KNode", "Train KNode", null,
                new ISemanticModification() {

                    public void apply(final EObject element, final IModificationContext context)
                            throws Exception {
                        try {
                            Class<?> c = Class
                                    .forName("de.cau.cs.kieler.klighd.test.SizeEstimationTrainer");
                            c.getMethod("train", KNode.class).invoke(null, (KNode) element);
                        } catch (ClassNotFoundException e) {
                            // in case the size estimation trainer is not available don't try
                            // anything
                        }
                    }
                });
    }
}
