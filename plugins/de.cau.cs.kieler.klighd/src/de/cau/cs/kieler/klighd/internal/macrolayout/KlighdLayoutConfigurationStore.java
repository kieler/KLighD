/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal.macrolayout;

import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.data.LayoutOptionData.Target;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.ILayoutConfigurationStore;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KLabeledGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.KPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyValueProxy;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.AbstractRunnableWithResult;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.util.RunnableWithResult;

// MIGRATE This whole class is a pile of poo and needs to be cleaned up if everything is working.
/**
 * A layout configuration which derives layout options from properties attached to layout data of
 * graph elements.
 *
 * @author mri
 * @author msp
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdLayoutConfigurationStore implements ILayoutConfigurationStore {

    /**
     * Provider for KLighD layout configuration stores.
     */
    public static final class Provider implements ILayoutConfigurationStore.Provider {

        @Override
        public ILayoutConfigurationStore get(final IWorkbenchPart workbenchPart, final Object context) {
            if (context instanceof KGraphElement) {
                try {
                    return new KlighdLayoutConfigurationStore(workbenchPart, (KGraphElement) context);
                } catch (IllegalArgumentException e) {
                    // Fall back to null
                }
            }
            return null;
        }
    }

    /** The {@link IWorkbenchPart} attached to this context. */
    private final IWorkbenchPart workbenchPart;

    /** The view model part used as context for this configuration store. */
    private final KGraphElement graphElement;

    /**
     * Create a KLighD layout configuration store.
     * 
     * @param workbenchPart
     *            The {@link IWorkbenchPart} attached to this context.
     * @param context
     *            The {@link KGraphElement} of the view model this {@link ILayoutConfigurationStore}
     *            is attached to.
     */
    public KlighdLayoutConfigurationStore(final IWorkbenchPart workbenchPart,
            final KGraphElement context) {
        this.workbenchPart = workbenchPart;
        this.graphElement = context;
    }

    /**
     * {@inheritDoc}
     */
    public Object getOptionValue(final String optionId) {
        LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(optionId);

        if (optionData == null || graphElement == null) {
            return null;
        }

        final KLayoutData elementLayout = graphElement.getData(KLayoutData.class);
        if (elementLayout == null) {
            return null;
        }

        final Object value = elementLayout.getProperties().get(optionData);
        if (value instanceof IPropertyValueProxy) {
            return ((IPropertyValueProxy) value).resolveValue(optionData);

        } else if (value == null) {
            // check whether an expansion aware layout option set is present
            final ExpansionAwareLayoutOptionData ealo =
                    elementLayout.getProperty(ExpansionAwareLayoutOption.OPTION);
            
            if (ealo == null) {
                // We provide special support for certain layout options
                return getSpecialLayoutOptionValue(optionData);
            } else {
                final KNode node;
                if (graphElement instanceof KNode) {
                    node = (KNode) graphElement;
                    
                } else if (graphElement instanceof KPort) {
                    node = ((KPort) graphElement).getNode();
                    
                } else {
                    return null;
                }
                
                final RenderingContextData rcd = RenderingContextData.get(node);
                final boolean expanded = !node.getChildren().isEmpty()
                        && rcd.getProperty(KlighdInternalProperties.POPULATED);
                
                return ealo.getValue(optionData, expanded);
            }
        } else {
            return value;
        }
    }

     /** The aspect ratio is rounded to two decimal places. */
     private static final float ASPECT_RATIO_ROUND = 100;

    /**
     * We support special layout options whose value we infer if they are not explicitly set on the
     * element. This method assumes that this is indeed the case and returns the special layout option
     * value, if any.
     * 
     * @param optionData the option whose value to return.
     * @return the value or {@code null} if it is not a special layout option.
     */
    private Object getSpecialLayoutOptionValue(final LayoutOptionData optionData) {
        if (optionData.equals(CoreOptions.ASPECT_RATIO) && getContainer() == null) {
            // Get aspect ratio for the current diagram
            final IViewer viewer = getViewer();
            if (viewer == null || viewer.getControl() == null) {
                System.out.println("NO VIEWER");
                return null;
            }

            final Control control = viewer.getControl();

            final RunnableWithResult<Float> runnable = new AbstractRunnableWithResult<Float>() {

                public void run() {
                    final Point size;

                    try {
                        size = control.getSize();
                    } catch (final SWTException exception) {
                        // ignore exception
                        return;
                    }

                    if (size.x == 0 || size.y == 0) {
                        return;
                    }

                    setResult(Math.round(ASPECT_RATIO_ROUND * size.x / size.y) / ASPECT_RATIO_ROUND);
                }
            };

            if (control.getDisplay() == Display.getCurrent()) {
                runnable.run();
            } else {
                control.getDisplay().syncExec(runnable);
            }

            return runnable.getResult();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setOptionValue(final String optionId, final String valueString) {
        LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(optionId);
        Object value = optionData.parseValue(valueString);

        if (graphElement != null) {
            KLayoutData elementLayout = graphElement.getData(KLayoutData.class);
            if (elementLayout == null) {
                if (graphElement instanceof KEdge) {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
                } else {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
                }
                graphElement.getData().add(elementLayout);
            }
            elementLayout.setProperty(optionData, value);
        }
    }

    /**
     * Reveals the KLighD (context) {@link IViewer} from the given layout context.
     *
     * @param context
     *            a layout context
     * @return the corresponding KLighD (context) {@link IViewer}, or {@code null}
     */
    private IViewer getViewer() {
        final KLayoutData elementLayout = graphElement.getData(KLayoutData.class);
        IViewer viewer = elementLayout.getProperty(KlighdOptions.VIEWER);

        if (viewer == null) {
            if (workbenchPart instanceof IDiagramWorkbenchPart) {
                viewer = ((IDiagramWorkbenchPart) workbenchPart).getViewer();
                elementLayout.setProperty(KlighdOptions.VIEWER, viewer);
            }
        }
        return viewer;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<String> getAffectedOptions() {
        if (graphElement == null) {
            return Collections.emptyList();
        }

        final KLayoutData elementLayout = graphElement.getData(KLayoutData.class);
        if (elementLayout == null) {
            return Collections.emptyList();
        }

        final List<String> options = new LinkedList<String>();

        Map<IProperty<?>, Object> allProperties = null;

        // chsch: we do have a concurrency issue here that leads to
        // 'elementLayout.getAllProperties() == null', and which I don't understand entirely
        // thus I added this retry routine that in addition catches
        // ConcurrentModificationExceptions, which IMO may occur as well due to the loop in
        // EMapPropertyHolder#getAllProperties()
        while (allProperties == null) {
            try {
                allProperties = elementLayout.getAllProperties();

            } catch (final ConcurrentModificationException e) {
                // add an info to the log and retry...
                final String msg = "Concurrent modification in KGraphPropertyLayoutConfig:"
                        + KlighdPlugin.LINE_SEPARATOR + "  elementLayout == " + elementLayout
                        + KlighdPlugin.LINE_SEPARATOR + "  element == " + graphElement
                        + KlighdPlugin.LINE_SEPARATOR + "  sourceElement == "
                        + elementLayout.getProperty(KlighdInternalProperties.MODEL_ELEMEMT);

                KlighdPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
            }
        }

        final Set<Map.Entry<IProperty<?>, Object>> entrySet = allProperties.entrySet();

        final KNode node;
        if (graphElement instanceof KNode) {
            node = (KNode) graphElement;
        } else if (graphElement instanceof KPort) {
            node = ((KPort) graphElement).getNode();
        } else {
            node = null;
        }

        // first handle all expansion aware layout option sets
        if (node != null) {
            for (final Map.Entry<IProperty<?>, Object> entry : entrySet) {
                if (entry.getKey().equals(ExpansionAwareLayoutOption.OPTION)) {

                    final ExpansionAwareLayoutOptionData ealo =
                            (ExpansionAwareLayoutOptionData) entry.getValue();

                    final RenderingContextData rcd = RenderingContextData.get(node);
                    final boolean expanded = !node.getChildren().isEmpty()
                            && rcd.getProperty(KlighdInternalProperties.POPULATED);

                    final Set<IProperty<?>> keySet =
                            ealo.getValues(expanded).getAllProperties().keySet();
                    for (IProperty<?> iProperty : keySet) {
                        options.add(iProperty.getId());
                    }
                }
            }
        }

        // then handle all normal layout options
        for (final Map.Entry<IProperty<?>, Object> entry : entrySet) {
            if (!entry.getKey().equals(ExpansionAwareLayoutOption.OPTION)) {
                options.add(entry.getKey().getId());
            }
        }
        
        // handle special layout options
        if (getContainer() == null) {
            options.add(CoreOptions.ASPECT_RATIO.getId());
        }

        return options;
    }

    /**
     * {@inheritDoc}
     */
    public EditingDomain getEditingDomain() {
        // MIGRATE What is our editing domain here?
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Set<Target> getOptionTargets() {
        if (graphElement instanceof KEdge) {
            return EnumSet.of(Target.EDGES);
        } else if (graphElement instanceof KLabel) {
            return EnumSet.of(Target.LABELS);
        } else if (graphElement instanceof KPort) {
            return EnumSet.of(Target.PORTS);
        } else if (graphElement instanceof KNode) {
            if (((KNode) graphElement).getChildren().isEmpty()) {
                return EnumSet.of(Target.NODES);
            } else {
                return EnumSet.of(Target.NODES, Target.PARENTS);
            }
        }
        
        return EnumSet.noneOf(LayoutOptionData.Target.class);
    }

    /**
     * {@inheritDoc}
     */
    public ILayoutConfigurationStore getParent() {
        KGraphElement container = getContainer();
        if (container != null) {
            return new KlighdLayoutConfigurationStore(workbenchPart, container);
        } else {
            return null;
        }
    }

    /**
     * Returns the parent node of the given graph element.
     *
     * @param graphElement
     *            a graph element
     * @return the corresponding parent node
     */
    private KNode getContainer() {
        if (graphElement instanceof KNode) {
            return ((KNode) graphElement).getParent();

        } else if (graphElement instanceof KEdge) {
            return ((KEdge) graphElement).getSource().getParent();

        } else if (graphElement instanceof KPort) {
            return ((KPort) graphElement).getNode().getParent();

        } else if (graphElement instanceof KLabel) {
            final KLabeledGraphElement labeledGraphElement = ((KLabel) graphElement).getParent();

            if (labeledGraphElement instanceof KNode) {
                return ((KNode) labeledGraphElement).getParent();

            } else if (labeledGraphElement instanceof KEdge) {
                return ((KEdge) labeledGraphElement).getSource().getParent();

            } else if (labeledGraphElement instanceof KPort) {
                return ((KPort) labeledGraphElement).getNode().getParent();
            }
        }
        return null;
    }

    // /**
    // * Reveals the KLighD {@link KNode view model} from the given layout context.
    // *
    // * @param context
    // * a layout context
    // * @return the corresponding KLighD (context) {@link IViewer}, or {@code null}
    // */
    // private KNode getViewModel(final LayoutContext context) {
    // final IViewer viewer = getViewer(context);
    // return viewer != null ? viewer.getViewContext().getViewModel() : null;
    // }

    // /**
    // * Returns the graph element that shall be subject to modifications by this layout
    // configurator.
    // *
    // * @param context
    // * a layout context
    // * @param considerDomainModel
    // * whether associated domain model elements should be queried. This should be the
    // * case for 'writing actions' that yield persistent changes in the domain model.
    // * @return the graph element that shall be modified in the given context, or {@code null}
    // */
    // private KGraphElement getAffectedElement(final LayoutContext context,
    // final boolean considerDomainModel) {
    //
    // if (considerDomainModel) {
    // // the use case here is that for kgraph domain models (i.e. .kgt .kgx files)
    // // any changed property should be written back to the originating domain model.
    // //
    // // however, when retrieving these properties during layout they are
    // // already copied to the diagram element and further properties
    // // that were added during diagram synthesis would be neglected.
    // final Object domainElement = context.getProperty(LayoutContext.DOMAIN_MODEL);
    // if (domainElement instanceof KGraphElement) {
    // return (KGraphElement) domainElement;
    // }
    // }
    //
    // final Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
    // if (diagramPart instanceof KGraphElement) {
    // return (KGraphElement) diagramPart;
    // }
    //
    // return getViewModel(context);
    // }

    // /**
    // * {@inheritDoc}
    // */
    // public boolean isSet(final LayoutOptionData optionData, final LayoutContext context) {
    // final KGraphElement element = getAffectedElement(context, true);
    // if (element != null) {
    // final KLayoutData elementLayout = element.getData(KLayoutData.class);
    // if (elementLayout != null) {
    // return elementLayout.getProperties().containsKey(optionData);
    // }
    // }
    // return false;
    // }

    // /**
    // * {@inheritDoc}
    // */
    // public void clearOptionValues(final LayoutContext context) {
    // final KGraphElement element = getAffectedElement(context, true);
    // if (element != null) {
    // final KLayoutData elementLayout = element.getData(KLayoutData.class);
    // if (elementLayout != null) {
    // elementLayout.getProperties().clear();
    // refreshModel(element, context);
    // }
    // }
    // }

    // /** layout context property for the (context) viewer. */
    // private static final IProperty<IViewer> VIEWER = new Property<IViewer>("klighd.viewer");

}
