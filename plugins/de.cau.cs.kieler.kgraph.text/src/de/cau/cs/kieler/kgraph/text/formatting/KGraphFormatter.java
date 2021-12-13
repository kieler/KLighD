/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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

import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess;

/**
 * Formatter for the KGraph language.
 * 
 * @author msp
 */
public class KGraphFormatter extends AbstractDeclarativeFormatter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFormatting(final FormattingConfig c) {
        KGraphGrammarAccess f = (KGraphGrammarAccess) getGrammarAccess();
        
        // general formatting options relative to some delimiters
        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setLinewrap(1).after(pair.getFirst());
            c.setLinewrap(1).before(pair.getSecond());
            c.setLinewrap(1).after(pair.getSecond());
        }
        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("[", "]")) {
            c.setNoSpace().after(pair.getFirst());
            c.setNoSpace().before(pair.getSecond());
        }
        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("(", ")")) {
            c.setNoSpace().after(pair.getFirst());
            c.setNoSpace().before(pair.getSecond());
        }
        for (Keyword comma : f.findKeywords(",")) {
            c.setNoLinewrap().before(comma);
            c.setNoSpace().before(comma);
        }
        for (Keyword colon : f.findKeywords(":")) {
            c.setNoLinewrap().before(colon);
            c.setNoSpace().before(colon);
        }
        for (Keyword star : f.findKeywords("*")) {
            c.setNoLinewrap().after(star);
            c.setNoSpace().after(star);
        }
        for (Keyword equals : f.findKeywords("=")) {
            c.setNoLinewrap().after(equals);
            c.setNoLinewrap().before(equals);
            c.setSpace(" ").before(equals);
            c.setSpace(" ").after(equals);
        }
        
        // line wrapping for comments
        c.setLinewrap(0, 1, 2).before(f.getSL_COMMENTRule());
        c.setLinewrap(0, 1, 2).before(f.getML_COMMENTRule());
        c.setLinewrap(0, 1, 1).after(f.getML_COMMENTRule());
        
        // line wrapping for main objects
        c.setLinewrap().before(f.getKNodeRule());
        c.setLinewrap().before(f.getKEdgeRule());
        c.setLinewrap().before(f.getKPortRule());
        c.setLinewrap().before(f.getKLabelRule());
        c.setLinewrap().before(f.getKRenderingRule());
        c.setLinewrap().before(f.getKStyleHolderRule());
        
        // line wrapping for key-value sections
        c.setLinewrap().before(f.getParentKNodeAccess().getPosKeyword_2_0_0());
        c.setLinewrap().before(f.getParentKNodeAccess().getSizeKeyword_2_1_0());
        c.setLinewrap().before(f.getParentKNodeAccess().getPropertiesKeyword_2_2_0());
        c.setLinewrap().before(f.getParentKNodeAccess().getPersistentEntriesAssignment_2_2_2());
        c.setLinewrap().before(f.getParentKNodeAccess().getInsetsKeyword_3_0_0());
        c.setLinewrap().before(f.getKNodeAccess().getPosKeyword_3_1_0_0());
        c.setLinewrap().before(f.getKNodeAccess().getSizeKeyword_3_1_1_0());
        c.setLinewrap().before(f.getKNodeAccess().getPropertiesKeyword_3_1_2_0());
        c.setLinewrap().before(f.getKNodeAccess().getPersistentEntriesAssignment_3_1_2_2());
        c.setLinewrap().before(f.getKNodeAccess().getInsetsKeyword_3_2_0_0());
        c.setLinewrap().before(f.getKPortAccess().getPosKeyword_3_1_0_0());
        c.setLinewrap().before(f.getKPortAccess().getSizeKeyword_3_1_1_0());
        c.setLinewrap().before(f.getKPortAccess().getPropertiesKeyword_3_1_2_0());
        c.setLinewrap().before(f.getKPortAccess().getPersistentEntriesAssignment_3_1_2_2());
        c.setLinewrap().before(f.getKLabelAccess().getPosKeyword_4_1_0_0());
        c.setLinewrap().before(f.getKLabelAccess().getSizeKeyword_4_1_1_0());
        c.setLinewrap().before(f.getKLabelAccess().getPropertiesKeyword_4_1_2_0());
        c.setLinewrap().before(f.getKLabelAccess().getPersistentEntriesAssignment_4_1_2_2());
        c.setLinewrap().before(f.getKEdgeAccess().getPointsKeyword_8_1_0_0());
        c.setLinewrap().before(f.getKEdgeAccess().getPropertiesKeyword_8_2_0());
        c.setLinewrap().before(f.getKEdgeAccess().getPersistentEntriesAssignment_8_2_2());
        
        c.setLinewrap().before(f.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0());
        c.setLinewrap().before(f.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0());
        c.setLinewrap().before(f.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0());
        c.setLinewrap().before(f.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0());
        c.setLinewrap().before(f.getKPolylineAccess().getPointsKeyword_3_1_0_0());
        c.setLinewrap().before(f.getKPolylineAccess().getStylesKeyword_3_1_1_0());
        c.setLinewrap().before(f.getKPolylineAccess().getActionsKeyword_3_1_2_0());
        c.setLinewrap().before(f.getKPlacementRule());
        c.setLinewrap().before(f.getKPlacementDataRule());
    }

}
