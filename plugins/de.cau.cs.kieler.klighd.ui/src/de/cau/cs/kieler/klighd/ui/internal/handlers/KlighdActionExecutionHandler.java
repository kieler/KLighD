/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IParameterValues;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IAction.ActionContext;
import de.cau.cs.kieler.klighd.IAction.ActionResult;
import de.cau.cs.kieler.klighd.IKlighdSelection;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.util.Iterables2;

/**
 * Generic handler implementation executing {@link IAction IActions} on view models and re-arranging
 * the diagrams afterwards.<br>
 * <br>
 * This class contains some inner helper classes contributing to the command parameter definitions
 * in the plugin.xml.
 * 
 * @author chsch
 */
public class KlighdActionExecutionHandler extends AbstractHandler {

    /** The action parameter id as defined in the plugin.xml. */
    private static final String ACTION_PARAMETER_ID = "de.cau.cs.kieler.klighd.ui.action";
    
    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IKlighdSelection selection;

        // in case this handler is invoked via a context menu,
        //  the activeMenuSelection (ISources#ACTIVE_MENU_SELECTION_NAME) is available
        ISelection s = HandlerUtil.getActiveMenuSelection(event);
        
        if (s instanceof IKlighdSelection) {
            // if it's a KLighD selection (it is supposed to be, we're fine :-)
            selection = (IKlighdSelection) s;

        } else if (s == null) {
            // if no activeMenuSelection is set, the handler may be called by the main menu,
            //  toolbar, or a key binding; refer to the global selection in that case 
            s = HandlerUtil.getCurrentSelectionChecked(event);

            if (s instanceof IKlighdSelection) {
                // again if it's a KLighD selection (it is supposed to be, we're fine :-)
                selection = (IKlighdSelection) s;

            } else {
                // something really strange must have happened
                return null;
            }
        } else {
            // something really strange must have happened
            return null;
        }
        
        final IAction action;
        try {
            action = (IAction) event.getObjectParameterForExecution(ACTION_PARAMETER_ID);
        } catch (final ExecutionException e) {
            return null;
        }
        
        if (action == null) {
            return null;
        }
        
        final ViewContext viewContext = selection.getViewContext();
        final List<ActionResult> results = Lists.newArrayList();
        
        // this flag is used to track the successful execution of actions
        //  in order to enable animated diagram changes, the viewer must be informed to
        //  record view model changes, which is done once an action is actually executed
        boolean anyActionPerformed = false;
        
        viewContext.getLayoutRecorder().startRecording();

        final Iterator<KGraphElement> kges =
                Iterators.filter(selection.diagramElementsiterator(), KGraphElement.class);

        for (final KGraphElement kge : Iterables2.toIterable(kges)) {
            final ActionContext context = new ActionContext(viewContext.getViewer(), null, kge, null);

            final ActionResult result = action.execute(context);
            if (result != null) {
                results.add(result);
                anyActionPerformed |= result.getActionPerformed();
            }
        }

        final ActionResult result = Iterables.getFirst(results, ActionResult.createResult(false));

        if (anyActionPerformed) {
            LightDiagramServices.layoutDiagram(viewContext, result.getAnimateLayout(),
                    ZoomStyle.create(result, viewContext), result.getFocusNode(),
                    result.getLayoutConfigs());
        } else {
            viewContext.getLayoutRecorder().stopRecording(ZoomStyle.NONE, null, 0);
        }
        
        return null;
    }
    
    /**
     * An implementation of {@link AbstractParameterValueConverter} converting {@link IAction} to
     * their ids and vice versa. It is referenced in the plugin.xml.
     */
    public static class IActionParameterValueConverter extends AbstractParameterValueConverter {

        /**
         * Constructor.
         */
        public IActionParameterValueConverter() {
        }

        @Override
        public Object convertToObject(final String parameterValue)
                throws ParameterValueConversionException {
            return KlighdDataManager.getInstance().getActionById(parameterValue);
        }

        @Override
        public String convertToString(final Object parameterValue)
                throws ParameterValueConversionException {
            if (parameterValue instanceof String) {
                // given parameter values in menu contributions (as Strings) need to
                //  be converted to another String first...
                return (String) parameterValue;
            } else if (parameterValue instanceof IAction) {
                return KlighdDataManager.getInstance().getActionsId((IAction) parameterValue);
            } else {
                throw new ParameterValueConversionException("");
            }
        }
    }

    /**
     * An implementation of {@link IParameterValues} providing the ids and human-friendly names of
     * the registered {@link IAction IActions}. It is referenced in the plugin.xml.
     */
    public static class IActionParameterValues implements IParameterValues {

        /**
         * {@inheritDoc}
         */
        public Map<String, String> getParameterValues() {
            return Maps.uniqueIndex(KlighdDataManager.getInstance().getActionIds(),
                    new Function<String, String>() {
                        public String apply(final String actionId) {
                            final int index = actionId.lastIndexOf('.');
                            return index == -1 ? actionId : actionId.substring(index + 1);
                        }
                    });
        }
    }
}
