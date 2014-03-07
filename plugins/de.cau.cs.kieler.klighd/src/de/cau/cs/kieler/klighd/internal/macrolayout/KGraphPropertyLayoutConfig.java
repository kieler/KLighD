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
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.RenderingContextData;

/**
 * A layout configuration which derives layout options from properties attached to layout data of
 * graph elements.
 * 
 * @author mri
 * @author msp
 */
public class KGraphPropertyLayoutConfig implements IMutableLayoutConfig {

    /** layout context property for the context viewer. */
    public static final IProperty<IViewer<?>> CONTEXT_VIEWER = new Property<IViewer<?>>(
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
    public Object getContextValue(final IProperty<?> property, final LayoutContext context) {
        KGraphElement element = null;
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            element = (KGraphElement) diagramPart;
        } else {
            IViewer<?> contextViewer = getContextViewer(context);
            if (contextViewer != null) {
                element = contextViewer.getViewContext().getViewModel();
            }
        }
        
        if (element != null) {
            KLayoutData elementLayout = element.getData(KLayoutData.class);

            if (property.equals(LayoutContext.DIAGRAM_PART)) {
                return element;
                
            } else if (property.equals(LayoutContext.CONTAINER_DIAGRAM_PART)) {
                // find the parent node for the selected graph element
                return getParentNode(element);
                
            } else if (property.equals(LayoutContext.DOMAIN_MODEL)) {
                // determine the domain model element
                IViewer<?> contextViewer = getContextViewer(context);
                if (contextViewer != null) {
                    ViewContext viewContext = contextViewer.getViewContext();
                    if (viewContext != null) {
                        return viewContext.getSourceElement(element);
                    }
                }
                
            } else if (property.equals(LayoutContext.CONTAINER_DOMAIN_MODEL)) {
                // determine the domain model element of the parent node
                KNode parentNode = getParentNode(element);
                IViewer<?> contextViewer = getContextViewer(context);
                if (parentNode != null && contextViewer != null) {
                    ViewContext viewContext = contextViewer.getViewContext();
                    if (viewContext != null) {
                        return viewContext.getSourceElement(parentNode);
                    }
                }
                
            } else if (property.equals(LayoutContext.OPT_TARGETS)) {
                // add layout option target types
                return new DefaultLayoutConfig.OptionTargetSwitch().doSwitch(element);
                
            } else if (property.equals(DefaultLayoutConfig.HAS_PORTS)) {
                // determine whether the graph element is a node with ports
                if (element instanceof KNode) {
                    return !((KNode) element).getPorts().isEmpty();
                }
                
            } else if (property.equals(EclipseLayoutConfig.ASPECT_RATIO)) {
                // get aspect ratio for the current diagram
                try {
                    IViewer<?> contextViewer = getContextViewer(context);
                    if (contextViewer != null) {
                        Control control = contextViewer.getControl();
                        if (control != null) {
                            Point size = control.getSize();
                            if (size.x > 0 && size.y > 0) {
                                return Math.round(ASPECT_RATIO_ROUND * (float) size.x / size.y)
                                        / ASPECT_RATIO_ROUND;
                            }
                        }
                    }
                } catch (SWTException exception) {
                    // ignore exception
                }
                
            } else if (property.equals(DefaultLayoutConfig.CONTENT_HINT)) {
                // check whether a hint for the layout algorithm has been set
                if (elementLayout != null) {
                    elementLayout.getProperty(LayoutOptions.ALGORITHM);
                }
                
            } else if (property.equals(DefaultLayoutConfig.CONTAINER_HINT)) {
                // check whether a hint for the layout algorithm has been set on the parent node
                KNode parentNode = getParentNode(element);
                if (parentNode != null) {
                    KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
                    if (parentLayout != null) {
                        return parentLayout.getProperty(LayoutOptions.ALGORITHM);
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Determine the KLighD context viewer from the given layout context.
     * 
     * @param context a layout context
     * @return the corresponding KLighD context viewer, or {@code null}
     */
    private IViewer<?> getContextViewer(final LayoutContext context) {
        IViewer<?> contextViewer = context.getProperty(CONTEXT_VIEWER);
        if (contextViewer == null) {
            IWorkbenchPart workbenchPart = context.getProperty(EclipseLayoutConfig.WORKBENCH_PART);
            if (workbenchPart instanceof IDiagramWorkbenchPart) {
                contextViewer = ((IDiagramWorkbenchPart) workbenchPart).getViewer().getContextViewer();
                context.setProperty(CONTEXT_VIEWER, contextViewer);
            }
        }
        return contextViewer;
    }

    /**
     * {@inheritDoc}
     */
    public Object getOptionValue(final LayoutOptionData optionData, final LayoutContext context) {
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
            final IViewer<?> contextViewer = getContextViewer(layoutContext);
            if (contextViewer == null) {
                return;
            }
            
            final ViewContext viewContext = contextViewer.getViewContext();
            if (viewContext == null) {
                return;
            }

            // update the view context in order to re-apply the view synthesis
            viewContext.update(viewContext.getInputModel());

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
    public void setOptionValue(final LayoutOptionData optionData, final LayoutContext context,
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
            elementLayout.setProperty(optionData, value);
            refreshModel(element, context);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSet(final LayoutOptionData optionData, final LayoutContext context) {
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
    public void clearOptionValues(final LayoutContext context) {
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
