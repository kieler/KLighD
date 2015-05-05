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
package de.cau.cs.kieler.klighd.piccolo.test.highlightedEdgeToForeground;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Scopes;

import de.cau.cs.kieler.core.krendering.Colors;
import de.cau.cs.kieler.core.krendering.ViewSynthesisShared;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.test.ColorMatcher;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * The aim of this class is to test the "bring the highlighted edge to foreground" feature.<br>
 * To this end a small diagram with ports and overlapping edges is build up (composition is out-
 * sourced to {@link HighlightedEdgeToForegroundTestModelGen}. In order to test not only single
 * methods but the whole tool a simple diagram viewer is launched and some mouse move and click
 * events are send to the employed {@link Display} instance. By means of those events edges are
 * selected and due to corresponding styles their color is switched from black to red.<br>
 * <br>
 * In order to assess the correctness of the tool's behavior the color of the diagram is evaluated
 * at certain positions of the diagram. Since the "measured" color will differ from the expected
 * one by some units due to anti-aliasing a similarity heuristic is applied.
 *
 * @author chsch
 */
public class HighlightedEdgeToForegroundTestDeactivated {

    private static HighlightedEdgeToForegroundTestModelGen testModelGen;
    private static ViewContext viewContext;

    private static Shell shell;
    private static Control canvas;
    private static Point zeroPoint;

    /**
     * Prepares a {@link Shell} with a KLighD diagram canvas displaying a simple test model.
     */
    @BeforeClass
    public static void prepare() {

        testModelGen = Guice.createInjector(new Module() {
            public void configure(final Binder binder) {
                binder.bindScope(ViewSynthesisShared.class, Scopes.SINGLETON);
            }
        }).getInstance(HighlightedEdgeToForegroundTestModelGen.class);

        shell = new Shell(Display.getDefault());
        shell.setSize(630, 330);
        shell.setLayout(new FillLayout());

        viewContext = new ViewContext((IDiagramWorkbenchPart) null, testModelGen.getTestModel())
                .configure(new KlighdSynthesisProperties().useViewer(PiccoloViewer.ID));

        new ContextViewer(shell).setModel(viewContext, true);

        viewContext.update(null);
        LightDiagramServices.layoutDiagram(viewContext, false, ZoomStyle.ZOOM_TO_ACTUAL_SIZE);

        shell.layout(true, true);
        shell.open();

        canvas = viewContext.getViewer().getControl();
        zeroPoint = canvas.toDisplay(0, 0);
    }

    private static final ColorMatcher<RGB> IS_BLACK = ColorMatcher.acceptingRGBsExpecting(Colors.BLACK);
    private static final ColorMatcher<RGB> IS_RED = ColorMatcher.acceptingRGBsExpecting(Colors.RED);


    /**
     * This is just a "warm up" test since the continuous build's (virtual) display's palette seems
     * to be very limited. The observed behavior is: If 'black' or 'white' is the first observed
     * color, only those colors will be obtained subsequently. I interpret this as follows: The
     * image's color palette then contains only black and white. If the image also contains 'red'
     * parts before testing the first time for any color the palette than also contains 'red'.
     * Weird ...
     */
    @Test
    public void test00() throws InterruptedException {
        final KShapeLayout firstChildNodeLayout =
                viewContext.getViewModel().getChildren().get(0).getChildren().get(0)
                        .getData(KShapeLayout.class);
        final int firstChildNodeYPos = Math.round(firstChildNodeLayout.getYpos());

        final int firstClickXPos = 5 + 100; // port width + border spacing + edge spacing factor * spacing

        waitAmoment();
        clickOn(firstClickXPos, firstChildNodeYPos);
        waitAmoment();

        clickOn(4, 4);
        waitAmoment();
    }


    /**
     * Tests the highlighting of overlapped interlevel edges connected to same source.
     */
    @Test
    public void test01() throws InterruptedException {
        final KShapeLayout firstChildNodeLayout =
                viewContext.getViewModel().getChildren().get(0).getChildren().get(0)
                        .getData(KShapeLayout.class);
        final int firstChildNodeYPos = Math.round(firstChildNodeLayout.getYpos());

        final KShapeLayout firstWPortLayout =
                viewContext.getViewModel().getChildren().get(0).getPorts().get(2)
                .getData(KShapeLayout.class);
        final int firstWPortLayoutCenterYPos = Math.round(firstWPortLayout.getYpos()) + 3;

        final int sampleXPos = 50;
        final int firstClickXPos = 5 + 100; // port width + border spacing + edge spacing factor * spacing
        final int secondClickXPos = 200;
        waitAmoment();

        moveTo(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_BLACK);

        clickOn(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_RED);

        moveTo(sampleXPos, firstWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, firstWPortLayoutCenterYPos), IS_RED);

        moveTo(secondClickXPos, firstWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, firstWPortLayoutCenterYPos), IS_BLACK);

        clickOn(secondClickXPos, firstWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, firstWPortLayoutCenterYPos), IS_RED);

        moveTo(sampleXPos, firstWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, firstWPortLayoutCenterYPos), IS_RED);

        clickOn(4, 4);
        waitAmoment();
    }


    /**
     * Tests the highlighting of overlapped interlevel edges connected to same target.
     */
    @Test
    public void test02() throws InterruptedException {
        final KShapeLayout firstChildNodeLayout =
                viewContext.getViewModel().getChildren().get(0).getChildren().get(0)
                        .getData(KShapeLayout.class);
        final int firstChildNodeYPos = Math.round(firstChildNodeLayout.getYpos());

        final KShapeLayout secondWPortLayout =
                viewContext.getViewModel().getChildren().get(0).getPorts().get(0)
                .getData(KShapeLayout.class);
        final int secondWPortLayoutCenterYPos = Math.round(secondWPortLayout.getYpos()) + 3;

        final int sampleXPos = 120;
        final int firstClickXPos = 5 + 100; // port width + border spacing + edge spacing factor * spacing
        final int secondClickXPos = 50;
        waitAmoment();

        moveTo(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_BLACK);

        clickOn(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_RED);

        moveTo(sampleXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, secondWPortLayoutCenterYPos), IS_RED);

        moveTo(secondClickXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, secondWPortLayoutCenterYPos), IS_BLACK);

        clickOn(secondClickXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, secondWPortLayoutCenterYPos), IS_RED);

        moveTo(sampleXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, secondWPortLayoutCenterYPos), IS_RED);

        clickOn(4, 4);
        waitAmoment();
    }


    /**
     * Tests the highlighting of overlapped equal-level edge and interlevel edge
     */
    @Test
    public void test03() throws InterruptedException {
        final KShapeLayout firstChildNodeLayout =
                viewContext.getViewModel().getChildren().get(0).getChildren().get(0)
                        .getData(KShapeLayout.class);
        final int firstChildNodeYPos = Math.round(firstChildNodeLayout.getYpos());

        final KShapeLayout secondWPortLayout =
                viewContext.getViewModel().getChildren().get(0).getPorts().get(0)
                .getData(KShapeLayout.class);
        final int secondWPortLayoutCenterYPos = Math.round(secondWPortLayout.getYpos()) + 3;

        final int sampleXPos = 350;
        final int firstClickXPos = 5 + 50 + 100 + 5 + 100 + 5
                + Math.round(1f/2f * 100f);
            // port width + border spacing + spacing + port width + node with + port width
                //   + edge spacing factor * spacing
        final int secondClickXPos = 5 + 50 + 100 + 5 + 100 + 5 + 20;
            // port width + border spacing + spacing + port width + node with + port width + 20
        waitAmoment();

        moveTo(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_BLACK);

        clickOn(firstClickXPos, firstChildNodeYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(firstClickXPos, firstChildNodeYPos), IS_RED);

        moveTo(sampleXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, secondWPortLayoutCenterYPos), IS_RED);

        moveTo(secondClickXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, secondWPortLayoutCenterYPos), IS_BLACK);

        clickOn(secondClickXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(secondClickXPos, secondWPortLayoutCenterYPos), IS_RED);

        moveTo(sampleXPos, secondWPortLayoutCenterYPos);
        waitAmoment();
        Assert.assertThat(getColorAt(sampleXPos, secondWPortLayoutCenterYPos), IS_RED);

        clickOn(4, 4);
    }


    private static final int A_MOMENT = 750;

    /**
     * Closes the employed shell.
     */
    @AfterClass
    public static void cleanup() {
        final Display display = shell.getDisplay();
        display.timerExec(A_MOMENT, new Runnable() {
            public void run() {
                shell.close();
            }
        });

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    private void moveTo(final int x, final int y) {
        final Display d = shell.getDisplay();

        final Event moveTo = new Event();
        moveTo.type = SWT.MouseMove;
        moveTo.x = zeroPoint.x + x;
        moveTo.y = zeroPoint.y + y;
        d.post(moveTo);

        while (d.readAndDispatch());
    }

    private void clickOn(final int x, final int y) {
        final Display d = shell.getDisplay();

        moveTo(x, y);

        final Event press = new Event();
        press.type = SWT.MouseDown;
        press.button = 1;
        d.post(press);

        while (d.readAndDispatch());

        final Event release = new Event();
        release.type = SWT.MouseUp;
        release.button = 1;
        d.post(release);

        while (d.readAndDispatch());
    }

    private void waitAmoment() throws InterruptedException {
        final Display d = shell.getDisplay();

        Thread.sleep(A_MOMENT);

        while (d.readAndDispatch());
    }

    private RGB getColorAt(final int x, final int y) {
        return ColorMatcher.getColorAt(canvas, x, y);
    }
}
