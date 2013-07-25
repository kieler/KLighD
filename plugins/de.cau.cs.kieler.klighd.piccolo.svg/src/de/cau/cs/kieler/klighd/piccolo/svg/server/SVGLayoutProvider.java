/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg.server;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.svg.PiccoloSVGBrowseViewer;

/**
 * @author uru
 * 
 * 
 */
@SuppressWarnings("restriction")
public class SVGLayoutProvider {

    // KlighD and Layout facilities
    private KlighdLayoutManager mng;

    private static SVGLayoutProvider instance;

    /**
     * 
     */
    private SVGLayoutProvider() {
        mng = new KlighdLayoutManager();
    }

    public static SVGLayoutProvider getInstance() {
        if (instance == null) {
            instance = new SVGLayoutProvider();
        }
        return instance;
    }

    public void layout(final PiccoloSVGBrowseViewer viewer) {
        // build the layout mapping
        KNode model = viewer.getModel();
        // initially the viewer might not have a model set
        if (model == null) {
            return;
        }
        LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(null, model);
        mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);

        // perform the layout
        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
        mng.applyLayout(mapping, true, 0);

        // redraw
        viewer.globalRedraw();
    }

    public String getSVG(final PiccoloSVGBrowseViewer viewer) {
        String data = viewer.getGraphics().getSVG();

        // insert an id for the first group element
        String res3 = data.substring(data.indexOf("<svg") - 1, data.length());
        String res4 = res3.replaceFirst("width=", "w=");
        String res5 = res4.replaceFirst("height=", "w=");

        StringBuffer sb = new StringBuffer(res5);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        if (viewer.getSvgTransform() != null) {
            sb.insert(sb.indexOf("<g") + 2, " transform=\"" + viewer.getSvgTransform() + "\"");
        }

        return sb.toString();
    }

    public String layoutAndGetSVG(final PiccoloSVGBrowseViewer viewer) {
        layout(viewer);
        return getSVG(viewer);
    }
}
