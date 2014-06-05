/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui;

import org.eclipse.jface.text.ITextSelection;

/**
 * KLighD-specific implementation of {@link ITextSelection}.<br>
 * Its aim is to broadcast the selection of cursor selectable label's text to platform's
 * {@link org.eclipse.ui.ISelectionService ISelectionService}.
 * 
 * @author chsch
 */
public class KlighdTextSelection implements ITextSelection {

    private final String text;
    
    private final boolean completeLine;

    private final boolean completeLabel;
    

    /**
     * Standard Constructor.
     * 
     * @param theText
     *            the text selection
     * @param isCompleteLine
     *            <code>true</code> if whole line is selected
     * @param isCompleteLabel
     *            <code>true</code> if whole label is selected
     */
    public KlighdTextSelection(final String theText, final boolean isCompleteLine,
            final boolean isCompleteLabel) {
        this.text = theText;
        this.completeLine = isCompleteLine;
        this.completeLabel = isCompleteLabel;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return text;
    }


    /**
     * @return the text
     */
    public String getText() {
        return text;
    }


    /**
     * @return the completeLine
     */
    public boolean isCompleteLine() {
        return completeLine;
    }


    /**
     * @return the completeLabel
     */
    public boolean isCompleteLabel() {
        return completeLabel;
    }


    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return false;
    }


    /**
     * {@inheritDoc}
     */
    public int getOffset() {
        return 0;
    }


    /**
     * {@inheritDoc}
     */
    public int getLength() {
        return text.length();
    }


    /**
     * {@inheritDoc}
     */
    public int getStartLine() {
        return 0;
    }


    /**
     * {@inheritDoc}
     */
    public int getEndLine() {
        return 0;
    }
}
