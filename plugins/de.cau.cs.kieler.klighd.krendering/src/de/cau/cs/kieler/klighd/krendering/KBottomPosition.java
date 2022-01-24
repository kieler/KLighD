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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KBottom Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * defines a position starting at the bottom point B of the parent rendering. 
 * pos = (B-absolute) - (B-H-absolute)*relative
 * pos = (B-absolute) - heightOfParent*relative
 * <!-- end-model-doc -->
 *
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKBottomPosition()
 * @model
 * @generated
 */
public interface KBottomPosition extends KYPosition<KBottomPosition> {
} // KBottomPosition
