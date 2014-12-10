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

import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IViewChangeListener;
import de.cau.cs.kieler.klighd.IViewChangeListener.ViewChange;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.ViewChangeType;
import de.cau.cs.kieler.klighd.ZoomStyle;

/**
 * An abstract base class for concrete KGraph/KRendering viewers. It provides implementations of
 * those methods concrete viewers are not in charge of implementing, e.g. the source model related
 * ones ({@link Object}-based) and the selection related ones. The view model related methods (
 * {@link KGraphElement}/{@link de.cau.cs.kieler.core.kgraph.KNode KNode}/{@link KText}-based ones)
 * must be implemented by concrete viewers.
 * 
 * @author mri
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public abstract class AbstractViewer implements IViewer {

    /**
     * {@inheritDoc}
     */
    public void setModel(final Object model) {
        this.setModel(model, false);
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final Object model, final boolean sync) {
        if (model instanceof KNode) {
            setModel((KNode) model, sync);
        }
    }

    /**
     * Specialized version of {@link #setModel(Object, boolean)} tailored to {@link KNode} input models.
     * 
     * @param model
     *            the input model
     * @param sync
     *            true if the viewer should synchronize the visualization with the model; false else
     */
    public abstract void setModel(KNode model, boolean sync);


    private SetMultimap<ViewChangeType, IViewChangeListener> viewChangeListeners;
    private Map<IViewChangeListener, Map<ViewChangeType, Long>> notificationSuppressions;

    /**
     * {@inheritDoc}
     */
    public void addViewChangedListener(final IViewChangeListener listener,
            final ViewChangeType... eventTypes) {
        if (listener == null) {
            return;
        }

        if (viewChangeListeners == null) {
            viewChangeListeners = HashMultimap.create();
            notificationSuppressions = Maps.newHashMap();
        }
        
        final ViewChangeType[] types =
                eventTypes != null && eventTypes.length != 0 ? eventTypes : ViewChangeType.values();
        
        for (final ViewChangeType t : types) {
            viewChangeListeners.put(t, listener);
        }

        viewChangeListenersView = null;
    }

    /**
     * {@inheritDoc}
     */
    public void removeViewChangedEventListener(final IViewChangeListener listener) {
        if (listener != null) {
            this.viewChangeListeners.values().remove(listener);
            this.notificationSuppressions.remove(listener);

            this.viewChangeListenersView = null;
        }
    }

    private SetMultimap<ViewChangeType, IViewChangeListener> viewChangeListenersView;

    /**
     * Provides the {@link Multimap} containing the registered {@link IViewChangeListener
     * IViewChangeListeners}.
     * 
     * @return the {@link Multimap} containing the registered {@link IViewChangeListener
     *         IViewChangeListeners}.
     */
    protected Multimap<ViewChangeType, IViewChangeListener> getViewChangeListeners() {
        if (viewChangeListenersView == null) {
            viewChangeListenersView = Multimaps.unmodifiableSetMultimap(this.viewChangeListeners);
        }
        return viewChangeListeners;
    }
    
    /**
     * Notifies the registered {@link IViewChangeListener IViewChangeListeners} of a diagram view
     * change.
     * 
     * @param type
     *            the corresponding {@link ViewChangeType}
     * @param affectedElement
     *            a potentially affect few element, e.g. a collapsed or expanded
     *            {@link de.cau.cs.kieler.core.kgraph.KNode KNode}, may be <code>null</code>
     * @param viewPort
     *            a {@link Rectangle2D} with the bounds of the currently visible diagram area
     * @param diagramScale
     *            the zoom factor of the currently visible diagram area
     */
    protected void notifyViewChangeListeners(final ViewChangeType type,
            final KGraphElement affectedElement, final Rectangle2D viewPort,
            final double diagramScale) {

        if (viewChangeListeners == null) {
            return;
        }

        // create a ViewChange instance being "shown" to all registered listeners
        final ViewChange change = new ViewChange(this, type, affectedElement, viewPort, diagramScale);

        // take the time being used for evaluating suppression deadlines
        final long time = System.currentTimeMillis();

        for (final IViewChangeListener listener : viewChangeListeners.get(type)) {
            final Map<ViewChangeType, Long> earlierSuppressions = notificationSuppressions.get(listener);

            // check the presence of any suppression configs for 'listener'
            if (earlierSuppressions != null) {
                final Long suppressionDeadline = earlierSuppressions.get(type);

                // check the presence of any suppression config for 'type' wrt. 'listener'
                if (suppressionDeadline != null) {
                    final long suppressionDeadlineValue = suppressionDeadline.longValue();

                    // if a ('listener','type') corresponding suppression config is given, distinguish...
                    if (suppressionDeadlineValue == 0) {
                        // this case denotes a suppression of a single event w/o regard of the time:
                        //  the next one is to be notified again so ...
                        earlierSuppressions.remove(type);

                        // ... stop evaluating for 'listener', and ...
                        continue;

                    } else if (suppressionDeadlineValue >= time) {
                        // the suppression deadline is in future so don't do anything for 'listener'
                        //  and ...
                        continue;

                    } else if (suppressionDeadlineValue < time) {
                        // the suppression deadline was in past so remove this config and proceed
                        earlierSuppressions.remove(type);
                    }
                }
            }

            // perform the notification by "showing" 'change' to 'listener'
            listener.viewChanged(change);

            // obtain the notification suppression config optionally contributed by 'listener'
            //  this is a DESTRUCTIVE read operation!
            final Map<ViewChangeType, Long> listenersSuppressionConfig =
                    change.obtainAndResetNotificationSuppressionConfig();

            if (listenersSuppressionConfig != null) {
                // if unequal to 'null', i.e. 'listener' contributed suppression infos, evaluate them
                updateSuppressionData(listener, listenersSuppressionConfig);
            }
        }
    }

    private void updateSuppressionData(final IViewChangeListener listener,
            final Map<ViewChangeType, Long> declaredSuppressionConfig) {

        final Map<ViewChangeType, Long> currentConfig = notificationSuppressions.get(listener);

        if (currentConfig == null) {
            // in case no suppression config is available, yet, just put the new one into the map
            notificationSuppressions.put(listener, declaredSuppressionConfig);
            return;
        }

        // otherwise update the entries of 'currentConfig' entry by entry
        for (final Map.Entry<ViewChangeType, Long> entry : declaredSuppressionConfig.entrySet()) {
            final ViewChangeType type = entry.getKey();
            final Long currentDeadline = currentConfig.get(type);

            if (currentDeadline == null) {
                // in case no suppression of 'type' is currently configured
                currentConfig.put(type, entry.getValue());

            } else if (currentDeadline < entry.getValue()) {
                // in case the deadline of 'type' suppressions is to be extended
                //  note that this case implicitly includes the case of 'currentDeadline' equals to '0'!
                currentConfig.put(type, entry.getValue());
            } // else if currentDeadline >= entry.getValue the later deadline will be kept
        }
    }

    /**
     * Forwards the given <code>selection</code> to the employed {@link ContextViewer} that is in
     * charge of broadcasting it into the platform and the registered selection listeners.
     *
     * @param selection
     *            the new {@link ISelection}
     */
    protected void updateSelection(final ISelection selection) {
        this.getContextViewer().notifySelectionListeners(selection);
    }

    /* ----------------------------- */
    /*   the view manipulation API   */
    /* ----------------------------- */

    /**
     * {@inheritDoc}
     */
    public boolean isDisplayed(final Object semanticElement, final boolean checkParents) {
        return getContextViewer().isVisible(semanticElement, false);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isVisible(final Object semanticElement, final boolean checkParents) {
        return getContextViewer().isVisible(semanticElement, false);
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<KNode> getVisibleDiagramNodes() {
        return getContextViewer().getVisibleDiagramNodes();
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<KGraphElement> getVisibleDiagramElements() {
        return getContextViewer().getVisibleDiagramElements();
    }

    /**
     * {@inheritDoc}
     */
    public void reveal(final Object semanticElement, final int duration) {
        getContextViewer().reveal(semanticElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public void centerOn(final Object semanticElement, final int duration) {
        getContextViewer().centerOn(semanticElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public void panToTopLeftCorner(final Object semanticElement, final int duration) {
        getContextViewer().panToTopLeftCorner(semanticElement, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public void zoomToLevel(final float zoomLevel, final int duration) {
        getContextViewer().zoomToLevel(zoomLevel, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public void zoomToFocus(final Object semanticElement, final int duration) {
        getContextViewer().zoomToFocus(semanticElement, duration);
        
    }

    /**
     * {@inheritDoc}
     */
    public void zoom(final ZoomStyle style, final int duration) {
        getContextViewer().zoom(style, duration);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isExpanded(final Object semanticElement) {
        return getContextViewer().isExpanded(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void collapse(final Object semanticElement) {
        getContextViewer().collapse(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void expand(final Object semanticElement) {
        getContextViewer().expand(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleExpansion(final Object semanticElement) {
        getContextViewer().toggleExpansion(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void hide(final Object semanticElement) {
        getContextViewer().hide(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void show(final Object semanticElement) {
        getContextViewer().show(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void clip(final Object semanticElement) {
        getContextViewer().clip(semanticElement);
    }

    /**
     * {@inheritDoc}
     */
    public void scale(final Object semanticElement, final float scale) {
        getContextViewer().scale(semanticElement, scale);
    }

    /**
     * {@inheritDoc}
     */
    public float getScale(final Object semanticElement) {
        return getContextViewer().getScale(semanticElement);
    }

    /* ---------------------------------------------------------- */
    /*   the selection setting API                                */
    /*    it is completely implemented by the ContextViewer,      */
    /*    no implementations of this class need to implement it!  */
    /* ---------------------------------------------------------- */

    /**
     * {@inheritDoc}
     */
    public ISelection getSelection() {
        return getContextViewer().getSelection();
    }

    /**
     * {@inheritDoc}
     */
    public KlighdTreeSelection getDiagramSelection() {
        return getContextViewer().getDiagramSelection();
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final Object semanticElement) {
        getContextViewer().toggleSelectionOf(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KGraphElement diagramElement) {
        getContextViewer().toggleSelectionOf(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOf(final KText diagramElement) {
        getContextViewer().toggleSelectionOf(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfSemanticElements(final Set<Object> diagramElements) {
        getContextViewer().toggleSelectionOfSemanticElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void toggleSelectionOfDiagramElements(final Set<? extends EObject> diagramElements) {
        getContextViewer().toggleSelectionOfDiagramElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final Object semanticElement) {
        getContextViewer().resetSelectionTo(semanticElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KGraphElement diagramElement) {
        getContextViewer().resetSelectionTo(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionTo(final KText diagramElement) {
        getContextViewer().resetSelectionTo(diagramElement);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToSemanticElements(final Iterable<? extends Object> diagramElements) {
        getContextViewer().resetSelectionToSemanticElements(diagramElements);
    }
    
    /**
     * {@inheritDoc}
     */
    public void resetSelectionToDiagramElements(final Iterable<? extends EObject> diagramElements) {
        getContextViewer().resetSelectionToDiagramElements(diagramElements);
    }
}

