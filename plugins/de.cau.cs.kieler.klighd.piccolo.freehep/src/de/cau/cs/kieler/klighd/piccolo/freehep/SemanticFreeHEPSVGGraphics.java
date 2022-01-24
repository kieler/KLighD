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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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
    private String css;
    private String additionalRootData;

    /**
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @param description
     *            optional description to be inserted into the {@code desc} property of the
     *            generated SVG. Can be null.
     */
    public SemanticFreeHEPSVGGraphics(final Rectangle2D bounds, final Boolean textAsShapes,
            final Boolean embedFonts, final String description, final String css, 
            final String additionalRootData) {
        super(null);
        this.bounds = bounds;
        this.textAsShapes = textAsShapes;
        this.description = description;
        this.css = css;
        this.additionalRootData = additionalRootData;
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
        if (css != null) {
            props.setProperty(SemanticSVGGraphics2D.CSS, css);            
        }
        if (description != null) {
            props.setProperty(SemanticSVGGraphics2D.DESCRIPTION, description);
        }
        if (additionalRootData != null) {
            props.setProperty(SemanticSVGGraphics2D.ADDITIONAL_ROOT_DATA, additionalRootData);
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
    public void addSemanticData(final KlighdSemanticDiagramData semanticData) {
        graphicsDelegate.addSemanticData(semanticData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGroup(final KlighdSemanticDiagramData semanticData) {
        graphicsDelegate.startGroup(semanticData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGroup() {
        graphicsDelegate.endGroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextTextLength(double textLength) {
        graphicsDelegate.setNextTextLength(textLength);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected float getAbsoluteFontHeight() {
        return graphicsDelegate.getAdjustedFontHeight();
    }
}
