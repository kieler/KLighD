/**
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
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KTop Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * defines a position starting at the highest point H of the parent rendering. 
 * pos = (H+absolute) + (L-H-absolute)*relative
 * pos = (H+absolute) + heightOfParent*relative
 * <!-- end-model-doc -->
 *
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTopPosition()
 * @model
 * @generated
 */
public interface KTopPosition extends KYPosition {
} // KTopPosition
