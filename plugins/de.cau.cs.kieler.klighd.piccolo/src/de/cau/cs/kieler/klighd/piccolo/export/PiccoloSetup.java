/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2023 by
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
package de.cau.cs.kieler.klighd.piccolo.export;

import de.cau.cs.kieler.klighd.IKlighdStartupHook;
import de.cau.cs.kieler.klighd.IOffscreenRenderer;
import de.cau.cs.kieler.klighd.KlighdDataManager;

/**
 * Setup registering Piccolo offscreen renderers.
 * 
 * @author nre
 */
public class PiccoloSetup implements IKlighdStartupHook {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        KlighdDataManager.getInstance()
        .registerOffscreenRenderer(BitmapOffscreenRenderer.ID, new BitmapOffscreenRenderer(),
                IOffscreenRenderer.BMP, IOffscreenRenderer.JPEG, IOffscreenRenderer.PNG)
        .registerOffscreenRenderer(SVGOffscreenRenderer.ID, new SVGOffscreenRenderer(),
                IOffscreenRenderer.SVG);
    }
}