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
package de.cau.cs.kieler.core.kgraph.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.SaveOptions;

import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A customized {@link LazyLinkingResource} handling the (de-) serialization of
 * {@link de.cau.cs.kieler.core.properties.IProperty}s correctly.
 * 
 * Deactivates the syntax validation during the serialization since it relies on the
 * ITransientValueService of the out-dated parse tree constructor.
 * 
 * @author chsch
 * @kieler.design proposed 2012-11-01 chsch
 * @kieler.rating proposed yellow 2012-11-01 chsch
 */
public class KGraphResource extends LazyLinkingResource {

    /**
     * Default values for {@link KGraphElement}'s, see
     * {@link de.cau.cs.kieler.klighd.xtext.transformations.KGraphDiagramSynthesis
     * KGraphDiagramSynthesis}
     */
    private static final IProperty<Boolean> DEFAULTS =
            new Property<Boolean>("de.cau.cs.kieler.kgraphsynthesis.defaults", false); 
    
    /**
     * KLighD offers the possibility to display tooltips that need to be parsed
     * from persistent entries and added to the {@link KGraphElement}'s properties.
     */
    private static final IProperty<String> TOOLTIP =
            new Property<String>("klighd.tooltip");
    
    /**
     * Additional properties known to the kgraph text format that are no layout options. However,
     * they are made available through content assist and are parsed properly.
     */
    public static final IProperty<?>[] ADDITIONAL_PROPERTIES = ImmutableList.of(DEFAULTS, TOOLTIP).toArray(
            new IProperty<?>[2]);

    /**
     * {@inheritDoc}<br>
     * This customized implementation delegates to {@link LazyLinkingResource#doLoad}.
     */
    protected void doLoad(final InputStream inputStream, final Map<?, ?> options) throws IOException {
        super.doLoad(inputStream, options);
        
        if (!this.getContents().isEmpty()) {
            EObject o = this.getContents().get(0);
            if (o instanceof KNode) {
                // parse persisted key-value pairs using KIML's layout data service
                KimlUtil.loadDataElements((KNode) o, ADDITIONAL_PROPERTIES);
                // validate layout data and references and fill in missing data
                KimlUtil.validate((KNode) o);
            }
        }
    }
    
    /**
     * Method delegates to {@link LazyLinkingResource#update(int, int, String)} and re-loads
     * affected {@link de.cau.cs.kieler.core.properties.IProperty}s.
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
            // parse persisted key-value pairs using KIML's layout data service
            KimlUtil.loadDataElements(node, true, ADDITIONAL_PROPERTIES);
            // validate layout data and references and fill in missing data
            KimlUtil.validate(node);
        }
    }
    
    /**
     * {@inheritDoc}<br>
     * This customized implementation serializes attached
     * {@link de.cau.cs.kieler.core.properties.IProperty IProperty}s into
     * {@link de.cau.cs.kieler.core.kgraph.PersistentEntry PersistentEntry}s.
     */
    public void doSave(final OutputStream outputStream, final Map<?, ?> options) throws IOException {
        if (!this.getContents().isEmpty()) {
            EObject o = this.getContents().get(0);
            if (o instanceof KNode) {
                KimlUtil.persistDataElements((KNode) o);
            }
        }
        
        // validation is deactivated since it relies on the ITransientValueService of the
        //  out-dated parse tree constructor
        super.doSave(outputStream, SaveOptions.newBuilder().format().noValidation().getOptions()
                .toOptionsMap());
    }
    
}
