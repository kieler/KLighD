/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;

import de.cau.cs.kieler.core.kgraph.KGraphData;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRenderingLibrary#getRenderings <em>Renderings</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRenderingLibrary()
 * @model
 * @generated
 */
public interface KRenderingLibrary extends KGraphData {
    /**
     * Returns the value of the '<em><b>Renderings</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KRendering}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Renderings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Renderings</em>' containment reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRenderingLibrary_Renderings()
     * @model containment="true"
     * @generated
     */
    EList<KRendering> getRenderings();

} // KRenderingLibrary
