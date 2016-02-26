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
/**
 *
 */
package de.cau.cs.kieler.klighd.util;

import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.impl.KGraphDataImpl;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;

/**
 * A graph data implementation for storing context information about elements in a
 * visualization of a KGraph with attached KRendering data.
 *
 * @author mri
 * @author chsch (moved it here from klighd.piccolo to be referenced in layout and compare stuff)
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class RenderingContextData extends KGraphDataImpl {

    /**
     * Adds an instance of {@code RenderingContextData} to the given diagram element if it has no such
     * data added. In any case returns the data instance.
     *
     * @param element
     *            the diagram element
     * @return the corresponding {@code RenderingContextData}
     */
    public static RenderingContextData get(final KGraphElement element) {
        RenderingContextData data = basicGet(element);
        if (data == null) {
            data = new RenderingContextData();
            element.getData().add(data);
        }
        return data;
    }

    /**
     * Returns the corresponding {@code RenderingContextData} for the given diagram element if any
     * one exists, and <code>null</code> otherwise.
     *
     * @param element
     *            the diagram element
     * @return the corresponding {@code RenderingContextData} or <code>null</code>
     */
    public static RenderingContextData basicGet(final KGraphElement element) {
        final RenderingContextData data = element.getData(RenderingContextData.class);
        return data;
    }

    /**
     * Tests whether {@link RenderingContextData} are available for the given {@link KGraphElement}
     * <code>element</code>.
     *
     * @param element
     *            to test
     * @return <code>true</code> if {@link RenderingContextData} are available, <code>false</code>
     *         otherwise.
     */
    public static boolean exists(final KGraphElement element) {
        return basicGet(element) != null;
    }

    /**
     * Tests whether the given {@link KGraphElement} is <i>active</i>, i.e. whether a related figure
     * (PNode) is contained in the corresponding diagram's figure tree.
     *
     * <code>element</code>.
     *
     * @param element
     *            to test
     * @return <code>true</code> if {@link RenderingContextData} are available, <code>false</code>
     *         otherwise.
     */
    public boolean isActive(final KGraphElement element) {
        return this.getProperty(KlighdInternalProperties.ACTIVE);
    }

    /**
     * Removes the (first) {@link RenderingContextData} instance from its container if any exists.
     *
     * @param element
     *            the element whose associated instance of {@link RenderingContextData} is to be
     *            removed
     */
    public static void removeFrom(final KGraphElement element) {
        if (element == null) {
            return;
        }

        final RenderingContextData data = element.getData(RenderingContextData.class);
        if (data != null) {
            data.remove();
        }
    }

    /**
     * Removes <code>this</code> instance from its container.
     */
    public void remove() {
        final EObject container = this.eContainer();
        if (container != null) {
            ((KGraphElement) container).getData().remove(this);
        }
    }

    /**
     * Returns true if <code>property</code> has been defined for <code>this</code> instance.
     *
     * @param property
     *            the <code>property</code> to check for definition
     * @return true if <code>property</code> is set, false otherwise.
     */
    public boolean containsPoperty(final IProperty<?> property) {
        return this.getProperties().keySet().contains(property);
    }

    /**
     * A predicate definition used to drop inactive nodes while processing the layout input graph.<br>
     * Currently all children of a node are active or non-active at a time, a selective filtering is
     * not done so far (see e.g. DiagramController#addExpansionListener). This might change in
     * future.<br>
     * <br>
     * <b>Caution:</b> Note the special behavior in case no {@link RenderingContextData} are
     * attached to {@code element}. As opposed to the default value of
     * {@link KlighdInternalProperties#ACTIVE} this predicate returns <code>true</code> in that
     * case. This is required in the
     * {@link de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager KlighdLayoutManager}
     * for applying layout to view models that are not shown by any viewer and whose
     * {@link KGraphElement KGraphElements} are not tagged to be 'active'. This may happen in batch
     * tests, for example.
     */
    public static final Predicate<KGraphElement> IS_ACTIVE = new Predicate<KGraphElement>() {
        public boolean apply(final KGraphElement element) {
            final RenderingContextData data = basicGet(element);

            return data == null // || !data.containsPoperty(KlighdInternalProperties.ACTIVE)
                    || data.getProperty(KlighdInternalProperties.ACTIVE);
        }
    };

    /**
     * A predicate definition used to test the {@link KlighdInternalProperties#POPULATED} state of
     * {@link KNode KNodes}.<br>
     * <br>
     * <b>Caution:</b> Note the special behavior in case no {@link RenderingContextData} are
     * attached to {@code node}. As opposed to the default value of
     * {@link KlighdInternalProperties#POPULATED} this predicate returns <code>true</code> in that
     * case. This is required in the
     * {@link de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager KlighdLayoutManager}
     * for applying layout to view models that are not shown by any viewer and whose compound nodes
     * are not tagged to be 'populated'. This may happen in batch tests, for example.
     */
    public static final Predicate<KNode> IS_POPULATED = new Predicate<KNode>() {
        public boolean apply(final KNode node) {
            final RenderingContextData data = basicGet(node);

            return data == null // || !data.containsPoperty(KlighdInternalProperties.POPULATED)
                    || data.getProperty(KlighdInternalProperties.POPULATED);
        }
    };
}
