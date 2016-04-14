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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KLeft Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * defines a position starting at the leftmost point of the parent rendering.
 * pos = (L+absolute) + (R-L)*relative = (R-absolute) - widthOfParent*relative
 * <!-- end-model-doc -->
 *
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLeftPosition()
 * @model
 * @generated
 */
public interface KLeftPosition extends KXPosition<KLeftPosition> {
} // KLeftPosition
