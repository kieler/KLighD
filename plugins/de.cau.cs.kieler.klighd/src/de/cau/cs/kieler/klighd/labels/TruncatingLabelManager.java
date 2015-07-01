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

import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Modifies the size of labels by truncating them once the target width is reached. The rest of the
 * label's text is replaced by an ellipsis.
 * 
 * @author cds
 */
public final class TruncatingLabelManager extends AbstractKlighdLabelManager {
    
    /** The string appended to a truncated label text. */
    private static final String ELLIPSES = "...";

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected KVector doResizeLabelToWidth(final KLabel label, final double targetWidth) {
        final KShapeLayout labelLayout = label.getData(KShapeLayout.class);
        
        KVector newLabelSize = null;
        if (labelLayout.getWidth() > targetWidth) {
            // Label exceeds target width, so shorten it
            newLabelSize = truncateOverlyWideLabel(label, targetWidth);
        } else {
            // We also shorten multiline labels
            newLabelSize = truncateNarrowButMultilineLabel(label);
        }
        
        return newLabelSize;
    }
    
    /**
     * Truncates the text of the given label until it falls below the given target width.
     * 
     * @param label
     *            the label whose text to truncate.
     * @param targetWidth
     *            the width the label shouldn't exceed.
     * @return the label's new size as estimated.
     */
    private KVector truncateOverlyWideLabel(final KLabel label, final double targetWidth) {
        // The label's font data
        final FontData font = PlacementUtil.fontDataFor(label);
        // The label's original text
        final String origLabelText = label.getText();
        // Length of the original label's text
        final int origLabelTextLength = label.getText().length();
        
        // Our current index inside the label's text
        int currentIndex = 0;
        // The character at the current position
        char currentChar;
        // Index of the last non-whitespace character
        int lastNonWhitespaceCharacter = 0;
        
        // The label's size from the beginning up to the character we're currently at
        Bounds currentSize = null;
        
        // Iterate over the characters until we find a newline character or until the width exceeds
        // the target width; after this loop, currentSize is the (possibly new) size of the label
        while (currentIndex < origLabelTextLength) {
            // Measure the length of the string from the beginning up to the current position
            currentSize = PlacementUtil.estimateTextSize(font, origLabelText.substring(0, currentIndex));
            
            // If we exceed the target width or find a newline character, truncate!
            currentChar = origLabelText.charAt(currentIndex);
            if (currentSize.getWidth() > targetWidth || currentChar == '\n') {
                label.setText(origLabelText.substring(0, lastNonWhitespaceCharacter) + ELLIPSES);
                currentSize = PlacementUtil.estimateTextSize(font, label.getText());
                break;
            }
            
            // If this is not a whitespace character, remember its position
            if (!Character.isWhitespace(currentChar)) {
                lastNonWhitespaceCharacter = currentIndex;
            }
            
            currentIndex++;
        }
        
        return new KVector(currentSize.getWidth(), currentSize.getHeight());
    }
    
    /**
     * Truncates the text of the given label to the first line. The label is assumed to be narrower
     * than the required target width. If the label only has one line, nothing is changed.
     * 
     * @param label
     *            the label whose text to truncate.
     * @param origSize
     *            the label's original size.
     * @return the label's new size as estimated or {@code null} if it has not been shortened..
     */
    private KVector truncateNarrowButMultilineLabel(final KLabel label) {
        // Find the label's last non-whitespace character before the first newline
        int newlineIndex = label.getText().indexOf('\n');
        if (newlineIndex == -1) {
            return null;
        }
        
        // Find the last non-whitespace character before the newline
        String origText = label.getText();
        int currentIndex = newlineIndex - 1;
        while (currentIndex >= 0 && Character.isWhitespace(origText.charAt(currentIndex))) {
            currentIndex--;
        }
        
        // Set the new text
        String newText = currentIndex == -1
                ? ELLIPSES
                : origText.substring(0, currentIndex + 1) + ELLIPSES;
        label.setText(newText);
        
        // Find the label's new bounds
        final FontData font = PlacementUtil.fontDataFor(label);
        Bounds newSize = PlacementUtil.estimateTextSize(font, newText);
        
        return new KVector(newSize.getWidth(), newSize.getHeight());
    }
    
}
