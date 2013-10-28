/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.util.RunnableWithResult;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.internal.preferences.KlighdPreferences;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * A view context is a data record containing the required configuration information to translate a
 * model into a diagram and draw it on the screen, as well as provide user operations on it.<br>
 * <br>
 * 
 * ViewContexts contain information on
 * <ul>
 * <li>the business model (input model) that is to be shown,</li>
 * <li>the source workbench part the business model stems from,</li>
 * <li>the transformations that are involved in creating the diagram,</li>
 * <li>the update strategy that is to be applied in case of diagram updates,</li>
 * <li>the resulting view model describing the diagram,</li>
 * <li>the {@link IViewerProvider} that wraps the instantiation of the viewer being used</li>
 * </ul>
 * 
 * ViewContexts do currently provide no operations, related ones are outsourced into
 * {@link LightDiagramServices}.<br>
 * <br>
 * <b>TODO</b>: Do we actually want to keep the "transformation chain" feature?<br>
 * <br>
 * 
 * @author mri
 * @author chsch
 */
public final class ViewContext extends MapPropertyHolder {

    /** the serial version UID. */
    private static final long serialVersionUID = -431994394109554393L;

    /** the part the source model was selected from (if can reasonably be determined). */
    private transient IWorkbenchPart sourceWorkbenchPart = null;
    
    /** the viewer provider. */
    private transient IViewerProvider<KNode> viewerProvider = null;
    /** the update strategy. */
    private transient IUpdateStrategy<KNode> updateStrategy = null;
    /** the list of transformation contexts in this view context. */
    private transient List<TransformationContext<?, ?>> transformationContexts = null;
    /** the reversed list of transformation contexts. */
    private transient List<TransformationContext<?, ?>> transformationContextsRev = null;
    /** the business model to be represented by means of this context. */
    private Object businessModel = null;
    /** the view model is initiated while configuring the involved {@link IUpdateStrategy} and kept
     * for the whole life-cycle of the view context, in order to enable proper incremental update. */
    private KNode viewModel = null;
    
    /** the {@link IViewer} being in charge of showing this {@link ViewContext}. */
    private IViewer<KNode> viewer = null;
    
    /** the view-specific zoom style. */
    private ZoomStyle zoomStyle = KlighdPlugin.getDefault().getPreferenceStore()
            .getBoolean(KlighdPreferences.ZOOM_TO_FIT) ? ZoomStyle.ZOOM_TO_FIT : ZoomStyle.NONE;
    
    /**
     * Default constructor.
     */
    public ViewContext() {
        super();
        this.transformationContexts = Lists.newLinkedList();
        this.transformationContextsRev = Lists.reverse(transformationContexts);
    }
    
    /**
     * Sets the source workbench part (part the source model has been chosen in).
     * 
     * @param theSourceWorkbenchPart
     *            the source workbench part (part the source model has been chosen in)
     */
    public void setSourceWorkbenchPart(final IWorkbenchPart theSourceWorkbenchPart) {
        this.sourceWorkbenchPart = theSourceWorkbenchPart;
    }

    /**
     * Returns the source workbench part.
     * 
     * @return the source workbench part (part the source model has been chosen in)
     */
    public IWorkbenchPart getSourceWorkbenchPart() {
        return sourceWorkbenchPart;
    }

    /**
     * Returns the source workbench part viewer (experimental).
     * 
     * @return the source workbench part viewer(viewer the source model has been chosen in)
     */
    public Viewer getSourceWorkbenchPartViewer() {
        if (this.sourceWorkbenchPart == null) {
            return null;
        }
        try {
            IWorkbenchPart part = this.sourceWorkbenchPart;
            return (Viewer) part.getClass().getMethod("getViewer").invoke(part);
        } catch (Exception e) {
            StatusManager.getManager().addLoggedStatus(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "KLighD: Determination of a viewer widget (beyond the KLighD viewer) "
                                    + "showing the currently depicted model failed.\n"
                                    + "This error might occured while trying to focus a depicted model "
                                    + "element in a related editor."));
        }
        return null;               
    }
    
    /**
     * Sets the input model.
     * 
     * @param model
     *            the input model
     */    
    void setInputModel(final Object model) {
        this.businessModel = model; 
    }

    /**
     * Returns the current model to be represented.
     * 
     * @return the current model to be represented.
     */
    public Object getInputModel() {
        RunnableWithResult<?> modelAccess = this.getProperty(KlighdProperties.MODEL_ACCESS);
        if (modelAccess != null) {
            modelAccess.run();
            return modelAccess.getResult();
        }
        return this.businessModel;
    }

    /**
     * Sets the contexts viewer provider.
     * 
     * @param viewerProvider
     *            the viewer provider
     */
    void setViewerProvider(final IViewerProvider<KNode> viewerProvider) {
        this.viewerProvider = viewerProvider;
    }

    /**
     * Returns the contexts viewer provider.
     * 
     * @return the viewer provider
     */
    public IViewerProvider<KNode> getViewerProvider() {
        return viewerProvider;
    }

    /**
     * Sets the update strategy used in this view context.<br>
     * In addition, it configures the view model root, which is kept for the whole life-cycle of the
     * view context, in order to enable proper incremental update.
     * 
     * @param updateStrategy
     *            the update strategy
     */
    void setUpdateStrategy(final IUpdateStrategy<KNode> updateStrategy) {
        this.updateStrategy = updateStrategy;
        if (updateStrategy != null) {
            viewModel = updateStrategy.getInitialBaseModel(this);
        } else {
            viewModel = null;
        }
    }

    /**
     * Returns the update strategy used in this view context.
     * 
     * @return the update strategy
     */
    public IUpdateStrategy<?> getUpdateStrategy() {
        return updateStrategy;
    }

    /**
     * Returns the view model root, which is derived from the update strategy
     * and kept for the whole life-cycle of the view context,
     * in order to enable proper incremental update.
     * 
     * @return the base model or null if no update strategy is set
     */
    public KNode getViewModel() {
        return viewModel;
    }
    
    /**
     * Setter.
     * 
     * @param theViewer
     *            the {@link IViewer} being in charge of showing this {@link ViewContext}.
     */
    void setViewer(final IViewer<KNode> theViewer) {
        this.viewer = theViewer;
    }
    
    /**
     *  @return the {@link IViewer} being in charge of showing this {@link ViewContext}.
     */
    public IViewer<KNode> getViewer() {
        return viewer;
    }

    /**
     * @return the zoomStyle
     */
    public ZoomStyle getZoomStyle() {
        return zoomStyle;
    }
    
    /**
     * @return whether the zoom style is zoom to fit.
     */
    public boolean isZoomToFit() {
        return zoomStyle == ZoomStyle.ZOOM_TO_FIT;
    }
    
    /**
     * Keep in mind that zoom to focus has a higher priority, thus
     * this can only return false if {@link #isZoomToFit()} returns false.
     * 
     * @return whether the zoom style is zoom to focus.
     */
    public boolean isZoomToFocus() {
        return !isZoomToFit() && zoomStyle == ZoomStyle.ZOOM_TO_FOCUS;
    }
    
    /**
     * @param zoomStyle the zoomStyle to set
     */
    public void setZoomStyle(final ZoomStyle zoomStyle) {
        this.zoomStyle = zoomStyle;
    }
    
    /**
     * Adds a transformation context to the view context.
     * 
     * @param transformationContext
     *            the transformation context
     */
    void addTransformationContext(final TransformationContext<?, ?> transformationContext) {
        transformationContexts.add(transformationContext);
        transformationContext.setViewContext(this);
    }

    /**
     * Adds a list of transformation contexts to the view context.
     * 
     * @param contexts
     *            the transformation contexts
     */
    void addTransformationContexts(final List<TransformationContext<?, ?>> contexts) {
        for (TransformationContext<?, ?> transformationContext : contexts) {
            addTransformationContext(transformationContext);
        }
    }

    /**
     * Returns the list of transformation contexts.
     * 
     * @return the transformation contexts
     */
    public List<TransformationContext<?, ?>> getTransformationContexts() {
        return transformationContexts;
    }

    /**
     * Returns the class of the target model in the complete context.
     * 
     * @return the class of the target model or null if there is no transformation in this context
     */
    public Class<?> getTargetClass() {
        return viewerProvider.getModelClass();
    }
    

    // ---------------------------------------------------------------------------------- //
    //  Source target element handling    

    /**
     * An additional map containing target source mappings that are contributed into this view
     * context while merging another view context into this one. It is populated, e.g, when the
     * diagram is incrementally extended. Usually such mappings are stored in the transformation
     * contexts.
     */
    private Map<Object, Object> additionalTargetSourceElementMap = null;

    /**
     * Returns the element in the source model that translates to the given object in the context's
     * model by using the transformations invoked to obtain that model.
     * 
     * @param element
     *            the object in the context's model
     * @return the element in the source model or null if the link could not be made
     */
    public Object getSourceElement(final Object element) {
        Object model;
        if (KGraphPackage.eINSTANCE.getKGraphData().isInstance(element)) {
            model = ((KGraphData) element).getProperty(KlighdInternalProperties.MODEL_ELEMEMT);
        } else if (KGraphPackage.eINSTANCE.getKGraphElement().isInstance(element)) {
            KLayoutData layoutData = ((KGraphElement) element).getData(KLayoutData.class);
            if (layoutData != null) {
                model = layoutData.getProperty(KlighdInternalProperties.MODEL_ELEMEMT);
            } else {
                model = null;
            }
        } else {
            model = null;
        }
        
        if (model != null) {
            return model;
        }
        
        if (additionalTargetSourceElementMap != null) {
            Object source = additionalTargetSourceElementMap.get(element);
            if (source != null) {
                return source;
            }
        }
        
        Object source = element;
        for (TransformationContext<?, ?> transformationContext : transformationContextsRev) {
            if (source == null) {
                return null;
            }
            source = transformationContext.getSourceElement(source);
        }
        return source;
    }

    /**
     * An additional map containing source target mappings that are contributed into this view
     * context while merging another view context into this one. It is populated, e.g, when the
     * diagram is incrementally extended. Usually such mappings are stored in the transformation
     * contexts.
     */
    private Multimap<Object, Object> additionalSourceTargetElementMap = null;

    /**
     * Returns the element in the context's model that derives from the given element in the source
     * model by using the transformations invoked to obtain the context's model.
     * 
     * @param element
     *            the element in the source model
     * @return the element in the context's model or null if the link could not be made
     */
    public Collection<?> getTargetElement(final Object element) {
        if (additionalSourceTargetElementMap != null) {
            Collection<?> target = additionalSourceTargetElementMap.get(element);
            if (target != null) {
                return target;
            }
        }
        
        Object target = element;
        for (TransformationContext<?, ?> transformationContext : transformationContexts) {
            if (target == null) {
                return null;
            }
            target = transformationContext.getTargetElement(element);
        }
        // the following cast a hot fix, the above loops we be removed soon due to the planned
        //  abolishment of the transformation chain feature
        return (Collection<?>) target;
    }

    /**
     * Merges the source target mappings of <code>otherViewContext</code> into this one.
     * 
     * @param otherViewContext another view context to take source target mappings from
     */
    public void merge(final ViewContext otherViewContext) {
        if (this.additionalTargetSourceElementMap == null) {
            this.additionalTargetSourceElementMap = Maps.newHashMap();
        }
        if (this.additionalSourceTargetElementMap == null) {
            this.additionalSourceTargetElementMap = HashMultimap.create();
        }
        
        for (Object target : Iterables.getLast(otherViewContext.getTransformationContexts())
                .getTargetElements()) {
            Object source = otherViewContext.getSourceElement(target);
            if (source != null) {
                this.additionalTargetSourceElementMap.put(target, source);
                this.additionalSourceTargetElementMap.put(source, target);
            }
        }
    }

    /**
     * Clears out the mapping data of the involved transformation contexts and those of the
     * additional maps that may contain mappings of lazily added diagram elements.<br>
     * <br>
     * <b>TODO</b>: Doing that this way is wrong in case of incremental updates. We need another
     * solution for that situation.
     */
    public void clearSourceTargetMappings() {
        if (additionalSourceTargetElementMap != null) {
            additionalSourceTargetElementMap.clear();
        }
        if (additionalTargetSourceElementMap != null) {
            additionalTargetSourceElementMap.clear();
        }
        for (TransformationContext<?, ?> tContext : this.transformationContexts) {
            tContext.clear();
        }
    }

    /**
     * Resets the view context.
     */
    protected void reset() {
        transformationContexts.clear();
        viewerProvider = null;
        updateStrategy = null;
        businessModel = null;
        viewModel = null;
        if (additionalSourceTargetElementMap != null) {
            additionalSourceTargetElementMap.clear();
        }
        if (additionalTargetSourceElementMap != null) {
            additionalTargetSourceElementMap.clear();
        }
    }


    // ---------------------------------------------------------------------------------- //
    //  Transformation option handling    

    private Map<TransformationContext<?, ?>, Set<TransformationOption>> options = null;
    
    /**
     * Returns the set of {@link TransformationOption TransformationOptions} declared by the
     * transformation and forward to the users in the UI in order to allow them to influence the
     * transformation result.
     * 
     * @return the set of {@link TransformationOption TransformationOptions}
     * 
     * @author chsch
     */
    public Map<TransformationContext<?, ?>, Set<TransformationOption>> getTransformationOptions() {
        if (this.options == null) {
            Map<TransformationContext<?, ?>, Set<TransformationOption>> map = Maps
                    .newLinkedHashMap();
            for (TransformationContext<?, ?> c : this.transformationContexts) {
                map.put(c, c.getTransformationOptions());
            }        
            this.options = ImmutableMap.copyOf(map);
        }
        return this.options;
    }
    

    // ---------------------------------------------------------------------------------- //
    //  Recommended layout option handling    
    
    /**
     * Passes the recommended layout options and related values provided by the last transformation
     * context in the chain of such contexts, i.e. the last transformation in the transformation
     * chain.
     * 
     * @return a map of options (map keys) and related values (map values)
     */
    public Map<IProperty<?>, Collection<?>> getRecommendedLayoutOptions() {
        return Iterables.getFirst(this.transformationContextsRev, null)
                .getRecommendedLayoutOptions();
    }
}
