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
package de.cau.cs.kieler.klighd.piccolo.krendering.viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.Timer;

import org.eclipse.swt.widgets.Display;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.piccolo.krendering.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTStyledText;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.SWTTimer;

/**
 * @author uru
 * 
 */
public class PiccoloTooltip {

    private PCamera camera;
    private PSWTAdvancedPath root;
    private PSWTStyledText tooltip;
    private Display display;

    private int tooltipDelay = 500;

    private static final int ROUNDNESS = 5;
    private static final int OPACITY = 220;
    private static final int INSETS = 5;

    /**
     * 
     */
    public PiccoloTooltip(final Display display, final PCamera camera) {
        this.display = display;
        this.camera = camera;
        this.tooltip = new PSWTStyledText(Lists.newArrayList("foobar"));

        tooltip.setPickable(false);

        root = PSWTAdvancedPath.createRoundRectangle(0, 0, 0, 0, ROUNDNESS, ROUNDNESS);
        root.setPaint(Color.WHITE);
        root.setPaintAlpha(OPACITY);
        root.addChild(tooltip);

        camera.addChild(root);

        camera.addInputEventListener(new TooltipListener());

    }

    /**
     * 
     * @author uru
     */
    class TooltipListener extends PBasicInputEventHandler {

        private KNode knode;
        private Point2D p;

        boolean visible = false;

        Timer timer = new SWTTimer(display, tooltipDelay, new ActionListener() {

            public void actionPerformed(final ActionEvent e) {

                List<String> tooltipStrings = Lists.newLinkedList();
                if (!knode.getLabels().isEmpty()) {
                    tooltipStrings.add(knode.getLabels().get(0).getText());
                }

                KShapeLayout l = knode.getData(KShapeLayout.class);
                String tt = l.getProperty(LayoutOptions.TOOLTIP);
                if (!Strings.isNullOrEmpty(tt)) {
                    tooltipStrings.addAll(Lists.newLinkedList(Splitter.on("\n").split(tt)));
                }

                tooltip.setText(Lists.newArrayList(tooltipStrings));
                root.setOffset(p.getX() + 2 * INSETS, p.getY() + 2 * INSETS);

                // adapt bounds to the text
                PBounds tooltipBounds = tooltip.getBounds();
                root.setPathToRoundRectangle((float) tooltipBounds.x - INSETS,
                        (float) tooltipBounds.y - INSETS, (float) tooltipBounds.width + 2 * INSETS,
                        (float) tooltipBounds.height + 2 * INSETS, ROUNDNESS, ROUNDNESS);

                root.setVisible(visible);
                root.invalidatePaint();
                root.invalidateLayout();
            }
        });

        public void mouseMoved(final PInputEvent event) {
            updateToolTip(event);

        }

        public void mouseDragged(final PInputEvent event) {
            updateToolTip(event);
        }

        public void updateToolTip(final PInputEvent event) {
            PNode n = event.getInputManager().getMouseOver().getPickedNode();

            if (n instanceof ITracingElement<?>) {
                visible = true;
                ITracingElement<?> graphElement = (ITracingElement<?>) n;
                Object ge = graphElement.getGraphElement();

                if (ge instanceof KNode) {
                    knode = (KNode) ge;

                    // get the mouse position
                    p = event.getCanvasPosition();
                    event.getPath().canvasToLocal(p, camera);

                    // start the timer
                    timer.setRepeats(false);
                    timer.start();
                }
            } else {
                visible = false;
                timer.stop();
                root.setVisible(visible);
            }

        }
    }

}
