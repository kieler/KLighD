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
package de.cau.cs.kieler.core.kgraph.text.ui.quickfix;

import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;

/**
 * Customization of the quick fix provider.
 * 
 * @author msp
 */
public class KGraphQuickfixProvider extends DefaultQuickfixProvider {

//    @Fix(MyJavaValidator.INVALID_NAME)
//    public void capitalizeName(final Issue issue, IssueResolutionAcceptor acceptor) {
//        acceptor.accept(issue, "Capitalize name", "Capitalize the name.", "upcase.png",
//                new IModification() {
//            public void apply(IModificationContext context) throws BadLocationException {
//                IXtextDocument xtextDocument = context.getXtextDocument();
//                String firstLetter = xtextDocument.get(issue.getOffset(), 1);
//                xtextDocument.replace(issue.getOffset(), 1, firstLetter.toUpperCase());
//            }
//        });
//    }

}
