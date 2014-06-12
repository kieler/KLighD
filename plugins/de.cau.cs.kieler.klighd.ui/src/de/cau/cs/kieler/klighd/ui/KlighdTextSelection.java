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

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;

/**
 * KLighD-specific implementation of {@link ITextSelection}.<br>
 * Its aim is to broadcast the selection of cursor selectable label's text to platform's
 * {@link org.eclipse.ui.ISelectionService ISelectionService}.
 * 
 * @author chsch
 */
public class KlighdTextSelection implements ITextSelection {

    private final String text;
    
    private final int offset;
    
    private final boolean completeLine;

    private final boolean completeLabel;
    
    private final KGraphElement kgraphElement;
    

    /**
     * Standard Constructor.
     * 
     * @param theText
     *            the text selection
     * @param theOffset
     *            see {@link ITextSelection#getOffset()}
     * @param isCompleteLine
     *            <code>true</code> if whole line is selected
     * @param isCompleteLabel
     *            <code>true</code> if whole label is selected
     * @param graphNode
     *            the underlying {@link KGraphElement} containing the selected text
     */
    public KlighdTextSelection(final String theText, final int theOffset,
            final boolean isCompleteLine, final boolean isCompleteLabel,
            final IGraphElement<?> graphNode) {
        this.text = theText;
        this.offset = theOffset;
        this.completeLine = isCompleteLine;
        this.completeLabel = isCompleteLabel;
        this.kgraphElement = graphNode == null ? null : graphNode.getGraphElement();
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
     * @deprecated this method may still return wrong values
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
     * @return the kraphElement
     */
    public KGraphElement getKGraphElement() {
        return kgraphElement;
    }


    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return Strings.isNullOrEmpty(text);
    }


    /**
     * {@inheritDoc}
     */
    public int getOffset() {
        return offset;
    }


    /**
     * {@inheritDoc}
     */
    public int getLength() {
        return text.length();
    }


    /**
     * {@inheritDoc}
     * @deprecated this method still simply returns zero
     */
    public int getStartLine() {
        return 0;
    }


    /**
     * {@inheritDoc}
     * @deprecated this method still simply returns zero
     */
    public int getEndLine() {
        return 0;
    }
}
