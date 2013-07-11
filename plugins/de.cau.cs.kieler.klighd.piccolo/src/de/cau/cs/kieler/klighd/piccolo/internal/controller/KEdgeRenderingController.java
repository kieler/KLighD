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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.krendering.KCustomConnectionFigureNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.KEdgeNode;
import edu.umd.cs.piccolo.PNode;

/**
 * @author mri
 */
public class KEdgeRenderingController extends AbstractKGERenderingController<KEdge, KEdgeNode> {

    /**
     * Constructs a rendering controller for an edge.
     * 
     * @param edge
     *            the Piccolo node representing an edge
     */
    public KEdgeRenderingController(final KEdgeNode edge) {
        super(edge.getGraphElement(), edge);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        PNode repNode = getRepresentation();

        // evaluate the rendering data
        KRendering currentRendering = getCurrentRendering();
        if (currentRendering == null) {
            return handleEdgeRendering(createDefaultEdgeRendering(), (KEdgeNode) repNode);
        } 
        PNode renderingNode;
        
        // the rendering of an edge has to be a KPolyline or a sub type of KPolyline except KPolygon,
        //  or a KCustomRendering providing a KCustomConnectionFigureNode
        switch (currentRendering.eClass().getClassifierID()) {
        case KRenderingPackage.KPOLYLINE:
        case KRenderingPackage.KROUNDED_BENDS_POLYLINE:
        case KRenderingPackage.KSPLINE:
            renderingNode = handleEdgeRendering((KPolyline) currentRendering, (KEdgeNode) repNode);
            break;
            
        case KRenderingPackage.KCUSTOM_RENDERING:
            final KCustomRendering customRendering = (KCustomRendering) currentRendering;
            if (customRendering.getFigureObject()  == null 
                    || customRendering.getFigureObject() instanceof KCustomConnectionFigureNode) {
                renderingNode = handleEdgeRendering(customRendering, (KEdgeNode) repNode);
                break;
            } else {
                final String msg = "KLighD: KCustomRendering attached to edge "
                        + currentRendering.eContainer()
                        + " must provide a figure object of type KCustomConnectionFigureNode"
                        + " allowing to set the start, end, and bend points, or 'null' letting KLighD"
                        + " trying to load the class provided by its name.";
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                        StatusManager.LOG);
                return null;
            }
            
        default:
            // hence, throw an exception if something different is provided
            final String msg = "KLighD: Illegal KRendering is attached to graph edge: "
                    + getGraphElement()
                    + ", must be a KPolyline, KRoundedBendsPolyline, KSpline, or KCustomRendering!";
            throw new RuntimeException(msg);
        }

        
        return renderingNode;
    }
    
    /**
     * Creates the Piccolo node for a rendering of a {@code KEdge} inside a parent Piccolo node.<br>
     * <br>
     * The rendering has to be a {@code KPolyline} or the method fails.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo edge node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleEdgeRendering(final KPolyline rendering, final KEdgeNode parent) {
        // create the rendering
        @SuppressWarnings("unchecked")
        final PNodeController<PSWTAdvancedPath> controller =
                (PNodeController<PSWTAdvancedPath>) 
                createRendering(rendering,
                        new ArrayList<KStyle>(0), parent, new Bounds(1, 1));
        if (rendering instanceof KSpline) {
            controller.getNode().setPathToSpline(parent.getBendPoints());
        } else if (rendering instanceof KRoundedBendsPolyline) {
            controller.getNode().setPathToRoundedBendPolyline(parent.getBendPoints(),
                    ((KRoundedBendsPolyline) rendering).getBendRadius());
        } else {
            controller.getNode().setPathToPolyline(parent.getBendPoints());
        }
        parent.setRepresentationNode(controller.getNode());

        // add a listener on the parent's bend points
        addListener(KEdgeNode.PROPERTY_BEND_POINTS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        if (rendering instanceof KSpline) {
                            controller.getNode().setPathToSpline(parent.getBendPoints());
                        } else if (rendering instanceof KRoundedBendsPolyline) {
                            controller.getNode().setPathToRoundedBendPolyline(parent.getBendPoints(),
                                    ((KRoundedBendsPolyline) rendering).getBendRadius());
                        } else {
                            controller.getNode().setPathToPolyline(parent.getBendPoints());
                        }
                    }
                });

        return controller.getNode();
    }
    
    /**
     * Creates the Piccolo node for a rendering of a {@code KEdge} inside a parent Piccolo node.<br>
     * <br>
     * The rendering has to be a {@code KPolyline} or the method fails.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo edge node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleEdgeRendering(final KCustomRendering rendering, final KEdgeNode parent) {

        // create the rendering
        @SuppressWarnings("unchecked")
        final PNodeController<KCustomConnectionFigureNode> controller =
                (PNodeController<KCustomConnectionFigureNode>) createRendering(rendering,
                        Collections.<KStyle>emptyList(), parent, new Bounds(1, 1));
        controller.getNode().setPoints(parent.getBendPoints());

        parent.setRepresentationNode(controller.getNode());

        // add a listener on the parent's bend points
        addListener(KEdgeNode.PROPERTY_BEND_POINTS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        controller.getNode().setPoints(parent.getBendPoints());
                    }
                });

        return controller.getNode();
    }
    
    /**
     * Creates a default rendering for edges without attached rendering data.
     * 
     * @return the rendering
     */
    private static KPolyline createDefaultEdgeRendering() {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KPolyline polyline = factory.createKPolyline();

        KForeground foreground = factory.createKForeground();
        foreground.setColor(factory.createKColor());
        
        polyline.getStyles().add(foreground);
        return polyline;
    }
    
}
