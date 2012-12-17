/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.views;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.options.OptionControlFactory;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 * @author msp
 */
public class DiagramViewPart extends ViewPart {

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";
    
    /** the viewer for this view part. */
    private ContextViewer viewer;
    /** the factory for option controls. */
    private OptionControlFactory optionControlFactory;
    /** controller for the expanded options pane. */
    private final PaneController expandedController = new PaneController();
    /** controller for the collapsed options pane. */
    private final PaneController collapsedController = new PaneController();
    /** the form toolkit. */
    private FormToolkit formToolkit;
    /** the set of resources to be disposed when the view is closed. */
    private final Collection<Resource> resources = new LinkedList<Resource>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {        
        // add buttons to the view toolbar 
        addButtons();
        
        // create the context viewer
        Composite diagramContainer = new Composite(parent, SWT.NONE);
        viewer = new ContextViewer(diagramContainer, getViewSite().getSecondaryId(), this);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.verticalSpan = 2;
        diagramContainer.setLayoutData(gridData);
        diagramContainer.setLayout(new FillLayout());
        
        // create the options pane
        createOptionsContainer(parent);
        
        // install a drop handler for the view (XXX this could be omitted)
        installDropHandler(diagramContainer);
        viewer.setModel("No model selected.", false);
        
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
    }
    
    /**
     * Update the options to be displayed in the options pane.
     * TODO make the selection of options configurable through method arguments
     */
    public void updateOptions() {
        // remove any option controls that have been created before
        optionControlFactory.clear();
        // initialize a layout configuration for retrieving default values
        optionControlFactory.initialize();
        
        // TODO implement a generic interface for selecting layout options
        optionControlFactory.createControl(LayoutOptions.ALGORITHM.getId());
        optionControlFactory.createControl(LayoutOptions.SPACING.getId(), 3f, 200f);
        optionControlFactory.createControl(LayoutOptions.RANDOM_SEED.getId(), 1f, 100f);
        optionControlFactory.createControl(LayoutOptions.DIRECTION.getId(),
                EnumSet.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN));
        
        // TODO make this configurable, too
        collapsedController.setVisible(false);
        expandedController.setVisible(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        if (formToolkit != null) {
            formToolkit.dispose();
            formToolkit = null;
        }
        for (Resource res : resources) {
            res.dispose();
        }
        resources.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * Returns the context viewer represented by this view part.
     * 
     * @return the context viewer
     */
    public ContextViewer getContextViewer() {
        return viewer;
    }

    /**
     * Sets a new name for the view.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        setPartName(name);
    }
    
    /**
     * A simple paint listener that draws a vertical line.
     */
    private final class LinePainter implements PaintListener {
        public void paintControl(final PaintEvent event) {
            Point size = ((Control) event.widget).getSize();
            event.gc.setForeground(Display.getCurrent().getSystemColor(
                    SWT.COLOR_WIDGET_NORMAL_SHADOW));
            event.gc.drawLine(1, 0, 1, size.y);
        }
    }
    
    /**
     * The initial width of the option pane.
     */
    private static final int DEFAULT_PALETTE_WIDTH = 150;
    /**
     * The minimal width of the option pane and the diagram viewer.
     */
    private static final int MIN_WIDTH = 60;
    
    /**
     * Create the container for layout options, including controls for collapsing and expanding.
     * 
     * @param parent the parent composite into which controls are created
     */
    private void createOptionsContainer(final Composite parent) {
        // create the right arrow for collapsing the options pane
        Label rightArrowLabel = new Label(parent, SWT.NONE);
        GridData rightArrowLayoutData = new GridData(SWT.CENTER, SWT.TOP, false, false);
        rightArrowLabel.setLayoutData(rightArrowLayoutData);
        Image rightArrow = KlighdPlugin.getImageDescriptor("icons/arrow-right.gif").createImage();
        resources.add(rightArrow);
        rightArrowLabel.setImage(rightArrow);
        
        // create container for options
        formToolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm form = formToolkit.createScrolledForm(parent);
        form.setText("Options");
        final GridData formLayoutData = new GridData(SWT.FILL, SWT.FILL, false, true);
        formLayoutData.widthHint = DEFAULT_PALETTE_WIDTH;
        formLayoutData.verticalSpan = 2;
        form.setLayoutData(formLayoutData);
        Composite optionsContainer = form.getBody();
        optionsContainer.setLayout(new GridLayout(2, false));
        
        // create the factory for option controls to fill the options container
        optionControlFactory = new OptionControlFactory(optionsContainer, this, formToolkit);
        
        // create the left arrow for expanding the options pane
        Label leftArrowLabel = new Label(parent, SWT.NONE);
        GridData leftArrowLayoutData = new GridData(SWT.CENTER, SWT.TOP, false, false);
        leftArrowLayoutData.horizontalSpan = 2;
        leftArrowLabel.setLayoutData(leftArrowLayoutData);
        Image leftArrow = KlighdPlugin.getImageDescriptor("icons/arrow-left.gif").createImage();
        resources.add(leftArrow);
        leftArrowLabel.setImage(leftArrow);
        
        // create the sash for resizing the options pane
        Sash sash = new Sash(parent, SWT.VERTICAL);
        GridData sashLayoutData = new GridData(SWT.CENTER, SWT.FILL, false, true);
        sash.setLayoutData(sashLayoutData);
        sash.addPaintListener(new LinePainter());
        sash.addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                if (event.detail == SWT.DRAG) {
                    // FIXME the "30" in the next line was determined experimentally
                    int newWidth = parent.getClientArea().width - (event.x + 30);
                    if (event.x > MIN_WIDTH && newWidth > MIN_WIDTH) {
                        formLayoutData.widthHint = newWidth;
                        parent.layout();
                    }
                }
            }
        });
        
        // create a line below the expansion arrow
        Composite dummyLine = new Composite(parent, SWT.NONE);
        GridData dummyLineLayoutData = new GridData(SWT.CENTER, SWT.FILL, false, true);
        dummyLineLayoutData.widthHint = 3;  // SUPPRESS CHECKSTYLE MagicNumber
        dummyLineLayoutData.horizontalSpan = 2;
        dummyLine.setLayoutData(dummyLineLayoutData);
        dummyLine.addPaintListener(new LinePainter());
        
        // create controllers for collapsing and expanding
        expandedController.controls = new Control[] {
                rightArrowLabel, sash, form
        };
        expandedController.layoutData = new GridData[] {
                rightArrowLayoutData, sashLayoutData, formLayoutData
        };
        expandedController.setVisible(false);
        collapsedController.controls = new Control[] {
                leftArrowLabel, dummyLine
        };
        collapsedController.layoutData = new GridData[] {
                leftArrowLayoutData, dummyLineLayoutData
        };
        collapsedController.setVisible(false);
        
        // register actions for the collapse / expand labels
        rightArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                expandedController.setVisible(false);
                collapsedController.setVisible(true);
                parent.layout();
            }
        });
        leftArrowLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent event) {
                collapsedController.setVisible(false);
                expandedController.setVisible(true);
                parent.layout();
            }
        });
        
        // set the grid layout of the parent container
        GridLayout gridLayout = new GridLayout(3, false); // SUPPRESS CHECKSTYLE MagicNumber
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        parent.setLayout(gridLayout);
    }
    
    /**
     * A controller class for easy collapsing and expanding.
     */
    private class PaneController {
        
        /** the controls that are made visible or invisible. */
        private Control[] controls;
        /** the layout data of the controls, used to exclude the controls from the grid. */
        private GridData[] layoutData;
        
        /**
         * Make the contained controls visible or invisible.
         * 
         * @param visible {@code true} to make all controls visible, or {@code false} to make
         *              them all invisible
         */
        void setVisible(final boolean visible) {
            for (Control c : controls) {
                c.setVisible(visible);
            }
            for (GridData ld : layoutData) {
                ld.exclude = !visible;
            }
        }
    }

    /**
     * Installs a handler for dropping resources on the view.
     */
    private void installDropHandler(final Composite parent) {
        DropTarget target = new DropTarget(parent, DND.DROP_COPY | DND.DROP_DEFAULT);
        final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
        target.setTransfer(new Transfer[] { resourceTransfer });
        target.addDropListener(new DropTargetListener() {

            public void drop(final DropTargetEvent event) {
                KlighdResourceDropTrigger trigger = KlighdResourceDropTrigger.getInstance();
                if (trigger != null) {
                    if (resourceTransfer.isSupportedType(event.currentDataType)
                            && event.data instanceof IResource[]) {
                        IResource[] resources = (IResource[]) event.data;
                        if (resources.length > 0) {
                            KlighdResourceDropState state = new KlighdResourceDropState(
                                    getViewSite().getSecondaryId(), resources[0]);
                            trigger.trigger(state);
                        }
                    }
                }
            }

            public void dropAccept(final DropTargetEvent event) {
                // do nothing
            }

            public void dragOperationChanged(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragEnter(final DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragOver(final DropTargetEvent event) {
                // do nothing
            }

            public void dragLeave(final DropTargetEvent event) {
                // do nothing
            }

        });
    }
    

    private void addButtons() {
        this.getViewSite()
                .getActionBars()
                .getToolBarManager()
                .add(new Action("Refresh diagram", KlighdPlugin
                        .getImageDescriptor("icons/full/elcl16/refresh.gif")) {
                    public void runWithEvent(final Event event) {
                        DiagramViewManager.getInstance().updateView(viewer.getViewPartId());
                    }
                });
//        DiagramViewPart.this.getViewSite().getActionBars().getToolBarManager()
//                .add(new Action("Zoom to fit", IAction.AS_CHECK_BOX) {
//                    public void run() {
//                    }
//                });
        new LayoutAction("Arrange", KimlUiPlugin.getImageDescriptor("icons/menu16/kieler-arrange.gif"));
        new LayoutAction("Arrange rightward", Direction.RIGHT, "icons/full/elcl16/forward_nav.gif");
        new LayoutAction("Arrange downward", Direction.DOWN, "icons/full/elcl16/downward_nav.gif");
        new LayoutAction("Arrange upward", Direction.UP, "icons/full/elcl16/upward_nav.gif");
        new LayoutAction("Arrange leftward", Direction.LEFT, "icons/full/elcl16/backward_nav.gif");
        new LayoutAction("Arrange circular",
                "de.cau.cs.kieler.algorithm=de.cau.cs.kieler.graphviz.circo",
                "icons/full/elcl16/refresh.gif");
    }

    /**
     * LayoutActionButtonContribution.
     * Specialized Button to be attached to the local toolbar of the KLighD view part
     * offering the application of the automatic layout wrt. a given configuration (e.g. direction).
     * 
     * @author chsch
     */
    private class LayoutAction extends Action {

        private Direction dir = null;
        private String keyValue = null;

        public LayoutAction(final String text, final ImageDescriptor image) {
            super(text, image);
            DiagramViewPart.this.getViewSite().getActionBars().getToolBarManager().add(this);
        }

        public LayoutAction(final String text, final String keyValue, final String imagePath) {
            this(text, keyValue, KlighdPlugin.getImageDescriptor(imagePath));
        }
        
        public LayoutAction(final String text, final String keyValue, final ImageDescriptor image) {
            super(text, image);
            this.keyValue = keyValue;
            DiagramViewPart.this.getViewSite().getActionBars().getToolBarManager().add(this);
        }

        public LayoutAction(final String text, final Direction d, final String imagePath) {
            this(text, d, KlighdPlugin.getImageDescriptor(imagePath));
        }

        public LayoutAction(final String text, final Direction d, final ImageDescriptor image) {
            super(text, image);
            this.dir = d;
            DiagramViewPart.this.getViewSite().getActionBars().getToolBarManager().add(this);
        }

        /**
         * {@inheritDoc}
         * 
         * The <code>run</code> method of the button action. Triggers the automatic layout
         * and provides a related {@link ButtonLayoutConfig}. 
         */
        public void run() {
            final DiagramViewPart view = DiagramViewPart.this;
            try {
                List<ILayoutConfig> options = null;
                if (this.dir != null) {
                    options = ImmutableList.<ILayoutConfig>of(new ButtonLayoutConfig(this.dir));
                } else if (!Strings.isNullOrEmpty(this.keyValue)) {
                    int index = keyValue.indexOf('=');
                    LayoutOptionData<?> data = LayoutDataService.getInstance().getOptionData(
                            keyValue.substring(0, index));
                    options = ImmutableList.<ILayoutConfig>of(new ButtonLayoutConfig(data,
                            keyValue.substring(index + 1)));
                }
                DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
                layoutEngine.layout(view, null, true, false, false, true, options);
            } catch (UnsupportedOperationException e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID,
                                "Performing automatic layout on view content of "
                                        + view.getPartName() + " failed with an Exception.", e));
            }
        }
    }

    /**
     * ButtonLayoutConfig.
     * 
     * Special layout configuration handed over to the {@link DiagramLayoutEngine} if the
     * layout is invoked by the above introduced buttons.
     * 
     * @author chsch
     */
    private static class ButtonLayoutConfig implements ILayoutConfig {

        private Direction dir;
        private LayoutOptionData<?> option;
        private String value;

        public ButtonLayoutConfig(final Direction d) {
            this.dir = d;
        }

        public ButtonLayoutConfig(final LayoutOptionData<?> data, final String val) {
            this.option = data;
            this.value = val;
        }

        public int getPriority() {
            return Integer.MAX_VALUE; 
        }

        public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
            // method appears to be effectless in this case
            return null;
        }

        public void enrich(final LayoutContext context) {
            // method appears to be effectless in this case
        }

        public void transferValues(final KGraphData graphData, final LayoutContext context) {
            Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
              // This is the old Pictogram-Piccolo-based version:
              //  if (diagramPart.getClass().getCanonicalName().endsWith("DiagramNode")) {
              // KRendering-based diagrams consist of KNodes, the layout option is
              //  attached to the root one (with no container) only.
              if (KGraphPackage.eINSTANCE.getKNode().isInstance(diagramPart)
                      && ((KNode) diagramPart).eContainer() == null) {
                if (this.dir != null) {
                    graphData.setProperty(LayoutOptions.DIRECTION, this.dir);
                }
                if (this.option != null) {
                    graphData.setProperty(this.option, this.value);
                }
            }
        }
    }
}
