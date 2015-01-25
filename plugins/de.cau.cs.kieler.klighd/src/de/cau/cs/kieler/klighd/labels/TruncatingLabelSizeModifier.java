/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.labels.ILabelSizeModifier;

/**
 * Modifies the size of labels by truncating them once the target width is reached. The rest of the
 * label's text is replaced by an ellipsis.
 * 
 * <p>
 * The label passed to this size modifier is the one from the layout KGraph fed to the layout
 * algorithm, not the one used in KLighD's view model. This means that we need to remember the
 * label's new text somewhere. We actually remember it by modifying the text of the layout graph's
 * label. When applying the layout results,
 * {@link de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager KlighdLayoutManager}
 * checks if the layout graph's label text differs from the text in the view model. If so, it
 * applies the label's new text to a property set on the label
 * ({@link KlighdLabelProperties#LABEL_TEXT_OVERRIDE}) which is then used as the label's text when
 * displaying the label.
 * </p>
 * 
 * @author cds
 */
public final class TruncatingLabelSizeModifier implements ILabelSizeModifier<KLabel> {
    
    /**
     * {@inheritDoc}
     */
    public KVector resizeLabelToWidth(final KLabel label, final double targetWidth) {
        // TODO Implement this properly
        
        // DEBUG START
        System.out.println("Shortening label text '" + label.getText() + "' to 'bla'");
        // DEBUG END
        
        label.setText("bla");
        
        // Shorten the label to half its text to see if this works at all
        KShapeLayout shapeLayout = label.getData(KShapeLayout.class);
        return new KVector(15, shapeLayout.getHeight());
    }
    
}
