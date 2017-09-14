/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015, 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels.management;

import org.eclipse.elk.graph.ElkLabel;

import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Modifies the size of labels by truncating them once the target width is reached. The rest of the
 * label's text is replaced by an ellipsis.
 * 
 * <p>
 * It does not make sense to put this label manager into {@link Mode#ALWAYS_ON}. If a label does not
 * exceed a target width, there's no sense in truncating anything.
 * </p>
 * 
 * @author ybl
 * @author cds
 */
public class TruncatingLabelManager extends AbstractKlighdLabelManager {

    /** The string appended to a truncated label text. */
    private static final String ELLIPSES = "...";
    
    @Override
    public Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        // Check if the label is bigger than the targetWidth
        Bounds textBounds = PlacementUtil.estimateTextSize(
                LabelManagementUtil.fontDataFor(label), label.getText());
        
        if (textBounds.getWidth() > targetWidth) {
            // Size of three ellipses
            final float ellipseWidth = PlacementUtil.estimateTextSize(
                    LabelManagementUtil.fontDataFor(label), ELLIPSES).getWidth();
            String calculatedText = "";

            // If there is enough space for some text and ellipses, calculate the fitting text
            if (targetWidth > ellipseWidth) {
                calculatedText = LabelManagementUtil.findFittingString(label.getText(),
                        LabelManagementUtil.fontDataFor(label), targetWidth - ellipseWidth);

                // Delete whitespaces
                calculatedText = calculatedText.trim();                
            }
            
            return Result.modified(calculatedText + ELLIPSES);
            
        } else {
            // We label wasn't too long
            return Result.unmodified();
        }
    }

}
