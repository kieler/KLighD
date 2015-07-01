/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStyle Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Special {@link KStyle} allowing to reference the styles of another {@link KRendering} or {@link KStyleHolder} in general.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyleRef#getStyleHolder <em>Style Holder</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyleRef#getReferencedTypes <em>Referenced Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef()
 * @model
 * @generated
 */
public interface KStyleRef extends KStyle {
    /**
     * Returns the value of the '<em><b>Style Holder</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style Holder</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style Holder</em>' reference.
     * @see #setStyleHolder(KStyleHolder)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef_StyleHolder()
     * @model required="true"
     * @generated
     */
    KStyleHolder getStyleHolder();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyleRef#getStyleHolder <em>Style Holder</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style Holder</em>' reference.
     * @see #getStyleHolder()
     * @generated
     */
    void setStyleHolder(KStyleHolder value);

    /**
     * Returns the value of the '<em><b>Referenced Types</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Class}&lt;de.cau.cs.kieler.core.krendering.KStyle>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Referenced Types</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A list of {@link KStyle} class instances (List&lt;Class&lt;KStyle&gt;&gt;) that is used for filtering the styles being "imported" from <code>styleHolder</code>; if it is empty all styles of <code>styleHolder</code> are (transitively) evaluated.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Referenced Types</em>' attribute list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef_ReferencedTypes()
     * @model
     * @generated
     */
    EList<Class<KStyle>> getReferencedTypes();

} // KStyleRef
