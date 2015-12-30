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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.options;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IAction.ActionContext;
import de.cau.cs.kieler.klighd.IAction.ActionResult;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.SynthesisOption;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * A factory providing methods for creating diagram synthesis option controls in the diagram side bar.
 * 
 * @author chsch
 * @author als
 */
public class SynthesisOptionControlFactory {

    /** The parent composite into which controls are created. */
    private Composite parent;
    /** the form toolkit used to create controls. */
    private FormToolkit formToolkit;
    /** The set of controls to be disposed when {@link #clear()} is called. */
    private final Collection<Control> controls = Lists.newArrayList();
    /** This flag is true if this factory is factory for options of a subordinate synthesis. */
    private boolean isSubFactory = false;
    /** The title text of the parent form. */
    private final String parentTitle;
    /** The list of {SynthesisOptionControlFactory} for synthesis specific sections. */
    private final List<SynthesisOptionControlFactory> subFactories = Lists.newArrayList();
    /** If set, the category this factory is responsible for. */
    private SynthesisOption factoryCategory = null;
    /** The list of {SynthesisOptionControlFactory} for categories directly nested. */
    private final Map<SynthesisOption, SynthesisOptionControlFactory> subCategoryFactories =
            Maps.newHashMap();
    
    /** Preference prefix for sub-factory. */
    private static final String SUB_FACTORY_PREFIX = "sidebar.synthesis.expansion.";
    /** Preference prefix for category-factory. */
    private static final String CATEGORY_FACTORY_PREFIX = "sidebar.category.expansion.";
    
    /** minimal width of sliders. */
    private static final int SLIDER_MIN_WIDTH = 70;
    /** the vertical space between different option controls, e.g. 2 check buttons. */
    private static final int MAJOR_VERTICAL_SPACING = 6;
    /** the vertical space between different option value controls, e.g. 2 radio buttons. */
    private static final int MINOR_VERTICAL_SPACING = 3;
    /** the vertical space above a group separator (separator with a label). */
    private static final int GROUP_SEPARATOR_SPACING = 10;
    /** the horizontal indentation option value controls, e.g. radio buttons. */
    private static final int MINOR_HORIZONTAL_MARGIN = 10;
    
    
    /**
     * Constructor.
     * 
     * @param parent the widget container
     * @param formToolkit the form toolkit to be used to create controls
     */
    public SynthesisOptionControlFactory(final Composite parent, final FormToolkit formToolkit) {
        this.parent = parent;
        this.formToolkit = formToolkit;
        
        // configure the parent's layout
        final GridLayout gl = new GridLayout(1, false);
        gl.verticalSpacing = MAJOR_VERTICAL_SPACING;
        this.parent.setLayout(gl);
        
        // save parent title
        if (parent.getParent() instanceof Form) {
            parentTitle = ((Form) parent.getParent()).getText();
        } else {
            parentTitle = null;
        }
    }
    
    /**
     * Clear the previously created option controls.
     */
    public void clear() {
        for (final Control c : controls) {
            c.dispose();
        }
        controls.clear();
        
        // Clear sections
        for (SynthesisOptionControlFactory subFactory : subFactories) {
            subFactory.clear();
            subFactory.parent.getParent().dispose();
        }
        subFactories.clear();

        // Restore default form title
        if (isSubFactory && parent.getParent() instanceof Form) {
            ((Form) parent.getParent()).setText(parentTitle);
        }

        // Clear categories
        for (SynthesisOptionControlFactory categoryFactory : subCategoryFactories.values()) {
            categoryFactory.clear();
            categoryFactory.parent.getParent().dispose();
        }
        subCategoryFactories.clear();
    }

    /**
     * Creates a {@link SynthesisOptionControlFactory} for the given synthesis. Controls created
     * with the returned factory will be clients of this section.
     * 
     * @param synthesis
     *            the {@link ISynthesis} defining the section.
     * @return the {@link SynthesisOptionControlFactory} for clients.
     */
    public SynthesisOptionControlFactory createSubSynthesisOptionControlFactory(
            final ISynthesis synthesis) {
        // Remove default title
        if (!isSubFactory && parent.getParent() instanceof Form) {
            ((Form) parent.getParent()).setText(null);
        }
        // Create section container for diagram synthesis options
        Section synthesisSection = formToolkit.createSection(parent,
                Section.EXPANDED | Section.NO_TITLE_FOCUS_BOX | Section.TWISTIE);
        // Format text such as form titles
        synthesisSection.setFont(JFaceResources.getHeaderFont());
        synthesisSection.setForeground(formToolkit.getColors().getColor(IFormColors.TITLE));
        synthesisSection.setText(synthesis.getInputDataType().getSimpleName() + " " + parentTitle);

        // Restore saved expansion state
        final String preferenceKey =
                SUB_FACTORY_PREFIX + KlighdDataManager.getInstance().getSynthesisID(synthesis);
        IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(preferenceKey, true);
        synthesisSection.setExpanded(preferenceStore.getBoolean(preferenceKey));
        
        // Add expansion listener to save expansion state
        synthesisSection.addExpansionListener(new IExpansionListener() {

            @Override
            public void expansionStateChanging(final ExpansionEvent e) {
            }

            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                KlighdUIPlugin.getDefault().getPreferenceStore().setValue(preferenceKey,
                        e.getState());
            }
        });

        // Create client composite
        Composite client = formToolkit.createComposite(synthesisSection);
        synthesisSection.setClient(client);

        // Create the factory for diagram synthesis option controls to fill the clients container
        SynthesisOptionControlFactory subSynthesisOptionControlFactory =
                new SynthesisOptionControlFactory(client, formToolkit);
        subSynthesisOptionControlFactory.isSubFactory = true;
        subFactories.add(subSynthesisOptionControlFactory);

        return subSynthesisOptionControlFactory;
    }
    
    /**
     * Factory method for creating a controls for different {@link SynthesisOption}.
     * 
     * @param option
     *            the option
     * @param context
     *            the related {@link ViewContext} the option
     * @return true if the created control has an action, false otherwise (e.g. in case of a
     *         separator)
     */
    public boolean createOptionControl(final SynthesisOption option, final ViewContext context) {
        SynthesisOption category = option.getCategory();
        // If category is given and the factory in not the factory for this factory create the factory.
        if (category != null && factoryCategory != category) {
            // Creates (if not already created) the factory and all its parents
            return createCategorySynthesisOptionControlFactories(category, context)
                    .createOptionControl(option, context);
        } else {
            if (option.isCheckOption()) {
                createCheckOptionControl(option, context);
                return true;
            } else if (option.isChoiceOption()) {
                createChoiceOptionControl(option, context);
                return true;
            } else if (option.isRangeOption()) {
                createRangeOptionControl(option, context);
                return true;
            } else if (option.isSeparator()) {
                createSeparator(option.getName());
                return false;
            } else if (option.isCategory()) {
                // Creates (if not already created) the factory and all its parents
                // In case the factory is already created the position is updated
                SynthesisOptionControlFactory categoryFactory =
                        createCategorySynthesisOptionControlFactories(option, context);
                categoryFactory.parent.getParent()
                        .moveBelow(parent.getChildren()[parent.getChildren().length - 1]);
            }
        }
        return false;
    }
    
    /**
     * Creates a {@link SynthesisOptionControlFactory} for the given category, including all parent
     * categories. Factories will only be created once. Controls created with the returned factory
     * will be clients of this category.
     * 
     * @param option
     *            the 'category' option
     * @param context
     *            the related {@link ViewContext} the option is declared in
     * @return the {@link SynthesisOptionControlFactory} for clients.
     */
    private SynthesisOptionControlFactory createCategorySynthesisOptionControlFactories(
            final SynthesisOption option, final ViewContext context) {
        // The nesting path of the category
        LinkedList<SynthesisOption> categoryPath = new LinkedList<SynthesisOption>();
        categoryPath.add(option); // Add category to create
        SynthesisOption parentCategory = option.getCategory();
        // Bottom up creation of the path from the option (category to create) to this factory. In
        // case this factory is not a category factory the path will lead to the upper most category.
        while (parentCategory != factoryCategory) {
            // If the category path contains a cycle throw exception.
            if (categoryPath.contains(parentCategory)) {
                throw new IllegalArgumentException(
                        "The given category option has a cycle in the parent categories");
            }
            // Add parent category
            categoryPath.add(parentCategory);
            // Calculate next parent parent
            parentCategory = parentCategory.getCategory();
        }

        // Reverse the list to get a top town path form this category factory to the given option.
        Collections.reverse(categoryPath);
        
        // The category where the first part of the path should be created
        SynthesisOptionControlFactory categoryFactory = this;
        // Move path down and create all parent categories
        for (SynthesisOption category : categoryPath) {
            // Create factory only once per category
            if (!categoryFactory.subCategoryFactories.containsKey(category)) {
                categoryFactory.createCategorySynthesisOptionControlFactory(category, context);
            }
            categoryFactory = categoryFactory.subCategoryFactories.get(category);
        }

        return categoryFactory;
    }
    
    /**
     * Creates a {@link SynthesisOptionControlFactory} for the given category. Controls created with
     * the returned factory will be clients of this category.
     * 
     * @param option
     *            the 'category' option
     * @param context
     *            the related {@link ViewContext} the option is declared in
     * @return the {@link SynthesisOptionControlFactory} for clients.
     */
    private SynthesisOptionControlFactory createCategorySynthesisOptionControlFactory(
            final SynthesisOption option, final ViewContext context) {
        // Create category container for diagram synthesis options
        Section categorySection = formToolkit.createSection(parent,
                Section.CLIENT_INDENT | Section.NO_TITLE_FOCUS_BOX | Section.TWISTIE);

        if (option.getName() != null && !option.getName().isEmpty()) {
            categorySection.setText(option.getName());
        } else {
            categorySection.setText("Unknown Category");
        }

        // Restore saved expansion state or set initially
        final String preferenceKey = CATEGORY_FACTORY_PREFIX
                + KlighdDataManager.getInstance().getSynthesisID(context.getDiagramSynthesis())
                + "." + option.getName();
        IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();
        if (preferenceStore.contains(preferenceKey)) {
            categorySection.setExpanded(
                    KlighdUIPlugin.getDefault().getPreferenceStore().getBoolean(preferenceKey));
        } else {
            categorySection.setExpanded((Boolean) context.getOptionValue(option));
        }
        
        // Add expansion listener to save expansion state
        categorySection.addExpansionListener(new IExpansionListener() {

            @Override
            public void expansionStateChanging(final ExpansionEvent e) {
            }

            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                KlighdUIPlugin.getDefault().getPreferenceStore().setValue(preferenceKey,
                        e.getState());
            }
        });

        // Create client composite
        Composite client = formToolkit.createComposite(categorySection);
        categorySection.setClient(client);

        // Create the factory for diagram synthesis option controls to fill the clients container
        SynthesisOptionControlFactory categorySynthesisOptionControlFactory =
                new SynthesisOptionControlFactory(client, formToolkit);
        categorySynthesisOptionControlFactory.factoryCategory = option;
        subCategoryFactories.put(option, categorySynthesisOptionControlFactory);

        return categorySynthesisOptionControlFactory;
    }
    
    /**
     * Factory method for creating a horizontal spacer with an optional label.
     * 
     * @param labelText the label text of the separator. If {@code null} or empty, the separator won't
     *                  have a label.
     */
    private void createSeparator(final String labelText) {
        // Check if the separator is supposed to have a label
        if (labelText == null || labelText.isEmpty()) {
            final Label separator = formToolkit.createSeparator(parent, SWT.HORIZONTAL);
            controls.add(separator);
            GridDataFactory.fillDefaults().grab(true, false).applyTo(separator);
        } else {
            final Label label = formToolkit.createLabel(parent, labelText);
            controls.add(label);
            label.setForeground(formToolkit.getColors().getColor(IFormColors.TITLE));
            GridDataFactory.fillDefaults()
                .grab(true, false)
                .indent(0, GROUP_SEPARATOR_SPACING)
                .applyTo(label);
        }

    }
    
    /**
     * Factory method for creating a check button related to a 'check' option.  
     * 
     * @param option the 'check' option
     * @param context the related {@link ViewContext} the option is declared in
     */
    private void createCheckOptionControl(final SynthesisOption option,
            final ViewContext context) {

        final Button checkButton = formToolkit.createButton(parent, option.getName(), SWT.CHECK);
        checkButton.setToolTipText(option.getName());
        controls.add(checkButton);

        // set initial value
        checkButton.setSelection((Boolean) context.getOptionValue(option));

        final String us = option.getUpdateStrategy();
        final KlighdSynthesisProperties properties = 
                us == null ? null : KlighdSynthesisProperties.create().useUpdateStrategy(us);

        // add a selection listener for instant diagram updates
        checkButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                // set the new option value and trigger the diagram update
                context.configureOption(option, ((Button) event.widget).getSelection());

                // trigger the diagram update
                Display.getCurrent().asyncExec(new Runnable() {

                    public void run() {
                        if (option.getUpdateAction() != null) {
                            invokeUpdateAction(option.getUpdateAction(), context);
                        } else {
                            LightDiagramServices.updateDiagram(context, null, properties,
                                    option.getAnimateUpdate());
                        }
                    }
                });
            }
        });
    }
    
    /**
     * Factory method for creating a check button related to a 'choice' option.  
     * 
     * @param option the 'choice' option
     * @param context the related {@link ViewContext} the option is declared in
     */
    private void createChoiceOptionControl(final SynthesisOption option, final ViewContext context) {
        
        final GridLayout gl = new GridLayout();
        gl.verticalSpacing = MINOR_VERTICAL_SPACING;
        gl.marginHeight = 0;
        gl.marginWidth = 0;

        // create a container composite in order to realize correct radio button grouping
        final Composite valuesContainer = formToolkit.createComposite(parent);
        //  ... and determine its layout
        valuesContainer.setLayout(gl);
        controls.add(valuesContainer);

        // add the radio group label ...
        formToolkit.createLabel(valuesContainer, option.getName() + ":");
        
        // ... and a radio button for each possible value
        GridData vGd;
        for (final Object value : option.getValues()) {
            vGd = new GridData();
            vGd.horizontalIndent = MINOR_HORIZONTAL_MARGIN;
            
            // create the button ...
            final Button button = formToolkit.createButton(valuesContainer, value.toString(), SWT.RADIO);
            button.setToolTipText(value.toString());
            button.setLayoutData(vGd);

            final String us = option.getUpdateStrategy();
            final KlighdSynthesisProperties properties = 
                    us == null ? null : KlighdSynthesisProperties.create().useUpdateStrategy(us);

            // ... add a selection listener for instant diagram updates ...
            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent event) {
                    if (((Button) event.widget).getSelection()) {
                        // set the new option value and trigger the diagram update
                        context.configureOption(option, value);
                        
                        // trigger the diagram update
                        Display.getCurrent().asyncExec(new Runnable() {
                            public void run() {
                                if (option.getUpdateAction() != null) {
                                    invokeUpdateAction(option.getUpdateAction(), context);
                                } else {
                                    LightDiagramServices.updateDiagram(context, null, properties,
                                            option.getAnimateUpdate());
                                }
                            }
                        });
                    }
                }
            });

            // ... and set its selection depending on the currently configured option value
            button.setSelection(value.equals(context.getOptionValue(option)));
        }
    }
    
    /**
     * Factory method for creating a check button related to a 'range' option.  
     * 
     * @param option the 'range' option
     * @param context the related {@link ViewContext} the option is declared in
     */
    private void createRangeOptionControl(final SynthesisOption option, final ViewContext context) {
        
        final GridLayout gl = new GridLayout();
        gl.verticalSpacing = MINOR_VERTICAL_SPACING;
        gl.marginTop = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        
        // create a container composite in order to group the label and the scaler
        final Composite container = formToolkit.createComposite(parent);
        //  ... and determine its layout
        container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        container.setLayout(gl);
        controls.add(container);
        
        // add the label ...
        final Label label = formToolkit.createLabel(container, "");
        
        // ... and the scaler for choosing the value
        final Scale scale = new Scale(container, SWT.NONE);
        // the following setting is needed on windows
        scale.setBackground(container.getBackground());
        scale.setToolTipText(option.getName());

        // configure its layout, esp. the minimal width and the 'grab additional space' 
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.minimumWidth = SLIDER_MIN_WIDTH;
        scale.setLayoutData(gridData);

        // ... and its provided range
        final double min = option.getRange().getFirst().doubleValue();
        final double max = option.getRange().getSecond().doubleValue();
        final double stepSize = option.getStepSize().doubleValue();
        final double init = ((Number) context.getOptionValue(option)).doubleValue();
        
        final double minShifted = min + stepSize / 2;
        
        final boolean floatSteps = (option.getStepSize() instanceof Float
                || option.getStepSize() instanceof Double);
        
        final int scalerMax = 255;
        final double scalerStepSize = (max - min) / scalerMax;
        
        scale.setMinimum(0);
        scale.setMaximum(scalerMax);
        
        final int scaleInit = (int) Math.ceil((init - min) / scalerStepSize);
        scale.setSelection(scaleInit);
        
        // configure the label's text in terms of a fixed string and the initial value
        final String labelString = option.getName() + ": ";
        label.setText(labelString + context.getOptionValue(option));

        final String us = option.getUpdateStrategy();
        final KlighdSynthesisProperties properties = 
                us == null ? null : KlighdSynthesisProperties.create().useUpdateStrategy(us);

        // and finally add a selection listener for instant diagram updates
        scale.addSelectionListener(new SelectionAdapter() {
            
            // a little buffer used for dropping unnecessary events
            private double currentValue = scale.getSelection();
            
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final Scale scale = (Scale) event.widget;
                
                // determine the actually selected value
                Double value = minShifted + scalerStepSize * (scale.getSelection());
                
                // round it wrt the required step size
                value = min + Math.floor((value - min) / stepSize) * stepSize;
                
                // configure the adjusted selection
                //  lets the the scaler snap to the closest possible value
                scale.setSelection((int) Math.floor((value - min) / scalerStepSize));

                // check whether the value actually changed
                if (value == currentValue) {
                    return;
                } else {
                    currentValue = value;
                }
                
                // update the value in the label
                //  and configure the new option value in the view context
                if (floatSteps) {
                    label.setText(labelString + value);
                    context.configureOption(option, value.floatValue());
                } else {
                    label.setText(labelString + value.intValue());
                    context.configureOption(option, value.intValue());
                }
                container.layout(true);
                
                // trigger the diagram update
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        if (option.getUpdateAction() != null) {
                            invokeUpdateAction(option.getUpdateAction(), context);
                        } else {
                            LightDiagramServices.updateDiagram(context, null, properties,
                                    option.getAnimateUpdate());
                        }
                    }
                });
            }
        });
    }
    
    /**
     * Executes the action on the root element of the given view context.
     * 
     * @param actionID
     *            the id of the action
     * @param viewContext
     *            the view context
     */
    private void invokeUpdateAction(final String actionID, final ViewContext viewContext) {
        if (actionID == null) {
            return;
        }

        // Get action instance
        final IAction action = KlighdDataManager.getInstance().getActionById(actionID);

        if (action == null) {
            return;
        }

        final IViewer viewer = viewContext.getViewer();

        viewContext.getLayoutRecorder().startRecording();

        // Call the action on the root of the view model
        ActionResult result =
                action.execute(new ActionContext(viewer, null, viewer.getViewContext()
                        .getViewModel(), null));

        if (result.getActionPerformed()) {
            LightDiagramServices.layoutDiagram(viewContext, result.getAnimateLayout(),
                    ZoomStyle.create(result, viewContext), result.getFocusNode(),
                    result.getLayoutConfigs());
        } else {
            viewContext.getLayoutRecorder().stopRecording(ZoomStyle.NONE, null, 0);
        }
    }
}
