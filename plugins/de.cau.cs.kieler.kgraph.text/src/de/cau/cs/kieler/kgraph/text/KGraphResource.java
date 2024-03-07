/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.SaveOptions;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KRendering;

/**
 * A customized {@link LazyLinkingResource} handling the (de-) serialization of
 * {@link IProperty}s correctly.
 * 
 * Deactivates the syntax validation during the serialization since it relies on the
 * ITransientValueService of the out-dated parse tree constructor.
 * 
 * @author chsch
 */
public class KGraphResource extends LazyLinkingResource {

    private static final Predicate<EMapPropertyHolder> HANDLED_TYPES_FILTER =
            e -> e instanceof KLayoutData || e instanceof KRendering;

    /**
     * Additional properties known to the kgraph text format that are no layout options. However,
     * they are made available through content assist and are parsed properly.
     */
    public static final IProperty<?>[] ADDITIONAL_PROPERTIES = getAdditionalProperties();

    private static IProperty<?>[] getAdditionalProperties() {
        try {
            // traditionally we didn't have a hard dependency from the textual syntax implementation
            //  to the KLighD base package, so try to access the 'KlighdProperties' and its members via reflection
            //  (the optional dependency on 'de.cau.cs.kieler.klighd' will bring it into the runtime class path)
            return Stream.of(
                Class.forName("de.cau.cs.kieler.klighd.util.KlighdProperties").getDeclaredFields()
            ).filter(
                f -> Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers())
                        && IProperty.class.isAssignableFrom(f.getType())
            ).map(
                f -> {
                    try {
                        return f.get(null);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        return null;
                    }
                }
            ).filter(
                Predicates.notNull()
            ).toArray(IProperty<?>[]::new);

        } catch (Throwable t) {
            return new IProperty<?>[0];
        }
    };

    /**
     * {@inheritDoc}<br>
     * This customized implementation delegates to {@link LazyLinkingResource#doLoad}.
     */
    protected void doLoad(final InputStream inputStream, final Map<?, ?> options) throws IOException {
        super.doLoad(inputStream, options);
        
        if (!this.getContents().isEmpty()) {
            EObject o = this.getContents().get(0);
            if (o instanceof KNode) {
                // parse persisted key-value pairs using ELK layout data service
                KGraphDataUtil.loadDataElements((KNode) o, HANDLED_TYPES_FILTER, ADDITIONAL_PROPERTIES);
                // validate layout data and references and fill in missing data
                KGraphUtil.validate((KNode) o);
            }
        }
    }
    
    /**
     * Method delegates to {@link LazyLinkingResource#update(int, int, String)} and re-loads
     * affected {@link IProperty}s.
     * 
     * @param offset the offset of the modified text position.
     * @param replacedTextLength the length of the modified part.
     * @param newText the new content.
     */
    public void update(final int offset, final int replacedTextLength, final String newText) {
        super.update(offset, replacedTextLength, newText);
        
        EObject refreshed = NodeModelUtils.findActualSemanticObjectFor(NodeModelUtils
                .findLeafNodeAtOffset(this.getParseResult().getRootNode(), offset));
        KNode node = (KNode) EcoreUtil2.getRootContainer(refreshed);
        
        if (node != null) {
            // parse persisted key-value pairs using ELK's layout data service
            KGraphDataUtil.loadDataElements(node, true, ADDITIONAL_PROPERTIES);
            // validate layout data and references and fill in missing data
            KGraphUtil.validate(node);
        }
    }
    
    /**
     * {@inheritDoc}<br>
     * This customized implementation serializes attached
     * {@link IProperty}s into
     * {@link PersistentEntry}s.
     */
    @SuppressWarnings("deprecation")
    public void doSave(final OutputStream outputStream, final Map<?, ?> options) throws IOException {
        
    	if (!this.getContents().isEmpty()) {
            EObject o = this.getContents().get(0);
            if (o instanceof KNode) {
                KGraphUtil.persistDataElements((KNode) o);
            }
        }
        
        // validation is deactivated since it relies on the ITransientValueService of the
        //  out-dated parse tree constructor
        super.doSave(outputStream, SaveOptions.newBuilder().format().noValidation().getOptions()
                .toOptionsMap());
    }
    
}
