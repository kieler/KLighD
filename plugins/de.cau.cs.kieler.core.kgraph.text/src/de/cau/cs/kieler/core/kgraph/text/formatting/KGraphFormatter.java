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
package de.cau.cs.kieler.core.kgraph.text.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Pair;

import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;

/**
 * This class contains KGraph specific formatting descriptions.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
 */
public class KGraphFormatter extends AbstractDeclarativeFormatter {

    @Override
    protected void configureFormatting(final FormattingConfig c) {
        KGraphGrammarAccess f = (KGraphGrammarAccess) getGrammarAccess();
        
        // configure formatting of curly braces 
        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setLinewrap(1).after(pair.getFirst());
            c.setLinewrap(1).before(pair.getSecond());
            c.setLinewrap(1).after(pair.getSecond());
        }
        
        // configure formatting of comma and colon (no space before, ...)
        for (Keyword comma : f.findKeywords(",", ":")) {
            c.setNoLinewrap().before(comma);
            c.setNoSpace().before(comma);
            c.setLinewrap().after(comma);
        }
        
        // configure insertion of a line wrap in front of the following keywords
        for (Keyword word : f.findKeywords("mapProperties", "children", "styles", "bendPoints",
                "placementData", "detailedPlacementData", "sourcePoint", "targetPoint",
                "sourcePort", "targetPort", "topLeft", "bottomRight", "lineStyle", "lineWidth",
                "backgroundColor", "foregroundColor", "backgroundVisibility",
                "foregroundVisibility", "font", "fontSize", "fontColor", "bold", "italic",
                "horizontalAlignment", "verticalAlignment", "left", "right", "location", "xOffset",
                "width", "insets", "KNode", "KInsets", "KPoint")) {
            c.setLinewrap().before(word);
        }
        
        // configure insertion of a line wrap behind the following keywords
        for (Keyword word : f.findKeywords("points")) {
            c.setLinewrap().after(word);
        }
        
        // suppress the line wrap after 'topLeft' and 'bottomRight' of directPlacementData 
        c.setNoLinewrap().after(f.getKAreaPlacementDataAccess().getTopLeftKeyword_2());
        c.setNoLinewrap().after(f.getKAreaPlacementDataAccess().getBottomRightKeyword_5());

        // some custom formatting of coordinate numbers 
        c.setNoLinewrap().after(
                f.getKRenderingGrammarAccess().getKLayoutDataGrammarAccess().getKPointAccess()
                        .getXEFloatParserRuleCall_2_1_0());
        
        // configures the line wrap after a valid map property tuple
        c.setLinewrap().after(
                f.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0());
        
        // configures the line wrap after 'mapProperties', if no colon follows
        c.setLinewrap().between(f.getKEdgeLayoutAccess().getMapPropertiesKeyword_6_0(),
                f.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0());
        c.setLinewrap().between(f.getKShapeLayoutAccess().getMapPropertiesKeyword_8_0(),
                f.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0());
        c.setLinewrap().between(f.getKTextAccess().getMapPropertiesKeyword_3_3_0(),
                f.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0());
        
        // standard rules targeting comments
        c.setLinewrap(0, 1, 2).before(f.getSL_COMMENTRule());
        c.setLinewrap(0, 1, 2).before(f.getML_COMMENTRule());
        c.setLinewrap(0, 1, 1).after(f.getML_COMMENTRule());
    }
}
