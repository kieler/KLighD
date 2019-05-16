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
 * An example action that adds an ellipse as a child rendering to the clicked container rendering may look as follows:
 * 
 * <pre>
 *class RevealRequiredBundlesAction extends SynthesizingAction {
 *    &#64;Inject extension KColorExtensions
 *    &#64;Inject extension KRenderingExtensions
 *    &#64;Inject extension KContainerRenderingExtensions
 *    
 *    override execute(ActionContext context) {
 *        val clickedRendering = context.KRendering as KContainerRendering
 *        
 *        clickedRendering.addEllipse => [background = 'green'.color]
 *        
 *        return ActionResult.createResult(true)
 *    }
 *}
 * </pre>
 * 
 * This class helps that the &#64;Inject annotations for the extensions with &#64;ViewSynthesisShared annotations are
 * correctly initialized and can be used.
 * 
 * @author nre
 */
public abstract class SynthesizingAction implements IAction {

    /**
     * The default constructor called before the first execution of this action.
     */
    public SynthesizingAction() {
        // Set up an injector that is able to inject the rendering extensions that can then be injected and used as 
        // usual in syntheses.
        Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bindScope(ViewSynthesisShared.class, Scopes.SINGLETON);
            }
        }).injectMembers(this);
    }

}
