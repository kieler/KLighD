/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Defines the notification API to be implemented by concrete listeners being informed about changes
 * of the corresponding diagram view, e.g. changes in visible area, expansion of elements, the
 * current clip, ...
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public interface IViewChangeListener {

    /**
     * Called by KLighD viewers in order to notify <code>this</code> {@link IViewChangeListener}.
     *
     * @param change a {@link ViewChange} notification providing information on the occurred change
     */
    void viewChanged(ViewChange change);


    /**
     * Describes notification objects providing information of the view change to be broadcasted to
     * {@link IViewChangeListener IViewChangeListeners}.
     *
     * @author chsch
     */
    public static class ViewChange {

        private final IViewer activeViewer;
        private final ViewChangeType changeType;
        private final KGraphElement affectedElement;
        private final double diagramScale;

        /**
         * Constructor.
         *
         * @param viewer
         *            the viewer the change took place in
         * @param type
         *            an element of {@link ViewChangeType} denoting the view change type.
         * @param element
         *            the element affected during the view change being notified
         * @param viewPort
         *            a {@link Rectangle2D} denoting the visible area of the displayed diagram view
         * @param diagramScale
         *            the zoom factor of the currently visible diagram area
         */
        public ViewChange(final IViewer viewer, final ViewChangeType type,
                final KGraphElement element, final Rectangle2D viewPort, final double diagramScale) {
            this.activeViewer = viewer;
            this.changeType = type;
            this.affectedElement = element;
            this.diagramScale = diagramScale;
        }

        /**
         * Provides the {@link IViewer} the change occurred in.
         *
         * @return the {@link IViewer} the change occurred in.
         */
        public IViewer getViewer() {
            return this.activeViewer;
        }

        /**
         * Provides the corresponding {@link ViewContext} for convenience.
         *
         * @return the {@link ViewContext} associated to the diagram the change took place in.
         */
        public ViewContext getViewContext() {
            if (this.activeViewer != null) {
                return this.activeViewer.getViewContext();
            } else {
                return null;
            }
        }

        /**
         * Provides the view change type.
         *
         * @return a element of {@link ViewChangeType} denoting view change type.
         */
        public ViewChangeType getType() {
            return this.changeType;
        }

        /**
         * Provides the {@link KGraphElement} affected during the view change being notified.
         *
         * @return the {@link KGraphElement} affected during the view change being notified
         */
        public KGraphElement getAffectedElement() {
            return affectedElement;
        }

        /**
         * Provides the zoom factor of the currently visible diagram area.
         * The value is normalized to 1.0, i.e. 1.0 denotes original size.
         *
         * @return the zoom factor of the currently visible diagram area
         */
        public double getDiagramZoomScale() {
            return diagramScale;
        }


        // ---------------------------------- //
        //  visible diagram parts evaluation  //
        // ---------------------------------- //

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KNode KNodes} visible at the moment of iterating (lazy evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons.
         *
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KNode> visibleDiagramNodes() {
            return activeViewer.getVisibleDiagramNodes();
        }

        /**
         * Creates an {@link org.eclipse.emf.common.util.TreeIterator TreeIterator} providing the
         * {@link KGraphElement KGraphElements} visible at the moment of iterating (lazy
         * evaluation)!<br>
         * <br>
         * <b>Caution:</b> Traversal must be performed by the display (UI) thread for integrity
         * reasons. {@link de.cau.cs.kieler.core.kgraph.KEdge KEdges} are likely to be returned
         * twice, as both outgoing as well as incoming edges of a {@link KNode} must be considered.
         *
         * @return the desired {@link org.eclipse.emf.common.util.TreeIterator TreeIterator}
         */
        public Iterator<KGraphElement> visibleDiagramElements() {
            return activeViewer.getVisibleDiagramElements();
        }


        // -------------------------------------- //
        //  view change notification suppression  //
        // -------------------------------------- //

        private Map<ViewChangeType, Long> notificationSuppressions;

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on the first
         * of the subsequent view changes for each of the given {@link ViewChangeType
         * ViewChangeTypes}.<br>
         * For example, if the view change listener calls
         *
         * <pre>
         * suppressSubsequentNotifications(ViewChangeType.COLLAPSE, ViewChangeType.VIEWPORT);
         * </pre>
         *
         * and events occur like this
         *
         * <pre>
         * EXPAND, CLIP, VIEWPORT, COLLAPSE, VIEWPORT, COLLAPSE
         * </pre>
         *
         * the listener will be notified on
         *
         * <pre>
         * EXPAND, CLIP,                     VIEWPORT, COLLAPSE
         * </pre>
         *
         * @param changeTypes
         *            the {@link ViewChangeType ViewChangeTypes} the caller doesn't want to hear
         *            about in (near) future
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentNotifications(final ViewChangeType...changeTypes) {
            return suppressSubsequentNotifications(0, changeTypes);
        }

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on the first
         * of the subsequent view changes for each of the given {@link ViewChangeType
         * ViewChangeTypes}.<br>
         * For example, if the view change listener calls
         *
         * <pre>
         * suppressSubsequentNotifications(ViewChangeType.COLLAPSE, ViewChangeType.VIEWPORT);
         * </pre>
         *
         * and events occur like this
         *
         * <pre>
         * EXPAND, CLIP, VIEWPORT, COLLAPSE, VIEWPORT, COLLAPSE
         * </pre>
         *
         * the listener will be notified on
         *
         * <pre>
         * EXPAND, CLIP,                     VIEWPORT, COLLAPSE
         * </pre>
         *
         * @param changeTypes
         *            the {@link ViewChangeType ViewChangeTypes} the caller doesn't want to hear
         *            about in (near) future
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentNotifications(final Iterable<ViewChangeType> changeTypes) {
            return suppressSubsequentNotifications(0, changeTypes);
        }

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on view
         * changes of the given {@link ViewChangeType ViewChangeTypes} within the upcoming
         * {@code durationInMS}.<br>
         * <b>Note:</b> If {@code durationInMS} is equal to {@code 0} the next notification of the
         * corresponding type(s) will be omitted without any temporal limitation of that
         * suppression. See {@link #suppressSubsequentNotifications(ViewChangeType...)} for more
         * details.
         *
         * @param durationInMS
         *            the duration in milliseconds the caller will not receive any events of type
         *            {@code changeTypes}
         * @param changeTypes
         *            the type(s) of view change(s) the caller doesn't want to hear about in (near)
         *            future
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentNotifications(final int durationInMS,
                final ViewChangeType... changeTypes) {
            return suppressSubsequentNotifications(durationInMS, Lists.newArrayList(changeTypes));
        }

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on view
         * changes of the given {@link ViewChangeType ViewChangeTypes} within the upcoming
         * {@code durationInMS}.<br>
         * <b>Note:</b> If {@code durationInMS} is equal to {@code 0} the next notification of the
         * corresponding type(s) will be omitted without any temporal limitation of that
         * suppression. See {@link #suppressSubsequentNotifications(Iterable)} for more details.
         *
         * @param durationInMS
         *            the duration in milliseconds the caller will not receive any events of type
         *            {@code changeTypes}
         * @param changeTypes
         *            the type(s) of view change(s) the caller doesn't want to hear about in (near)
         *            future
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentNotifications(final int durationInMS,
                final Iterable<ViewChangeType> changeTypes) {

            if (notificationSuppressions == null) {
                notificationSuppressions = Maps.newHashMap();
            }

            for (final ViewChangeType type : changeTypes) {
                if (durationInMS > 0) {
                    notificationSuppressions.put(type, System.currentTimeMillis() + durationInMS);
                } else {
                    notificationSuppressions.put(type, 0L);
                }
            }

            return this;
        }


        /**
         * Static singleton list instance for avoiding recurring instantiation in the following
         * methods.
         */
        private static final List<ViewChangeType> VIEW_PORT =
                Collections.singletonList(ViewChangeType.VIEW_PORT);

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on
         * {@link ViewChangeType#VIEW_PORT VIEW_PORT} changes within the upcoming
         * {@code durationInMS}.<br>
         * For example, if the view change listener calls
         *
         * <pre>
         * suppressSubsequentViewPortChangeNotification();
         * </pre>
         *
         * and events occur like this
         *
         * <pre>
         * EXPAND, CLIP, VIEWPORT, COLLAPSE, VIEWPORT, COLLAPSE
         * </pre>
         *
         * the listener will be notified on
         *
         * <pre>
         * EXPAND, CLIP,           COLLAPSE, VIEWPORT, COLLAPSE
         * </pre>
         *
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentViewPortChangeNotification() {
            return suppressSubsequentNotifications(0, VIEW_PORT);
        }

        /**
         * Instructs KLighD to stop notifying the calling {@link IViewChangeListener} on
         * {@link ViewChangeType#VIEW_PORT VIEW_PORT} changes within the upcoming
         * {@code durationInMS}.<br>
         * <b>Note:</b> If {@code durationInMS} is equal to {@code 0} the delivery of the next view
         * port change notification will be omitted without any temporal limitation of that
         * suppression. See {@link #suppressSubsequentViewPortChangeNotifications(int)} for more details.
         *
         * @param durationInMS
         *            the duration in milliseconds the caller will not receive any events of type
         *            {@code changeTypes}
         * @return <code>this</code> {@link ViewChange} for convenience
         */
        public ViewChange suppressSubsequentViewPortChangeNotifications(final int durationInMS) {
            return suppressSubsequentNotifications(durationInMS, VIEW_PORT);
        }


        /**
         * Evaluation and reset of the notification suppression configuration, to be called by
         * KLighD only!
         *
         * @return the {@link Map} of {@link ViewChangeType ViewChangeTypes} and corresponding
         *         durations
         */
        public Map<ViewChangeType, Long> obtainAndResetNotificationSuppressionConfig() {
            final Map<ViewChangeType, Long> result = notificationSuppressions;
            notificationSuppressions = null;
            return result;
        }
    }
}
