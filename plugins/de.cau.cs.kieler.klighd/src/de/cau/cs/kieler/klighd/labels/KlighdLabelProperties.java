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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * KLighD-specific properties related to label management.
 * 
 * @author cds
 */
public final class KlighdLabelProperties {
    
    /**
     * The result of invoking a label manager on a label, if a label manager is registered. This option
     * is not a registered KIML layout option, since all labels in the layout graph are initially
     * assumed to be unmanaged.
     */
    public static final IProperty<LabelManagementResult> LABEL_MANAGEMENT_RESULT =
            new Property<LabelManagementResult>("de.cau.cs.kieler.labels.labelManagementResult",
                    LabelManagementResult.UNMANAGED);
    
    /**
     * String to override a label's original text with. If this property is set to a non-null value,
     * that value is used as the label's text instead of the original text set in the view model.
     */
    public static final Property<String> LABEL_TEXT_OVERRIDE = new Property<String>(
            "klighd.labels.modifiedLabelText", null);
    
    
    /**
     * Not supposed to be instantiated.
     */
    private KlighdLabelProperties() {
        throw new IllegalStateException("not supposed to be instantiated.");
    }
    
}
