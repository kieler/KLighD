/**
 * generated by Xtext 2.10.0
 */
package de.cau.cs.kieler.kgraph.text.grandom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Size</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Size#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kgraph.text.grandom.Size#getWidth <em>Width</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getSize()
 * @model
 * @generated
 */
public interface Size extends EObject
{
  /**
   * Returns the value of the '<em><b>Height</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Height</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Height</em>' containment reference.
   * @see #setHeight(DoubleQuantity)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getSize_Height()
   * @model containment="true"
   * @generated
   */
  DoubleQuantity getHeight();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Size#getHeight <em>Height</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Height</em>' containment reference.
   * @see #getHeight()
   * @generated
   */
  void setHeight(DoubleQuantity value);

  /**
   * Returns the value of the '<em><b>Width</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Width</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Width</em>' containment reference.
   * @see #setWidth(DoubleQuantity)
   * @see de.cau.cs.kieler.kgraph.text.grandom.GrandomPackage#getSize_Width()
   * @model containment="true"
   * @generated
   */
  DoubleQuantity getWidth();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.kgraph.text.grandom.Size#getWidth <em>Width</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Width</em>' containment reference.
   * @see #getWidth()
   * @generated
   */
  void setWidth(DoubleQuantity value);

} // Size
