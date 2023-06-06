/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
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
package de.cau.cs.kieler.klighd.internal;

import org.eclipse.elk.core.LayoutConfigurator;

/**
 * In interface of classes contributing a {@link LayoutConfigurator}.<br>
 * It is currently implemented by the {@link de.cau.cs.kieler.klighd.IDiagramWorkbenchPart
 * IDiagramWorkbenchParts} in <code>de.cau.cs.kieler.klighd.ui</code>. This interface is separated
 * from {@link de.cau.cs.kieler.klighd.IDiagramWorkbenchPart IDiagramWorkbenchPart} since it
 * describes an orthogonal aspect and is used for internal purposes only.
 *
 * @author chsch
 */
public interface ILayoutConfigProvider {

    /**
     * Getter.
     *
     * @return the {@link LayoutConfigurator} provided by the implementing class
     */
    LayoutConfigurator getLayoutConfig();

    /**
     * Drops all layout configurations in the employed {@link LayoutConfigurator} and performs a
     * subsequent layout run.
     */
    void resetLayoutConfig();

    /**
     * Drops all layout configurations in the employed {@link LayoutConfigurator}.
     *
     * @param doLayout
     *            if <code>true</code> a subsequent layout run will be triggered
     */
    void resetLayoutConfig(boolean doLayout);
}