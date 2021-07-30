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

import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

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
        final FontData font = LabelManagementUtil.fontDataFor(label);

        if (PlacementUtil.estimateTextSize(font, label.getText()).getWidth() > targetWidth) {
            String textWithoutLineBreaks = label.getText().replace("\n", " ");
            String restText = textWithoutLineBreaks;
            StringBuilder resultText = new StringBuilder(label.getText().length());

            while (restText.length() != 0) {
                // Delete surrounding whitespace
                restText = restText.trim();
                
                // Find the part of the rest of the string which fits the line
                String fittingString = LabelManagementUtil.findFittingString(
                        restText, font, targetWidth);

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
