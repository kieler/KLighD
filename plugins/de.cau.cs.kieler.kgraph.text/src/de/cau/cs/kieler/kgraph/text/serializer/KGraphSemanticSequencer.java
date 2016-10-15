/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text.serializer;

import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;

import com.google.common.base.Strings;
import com.google.inject.Inject;

import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KEdgeElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KFontBoldElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KFontItalicElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KInvisibilityElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KLabelElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KNodeElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KPolylineElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.KPortElements;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess.ParentKNodeElements;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;
import de.cau.cs.kieler.klighd.krendering.KAction;
import de.cau.cs.kieler.klighd.krendering.KFontBold;
import de.cau.cs.kieler.klighd.krendering.KFontItalic;
import de.cau.cs.kieler.klighd.krendering.KInvisibility;
import de.cau.cs.kieler.klighd.krendering.KPolygon;
import de.cau.cs.kieler.klighd.krendering.KPolyline;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.klighd.krendering.KSpline;
import de.cau.cs.kieler.klighd.krendering.KStyle;

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
    
    @Inject
    private KGraphGrammarAccess grammarAccess;
    
    // CHECKSTYLEOFF MethodName
    
    // --------------------------------------------------------------------------------------- //
    // KGraph
    private static final KGraphPackage KGRAPH_PKG = KGraphPackage.eINSTANCE;
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_ParentKNode(final ISerializationContext context, final KNode knode) {
        KIdentifier identifier = null;
        for (KGraphData data : knode.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(knode);
        SequenceFeeder feeder = createSequencerFeeder(knode, nodesProvider);
        ParentKNodeElements knodeAccess = grammarAccess.getParentKNodeAccess();
        
        // serialize the identifier
        if (identifier != null && !Strings.isNullOrEmpty(identifier.getId())) {
            feeder.accept(knodeAccess.getDataKIdentifierParserRuleCall_1_1_0(), identifier,
                    knode.getData().indexOf(identifier));
        }
        
        int i;
        
        // serialize the node layout
        if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Xpos())) {
            feeder.accept(knodeAccess.getXposFloatParserRuleCall_2_0_2_0_2_0(),
                    knode.getXpos());
        }
        if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Ypos())) {
            feeder.accept(knodeAccess.getYposFloatParserRuleCall_2_0_2_1_2_0(),
                    knode.getYpos());
        }
        if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Width())) {
            feeder.accept(knodeAccess.getWidthFloatParserRuleCall_2_1_2_0_2_0(),
                    knode.getWidth());
        }
        if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Height())) {
            feeder.accept(knodeAccess.getHeightFloatParserRuleCall_2_1_2_1_2_0(),
                    knode.getHeight());
        }
        
        // serialize the properties
        i = 0;
        for (PersistentEntry entry : knode.getPersistentEntries()) {
            feeder.accept(knodeAccess.getPersistentEntriesPropertyParserRuleCall_2_2_2_0(),
                    entry, i++);
        }
        
        // serialize the insets
        KInsets insets = knode.getInsets();
        if (insets != null && insets.getLeft() != 0 || insets.getRight() != 0
                || insets.getTop() != 0 || insets.getBottom() != 0) {
            feeder.accept(knodeAccess.getInsetsKInsetsParserRuleCall_3_0_2_0(), insets);
        } else if (insets != null) {
            feeder.accept(knodeAccess.getInsetsEmptyKInsetsParserRuleCall_3_1_0(), insets);
        }
        
        // serialize the node labels
        i = 0;
        for (KLabel label : knode.getLabels()) {
            feeder.accept(knodeAccess.getLabelsKLabelParserRuleCall_4_0_0(), label, i++);
        }
        
        // serialize the contained child nodes
        i = 0;
        for (KNode child : knode.getChildren()) {
            feeder.accept(knodeAccess.getChildrenKNodeParserRuleCall_4_1_0(), child, i++);
        }
        
        // serialize the contained ports
        i = 0;
        for (KPort port : knode.getPorts()) {
            feeder.accept(knodeAccess.getPortsKPortParserRuleCall_4_2_0(), port, i++);
        }
        
        // serialize the outgoing edges
        i = 0;
        for (KEdge edge : knode.getOutgoingEdges()) {
            feeder.accept(knodeAccess.getOutgoingEdgesKEdgeParserRuleCall_4_3_0(), edge, i++);
        }
        
        // serialize the renderings and rendering libraries
        i = 0;
        for (KGraphData data : knode.getData()) {
            if (data instanceof KRendering) {
                feeder.accept(knodeAccess.getDataKRenderingLibraryParserRuleCall_4_5_0(), data, i);
            } else if (data instanceof KRenderingLibrary) {
                feeder.accept(knodeAccess.getDataKRenderingLibraryParserRuleCall_4_5_0(), data, i);
            }
            i++;
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KNode(final ISerializationContext context, final KNode knode) {
        KIdentifier identifier = null;
        boolean hasRenderings = false;
        boolean hasRenderingLibraries = false;
        for (KGraphData data : knode.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
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
        if (identifier != null && !Strings.isNullOrEmpty(identifier.getId())) {
            feeder.accept(knodeAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    knode.getData().indexOf(identifier));
        }
        
        if (nonEmptyLayout(knode) || !knode.getLabels().isEmpty()
                || !knode.getChildren().isEmpty() || !knode.getPorts().isEmpty()
                || !knode.getOutgoingEdges().isEmpty() || hasRenderings || hasRenderingLibraries) {
            int i;
            
            // serialize the node layout
            if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Xpos())) {
                feeder.accept(knodeAccess.getXposFloatParserRuleCall_3_1_0_2_0_2_0(),
                        knode.getXpos());
            }
            if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Ypos())) {
                feeder.accept(knodeAccess.getYposFloatParserRuleCall_3_1_0_2_1_2_0(),
                        knode.getYpos());
            }
            if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Width())) {
                feeder.accept(knodeAccess.getWidthFloatParserRuleCall_3_1_1_2_0_2_0(),
                        knode.getWidth());
            }
            if (knode.eIsSet(KGRAPH_PKG.getKShapeLayout_Height())) {
                feeder.accept(knodeAccess.getHeightFloatParserRuleCall_3_1_1_2_1_2_0(),
                        knode.getHeight());
            }
            
            // serialize the properties
            i = 0;
            for (PersistentEntry entry : knode.getPersistentEntries()) {
                feeder.accept(knodeAccess.getPersistentEntriesPropertyParserRuleCall_3_1_2_2_0(),
                        entry, i++);
            }
            
            // serialize the insets
            KInsets insets = knode.getInsets();
            if (insets != null && insets.getLeft() != 0 || insets.getRight() != 0
                    || insets.getTop() != 0 || insets.getBottom() != 0) {
                feeder.accept(knodeAccess.getInsetsKInsetsParserRuleCall_3_2_0_2_0(), insets);
            } else if (insets != null) {
                feeder.accept(knodeAccess.getInsetsEmptyKInsetsParserRuleCall_3_2_1_0(), insets);
            }
            
            // serialize the node labels
            i = 0;
            for (KLabel label : knode.getLabels()) {
                feeder.accept(knodeAccess.getLabelsKLabelParserRuleCall_3_3_0_0(), label, i++);
            }
            
            // serialize the contained child nodes
            i = 0;
            for (KNode child : knode.getChildren()) {
                feeder.accept(knodeAccess.getChildrenKNodeParserRuleCall_3_3_1_0(), child, i++);
            }
            
            // serialize the contained ports
            i = 0;
            for (KPort port : knode.getPorts()) {
                feeder.accept(knodeAccess.getPortsKPortParserRuleCall_3_3_2_0(), port, i++);
            }
            
            // serialize the outgoing edges
            i = 0;
            for (KEdge edge : knode.getOutgoingEdges()) {
                feeder.accept(knodeAccess.getOutgoingEdgesKEdgeParserRuleCall_3_3_3_0(), edge, i++);
            }
            
            // serialize the renderings and rendering libraries
            i = 0;
            for (KGraphData data : knode.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(knodeAccess.getDataKRenderingParserRuleCall_3_3_4_0(), data, i);
                } else if (data instanceof KRenderingLibrary) {
                    feeder.accept(knodeAccess.getDataKRenderingLibraryParserRuleCall_3_3_5_0(),
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KEdge(final ISerializationContext context, final KEdge kedge) {
        KIdentifier identifier = null;
        boolean hasRenderings = false;
        for (KGraphData data : kedge.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(kedge);
        SequenceFeeder feeder = createSequencerFeeder(kedge, nodesProvider);
        KEdgeElements kedgeAccess = grammarAccess.getKEdgeAccess();
        
        // serialize the identifier
        if (identifier != null && !Strings.isNullOrEmpty(identifier.getId())) {
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
        
        if (nonEmptyLayout(kedge) || !kedge.getLabels().isEmpty() || hasRenderings) {
            int i;
            
            // serialize the points
            KPoint sourcePoint = kedge.getSourcePoint();
            KPoint targetPoint = kedge.getTargetPoint();
            if (sourcePoint != null && (sourcePoint.getX() != 0 || sourcePoint.getY() != 0)
                    || targetPoint != null && (targetPoint.getX() != 0 || targetPoint.getY() != 0)
                    || !kedge.getBendPoints().isEmpty()) {
                feeder.accept(kedgeAccess.getSourcePointKPointParserRuleCall_8_1_0_2_0_0_0(),
                        sourcePoint);
                if (targetPoint != null && (targetPoint.getX() != 0 || targetPoint.getY() != 0)
                    || !kedge.getBendPoints().isEmpty()) {
                    
                    i = 0;
                    for (KPoint bendPoint : kedge.getBendPoints()) {
                        feeder.accept(
                                kedgeAccess.getBendPointsKPointParserRuleCall_8_1_0_2_0_1_0_1_0_0(),
                                bendPoint, i++);
                    }
                    feeder.accept(kedgeAccess.getTargetPointKPointParserRuleCall_8_1_0_2_0_1_0_2_0(),
                            targetPoint);
                } else {
                    // TODO Multiple options for parser rule call, not sure which is correct
                    feeder.accept(kedgeAccess.getTargetPointEmptyKPointParserRuleCall_8_1_1_1_0(),
                            targetPoint);
                }
            } else {
                // TODO Multiple options for parser rule call, not sure which is correct
                feeder.accept(kedgeAccess.getSourcePointEmptyKPointParserRuleCall_8_1_1_0_0(),
                        sourcePoint);
                // TODO Multiple options for parser rule call, not sure which is correct
                feeder.accept(kedgeAccess.getTargetPointEmptyKPointParserRuleCall_8_1_1_1_0(),
                        targetPoint);
            }
            
            // serialize the properties
            i = 0;
            for (PersistentEntry entry : kedge.getPersistentEntries()) {
                feeder.accept(kedgeAccess.getPersistentEntriesPropertyParserRuleCall_8_2_2_0(),
                        entry, i++);
            }
            
            // serialize the edge labels
            i = 0;
            for (KLabel label : kedge.getLabels()) {
                feeder.accept(kedgeAccess.getLabelsKLabelParserRuleCall_8_3_0_0(), label, i++);
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : kedge.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(kedgeAccess.getDataKRenderingParserRuleCall_8_3_1_0(), data, i);
                }
                i++;
            }
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
    protected void sequence_KPort(final ISerializationContext context, final KPort kport) {
        KIdentifier identifier = null;
        boolean hasRenderings = false;
        for (KGraphData data : kport.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(kport);
        SequenceFeeder feeder = createSequencerFeeder(kport, nodesProvider);
        KPortElements kportAccess = grammarAccess.getKPortAccess();
        
        // serialize the identifier
        if (identifier != null && !Strings.isNullOrEmpty(identifier.getId())) {
            feeder.accept(kportAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    kport.getData().indexOf(identifier));
        }
        
        if (nonEmptyLayout(kport) || !kport.getLabels().isEmpty() || hasRenderings) {
            int i;
            
            // serialize the width and height
            if (kport.eIsSet(KGRAPH_PKG.getKShapeLayout_Xpos())) {
                feeder.accept(kportAccess.getXposFloatParserRuleCall_3_1_0_2_0_2_0(),
                        kport.getXpos());
            }
            if (kport.eIsSet(KGRAPH_PKG.getKShapeLayout_Ypos())) {
                feeder.accept(kportAccess.getYposFloatParserRuleCall_3_1_0_2_1_2_0(),
                        kport.getYpos());
            }
            if (kport.eIsSet(KGRAPH_PKG.getKShapeLayout_Width())) {
                feeder.accept(kportAccess.getWidthFloatParserRuleCall_3_1_1_2_0_2_0(),
                        kport.getWidth());
            }
            if (kport.eIsSet(KGRAPH_PKG.getKShapeLayout_Height())) {
                feeder.accept(kportAccess.getHeightFloatParserRuleCall_3_1_1_2_1_2_0(),
                        kport.getHeight());
            }
            
            // serialize the properties
            i = 0;
            for (PersistentEntry entry : kport.getPersistentEntries()) {
                feeder.accept(kportAccess.getPersistentEntriesPropertyParserRuleCall_3_1_2_2_0(),
                        entry, i++);
            }
            
            // serialize the port labels
            i = 0;
            for (KLabel label : kport.getLabels()) {
                feeder.accept(kportAccess.getLabelsKLabelParserRuleCall_3_2_0_0(), label, i++);
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : kport.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(kportAccess.getDataKRenderingParserRuleCall_3_2_1_0(), data, i);
                }
                i++;
            }
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KLabel(final ISerializationContext context, final KLabel klabel) {
        KIdentifier identifier = null;
        boolean hasRenderings = false;
        for (KGraphData data : klabel.getData()) {
            if (data instanceof KIdentifier) {
                identifier = (KIdentifier) data;
            } else if (data instanceof KRendering) {
                hasRenderings = true;
            }
        }
        
        INodesForEObjectProvider nodesProvider = createNodeProvider(klabel);
        SequenceFeeder feeder = createSequencerFeeder(klabel, nodesProvider);
        KLabelElements klabelAccess = grammarAccess.getKLabelAccess();
        
        // serialize the identifier
        if (identifier != null && !Strings.isNullOrEmpty(identifier.getId())) {
            feeder.accept(klabelAccess.getDataKIdentifierParserRuleCall_2_0(), identifier,
                    klabel.getData().indexOf(identifier));
        }
        
        // serialize the text
        if (klabel.getText() != null) {
            feeder.accept(klabelAccess.getTextSTRINGTerminalRuleCall_3_0(), klabel.getText());
        }
        
        if (nonEmptyLayout(klabel) || hasRenderings) {
            int i;
            
            // serialize the width and height
            if (klabel.eIsSet(KGRAPH_PKG.getKShapeLayout_Xpos())) {
                feeder.accept(klabelAccess.getXposFloatParserRuleCall_4_1_0_2_0_2_0(),
                        klabel.getXpos());
            }
            if (klabel.eIsSet(KGRAPH_PKG.getKShapeLayout_Ypos())) {
                feeder.accept(klabelAccess.getYposFloatParserRuleCall_4_1_0_2_1_2_0(),
                        klabel.getYpos());
            }
            if (klabel.eIsSet(KGRAPH_PKG.getKShapeLayout_Width())) {
                feeder.accept(klabelAccess.getWidthFloatParserRuleCall_4_1_1_2_0_2_0(),
                        klabel.getWidth());
            }
            if (klabel.eIsSet(KGRAPH_PKG.getKShapeLayout_Height())) {
                feeder.accept(klabelAccess.getHeightFloatParserRuleCall_4_1_1_2_1_2_0(),
                        klabel.getHeight());
            }
            
            // serialize the properties
            i = 0;
            for (PersistentEntry entry : klabel.getPersistentEntries()) {
                feeder.accept(klabelAccess.getPersistentEntriesPropertyParserRuleCall_4_1_2_2_0(),
                        entry, i++);
            }
            
            // serialize the renderings
            i = 0;
            for (KGraphData data : klabel.getData()) {
                if (data instanceof KRendering) {
                    feeder.accept(klabelAccess.getDataKRenderingParserRuleCall_4_2_0(), data, i);
                }
                i++;
            }
        }
        
        feeder.finish();
    }


    // --------------------------------------------------------------------------------------- //
    // KRendering-specific rules

    private static final KRenderingPackage KRENDERING_PKG = KRenderingPackage.eINSTANCE;
    
    @Override
    protected void sequence_KPolyline_KSimplePolyline(ISerializationContext context,
            KPolyline polyline) {
        INodesForEObjectProvider nodesProvider = createNodeProvider(polyline);
        SequenceFeeder feeder = createSequencerFeeder(polyline, nodesProvider);
        KPolylineElements polylineAccess = grammarAccess.getKPolylineAccess();
        
        if (polyline instanceof KRoundedBendsPolyline) {
            KRoundedBendsPolyline rbp = (KRoundedBendsPolyline) polyline;
            
            if (rbp.eIsSet(KRENDERING_PKG.getKRoundedBendsPolyline_BendRadius())) {
                feeder.accept(grammarAccess.getKRoundedBendsPolylineAccess()
                        .getBendRadiusFloatParserRuleCall_2_1_0(), rbp.getBendRadius());
            }
        }
        
        if (!Strings.isNullOrEmpty(polyline.getId())) {
            feeder.accept(polylineAccess.getIdQualifiedIDParserRuleCall_1_0(), polyline.getId());
        }

        // serialize the properties
        int i = 0;
        for (de.cau.cs.kieler.klighd.kgraph.PersistentEntry entry : polyline.getPersistentEntries()) {
            feeder.accept(polylineAccess.getPersistentEntriesPropertyParserRuleCall_2_1_0(),
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

        if (polyline.eIsSet(KRENDERING_PKG.getKRendering_PlacementData())) {
            feeder.accept(polylineAccess.getPlacementDataKPlacementDataParserRuleCall_3_1_3_0(),
                    polyline.getPlacementData());
        }

        if (polyline.eIsSet(KRENDERING_PKG.getKContainerRendering_ChildPlacement())) {
            feeder.accept(polylineAccess.getChildPlacementKPlacementParserRuleCall_3_1_4_0(),
                    polyline.getChildPlacement());
        }

        if (polyline.eIsSet(KRENDERING_PKG.getKPolyline_JunctionPointRendering())) {
            feeder.accept(polylineAccess.getJunctionPointRenderingKRenderingParserRuleCall_3_2_1_0(),
                    polyline.getJunctionPointRendering());
        }

        i = 0;
        for (KRendering entry : polyline.getChildren()) {
            feeder.accept(polylineAccess.getChildrenKRenderingParserRuleCall_3_3_0(), entry, i++);
        }

        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KPolygon_KPolyline(ISerializationContext context,
            KPolygon polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KPolyline_KRoundedBendsPolyline(final ISerializationContext context,
            final KRoundedBendsPolyline polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    protected void sequence_KPolyline_KSpline(final ISerializationContext context, final KSpline polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KSimplePolyline(final ISerializationContext context, final KPolyline polyline) {
        sequence_KPolyline_KSimplePolyline(context, polyline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KPolygon(final ISerializationContext context, final KPolygon polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KRoundedBendsPolyline(final ISerializationContext context,
            final KRoundedBendsPolyline polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KSpline(final ISerializationContext context, final KSpline polyline) {
        sequence_KPolyline_KSimplePolyline(context, (KPolyline) polyline);
    }

    /**
     * {@inheritDoc}
     * The following rule is required due to the bug documented in
     *     https://bugs.eclipse.org/bugs/show_bug.cgi?id=412578#c0
     * This basically requires a custom sequence_ method for all styles.
     * We, however, restrict to KFontBold, KFontItalic, and KInvisibility
     * as 'true' is the default of their attribute fields, other style instances
     * are used to have an attribute different from the related default value.
     */
    @Override
    protected void sequence_KInvisibility_KStyle(final ISerializationContext context,
            final KInvisibility invisibility) {
        SequenceFeeder feeder = createSequencerFeeder(invisibility,
                createNodeProvider(invisibility));
        KInvisibilityElements invisibilityAccess = grammarAccess.getKInvisibilityAccess();

        if (invisibility.eIsSet(KRENDERING_PKG.getKStyle_PropagateToChildren())) {
            feeder.accept(invisibilityAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        if (invisibility.eIsSet(KRENDERING_PKG.getKStyle_Selection())) {
            feeder.accept(invisibilityAccess.getSelectionSelectionKeyword_1_0());
        }
        
        feeder.accept(invisibilityAccess.getInvisibleBOOLEANTerminalRuleCall_4_0(),
                invisibility.isInvisible());
        
        if (invisibility.eIsSet(KRENDERING_PKG.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), invisibility.getModifierId());
        }
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KFontBold_KStyle(final ISerializationContext context, final KFontBold fontBold) {
        SequenceFeeder feeder = createSequencerFeeder(fontBold, createNodeProvider(fontBold));
        KFontBoldElements fontBoldAccess = grammarAccess.getKFontBoldAccess();

        if (fontBold.eIsSet(KRENDERING_PKG.getKStyle_PropagateToChildren())) {
            feeder.accept(fontBoldAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        if (fontBold.eIsSet(KRENDERING_PKG.getKStyle_Selection())) {
            feeder.accept(fontBoldAccess.getSelectionSelectionKeyword_1_0());
        }
        
        feeder.accept(fontBoldAccess.getBoldBOOLEANTerminalRuleCall_4_0(), fontBold.isBold());
        
        if (fontBold.eIsSet(KRENDERING_PKG.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), fontBold.getModifierId());
        }
        
        feeder.finish();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void sequence_KFontItalic_KStyle(final ISerializationContext context, final KFontItalic fontItalic) {
        SequenceFeeder feeder = createSequencerFeeder(fontItalic, createNodeProvider(fontItalic));
        KFontItalicElements fontItalicAccess = grammarAccess.getKFontItalicAccess();

        if (fontItalic.eIsSet(KRENDERING_PKG.getKStyle_PropagateToChildren())) {
            feeder.accept(fontItalicAccess.getPropagateToChildrenPropagateKeyword_0_0());
        }
        
        if (fontItalic.eIsSet(KRENDERING_PKG.getKStyle_Selection())) {
            feeder.accept(fontItalicAccess.getSelectionSelectionKeyword_1_0());
        }
        
        feeder.accept(fontItalicAccess.getItalicBOOLEANTerminalRuleCall_4_0(), fontItalic.isItalic());
        
        if (fontItalic.eIsSet(KRENDERING_PKG.getKStyle_ModifierId())) {
            feeder.accept(grammarAccess.getKStyleAccess()
                    .getModifierIdQualifiedIDParserRuleCall_1_1_0(), fontItalic.getModifierId());
        }
        
        feeder.finish();
    }
    
}
