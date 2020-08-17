/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015, 2017 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels.management;

import java.awt.Font;

import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtilSWT;

/**
 * Label manager which hard wraps the text so it fits a certain width. Hard wrapping means that a
 * new line is inserted after the last character that still fits into the target width, regardless
 * of whether the line break interrupts a word or not.
 * 
 * <p>
 * It does not make sense to put this label manager into {@link Mode#ALWAYS_ON}. If a label does not
 * exceed a target width, there's no sense in inserting wraps anywhere.
 * </p>
 * 
 * @author ybl
 * @author cds
 */
public class HardWrappingLabelManager extends AbstractKlighdLabelManager {

    @Override
    public Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        double width = 0;
        
        if (Klighd.IS_LANGUAGE_SERVER) {
            // LS case
            final Font font = PlacementUtil.fontFor(label);
            width = PlacementUtil.estimateTextSizeMock(font, label.getText()).getWidth();
        } else {
            final FontData fontData = LabelManagementUtil.fontDataFor(label);
            width = PlacementUtilSWT.estimateTextSize(fontData, label.getText()).getWidth();
        }            

        if (width > targetWidth) {
            String textWithoutLineBreaks = label.getText().replace("\n", " ");
            String restText = textWithoutLineBreaks;
            StringBuilder resultText = new StringBuilder(label.getText().length());

            while (restText.length() != 0) {
                // Delete surrounding whitespace
                restText = restText.trim();
                
                // Find the part of the rest of the string which fits the line
                String fittingString = null;
                if (Klighd.IS_LANGUAGE_SERVER) {
                    fittingString = PlacementUtil.findFittingString(
                            restText, PlacementUtil.fontFor(label), targetWidth);
                } else {
                    fittingString = LabelManagementUtil.findFittingString(
                            restText, LabelManagementUtil.fontDataFor(label), targetWidth);
                }
                

                // Break if the targetWidth is too small to find something
                if (fittingString.equals("")) {
                    return Result.modified("");
                }
                
                resultText.append(fittingString).append("\n");
                restText = restText.substring(fittingString.length());
            }
            
            // Delete last \n
            return Result.modified(resultText.substring(0, resultText.length() - 1));
            
        } else {
            // We label wasn't too long
            return Result.unmodified();
        }
    }

}
