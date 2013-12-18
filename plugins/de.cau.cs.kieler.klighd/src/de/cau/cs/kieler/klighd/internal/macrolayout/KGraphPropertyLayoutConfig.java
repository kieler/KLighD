/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal.macrolayout;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyValueProxy;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.EclipseLayoutConfig;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
//SUPPRESS CHECKSTYLE PREVIOUS 6 LineLength

/**
 * A layout configuration which derives layout options from properties attached to layout data of
 * graph elements.
 * 
 * @author mri
 * @author msp
 */
public class KGraphPropertyLayoutConfig implements IMutableLayoutConfig {

    /** layout context property for the context viewer. */
    public static final IProperty<ContextViewer> CONTEXT_VIEWER = new Property<ContextViewer>(
            "klighd.contextViewer");
    /** the priority for the property layout layout configuration. */
    public static final int PRIORITY = 20;
    
    /** The aspect ratio is rounded at two decimal places. */
    private static final float ASPECT_RATIO_ROUND = 100;
    
    /**
     * Returns the parent node of the given graph element.
     * 
     * @param graphElement a graph element
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
            KLabeledGraphElement labeledGraphElement = ((KLabel) graphElement).getParent();
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

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KLayoutData elementLayout = element.getData(KLayoutData.class);

            ContextViewer contextViewer = null;
            IWorkbenchPart workbenchPart = context.getProperty(EclipseLayoutConfig.WORKBENCH_PART);
            if (workbenchPart instanceof IDiagramWorkbenchPart) {
                contextViewer = ((IDiagramWorkbenchPart) workbenchPart).getContextViewer();
                context.setProperty(CONTEXT_VIEWER, contextViewer);
            }
            
            // determine the domain model element
            if (context.getProperty(LayoutContext.DOMAIN_MODEL) == null) {
                if (contextViewer != null) {
                    ViewContext viewContext = contextViewer.getViewContext();
                    if (viewContext != null) {
                        Object sourceElement = viewContext.getSourceElement(element);
                        if (sourceElement instanceof EObject) {
                            context.setProperty(LayoutContext.DOMAIN_MODEL, (EObject) sourceElement);
                        }
                    }
                }
            }
            
            // add layout option target types
            Set<LayoutOptionData.Target> optionTargets = context.getProperty(LayoutContext.OPT_TARGETS);
            if (optionTargets == null) {
                optionTargets = new DefaultLayoutConfig.OptionTargetSwitch().doSwitch(element);
                context.setProperty(LayoutContext.OPT_TARGETS, optionTargets);
            }

            // determine whether the graph element is a node with ports
            if (element instanceof KNode) {
                context.setProperty(DefaultLayoutConfig.HAS_PORTS,
                        !((KNode) element).getPorts().isEmpty());
            }
            
            // get aspect ratio for the current diagram
            try {
                if (contextViewer != null) {
                    Control control = contextViewer.getControl();
                    if (control != null) {
                        Point size = control.getSize();
                        if (size.x > 0 && size.y > 0) {
                            context.setProperty(EclipseLayoutConfig.ASPECT_RATIO,
                                    Math.round(ASPECT_RATIO_ROUND * (float) size.x / size.y)
                                    / ASPECT_RATIO_ROUND);
                        }
                    }
                }
            } catch (SWTException exception) {
                // ignore exception
            }
            
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                // if not defined yet, check whether a hint for the layout algorithm has been set
                if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null
                        && elementLayout != null) {
                    String contentHint = elementLayout.getProperty(LayoutOptions.ALGORITHM);
                    if (contentHint != null) {
                        context.setProperty(DefaultLayoutConfig.CONTENT_HINT, contentHint);
                    }
                }
                
                // find the parent node for the selected graph element
                KNode parentNode = getParentNode(element);
                if (parentNode != null) {
                    if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null) {
                        String containerHint = parentNode.getData(KShapeLayout.class)
                                .getProperty(LayoutOptions.ALGORITHM);
                        if (containerHint != null) {
                            context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerHint);
                        }
                    }
                    context.setProperty(LayoutContext.CONTAINER_DIAGRAM_PART, parentNode);
                    if (contextViewer != null) {
                        ViewContext viewContext = contextViewer.getViewContext();
                        if (viewContext != null) {
                            Object sourceElement = viewContext.getSourceElement(parentNode);
                            if (sourceElement instanceof EObject) {
                                context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL,
                                        (EObject) sourceElement);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KLayoutData elementLayout = ((KGraphElement) diagramPart).getData(KLayoutData.class);
            if (elementLayout != null) {
                Object value = elementLayout.getProperties().get(optionData);
                if (value instanceof IPropertyValueProxy) {
                    return ((IPropertyValueProxy) value).resolveValue(optionData);
                } else if (value == null) {
                    
                    // check whether an expansion aware layout option set is present
                    ExpansionAwareLayoutOptionData ealo = elementLayout.getProperty(
                            ExpansionAwareLayoutOption.OPTION);
                    if (ealo != null) {
                        KNode node = null;
                        if (diagramPart instanceof KNode) {
                            node = (KNode) diagramPart; 
                        } else if (diagramPart instanceof KPort) {
                            node = ((KPort) diagramPart).getNode();
                        }
                        RenderingContextData rcd = RenderingContextData.get(node);
                        boolean expanded = !node.getChildren().isEmpty()
                                && rcd.getProperty(KlighdInternalProperties.POPULATED);
                        return ealo.getValue(optionData, expanded);
                    }
                    
                }
                return value;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IProperty<?>> getAffectedOptions(final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        List<IProperty<?>> options = new LinkedList<IProperty<?>>();
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KLayoutData elementLayout = element.getData(KLayoutData.class);
            if (elementLayout != null) {
                Set<Map.Entry<IProperty<?>, Object>> entrySet = elementLayout.getAllProperties()
                        .entrySet();
                
                // first handle all expansion aware layout option sets
                for (Map.Entry<IProperty<?>, Object> entry : entrySet) {
                    if (entry.getKey().equals(ExpansionAwareLayoutOption.OPTION)) {
                        KNode node = null;
                        if (element instanceof KNode) {
                            node = (KNode) element; 
                        } else if (element instanceof KPort) {
                            node = ((KPort) element).getNode();
                        }
                        
                        ExpansionAwareLayoutOptionData ealo =
                                (ExpansionAwareLayoutOptionData) entry.getValue();
                        RenderingContextData rcd = RenderingContextData.get(node);
                        
                        boolean expanded = !node.getChildren().isEmpty()
                                && rcd.getProperty(KlighdInternalProperties.POPULATED);
                        options.addAll(ealo.getValues(expanded).getAllProperties().keySet());
                    }
                }
                
                // then handle all normal layout options
                for (Map.Entry<IProperty<?>, Object> entry : entrySet) {
                    if (!entry.getKey().equals(ExpansionAwareLayoutOption.OPTION)) {
                        options.add(entry.getKey());
                    }
                }
            }
        }
        return options;
    }
    
    /**
     * Returns the model that shall be subject to modifications by this layout configurator.
     * 
     * @param context a layout context
     * @return the graph element that shall be modified in the given context, or {@code null}
     */
    private KGraphElement getModificationModel(final LayoutContext context) {
        Object domainElement = context.getProperty(LayoutContext.DOMAIN_MODEL);
        if (domainElement instanceof KGraphElement) {
            return (KGraphElement) domainElement;
        }
        
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            return (KGraphElement) diagramPart;
        }
        
        return null;
    }
    
    /**
     * Refresh the model in case the domain model was modified by this layout configurator.
     * 
     * @param element the affected model element
     * @param layoutContext the layout context
     */
    private void refreshModel(final KGraphElement element, final LayoutContext layoutContext) {
        if (element == layoutContext.getProperty(LayoutContext.DOMAIN_MODEL)) {
            final ContextViewer contextViewer = layoutContext.getProperty(CONTEXT_VIEWER);
            if (contextViewer == null) {
                return;
            }
            
            final ViewContext viewContext = contextViewer.getViewContext();
            if (viewContext == null) {
                return;
            }

            // update the view context in order to re-apply the view synthesis
            LightDiagramServices.updateViewContext(viewContext, viewContext.getInputModel());
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    IWorkbenchPart workbenchPart = layoutContext.getProperty(
                            EclipseLayoutConfig.WORKBENCH_PART);
                    if (workbenchPart != null) {
                        // re-apply auto-layout with the new configuration
                        DiagramLayoutEngine.INSTANCE.layout(workbenchPart, null,
                                true, false, false, false);
                        
                        if (workbenchPart instanceof IDiagramWorkbenchPart.IDiagramEditorPart) {
                            // mark the editor as dirty
                            ((IDiagramWorkbenchPart.IDiagramEditorPart) workbenchPart)
                                    .setDirty(true);
                        }
                    }
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void setValue(final LayoutOptionData<?> optionData, final LayoutContext context,
            final Object value) {
        KGraphElement element = getModificationModel(context);
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
            elementLayout.setProperty((IProperty<Object>) optionData, value);
            refreshModel(element, context);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSet(final LayoutOptionData<?> optionData, final LayoutContext context) {
        KGraphElement element = getModificationModel(context);
        if (element != null) {
            KLayoutData elementLayout = element.getData(KLayoutData.class);
            if (elementLayout != null) {
                return elementLayout.getProperties().containsKey(optionData);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void clearValues(final LayoutContext context) {
        KGraphElement element = getModificationModel(context);
        if (element != null) {
            KLayoutData elementLayout = element.getData(KLayoutData.class);
            if (elementLayout != null) {
                elementLayout.getProperties().clear();
                refreshModel(element, context);
            }
        }
    }
    
}
