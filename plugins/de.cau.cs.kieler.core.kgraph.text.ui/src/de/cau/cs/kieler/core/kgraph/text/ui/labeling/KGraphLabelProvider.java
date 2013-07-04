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
package de.cau.cs.kieler.core.kgraph.text.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.ui.KGraphUiModule;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Label provider for KGraph.
 * 
 * @author msp
 */
public class KGraphLabelProvider extends DefaultEObjectLabelProvider {

    /**
     * Create a KGraph label provider.
     * 
     * @param delegate the delegating label provider
     */
    @Inject
    public KGraphLabelProvider(final AdapterFactoryLabelProvider delegate) {
        super(delegate);
    }
    
    
    //~~~~~~~~~~~~~~~~ TEXT OUTPUT
    
    /**
     * Create text for a KLabel.
     * 
     * @param label a label
     * @return the displayed text
     */
    public String text(final KLabel label) {
        KIdentifier identifier = label.getData(KIdentifier.class);
        if (identifier != null) {
            return "KLabel " + identifier.getId();
        }
        return "KLabel " + label.getText();
    }
    
    /**
     * Create text for a labeled graph element.
     * 
     * @param element a labeled graph element
     * @return the displayed text
     */
    public String text(final KLabeledGraphElement element) {
        String className = element.eClass().getName();
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier != null) {
            return className + " " + identifier.getId();
        }
        for (KLabel label : element.getLabels()) {
            if (label.getText() != null) {
                return className + " " + label.getText();
            }
        }
        return className;
    }
    
    /**
     * Create text for a KShapeLayout.
     * 
     * @param shapeLayout a shape layout
     * @return the displayed text
     */
    public String text(final KShapeLayout shapeLayout) {
        return "x=" + shapeLayout.getXpos() + ",y=" + shapeLayout.getYpos()
                + " width=" + shapeLayout.getWidth() + ",height=" + shapeLayout.getHeight();
    }
    
    /**
     * Create text for a KEdgeLayout.
     * 
     * @param edgeLayout an edge layout
     * @return the displayed text
     */
    public String text(final KEdgeLayout edgeLayout) {
        return edgeLayout.createVectorChain().toString();
    }
    
    /**
     * Create text for a persistent entry.
     * 
     * @param entry a persistent entry
     * @return the displayed text
     */
    public String text(final PersistentEntry entry) {
        if (entry.getKey() != null && entry.getValue() != null) {
            return entry.getKey() + "=" + entry.getValue();
        }
        return null;
    }
    
    /**
     * Create text for a KPoint.
     * 
     * @param point a point
     * @return the displayed text
     */
    public String text(final KPoint point) {
        return point.getX() + "," + point.getY();
    }
    
    
    //~~~~~~~~~~~~~~~~ IMAGE OUTPUT

    /**
     * Create an image descriptor for the given path.
     * 
     * @param path an image path
     * @return an image descriptor
     */
    private static ImageDescriptor imageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(KGraphUiModule.PLUGIN_ID, path);
    }
    
    /**
     * Create an image path for a KNode.
     * 
     * @param node a node
     * @return the image descriptor
     */
    public ImageDescriptor image(final KNode node) {
        return imageDescriptor("icons/kgraph/knode.gif");
    }
    
    /**
     * Create an image path for a KEdge.
     * 
     * @param edge an edge
     * @return the image descriptor
     */
    public ImageDescriptor image(final KEdge edge) {
        return imageDescriptor("icons/kgraph/kedge.gif");
    }
    
    /**
     * Create an image path for a KPort.
     * 
     * @param port a port
     * @return the image descriptor
     */
    public ImageDescriptor image(final KPort port) {
        return imageDescriptor("icons/kgraph/kport.gif");
    }
    
    /**
     * Create an image path for a KLabel.
     * 
     * @param label a label
     * @return the image descriptor
     */
    public ImageDescriptor image(final KLabel label) {
        return imageDescriptor("icons/kgraph/klabel.gif");
    }
    
    /**
     * Create an image path for a KRendering (fallback image for all renderings that have no
     * specific image assigned).
     * 
     * @param rendering a rendering
     * @return the image descriptor
     */
    public ImageDescriptor image(final KRendering rendering) {
        return imageDescriptor("icons/krendering/krendering.gif");
    }
    
    /**
     * Create an image path for a KArc.
     * 
     * @param arc an arc
     * @return the image descriptor
     */
    public ImageDescriptor image(final KArc arc) {
        return imageDescriptor("icons/krendering/karc.gif");
    }
    
    /**
     * Create an image path for a KChildArea.
     * 
     * @param childArea a child area
     * @return the image descriptor
     */
    public ImageDescriptor image(final KChildArea childArea) {
        return imageDescriptor("icons/krendering/kchild-area.gif");
    }
    
    /**
     * Create an image path for a KEllipse.
     * 
     * @param ellipse an ellipse
     * @return the image descriptor
     */
    public ImageDescriptor image(final KEllipse ellipse) {
        return imageDescriptor("icons/krendering/kellipse.gif");
    }
    
    /**
     * Create an image path for a KPolygon.
     * 
     * @param polygon a polygon
     * @return the image descriptor
     */
    public ImageDescriptor image(final KPolygon polygon) {
        return imageDescriptor("icons/krendering/kpolygon.gif");
    }
    
    /**
     * Create an image path for a KPolyline.
     * 
     * @param polyline a polyline
     * @return the image descriptor
     */
    public ImageDescriptor image(final KPolyline polyline) {
        return imageDescriptor("icons/krendering/kpolyline.gif");
    }
    
    /**
     * Create an image path for a KRectangle.
     * 
     * @param rectangle a rectangle
     * @return the image descriptor
     */
    public ImageDescriptor image(final KRectangle rectangle) {
        return imageDescriptor("icons/krendering/krectangle.gif");
    }
    
    /**
     * Create an image path for a KRenderingRef.
     * 
     * @param reference a rendering reference
     * @return the image descriptor
     */
    public ImageDescriptor image(final KRenderingRef reference) {
        return imageDescriptor("icons/krendering/kreference.gif");
    }
    
    /**
     * Create an image path for a KRenderingLibrary.
     * 
     * @param library a rendering library
     * @return the image descriptor
     */
    public ImageDescriptor image(final KRenderingLibrary library) {
        return imageDescriptor("icons/krendering/krendering-library.gif");
    }
    
    /**
     * Create an image path for a KRoundedRectangle.
     * 
     * @param roundedRectangle a rounded rectangle
     * @return the image descriptor
     */
    public ImageDescriptor image(final KRoundedRectangle roundedRectangle) {
        return imageDescriptor("icons/krendering/krounded-rectangle.gif");
    }
    
    /**
     * Create an image path for a KSpline.
     * 
     * @param spline a spline
     * @return the image descriptor
     */
    public ImageDescriptor image(final KSpline spline) {
        return imageDescriptor("icons/krendering/kspline.gif");
    }
    
}
