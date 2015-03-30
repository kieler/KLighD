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

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.labels.ILabelSizeModifier;

/**
 * Modifies the size of labels by word-wrapping lines once the target width is reached.
 * 
 * <p>
 * The label passed to this size modifier is the one from the layout KGraph fed to the layout
 * algorithm, not the one used in KLighD's view model. This means that we need to remember the
 * label's new text somewhere. We actually remember it by modifying the text of the layout graph's
 * label. When applying the layout results,
 * {@link de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager KlighdLayoutManager}
 * checks if the layout graph's label text differs from the text in the view model. If so, it
 * applies the label's new text to a property set on the label
 * ({@link KlighdLabelProperties#LABEL_TEXT_OVERRIDE}) which is then used as the label's text when
 * displaying the label.
 * </p>
 * 
 * @author cds
 */
public final class WrappingLabelSizeModifier implements ILabelSizeModifier {
    
    /** An estimation of the number of newline characters we will usually add to a label. */
    private static final int INSERTED_NEWLINES_ESTIMATION = 10;
    
    
    /**
     * {@inheritDoc}
     */
    public KVector resizeLabelToWidth(final Object label, final double targetWidth) {
        if (label instanceof KLabel) {
            return doResize((KLabel) label);
        } else {
            return null;
        }
    }
    
    /**
     * Does the actual work of resizing the label.
     * 
     * @param label
     *            the label to shorten.
     * @return new label dimensions.
     */
    private KVector doResize(final KLabel label) {
        // The label's original text
        final String origLabelText = label.getText();
        // This is where we build the label's new text (make enough space for the label's original text
        // plus a few newline characters we're probably going to add)
        final StringBuilder newLabelText = new StringBuilder(
                origLabelText.length() + INSERTED_NEWLINES_ESTIMATION);
        
        // Index of the first character of the line we're currently wrapping
        int currentStartOfLine = 0;
        // Index of the last character of the current line that we know still fits
        int lastSafeEndOfLine = 0;
        // Index of the current character we're looking at
        int currentIndex = 0;
        // The actual character at the current position
        char currentChar;
        // Length of the original label's text
        final int origLabelTextLength = label.getText().length();
        
        // TODO: Complete this!
        while (currentIndex < origLabelTextLength) {
            // We're interested in what character we're dealing with
            currentChar = origLabelText.charAt(currentIndex);
            switch (currentChar) {
            case ' ':
                break;
                
            case '\n':
                // Newlines can simply be added to the new text and reset the current line's start
                newLabelText.append(currentChar);
                
                currentStartOfLine = currentIndex + 1;
                lastSafeEndOfLine = currentStartOfLine;
                break;
                
            default:
                
            }
            
            // Advance one character
            currentIndex++;
        }
        
        // TODO Set the label's new text and size
        label.setText(newLabelText.toString());
        
        KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
        return new KVector(15, shapeLayout.getHeight());
    }
    
}
