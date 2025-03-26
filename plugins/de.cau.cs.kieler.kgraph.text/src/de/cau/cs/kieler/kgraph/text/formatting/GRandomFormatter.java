/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.lib.Extension;

import com.google.inject.Inject;

import de.cau.cs.kieler.kgraph.text.services.GRandomGrammarAccess;

public class GRandomFormatter extends AbstractDeclarativeFormatter {

    @Inject
    @Extension
    private GRandomGrammarAccess gRandomGrammarAccess;

    protected void configureFormatting(final FormattingConfig c) {

        c.setAutoLinewrap(120);

        // line wrapping for comments
        c.setLinewrap(0, 1, 2).before(gRandomGrammarAccess.getSL_COMMENTRule());
        c.setLinewrap(0, 1, 2).before(gRandomGrammarAccess.getML_COMMENTRule());
        c.setLinewrap(0, 1, 1).after(gRandomGrammarAccess.getML_COMMENTRule());

        // general formatting options relative to some delimiters
        for (final Pair<Keyword, Keyword> pair : gRandomGrammarAccess.findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setNoLinewrap().before(pair.getFirst());
            c.setLinewrap().after(pair.getFirst());
            c.setLinewrap().after(pair.getSecond());
        }

        for (final Keyword equals : gRandomGrammarAccess.findKeywords("=")) {
            c.setNoLinewrap().before(equals);
        }

        c.setLinewrap().after(gRandomGrammarAccess.getDoubleQuantityRule());
        c.setLinewrap().after(gRandomGrammarAccess.getLabelsRule());
        c.setLinewrap().after(gRandomGrammarAccess.getIntegerRule());
        c.setLinewrap().after(gRandomGrammarAccess.getSTRINGRule());
        c.setLinewrap().after(gRandomGrammarAccess.getFormatsRule());
        c.setLinewrap().after(gRandomGrammarAccess.getFloatRule());
        c.setLinewrap().after(gRandomGrammarAccess.getSideRule());
        c.setLinewrap().after(gRandomGrammarAccess.getConstraintTypeRule());

        c.setLinewrap(2).after(gRandomGrammarAccess.getConfigurationRule()); // amounts to two
    }

}
