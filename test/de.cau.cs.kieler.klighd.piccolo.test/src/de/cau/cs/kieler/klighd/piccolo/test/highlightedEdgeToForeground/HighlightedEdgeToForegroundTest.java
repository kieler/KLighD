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
// SUPPRESS CHECKSTYLE PREVIOUS Package

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
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
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewerProvider;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

// CHECKSTYLEOFF MagicNumber

/**
 * @author chsch
 */
public class HighlightedEdgeToForegroundTest {

    private static HighlightedEdgeToForegroundTestModelGen testModelGen;
    private static ViewContext viewContext;
    
    private static Shell shell;
    private static KlighdCanvas canvas;
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
                .configure(new KlighdSynthesisProperties().useViewer(PiccoloViewerProvider.ID));
        
        new ContextViewer(shell).setModel(viewContext, true);        

        viewContext.update(null);
        LightDiagramServices.layoutDiagram(viewContext, false, ZoomStyle.ZOOM_TO_ACTUAL_SIZE);
        
        shell.layout(true, true);
        shell.open();
        
        canvas = (KlighdCanvas) viewContext.getViewer().getControl();
        zeroPoint = canvas.toDisplay(0, 0);
    }

    /**
     * The first test.
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

        Thread.sleep(1000);

        clickOn(5 + 100 + 1, // port width + border spacing + edge spacing factor * spacing + 1
            firstChildNodeYPos);

        Thread.sleep(1000);

        moveTo(50, firstWPortLayoutCenterYPos);

        Thread.sleep(1000);

        Assert.assertTrue(areSimilar(Colors.RED, getColorAt(50, firstWPortLayoutCenterYPos)));

        clickOn(200, firstWPortLayoutCenterYPos);

        Thread.sleep(1000);

        moveTo(50, firstWPortLayoutCenterYPos);

        Assert.assertTrue(areSimilar(Colors.RED, getColorAt(50, firstWPortLayoutCenterYPos)));
    }


    /**
     * The second test.
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

        Thread.sleep(1000);

        clickOn(5 + 100 + 1, // port width + border spacing + edge spacing factor * spacing + 1
            firstChildNodeYPos);
        
        Thread.sleep(1000);

        moveTo(120, secondWPortLayoutCenterYPos);
        
        Thread.sleep(1000);

        Assert.assertTrue(areSimilar(Colors.RED, getColorAt(120, secondWPortLayoutCenterYPos)));
        
        clickOn(50, secondWPortLayoutCenterYPos);
        
        Thread.sleep(1000);

        moveTo(120, secondWPortLayoutCenterYPos);
        
        Assert.assertTrue(areSimilar(Colors.RED, getColorAt(120, secondWPortLayoutCenterYPos)));
    }


    /**
     * Closes the employed shell.
     */
    @AfterClass
    public static void cleanup() {
        final Display display = shell.getDisplay();
        display.timerExec(1000, new Runnable() {
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

    private RGB getColorAt(final int x, final int y) {
        final Image image = new Image(shell.getDisplay(), 1, 1);
        final GC gc = new GC(canvas);
        gc.copyArea(image, x, y);
        gc.dispose();

        final ImageData iData = image.getImageData();
        image.dispose();

        final RGB pixel = iData.palette.getRGB(iData.getPixel(0, 0));
        return pixel;
    }

    private boolean areSimilar(final Colors color, final RGB rgb) {
        return Math.abs(color.getRed() - rgb.red) < 11
                && Math.abs(color.getGreen() - rgb.green) < 11
                && Math.abs(color.getBlue() - rgb.blue) < 11;
    }
}
