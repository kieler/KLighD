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
package de.cau.cs.kieler.klighd.piccolo.svg;

import de.cau.cs.kieler.core.kgraph.KNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * @author uru
 * 
 */
public class WrappedKNodeNode extends PNode {

    KNode node;
    PNode view;

    /**
     * 
     */
    public WrappedKNodeNode(PNode view, KNode node) {
        this.node = node;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(PPaintContext paintContext) {
        //super.paint(paintContext);
//        System.out.println("Painting Wrapper");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(PPaintContext paintContext) {
        // TODO Auto-generated method stub
        super.fullPaint(paintContext);
        if(paintContext.getGraphics() instanceof KlighdSVGGraphicsImpl) {
//            System.out.println("Painting Wrapper");
            
            KlighdSVGGraphicsImpl g = (KlighdSVGGraphicsImpl) paintContext.getGraphics();
//            g.setColor(Color.BLACK);
            int oldAlpha = g.getAlpha();
            g.setAlpha(0);
            //g.drawRect(view.getX(), view.getY(), view.getWidth(), view.getHeight());
            g.drawText("de.cau.cs.kieler.id:" + node.hashCode(), 0, 0);
            g.setAlpha(oldAlpha);
            
//            Element root = g.getDocument().getDocumentElement();
////            Node elem = g.getDocument().getDocumentElement();
//            Node  elem = g.getSVGGraphics().getRoot(root);
//            while (elem.getLastChild() != null) {
//                elem = elem.getLastChild();
//            }
//            Element lastElement = (Element) elem;
//            lastElement.setAttribute("idb", 4 + "");
//            System.out.println(lastElement + " " + lastElement.getAttribute("idb"));
            
        }
        
    }
}
