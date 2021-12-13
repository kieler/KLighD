/*
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
package de.cau.cs.kieler.kgraph.text.formatting

import com.google.inject.Inject
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import de.cau.cs.kieler.kgraph.text.services.GRandomGrammarAccess

class GRandomFormatter extends AbstractDeclarativeFormatter {

    @Inject extension GRandomGrammarAccess

    override protected configureFormatting(FormattingConfig c) {

        c.setAutoLinewrap(120);

        // line wrapping for comments
        c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
        c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
        c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)

         // general formatting options relative to some delimiters
        for (pair : findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setNoLinewrap.before(pair.first)
            c.setLinewrap.after(pair.first);
            c.setLinewrap.after(pair.second);
        }

        for (Keyword equals : findKeywords("=")) {
            c.setNoLinewrap().before(equals);
        }

        c.setLinewrap.after(doubleQuantityRule)
        c.setLinewrap.after(labelsRule)
        c.setLinewrap.after(integerRule)
        c.setLinewrap.after(STRINGRule)
        c.setLinewrap.after(formatsRule)
        c.setLinewrap.after(floatRule)
        c.setLinewrap.after(sideRule)
        c.setLinewrap.after(constraintTypeRule)

        c.setLinewrap(2).after(configurationRule) // amounts to two
    }

}