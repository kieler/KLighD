/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.test;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewChangeListener;
import de.cau.cs.kieler.klighd.IViewChangeListener.ViewChange;
import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * Tests KLighD's view change notification mechanism, especially
 * {@link ViewChange#visibleDiagramNodes()} and {@link ViewChange#visibleDiagramElements()}.
 *
 * @author chsch
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewChangedNotificationTest {

    private static final boolean DEBUG_SYS_OUT = false;

    private Shell shell = null;
    private ViewContext viewContext = null;
    private AssertionError failure = null;
    private int heightDelta = 0;

    // CHECKSTYLEOFF MagicNumber

    @BeforeClass
    public static void lookForElkLayered() {
        Assert.assertNotNull(
                "ELK Layered is not on the classpath, but it's required for properly executing the tests.",
                LayoutMetaDataService.getInstance().getAlgorithmDataBySuffix(DiagramLayoutOptions.ELK_LAYERED)
        );
    }

    /**
     * Prepares the test: creates a fresh {@link Shell} and attaches KLighD's diagramming
     * infrastructure initialized with the model provided by {@link KlighdTestPlugin#loadTestModel()}.
     */
    @Before
    public void prepare() {
        // chsch: on my windows 10 vm the minimal width of dialogs with title and/or close buttons is 136
        //  which is too high for some tests below 100, so I request the modelless style here
        // however, on linux the SWT.RESIZE flag is required to successfully run the tests 4-5b, so...
        shell = new Shell(Display.getDefault(), SWT.RESIZE);
        shell.setSize(400, 200);
        shell.setLocation(100, 100);
        shell.setLayout(new FillLayout());

        viewContext = new ViewContext((IDiagramWorkbenchPart) null, KlighdTestPlugin.loadTestModel())
                .configure(new KlighdSynthesisProperties().useViewer(PiccoloViewer.ID));

        new ContextViewer(shell).setModel(viewContext, true);

        heightDelta = 200 - viewContext.getViewer().getControl().getSize().y;
        shell.setSize(400, 200 + heightDelta);

        viewContext.update(null);

        // the zoom to fit causes the VIEW_PORT change events the listener is waiting for
        viewContext.setZoomStyle(ZoomStyle.ZOOM_TO_FIT);
        viewContext.getViewer().addViewChangeListener(listener, ViewChangeType.VIEW_PORT);

        shell.open();

        failure = null;
    }

    @Rule
    public TestName name= new TestName();

    /**
     * Process queued tasks delegated to the Display's thread, which is the one the tests are
     * executed, and clean up the employed {@link Shell}.
     */
    @After
    public void postProcess() {
        new LightDiagramLayoutConfig(viewContext)
            .animate(false).zoomStyle(ZoomStyle.ZOOM_TO_ACTUAL_SIZE)
            .performLayout();

        final int[] retry = new int[] {10};
        final Runnable wakeUp = new Runnable() {
            public void run() {
                retry[0]--;
            }
        };

        final Display d = shell.getDisplay();

        while (retry[0] != 0) {
            if (!d.readAndDispatch()) {
                d.timerExec(40, wakeUp);
                d.sleep();
            }
        }
        while (d.readAndDispatch());

        shell.close();

        if (failure != null) {
            throw failure;
        }
    }

    /**
     * Denominates whether to count only diagram nodes or all visible diagram elements.
     */
    private boolean countNodesOnly = true;

    /**
     * Field to be configured in test methods, is read by {@link #listener}.
     */
    private int expectedElementsNumber = 0;

    /**
     * The {@link IViewChangeListener} being employed for testing
     * {@link ViewChange#visibleDiagramNodes()}.
     */
    private final IViewChangeListener listener = new IViewChangeListener() {

        public void viewChanged(final ViewChange change) {

            final Iterable<KGraphElement> l = Sets.<KGraphElement>newLinkedHashSet(
                () -> countNodesOnly ? Iterators.<KGraphElement>filter(change.visibleDiagramNodes(), KGraphElement.class) : change.visibleDiagramElements()
            );
            
            if (DEBUG_SYS_OUT) {
                System.out.println(name.getMethodName() + ":\n" + String.join("\n", Iterables.transform(l, e -> e.toString())));
            }
            
            try {
                MatcherAssert.assertThat("", l,
                        IsIterableWithSize.<KGraphElement>iterableWithSize(expectedElementsNumber));
            } catch (final AssertionError e) {
                failure = e;
            }
        }
    };

    /**
     * Test on whole diagram with notification due to layout + zoom to fit.
     */
    @Test
    public void test01() {
        expectedElementsNumber = 6; // SUPPRESS CHECKSTYLE MagicNumber
    }

    /**
     * Test on whole diagram with notification due to layout + zoom to fit.
     */
    @Test
    public void test01b() {
        countNodesOnly = false;
        expectedElementsNumber = 44;
    }


    /**
     * Test on diagram with collapsed main circuit with notification due to layout + zoom to fit.
     */
    @Test
    public void test02() {
        viewContext.getViewer().collapse(
                (Object) Iterables.getFirst(Iterables.filter(
                        ((EObject) viewContext.getInputModel()).eContents(), KNode.class), null));
        expectedElementsNumber = 2;
    }

    /**
     * Test on diagram with collapsed main circuit with notification due to layout + zoom to fit.
     */
    @Test
    public void test02b() {
        viewContext.getViewer().collapse(
                (Object) Iterables.getFirst(Iterables.filter(
                        ((EObject) viewContext.getInputModel()).eContents(), KNode.class), null));
        countNodesOnly = false;
        expectedElementsNumber = 4;
    }


    /**
     * Test on diagram with collapsed main circuit with notification due to layout + zoom to fit.
     */
    @Test
    public void test03() {
        viewContext.getViewer().collapse(
                (Object) ((KNode) viewContext.getInputModel()).getChildren().get(0).getChildren()
                        .get(0));
        expectedElementsNumber = 4;
    }

    /**
     * Test on diagram with collapsed main circuit with notification due to layout + zoom to fit.
     */
    @Test
    public void test03b() {
        viewContext.getViewer().collapse(
                (Object) ((KNode) viewContext.getInputModel()).getChildren().get(0).getChildren()
                        .get(0));
        countNodesOnly = false;
        expectedElementsNumber = 24;
    }


    /**
     * Test on whole diagram with notification due to canvas resizing.
     */
    @Test
    public void test04() {
        viewContext.setZoomStyle(ZoomStyle.NONE);
        expectedElementsNumber = 3;
        shell.setSize(110, shell.getSize().y);
    }

    /**
     * Test on whole diagram with notification due to canvas resizing.
     */
    @Test
    public void test04b() {
        viewContext.setZoomStyle(ZoomStyle.NONE);
        countNodesOnly = false;
        expectedElementsNumber = 12;
        shell.setSize(110, shell.getSize().y);
    }


    /**
     * Further test on whole diagram with notification due to canvas resizing.
     */
    @Test
    public void test05() {
        viewContext.setZoomStyle(ZoomStyle.NONE);
        expectedElementsNumber = 4;
        if (Klighd.IS_MACOSX)
            shell.setSize(130, shell.getSize().y);
        else
            shell.setSize(140, shell.getSize().y);
    }

    /**
     * Further test on whole diagram with notification due to canvas resizing.
     */
    @Test
    public void test05b() {
        viewContext.setZoomStyle(ZoomStyle.NONE);
        countNodesOnly = false;
        expectedElementsNumber = 23;
        if (Klighd.IS_MACOSX)
            shell.setSize(130, shell.getSize().y);
        else
            shell.setSize(140, shell.getSize().y);
    }
}
