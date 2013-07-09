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

import com.google.common.base.Strings;
import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KEdgeElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KEdgeLayoutElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KFontBoldElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KFontItalicElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KInvisibilityElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KLabelElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KNodeElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KNodeLayoutElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KPolylineElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KPortElements;
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess.KShapeLayoutElements;
import de.cau.cs.kieler.core.krendering.KAction;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Semantic sequencer for KGraphs.
 * 
 * The problems addressed by this manually implemented rules might be due to
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=369175, we'll see.
 *
 * @author msp
 * @author chsch
 */
@SuppressWarnings("restriction")
public class KGraphSemanticSequencer extends AbstractKGraphSemanticSequencer {
    
    private static final KRenderingPackage krenderingPackage = KRenderingPackage.eINSTANCE;
    
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
        } else if (nodeLayout != null) {
            feeder.accept(knodeAccess.getDataEmptyKNodeLayoutParserRuleCall_3_1_0(), nodeLayout,
                    knode.getData().indexOf(nodeLayout));
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KNodeLayout(final EObject context, final KShapeLayout shapeLayout) {
        INodesForEObjectProvider nodesProvider = createNodeProvider(shapeLayout);
        SequenceFeeder feeder = createSequencerFeeder(shapeLayout, nodesProvider);
        KNodeLayoutElements shapeLayoutAccess = grammarAccess.getKNodeLayoutAccess();
        
        // serialize the width and height
        if (shapeLayout.getXpos() != 0) {
            feeder.accept(shapeLayoutAccess.getXposFloatParserRuleCall_0_0_2_0_2_0(),
                    shapeLayout.getXpos());
        }
        if (shapeLayout.getYpos() != 0) {
            feeder.accept(shapeLayoutAccess.getYposFloatParserRuleCall_0_0_2_1_2_0(),
                    shapeLayout.getYpos());
        }
        if (shapeLayout.getWidth() != 0) {
            feeder.accept(shapeLayoutAccess.getWidthFloatParserRuleCall_0_1_2_0_2_0(),
                    shapeLayout.getWidth());
        }
        if (shapeLayout.getHeight() != 0) {
            feeder.accept(shapeLayoutAccess.getHeightFloatParserRuleCall_0_1_2_1_2_0(),
                    shapeLayout.getHeight());
        }
        
        // serialize the properties
        int i = 0;
        for (PersistentEntry entry : shapeLayout.getPersistentEntries()) {
            feeder.accept(shapeLayoutAccess.getPersistentEntriesPersistentEntryParserRuleCall_0_2_2_0(),
                    entry, i++);
        }
        
        // serialize the insets
        KInsets insets = shapeLayout.getInsets();
        if (insets != null && insets.getLeft() != 0 || insets.getRight() != 0 || insets.getTop() != 0
                || insets.getBottom() != 0) {
            feeder.accept(shapeLayoutAccess.getInsetsKInsetsParserRuleCall_1_0_2_0(), insets);
        } else if (insets != null) {
            feeder.accept(shapeLayoutAccess.getInsetsEmptyKInsetsParserRuleCall_1_1_0(), insets);
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KEdge(final EObject context, final KEdge kedge) {
        KIdentifier identifier = null;
        KEdgeLayout edgeLayout = null;
        boolean hasRenderings = false;
        for (KGraphData data : kedge.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KEdgeLayout) {
                edgeLayout = (KEdgeLayout) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(kedge);
        SequenceFeeder feeder = createSequencerFeeder(kedge, nodesProvider);
        KEdgeElements kedgeAccess = grammarAccess.getKEdgeAccess();
        
        // serialize the identifier
        if (identifier != null && identifier.getId() != null && identifier.getId().length() > 0) {
            feeder.accept(kedgeAccess.getDataKIdentifierParserRuleCall_1_0(), identifier,
                    kedge.getData().indexOf(identifier));
        }
        
        // serialize connected elements
        if (kedge.getSourcePort() != null) {
            feeder.accept(kedgeAccess.getSourcePortKPortQualifiedIDParserRuleCall_3_1_0_1(),
                    kedge.getSourcePort());
        }
        if (kedge.getTarget() != null) {
            feeder.accept(kedgeAccess.getTargetKNodeQualifiedIDParserRuleCall_5_0_1(),
                    kedge.getTarget());
        }
        if (kedge.getTargetPort() != null) {
            feeder.accept(kedgeAccess.getTargetPortKPortQualifiedIDParserRuleCall_6_1_0_1(),
                    kedge.getTargetPort());
        }
        
        if (edgeLayout != null && nonEmptyLayout(edgeLayout) || !kedge.getLabels().isEmpty()
                || hasRenderings) {
            int i;
            
            // serialize the edge layout
            if (edgeLayout != null) {
                feeder.accept(kedgeAccess.getDataKEdgeLayoutParserRuleCall_8_0_1_0(), edgeLayout,
                        kedge.getData().indexOf(edgeLayout));
            }
            
            // serialize the edge labels
            i = 0;
            for (KLabel label : kedge.getLabels()) {
                feeder.accept(kedgeAccess.getLabelsKLabelParserRuleCall_8_0_2_0_0(), label, i++);
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : kedge.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(kedgeAccess.getDataKRenderingParserRuleCall_8_0_2_1_0(), data, i);
                }
                i++;
            }
        } else if (edgeLayout != null) {
            feeder.accept(kedgeAccess.getDataEmptyKEdgeLayoutParserRuleCall_8_1_0(), edgeLayout,
                    kedge.getData().indexOf(edgeLayout));
        }
        
        feeder.finish();
    }
    
    /**
     * Determine whether the given edge layout is empty, that is all features are set to zero.
     * 
     * @param layout an edge layout
     * @return true if the shape layout is empty
     */
    private static boolean nonEmptyLayout(final KEdgeLayout layout) {
        KPoint sourcePoint = layout.getSourcePoint();
        KPoint targetPoint = layout.getTargetPoint();
        return sourcePoint != null && (sourcePoint.getX() != 0 || sourcePoint.getY() != 0)
                || targetPoint != null && (targetPoint.getX() != 0 || targetPoint.getY() != 0)
                || !layout.getBendPoints().isEmpty() || !layout.getPersistentEntries().isEmpty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KEdgeLayout(final EObject context, final KEdgeLayout edgeLayout) {
        INodesForEObjectProvider nodesProvider = createNodeProvider(edgeLayout);
        SequenceFeeder feeder = createSequencerFeeder(edgeLayout, nodesProvider);
        KEdgeLayoutElements edgeLayoutAccess = grammarAccess.getKEdgeLayoutAccess();
        
        // serialize the points
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        if (sourcePoint != null && (sourcePoint.getX() != 0 || sourcePoint.getY() != 0)
                || targetPoint != null && (targetPoint.getX() != 0 || targetPoint.getY() != 0)
                || !edgeLayout.getBendPoints().isEmpty()) {
            feeder.accept(edgeLayoutAccess.getSourcePointKPointParserRuleCall_0_0_2_0_0_0(),
                    sourcePoint);
            if (targetPoint != null && (targetPoint.getX() != 0 || targetPoint.getY() != 0)
                || !edgeLayout.getBendPoints().isEmpty()) {
                int i = 0;
                for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                    feeder.accept(
                            edgeLayoutAccess.getBendPointsKPointParserRuleCall_0_0_2_0_1_0_1_0_0(),
                            bendPoint, i++);
                }
                feeder.accept(edgeLayoutAccess.getTargetPointKPointParserRuleCall_0_0_2_0_1_0_2_0(),
                        targetPoint);
            } else {
                feeder.accept(edgeLayoutAccess.getTargetPointEmptyKPointParserRuleCall_0_0_2_0_1_1_0(),
                        targetPoint);
            }
        } else {
            feeder.accept(edgeLayoutAccess.getSourcePointEmptyKPointParserRuleCall_0_1_0_0(),
                    sourcePoint);
            feeder.accept(edgeLayoutAccess.getTargetPointEmptyKPointParserRuleCall_0_1_1_0(),
                    targetPoint);
        }
        
        // serialize the properties
        int i = 0;
        for (PersistentEntry entry : edgeLayout.getPersistentEntries()) {
            feeder.accept(edgeLayoutAccess.getPersistentEntriesPersistentEntryParserRuleCall_1_2_0(),
                    entry, i++);
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KPort(final EObject context, final KPort kport) {
        KIdentifier identifier = null;
        KShapeLayout portLayout = null;
        boolean hasRenderings = false;
        for (KGraphData data : kport.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KShapeLayout) {
                portLayout = (KShapeLayout) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(kport);
        SequenceFeeder feeder = createSequencerFeeder(kport, nodesProvider);
        KPortElements kportAccess = grammarAccess.getKPortAccess();
        
        // serialize the identifier
        if (identifier != null && identifier.getId() != null && identifier.getId().length() > 0) {
            feeder.accept(kportAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    kport.getData().indexOf(identifier));
        }
        
        if (portLayout != null && nonEmptyLayout(portLayout) || !kport.getLabels().isEmpty()
                || hasRenderings) {
            int i;
            
            // serialize the port layout
            if (portLayout != null) {
                feeder.accept(kportAccess.getDataKShapeLayoutParserRuleCall_3_0_1_0(), portLayout,
                        kport.getData().indexOf(portLayout));
            }
            
            // serialize the port labels
            i = 0;
            for (KLabel label : kport.getLabels()) {
                feeder.accept(kportAccess.getLabelsKLabelParserRuleCall_3_0_2_0_0(), label, i++);
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : kport.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(kportAccess.getDataKRenderingParserRuleCall_3_0_2_1_0(), data, i);
                }
                i++;
            }
        } else if (portLayout != null) {
            feeder.accept(kportAccess.getDataEmptyKShapeLayoutParserRuleCall_3_1_0(), portLayout,
                    kport.getData().indexOf(portLayout));
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KShapeLayout(final EObject context, final KShapeLayout shapeLayout) {
        INodesForEObjectProvider nodesProvider = createNodeProvider(shapeLayout);
        SequenceFeeder feeder = createSequencerFeeder(shapeLayout, nodesProvider);
        KShapeLayoutElements shapeLayoutAccess = grammarAccess.getKShapeLayoutAccess();
        
        // serialize the width and height
        if (shapeLayout.getXpos() != 0) {
            feeder.accept(shapeLayoutAccess.getXposFloatParserRuleCall_1_0_2_0_2_0(),
                    shapeLayout.getXpos());
        }
        if (shapeLayout.getYpos() != 0) {
            feeder.accept(shapeLayoutAccess.getYposFloatParserRuleCall_1_0_2_1_2_0(),
                    shapeLayout.getYpos());
        }
        if (shapeLayout.getWidth() != 0) {
            feeder.accept(shapeLayoutAccess.getWidthFloatParserRuleCall_1_1_2_0_2_0(),
                    shapeLayout.getWidth());
        }
        if (shapeLayout.getHeight() != 0) {
            feeder.accept(shapeLayoutAccess.getHeightFloatParserRuleCall_1_1_2_1_2_0(),
                    shapeLayout.getHeight());
        }
        
        // serialize the properties
        int i = 0;
        for (PersistentEntry entry : shapeLayout.getPersistentEntries()) {
            feeder.accept(shapeLayoutAccess.getPersistentEntriesPersistentEntryParserRuleCall_1_2_2_0(),
                    entry, i++);
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KLabel(final EObject context, final KLabel klabel) {
        KIdentifier identifier = null;
        KShapeLayout labelLayout = null;
        boolean hasRenderings = false;
        for (KGraphData data : klabel.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KShapeLayout) {
                labelLayout = (KShapeLayout) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(klabel);
        SequenceFeeder feeder = createSequencerFeeder(klabel, nodesProvider);
        KLabelElements klabelAccess = grammarAccess.getKLabelAccess();
        
        // serialize the identifier
        if (identifier != null && identifier.getId() != null && identifier.getId().length() > 0) {
            feeder.accept(klabelAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    klabel.getData().indexOf(identifier));
        }
        
        // serialize the text
        if (klabel.getText() != null) {
            feeder.accept(klabelAccess.getTextSTRINGTerminalRuleCall_3_0(), klabel.getText());
        }
        
        if (labelLayout != null && nonEmptyLayout(labelLayout) || hasRenderings) {
            int i;
            
            // serialize the label layout
            if (labelLayout != null) {
                feeder.accept(klabelAccess.getDataKShapeLayoutParserRuleCall_4_0_1_0(), labelLayout,
                        klabel.getData().indexOf(labelLayout));
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : klabel.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(klabelAccess.getDataKRenderingParserRuleCall_4_0_2_0(), data, i);
                }
                i++;
            }
        } else if (labelLayout != null) {
            feeder.accept(klabelAccess.getDataEmptyKShapeLayoutParserRuleCall_4_1_0(), labelLayout,
                    klabel.getData().indexOf(labelLayout));
        }
        
        feeder.finish();
    }


    // --------------------------------------------------------------------------------------- //
    // KRendering-specific rules
    
    @Override
    protected void sequence_KPolyline(final EObject context, final KPolyline polyline) {
        INodesForEObjectProvider nodesProvider = createNodeProvider(polyline);
        SequenceFeeder feeder = createSequencerFeeder(polyline, nodesProvider);
        KPolylineElements polylineAccess = grammarAccess.getKPolylineAccess();
        
        if (polyline instanceof KRoundedBendsPolyline) {
            KRoundedBendsPolyline rbp = (KRoundedBendsPolyline) polyline;
            
            if (rbp.eIsSet(krenderingPackage.getKRoundedBendsPolyline_BendRadius())) {
                feeder.accept(grammarAccess.getKRoundedBendsPolylineAccess()
                        .getBendRadiusFloatParserRuleCall_2_1_0(), rbp.getBendRadius());
            }
        }
        
        if (!Strings.isNullOrEmpty(polyline.getId())) {
            feeder.accept(polylineAccess.getIdQualifiedIDParserRuleCall_1_0(), polyline.getId());
        }

        // serialize the properties
        int i = 0;
        for (PersistentEntry entry : polyline.getPersistentEntries()) {
            feeder.accept(polylineAccess.getPersistentEntriesPersistentEntryParserRuleCall_2_1_0(),
                    entry, i++);
        }

        i = 0;
        for (KPosition entry : polyline.getPoints()) {
            if (i == 0) {
                feeder.accept(polylineAccess.getPointsKPositionParserRuleCall_3_1_0_2_0_0(), entry,
                        i++);
            } else {
                feeder.accept(polylineAccess.getPointsKPositionParserRuleCall_3_1_0_2_1_1_0(),
                        entry, i++);
            }
        }

        i = 0;
        for (KStyle entry : polyline.getStyles()) {
            feeder.accept(polylineAccess.getStylesKStyleParserRuleCall_3_1_1_2_0(), entry, i++);
        }

        i = 0;
        for (KAction entry : polyline.getActions()) {
            feeder.accept(polylineAccess.getActionsKActionParserRuleCall_3_1_2_2_0(), entry, i++);
        }

        if (polyline.eIsSet(krenderingPackage.getKRendering_PlacementData())) {
            feeder.accept(polylineAccess.getPlacementDataKPlacementDataParserRuleCall_3_1_3_0(),
                    polyline.getPlacementData());
        }

        if (polyline.eIsSet(krenderingPackage.getKContainerRendering_ChildPlacement())) {
            feeder.accept(polylineAccess.getChildPlacementKPlacementParserRuleCall_3_1_4_0(),
                    polyline.getChildPlacement());
        }

        if (polyline.eIsSet(krenderingPackage.getKPolyline_JointPointRendering())) {
            feeder.accept(polylineAccess.getJointPointRenderingKRenderingParserRuleCall_3_2_1_0(),
                    polyline.getJointPointRendering());
        }

        i = 0;
        for (KRendering entry : polyline.getChildren()) {
            feeder.accept(polylineAccess.getChildrenKRenderingParserRuleCall_3_3_0(), entry, i++);
        }

        feeder.finish();
    }
    
    @Override
    protected void sequence_KSimplePolyline(final EObject context, final KPolyline polyline) {
        sequence_KPolyline(context, polyline);
    }
    
    @Override
    protected void sequence_KPolygon(final EObject context, final KPolygon polyline) {
        sequence_KPolyline(context, polyline);
    }
    
    @Override
    protected void sequence_KRoundedBendsPolyline(final EObject context, final KRoundedBendsPolyline polyline) {
        sequence_KPolyline(context, polyline);
    }
    
    @Override
    protected void sequence_KSpline(final EObject context, final KSpline polyline) {
        sequence_KPolyline(context, polyline);
    }


    // --------------------------------------------------------------------------------------- //
    // KRendering-specific rules
    
    // The following rule is required due to the bug documented in
    //  https://bugs.eclipse.org/bugs/show_bug.cgi?id=412578#c0
    // This basically requires a custom sequence_ method for all styles.
    // We, however, restrict to KFontBold, KFontItalic, and KInvisibility
    //  as 'true' is the default of their attribute fields, other style instances
    //  are used to have an attribute different from the related default value.
    @Override
    protected void sequence_KInvisibility_KStyle(final EObject context, final KInvisibility invisibility) {
        SequenceFeeder feeder = createSequencerFeeder(invisibility,
                createNodeProvider(invisibility));
        KInvisibilityElements invisibilityAccess = grammarAccess.getKInvisibilityAccess();

        if (invisibility.eIsSet(krenderingPackage.getKStyle_PropagateToChildren())) {
            feeder.accept(invisibilityAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        feeder.accept(invisibilityAccess.getInvisibleBOOLEANTerminalRuleCall_3_0(), invisibility.isInvisible());
        
        if (invisibility.eIsSet(krenderingPackage.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), invisibility.getModifierId());
        }
        feeder.finish();
    }
    
    @Override
    protected void sequence_KFontBold_KStyle(final EObject context, final KFontBold fontBold) {
        SequenceFeeder feeder = createSequencerFeeder(fontBold, createNodeProvider(fontBold));
        KFontBoldElements fontBoldAccess = grammarAccess.getKFontBoldAccess();

        if (fontBold.eIsSet(krenderingPackage.getKStyle_PropagateToChildren())) {
            feeder.accept(fontBoldAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        feeder.accept(fontBoldAccess.getBoldBOOLEANTerminalRuleCall_3_0(), fontBold.isBold());
        
        if (fontBold.eIsSet(krenderingPackage.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), fontBold.getModifierId());
        }
        
        feeder.finish();
    }
    
    @Override
    protected void sequence_KFontItalic_KStyle(final EObject context, final KFontItalic fontItalic) {
        SequenceFeeder feeder = createSequencerFeeder(fontItalic, createNodeProvider(fontItalic));
        KFontItalicElements fontItalicAccess = grammarAccess.getKFontItalicAccess();

        if (fontItalic.eIsSet(krenderingPackage.getKStyle_PropagateToChildren())) {
            feeder.accept(fontItalicAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        feeder.accept(fontItalicAccess.getItalicBOOLEANTerminalRuleCall_3_0(), fontItalic.isItalic());
        
        if (fontItalic.eIsSet(krenderingPackage.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), fontItalic.getModifierId());
        }
        
        feeder.finish();
    }
    
}

