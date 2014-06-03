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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import com.google.common.base.Strings;

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
 * place and updating it.<br>
 * <br>
 * The text label shall be as smoothly as possible integrated into the diagram meaning that there
 * should be as little as possible additional clicks required in order to select the text (or parts
 * of it) and copy it, for example. This however requires quite some event handling magic like
 * duplicating SWT events.
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


    private boolean widgetJustPrepared = false;

    /**
     * {@inheritDoc}<br>
     * <br>
     * This method triggers the preparation of the text label widget if it's not blocked due to a
     * text selection and the mouse moved over a text figure node, and hides the widget if the mouse
     * moved over a non-text figure and no text selection was defined. 
     */
    @Override
    public void mouseMoved(final PInputEvent event) {
        if (widgetJustPrepared) {
            // This special case is active if the text widget is been prepared some milliseconds ago.
            // Thus, prevent the canvas, i.e. the registered handlers, from reacting on this event,
            //  and "forward" it to the label widget.
            
            forwardEventToLabel(event.getSourceSwingEvent());
            
            // Don't let any further event handler evaluate 'event'.
            //  This works fine since this handler is supposed to be the last one in the row of
            //  registered handlers and is, therefore, called first
            event.setHandled(true);

        } else if (event.getPickedNode() instanceof KlighdStyledText) {
            updateTextInput(event, false);
            
            // event.setHandled(true) is skipped here by intention
            //  - other handlers might want to react on it

        } else if (labelWidget.getSelectionCount() == 0) {
            labelWidget.setVisible(false);

            // event.setHandled(true) is skipped here by intention
            //  - other handlers might want to react on it
        }
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This method triggers the preparation of the text label widget if the user clicked on it,
     * regardless whether the widget is blocked due to text selection.
     */
    @Override
    public void mousePressed(final PInputEvent event) {
        if (widgetJustPrepared) {            
            // This special case is active if the text widget is been prepared some milliseconds ago.
            // Thus, prevent the canvas, i.e. the registered handlers, from reacting on this event,
            //  and "forward" it to the label widget.
            
            forwardEventToLabel(event.getSourceSwingEvent());
            
            // Don't let any further event handler evaluate 'event'.
            //  This works fine since this handler is supposed to be the last one in the row of
            //  registered handlers and is, therefore, called first
            event.setHandled(true);

        } else if (labelWidget.isVisible() && labelWidget.getSelectionCount() != 0) {
            // if the widget is shown and blocked by a text selection try to steel the text label
            //  widget and reset it to the picked styled text (indicated by 'true')

            final boolean widgetVisible = updateTextInput(event, true);
            if (!widgetVisible) {
                // stop in case the widget couldn't be brought to the picked figure, since it is
                //  not a text figure for example
                return;
            }

            // otherwise prevent other handler from evaluating this event 
            event.setHandled(true);

            // fire a small mouse movement that helps much in capturing the first character of a
            //  line in case a mouse-press-and-move-selection event
            injectMoveLeftEvent(event.getSourceSwingEvent());

            // "forward" the mouse pressed event to the label widget in order to initiate the
            //  selection process
            forwardEventToLabel(event.getSourceSwingEvent());

        } else if (!labelWidget.isVisible()) {
            // if the widget is not visible and try to prepare the label text widget,
            //  this is helpful in case the whole tool was inactive and, therefore, no move events
            //  are propagated to the canvas widget
            final boolean widgetVisible = this.updateTextInput(event, false);
            if (widgetVisible) {
                event.setHandled(true);

                // "forward" the mouse pressed event to the label widget in order to initiate the
                //  selection process if required
                forwardEventToLabel(event.getSourceSwingEvent());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final PInputEvent event) {
        if (widgetJustPrepared) {
            // This special case is active if the text widget is been prepared some milliseconds ago.
            // Thus, prevent the canvas, i.e. the registered handlers, from reacting on this event,
            //  and "forward" it to the label widget in order to enable immediate double clicks

            event.setHandled(true);
            forwardEventToLabel(event.getSourceSwingEvent());
        }
    }


    /**
     * Configures the text label widget to exhibit the text content in case event's picked node is a
     * {@link KlighdStyledText} that is configured to be cursorSelectable and its text is non-
     * <code>null</code>.
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
            return false;
        } else if (Strings.isNullOrEmpty(text)) {
            labelWidget.setText(" ");
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
        labelWidget.setFocus();
        setWidgetPrepared();

        return true;
    }


    /** String key for caching the KlighdStyledText in the labelWidget's data list. */
    // this field is package protected by intention
    static final String STYLED_TEXT_FIGURE_KEY = "STYLED_TEXT_FIGURE_KEY";

    /** String key for caching the font scale factor in the labelWidget's data list. */
    private static final String FONT_SCALE_FACTOR_KEY = "FONT_SCALE_FACTOR_KEY";
    
    /** Example: ...|11.0|.. */
    private static final String FONT_HEIGHT_PATTERN_REGEX = "\\|\\d*\\p{Punct}\\d*\\|";

    /** The pattern employed in configuring the fonts, is kept in order to avoid re-compilations. */
    private static Pattern fontHeightPattern = null;


    /**
     * Aligns the label text widget to the given <code>styledText</code> in terms of position, font
     * size, and size.
     * 
     * @param styledText
     *            the {@link KlighdStyledText} the text label widget is to be aligned to
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

        if (fontHeightPattern == null) {
            fontHeightPattern = Pattern.compile(FONT_HEIGHT_PATTERN_REGEX);
        }

        final Matcher matcher = fontHeightPattern.matcher(fontConfig);

        final float givenHeight;
        if (matcher.find()) {
            givenHeight = Float.valueOf(fontConfig.substring(matcher.start() + 1, matcher.end() - 1));
        } else {
            givenHeight = theStyledText.getFontData().getHeight();
        }

        // Create the updated FontData ...
        final FontData fd = new FontData(
                matcher.replaceFirst("|" + Float.toString(givenHeight * curViewScale) + "|"));

        final Font previousFont = labelWidget.getFont();

        // ... dispose the previous Font, configure the new one, and update the text widget's size 
        labelWidget.setFont(new Font(labelWidget.getDisplay(), fd));
        labelWidget.setSize(labelWidget.computeSize(SWT.DEFAULT, SWT.DEFAULT));

        previousFont.dispose();        
    }

    private void setWidgetPrepared() {
        widgetJustPrepared = true;
        // System.out.println("'widgetPrepared' set");

        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        labelWidget.getDisplay().timerExec(250, resetWidgetJustPrepared);
    }

    private final Runnable resetWidgetJustPrepared = new Runnable() {
        public void run() {
            widgetJustPrepared = false;
            // System.out.println("'widgetPrepared' reset");
        }
    };

    /**
     * Fires an additional {@link SWT#MouseMove MouseMove} {@link Event} one unit in left of the
     * position given in <code>inputEvent</code>.<br>
     * <br>
     * See {@link org.eclipse.swt.widgets.Display#post(Event) Display#post(Event)} for details on that.
     */
    private void injectMoveLeftEvent(final InputEvent inputEvent) {
        final MouseEvent sourceEvent = ((KlighdMouseEvent) inputEvent).getEvent();
        final Point canvasLocation = ((Control) sourceEvent.widget).toDisplay(0, 0);

        final Event event = new Event();
        event.type = SWT.MouseMove;
        event.x = canvasLocation.x + sourceEvent.x - 1;
        event.y = canvasLocation.y + sourceEvent.y;

        sourceEvent.display.post(event);
    }

    /**
     * Fires an additional {@link Event} event one unit at the position given in
     * <code>inputEvent</code>. This is used for "forwarding" events delivered to the canvas to the
     * label text widget.<br>
     * <br>
     * See {@link org.eclipse.swt.widgets.Display#post(Event) Display#post(Event)} for details on that.
     */
    private void forwardEventToLabel(final InputEvent input) {
        final KlighdMouseEvent kme = (KlighdMouseEvent) input;
        final MouseEvent sourceEvent = kme.getEvent();
        final int eventType = kme.getEventType();

        final Event event = new Event();
        event.button = sourceEvent.button;
        event.type = eventType;

        if (eventType == SWT.MouseMove) {
            final Point canvasLocation = ((Control) sourceEvent.widget).toDisplay(0, 0);            
            event.x = sourceEvent.x + canvasLocation.x;
            event.y = sourceEvent.y + canvasLocation.y;
        }

        sourceEvent.display.post(event);
    }    
}
