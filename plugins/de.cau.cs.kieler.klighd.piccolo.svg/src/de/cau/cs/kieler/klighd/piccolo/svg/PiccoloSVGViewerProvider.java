/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg;

import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerProvider;
import de.cau.cs.kieler.klighd.piccolo.svg.browsing.PiccoloSVGBrowseViewer;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A viewer provider for the Piccolo SVG viewer for KGraphs with attached KRendering data.
 * 
 * @author uru
 */
public class PiccoloSVGViewerProvider implements IViewerProvider<KNode> {

    /** the identifier for this viewer provider as specified in the extension point. */
    public static final String ID = "de.cau.cs.kieler.klighd.piccolo.svg.piccoloViewer";

    /**
     * {@inheritDoc}
     */
    public IViewer<KNode> createViewer(final ContextViewer parentViewer, final Composite parent) {
        return new PiccoloSVGViewer(parentViewer, parent);
//        return new PiccoloSVGBrowseViewer(parent);
    }

    /**
     * {@inheritDoc}
     */
    public Class<KNode> getModelClass() {
        return KNode.class;
    }

}
