/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PrimitiveIterator.OfInt;
import java.util.Random;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.labels.LabelManagementOptions;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphIterators;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KImage;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * Utilites to export a KGraph to a file.
 * 
 * @author uru, nbw
 */
@SuppressWarnings("restriction")
public final class ExportKGraphHelper {

    /**
     * Constructor to prevent instantiation.
     */
    private ExportKGraphHelper() {
    }

    /**
     * Export the given KGraph (represented by the "top" {@link KNode}) to the given {@link URI}.
     * 
     * @param graph
     *            The {@link KNode} representing the root of the exported graph.
     * @param fileOutputURI
     *            The {@link URI} representing the export target.
     * @param protectIP
     *            Flag to indicate whether the contained strings should be obfuscated and renderings
     *            should be removed.
     * @param removeChildren
     *            Flag to indicate whether the (unused) children of the nodes should be removed from
     *            the export.
     */
    public static void export(final KNode graph, final URI fileOutputURI, final boolean protectIP,
            final boolean removeChildren) {
        try {
            // We do not want to tamper with the original model, thus create a copy.
            // Also, we copy the whole graph because we must be able to
            // collect rendering libraries that might exist on higher levels
            KNode root = graph;
            while (root.getParent() != null) {
                root = root.getParent();
            }
            Copier copier = new Copier();
            KNode copy = (KNode) copier.copy(root);
            copier.copyReferences();

            // We have the whole graph copied now
            // If we want the whole graph thats fine,
            // if we want a subgraph, we need to perform
            // some adjustments to have a valid new root
            final KNode exportGraph;
            if (graph == root) {
                // Whole graph export
                exportGraph = copy;
            } else {
                // Subgraph export, move some stuff around
                // The new root's position is at 0,0 now
                KNode subgraphCopy = (KNode) copier.get(graph);
                subgraphCopy.setPos(0, 0);

                // Search for Rendering Libraries in the Graph to move them to the new root node
                KRenderingLibrary[] libs = Iterators.toArray(
                        Iterators.filter(copy.eAllContents(), KRenderingLibrary.class),
                        KRenderingLibrary.class);

                for (KRenderingLibrary lib : libs) {
                    // Move the libs to the new root
                    subgraphCopy.getData().add(lib);
                }

                // we wrap the subgraph into a new pseudo root node
                // to retain external ports
                KNode newRoot = KGraphUtil.createInitializedNode();
                newRoot.getChildren().add(subgraphCopy);
                // give the root a proper dimension
                newRoot.setWidth(subgraphCopy.getWidth());
                newRoot.setHeight(subgraphCopy.getHeight());

                // expand the subgraph by default
                newRoot.setProperty(KlighdInternalProperties.POPULATED, true);
                newRoot.setProperty(KlighdProperties.EXPAND, true);

                exportGraph = newRoot;
            }

            try {
                // We need to sanitize the graph a bit
                Set<KNode> populatedNodes = Sets.newHashSet();
                // Remove transient KlighD state
                // Care: do not iterate over the elements of the 'copy' as the subgraph
                // was already removed from its original containment
                EObject copyRoot = copier.get(graph);
                Iterator<KGraphElement> kgeIt =
                        KGraphIterators.getKGraphElementIterator((KNode) copyRoot, true);

                while (kgeIt.hasNext()) {
                    KGraphElement kge = kgeIt.next();

                    // remember whether a node was expanded / collapsed
                    if (kge instanceof KNode && kge != exportGraph) {
                        KNode kNode = (KNode) kge;
                        boolean isPopulated = kNode.getProperty(KlighdInternalProperties.POPULATED);
                        kNode.setProperty(KlighdProperties.EXPAND, isPopulated);

                        // Since the populated state is only attached to the transient
                        // rendering context that we throw away here, we have to remember
                        // it for later usage
                        if (isPopulated) {
                            populatedNodes.add(kNode);
                        }

                        // Klighd supports to change layout options based on the expansion
                        // state of a node
                        // Here we want to persist the options based on the current state.
                        ExpansionAwareLayoutOptionData ealo =
                                kNode.getProperty(ExpansionAwareLayoutOption.OPTION);
                        if (ealo != null) {
                            kNode.copyProperties(ealo.getValues(isPopulated));
                        }

                        // we make an exception if children of expanded compound
                        // nodes are remove. In such cases the port constraints of the
                        // compound node are fixed after internal layout,
                        // which we want to retain after export
                        // but don't fix them for the currently exported compound node
                        // because that's the one we wanna layout and hence be able
                        // to move external ports
                        if (removeChildren && isPopulated && kNode != copyRoot) {
                            kNode.setProperty(CoreOptions.PORT_CONSTRAINTS,
                                    PortConstraints.FIXED_POS);
                            Direction dir = kNode.getParent().getProperty(CoreOptions.DIRECTION);
                            // due to a previous FREE, ports might have been moved to
                            // different sides
                            for (KPort p : kNode.getPorts()) {
                                p.setProperty(CoreOptions.PORT_SIDE,
                                        KGraphUtil.calcPortSide(p, dir));
                            }
                        }
                    }

                    // Handle the data attached to the KGraph
                    Iterator<KGraphData> dataIt = kge.getData().iterator();
                    while (dataIt.hasNext()) {
                        KGraphData d = dataIt.next();

                        // remove rendering information if desired
                        // this might be useful for diagram types that use confidential renderings
                        if (protectIP) {
                            dataIt.remove();
                        } else {
                            // Remove KImages that would break after export
                            if (d instanceof KContainerRendering) {
                                Iterator<KRendering> renIt =
                                        ((KContainerRendering) d).getChildren().iterator();
                                while (renIt.hasNext()) {
                                    KRendering ren = renIt.next();
                                    if (ren instanceof KRenderingRef) {
                                        if (((KRenderingRef) ren)
                                                .getRendering() instanceof KImage) {
                                            dataIt.remove();
                                            break;
                                        }
                                    }
                                    if (d instanceof KImage) {
                                        dataIt.remove();
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    // If IP should be protected, we remove all properties, that are not strictly
                    // layout related
                    if (protectIP) {
                        removeNonLayoutProperties(kge);
                        // If the current element is a label, replace the text with random data top
                        // protect IP
                        if (kge instanceof KLabel) {
                            replaceLabelText((KLabel) kge);
                        }
                    }

                }

                // persist layout options and friends
                KGraphUtil.persistDataElements(copy);

                // remove children if requested. Do this after the previous removal
                // happening to make sure all rendering libraries are considered etc
                if (removeChildren) {
                    Iterator<KNode> childrenIterator =
                            ((KNode) copier.get(graph)).getChildren().iterator();
                    while (childrenIterator.hasNext()) {
                        KNode child = childrenIterator.next();
                        child.getChildren().clear();
                        child.setProperty(KlighdProperties.EXPAND, false);
                        child.setProperty(KlighdProperties.MINIMAL_NODE_SIZE, null);

                        // remove the collapsed rendering
                        Iterator<KGraphData> dataIt = child.getData().iterator();
                        while (dataIt.hasNext()) {
                            KGraphData d = dataIt.next();
                            // either remove the collapsed or the expanded rendering
                            if (d instanceof KRendering && populatedNodes.contains(child)) {
                                if (d.getProperty(KlighdProperties.COLLAPSED_RENDERING)) {
                                    dataIt.remove();
                                }
                            } else if (d instanceof KRendering) {
                                if (d.getProperty(KlighdProperties.EXPANDED_RENDERING)) {
                                    dataIt.remove();
                                }
                            }
                        }

                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
            ResourceSet rs = new ResourceSetImpl();
            Resource r = rs.createResource(fileOutputURI);
            r.getContents().add(exportGraph);

            // remove any edges that do not have a target anymore
            recursivelyRemoveInvalidEdges((KNode) copier.get(graph));

            Map<String, Object> saveOpts = Maps.newHashMap();
            // we have to drop several elements if only a subgraph
            // is exported ... just let the emf deal with it.
            // FIXME use constants
            saveOpts.put("PROCESS_DANGLING_HREF", "DISCARD");
            r.save(saveOpts);

        } catch (IOException e) {
            StatusManager.getManager()
                    .handle(new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Could not write the KGraph to selected resource.", e),
                            StatusManager.SHOW);
        } catch (Exception e) {
            e.printStackTrace();
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID, "Error occurred.", e),
                    StatusManager.SHOW);
        }
    }

    /**
     * @param kge
     */
    private static void replaceLabelText(final KLabel label) {
        Random r = new Random();
        int l = label.getText().length();
        StringBuilder string = new StringBuilder(l);
        for (int i = 0; i < l; i++) {
            string.append((char) (r.nextInt(26) + 'a'));
        }
        label.setText(string.toString());
    }

    /**
     * @param kge
     */
    private static void removeNonLayoutProperties(final KGraphElement kge) {
        Iterator<Entry<IProperty<?>, Object>> propIter = kge.getProperties().iterator();
        while (propIter.hasNext()) {
            Entry<IProperty<?>, Object> entry = propIter.next();
            if (!isLayoutProperty(entry.getKey())) {
                entry.setValue(null);
            }
        }
    }

    private static boolean isLayoutProperty(final IProperty<?> prop) {
        Set<IProperty<?>> goodProperties =
                Sets.newHashSet(KlighdProperties.EXPAND, KlighdProperties.MINIMAL_NODE_SIZE);
        Set<IProperty<?>> badProperties =
                Sets.newHashSet(CoreOptions.LABEL_MANAGER, LabelManagementOptions.LABEL_MANAGER);
        return (prop.getId().startsWith("org.eclipse.elk") && !badProperties.contains(prop))
                || goodProperties.contains(prop);
    }

    /**
     * Searches for edges, not valid in the graph anymore, especially when a subgraph is exported
     * and the target is not part of the subgraph.
     * 
     * @param parent
     *            The containing node to inspect
     */
    private static void recursivelyRemoveInvalidEdges(final KNode parent) {
        // Iterate over all outgoing edges of this node
        final Iterator<KEdge> edgeIt = parent.getOutgoingEdges().iterator();
        while (edgeIt.hasNext()) {
            KEdge edge = edgeIt.next();
            if (edge.getTarget() == null) {
                edgeIt.remove();
            } else if (!edge.getSource().eResource().equals(edge.getTarget().eResource())) {
                edgeIt.remove();
            }
        }

        // Check edges in all child nodes
        for (KNode child : parent.getChildren()) {
            recursivelyRemoveInvalidEdges(child);
        }
    }
}
