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
package de.cau.cs.kieler.core.kgraph.text.ui.labeling;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Provides labels for a EObjects. Labels and icons can be computed like this:
 * 
 * <pre>
 * String text(MyModel ele) {
 *     return &quot;my &quot; + ele.getName();
 * }
 * 
 * String image(MyModel ele) {
 *     return &quot;MyModel.gif&quot;;
 * }
 * </pre>
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class KGraphLabelProvider extends DefaultEObjectLabelProvider {

    @Inject
    public KGraphLabelProvider(AdapterFactoryLabelProvider delegate) {
        super(delegate);
    }
    
    public String text(final KLabel label) {
        KIdentifier identifier = label.getData(KIdentifier.class);
        if (identifier != null) {
            return identifier.getId();
        }
        return label.getText();
    }
    
    public String text(final KLabeledGraphElement element) {
        KIdentifier identifier = element.getData(KIdentifier.class);
        if (identifier != null) {
            return identifier.getId();
        }
        for (KLabel label : element.getLabels()) {
            if (label.getText() != null) {
                return label.getText();
            }
        }
        return null;
    }
    
    public String text(final KShapeLayout shapeLayout) {
        EObject container = shapeLayout.eContainer();
        if (container instanceof KNode) {
            return "node layout";
        } else if (container instanceof KPort) {
            return "port layout";
        } else if (container instanceof KLabel) {
            return "label layout";
        }
        return "shape layout";
    }
    
    public String text(final KEdgeLayout edgeLayout) {
        return "edge layout";
    }
    
    public String text(final PersistentEntry entry) {
        if (entry.getKey() != null && entry.getValue() != null) {
            return entry.getKey() + "=" + entry.getValue();
        }
        return null;
    }
    
    public String text(final KPoint point) {
        return point.getX() + "," + point.getY();
    }
    
}
