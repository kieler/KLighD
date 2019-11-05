/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal;

import org.eclipse.elk.core.math.KVector;

import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * A dedicated interface enable the separation of the features provided by
 * {@link de.cau.cs.kieler.klighd.IViewer IViewer} and the required methods for properly recording
 * layout changes.<br>
 * <br>
 * This is an internal interface that is not to be used by clients but implemented by
 * {@link de.cau.cs.kieler.klighd.IViewer IViewers}.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public interface ILayoutRecorder {

    /**
     * Starts to record layout changes in the model instead of instantly applying them to the
     * visualization.<br>
     * <br>
     * Executing {@link #stopRecording(ZoomStyle, KNode, int)} applies all recorded layout changes.
     */
    void startRecording();
    
    /**
     * Stops to record layout changes, initialized by {@link #startRecording()}.
     * 
     * @param animationTime
     *            duration of the animated layout
     */
    void stopRecording(final int animationTime);

    /**
     * Stops to record layout changes, initialized by {@link #startRecording()}.
     * 
     * @param zoomStyle
     *            the style used to zoom, e.g. zoom to fit or zoom to focus
     * @param focusElement
     *            the {@link KGraphElement} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS} or {@link ZoomStyle#ZOOM_TO_STAY_SELECTED}, is ignored otherwise
     * @param animationTime
     *            duration of the animated layout
     */
    void stopRecording(final ZoomStyle zoomStyle, final KGraphElement focusElement, final int animationTime);

    /**
     * Stops to record layout changes, initialized by {@link #startRecording()}.
     * 
     * @param zoomStyle
     *            the style used to zoom, e.g. zoom to fit or zoom to focus
     * @param focusElement
     *            the {@link KGraphElement} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS} or {@link ZoomStyle#ZOOM_TO_STAY_SELECTED}, is ignored otherwise
     * @param previousPosition
     *            the position the selected element had in the previous layout run.
     *            Is ignored if the <code>zoomStyle</code> is ont {@link ZoomStyle#ZOOM_TO_STAY_SELECTED}.
     * @param animationTime
     *            duration of the animated layout
     */
    void stopRecording(final ZoomStyle zoomStyle, final KGraphElement focusElement, final KVector previousPosition,
            final int animationTime);
}
