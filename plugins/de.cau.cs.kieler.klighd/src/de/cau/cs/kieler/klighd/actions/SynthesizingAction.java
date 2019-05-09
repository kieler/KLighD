/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.actions;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Scopes;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared;

/**
 * An action that can use extensions annotated with the {@link ViewSynthesisShared} annotation.
 * That way it can be used to synthesize additional content into the view model.
 * 
 * @author nre
 */
public abstract class SynthesizingAction implements IAction {

    public SynthesizingAction() {
        // Set up an injector that is able to inject the rendering extensions that can then be injected and used as 
        // usual in syntheses.
        Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bindScope(ViewSynthesisShared.class, Scopes.SINGLETON);
            }
        }).injectMembers(this);
    }

}
