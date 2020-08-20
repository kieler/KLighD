/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd;

import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;

/**
 * Interface for the KLighD extension to add functionality in terms of other extensions during the startup of KLighD
 * programmatically. This is meant to register e.g. {@link IAction}, {@link AbstractDiagramSynthesis}, and other
 * extensions programmatically using the programmatic extension registration in {@link KlighdDataManager} to avoid
 * having to use Eclipse extensions or Java Service loader in different launch scenarios.
 * 
 * @author nre
 */
public interface IKlighdStartupHook {

    /**
     * The hook that gets executed during the startup of KLighD.
     */
    void execute();
    
}
