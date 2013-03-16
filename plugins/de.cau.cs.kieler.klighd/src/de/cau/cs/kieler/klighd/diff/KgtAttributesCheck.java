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
package de.cau.cs.kieler.klighd.diff;

import java.util.List;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.ecore.EAttribute;
import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * Class filters attribute updates. For updates on these attributes merging will be suppressed.
 * 
 * @author sgu
 */

public class KgtAttributesCheck extends AttributesCheck {

    /**
     * {@inheritDoc}.
     * 
     * @param manager
     *            a referencer
     */
    public KgtAttributesCheck(final IMatchManager manager) {
        super(manager);
    }

    /**
     * List of attributes that will be suppressed.
     */
    private static List<EAttribute> attributes = ImmutableList.of(
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Xpos(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Ypos(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Width(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Height(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Left(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Right(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Top(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Bottom(),
            KLayoutDataPackage.eINSTANCE.getKPoint_X(), KLayoutDataPackage.eINSTANCE.getKPoint_Y());

    /**
     * In case we're assessing a attributes of the list and the layout contains default values then
     * suppress merging else merge it as usual.
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create if one of the
     *            attributes has actually been changed.
     * @param mapping
     *            This contains the mapping information about the elements we need to check.
     * @param attribute
     *            The attribute we need to check for differences except the one we filter.
     * @throws FactoryException
     *             Thrown if one of the checks fails.
     */
    protected void checkAttributeUpdates(final DiffGroup root, final Match2Elements mapping,
            final EAttribute attribute) throws FactoryException {

        if (attribute == KGraphPackage.eINSTANCE.getEMapPropertyHolder_Properties()
                && mapping.getLeftElement() instanceof KRendering) {
            return;
        }

        // if not(
        // attributes.contains(attribute)
        // && mapping.getRightElement() != null
        // && mapping.getRightElement().eGet(attribute).equals(attribute.getDefaultValue())
        // ) then suppress
        if (!(attributes.contains(attribute)) || mapping.getRightElement() == null
                || !(mapping.getRightElement().eGet(attribute).equals(attribute.getDefaultValue()))) {
            super.checkAttributeUpdates(root, mapping, attribute);
        }
    }
}
