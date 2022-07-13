/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
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
package de.cau.cs.kieler.klighd.graphanalysis;

import java.util.List;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author mka
 * @param <S>
 *
 */
public interface IKGraphLayoutEvaluator<T extends IZSampleable<S>, S> {
    
    public List<T> getResults();

}
