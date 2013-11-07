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
package de.cau.cs.kieler.core.krendering.extensions;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.ScopeAnnotation;

/**
 * This annotation is intended to be attached to transformation implementations that make use of
 * dependency injection by means of Google Guice and that are made accessible to KLighD via its
 * related extension port. The attachment of this annotation indicates a special treatment while
 * injecting instances of helper transformations into diagram synthesis implementations. <br>
 * <br>
 * For more details see
 * {@link de.cau.cs.kieler.klighd.syntheses.ReinitializingDiagramSynthesisProxy.ViewSynthesisScope} <br>
 * <br>
 * It is defined at this place as I need to use it within the KNode/KPort/KLabel/KEdgeExtensions.
 * 
 * @author chsch
 */
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@ScopeAnnotation
public @interface ViewSynthesisShared {
}
