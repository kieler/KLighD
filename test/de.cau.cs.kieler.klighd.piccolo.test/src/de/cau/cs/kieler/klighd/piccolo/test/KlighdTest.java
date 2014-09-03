/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.test;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PAffineTransform;

// CHECKSTYLEOFF Javadoc|MagicNumber

/**
 * 
 * Testing the klighd piccolo interface.
 * 
 * @author ckru
 * 
 */
public class KlighdTest {

    /**
     * To test: - PNode structure - all adapters set when adding stuff (expanded/collapsed) -
     * responsive to layoutdata changes
     */

    /*
     * public void test() {
     * 
     * KlighdMainCamera camera = new KlighdMainCamera(); PRoot pRoot = new PRoot();
     * pRoot.addChild(camera);
     * 
     * KNode root = KimlUtil.createInitializedNode(); KLabel l =
     * KimlUtil.createInitializedLabel(root); l.setText("rootnode");
     * 
     * // create a controller for the graph DiagramController controller = new
     * DiagramController(root, camera, true); INode topNode = controller.getNode();
     * 
     * controller.collapse(root);
     * 
     * 
     * controller.expand(root);
     * 
     * //for testing animation controller.startRecording();
     * 
     * 
     * 
     * controller.stopRecording(ZoomStyle.NONE, 0);
     * 
     * }
     */

    

    

    


}
