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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A specialized {@link PCamera} used for implementing the magnification lens.<br>
 * This dedicated type is currently only required for ensuring the clipping on graphics level, and for
 * distinguishing the magnification lens camera from the main camera or other cameras, e.g. those
 * employed in {@link KNodeNode KNodeNodes} for properly implementing the diagram clipping, or those
 * employed in {@link KEdgeNode KEdgeNodes} for implementing the junction points.
 *
 * @author chsch
 */
public class KlighdMagnificationLensCamera extends PCamera {

    private static final long serialVersionUID = 7681288801025861377L;

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization is required since the base implementations of
     * {@link PPaintContext#pushClip(java.awt.Shape) PPaintContext#pushClip(Shape)} and
     * {@link PPaintContext#popClip(java.awt.Shape) PPaintContext#popClip(Shape)} are overridden by
     * empty methods in KlighdPaintContext in order to deactivate the regular clipping. (We don't
     * want to have that as it may affect long labels etc.) Thus we have to force clipping here.
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

        // call the forced push clip first since we need clipping at the lens' bounds
        kpc.forcedPushClip(getBoundsReference());

        super.paint(paintContext);

        // restore the former clip state
        kpc.forcedPopClip();
    }
}
