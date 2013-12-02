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

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IParameterValues;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IAction.ActionContext;
import de.cau.cs.kieler.klighd.IAction.ActionResult;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.viewers.KlighdTreeSelection;

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
        final KlighdTreeSelection selection =
                (KlighdTreeSelection) HandlerUtil.getCurrentSelection(event);
        final IAction action;
        try {
            action = (IAction) event.getObjectParameterForExecution(ACTION_PARAMETER_ID);
        } catch (ExecutionException e) {
            return null;
        }
        
        if (action == null) {
            return null;
        }
        
        final List<ActionResult> results = Lists.newArrayList();
        
        for (KGraphElement kge: Iterables.filter(selection, KGraphElement.class)) {
            final ActionContext context = new ActionContext(
                    selection.getContextViewer().getActiveViewer(), null, kge, null);
            results.add(action.execute(context));
        }
        
        final ActionResult result = Iterables.getFirst(results, ActionResult.createResult(false));
        
        final boolean zoomToFit = result.getZoomToFit() != null
                ? result.getZoomToFit() : selection.getViewContext().isZoomToFit();
        final boolean zoomToFocus =
                result.getZoomToFocus() != null ? result.getZoomToFocus() : selection
                        .getViewContext().getZoomStyle() == ZoomStyle.ZOOM_TO_FOCUS;
        
        // remember the desired zoom style in the view context
        selection.getViewContext().setZoomStyle(ZoomStyle.create(zoomToFit, zoomToFocus));
        
        if (result.getActionPerformed()) {
            LightDiagramServices.layoutDiagram(selection.getViewContext(),
                    result.getAnimateLayout(), zoomToFit, result.getLayoutConfigs());
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
