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

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
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
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A view which is able to display models in light-weight diagrams.
 * 
 * @author mri
 */
public class DiagramViewPart extends ViewPart {

    /** the default name for this view. */
    public static final String DEFAULT_NAME = "Light Diagram";

    /** the viewer for this view part. */
    private ContextViewer viewer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        addLayoutButton();
        // create a context viewer
        viewer = new ContextViewer(parent, getViewSite().getSecondaryId());
        // install a drop handler for the view
        installDropHandler(parent);
        viewer.setModel("No model selected.", false);
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
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

    private void addLayoutButton() {
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
                layoutEngine.layout(view, null, true, false, false, false, options);
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
    private class ButtonLayoutConfig implements ILayoutConfig {

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
