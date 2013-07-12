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
package de.cau.cs.kieler.klighd.macrolayout;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.macrolayout.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.RenderingContextData;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * A layout configuration which derives layout options from properties attached to layout data of
 * graph elements.
 * 
 * @author mri
 * @author msp
 */
public class KGraphPropertyLayoutConfig implements IMutableLayoutConfig {

    /** the priority for the property layout layout configuration. */
    public static final int PRIORITY = 20;
    
    /** The aspect ratio is rounded at two decimal places. */
    private static final float ASPECT_RATIO_ROUND = 100;
    
    /**
     * Returns the layout data instance of the given graph element.
     * 
     * @param graphElement a graph element
     * @return a shape layout or edge layout
     */
    private static KGraphData getLayoutData(final KGraphElement graphElement) {
        if (graphElement instanceof KEdge) {
            return graphElement.getData(KEdgeLayout.class);
        } else {
            return graphElement.getData(KShapeLayout.class);
        }
    }
    
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
            KGraphData elementLayout = getLayoutData(element);

            ContextViewer contextViewer = null;
            IWorkbenchPart workbenchPart = context.getProperty(EclipseLayoutConfig.WORKBENCH_PART);
            if (workbenchPart instanceof IDiagramWorkbenchPart) {
                contextViewer = ((IDiagramWorkbenchPart) workbenchPart).getContextViewer();
            }
            
            // determine the domain model element
            if (context.getProperty(LayoutContext.DOMAIN_MODEL) == null) {
                if (contextViewer != null) {
                    ViewContext viewContext = contextViewer.getCurrentViewContext();
                    if (viewContext != null) {
                        Object sourceElement = viewContext.getSourceElement(element);
                        if (sourceElement instanceof EObject) {
                            context.setProperty(LayoutContext.DOMAIN_MODEL, sourceElement);
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
                        ViewContext viewContext = contextViewer.getCurrentViewContext();
                        if (viewContext != null) {
                            Object sourceElement = viewContext.getSourceElement(parentNode);
                            if (sourceElement instanceof EObject) {
                                context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL,
                                        sourceElement);
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
            KGraphData elementLayout = getLayoutData((KGraphElement) diagramPart);
            if (elementLayout != null) {
                return elementLayout.getProperty(optionData);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            KGraphElement element = (KGraphElement) diagramPart;
            KGraphData elementLayout = getLayoutData(element);
            if (elementLayout != null) {
                for (Map.Entry<IProperty<?>, Object> entry : elementLayout.getAllProperties()
                        .entrySet()) {
                    
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
                        
                        graphData.copyProperties(ealo.getValues(!node.getChildren().isEmpty()
                                && rcd.getProperty(KlighdConstants.POPULATED)));
                    } else {
                        graphData.setProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    }
    
    /**
     * Returns the model that shall be subject to modifications by this layout configurator.
     * 
     * @param context a layout context
     * @return the graph element that shall be modified in the given context, or {@code null}
     */
    private KGraphElement getModificationModel(final LayoutContext context) {
        // XXX this doesn't work yet
//        EObject domainElement = context.getProperty(LayoutContext.DOMAIN_MODEL);
//        if (domainElement instanceof KGraphElement) {
//            return (KGraphElement) domainElement;
//        }
        
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (diagramPart instanceof KGraphElement) {
            return (KGraphElement) diagramPart;
        }
        
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final LayoutOptionData<?> optionData, final LayoutContext context,
            final Object value) {
        KGraphElement element = getModificationModel(context);
        if (element != null) {
            KGraphData elementLayout = getLayoutData(element);
            if (elementLayout == null) {
                if (element instanceof KEdge) {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKEdgeLayout();
                } else {
                    elementLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
                }
                element.getData().add(elementLayout);
            }
            elementLayout.setProperty(optionData, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSet(final LayoutOptionData<?> optionData, final LayoutContext context) {
        KGraphElement element = getModificationModel(context);
        if (element != null) {
            KGraphData elementLayout = getLayoutData(element);
            if (elementLayout != null) {
                return elementLayout.getProperty(optionData) != null;
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
            KGraphData elementLayout = getLayoutData(element);
            if (elementLayout != null) {
                elementLayout.getProperties().clear();
            }
        }
    }
    
}
