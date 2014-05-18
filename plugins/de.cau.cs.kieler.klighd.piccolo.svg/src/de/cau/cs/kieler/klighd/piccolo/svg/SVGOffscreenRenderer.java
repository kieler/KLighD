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

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractOffscreenRenderer;

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
        final String generator = properties != null
                ? properties.getProperty(GENERATOR) : GENERATOR.getDefault();

        final KlighdSVGCanvas canvas = new KlighdSVGCanvas(generator, textAsShapes);

        buildUpDiagram(viewContext, canvas.getCamera(), properties);

        try {
            canvas.render(output);
        } catch (final IOException e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, EXPORT_DIAGRAM_FAILURE_MSG, e);
        }

        return Status.OK_STATUS; 
    }
}
