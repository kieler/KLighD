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
package de.cau.cs.kieler.klighd.piccolo.test;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Control;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import de.cau.cs.kieler.klighd.krendering.Colors;

/**
 * A dedicated {@link BaseMatcher} matching {@link RGB} color specifications, accepts a delta of 10
 * units per RGB component for complying with anti-aliasing effects.
 *
 * @author chsch
 */
public class ColorMatcher<T> extends BaseMatcher<T> {

    /**
     * Determines the color at pixel (x,y) on the given {@link Control}.
     *
     * @param canvas
     *            the {@link Control} to look on
     * @param absPos
     *            a {@link KVector} denoting the (absolute) position to evaluate,
     *            note that the vectors components will be casted to integer
     *
     * @return the requested color in form of an {@link RGB} instance.
     */
    public static RGB getColorAt(final Control canvas, final KVector absPos) {

        return getColorAt(canvas, (int) absPos.x, (int) absPos.y);
    }

    /**
     * Determines the color at pixel (x,y) on the given {@link Control}.
     *
     * @param canvas the {@link Control} to look on
     * @param x x position
     * @param y y position
     *
     * @return the requested color in form of an {@link RGB} instance.
     */
    public static RGB getColorAt(final Control canvas, final int x, final int y) {
        final Image image = new Image(canvas.getShell().getDisplay(), 1, 1);
        final GC gc = new GC(canvas);
        gc.copyArea(image, x, y);
        gc.dispose();

        final ImageData iData = image.getImageData();
        image.dispose();

        final RGB pixel = iData.palette.getRGB(iData.getPixel(0, 0));
        return pixel;
    }

    /**
     * Factory method for creating {@link ColorMatcher ColorMatchers} accepting {@link RGB RGBs}
     * with components like those in {@code color}
     *
     * @param color
     *            the expect color
     * @return the desired {@link ColorMatcher}
     */
    public static ColorMatcher<RGB> acceptingRGBsExpecting(final Colors color) {
        return new ColorMatcher<RGB>(color);
    }

    /**
     * Factory method for creating {@link ColorMatcher ColorMatchers} accepting {@link Pair Pairs}
     * of a Cont with components like those in {@code color}
     *
     * @param color
     *            the expect color
     * @return the desired {@link ColorMatcher}
     */
    public static ColorMatcher<Pair<Control, KVector>> acceptingPairsOfControlAndKVectorExpecting(final Colors color) {
        return new ColorMatcher<Pair<Control, KVector>>(color);
    }

    private final Colors color;

    /**
     * Constructor.
     */
    public ColorMatcher(final Colors color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    public void describeTo(final Description description) {
        description.appendText("Determined color " + color.toStringWithComponents());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describeMismatch(final Object item, final Description description) {
        if (item instanceof RGB)
            description.appendText("obtained actual color ").appendValue(item);
        else if (item instanceof Pair<?, ?> && ((Pair<?, ?>) item).getFirst() instanceof RGB)
            description.appendText("obtained actual color ")
                    .appendValue(((Pair<?, ?>) item).getFirst());
        else
            description.appendText("obtained ").appendValue(item);
    }

    /**
     * {@inheritDoc}
     */
    public boolean matches(final Object item) {
        if (item instanceof RGB) {
            return areSimilar(color, (RGB) item);

        } else if (item instanceof Pair<?, ?>) {
            @SuppressWarnings("unchecked")
            final Pair<Object, ?> pair = (Pair<Object, ?>) item;

            if (pair.getFirst() instanceof Control && pair.getSecond() instanceof KVector) {
                final RGB actualColor =
                        getColorAt((Control) pair.getFirst(), (KVector) pair.getSecond());
                // write the actual color into the result in order to have access to that
                //  while composing the error message in case of a mismatch
                pair.setFirst(actualColor);
                return areSimilar(color, actualColor);
            }
        };

        return false;
    }

    private boolean areSimilar(final Colors color, final RGB rgb) {
        return Math.abs(color.getRed() - rgb.red) < 11
                && Math.abs(color.getGreen() - rgb.green) < 11
                && Math.abs(color.getBlue() - rgb.blue) < 11;
    }
}