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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Pair;

import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;

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
        c.setLinewrap().before(f.getKNodeLayoutAccess().getPosKeyword_0_0_0());
        c.setLinewrap().before(f.getKNodeLayoutAccess().getSizeKeyword_0_1_0());
        c.setLinewrap().before(f.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0());
        c.setLinewrap().before(f.getKNodeLayoutAccess().getInsetsKeyword_1_0_0());
        c.setLinewrap().before(f.getKShapeLayoutAccess().getPosKeyword_1_0_0());
        c.setLinewrap().before(f.getKShapeLayoutAccess().getSizeKeyword_1_1_0());
        c.setLinewrap().before(f.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0());
        c.setLinewrap().before(f.getKEdgeLayoutAccess().getPointsKeyword_0_0_0());
        c.setLinewrap().before(f.getKEdgeLayoutAccess().getPropertiesKeyword_1_0());
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
