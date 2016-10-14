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
package de.cau.cs.kieler.klighd.krendering;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br><br>
 * Instances of this class may be employed in {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElements} for accommodating
 * {@link KRendering KRenderings} that are shared by multiple other {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElements}
 * and referenced by means of {@link KRenderingRef KRenderingRefs}.<br>
 * {@link KRenderingRef KRenderingRefs}, however, may reference any arbitrary {@link KRendering} its deposition in a KRenderingLibrary is
 * not required. Thus, KRenderingLibraries are just for structuring view models, they don't contribute any semantics.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KRenderingLibrary#getRenderings <em>Renderings</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRenderingLibrary()
 * @model
 * @generated
 */
public interface KRenderingLibrary extends KGraphData {
    /**
     * Returns the value of the '<em><b>Renderings</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.krendering.KStyleHolder}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The renderings defined by this library.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Renderings</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRenderingLibrary_Renderings()
     * @model containment="true"
     * @generated
     */
    EList<KStyleHolder> getRenderings();

} // KRenderingLibrary
