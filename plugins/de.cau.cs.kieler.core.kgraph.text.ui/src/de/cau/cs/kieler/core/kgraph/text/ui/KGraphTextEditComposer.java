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
package de.cau.cs.kieler.core.kgraph.text.ui;

import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.ui.editor.model.edit.DefaultTextEditComposer;

/**
 * A custom {@link org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer ITextEditComposer}
 * configuring the formatting after modifying the textual specification via a
 * {@link org.eclipse.xtext.ui.editor.model.edit.ISemanticModification ISemanticModification}.
 * 
 * @author chsch
 * @kieler.ignore (excluded from review process)
 */
public class KGraphTextEditComposer extends DefaultTextEditComposer {

    /**
     * Custom definition setting the 'formatting' flag.
     * 
     * @return the customized {@link SaveOptions}.
     */
    protected SaveOptions getSaveOptions() {
        return SaveOptions.newBuilder().format().getOptions();
    }

}
