/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.core.kgraph.text.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;
import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;

/**
 * Xtext based handler for the KGraph format.
 *
 * @author msp
 */
public class KGraphTextHandler extends AbstractEmfHandler<KNode> {
    
    /** The KGraph Text format identifier. */
    public static final String FORMAT = "de.cau.cs.kieler.kgraph.text";
    
    
    /**
     * Check all cross-references of the given graph and generate identifiers where necessary.
     * 
     * @param node the parent node of the graph.
     */
    public static void generateMissingIdentifiers(final KNode node) {
        IdentifierGenerator idGenerator = new IdentifierGenerator();
        generateMissingIdentifiers(node, idGenerator);
    }
    
    /**
     * Check all cross-references of the given graph and generate identifiers where necessary using the
     * given identifier generator.
     * 
     * @param node the parent node of the graph.
     * @param idGenerator the generator to use for generating object identifiers.
     */
    private static void generateMissingIdentifiers(final KNode node,
            final IdentifierGenerator idGenerator) {
        
        checkRenderings(node, idGenerator);
        for (KLabel label : node.getLabels()) {
            checkRenderings(label, idGenerator);
        }
        for (KPort port : node.getPorts()) {
            checkRenderings(port, idGenerator);
            for (KLabel label : port.getLabels()) {
                checkRenderings(label, idGenerator);
            }
        }
        for (KEdge edge : node.getOutgoingEdges()) {
            checkIdentifier(edge.getTarget(), idGenerator);
            checkIdentifier(edge.getSourcePort(), idGenerator);
            checkIdentifier(edge.getTargetPort(), idGenerator);
            checkRenderings(edge, idGenerator);
            for (KLabel label : edge.getLabels()) {
                checkRenderings(label, idGenerator);
            }
        }
        for (KNode child : node.getChildren()) {
            generateMissingIdentifiers(child, idGenerator);
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // KRendering Identifiers
    
    /**
     * Check rendering and style references and create identifiers if necessary.
     * 
     * @param graphElement a graph element
     * @param idGenerator the ID generator to use for generating IDs.
     */
    private static void checkRenderings(final KGraphElement graphElement,
            final IdentifierGenerator idGenerator) {
        
        for (KGraphData graphData : graphElement.getData()) {
            if (graphData instanceof KRendering) {
                checkRenderings((KRendering) graphData, idGenerator);
            } else if (graphData instanceof KRenderingLibrary) {
                for (KStyleHolder styleHolder : ((KRenderingLibrary) graphData).getRenderings()) {
                    checkRenderings(styleHolder, idGenerator);
                }
            }
        }
    }
    
    /**
     * Check rendering and style references and create identifiers if necessary.
     * 
     * @param styleHolder a style holder
     * @param idGenerator the ID generator to use for generating IDs.
     */
    private static void checkRenderings(final KStyleHolder styleHolder,
            final IdentifierGenerator idGenerator) {
        
        if (styleHolder instanceof KRenderingRef) {
            KRendering target = ((KRenderingRef) styleHolder).getRendering();
            if (target != null && (target.getId() == null || target.getId().length() == 0)) {
                target.setId(idGenerator.generateID(target));
            }
        }
        for (KStyle style : styleHolder.getStyles()) {
            if (style instanceof KStyleRef) {
                KStyleHolder target = ((KStyleRef) style).getStyleHolder();
                if (target != null && (target.getId() == null || target.getId().length() == 0)) {
                    target.setId(idGenerator.generateID(target));
                }
            }
        }
        if (styleHolder instanceof KContainerRendering) {
            for (KRendering containedRendering : ((KContainerRendering) styleHolder).getChildren()) {
                checkRenderings(containedRendering, idGenerator);
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // KGraph Element Identifiers
    
    /**
     * Check the identifier of a graph element and create it if necessary.
     * 
     * @param graphElement a graph element
     * @param idGenerator the ID generator to use for generating IDs.
     */
    private static void checkIdentifier(final KGraphElement graphElement,
            final IdentifierGenerator idGenerator) {
        
        if (graphElement != null) {
            KIdentifier identifier = graphElement.getData(KIdentifier.class);
            if (identifier == null) {
                identifier = KLayoutDataFactory.eINSTANCE.createKIdentifier();
                graphElement.getData().add(identifier);
            }
            if (identifier.getId() == null || identifier.getId().length() == 0) {
                identifier.setId(idGenerator.generateID(graphElement));
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Resource Set Stuff
    
    /** the resource set provider injected by the KGraphExecutableExtensionFactory. */
    @Inject private Provider<XtextResourceSet> resourceSetProvider;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        return resourceSetProvider.get();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final TransformationData<KNode, KNode> transData) {
        for (KNode graph : transData.getTargetGraphs()) {
            generateMissingIdentifiers(graph);
        }
        return super.serialize(transData);
    }
    
    /** the identity transformer for KGraph. */
    private static final IGraphTransformer<KNode, KNode> TRANSFORMER
            = new IGraphTransformer<KNode, KNode>() {
        
        public void transform(final TransformationData<KNode, KNode> data) {
            data.getTargetGraphs().add(data.getSourceGraph());
        }
        
        public void transferLayout(final TransformationData<KNode, KNode> data) {
            // nothing to do
        }
    };

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getImporter() {
        return TRANSFORMER;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getExporter() {
        return TRANSFORMER;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // IdentifierGenerator
    
    /**
     * Generates identifiers for the different components of a graph. One instance per graph, since this
     * will ensure IDs to be unique.
     * 
     * @author cds
     */
    private static class IdentifierGenerator {
        /** the highest ID generated for a node so far. */
        private int currNodeId = 0;
        /** the highest ID generated for a port so far. */
        private int currPortId = 0;
        /** the highest ID generated for an edge so far. */
        private int currEdgeId = 0;
        /** the highest ID generated for a label so far. */
        private int currLabelId = 0;
        /** the highest ID generated for a style holder so far. */
        private int currStyleHolderId = 0;
        /** the highest ID generated for other graph elements so far. */
        private int currGenericId = 0;
        
        /**
         * Generates a unique ID for the given graph element. A new ID is generated on each call, even
         * if the method was already called before with the same argument.
         * 
         * @param element the element to generate the ID for.
         * @return a new ID for the element.
         */
        public String generateID(final KGraphElement element) {
            if (element instanceof KNode) {
                return "N" + (++currNodeId);
            } else if (element instanceof KPort) {
                return "P" + (++currPortId);
            } else if (element instanceof KEdge) {
                return "E" + (++currEdgeId);
            } else if (element instanceof KLabel) {
                return "L" + (++currLabelId);
            } else {
                return "K" + (++currGenericId);
            }
        }
        
        /**
         * Generates a unique ID for the given style holder. A new ID is generated on each call, even
         * if the method was already called before with the same argument.
         * 
         * @param styleHolder the rendering to generate the ID for.
         * @return a new ID for the rendering.
         */
        public String generateID(final KStyleHolder styleHolder) {
            return "R" + (++currStyleHolderId);
        }
    }

}