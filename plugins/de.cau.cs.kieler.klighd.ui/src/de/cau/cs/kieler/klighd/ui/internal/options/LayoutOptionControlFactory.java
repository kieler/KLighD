/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.options;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.ISourceProxy;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutConfigurationStore;

/**
 * A factory for controls for layout options.
 *
 * @author msp
 * @author chsch
 */
public class LayoutOptionControlFactory {

    /** The parent composite into which controls are created. */
    private Composite parent;

    private ViewContext viewContext;

    /** The workbench part containing the diagram viewer. */
    private IDiagramWorkbenchPart workbenchPart;
    /** the form toolkit used to create controls. */
    private FormToolkit formToolkit;
    /** The layout configurator for retrieving initial values of controls. */
//    private ILayoutConfig defaultLayoutConfig;
    /** The layout context for retrieving initial values of controls. */
//    private LayoutContext defaultLayoutContext;
    /** The layout configurator that stores the values set by option controls. */
//    private VolatileLayoutConfig lightLayoutConfig;
    /** The set of controls to be disposed when {@link #clear()} is called. */
    private final Collection<Control> controls = new LinkedList<Control>();
    /** Whether the layout shall be refreshed automatically. */
    private boolean autoRefreshLayout = true;

    /** number of columns in the grid for enumeration value selection. */
    private static final int ENUM_GRID_COLS = 1;
    /** widget data identifier for the attached selection listener. */
    private static final String DATA_SELECTION_LISTENER = "klighd.selectionListener";

    /** a {@link KLighDLayoutConfigurationStore} instance required for computing the defaults. */
//    private static final ILayoutConfig KGRAPH_CONFIG = new KLighDLayoutConfigurationStore();

    /**
     * Create an option control factory.
     *
     * @param parent
     *            the parent container
     * @param formToolkit
     *            the form toolkit used to create controls
     * @param theLightLayoutConfig
     *            the layout configuration to the user-chosen values in
     */
    public LayoutOptionControlFactory(final Composite parent, final FormToolkit formToolkit,
            final LayoutConfigurator theLightLayoutConfig) {
        this.parent = parent;
        this.formToolkit = formToolkit;
//        this.lightLayoutConfig = theLightLayoutConfig;

        // configure the parent's layout
        this.parent.setLayout(new GridLayout(1, false)); // chsch: changed this from 2 to 1

        // set a dummy configurator with default values
//        defaultLayoutConfig = new DefaultLayoutConfig();
//        defaultLayoutContext = new LayoutContext();
    }

    /**
     * Clear the current layout configuration and reinitialize option values. These values
     * are used when new controls are created. If any controls have been created before,
     * they should be removed first using {@link #clear()}.
     *
     * @param theViewContext
     *            the viewContext belonging to the current diagram
     */
    public void initialize(final ViewContext theViewContext) {
//        lightLayoutConfig.clearOptionValues(LayoutContext.global());
//
//        this.viewContext = theViewContext;
//        this.workbenchPart = viewContext.getDiagramWorkbenchPart();
//
//        final Object input = this.viewContext.getInputModel();
//        final KNode viewModel = this.viewContext.getViewModel();
//
//        final LayoutOptionManager optionManager = DiagramLayoutEngine.INSTANCE.getOptionManager();
//
//        // create the layout configurator
//        if (input instanceof ISourceProxy) {
//            defaultLayoutConfig = ((ISourceProxy) input).execute(new Function<Object, ILayoutConfig>() {
//
//                public ILayoutConfig apply(final Object inputModel) {
//                    // question: shall we check for Iterables here, as well?
//                    // skip this for now as our diagram syntheses usually take a single source element
//                    //  rather than an iterable, although this is possible (see Ecore example)
//                    return optionManager.createConfig(inputModel, KGRAPH_CONFIG);
//                }
//            });
//
//        } else {
//            final EObject inputModel;
//
//            // question: why do we only accept EObjects here?
//
//            if (input instanceof EObject) {
//                inputModel = (EObject) input;
//            } else if (input instanceof Iterable) {
//                inputModel = Iterables.getFirst(
//                        Iterables.filter((Iterable<?>) input, EObject.class), null);
//            } else {
//                inputModel = null;
//            }
//
//            defaultLayoutConfig = optionManager.createConfig(inputModel, KGRAPH_CONFIG);
//        }
//
//        // create and enrich the layout context
//        defaultLayoutContext = new LayoutContext();
//        defaultLayoutContext.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
//
//        // chsch: deactivated the following declaration as it may disturb the layout massively
//        //  in case 'inputModel' is NOT represented by 'viewModel' (but, e.g., its first child)
//        //  and a semantic layout config is associated with the input model's root element;
//        // in that case those layout settings are taken into 'lightLayoutConfig',
//        //  and, thus, applied to all view model elements (view model nodes), which is wrong, of course!
//        // defaultLayoutContext.setProperty(LayoutContext.DOMAIN_MODEL, inputModel);
//        defaultLayoutContext.setProperty(LayoutContext.DIAGRAM_PART, viewModel);
//
//        defaultLayoutContext.setProperty(
//                LayoutContext.OPT_TARGETS, EnumSet.of(LayoutOptionData.Target.PARENTS));
//
//        optionManager.enrich(defaultLayoutContext, defaultLayoutConfig, true);
    }

    /**
     * Clear the previously created option controls.
     */
    public void clear() {
        for (final Control c : controls) {
            c.dispose();
        }
        controls.clear();
    }

    /**
     * Refresh the layout of the displayed diagram.
     *
     * @param animate whether the new layout shall be animated
     */
    private void refreshLayout(final boolean animate) {
        if (autoRefreshLayout) {
            if (animate) {
                LightDiagramServices.layoutDiagram(viewContext);
            } else {
                LightDiagramServices.layoutDiagram(viewContext, false);
            }
        }
    }

    /**
     * Create a control for the given layout option identifier.
     *
     * @param optionId a layout option identifier
     */
    public void createControl(final String optionId) {
//        final LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(optionId);
//        if (optionData != null) {
//            createControl(optionData, null, null, null);
//        }
    }

    /**
     * Create a control for the given layout option identifier with given bounds.
     *
     * @param optionId a layout option identifier
     * @param minValue the minimal value for the option
     * @param maxValue the maximal value for the option
     */
    public void createControl(final String optionId, final Float minValue, final Float maxValue) {
//        final LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(optionId);
//        if (optionData != null) {
//            createControl(optionData, minValue, maxValue, null);
//        }
    }

    /**
     * Create a control for the given layout option identifier with given available option values.
     *
     * @param optionId a layout option identifier
     * @param availableValues the set of values to offer
     */
    public void createControl(final String optionId, final Collection<?> availableValues) {
//        final LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(optionId);
//        if (optionData != null) {
//            createControl(optionData, null, null, availableValues);
//        }
    }

    /**
     * Reset all displayed layout options to their default values.
     *
     * @param doLayout
     *            if <code>true</code> a subsequent layout run will be triggered
     */
    @SuppressWarnings("incomplete-switch")
    public void resetToDefaults(final boolean doLayout) {
        // temporarily disable auto-refresh to avoid multiple layout runs triggered by listeners
//        autoRefreshLayout = false;
//        lightLayoutConfig.clearOptionValues(LayoutContext.global());
//        for (final Control control : controls) {
//            if (control.getData() instanceof LayoutOptionData) {
//                final LayoutOptionData optionData = (LayoutOptionData) control.getData();
//                final Object defaultValue = defaultLayoutConfig.getOptionValue(optionData,
//                        defaultLayoutContext);
//
//                switch (optionData.getType()) {
//                case INT:
//                case FLOAT: {
//                    final Scale slider = (Scale) control;
//                    final SliderListener sliderListener = (SliderListener) control.getData(
//                            DATA_SELECTION_LISTENER);
//                    if (sliderListener != null) {
//                        final float initialValue =
//                                ElkMath.boundf(((Number) defaultValue).floatValue(),
//                                        sliderListener.minFloat, sliderListener.maxFloat);
//                        final int selection = Math.round((initialValue - sliderListener.minFloat)
//                                / (sliderListener.maxFloat - sliderListener.minFloat)
//                                * (slider.getMaximum() - slider.getMinimum())) + slider.getMinimum();
//                        slider.setSelection(selection);
//                    }
//                    break;
//                }
//
//                case BOOLEAN:
//                case ENUM: {
//                    // the composite's children store the available values in their 'Data' fields
//                    final Composite composite = (Composite) control;
//                    final Control selection = Iterators.find(Iterators.forArray(composite.getChildren()),
//                            new Predicate<Control>() {
//                        public boolean apply(final Control input) {
//                            if (input.getData() != null) {
//                                return input.getData().equals(defaultValue);
//                            }
//                            return false;
//                        }
//                    }, null);
//                    for (final Control c : composite.getChildren()) {
//                        ((Button) c).setSelection(c == selection);
//                    }
//                    break;
//                }
//
//                }
//            }
//        }
//        autoRefreshLayout = true;
//
//        if (doLayout) {
//            refreshLayout(true);
//        }
    }

    /**
     * Create a control for the given layout option data instance with given bounds.
     *
     * @param optionData a layout option data instance
     * @param minValue the minimal value for the option, or {@code null}
     * @param maxValue the maximal value for the option, or {@code null}
     * @param availableValues the set of values to offer, or {@code null}
     */
    private void createControl(final LayoutOptionData optionData, final Float minValue,
            final Float maxValue, final Collection<?> availableValues) {
// chsch: deactivated the following part in order to get rid of the dependency to kiml.ui,
//  employing the fully-fledged layout algorithm selection dialog is not considered appropriate anyway,
//  see KIPRA-1448
//        if (optionData.equals(LayoutOptions.ALGORITHM)) {
//            final Button button =
//                    formToolkit.createButton(parent, "Select Layout Algorithm...", SWT.PUSH);
//            button.setToolTipText(optionData.getDescription());
//            button.addSelectionListener(new AlgorithmListener());
//            final GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false);
//            // gridData.horizontalSpan = 2;
//            button.setLayoutData(gridData);
//            button.setData(optionData);
//            controls.add(button);
//            // set initial value for the algorithm selection dialog
//            final String algorithmHint =
//                    defaultLayoutContext.getProperty(DefaultLayoutConfig.CONTENT_HINT);
//            if (algorithmHint != null && algorithmHint.length() > 0) {
//                lightLayoutConfig.setValue(optionData, algorithmHint);
//            }
//
//            // chsch: via this tweak we get more space below the 'Select ...' button as in GridData
//            //  one can only specify vertical indentation on the top side of the widget
//            final Composite dummy = new Composite(parent, SWT.NONE | SWT.NO_BACKGROUND);
//            dummy.setLayoutData(new GridData(1, 1));
//            controls.add(dummy);
//
//            return;
//        }
//        final Label label = formToolkit.createLabel(parent, optionData.getName() + ":");
//        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//        controls.add(label);
//
//        switch (optionData.getType()) {
//        case INT:
//        case FLOAT: {
//            final Scale slider = new Scale(parent, SWT.NONE);
//            // the following setting is needed on windows
//            slider.setBackground(parent.getBackground());
//            slider.setToolTipText(optionData.getDescription());
//            final SliderListener sliderListener = new SliderListener(label, optionData,
//                    getMinValue(optionData, minValue), getMaxValue(optionData, maxValue));
//            final GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
//            slider.setLayoutData(gridData);
//            slider.setData(optionData);
//            controls.add(slider);
//            // set initial value for the slider
//            float initialValue = ((Number) defaultLayoutConfig.getOptionValue(optionData,
//                    defaultLayoutContext)).floatValue();
//            initialValue = ElkMath.boundf(initialValue, sliderListener.minFloat,
//                    sliderListener.maxFloat);
//
//            label.setText(optionData.getName() + ": " + initialValue);
//
//            final int selection = Math.round((initialValue - sliderListener.minFloat)
//                    / (sliderListener.maxFloat - sliderListener.minFloat)
//                    * (slider.getMaximum() - slider.getMinimum())) + slider.getMinimum();
//            slider.setSelection(selection);
//
//            // add selection listener for instant layout updates
//            slider.addSelectionListener(sliderListener);
//            slider.setData(DATA_SELECTION_LISTENER, sliderListener);
//            break;
//        }
//
//        case BOOLEAN: {
//            final Composite valuesContainer = formToolkit.createComposite(parent);
//            valuesContainer.setLayout(new GridLayout(2, false));
//            final Button trueButton = formToolkit.createButton(valuesContainer, "True", SWT.RADIO);
//            trueButton.setToolTipText(optionData.getDescription());
//            trueButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//            trueButton.setData(true);
//            final Button falseButton = formToolkit.createButton(valuesContainer, "False", SWT.RADIO);
//            falseButton.setToolTipText(optionData.getDescription());
//            falseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//            falseButton.setData(false);
//            valuesContainer.setData(optionData);
//            controls.add(valuesContainer);
//            // set initial value for the radio buttons
//            if ((Boolean) defaultLayoutConfig.getOptionValue(optionData, defaultLayoutContext)) {
//                trueButton.setSelection(true);
//            } else {
//                falseButton.setSelection(true);
//            }
//            // add selection listeners for instant layout updates
//            trueButton.addSelectionListener(new EnumerationListener(optionData, Boolean.TRUE));
//            falseButton.addSelectionListener(new EnumerationListener(optionData, Boolean.FALSE));
//            break;
//        }
//
//        case ENUM: {
//            final Composite valuesContainer = formToolkit.createComposite(parent);
//            valuesContainer.setLayout(new GridLayout(ENUM_GRID_COLS, false));
//            Object[] values;
//            if (availableValues != null) {
//                values = availableValues.toArray();
//            } else {
//                @SuppressWarnings({ "rawtypes", "unchecked" })
//                final Class<Enum> clazz = (Class<Enum>) optionData.getOptionClass();
//                values = clazz.getEnumConstants();
//            }
//            final Object initialValue = defaultLayoutConfig.getOptionValue(optionData,
//                    defaultLayoutContext);
//            for (final Object value : values) {
//                final Button button = formToolkit.createButton(valuesContainer, getUserValue(value),
//                        SWT.RADIO);
//                button.setToolTipText(optionData.getDescription());
//                button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//                button.setData(value);
//                if (value.equals(initialValue)) {
//                    button.setSelection(true);
//                }
//                button.addSelectionListener(new EnumerationListener(optionData, value));
//            }
//            valuesContainer.setData(optionData);
//            controls.add(valuesContainer);
//            break;
//        }
//
//        case ENUMSET: {
//            final Composite valuesContainer = formToolkit.createComposite(parent);
//            valuesContainer.setLayout(new GridLayout(ENUM_GRID_COLS, false));
//
//            final Object[] values;
//            if (availableValues != null) {
//                values = availableValues.toArray();
//
//            } else {
//                final Label errorLabel = new Label(parent, SWT.NONE);
//                errorLabel.setText("This option type requires pre-defined values.");
//                errorLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
//                controls.add(errorLabel);
//                break;
//            }
//
//            final Object initialValue = defaultLayoutConfig.getOptionValue(optionData,
//                    defaultLayoutContext);
//            for (final Object value : values) {
//                final Button button = formToolkit.createButton(valuesContainer, getUserValue(value),
//                        SWT.RADIO);
//                button.setToolTipText(optionData.getDescription());
//                button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//                button.setData(value);
//                if (value.equals(initialValue)) {
//                    button.setSelection(true);
//                }
//                button.addSelectionListener(new EnumerationListener(optionData, value));
//            }
//            valuesContainer.setData(optionData);
//            controls.add(valuesContainer);
//            break;
//        }
//
//        default:
//            final Label errorLabel = new Label(parent, SWT.NONE);
//            errorLabel.setText("This option type is not supported");
//            errorLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
//            controls.add(errorLabel);
//        }
    }

    /** The default value for the lower bound. */
    private static final int DEFAULT_MIN = 0;

    /**
     * Get a lower bound for values of the given option.
     *
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a minimal value
     */
    private static float getMinValue(final LayoutOptionData optionData, final Float requested) {
        if (requested != null) {
            return requested;
        }
//        final Object lowerBound = optionData.getLowerBound();
//        if (lowerBound instanceof Number) {
//            return ((Number) lowerBound).floatValue();
//        }
        return DEFAULT_MIN;
    }

    /** The default value for the upper bound. */
    private static final int DEFAULT_MAX = 100;

    /**
     * Get an upper bound for values of the given option.
     *
     * @param optionData a layout option data instance
     * @param requested the requested bound, or {@code null}
     * @return a maximal value
     */
    private static float getMaxValue(final LayoutOptionData optionData, final Float requested) {
        if (requested != null) {
            return requested;
        }
//        final Object upperBound = optionData.getUpperBound();
//        if (upperBound instanceof Number) {
//            return ((Number) upperBound).floatValue();
//        }
        return DEFAULT_MAX;
    }

    /**
     * Get a user-friendly value for the given object.
     *
     * @param object an object, e.g. an enumeration value
     * @return a user-friendly string to display in the UI
     */
    private static String getUserValue(final Object object) {
        final String string = object.toString();
        final StringBuilder builder = new StringBuilder(string.length());
        boolean capital = true;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '_') {
                builder.append(' ');
                capital = true;
            } else if (capital) {
                builder.append(Character.toUpperCase(string.charAt(i)));
                capital = false;
            } else {
                builder.append(Character.toLowerCase(string.charAt(i)));
            }
        }
        return builder.toString();
    }

    private static final Pattern MAX_2_DIGITS = Pattern.compile("(.*\\.\\d[^0]?)\\d*");
    private static final String FIRST_GROUP = "$1";

    /**
     * A listener for sliders.
     */
    private class SliderListener extends SelectionAdapter {

        /** the corresponding name label used updating the selected value. */
        private final Label correspondingLabel;
        /** the layout option that is affected by the slider. */
        private final LayoutOptionData optionData;
        /** the maximal value. */
        private final float minFloat;
        /** the minimal value. */
        private final float maxFloat;

        /**
         * Create a slider listener.
         * @param textLabel the corresponding name label used updating the selected value
         * @param optionData the layout option that is affected by the slider
         * @param minFloat the maximal value
         * @param maxFloat the minimal value
         */
        SliderListener(final Label textLabel, final LayoutOptionData optionData,
                final float minFloat, final Float maxFloat) {
            this.correspondingLabel = textLabel;
            this.optionData = optionData;
            this.minFloat = minFloat;
            if (maxFloat <= minFloat) {
                this.maxFloat = minFloat + 1;
            } else {
                this.maxFloat = maxFloat;
            }
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            final Scale slider = (Scale) event.widget;
            final float sliderValue = (float) (slider.getSelection() - slider.getMinimum())
                    / (slider.getMaximum() - slider.getMinimum());
            final float optionValue = minFloat + sliderValue * (maxFloat - minFloat);
            setOptionValue(optionValue);
            correspondingLabel.setText(optionData.getName() + ": "
                    + MAX_2_DIGITS.matcher(String.valueOf(optionValue)).replaceFirst(FIRST_GROUP));
            correspondingLabel.getParent().layout(true);

            // trigger a new layout on the displayed diagram
            refreshLayout(false);
        }

        /**
         * Set the new value of the layout option.
         *
         * @param optionValue the layout option value
         */
        @SuppressWarnings({ "incomplete-switch" })
        public void setOptionValue(final float optionValue) {
//            switch (optionData.getType()) {
//            case INT:
//                lightLayoutConfig.setValue(optionData, (int) optionValue);
//                break;
//            case FLOAT:
//                lightLayoutConfig.setValue(optionData, optionValue);
//                break;
//            }
        }
    }

// chsch: deactivated the following part in order to get rid of the dependency to kiml.ui,
//  employing the fully-fledged layout algorithm selection dialog is not considered appropriate anyway,
//  see KIPRA-1448
//    /**
//     * A listener for the layout algorithm selection button.
//     */
//    private class AlgorithmListener extends SelectionAdapter {
//
//        @Override
//        public void widgetSelected(final SelectionEvent event) {
//            final String initialValue =
//                    (String) lightLayoutConfig.getGlobalValue(LayoutOptions.ALGORITHM);
//            final Dialog dialog = new AlgorithmSelectionDialog(parent.getShell(),
//                    initialValue);
//            dialog.addAlgorithmSelectionListener(new ISelectionChangedListener() {
//                public void selectionChanged(final SelectionChangedEvent event) {
//                    // instantly update the layout when an algorithm is selected
//                    final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
//                    if (!selection.isEmpty()
//                            && selection.getFirstElement() instanceof ILayoutMetaData) {
//                        ILayoutMetaData layoutData = (ILayoutMetaData) selection.getFirstElement();
//                        lightLayoutConfig.setValue(LayoutOptions.ALGORITHM, layoutData.getId());
//                        refreshLayout(true);
//                    }
//                }
//            });
//            if (dialog.open() == AlgorithmSelectionDialog.OK) {
//                lightLayoutConfig.setValue(LayoutOptions.ALGORITHM, dialog.getSelectedHint());
//            } else {
//                lightLayoutConfig.setValue(LayoutOptions.ALGORITHM, initialValue);
//            }
//
//            // trigger a new layout on the displayed diagram
//            refreshLayout(true);
//        }
//    }

    /**
     * A listener for enumeration values.
     */
    private class EnumerationListener extends SelectionAdapter {

        /** the layout option that is affected by the button. */
        private LayoutOptionData optionData;
        /** the enumeration value to set when selection is triggered. */
        private Object value;

        /**
         * Creates an enumeration listener.
         *
         * @param optionData the layout option that is affected by the button
         * @param value the enumeration value to set when selection is triggered
         */
        public EnumerationListener(final LayoutOptionData optionData, final Object value) {
            this.optionData = optionData;
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(final SelectionEvent event) {
//            if (((Button) event.widget).getSelection()) {
//                lightLayoutConfig.setValue(optionData, value);
//                refreshLayout(true);
//            }
        }
    }

}
