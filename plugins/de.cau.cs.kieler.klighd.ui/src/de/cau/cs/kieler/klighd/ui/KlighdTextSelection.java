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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * KLighD-specific implementation of {@link ITextSelection}.<br>
 * Its aim is to broadcast the selection of cursor selectable label's text to platform's
 * {@link org.eclipse.ui.ISelectionService ISelectionService}.<br>
 * In order to let the Properties View also react on this type of selection this class
 * also implements {@link IStructuredSelection} by providing itself as the only element.<br>
 * Be aware of the risk of recursion!
 * 
 * @author chsch
 */
public class KlighdTextSelection implements IStructuredSelection, ITextSelection {

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
     * @return the the parent {@link KNode} of {@link #getKGraphElement()} or <code>null</code>
     */
    public KNode getKNode() {
        return kgraphElement == null
                ? null : ModelingUtil.eContainerOfType(kgraphElement, KNode.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "KLighdTextSelection: " + text;
    }


    /* -------------------------------- */
    /*   ITextSelection methods         */
    /* -------------------------------- */

    /**
     * @return the text
     */
    public String getText() {
        return text;
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


    /* -------------------------------- */
    /*   IStructuredSelection methods   */
    /* -------------------------------- */
    
    /**
     * {@inheritDoc}
     * @deprecated this method still simply returns zero
     */
    public int getEndLine() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public Object getFirstElement() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<KlighdTextSelection> iterator() {
        return Iterators.singletonIterator(this);
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public KlighdTextSelection[] toArray() {
        return new KlighdTextSelection[] { this };
    }

    /**
     * {@inheritDoc}
     */
    public List<KlighdTextSelection> toList() {
        return Collections.singletonList(this);
    }
}
