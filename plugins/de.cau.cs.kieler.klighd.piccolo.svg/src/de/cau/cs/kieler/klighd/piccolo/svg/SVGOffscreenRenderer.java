/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg;

import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractOffscreenRenderer;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * An implementation of {@link de.cau.cs.kieler.klighd.IOffscreenRenderer IOffscreenRenderer}
 * producing SVG diagrams.
 * 
 * @author chsch
 */
public class SVGOffscreenRenderer extends AbstractOffscreenRenderer {

    /** this plugin's id. */
    public static final String PLUGIN_ID = KlighdPiccoloPlugin.PLUGIN_ID + ".svg";
    
    /**
     * Property definition for declaring the desired SVG generator.<br>
     */
    public static final IProperty<String> GENERATOR = new Property<String>(
            "de.cau.cs.kieler.klighd.piccolo.svg.generator",
            "de.cau.cs.kieler.klighd.piccolo.svg.batik");

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

        // Construct a KLighD main camera ... 
        final KlighdMainCamera camera = new KlighdMainCamera();

        // add it to a Piccolo2D root figure
        //  (the basic PRoot is sufficient as this canvas doesn't rely on any SWT stuff)
        new PRoot().addChild(camera);

        try {
            // build up the diagram, i.e. apply the necessary diagram syntheses, etc.
            this.buildUpDiagram(viewContext, camera, properties);
            
        } catch (final RuntimeException e) {
            return new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID,
                    BUILDING_UP_FIGURES_FAILURE_MSG, e);
        }
        
        // determine the bounds of the diagram to be exported
        final PBounds bounds = getExportedBounds(camera, false);

        try {
            // create a new graphics object
            final KlighdAbstractSVGGraphics graphics =
                    SVGGeneratorManager.createGraphics(generator, bounds, textAsShapes, embedFonts);
            
            // do the actual diagram drawing work
            this.drawDiagram(camera, false, graphics, bounds);
            
            // dump out the resulting SVG description via the provided output stream
            graphics.stream(output);
        } catch (final Exception e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, EXPORT_DIAGRAM_FAILURE_MSG, e);
        }

        return Status.OK_STATUS; 
    }
}
