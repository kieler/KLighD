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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.GraphDataUtil;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewChangeListener;
import de.cau.cs.kieler.klighd.IViewChangeListener.ViewChange;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * Tests KLighD's view change notification mechanism, especially
 * {@link ViewChange#visibleDiagramNodes()} & {@link ViewChange#visibleDiagramsElements()}.
 *
 * @author chsch
 */
public class ViewChangedNotificationSuppressionTest {

    private static final int _40_MILLISECONDS =  40; // SUPPRESS CHECKSTYLE Name
    private static final int SHORT_WAIT_DELAY = 300;
    private static final int  LONG_WAIT_DELAY = 400;

    private Shell shell = null;
    private ViewContext viewContext = null;
    private int heightDelta = 0;
    private boolean secondListenerEmployed = false;

    // CHECKSTYLEOFF MagicNumber|Visibility|Javadoc


    /**
     * Prepares the test: creates a fresh {@link Shell} and attaches KLighD's diagramming
     * infrastructure initialized with the model provided by {@link #loadTestModel()}.
     */
    @Before
    public void prepare() {
        shell = new Shell(Display.getDefault());
        shell.setSize(300, 200);
        shell.setLocation(100, 100);
        shell.setLayout(new FillLayout());

        viewContext = new ViewContext((IDiagramWorkbenchPart) null, loadTestModel())
                .configure(new KlighdSynthesisProperties().useViewer(PiccoloViewer.ID));

        new ContextViewer(shell).setModel(viewContext, true);

        heightDelta = 200 - viewContext.getViewer().getControl().getSize().y;
        shell.setSize(300, 200 + heightDelta);

        GraphDataUtil.loadDataElements((KNode) viewContext.getInputModel());
        viewContext.update(null);

        // the zoom to fit causes the VIEW_PORT change events the listeners are waiting for
        viewContext.setZoomStyle(ZoomStyle.ZOOM_TO_FIT);
    }

    /**
     * Loads 'circuit.kgx' from within this bundle.
     *
     * @return the runtime representation of the test model.
     */
    private EObject loadTestModel() {
        final ResourceSet set = new ResourceSetImpl();

        final Iterator<URL> it =
                Iterators.forEnumeration(KlighdTestPlugin.getDefault().getBundle()
                        .findEntries("/", "circuit.kgx", true));
        if (!it.hasNext()) {
            Assert.fail("Test model 'circuit.kgx' could not be found!");
        }

        final Resource res = set.getResource(URI.createURI(it.next().toString(), true), true);
        return res.getContents().get(0);
    }

    /**
     * Process queued tasks delegated to the Display's thread, which is the one the tests are
     * executed, and clean up the employed {@link Shell}.
     */
    @After
    public void postProcess() {

        final int[] retry = new int[] {10};
        final Runnable wakeUp = new Runnable() {
            public void run() {
                retry[0]--;
            }
        };

        final Display d = shell.getDisplay();

        while (retry[0] != 0) {
            if (!d.readAndDispatch()) {
                d.timerExec(_40_MILLISECONDS, wakeUp);
                d.sleep();
            }
        }

        shell.close();
        shell.dispose();

        Assert.assertThat(observedNotifications01,
                IsIterableContainingInOrder.contains(expectedNotifications01.toArray()));

        if (secondListenerEmployed) {
            Assert.assertThat(observedNotifications02,
                    IsIterableContainingInOrder.contains(expectedNotifications02.toArray()));
        }
    }


    private Map<ViewChangeType, ArrayList<ViewChangeType>> suppressNotificationConfig01 =
            Maps.newHashMap();
    private ArrayList<ViewChangeType> expectedNotifications01 = null;

    private Map<ViewChangeType, Map<ViewChangeType, Integer>> suppressNotificationConfig02 =
            Maps.newHashMap();
    private ArrayList<ViewChangeType> expectedNotifications02 = null;

    private Iterable<ViewChangeType> observedNotifications01 = null;
    private Iterable<ViewChangeType> observedNotifications02 = null;

    /**
     * First {@link IViewChangeListener} being employed for testing the notification delivery.
     */
    private final IViewChangeListener listener1 = new IViewChangeListener() {
        private final List<ViewChangeType> changes;

        /* Constructor */ {
            this.changes = Collections.synchronizedList(Lists.<ViewChangeType>newArrayList());
            ViewChangedNotificationSuppressionTest.this.observedNotifications01 =
                    Iterables.unmodifiableIterable(changes);
        }

        public void viewChanged(final ViewChange change) {
            changes.add(change.getType());

            final ArrayList<ViewChangeType> suppressed =
                    suppressNotificationConfig01.get(change.getType());
            if (suppressed != null) {
                change.suppressSubsequentNotifications(suppressed.toArray(new ViewChangeType[0]));
                if (suppressed.size() == 2) {
                    change.suppressSubsequentViewPortChangeNotifications(2 * LONG_WAIT_DELAY);
                }
            }
        }
    };

    /**
     * Second {@link IViewChangeListener} being employed for testing the notification delivery.
     */
    private final IViewChangeListener listener2 = new IViewChangeListener() {
        private final List<ViewChangeType> changes;

        /* Constructor */ {
            this.changes = Collections.synchronizedList(Lists.<ViewChangeType>newArrayList());
            ViewChangedNotificationSuppressionTest.this.observedNotifications02 =
                    Iterables.unmodifiableIterable(changes);
        }

        public void viewChanged(final ViewChange change) {
            changes.add(change.getType());

            final Map<ViewChangeType, Integer> suppressed =
                    suppressNotificationConfig02.get(change.getType());

            if (suppressed != null) {
                for (final Map.Entry<ViewChangeType, Integer> entry : suppressed.entrySet()) {
                    change.suppressSubsequentNotifications(entry.getValue(), entry.getKey());
                }
            }
        }
    };


    private void employFirstListener() {
        viewContext.getViewer().addViewChangeListener(listener1, ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.CLIP);
    }

    private void employFirstThanSecond() {
        viewContext.getViewer().addViewChangeListener(listener1, ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.CLIP);

        viewContext.getViewer().addViewChangeListener(listener2, ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.CLIP);

        secondListenerEmployed = true;
    }

    private void employSecondThanFirst() {
        viewContext.getViewer().addViewChangeListener(listener2, ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.CLIP);

        viewContext.getViewer().addViewChangeListener(listener1, ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.CLIP);

        secondListenerEmployed = true;
    }

    private static final List<LayoutConfigurator> CONF = Lists.newArrayList();
    
    static {
        final LayoutConfigurator lc = new LayoutConfigurator();
        lc.configure(KGraphElement.class)
                .setProperty(CoreOptions.MIN_ANIM_TIME, _40_MILLISECONDS)
                .setProperty(CoreOptions.MAX_ANIM_TIME, _40_MILLISECONDS);
        CONF.add(lc);
    }

    private static final Function<ViewContext, Object> MODEL_QUERY =
            new Function<ViewContext, Object>() {
                public Object apply(final ViewContext input) {
                    return Iterables.getFirst(Iterables.skip(Iterables2.toIterable(Iterators.filter(
                            ((EObject) input.getInputModel()).eAllContents(), KNode.class)), 1), null);
                }
            };


    /**
     * Test on diagram with collapsed main circuit with notification due to layout + zoom to fit.
     */
    @Test
    public void test00() {
        employFirstListener();

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.COLLAPSE,
                ViewChangeType.VIEW_PORT, ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT);
        expectedNotifications02 = expectedNotifications01;

        final Object modelElement = MODEL_QUERY.apply(viewContext);

        viewContext.getViewer().collapse(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
        waitAMoment();

        viewContext.getViewer().expand(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
    }


    @Test
    public void test01a() {
        employFirstListener();

        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));

        expectedNotifications01 = Lists.newArrayList(
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT);

        final Object modelElement = MODEL_QUERY.apply(viewContext);

        viewContext.getViewer().collapse(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
        waitAMoment();

        viewContext.getViewer().expand(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
    }

    @Test
    public void test01b() {
        employFirstListener();

        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        expectedNotifications01 = Lists.newArrayList(
                ViewChangeType.COLLAPSE, ViewChangeType.VIEW_PORT, ViewChangeType.EXPAND);

        final Object modelElement = MODEL_QUERY.apply(viewContext);

        viewContext.getViewer().collapse(modelElement);

        LightDiagramServices.layoutDiagram(viewContext, false);
        waitAMoment();

        viewContext.getViewer().expand(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
    }

    @Test
    public void test01c() {
        employFirstListener();

        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        expectedNotifications01 = Lists.newArrayList(
                ViewChangeType.COLLAPSE, ViewChangeType.EXPAND);

        final Object modelElement = MODEL_QUERY.apply(viewContext);

        viewContext.getViewer().collapse(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
        waitAMoment();

        viewContext.getViewer().expand(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, false);
    }


    public void test02base() {
        final Object modelElement = MODEL_QUERY.apply(viewContext);

        shell.open();
        LightDiagramServices.layoutDiagram(viewContext, false, ZoomStyle.ZOOM_TO_FIT);
        waitALongMoment();

        viewContext.getViewer().collapse(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, CONF);
        waitALongMoment();

        viewContext.getViewer().clip(modelElement);
        viewContext.getViewer().zoom(ZoomStyle.ZOOM_TO_FIT, _40_MILLISECONDS);
        // LightDiagramServices.zoomDiagram(viewContext);
        waitALongMoment();

        viewContext.getViewer().expand(modelElement);
        LightDiagramServices.layoutDiagram(viewContext, CONF);
        waitALongMoment();

        viewContext.getViewer().clip(null);
        viewContext.getViewer().zoom(ZoomStyle.ZOOM_TO_FIT, _40_MILLISECONDS);
        // LightDiagramServices.zoomDiagram(viewContext);
    }

    @Test
    public void test02a() {
        employFirstListener();

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT,
                ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test02base();
    }

    @Test
    public void test02b() {
        employFirstListener();

        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.CLIP, ViewChangeType.VIEW_PORT,
                ViewChangeType.EXPAND, ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test02base();
    }

    @Test
    public void test02c() {
        employFirstListener();

        suppressNotificationConfig01.put(
                ViewChangeType.CLIP, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.CLIP,
                ViewChangeType.EXPAND, ViewChangeType.CLIP);

        test02base();
    }

    @Test
    public void test02d() {
        employFirstListener();

        suppressNotificationConfig01.put(ViewChangeType.CLIP,
                Lists.newArrayList(ViewChangeType.VIEW_PORT, ViewChangeType.EXPAND));
        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.CLIP, ViewChangeType.CLIP);

        test02base();
    }


    private boolean secondThanFirst = false;

    public void test03base() {
        if (secondThanFirst) {
            employSecondThanFirst();
        } else {
            employFirstThanSecond();
        }

        suppressNotificationConfig01.put(
                ViewChangeType.COLLAPSE, Lists.newArrayList(ViewChangeType.VIEW_PORT));
        suppressNotificationConfig01.put(
                ViewChangeType.EXPAND, Lists.newArrayList(ViewChangeType.VIEW_PORT));

        expectedNotifications01 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT,
                ViewChangeType.EXPAND,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test02base();
    }

    @Test
    public void test03a() {
        suppressNotificationConfig02.put(
                ViewChangeType.COLLAPSE, ImmutableMap.of(ViewChangeType.VIEW_PORT, LONG_WAIT_DELAY));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT,
                ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test03base();
    }

    @Test
    public void test03b() {
        secondThanFirst = true;
        test03a();
    }

    @Test
    public void test03c() {
        suppressNotificationConfig02.put(
                ViewChangeType.COLLAPSE, ImmutableMap.of(ViewChangeType.VIEW_PORT, 2 * LONG_WAIT_DELAY));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,
                ViewChangeType.CLIP,
                ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test03base();
    }

    @Test
    public void test03d() {
        secondThanFirst = true;
        test03c();
    }

    @Test
    public void test03e() {
        suppressNotificationConfig02.put(
                ViewChangeType.COLLAPSE, ImmutableMap.of(ViewChangeType.VIEW_PORT, 4 * LONG_WAIT_DELAY));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,
                ViewChangeType.CLIP,
                ViewChangeType.EXPAND,
                ViewChangeType.CLIP);

        test03base();
    }

    @Test
    public void test03f() {
        secondThanFirst = true;
        test03e();
    }

    @Test
    public void test03g() {
        suppressNotificationConfig02.put(
                ViewChangeType.CLIP, ImmutableMap.of(ViewChangeType.VIEW_PORT, LONG_WAIT_DELAY));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP,
                ViewChangeType.EXPAND, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP);

        test03base();
    }

    @Test
    public void test03h() {
        secondThanFirst = true;
        test03g();
    }

    @Test
    public void test03i() {
        suppressNotificationConfig02.put(
                ViewChangeType.CLIP, ImmutableMap.of(ViewChangeType.VIEW_PORT, 2 * LONG_WAIT_DELAY));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP,
                ViewChangeType.EXPAND,
                ViewChangeType.CLIP);

        test03base();
    }

    @Test
    public void test03j() {
        secondThanFirst = true;
        test03i();
    }

    @Test
    public void test03k() {
        suppressNotificationConfig02.put(
                ViewChangeType.CLIP, ImmutableMap.of(
                        ViewChangeType.VIEW_PORT, 2 * LONG_WAIT_DELAY, ViewChangeType.EXPAND, 0));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE, ViewChangeType.VIEW_PORT,
                ViewChangeType.CLIP,

                ViewChangeType.CLIP);

        test03base();
    }

    @Test
    public void test03l() {
        secondThanFirst = true;
        test03k();
    }

    @Test
    public void test03m() {
        suppressNotificationConfig02.put(
                ViewChangeType.COLLAPSE, ImmutableMap.of(
                        ViewChangeType.VIEW_PORT, 3 * LONG_WAIT_DELAY,
                        ViewChangeType.EXPAND, 2 * LONG_WAIT_DELAY,
                                               // COLLAPSE to EXPAND takes more than 2sec ...
                        ViewChangeType.CLIP, 0));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,

                ViewChangeType.EXPAND,  // ... hence the notification must occur
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test03base();
    }

    @Test
    public void test03n() {
        secondThanFirst = true;
        test03m();
    }

    @Test
    public void test03o() {
        suppressNotificationConfig02.put(
                ViewChangeType.COLLAPSE, ImmutableMap.of(
                        ViewChangeType.VIEW_PORT, 3 * LONG_WAIT_DELAY,
                        ViewChangeType.EXPAND, 3 * LONG_WAIT_DELAY,
                        ViewChangeType.CLIP, 0));

        expectedNotifications02 = Lists.newArrayList(ViewChangeType.VIEW_PORT,
                ViewChangeType.COLLAPSE,

                // because of the 3 seconds the EXPAND notification is blocked here
                ViewChangeType.CLIP, ViewChangeType.VIEW_PORT);

        test03base();
    }

    @Test
    public void test03p() {
        secondThanFirst = true;
        test03o();
    }


    private void waitAMoment() {
        waitAmoment(SHORT_WAIT_DELAY);
    }

    private void waitALongMoment() {
        waitAmoment(LONG_WAIT_DELAY);
    }

    private void waitAmoment(final int time) {
        // SUPPRESS CHECKSTYLE NEXT 20 Braces|EmptyStatement|MagicNumber

        final Display d = shell.getDisplay();
        final boolean[] doSleep = new boolean[] {true};
        d.timerExec(time, new Runnable() {
            public void run() {
                doSleep[0] = false;
            }
        });

        while (doSleep[0]) {
            if (!d.readAndDispatch()) {
                // System.out.println("sleep...");
                d.sleep();
            // } else {
                // System.out.println("more work");
            }
        }
        while (d.readAndDispatch());
    }
}
