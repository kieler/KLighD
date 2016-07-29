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
package de.cau.cs.kieler.klighd.piccolo.export;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.IPropertyHolder;

import de.cau.cs.kieler.klighd.IOffscreenRenderer;
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * Abstract implementation of {@link IOffscreenRenderer} providing common functionalities of
 * concrete for particular diagram formats.
 *
 * @author chsch
 */
public abstract class AbstractOffscreenRenderer extends AbstractDiagramExporter implements
        IOffscreenRenderer {

    /** Error msg to be delivered in case of figure composition failures. */
    protected static final String BUILDING_UP_FIGURES_FAILURE_MSG =
            "KLighD offscreen diagram export: "
                + "Building up the diagram figures failed with the following exception.";

    /** Error msg to be delivered in case of diagram export failures. */
    protected static final String EXPORT_DIAGRAM_FAILURE_MSG =
            "KLighD offscreen diagram export: "
                + "Rendering the diagram on the provided canvas failed with the following exception.";

    /**
     * This method is to be used by concrete implementations of {@link IOffscreenRenderer}.<br>
     * It initialized a {@link DiagramController} that is in charge of building up the diagram
     * figures. Afterwards it collapses, expands, and scales diagram nodes, hides and shows diagram
     * elements, and configures the diagram clip if requested. Finally, it invokes the computation
     * and application of automatic layout data.
     *
     * @param viewContext
     *            the view context describing the diagram to be rendered
     * @param camera
     *            the {@link KlighdMainCamera} required to render the diagram
     * @param properties
     *            an {@link IPropertyHolder}, e.g. a
     *            {@link de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
     *            KlighdSynthesisProperties} instance, declaring the {@link #OUTPUT_FORMAT}, for
     *            example.
     * @return the {@link KShapeLayout} of the root {@link KNode} in order to conveniently provide
     *         access to the diagram size, which may be required for configuring the diagram export
     */
    protected KShapeLayout buildUpDiagram(final ViewContext viewContext,
            final KlighdMainCamera camera, final IPropertyHolder properties) {
        // create a controller for the graph
        // since the controller attaches the 'ACTIVE' and 'POPULATED' flags that are examined
        // by the KlighdLayoutManager we need to do this before arranging the diagram
        final DiagramController c =
                new DiagramController(viewContext.getViewModel(), camera, true,
                        viewContext.getProperty(KlighdProperties.EDGES_FIRST).booleanValue());

        if (properties == null) {
            // layout the diagram
            new LightDiagramLayoutConfig(viewContext).layout();

        } else {
            // expand the desired elements...
            for (final Object o : properties.getProperty(IOffscreenRenderer.EXPANDED_ELEMENTS)) {
                final KNode node = viewContext.getTargetElement(o, KNode.class);
                if (node != null) {
                    c.expand(node);
                }
            }

            // and collapse the desired elements, respectively
            for (final Object o : properties.getProperty(IOffscreenRenderer.COLLAPSED_ELEMENTS)) {
                final KNode node = viewContext.getTargetElement(o, KNode.class);
                if (node != null) {
                    c.collapse(node);
                }
            }

            if (!properties.getProperty(IOffscreenRenderer.NO_LAYOUT)) {
                // layout the diagram
                new LightDiagramLayoutConfig(viewContext).layout();
            }
        }

        return viewContext.getViewModel().getData(KShapeLayout.class);
    }
}
