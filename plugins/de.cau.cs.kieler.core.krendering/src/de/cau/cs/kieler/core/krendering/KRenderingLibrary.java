package de.cau.cs.kieler.core.krendering;


import org.eclipse.elk.graph.KGraphData;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br><br>
 * Instances of this class may be employed in {@link org.eclipse.elk.graph.KGraphElement KGraphElements} for accommodating
 * {@link KRendering KRenderings} that are shared by multiple other {@link org.eclipse.elk.graph.KGraphElement KGraphElements}
 * and referenced by means of {@link KRenderingRef KRenderingRefs}.<br>
 * {@link KRenderingRef KRenderingRefs}, however, may reference any arbitrary {@link KRendering} its deposition in a KRenderingLibrary is
 * not required. Thus, KRenderingLibraries are just for structuring view models, they don't contribute any semantics.
 * <!-- end-model-doc -->
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
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KStyleHolder}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The renderings defined by this library.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Renderings</em>' containment reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRenderingLibrary_Renderings()
     * @model containment="true"
     * @generated
     */
    EList<KStyleHolder> getRenderings();

} // KRenderingLibrary