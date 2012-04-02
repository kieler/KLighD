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
package de.cau.cs.kieler.klighd.piccolo.krendering.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.klighd.piccolo.krendering.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * @author mri
 */
public class KEdgeRenderingController extends AbstractRenderingController<KEdge, KEdgeNode> {

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
        PNode renderingNode;
        if (currentRendering != null) {
            renderingNode = handleEdgeRendering(currentRendering, (KEdgeNode) repNode);
        } else {
            renderingNode =
                    handleEdgeRendering(createDefaultEdgeRendering(), (KEdgeNode) repNode);
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
    private PNode handleEdgeRendering(final KRendering rendering, final KEdgeNode parent) {
        // the rendering of an edge has to be a polyline or spline
        if (!(rendering instanceof KPolyline) || rendering instanceof KPolygon) {
            throw new RuntimeException("Non-polyline rendering attached to graph edge: "
                    + getGraphElement());
        }

        // create the rendering
        @SuppressWarnings("unchecked")
        final PNodeController<PSWTAdvancedPath> controller =
                (PNodeController<PSWTAdvancedPath>) createRendering(rendering,
                        new ArrayList<KStyle>(0), parent, new PBounds(0, 0, 1, 1),
                        getRepresentation());
        controller.getNode().setPathToPolyline(parent.getBendPoints());
        parent.setRepresentationNode(controller.getNode());

        // add a listener on the parent's bend points
        addListener(KEdgeNode.PROPERTY_BEND_POINTS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        controller.getNode().setPathToPolyline(parent.getBendPoints());
                    }
                });

        return controller.getNode();
    }
    
    /**
     * Creates a default rendering for edges without attached rendering data.
     * 
     * @return the rendering
     */
    private static KRendering createDefaultEdgeRendering() {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KPolyline polyline = factory.createKPolyline();
        KForegroundColor color = factory.createKForegroundColor();
        color.setRed(0);
        color.setGreen(0);
        color.setBlue(0);
        polyline.getStyles().add(color);
        return polyline;
    }
    
}
