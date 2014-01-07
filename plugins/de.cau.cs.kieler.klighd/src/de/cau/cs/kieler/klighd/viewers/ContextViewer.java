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
package de.cau.cs.kieler.klighd.viewers;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static de.cau.cs.kieler.klighd.util.KlighdPredicates.isSelectable;
import static de.cau.cs.kieler.klighd.util.KlighdPredicates.notIn;
import static java.util.Collections.singleton;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IDiagramOutlinePage;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;

/**
 * A viewer for instances of type {@code ViewContext}. It is instantiated by
 * {@link de.cau.cs.kieler.klighd.views.DiagramViewPart DiagramViewPart} and
 * {@link de.cau.cs.kieler.klighd.views.DiagramEditorPart DiagramEditorPart}.<br>
 * <br>
 * This class acts as a wrapper for the viewer supplied by the current view context. The method
 * {@code getControl} returns the control of that viewer, all other methods are delegated to the
 * wrapped viewer.<br>
 * <br>
 * The motivation of this class is the intended multiformity of model viewers (although only the
 * Piccolo2D-based one has been realized). In addition, multiple diagram viewers, i.e. diagram
 * exhibiting controls, might be hosted and unified within a {@link ContextViewer}.<br>
 * <br>
 * During the initialization it is possible to set a message to be shown instead of a view context,
 * the wrapped viewer is then of type {@code StringViewer}.<br>
 * <br>
 * This viewer also implements the {@code ISelectionProvider} interface and acts as KLighD's
 * provider of selection events.
 * 
 * @author mri
 * @author chsch
 * @author msp
 */
public class ContextViewer implements IViewer<Object>, ILayoutRecorder, ISelectionProvider,
        IDiagramOutlinePage.Provider {

    /** the parent composite for diagram viewers. */
    private Composite diagramComposite;

    /** the current viewer. */
    private IViewer<Object> currentViewer;

    /** the current view context. */
    private ViewContext currentViewContext = null;

    /** the {@link #currentViewer} if it is a {@link ILayoutRecorder}, <code>null</code> otherwise. */
    private ILayoutRecorder layoutRecorder;


    /**
     * Constructs a view context viewer.
     * 
     * @param parent
     *            the parent composite
     */
    public ContextViewer(final Composite parent) {
        this.diagramComposite = parent;
    }

    /**
     * Employs the given <code>viewer</code>.
     * 
     * @param viewer
     *            the {@link IViewer} to be employed
     */
    protected synchronized void setViewer(final IViewer<?> viewer) {
        // remove the current viewer if someone exists
        if (currentViewer != null) {
            currentViewer.getControl().dispose();
            currentViewer = null;
        }

        @SuppressWarnings("unchecked")
        final IViewer<Object> objViewer = (IViewer<Object>) viewer;
        currentViewer = objViewer;

        diagramComposite.layout();
        
        if (currentViewer instanceof ILayoutRecorder) {
            layoutRecorder = (ILayoutRecorder) currentViewer;
        }
    }


    /* -------------------------------------------------- */
    /*   implementation of IDiagramOutlinePage.Provider   */
    /* -------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public IDiagramOutlinePage getDiagramOutlinePage() {
        if (currentViewer instanceof IDiagramOutlinePage.Provider) {
            return ((IDiagramOutlinePage.Provider) currentViewer).getDiagramOutlinePage();
        } else {
            return null;
        }
    }


    /* -------------------------------------------------- */
    /*   implementation of ILayoutRecorder                */
    /* -------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public void startRecording() {
        if (layoutRecorder != null) {
            layoutRecorder.startRecording();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void stopRecording(final int animationTime) {
        if (layoutRecorder != null) {
            layoutRecorder.stopRecording(animationTime);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stopRecording(final ZoomStyle zoomStyle,
            final int animationTime) {
        if (layoutRecorder != null) {
            layoutRecorder.stopRecording(zoomStyle, animationTime);
        }
    }


    /* -------------------------------------------------- */
    /*   implementation of IViewer                        */
    /* -------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public ContextViewer getContextViewer() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final Object model) {
        setModel(model, false);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setModel(final Object model, final boolean sync) {
        // if the model is a view context adapt the viewer to the given context if possible
        if (model instanceof ViewContext) {
            final ViewContext viewContext = (ViewContext) model;

            // set the new view context
            currentViewContext = viewContext;
            
            // create and set a corresponding new viewer
            setViewer(viewContext.createViewer(this, diagramComposite));

            // initialize the current selection
            notifySelectionListeners(new KlighdTreeSelection(currentViewContext));
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized Object getModel() {
        if (currentViewer != null) {
            return currentViewer.getModel();
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public ViewContext getViewContext() {
        return currentViewContext;
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return currentViewer != null ? currentViewer.getControl() : null;
    }

    /**
     * Returns the currently active view context.
     * 
     * @deprecated use {@link IViewer#getViewContext()}, which is implemented by this class
     * 
     * @return the view context
     */
    public ViewContext getCurrentViewContext() {
        return currentViewContext;
    }

    /**
     * Returns the currently active viewer.
     * 
     * @return the viewer
     */
    public IViewer<?> getActiveViewer() {
        return currentViewer;
    }
    
    /**
     * {@inheritDoc}
     */
    public void zoomToLevel(final float zoomLevel, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoomToLevel(zoomLevel, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final ZoomStyle style, final int duration) {
        if (currentViewer != null) {
            currentViewer.zoom(style, duration);
        }
    }


    /* ----------------------------- */
    /*   the view manipulation API   */
    /* ----------------------------- */

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
        if (diagramNode instanceof KNode) {
            return currentViewer.isExpanded((KNode) diagramNode);
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final KNode diagramElement) {
        return currentViewer.isExpanded(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    public void collapse(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
        if (diagramNode instanceof KNode) {
            currentViewer.collapse((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void collapse(final KNode diagramElement) {
        currentViewer.collapse(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
        if (diagramNode instanceof KNode) {
            currentViewer.expand((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final KNode diagramElement) {
        currentViewer.expand(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final Object semanticElement) {
        final EObject diagramNode =
                getViewContext().getTargetElement(semanticElement, KNode.class);
        if (diagramNode instanceof KNode) {
            currentViewer.toggleExpansion((KNode) diagramNode);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final KNode diagramElement) {
        currentViewer.toggleExpansion(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final Object semanticElement) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.hide(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final KGraphElement diagramElement) {
        currentViewer.hide(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final Object semanticElement) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.show(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final KGraphElement diagramElement) {
        currentViewer.show(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void clip(final Object semanticElement) {
        if (semanticElement == null) {
            this.currentViewer.clip(this.getViewContext().getViewModel());
            return;
        }
        
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KNode) {
            this.currentViewer.clip(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void clip(final KNode diagramElement) {
        if (diagramElement == null) {
            this.currentViewer.clip(this.getViewContext().getViewModel());
        } else {
            this.currentViewer.clip(diagramElement);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public KNode getClip() {
        return this.currentViewer.getClip();
    }


    /**
     * {@inheritDoc}
     */
    public void reveal(final Object semanticElement, final int duration) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void reveal(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.reveal(diagramElement, duration);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object semanticElement, final int duration) {
        final EObject diagramElement =
                getViewContext().getTargetElement(semanticElement, KGraphElement.class);
        if (diagramElement instanceof KGraphElement) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void centerOn(final KGraphElement diagramElement, final int duration) {
        if (currentViewer != null) {
            currentViewer.centerOn(diagramElement, duration);
        }
    }


    /* ----------------------------- */
    /*   the selection setting API   */
    /* ----------------------------- */

    private final Function<Object, EObject> getViews = new Function<Object, EObject>() {
        public EObject apply(final Object semanticElement) {
            final EObject diagramElement = getViewContext().getTargetElement(semanticElement, null);
            return isSelectable().apply(diagramElement) ? diagramElement : null;
        }
    };
    

    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final Object semanticElement) {
        final EObject diagramElement = getViews.apply(semanticElement);
        if (diagramElement != null) {
            toggleSelectionOfDiagramElements(singleton(diagramElement));
        }
    };
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KGraphElement diagramElement) {
        toggleSelectionOfDiagramElements(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KText diagramElement) {
        toggleSelectionOfDiagramElements(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfSemanticElements(final Set<Object> semanticElements) {
        toggleSelectionOfDiagramElements(
                Sets.newHashSet(transform(semanticElements, getViews)));
   
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfDiagramElements(final Set<? extends EObject> toBeToggled) {
        final List<EObject> theSelection = newArrayList(this.getSelection());
        for (EObject diagramElement : Sets.filter(toBeToggled, isSelectable())) {
            boolean removed = theSelection.remove(diagramElement);
            if (!removed) {
                theSelection.add(diagramElement);
            }
        }
        updateSelection(theSelection);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final Object semanticElement) {
        final EObject diagramElement = getViews.apply(semanticElement);
        // Collections.singleton accepts 'null' values and 'updateSelection' does so, too!
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KGraphElement diagramElement) {
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KText diagramElement) {
        updateSelection(singleton(diagramElement));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToSemanticElements(final Iterable<? extends Object> semanticElements) {
        updateSelection(transform(semanticElements, getViews));
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToDiagramElements(final Iterable<? extends EObject> diagramElements) {
        updateSelection(filter(diagramElements, isSelectable()));
    }
    

    /**
     * Function for determining the (root) {@link KRendering KRenderings} corresponding to a
     * selected diagram element. Returns the input value wrapped in a {@link java.util.Collection
     * Collection} if that is a {@link KRendering}, and all contained {@link KRendering KRenderings}
     * if the input is a {@link KGraphElement}. The function is used for determining the elements
     * whose value of {@link KlighdInternalProperties#SELECTED} is to be updated.
     */
    private static final Function<EObject, Iterable<KRendering>> AS_RENDERING
        = new Function<EObject, Iterable<KRendering>>() {
        
        public Iterable<KRendering> apply(final EObject eo) {
            if (KRenderingPackage.eINSTANCE.getKRendering().isInstance(eo)) {
                return singleton((KRendering) eo);
            } else {
                return newArrayList(filter(eo.eContents(), KRendering.class));
            }
        }
    };

    /**
     * Updates the selection provided by this {@link IViewer}.
     * 
     * @param diagramElements an {@link Iterable} of view model elements being selected
     */
    private void updateSelection(final Iterable<? extends EObject> diagramElements) {
        // here the selected elements are assumed to be diagram elements, i.e. KGraph elements or KTexts
        
        final List<EObject> currentlySelected = newArrayList(getSelection());
        final List<EObject> toBeSelected = newArrayList(filter(diagramElements, Predicates.notNull())); 
        
        for (KRendering r : concat(transform(filter(currentlySelected, notIn(toBeSelected)),
                AS_RENDERING))) {
            r.setProperty(KlighdInternalProperties.SELECTED, false);
        }
        
        for (KRendering r : concat(transform(toBeSelected, AS_RENDERING))) {
            r.setProperty(KlighdInternalProperties.SELECTED, true);
        }

        createSelection(toBeSelected);
    }
    
    /**
     * A.
     * 
     * @param elements a
     */
    protected void createSelection(final Collection<EObject> elements) {
        // update the selection status for the ISelectionProvider interface
        notifySelectionListeners(new KlighdTreeSelection(getViewContext(), elements));
    }


    /* -------------------------------------------------- */
    /*   The selection provider related stuff             */
    /* -------------------------------------------------- */

    /** the current selection. */
    private KlighdTreeSelection selection = KlighdTreeSelection.EMPTY;

    /** the selection listeners registered on this view. */
    private Set<ISelectionChangedListener> selectionListeners = Sets.newLinkedHashSet();

    /**
     * Notifies the registered {@link ISelectionChangedListener ISelectionChangedListeners}. Such
     * listeners are registered e.g. by the platform in order to broadcast changes in the selection
     * across change listeners registered in the {@link org.eclipse.ui.ISelectionService
     * ISelectionService}.
     * 
     * @param theSelection
     */
    private void notifySelectionListeners(final KlighdTreeSelection theSelection) {
        this.selection = theSelection;
        synchronized (selectionListeners) {
            for (ISelectionChangedListener listener : selectionListeners) {
                listener.selectionChanged(new SelectionChangedEvent(this, theSelection));
            }
        }
    }


    /* -------------------------------------------------- */
    /*   implementation of ISelectionProvider             */
    /* -------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public KlighdTreeSelection getSelection() {
        return this.selection;
    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(final ISelection selection) {
        // not supported yet
    }

    /**
     * {@inheritDoc}
     */
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        selectionListeners.remove(listener);
    }
}
