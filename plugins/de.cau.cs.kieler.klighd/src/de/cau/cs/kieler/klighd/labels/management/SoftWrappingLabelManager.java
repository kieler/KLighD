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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.labels.management;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Label manager which soft wraps the text so it fits a certain width. Soft wrapping only inserts
 * line breaks between words. If a word is longer than the target width, the width of the longest
 * word is used as the target width instead.
 * 
 * <p>
 * It does not make sense to put this label manager into {@link Mode#ALWAYS_ON}. If a label does not
 * exceed a target width, there's no sense in inserting wraps anywhere.
 * </p>
 * 
 * @author ybl
 * @author cds
 */
public class SoftWrappingLabelManager extends AbstractKlighdLabelManager {

    /**
     * Returns the amount of line overhang that is permitted in percent of the effectiveTargetWidth.
     * @param label The ElkLabel for which the fuzziness is requested.
     * @return The fuzziness
     */
    public double getFuzziness(final ElkLabel label) {
        return label.getProperty(CoreOptions.SOFTWRAPPING_FUZZINESS);
    }
    
    @Override
    public Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        final FontData font = LabelManagementUtil.fontDataFor(label);
        
        if (PlacementUtil.estimateTextSize(font, label.getText()).getWidth() > targetWidth) {
            String textWithoutLineBreaks = label.getText().replace("\n", " ");

            // Divide the text into "words"
            String[] words = textWithoutLineBreaks.split(" ");
            StringBuilder resultText = new StringBuilder(label.getText().length());
            String currentLineText;
            String prefixLine = "";
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
                
                // if current line is smaller than the threshold, then pull the next line up, this should only happen
                // in the first line or after special line breaks such as semantic line breaks
                if (prefixLine != "") {
                    currentLineText = prefixLine + " " + currentLineText;
                }
                
                // if current line is too small, make it a prefix line
                lineWidth = PlacementUtil.estimateTextSize(font, currentLineText).getWidth();
                prefixLine = "";
                if (lineWidth < effectiveTargetWidth * getFuzziness(label)) {
                    prefixLine = currentLineText;
                    currentLineText = "";
                }

                // Check whether next line would be below the fuzzy threshold
                if (getFuzziness(label) > 0) {
                    int previewWordIndex = currWordIndex;
                    if (previewWordIndex < words.length) {
                        String previewLineText = words[previewWordIndex];
                        testText = previewLineText;
                        do {
                            previewLineText = testText;
                            if (previewWordIndex < words.length - 1) {
                                testText = previewLineText + " " + words[++previewWordIndex];
                            } else {
                                testText = " ";
                                previewWordIndex++;
                            }
                            lineWidth = PlacementUtil.estimateTextSize(font, testText).getWidth();
                        } while (lineWidth < effectiveTargetWidth && previewWordIndex < words.length);
                        lineWidth = PlacementUtil.estimateTextSize(font, previewLineText).getWidth();
                        if (lineWidth < effectiveTargetWidth * getFuzziness(label)) {
                            // next line would contain too much whitespace so append it to this line
                            currWordIndex = previewWordIndex;
                            currentLineText += " " + previewLineText;
                        }
                    }
                }
                
                // this means that we deferred the current line and must do another iteration
                if (currentLineText == "") {
                    continue;
                }
                
                // No more words fit so the line is added to the result
                if (currWordIndex < words.length) {
                    resultText.append(currentLineText).append("\n");
                } else {
                    resultText.append(currentLineText);
                    break;
                }
            }
            
            return Result.modified(resultText.toString());
            
        } else {
            // We label wasn't too long
            return Result.unmodified();
        }
        
        
        
    }

}
