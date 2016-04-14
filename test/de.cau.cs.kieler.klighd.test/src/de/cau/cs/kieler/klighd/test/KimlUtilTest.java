/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.test;

import static com.google.common.collect.Iterables.getLast;

import java.net.URL;
import java.util.Iterator;

import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.test.ModelingUtilTest.IsIteratorWithSize;

/**
 * A collection of tests checking some helper methods of {@link ElkUtil}.
 *
 * @author chsch
 */
public class KimlUtilTest {

    private static KNode testModel;

    /**
     * Loads 'circuit.kgx' from within this bundle.
     */
    @BeforeClass
    public static void loadTestModel() {
        final ResourceSet set = new ResourceSetImpl();

        final Iterator<URL> it = Iterators.forEnumeration(
                KlighdTestPlugin.getDefault().getBundle().findEntries("/", "circuit.kgx", true));

        if (!it.hasNext()) {
            Assert.fail("Test model 'circuit.kgx' could not be found!");
        }

        final Resource res = set.getResource(URI.createURI(it.next().toString(), true), true);
        testModel = (KNode) res.getContents().get(0);
    }

    // CHECKSTYLEOFF MagicNumber|Method

    @Test
    public void getConnectedEdgesTest01() {
        final Iterator<KEdge> res = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getOutgoingEdges()));

        Assert.assertThat(res, IsIteratorWithSize.iteratorWithSize(3));
    }

    @Test
    public void getConnectedEdgesTest01a() {
        final Iterator<KEdge> edges = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getOutgoingEdges()));

        final KEdge first = Iterators.getNext(edges, null);
        Assert.assertTrue(first != null
                // source is supposed to be the NOT circuit
                && first.getSource().getChildren().isEmpty()
                // target is supposed to be the container of the AND + OR circuit
                && first.getTarget().getChildren().size() == 2);
    }

    @Test
    public void getConnectedEdgesTest01b() {
        final Iterator<KEdge> edges = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getOutgoingEdges()));

        Iterators.advance(edges, 1);

        final KEdge second = Iterators.getNext(edges, null);
        Assert.assertTrue(second != null
                // target is supposed to be the container of the AND + OR circuit
                && second.getSource().getChildren().size() == 2
                // target is supposed to be the container of the AND circuit
                && second.getSource().getChildren().get(0) == second.getTarget()
                && second.getTarget().getChildren().isEmpty());
    }

    @Test
    public void getConnectedEdgesTest01c() {
        final Iterator<KEdge> edges = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getOutgoingEdges()));

        Iterators.advance(edges, 2);

        final KEdge third = Iterators.getNext(edges, null);
        Assert.assertTrue(third != null
                // target is supposed to be the container of the AND + OR circuit
                && third.getSource().getChildren().size() == 2
                // target is supposed to be the container of the OR circuit
                && third.getSource().getChildren().get(1) == third.getTarget()
                && third.getTarget().getChildren().isEmpty());
    }

    @Test
    public void getConnectedEdgesTest02() {
        final Iterator<KEdge> res = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getIncomingEdges()));

        Assert.assertThat(res, IsIteratorWithSize.iteratorWithSize(2));
    }

    @Test
    public void getConnectedEdgesTest02a() {
        final Iterator<KEdge> edges = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getIncomingEdges()));

        final KEdge first = Iterators.getNext(edges, null);
        Assert.assertTrue(first != null
                // source is supposed to be the container of the AND + OR circuit
                && first.getSource().getChildren().size() == 2
                // target is supposed to be the NOT circuit
                && first.getTarget().getChildren().isEmpty());
    }

    @Test
    public void getConnectedEdgesTest02b() {
        final Iterator<KEdge> edges = ElkUtil.getConnectedEdges(
                getLast(getLast(getLast(testModel.getChildren()).getChildren()).getIncomingEdges()));

        Iterators.advance(edges, 1);

        final KEdge second = Iterators.getNext(edges, null);
        Assert.assertTrue(second != null
                // target is supposed to be the container of the AND + OR circuit
                && second.getTarget().getChildren().size() == 2
                // source is supposed to be the container of the OR circuit
                && second.getTarget().getChildren().get(1) == second.getSource()
                && second.getSource().getChildren().isEmpty());
    }
}
