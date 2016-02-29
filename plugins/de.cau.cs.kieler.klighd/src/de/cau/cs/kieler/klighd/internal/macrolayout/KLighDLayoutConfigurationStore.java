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
import org.eclipse.elk.core.service.DiagramLayoutEngine;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

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
public class KLighDLayoutConfigurationStore implements ILayoutConfigurationStore {

    /**
     * Provider for KLighD layout configuration stores.
     */
    public static final class Provider implements ILayoutConfigurationStore.Provider {

        @Override
        public ILayoutConfigurationStore get(final IWorkbenchPart workbenchPart,
                final Object context) {
            if (context instanceof KGraphElement) {
                try {
                    return new KLighDLayoutConfigurationStore(workbenchPart,
                            (KGraphElement) context);
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
    public KLighDLayoutConfigurationStore(final IWorkbenchPart workbenchPart,
            final KGraphElement context) {
        this.workbenchPart = workbenchPart;
        this.graphElement = context;
    }

    /**
     * {@inheritDoc}
     */
    public Object getOptionValue(final String optionId) {
        LayoutOptionData lod = LayoutMetaDataService.getInstance().getOptionData(optionId);
        return getOptionValue(lod);
    }

    private Object getOptionValue(
            final LayoutOptionData optionData/* , final LayoutContext context */) {
        // final KGraphElement element = getAffectedElement(context, false);
        final KGraphElement element = graphElement;
        if (element == null) {
            return null;
        }

        final KLayoutData elementLayout = element.getData(KLayoutData.class);
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
                return null;
            }

            final KNode node;
            if (element instanceof KNode) {
                node = (KNode) element;

            } else if (element instanceof KPort) {
                node = ((KPort) element).getNode();

            } else {
                return null;
            }

            final RenderingContextData rcd = RenderingContextData.get(node);
            final boolean expanded = !node.getChildren().isEmpty()
                    && rcd.getProperty(KlighdInternalProperties.POPULATED);

            return ealo.getValue(optionData, expanded);

        } else {
            return value;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setOptionValue(final String optionId, final String value) {
        LayoutOptionData lod = LayoutMetaDataService.getInstance().getOptionData(optionId);
        Object valueObject = lod.parseValue(value);
        setOptionValue(lod, valueObject);
    }

    private void setOptionValue(
            final LayoutOptionData optionData, /* final LayoutContext context, */
            final Object value) {
        // final KGraphElement element = getAffectedElement(context, true);
        final KGraphElement element = graphElement;
        if (element != null) {
            KLayoutData elementLayout = element.getData(KLayoutData.class);
            if (elementLayout == null) {
                if (element instanceof KEdge) {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
                } else {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
                }
                element.getData().add(elementLayout);
            }
            elementLayout.setProperty(optionData, value);
            refreshModel(element);
        }
    }

    /**
     * Refresh the model in case the domain model was modified by this layout configurator.
     *
     * @param element
     *            the affected model element
     * @param layoutContext
     *            the layout context
     */
    private void refreshModel(
            final KGraphElement element/* , final LayoutContext layoutContext */) {

        // if (element == layoutContext.getProperty(LayoutContext.DOMAIN_MODEL)) {

        final ViewContext viewContext = getViewContext(/* layoutContext */);
        if (viewContext == null) {
            return;
        }

        // update the view context in order to re-apply the view synthesis
        viewContext.update();

        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                // final IWorkbenchPart workbenchPart =
                // layoutContext.getProperty(EclipseLayoutConfig.WORKBENCH_PART);
                if (workbenchPart != null) {
                    // re-apply auto-layout with the new configuration
                    DiagramLayoutEngine.invokeLayout(workbenchPart, null, true, false, false,
                            false);

                    if (workbenchPart instanceof IDiagramWorkbenchPart.IDiagramEditorPart) {
                        // mark the editor as dirty
                        ((IDiagramWorkbenchPart.IDiagramEditorPart) workbenchPart).setDirty(true);
                    }
                }
            }
        });
        // }
    }

    /**
     * Reveals the KLighD {@link ViewContext} from the given layout context.
     *
     * @param context
     *            a layout context
     * @return the corresponding KLighD (context) {@link IViewer}, or {@code null}
     */
    private ViewContext getViewContext(/* final LayoutContext context */) {
        final IViewer viewer = getViewer(/* context */);
        return viewer != null ? viewer.getViewContext() : null;
    }

    /**
     * Reveals the KLighD (context) {@link IViewer} from the given layout context.
     *
     * @param context
     *            a layout context
     * @return the corresponding KLighD (context) {@link IViewer}, or {@code null}
     */
    private IViewer getViewer(/* final LayoutContext context */) {
        IViewer viewer = null; // = context.getProperty(VIEWER);

        // if (viewer == null) {
        // final IWorkbenchPart workbenchPart =
        // context.getProperty(EclipseLayoutConfig.WORKBENCH_PART);
        if (workbenchPart instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) workbenchPart).getViewer();
            // context.setProperty(VIEWER, viewer);
        }
        // }
        return viewer;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<String> getAffectedOptions() {
        Collection<IProperty<?>> affectedOptions = getAffectedOptions2();
        Collection<String> affectedOptionIDs = Collections.emptyList();
        for (IProperty<?> property : affectedOptions) {
            affectedOptionIDs.add(property.getId());
        }
        return affectedOptionIDs;
    }

    private Collection<IProperty<?>> getAffectedOptions2(/* final LayoutContext context */) {
        // final KGraphElement element = getAffectedElement(context, false);
        final KGraphElement element = graphElement;

        if (element == null) {
            return Collections.emptyList();
        }

        final KLayoutData elementLayout = element.getData(KLayoutData.class);
        if (elementLayout == null) {
            return Collections.emptyList();
        }

        final List<IProperty<?>> options = new LinkedList<IProperty<?>>();

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
                        + KlighdPlugin.LINE_SEPARATOR + "  element == " + element
                        + KlighdPlugin.LINE_SEPARATOR + "  sourceElement == "
                        + elementLayout.getProperty(KlighdInternalProperties.MODEL_ELEMEMT);

                KlighdPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
            }
        }

        final Set<Map.Entry<IProperty<?>, Object>> entrySet = allProperties.entrySet();

        final KNode node;
        if (element instanceof KNode) {
            node = (KNode) element;
        } else if (element instanceof KPort) {
            node = ((KPort) element).getNode();
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

                    options.addAll(ealo.getValues(expanded).getAllProperties().keySet());
                }
            }
        }

        // then handle all normal layout options
        for (final Map.Entry<IProperty<?>, Object> entry : entrySet) {
            if (!entry.getKey().equals(ExpansionAwareLayoutOption.OPTION)) {
                options.add(entry.getKey());
            }
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
            Set<Target> partTarget = EnumSet.of(Target.NODES);
            if (!((KNode) graphElement).getChildren().isEmpty()) {
                partTarget.add(Target.PARENTS);
            }
            return partTarget;
        }
        return EnumSet.noneOf(LayoutOptionData.Target.class);
    }    
    
    @Override
    public ILayoutConfigurationStore getParent() {
        KGraphElement container = getParentNode(graphElement);
        if (container != null) {
            return new KLighDLayoutConfigurationStore(workbenchPart, container);
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
    private static KNode getParentNode(final KGraphElement graphElement) {
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    private Object getContextValue(final IProperty<?> property, final LayoutContext context) {
//        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
//
//        if (!(diagramPart instanceof KGraphElement)) {
//            diagramPart = getViewModel(context);
//        }
//
//        if (diagramPart == null) {
//            return null;
//        }
//
//        final KGraphElement affectedElement = getAffectedElement(context, false);
//        if (affectedElement == null) {
//            return null;
//        }
//
//        final ViewContext viewContext = getViewContext(context);
//        final KGraphElement viewElement = (KGraphElement) diagramPart;
//
//        if (property.equals(LayoutContext.DIAGRAM_PART)) {
//            return viewElement;
//
//        } else if (property.equals(LayoutContext.CONTAINER_DIAGRAM_PART)) {
//            // find the parent node for the selected graph element
//            return getParentNode(viewElement);
//
//        } else if (property.equals(LayoutContext.DOMAIN_MODEL)) {
//            // determine the domain model element
//            if (viewContext != null) {
//                return viewContext.getSourceElement(viewElement);
//            }
//
//        } else if (property.equals(LayoutContext.CONTAINER_DOMAIN_MODEL)) {
//            // determine the domain model element of the parent node
//            final KNode parentNode = getParentNode(viewElement);
//
//            // determine the domain model element
//            if (parentNode != null && viewContext != null) {
//                return viewContext.getSourceElement(parentNode);
//            }
//
//            final Object domainModel = context.getProperty(LayoutContext.DOMAIN_MODEL);
//            if (domainModel instanceof KGraphElement) {
//                return getParentNode((KGraphElement) domainModel);
//            }
//
//        } else if (property.equals(LayoutContext.OPT_TARGETS)) {
//            // add layout option target types
//            return new DefaultLayoutConfig.OptionTargetSwitch().doSwitch(affectedElement);
//
//        } else if (property.equals(DefaultLayoutConfig.HAS_PORTS)) {
//            // determine whether the graph element is a node with ports
//            if (affectedElement instanceof KNode) {
//                return !((KNode) affectedElement).getPorts().isEmpty();
//            }
//
//        } else if (property.equals(EclipseLayoutConfig.ASPECT_RATIO)) {
//            // get aspect ratio for the current diagram
//            final IViewer viewer = getViewer(context);
//            if (viewer == null || viewer.getControl() == null) {
//                return null;
//            }
//
//            final Control control = viewer.getControl();
//
//            final RunnableWithResult<Float> runnable = new AbstractRunnableWithResult<Float>() {
//
//                public void run() {
//                    final Point size;
//
//                    try {
//                        size = control.getSize();
//                    } catch (final SWTException exception) {
//                        // ignore exception
//                        return;
//                    }
//
//                    if (size.x == 0 || size.y == 0) {
//                        return;
//                    }
//
//                    setResult(
//                            Math.round(ASPECT_RATIO_ROUND * size.x / size.y) / ASPECT_RATIO_ROUND);
//                }
//            };
//
//            if (control.getDisplay() == Display.getCurrent()) {
//                runnable.run();
//            } else {
//                control.getDisplay().syncExec(runnable);
//            }
//
//            return runnable.getResult();
//
//        } else if (property.equals(DefaultLayoutConfig.CONTENT_HINT)) {
//            // check whether a hint for the layout algorithm has been set
//            final KLayoutData elementLayout = affectedElement.getData(KLayoutData.class);
//            if (elementLayout != null) {
//                return elementLayout.getProperty(LayoutOptions.ALGORITHM);
//            }
//
//        } else if (property.equals(DefaultLayoutConfig.CONTAINER_HINT)) {
//            // check whether a hint for the layout algorithm has been set on the parent node
//            Object parentElement = context.getProperty(LayoutContext.CONTAINER_DOMAIN_MODEL);
//            if (!(parentElement instanceof KGraphElement)) {
//                parentElement = getParentNode(viewElement);
//            }
//
//            if (parentElement != null) {
//                final KLayoutData parentLayout =
//                        ((KGraphElement) parentElement).getData(KLayoutData.class);
//
//                if (parentLayout != null) {
//                    return parentLayout.getProperty(LayoutOptions.ALGORITHM);
//                }
//            }
//        }
//
//        return null;
//    }    
    
//    /**
//     * Reveals the KLighD {@link KNode view model} from the given layout context.
//     *
//     * @param context
//     *            a layout context
//     * @return the corresponding KLighD (context) {@link IViewer}, or {@code null}
//     */
//    private KNode getViewModel(final LayoutContext context) {
//        final IViewer viewer = getViewer(context);
//        return viewer != null ? viewer.getViewContext().getViewModel() : null;
//    }





//    /**
//     * Returns the graph element that shall be subject to modifications by this layout configurator.
//     *
//     * @param context
//     *            a layout context
//     * @param considerDomainModel
//     *            whether associated domain model elements should be queried. This should be the
//     *            case for 'writing actions' that yield persistent changes in the domain model.
//     * @return the graph element that shall be modified in the given context, or {@code null}
//     */
//    private KGraphElement getAffectedElement(final LayoutContext context,
//            final boolean considerDomainModel) {
//
//        if (considerDomainModel) {
//            // the use case here is that for kgraph domain models (i.e. .kgt .kgx files)
//            // any changed property should be written back to the originating domain model.
//            //
//            // however, when retrieving these properties during layout they are
//            // already copied to the diagram element and further properties
//            // that were added during diagram synthesis would be neglected.
//            final Object domainElement = context.getProperty(LayoutContext.DOMAIN_MODEL);
//            if (domainElement instanceof KGraphElement) {
//                return (KGraphElement) domainElement;
//            }
//        }
//
//        final Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
//        if (diagramPart instanceof KGraphElement) {
//            return (KGraphElement) diagramPart;
//        }
//
//        return getViewModel(context);
//    }

//    /**
//     * {@inheritDoc}
//     */
//    public boolean isSet(final LayoutOptionData optionData, final LayoutContext context) {
//        final KGraphElement element = getAffectedElement(context, true);
//        if (element != null) {
//            final KLayoutData elementLayout = element.getData(KLayoutData.class);
//            if (elementLayout != null) {
//                return elementLayout.getProperties().containsKey(optionData);
//            }
//        }
//        return false;
//    }

//    /**
//     * {@inheritDoc}
//     */
//    public void clearOptionValues(final LayoutContext context) {
//        final KGraphElement element = getAffectedElement(context, true);
//        if (element != null) {
//            final KLayoutData elementLayout = element.getData(KLayoutData.class);
//            if (elementLayout != null) {
//                elementLayout.getProperties().clear();
//                refreshModel(element, context);
//            }
//        }
//    }

//    /** layout context property for the (context) viewer. */
//    private static final IProperty<IViewer> VIEWER = new Property<IViewer>("klighd.viewer");

//    /** The aspect ratio is rounded at two decimal places. */
//    private static final float ASPECT_RATIO_ROUND = 100;


}
