/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingUtil;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import edu.umd.cs.piccolo.PNode;

/**
 * @author mri, chsch
 */
public class KLabelRenderingController extends AbstractKGERenderingController<KLabel, KLabelNode> {

    /**
     * Constructs a rendering controller for a label.
     * 
     * @param label
     *            the Piccolo node representing a label
     */
    public KLabelRenderingController(final KLabelNode label) {
        super(label.getGraphElement(), label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        final KLabelNode repNode = getRepresentation();

        // evaluate the rendering data
        final KRendering currentRendering = getCurrentRendering();

        final PNode renderingNode;
        if (currentRendering != null) {
            renderingNode = handleLabelRendering(currentRendering, repNode);
        } else {
            renderingNode = handleLabelRendering(createDefaultLabelRendering(), repNode);
        }

        return renderingNode;
    }

    /**
     * Creates the Piccolo node for a rendering of a {@code KLabel} inside a parent Piccolo node.<br>
     * <br>
     * The rendering has to be a {@code KText} or the method fails.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo label node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleLabelRendering(final KRendering rendering, final KLabelNode parent) {
        // the rendering of a label has to contain exact one KText
        //  that "inherits" the text from the KLabel itself
        if (Iterators.size(Iterators.filter(rendering.eAllContents(), KText.class)) != 1) {
            throw new RuntimeException("KLabel " + getGraphElement()
                    + " must (deeply) contain exactly 1 KText element.");
        }
        
        // create the rendering
        final PNodeController<?> controller = (PNodeController<?>) createRendering(rendering,
                parent, Bounds.of(parent.getBoundsReference()));
        
        final KText kText = Iterators.getNext(
                Iterators.filter(rendering.eAllContents(), KText.class), null); 
        
        @SuppressWarnings("unchecked")
        final PNodeController<KlighdStyledText> textController = (PNodeController<KlighdStyledText>)
                Iterables.getFirst(this.getPNodeController(kText), null);
        
        if (textController != null) {
            // the opposite should never happen (see test above), this test is just for preventing
            //  null pointer exceptions
            
            textController.getNode().setText(parent.getText());
            
            // add a listener on the parent's bend points
            addListener(KLabelNode.PROPERTY_TEXT, parent, controller.getNode(),
                    new PropertyChangeListener() {

                        public void propertyChange(final PropertyChangeEvent e) {
                            textController.getNode().setText(parent.getText());
                            textController.getNode().repaint();
                        }
                    });
        }

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        Bounds bounds = PlacementUtil.evaluateAreaPlacement(KRenderingUtil
                                .asAreaPlacementData(KRenderingUtil.getPlacementData(rendering)),
                                parent.getBoundsReference());
                        
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates a default rendering for labels without attached rendering data.
     * 
     * @return the rendering
     */
    private static KRendering createDefaultLabelRendering() {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KText text = factory.createKText();

        KForeground foreground = factory.createKForeground();
        foreground.setColor(factory.createKColor());
        
        text.getStyles().add(foreground);
        return text;
    }

}
