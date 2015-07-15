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
package de.cau.cs.kieler.klighd.piccolo.freehep;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.freehep.graphicsbase.util.UserProperties;
import org.freehep.graphicsio.svg.SVGGraphics2D;

import de.cau.cs.kieler.klighd.piccolo.export.KlighdAbstractSVGGraphics;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;

/**
 * Wrapper for the FreeHEP {@link SVGGraphics2D} class.
 * 
 * @author uru
 * 
 * @see <a href="http://java.freehep.org/vectorgraphics/">
 *      http://java.freehep.org/vectorgraphics/</a>
 */
public class SemanticFreeHEPSVGGraphics extends KlighdAbstractSVGGraphics {

    private ByteArrayOutputStream baos;
    private SemanticSVGGraphics2D graphicsDelegate;

    private Rectangle2D bounds;
    private boolean textAsShapes;
    private boolean embedFonts;
    private String description;

    /**
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public SemanticFreeHEPSVGGraphics(final Rectangle2D bounds, final Boolean textAsShapes,
            final Boolean embedFonts, final String description) {
        super(null);
        this.bounds = bounds;
        this.textAsShapes = textAsShapes;
        this.description = description;
        if (!textAsShapes) {
            this.setCanHandleMultiline(true);
        }
        this.embedFonts = embedFonts.booleanValue();

        init();
    }

    private void init() {
        baos = new ByteArrayOutputStream();

        // create graphics object
        graphicsDelegate = new SemanticSVGGraphics2D(baos, new Dimension(
                (int) Math.round(bounds.getWidth()), (int) Math.round(bounds.getHeight())));

        // some settings
        final UserProperties props = new UserProperties();
        props.setProperty(SVGGraphics2D.TEXT_AS_SHAPES, textAsShapes);
        props.setProperty(SVGGraphics2D.EMBED_FONTS, embedFonts);
        if (description != null) {
            props.setProperty(SemanticSVGGraphics2D.DESCRIPTION, description);
        }
        graphicsDelegate.setProperties(props);

        // start
        graphicsDelegate.startExport();

        setGraphicsDelegate(graphicsDelegate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {
        try {
            // end stream and retrieve svg from stream
            graphicsDelegate.endExport();
            graphicsDelegate.closeStream();

            final String s = new String(baos.toByteArray());
            return s;

        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        init();
    }

    @Override
    public void stream(final OutputStream out) throws IOException {
        // finish the data 
        graphicsDelegate.endExport();

        baos.writeTo(out);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addSemanticData(KlighdSemanticDiagramData semanticData) {
        graphicsDelegate.addSemanticData(semanticData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGroup(KlighdSemanticDiagramData semanticData) {
        graphicsDelegate.startGroup(semanticData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGroup() {
        graphicsDelegate.endGroup();
    }
}
