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
package de.cau.cs.kieler.core.kgraph.text.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KNodeElements;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Semantic sequencer for KGraphs.
 *
 * @author msp
 */
@SuppressWarnings("restriction")
public class KGraphSemanticSequencer extends AbstractKGraphSemanticSequencer {
    
    @Inject
    private KGraphGrammarAccess grammarAccess;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KNode(final EObject context, final KNode knode) {
        KIdentifier identifier = null;
        KShapeLayout nodeLayout = null;
        boolean hasRenderings = false;
        boolean hasRenderingLibraries = false;
        for (KGraphData data : knode.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KShapeLayout) {
                nodeLayout = (KShapeLayout) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            } else if (data instanceof KRenderingLibrary) {
                hasRenderingLibraries = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(knode);
        SequenceFeeder feeder = createSequencerFeeder(knode, nodesProvider);
        KNodeElements knodeAccess = grammarAccess.getKNodeAccess();
        
        // serialize the identifier
        if (identifier != null && identifier.getId() != null && identifier.getId().length() > 0) {
            feeder.accept(knodeAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    knode.getData().indexOf(identifier));
        }
        
        if (nodeLayout != null && nonEmptyLayout(nodeLayout) || !knode.getLabels().isEmpty()
                || !knode.getChildren().isEmpty() || !knode.getPorts().isEmpty()
                || !knode.getOutgoingEdges().isEmpty() || hasRenderings || hasRenderingLibraries) {
            int i;
            
            // serialize the node layout
            if (nodeLayout != null) {
                feeder.accept(knodeAccess.getDataKNodeLayoutParserRuleCall_3_0_1_0(), nodeLayout,
                        knode.getData().indexOf(nodeLayout));
            }
            
            // serialize the node labels
            i = 0;
            for (KLabel label : knode.getLabels()) {
                feeder.accept(knodeAccess.getLabelsKLabelParserRuleCall_3_0_2_0_0(), label, i++);
            }
            
            // serialize the contained child nodes
            i = 0;
            for (KNode child : knode.getChildren()) {
                feeder.accept(knodeAccess.getChildrenKNodeParserRuleCall_3_0_2_1_0(), child, i++);
            }
            
            // serialize the contained ports
            i = 0;
            for (KPort port : knode.getPorts()) {
                feeder.accept(knodeAccess.getPortsKPortParserRuleCall_3_0_2_2_0(), port, i++);
            }
            
            // serialize the outgoing edges
            i = 0;
            for (KEdge edge : knode.getOutgoingEdges()) {
                feeder.accept(knodeAccess.getOutgoingEdgesKEdgeParserRuleCall_3_0_2_3_0(), edge, i++);
            }
            
            // serialize the renderings and rendering libraries
            i = 0;
            for (KGraphData data : knode.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(knodeAccess.getDataKRenderingParserRuleCall_3_0_2_4_0(), data, i);
                } else if (data instanceof KRenderingLibrary) {
                    feeder.accept(knodeAccess.getDataKRenderingLibraryParserRuleCall_3_0_2_5_0(),
                            data, i);
                }
                i++;
            }
        }
        
        feeder.finish();
    }
    
    /**
     * Determine whether the given shape layout is empty, that is all features are set to zero.
     * 
     * @param layout a shape layout
     * @return true if the shape layout is empty
     */
    private static boolean nonEmptyLayout(final KShapeLayout layout) {
        KInsets insets = layout.getInsets();
        return layout.getXpos() != 0 || layout.getYpos() != 0 || layout.getWidth() != 0
                || layout.getHeight() != 0 || !layout.getPersistentEntries().isEmpty()
                || insets != null && (insets.getLeft() != 0 || insets.getRight() != 0
                || insets.getTop() != 0 || insets.getBottom() != 0);
    }
    
}
