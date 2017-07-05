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

import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.IDiagramExporter.ExportData;
import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PRoot;

/**
 * An implementation of {@link de.cau.cs.kieler.klighd.IOffscreenRenderer IOffscreenRenderer}
 * producing SVG diagrams.
 *
 * @author chsch
 */
public class SVGOffscreenRenderer extends AbstractOffscreenRenderer {

    /** The freeHEP-based SVG generator's id, requires the fragment "...klighd.piccolo.freehep". */
    public static final String GENERATOR_SVG_FREEHEP_EXTENDED =
            "de.cau.cs.kieler.klighd.piccolo.svggen.freeHEPExtended";

    /** The Batik-based SVG generator's id, requires the fragment "...klighd.piccolo.batik". */
    public static final String GENERATOR_SVG_BATIK =
            "de.cau.cs.kieler.klighd.piccolo.svggen.batik";

    /** Property definition for declaring the desired SVG generator. */
    public static final IProperty<String> GENERATOR = new Property<String>(
            "de.cau.cs.kieler.klighd.piccolo.svg.generator",
            GENERATOR_SVG_FREEHEP_EXTENDED);

    /**
     * {@inheritDoc}
     */
    public IStatus render(final ViewContext viewContext, final OutputStream output,
            final IPropertyHolder properties) {

        final boolean textAsShapes = properties != null
                ? properties.getProperty(TEXT_AS_SHAPES) : TEXT_AS_SHAPES.getDefault();
        final boolean embedFonts = properties != null
                ? properties.getProperty(EMBED_FONTS) : EMBED_FONTS.getDefault();
        final String generator = properties != null
                ? properties.getProperty(GENERATOR) : GENERATOR.getDefault();
        final String description = properties != null
                ? properties.getProperty(DESCRIPTION) : DESCRIPTION.getDefault();
                final String additionalRootData = properties != null
                        ? properties.getProperty(ADDITIONAL_ROOT_DATA) : ADDITIONAL_ROOT_DATA.getDefault();
        final String css = properties != null
                ? properties.getProperty(CSS) : CSS.getDefault();
        final boolean transparentBackground = properties != null
                ? properties.getProperty(KlighdOptions.SVG_EXPORT_TRANSPARENT_BACKGROUND)
                : KlighdOptions.SVG_EXPORT_TRANSPARENT_BACKGROUND.getDefault();
        final RGB backgroundColor = properties != null
                ? properties.getProperty(KlighdOptions.SVG_EXPORT_BACKGROUND_COLOR)
                : KlighdOptions.SVG_EXPORT_BACKGROUND_COLOR.getDefault();

        // Construct a KLighD main camera ...
        //  (the basic PRoot is sufficient here, as this canvas doesn't rely on any SWT stuff)
        final KlighdMainCamera camera = new KlighdMainCamera(new PRoot());

        try {
            // build up the diagram, i.e. apply the necessary diagram syntheses, etc.
            this.buildUpDiagram(viewContext, camera, properties);

        } catch (final RuntimeException e) {
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID,
                    BUILDING_UP_FIGURES_FAILURE_MSG, e);
        }

        try {
            ExportData data = new ExportData(viewContext, generator, output, false, 1, textAsShapes,
                    embedFonts, description);
            data.setBackgroundColor(backgroundColor);
            data.setTransparentBackground(transparentBackground);
            data.additionalRootData(additionalRootData);
            data.css(css);
            return new SVGExporter().export(camera, data);
        } catch (final RuntimeException e) {
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID,
                    EXPORT_DIAGRAM_FAILURE_MSG, e);
        }
    }
}
