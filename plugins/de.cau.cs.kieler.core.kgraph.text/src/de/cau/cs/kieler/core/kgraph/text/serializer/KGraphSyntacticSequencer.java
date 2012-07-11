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
package de.cau.cs.kieler.core.kgraph.text.serializer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;

/**
 * A specialized {@link org.eclipse.xtext.serializer.sequencer.ISyntacticSequencer} forcing optional
 * keywords to be serialized.
 * 
 * @author chsch
 */
@SuppressWarnings("restriction")
public class KGraphSyntacticSequencer extends AbstractKGraphSyntacticSequencer {

    /**
     * Syntax: ':'?
     */
    protected void emit_KNode_ColonKeyword_4_1_q(EObject semanticObject, ISynNavigable transition,
            List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKNodeAccess().getColonKeyword_4_1(), ":", null);
    }

    /**
     * Syntax: ':'?
     */
    protected void emit_KNode_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition,
            List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKNodeAccess().getColonKeyword_5_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KEdge_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKEdgeAccess().getColonKeyword_5_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KEdge_ColonKeyword_6_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKEdgeAccess().getColonKeyword_6_1(), ":", null);
    }

    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPolyline_Impl_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_1_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPolyline_Impl_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPolyline_Impl_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_3_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPolyline_Impl_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_4_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPort_ColonKeyword_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPortAccess().getColonKeyword_3_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPort_ColonKeyword_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPortAccess().getColonKeyword_4_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KPort_ColonKeyword_5_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKPortAccess().getColonKeyword_5_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KRectangle_ColonKeyword_2_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KRectangle_ColonKeyword_2_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KRectangle_ColonKeyword_2_3_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1(), ":", null);
    }
    
    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KRectangle_ColonKeyword_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1(), ":", null);
    }

    /**
     * Syntax:
     *     ':'?
     */
    protected void emit_KText_ColonKeyword_3_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getKTextAccess().getColonKeyword_3_2_1(), ":", null);
    }
    


}
