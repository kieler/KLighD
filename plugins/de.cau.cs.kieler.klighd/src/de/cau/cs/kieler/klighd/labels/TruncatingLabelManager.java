/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Modifies the size of labels by truncating them once the target width is reached. The rest of the
 * label's text is replaced by an ellipsis.
 * 
 * @author ybl
 */
public class TruncatingLabelManager extends AbstractKlighdLabelManager {

    /** The string appended to a truncated label text. */
    private static final String ELLIPSES = "...";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final KLabel label, final double targetWidth) {
        // Check if the label is bigger than the targetWidth
        Bounds textBounds = PlacementUtil.estimateTextSize(
                PlacementUtil.fontDataFor(label), label.getText());
        
        if (textBounds.getWidth() > targetWidth) {
            // Size of three ellipses
            final float ellipseWidth = PlacementUtil.estimateTextSize(
                    PlacementUtil.fontDataFor(label), ELLIPSES).getWidth();
            String calculatedText = "";

            // If there is enough space for some text and ellipses, calculate the fitting text
            if (targetWidth > ellipseWidth) {
                calculatedText = LabelManagementUtil.findFittingString(label.getText(),
                        PlacementUtil.fontDataFor(label), targetWidth - ellipseWidth);

                // Delete whitespaces
                calculatedText = calculatedText.trim();                
            }
            
            return calculatedText + ELLIPSES;
        }
        
        return null;
    }

}
