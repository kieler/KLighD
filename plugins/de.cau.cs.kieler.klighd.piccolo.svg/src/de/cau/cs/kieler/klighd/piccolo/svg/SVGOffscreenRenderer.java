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
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.core.properties.IPropertyHolder;
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
     * {@inheritDoc}
     */
    public IStatus render(final ViewContext viewContext, final OutputStream output,
            final IPropertyHolder properties) {
        
        final boolean textAsShapes = properties != null
                ? properties.getProperty(TEXT_AS_SHAPES) : TEXT_AS_SHAPES.getDefault();

        final KlighdSVGCanvas canvas = new KlighdSVGCanvas(textAsShapes);
        
        buildUpDiagram(viewContext, canvas.getCamera(), properties);

        canvas.setDiagramBounds(viewContext.getViewModel());

        final String svg = canvas.render();
        
        try {
            final Writer writer = new OutputStreamWriter(output);
            writer.write(svg);
            writer.flush();
        } catch (final IOException e) {
            return new Status(IStatus.ERROR, PLUGIN_ID, EXPORT_DIAGRAM_FAILURE_MSG, e);
        }
        
        return Status.OK_STATUS; 
    }
}
