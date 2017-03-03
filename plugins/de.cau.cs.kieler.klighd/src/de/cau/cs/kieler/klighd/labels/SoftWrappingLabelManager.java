/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Label manager which soft wraps the text so it fits a certain width. Soft wrapping only inserts line
 * breaks between words. If a word is longer than the target width, the width of the longest word is
 * used as the target width instead.
 * 
 * @author ybl
 */
public class SoftWrappingLabelManager extends AbstractKlighdLabelManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final ElkLabel label, final double targetWidth) {
        final FontData font = LabelManagementUtil.fontDataFor(label);
        String textWithoutLineBreaks = label.getText().replace("\n", " ");

        // Divide the text into "words"
        String[] words = textWithoutLineBreaks.split(" ");
        StringBuilder resultText = new StringBuilder(label.getText().length());
        String currentLineText;
        double effectiveTargetWidth =
                Math.max(LabelManagementUtil.getWidthOfBiggestWord(font, words), targetWidth);

        // iterate over the lines
        int currWordIndex = 0;
        while (currWordIndex < words.length) {
            currentLineText = words[currWordIndex];
            String testText = currentLineText;

            // Test if another word fits into this line
            double lineWidth = 0;
            do {
                currentLineText = testText;
                if (currWordIndex < words.length - 1) {
                    testText = currentLineText + " " + words[++currWordIndex];
                } else {
                    testText = " ";
                    currWordIndex++;
                }
                lineWidth = PlacementUtil.estimateTextSize(font, testText).getWidth();
            } while (lineWidth < effectiveTargetWidth && currWordIndex < words.length);

            // No more words fit so the line is added to the result
            if (currWordIndex < words.length) {
                resultText.append(currentLineText).append("\n");
            } else {
                resultText.append(currentLineText);
                break;
            }
        }
        
        return resultText.toString();
    }

}
