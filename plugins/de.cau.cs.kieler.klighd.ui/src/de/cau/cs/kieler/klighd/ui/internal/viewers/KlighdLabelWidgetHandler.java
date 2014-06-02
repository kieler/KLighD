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
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * A dedicated {@link KlighdBasicInputEventHandler} in charge of putting the text label widget in
 * place and updating it.
 * 
 * @author chsch
 */
public class KlighdLabelWidgetHandler extends KlighdBasicInputEventHandler {

    private final KlighdMainCamera camera;
    private StyledText labelWidget;
    
    /**
     * Constructor that just calls super.
     * 
     * @param viewer
     *            the employed {@link PiccoloViewerUI}
     * @param labelWidget
     *            the employed labelWidget
     */
    public KlighdLabelWidgetHandler(final PiccoloViewerUI viewer, final StyledText labelWidget) {
        super();
        // this.viewer = viewer;
        this.labelWidget = labelWidget;
        this.camera = viewer.getCanvas().getCamera();

        new PropertyChangeListener() {
            {
                camera.addPropertyChangeListener(PCamera.PROPERTY_VIEW_TRANSFORM, this);
                camera.addPropertyChangeListener(NodeDisposeListener.DISPOSE, this);
            }

            public void propertyChange(final PropertyChangeEvent event) {
                final String propName = event.getPropertyName();

                if (PCamera.PROPERTY_VIEW_TRANSFORM.equals(propName)) {
                    KlighdLabelWidgetHandler.this.updateWidgetBounds(null);

                } else if (NodeDisposeListener.DISPOSE.equals(propName)) {
                    camera.removePropertyChangeListener(this);
                }
            }
        };
    }


    private boolean widgetPrepared = false;
    
    private void setWidgetPrepared() {
        widgetPrepared = true;
        System.out.println("'widgetPrepared' set");
        labelWidget.getDisplay().timerExec(500, r);
    }
    
    private Runnable r = new Runnable() {
        
        public void run() {
            System.out.println("'widgetPrepared' unset");
            widgetPrepared = false;
        }
    }; 

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final PInputEvent event) {
//        System.out.println("Move");
//        if (widgetPrepared) {
//            System.out.println("Forward Move");
//            forwardEventToLabel(event.getSourceSwingEvent());
//            event.setHandled(true);
//        } else
        if (event.getPickedNode() instanceof KlighdStyledText) {
            updateTextInput(event, false);
        } else if (labelWidget.getSelectionCount() == 0) {
            labelWidget.setVisible(false);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final PInputEvent event) {
        if (widgetPrepared) {            
            forwardEventToLabel(event.getSourceSwingEvent());

        } else if (labelWidget.isVisible() && labelWidget.getSelectionCount() != 0) {
            if (event.getPickedNode() instanceof KlighdStyledText) {
                final boolean widgetVisible = updateTextInput(event, true);

                if (!widgetVisible) {
                    System.out.println("Return");
                    return;
                }
                
                event.setHandled(true);
                
                injectMoveEvent(event.getSourceSwingEvent());

                setWidgetPrepared();
                
                forwardEventToLabel(event.getSourceSwingEvent());
            }

        } else if (!labelWidget.isVisible()) {
            final boolean widgetVisible = this.updateTextInput(event, true);
            if (widgetVisible) {
                event.setHandled(true);

                setWidgetPrepared();
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final PInputEvent event) {
        if (widgetPrepared) {
            event.setHandled(true);

            forwardEventToLabel(event.getSourceSwingEvent());
//            System.out.println("Forward release");
        }
    }
    
    private void injectMoveEvent(final InputEvent input) {
        final KlighdMouseEvent kme = (KlighdMouseEvent) input;
        final MouseEvent sourceEvent = kme.getEvent();
        
        final Point loc = ((Control) labelWidget.getParent()).toDisplay(0, 0);
        final Event event = new Event();
        event.type = SWT.MouseMove;
        event.widget = labelWidget;
        event.x = loc.x + sourceEvent.x - 1;
        event.y = loc.y + sourceEvent.y - 1;

//        System.out.println("Move left");
        labelWidget.getDisplay().post(event);
        
    }
    
    private void forwardEventToLabel(final InputEvent input) {
        final KlighdMouseEvent kme = (KlighdMouseEvent) input;
        final MouseEvent sourceEvent = kme.getEvent();
        final int eventType = kme.getEventType();

        final Point loc;
        if (eventType == SWT.MouseMove) {
            loc = ((Control) labelWidget).toDisplay(0, 0);            
        } else {
            loc = new Point(0, 0);
        }

        final Event event = new Event();
        event.button = sourceEvent.button;
        event.display = labelWidget.getDisplay();
        event.type = eventType;
        event.widget = labelWidget;
        event.x = sourceEvent.x + loc.x;
        event.y = sourceEvent.y + loc.y;

        labelWidget.getDisplay().post(event);
    }

    /**
     * Sets position, style and text of the text input widget to the text element the mouse
     * currently hovers over.
     * 
     * @param event
     *            the event that triggered this update.
     * @return <code>true</code> if the text widget has been updated successfully,
     *         <code>false</code> if the update has been aborted
     */
    private boolean updateTextInput(final PInputEvent event, final boolean force) {
        final PNode n = event.getPickedNode();
        final KText kText;
        final KlighdStyledText styledText;

        if (n instanceof KlighdStyledText) {
            styledText = (KlighdStyledText) n;
            kText = styledText.getGraphElement();
        } else {
            return false;
        }

        if (kText == null || !kText.isCursorSelectable() || !force
                && labelWidget.getSelectionCount() > 0) {
            return false;
        }
        
        final String text = styledText.getText();

        // determine text value
        if (text == null) {
            if (kText != null && kText.getText() != null) {
                labelWidget.setText(kText.getText());
            } else {
                return false;
            }
        } else {
            labelWidget.setText(text);
        }

        updateWidgetBounds(styledText);

        // determine text color
        final Color oldColor = labelWidget.getForeground();
        final Color newColor = new Color(labelWidget.getDisplay(), styledText.getPenColor());
        labelWidget.setForeground(newColor);
        oldColor.dispose();

        labelWidget.setVisible(true);

        return true;
    }


    /** String key for caching the KlighdStyledText in the labelWidget's data list. */
    static final String STYLED_TEXT_FIGURE_KEY = "STYLED_TEXT_FIGURE_KEY";

    /** String key for caching the font scale factor in the labelWidget's data list. */
    static final String FONT_SCALE_FACTOR_KEY = "FONT_SCALE_FACTOR_KEY";
    
    /** example ...|11.0|.. */
    static final String FONT_HEIGHT_PATTERN = "\\|\\d*\\p{Punct}\\d*\\|";

    /**
     * 
     * @param styledText
     */
    private void updateWidgetBounds(final KlighdStyledText styledText) {

        final KlighdStyledText theStyledText;
        if (styledText != null) {
            labelWidget.setData(STYLED_TEXT_FIGURE_KEY, styledText);
            theStyledText = styledText;
        } else {
            theStyledText = (KlighdStyledText) labelWidget.getData(STYLED_TEXT_FIGURE_KEY);
            if (theStyledText == null) {
                return;
            }
        }

        // determine global position of the text element
        //  although 'clipRelativeGlobalBoundsOf' may return null that should never happen here as
        //  this is method is supposed to be only called for 'styledText' element that are contained
        //  in the current clip
        final Rectangle2D bounds =
                NodeUtil.clipRelativeGlobalBoundsOf(theStyledText, camera.getDisplayedINode());
        
        if (bounds == null) {
            return;
        }
        
        camera.getViewTransformReference().transform(bounds, bounds);

        labelWidget.setLocation((int) Math.round(bounds.getX()), (int) Math.round(bounds.getY()));

        final Float prevFontScale = (Float) labelWidget.getData(FONT_SCALE_FACTOR_KEY);
        final float curViewScale = (float) camera.getViewScale();

        // in case styledText = null, i.e. this method has been called due to a view transform change
        //  and the widget is not moved to another text field,
        //  and the previously applied scale factor is configured and is equal to the current one
        // skip the resizing of the widget, it is not required.
        if (styledText == null && prevFontScale != null && prevFontScale.floatValue() == curViewScale) {
            return;
        }

        // backup the current view/font scale ...
        labelWidget.setData(FONT_SCALE_FACTOR_KEY, Float.valueOf(curViewScale));

        // ... and compose the updated FontData by means of a String-based configuration
        final String fontConfig = theStyledText.getFontData().toString();
        final StringTokenizer tokenizer = new StringTokenizer(fontConfig, "|", false);

        if (tokenizer.hasMoreTokens()) {
            // returns the version
            tokenizer.nextToken();
        }
        if (tokenizer.hasMoreTokens()) {
            // returns the font name 
            tokenizer.nextToken();
        }

        final float givenHeight;
        if (tokenizer.hasMoreTokens()) {
            givenHeight = Float.valueOf(tokenizer.nextToken());
        } else {
            givenHeight = theStyledText.getFontData().getHeight();
        }

        // Create the updated FontData ...
        final FontData fd = new FontData(fontConfig.replaceFirst(FONT_HEIGHT_PATTERN,
                        "|" + Float.toString(givenHeight * curViewScale) + "|"));

        // ... dispose the previous Font, configure the new one, and update the text widget's size 
//        labelWidget.getFont().dispose();        
        labelWidget.setFont(new Font(labelWidget.getDisplay(), fd));
        labelWidget.setSize(labelWidget.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }
}
