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
package de.cau.cs.kieler.klighd.incremental.diff;

import java.util.List;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.ecore.EAttribute;

import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;

/**
 * A customized {@link AttributesCheck} that realizes KGraph-specific customizations of the EMF
 * Compare diff process.<br>
 * <br>
 * This concrete {@link AttributesCheck} suppresses the synthesis of diff statements for differing
 * attribute values in case the new values, i.e. those created by a subsequent diagram synthesis
 * run, are initial values according to their meta models (usually zero) while deriving a diff model
 * based on a given match model. This situation indicates non-set values that are not to be
 * transfered into the diagram in order to avoiding flickering.<br>
 * In case the value are non-zero, which indicated a pre-initialization of the layout data by the
 * diagram synthesis, those data must be transfered into the diagram.<br>
 * <br>
 * Note: Simply overriding {@link #shouldBeIgnored(EAttribute)} doesn't suffice here since we need
 * to examine concrete {@link Match2Elements Match2Elements}.
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KGraphAttributesCheck extends AttributesCheck {

    /**
     * See {@link AttributesCheck#AttributesCheck(IMatchManager)}.
     * 
     * @param manager
     *            the IMatchManager instance to determine matches for certain <code>EObject</code>
     */
    public KGraphAttributesCheck(final IMatchManager manager) {
        super(manager);
    }

    /**
     * List of attributes that will be ignored.
     */
    private static final List<EAttribute> ATTRIBUTES = ImmutableList.of(
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Xpos(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Ypos(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Width(),
            KLayoutDataPackage.eINSTANCE.getKShapeLayout_Height(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Left(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Right(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Top(),
            KLayoutDataPackage.eINSTANCE.getKInsets_Bottom(),
            KLayoutDataPackage.eINSTANCE.getKPoint_X(),
            KLayoutDataPackage.eINSTANCE.getKPoint_Y());

    /**
     * In case we're assessing an attribute mentioned in {@link #ATTRIBUTES} and the provided
     * {@link org.eclipse.emf.ecore.EObject EObject}, here the right one of the match pair,
     * contains default values then suppress merging. Otherwise merge them it as usual.
     * 
     * @param root
     *            {@link DiffGroup root} of the
     *            {@link org.eclipse.emf.compare.diff.metamodel.DiffElement DiffElement} to create
     *            if one of the attributes has actually been changed.
     * @param mapping
     *            This contains the mapping information about the elements we need to check.
     * @param attribute
     *            The attribute we need to check for differences except the one we filter.
     * @throws FactoryException
     *             Thrown if one of the checks fails.
     */
    protected void checkAttributeUpdates(final DiffGroup root, final Match2Elements mapping,
            final EAttribute attribute) throws FactoryException {

        // this case covers the layout data mentioned in #attributes, see class documentation.
        if (ATTRIBUTES.contains(attribute)
                && mapping.getRightElement() != null
                && mapping.getRightElement().eGet(attribute).equals(attribute.getDefaultValue())) {
            return;
        }
        
        super.checkAttributeUpdates(root, mapping, attribute);
    }
}