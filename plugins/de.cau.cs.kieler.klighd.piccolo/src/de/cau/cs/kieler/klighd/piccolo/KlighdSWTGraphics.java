/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
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
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;

/**
 * This interface defines methods to be used by custom {@link edu.umd.cs.piccolo.PNode PNode}
 * implementations to draw there shapes. Its aim is to abstract the concrete implementation of
 * {@link java.awt.Graphics2D Graphics2D} like {@link edu.umd.cs.piccolox.swt.SWTGraphics2D
 * SWTGraphics2D} contributed by the <code>edu.umd.cs.piccolox.swt</code> package. Instead a
 * <i>Graphics</i> layer realizing e.g. an SVG output shall be supported interchangeably.<br>
 * <br>
 * For drawing basic figures (i.e. no text fields and images) {@link #draw(Path)} and
 * {@link #fill(Path)} as well as {@link #draw(Shape)} and {@link #fill(Shape)} are provided. If an
 * implementation is dedicated to draw on an SWT {@link Device}, indicated by {@link #getDevice()}
 * <code>!= null</code> the former <code>draw()</code> and <code>fill()</code> methods shall be used
 * by clients. This way clients can control the life cycle of the {@link Path} objects in order to
 * reduce waste of performance due to continuous object creation and dismiss. Otherwise the latter
 * pair of methods is to be used, e.g. for drawing on an SVG graphics.<br>
 * <br>
 * The rational of using Paths/Shapes for drawing is the decision to draw all basic figures by means
 * of SWT {@link Path Paths} objects. This is currently the only way of passing floating-point-based
 * coordinates to the {@link org.eclipse.swt.graphics.GC GC}, and thus to get rid of rounding
 * issues. Such SWT {@link Path Paths} can be easily built-up from {@link Shape Shapes} by means of
 * AWT Geometry {@link java.awt.geom.PathIterator PathIterators}, see
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths#createSWTPath(
 * java.awt.geom.PathIterator, Device) KlighdPaths#createSWTPath(PathIterator, Device)}.<br>
 * <br>
 * Consequently, we can delegate the application of {@link AffineTransform AffineTransforms} to SWT
 * by simply transforming them into an SWT {@link org.eclipse.swt.graphics.Transform Transform} and
 * calling {@link org.eclipse.swt.graphics.GC#setTransform(org.eclipse.swt.graphics.Transform)
 * GC#setTransform(Transform)}.
 * 
 * @author chsch
 */
public interface KlighdSWTGraphics {

    /**
     * Returns the SWT {@link Device} to draw on. This method is to be used for creating
     * {@link Device}-dependent objects like {@link org.eclipse.swt.graphics.Color Colors},
     * {@link org.eclipse.swt.graphics.Font Fonts}, {@link Image Images}, and {@link Path Paths}.<br>
     * <br>
     * If the return value is <code>null</code> the current canvas is not an SWT-based one.<br>
     * In that case only the AWT {@link Shape}-based <code>draw()</code> and <code>fill()</code>
     * methods are supported.
     * 
     * @return the {@link Device} to work with, or <code>null</code>, if a non-SWT canvas is used
     */
    Device getDevice();
    
    /**
     * This setter allows to (re-) use an object adhering to this interface for multiple paint runs.
     * 
     * @param theDevice
     *            the {@link Device} to work with
     */
    void setDevice(Device theDevice);
    
    /**
     * This setter allows to (re-) use an object adhering to this interface for multiple paint runs.
     * 
     * @param theGc
     *            the {@link GC} to paint on
     */
    void setGC(GC theGc);

    /**
     * Sets the line attributes to use when drawing shapes.
     * 
     * @param attributes of lines when drawing shapes
     */
    void setLineAttributes(LineAttributes attributes);


    /*------------------------------*/
    /* The coloring & style setter. */
    /*------------------------------*/
    
    /**
     * Returns the alpha value currently used by the current {@link GC}.
     * 
     * @author chsch
     * 
     * @return the current alpha value in range of 0 to 255.
     */
    int getAlpha();

    /**
     * Sets the alpha value to be used during the subsequent paint instructions.<br>
     * <b>Caution:</b> This value will also set by Piccolo2D for realizing the transparency. That
     * feature is used in the
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeNodeInActivity
     * FadeNodeInActivity} and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeEdgeInActivity
     * FadeEdgeInActivity} activities. Thus do <b>not</b> set the alpha to an absolute value, but
     * only to someone relative to the current value. The current value can be obtained by
     * {@link #getAlpha()}, the denominator is
     * {@link de.cau.cs.kieler.klighd.KlighdConstants#ALPHA_FULL_OPAQUE
     * KlighdConstants#ALPHA_FULL_OPAQUE}.
     * 
     * @author chsch
     * 
     * @param alpha
     *            the alpha value to be used in range of 0 to 255
     */
    void setAlpha(final int alpha);

    /**
     * Sets the stroke color to the provided {@link RGB} color descriptor.
     * 
     * @author chsch
     * 
     * @param color
     *            new stroke color
     */
    void setStrokeColor(final RGB color);

    /**
     * Sets the stroke color gradient to the provided {@link RGBGradient} descriptor.
     * 
     * @author chsch
     * 
     * @param gradient
     *            new background gradient
     * @param bounds
     *            the local (non-adjusted) bounds within the gradient is to be applied
     */
    void setStrokePattern(final RGBGradient gradient, final Rectangle2D bounds);

    /**
     * Sets the background color to the provided {@link RGB} color descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundColor
     *            new background color
     */
    void setFillColor(final RGB backgroundColor);

    /**
     * Sets the background color gradient to the provided {@link RGBGradient} descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundGradient
     *            new background gradient
     * @param bounds
     *            the local (non-adjusted) bounds within the gradient is to be applied
     */
    void setFillPattern(final RGBGradient backgroundGradient, final Rectangle2D bounds);


    /*-------------------------------*/
    /* The font & text style setter. */
    /*-------------------------------*/
    
    /**
     * Set the font by means of a {@link FontData}.
     * 
     * @param fontData
     *            font configuration to be applied while drawing text
     */
    void setFont(final FontData fontData);

    /**
     * Set the font by means of a {@link FontData} and configures a maximal line width. If the text
     * exceeds {@code maxLineWidth} it is wrapped.<br>
     * <br>
     * <b>Caution: Line wrapping is not supported by KLighD's SVG exporters!!</b>
     *
     * @param fontData
     *            font configuration to be applied while drawing text
     * @param maxLineWidth
     *            the maximal line width in pixels, is ignored if less or equal to zero
     */
    void setFont(final FontData fontData, final int maxLineWidth);
    
    /**
     * Sets the underline for next text to be drawn.
     * 
     * @param theUnderlining
     *            the underline style constant, see {@link org.eclipse.swt.SWT SWT} class
     * @param color
     *            the underline color
     */
    void setUnderline(final int theUnderlining, final RGB color);

    /**
     * Sets the strikeout flag for next text to be drawn.
     * 
     * @param theStrikeout
     *            indicate whether to strike out
     * @param color
     *            the underline color
     */
    void setStrikeout(final boolean theStrikeout, final RGB color);


    /*--------------------------------------------------------*/
    /* Some AffineTransform-related methods for properly      */
    /* realizing shifts, scalings, and rotations of shapes.   */
    /*--------------------------------------------------------*/
    
    /**
     * Returns a copy of the current {@link AffineTransform} of this {@link KlighdSWTGraphics}
     * context.<br>
     * 
     * @return the desired copy.
     */
    AffineTransform getTransform();

    /**
     * Re-initializes the {@link AffineTransform} of this {@link KlighdSWTGraphics} context with the
     * data of the provided {@link AffineTransform} <code>transform</code>.<br>
     * See {@link AffineTransform#setTransform(AffineTransform)} for more details!
     * 
     * @param transform
     *            the <code>AffineTransform</code> that is to be applied
     */
    void setTransform(final AffineTransform transform);
    
    /**
     * Concatenates the {@link AffineTransform} in this {@link KlighdSWTGraphics} context with the
     * provided {@link AffineTransform} <code>transform</code>. Operations of the resulting compound
     * transform are applied according to the rule last-specified-first-applied.<br>
     * See e.g. {@link AffineTransform#concatenate(AffineTransform)} for more details
     * 
     * @param transform
     *            the {@link AffineTransform} object to be appended to the current one.
     */
    void transform(final AffineTransform transform);


    /*-------------------------------------------------------------------------*/
    /* Some clipping-related methods for limiting the screen area to paint on. */
    /*-------------------------------------------------------------------------*/

    /**
     * Gets the current clipping area. Position and size of the are adjusted by the current
     * {@link AffineTransform} data. If no clip has previously been set, or
     * 
     * @return a <code>Shape</code> object representing the current clipping area, or
     *         <code>null</code> if no clip is set (e.g. after it has been cleared through
     *         {@link #setClip(Shape) setClip(null)} or {@link #clip(Shape) clip(null)}).
     */
    Shape getClip();

    /**
     * Sets the current clipping area to the provided {@link Shape} <code>clip</code>.
     * <code>clip</code> will be adjusted by the currently set {@link AffineTransform} of this
     * {@link KlighdSWTGraphics} context. Not all objects that implement the {@link Shape} interface
     * can be used to set the clip. The only {@link Shape} objects that are guaranteed to be
     * supported are {@link Rectangle2D Rectangle2Ds}.
     * 
     * @param clip
     *            the {@link Shape} forming the clip area; if <code>clip</code> is <code>null</code>
     *            this method removes the current clip area
     */
    void setClip(final Shape clip);
    
    /**
     * Intersects the current clip area with the provided {@link Shape} <code>clip</code>.
     * <code>clip</code> will be adjusted by the currently set {@link AffineTransform} of this
     * {@link KlighdSWTGraphics} context. If no clip area has previously been set, or if the clip
     * area has been cleared using {@link #setClip(Shape) setClip(null)}, the specified
     * <code>Shape</code> becomes the new clip.
     * 
     * @param clip
     *            the {@link Shape} to be intersected with the current clip area; if
     *            <code>clip</code> is <code>null</code> this method removes the current clip area
     */
    void clip(final Shape clip);


    /*-----------------------------*/
    /* The drawing/filling methods */
    /*-----------------------------*/

    /**
     * Draws the provided AWT {@link Shape} by relying on the provided
     * {@link java.awt.geom.PathIterator PathIterator} (
     * {@link Shape#getPathIterator(AffineTransform)}) respecting the current AWT transform without
     * any caching.
     * 
     * @param s
     *            the {@link Shape} to be rendered
     */
    void draw(final Shape s);

    /**
     * Draws the provided SWT {@link Path}.
     * 
     * <b>Care</b> this method can only be used if you are sure that an SWT environment is
     * available, i.e., the {@link #getDevice()} method does not return <code>null</code>. Otherwise
     * use {@link #draw(Shape)}.
     * 
     * @param p
     *            the {@link Path} to be rendered
     */
    void draw(final Path p);

    /**
     * Fills the provided AWT {@link Shape} by relying on the provided
     * {@link java.awt.geom.PathIterator PathIterator} (
     * {@link Shape#getPathIterator(AffineTransform)}) respecting the current AWT transform without
     * any caching.
     * 
     * @param s
     *            the <code>Shape</code> to be filled
     */
    void fill(final Shape s);

    /**
     * Fills the provided SWT {@link Path}.
     * 
     * <b>Care</b> this method can only be used if you are sure that an SWT environment is
     * available, i.e., the {@link #getDevice()} method does not return <code>null</code>. Otherwise
     * use {@link #fill(Shape)}.
     * 
     * @param p
     *            the {@link Path} to be rendered
     */
    void fill(final Path p);
    
    /**
     * Draws the provided image at the specified position with the specified width and height.<br>
     * Its position can be determined by means of {@link #setTransform(AffineTransform)}.
     * 
     * <b>Care</b> this method can only be used if you are sure that an SWT environment is
     * available, i.e., the {@link #getDevice()} method does not return <code>null</code>. Otherwise
     * use {@link #drawImage(ImageData, double, double)}.
     * 
     * @param image
     *            {@link Image} to draw
     * @param width
     *            the width of the image drawing
     * @param height
     *            the height of the image drawing
     */
    void drawImage(final Image image, final double width, final double height);
    
    /**
     * Draws the provided image at the specified position with the specified width and height.<br>
     * Its position can be determined by means of {@link #setTransform(AffineTransform)}.
     * 
     * @param imageData
     *            {@link ImageData} to draw
     * @param width
     *            the width of the image drawing
     * @param height
     *            the height of the image drawing
     */
    void drawImage(final ImageData imageData, final double width, final double height);

    /**
     * If supported, add the passed text length value to the text element that is drawn by the next
     * call of {@link #drawText(String)}.
     * 
     * For example for an SVG exporter, create the subsequent {@code <text>} or {@code <tspan>}
     * element with the {@code textLength} property set to the given value.
     * 
     * @param textLength
     *            the textLength value to be set.
     */
    void setNextTextLength(final double textLength);

    /**
     * Draws the provided string while respecting the recently set font & text style settings.<br>
     * Its position can be determined by means of {@link #setTransform(AffineTransform)}.
     * 
     * @param string
     *            the text to be drawn on the canvas
     */
    void drawText(final String string);
    
    
    /* --------------------- Semantic Data ---------------------*/
    
    /**
     * If supported, start a new group and add the passed semantic data to the group.
     * 
     * For instance, for an SVG exporter, create a new group and add the semantic data as
     * attributes. {@code <g class="node">}.
     * 
     * The method must be used in conjunction with {@link #endGroup()}.
     * 
     * @param semanticData
     *            the semantic data to be added.
     */
    void startGroup(final KlighdSemanticDiagramData semanticData);
    
    /**
     * If supported, finish a previously opened group (see
     * {@link #startGroup(KlighdSemanticDiagramData)}).
     */
    void endGroup();
    
    /**
     * If supported, add the passed semantic data to the <b>next</b> element that is drawn by any
     * paint method.
     * 
     * For example for an SVG exporter, if {@link #draw(Path)} is called next, create a
     * {@code <path>} element with the specified semantic data as attributes.
     * 
     * @param semanticData
     *            the semantic data to be added.
     */
    void addSemanticData(final KlighdSemanticDiagramData semanticData);
}
