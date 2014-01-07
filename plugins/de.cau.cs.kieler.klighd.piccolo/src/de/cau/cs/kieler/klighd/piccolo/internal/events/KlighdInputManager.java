/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.event.MouseEvent;

import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventListener;

/**
 * Specialized {@link PInputManager} that replaces the evaluation of events from the SWT event
 * listeners installed on the {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas
 * KlighdCanvas} based on AWT's event type codes by an evaluation based on the codes of
 * {@link org.eclipse.swt.SWT SWT}. This way {@link org.eclipse.swt.SWT#MouseDoubleClick
 * SWT#MouseDoubleClick}, {@link org.eclipse.swt.SWT#MouseHover SWT#MouseHover}, and
 * {@link org.eclipse.swt.SWT#Gesture SWT#Gesture} events are supported, too, which have no
 * corresponding counterparts in AWT.<br>
 * <br>
 * Fortunately, {@link PInputManager} is a subclass of
 * {@link edu.umd.cs.piccolo.event.PBasicInputEventHandler PBasicInputEventHandler} so we can
 * delegate to a {@link KlighdBasicInputEventHandler} while performing
 * {@link #processEvent(PInputEvent, int)}. This {@link KlighdBasicInputEventHandler} "delegates"
 * back to <code>this</code> instance while calling the particular event handling methods.<br>
 * <br>
 * This class implements {@link IKlighdInputEventHandlerEx} in order to properly support the above
 * mentioned SWT events, see {@link KlighdBasicInputEventHandler}.<br>
 * <br>
 * <b>Note:</b> the local override of
 * {@link #dispatchEventToListener(PInputEvent, int, PInputEventListener)} omits the propagation of
 * {@link MouseEvent#MOUSE_ENTERED} and {@link MouseEvent#MOUSE_EXITED} e.g. fired by
 * {@link #checkForMouseEnteredAndExited(PInputEvent)} as it disturbs the panning.
 * 
 * @author chsch
 */
public class KlighdInputManager extends PInputManager implements IKlighdInputEventHandlerEx {

    //SUPPRESS CHECKSTYLE NEXT 25 MagicNumber
    
    /**
     * The "mouse double clicked " event type. Such events are fired if the mouse has been clicked
     * doubly.
     */
    public static final int MOUSE_DOUBLY_CLICKED = 8 + MouseEvent.MOUSE_FIRST;
    
    /**
     * The "mouse hovered" event type. Such events are fired if the mouse stays at a position for a
     * while.
     */
    public static final int MOUSE_HOVERED = 9 + MouseEvent.MOUSE_FIRST;
    
    /**
     * The "mouse horizontal wheel" event type. Such events are fired if the mouse' horizontal wheel
     * is rotated.
     */
    public static final int MOUSE_HORIZONTAL_WHEEL = 10 + MouseEvent.MOUSE_FIRST;
    
    /**
     * The "mouse gesture" event type. Such events are treated as mouse event since AWT doesn't
     * provide any gesture support yet.
     */
    public static final int MOUSE_GESTURE = 11 + MouseEvent.MOUSE_FIRST;
    
    /**
     * The delegate event handler employed for replacing the AWT event code-based event evaluation.  
     */
    private KlighdBasicInputEventHandler helper = new KlighdBasicInputEventHandler(this);


    // the overriding method replacing the original event evaluation
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processEvent(final PInputEvent event, final int type) {
        helper.processEvent(event, type);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatchEventToListener(final PInputEvent event, final int type,
            final PInputEventListener listener) {
        if (type == MouseEvent.MOUSE_ENTERED || type == MouseEvent.MOUSE_EXITED) {
            // suppress original implementation in this case as it disturbs the panning,
            //  may be implemented in future in case exiting/entering of PNodes shall be observed 
            
            return;
        }
        super.dispatchEventToListener(event, type, listener);
    }

    // the additional handler methods according to KlighdInputEventHandlerEx

    /**
     * {@inheritDoc}
     */
    public void mousemouseDoubleClicked(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_DOUBLY_CLICKED, getMouseOver());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHovered(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_HOVERED, getMouseOver());
    }

    /**
     * {@inheritDoc}
     */
    public void mouseHorizontalWheelRotated(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_HORIZONTAL_WHEEL, getMouseOver());
    }

    /**
     * {@inheritDoc}
     */
    public void gesture(final PInputEvent event) {
        this.dispatchEventToListener(event, MOUSE_GESTURE, getMouseOver());
    }
}
