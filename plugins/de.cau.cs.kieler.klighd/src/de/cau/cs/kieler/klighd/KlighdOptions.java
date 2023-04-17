/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2017 by
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
package de.cau.cs.kieler.klighd;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

import de.cau.cs.kieler.klighd.filtering.SemanticFilterRule;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;
import de.cau.cs.kieler.klighd.labels.management.LabelManagementResult;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;

public class KlighdOptions implements ILayoutMetaDataProvider {

    /**
     * Default value for {@link #LABELS_ELEMENT_IN_FOCUS}.
     */
    private static final boolean LABELS_ELEMENT_IN_FOCUS_DEFAULT = false;

    /**
     * Whether an element is in the focus rather than in the context. Can be used by label managers
     * to decide whether (and how much) to shorten labels.
     */
    public static final IProperty<Boolean> LABELS_ELEMENT_IN_FOCUS =
            new Property<Boolean>("de.cau.cs.kieler.klighd.labels.elementInFocus",
                    LABELS_ELEMENT_IN_FOCUS_DEFAULT, null, null);

    /**
     * Default value for {@link #LABELS_MANAGEMENT_RESULT}.
     */
    private static final LabelManagementResult LABELS_MANAGEMENT_RESULT_DEFAULT =
            LabelManagementResult.UNMANAGED;

    /**
     * Output option that indicates how a given label was or was not affected by label management
     * during automatic layout.
     */
    public static final IProperty<LabelManagementResult> LABELS_MANAGEMENT_RESULT =
            new Property<LabelManagementResult>("de.cau.cs.kieler.klighd.labels.managementResult",
                    LABELS_MANAGEMENT_RESULT_DEFAULT, null, null);

    /**
     * Default value for {@link #LABELS_TEXT_OVERRIDE}.
     */
    private static final String LABELS_TEXT_OVERRIDE_DEFAULT = null;

    /**
     * Output option that, if set, replaces the original label text.
     */
    public static final IProperty<String> LABELS_TEXT_OVERRIDE =
            new Property<String>("de.cau.cs.kieler.klighd.labels.textOverride",
                    LABELS_TEXT_OVERRIDE_DEFAULT, null, null);
    
    public static final List<SemanticFilterTag> SEMANTIC_FILTER_TAGS_DEFAULT = null;
    
    public static final IProperty<List<SemanticFilterTag>> SEMANTIC_FILTER_TAGS = 
            new Property<List<SemanticFilterTag>>("de.cau.cs.kieler.klighd.semanticFilter.tags",
                    SEMANTIC_FILTER_TAGS_DEFAULT, null, null);
    
    public static final List<SemanticFilterRule> SEMANTIC_FILTER_RULES_DEFAULT = null;
    
    public static final IProperty<List<SemanticFilterRule>> SEMANTIC_FILTER_RULES = 
            new Property<List<SemanticFilterRule>>("de.cau.cs.kieler.klighd.semanticFilter.rules",
                    SEMANTIC_FILTER_RULES_DEFAULT, null, null);

    public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.expansionAwareLayoutOption").group("")
                .name("Expansion Aware Options")
                .description(
                        "Compound option containing pairs of layout option definitions for the collapsed and expanded state of a KNode")
                .type(LayoutOptionData.Type.OBJECT)
                .optionClass(ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData.class)
                .targets(EnumSet.of(LayoutOptionData.Target.PARENTS))
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());

        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.minimalNodeSize").group("").name("Minimal Node Size")
                .description(null).type(LayoutOptionData.Type.OBJECT).optionClass(KVector.class)
                .targets(EnumSet.of(LayoutOptionData.Target.NODES))
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());

        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.labels.elementInFocus").group("labels")
                .name("Element in Focus")
                .description(
                        "Whether an element is in the focus rather than in the context. Can be used by label managers to decide whether (and how much) to shorten labels.")
                .defaultValue(LABELS_ELEMENT_IN_FOCUS_DEFAULT).type(LayoutOptionData.Type.BOOLEAN)
                .optionClass(Boolean.class)
                .targets(EnumSet.of(LayoutOptionData.Target.PARENTS, LayoutOptionData.Target.NODES,
                        LayoutOptionData.Target.PORTS, LayoutOptionData.Target.LABELS))
                .visibility(LayoutOptionData.Visibility.ADVANCED).create());

        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.labels.managementResult").group("labels")
                .name("Label Management Result")
                .description(
                        "Output option that indicates how a given label was or was not affected by label management during automatic layout.")
                .defaultValue(LABELS_MANAGEMENT_RESULT_DEFAULT).type(LayoutOptionData.Type.ENUM)
                .optionClass(LabelManagementResult.class)
                .targets(EnumSet.of(LayoutOptionData.Target.LABELS))
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());

        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.labels.textOverride").group("labels")
                .name("Label Text Override")
                .description("Output option that, if set, replaces the original label text.")
                .defaultValue(LABELS_TEXT_OVERRIDE_DEFAULT).type(LayoutOptionData.Type.STRING)
                .optionClass(String.class).targets(EnumSet.of(LayoutOptionData.Target.LABELS))
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());
        
        // TODO: check if these are sensible settings (all below options)
        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.semanticFilter.tags").group("semanticFilter")
                .name("Semantic Filter Tags")
                .description("TODO")
                .defaultValue(SEMANTIC_FILTER_TAGS_DEFAULT).type(LayoutOptionData.Type.OBJECT)
                .optionClass(Collection.class)
                .targets(EnumSet.of(LayoutOptionData.Target.NODES)) // TODO: also other element types
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());
        
        registry.register(new LayoutOptionData.Builder()
                .id("de.cau.cs.kieler.klighd.semanticFilter.rules").group("semanticFilter")
                .name("Semantic Filter Rules")
                .description("TODO")
                .defaultValue(SEMANTIC_FILTER_RULES_DEFAULT).type(LayoutOptionData.Type.OBJECT)
                .optionClass(Collection.class)
                .targets(EnumSet.of(LayoutOptionData.Target.PARENTS)) // TODO: really only want this on top level of graph
                .visibility(LayoutOptionData.Visibility.HIDDEN).create());
    }
}
