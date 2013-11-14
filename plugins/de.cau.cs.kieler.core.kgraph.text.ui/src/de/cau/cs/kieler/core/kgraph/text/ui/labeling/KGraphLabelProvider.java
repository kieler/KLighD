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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.text.ui.KGraphUiModule;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KColoring;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
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
     * Create text for a graph element.
     * 
     * @param element a graph element
     * @return the displayed text
     */
    public String text(final KGraphElement element) {
        StringBuilder text = new StringBuilder();
        if (element instanceof KNode && ((KNode) element).getParent() == null) {
            text.append("KGraph");
        } else {
            text.append(element.eClass().getName());
        }
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier != null && identifier.getId() != null && identifier.getId().length() > 0) {
            text.append(" ").append(identifier.getId());
        }
        if (element instanceof KEdge) {
            KNode target = ((KEdge) element).getTarget();
            if (target != null) {
                KIdentifier targetId = target.getData(KIdentifier.class);
                if (targetId != null && targetId.getId() != null && targetId.getId().length() > 0) {
                    text.append(" -> ").append(targetId.getId());
                }
            }
        } else if (element instanceof KLabel) {
            String labelText = ((KLabel) element).getText();
            if (labelText != null && labelText.length() > 0) {
                text.append(" \"").append(labelText).append("\"");
            }
        }
        return text.toString();
    }
    
    /**
     * Create text for a KShapeLayout.
     * 
     * @param shapeLayout a shape layout
     * @return the displayed text
     */
    public String text(final KShapeLayout shapeLayout) {
        return "x=" + shapeLayout.getXpos() + ", y=" + shapeLayout.getYpos()
                + ", w=" + shapeLayout.getWidth() + ", h=" + shapeLayout.getHeight();
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
     * Create text for a style holder.
     * 
     * @param styleHolder a style holder
     * @return the displayed text
     */
    public String text(final KStyleHolder styleHolder) {
        StringBuilder text = new StringBuilder();
        text.append(styleHolder.eClass().getName());
        if (styleHolder.getId() != null && styleHolder.getId().length() > 0) {
            text.append(" ").append(styleHolder.getId());
        }
        if (styleHolder instanceof KRenderingRef) {
            KRendering ref = ((KRenderingRef) styleHolder).getRendering();
            if (ref != null && ref.getId() != null && ref.getId().length() > 0) {
                text.append(" *").append(ref.getId());
            }
        } else if (styleHolder instanceof KText) {
            String labelText = ((KText) styleHolder).getText();
            if (labelText != null && labelText.length() > 0) {
                text.append(" \"").append(labelText).append("\"");
            }
        } else if (styleHolder instanceof KRoundedRectangle) {
            KRoundedRectangle roundedRectangle = (KRoundedRectangle) styleHolder;
            text.append(" (").append(roundedRectangle.getCornerWidth()).append(", ");
            text.append(roundedRectangle.getCornerHeight()).append(")");
        } else if (styleHolder instanceof KArc) {
            KArc arc = (KArc) styleHolder;
            text.append(" (").append(arc.getStartAngle()).append(", ");
            text.append(arc.getArcAngle()).append(")");
        } else if (styleHolder instanceof KCustomRendering) {
            KCustomRendering customRend = (KCustomRendering) styleHolder;
            text.append(" (");
            if (customRend.getBundleName() != null && customRend.getBundleName().length() > 0) {
                text.append(customRend.getBundleName()).append(":");
            }
            text.append(customRend.getClassName()).append(")");
        } else if (styleHolder instanceof KImage) {
            KImage image = (KImage) styleHolder;
            text.append(" (");
            if (image.getBundleName() != null && image.getBundleName().length() > 0) {
                text.append(image.getBundleName()).append(":");
            }
            text.append(image.getImagePath()).append(")");
        }
        return text.toString();
    }
    
    /**
     * Create text for a KRendering library.
     * 
     * @param library a rendering library
     * @return the displayed text
     */
    public String text(final KRenderingLibrary library) {
        return library.eClass().getName();
    }
    
    /**
     * Create generic text for a style.
     * 
     * @param style a style
     * @return the displayed text
     */
    public String text(final KStyle style) {
        StringBuilder text = new StringBuilder();
        text.append(style.eClass().getName().toLowerCase());
        boolean first = true;
        for (EAttribute attribute : style.eClass().getEAttributes()) {
            Object value = style.eGet(attribute);
            if (value != null) {
                if (first) {
                    text.append(" = ");
                    first = false;
                } else {
                    text.append(", ");
                }
                text.append(value.toString());
            }
        }
        return text.toString();
    }
    
    /**
     * Create text for a coloring style.
     * 
     * @param coloring a coloring style
     * @return the displayed text
     */
    public String text(final KColoring<?> coloring) {
        StringBuilder text = new StringBuilder();
        if (coloring instanceof KBackground) {
            text.append("background");
        } else {
            text.append("foreground");
        }
        if (coloring.getColor() != null) {
            KColor color = coloring.getColor();
            text.append(" = ").append(color.getRed()).append("r,");
            text.append(color.getGreen()).append("g,");
            text.append(color.getBlue()).append("b,");
            text.append(coloring.getAlpha()).append("a");
        }
        if (coloring.getTargetColor() != null) {
            KColor color = coloring.getTargetColor();
            text.append(" -> ").append(color.getRed()).append("r,");
            text.append(color.getGreen()).append("g,");
            text.append(color.getBlue()).append("b,");
            text.append(coloring.getTargetAlpha()).append("a");
        }
        return text.toString();
    }
    
    /**
     * Create text for a style reference.
     * 
     * @param styleRef a style reference
     * @return the displayed text
     */
    public String text(final KStyleRef styleRef) {
        if (styleRef.getStyleHolder() != null && styleRef.getStyleHolder().getId() != null) {
            return "reference = " + styleRef.getStyleHolder().getId();
        }
        return "reference";
    }
    
    /**
     * Create text for a placement.
     * 
     * @param placement a placement
     * @return the displayed text
     */
    public String text(final KPlacement placement) {
        return placement.eClass().getName();
    }
    
    /**
     * Create text for a placement data.
     * 
     * @param placementData a placement data
     * @return the displayed text
     */
    public String text(final KPlacementData placementData) {
        return placementData.eClass().getName();
    }
    
    /**
     * Create text for a position.
     * 
     * @param position a position
     * @return the displayed text
     */
    public String text(final KPosition position) {
        StringBuilder text = new StringBuilder();
        if (position.getX() != null) {
            if (position.getX() instanceof KRightPosition) {
                text.append("right ");
            } else {
                text.append("left ");
            }
            text.append(position.getX().getRelative()).append("% + ");
            text.append(position.getX().getAbsolute());
        }
        if (position.getX() != null && position.getY() != null) {
            text.append(", ");
        } else {
            text.append("<undefined position>");
        }
        if (position.getY() != null) {
            if (position.getY() instanceof KBottomPosition) {
                text.append("bottom ");
            } else {
                text.append("top ");
            }
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            text.append(position.getY().getRelative() * 100).append("% + ");
            text.append(position.getY().getAbsolute());
        }
        return text.toString();
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
    
    /**
     * Create an image path for a KText.
     * 
     * @param text a text
     * @return the image descriptor
     */
    public ImageDescriptor image(final KText text) {
        return imageDescriptor("icons/kgraph/klabel.gif");
    }
    
}
