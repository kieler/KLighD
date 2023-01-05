/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.test.freehep

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.swt.widgets.Display
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test

import static de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil.*

import static extension de.cau.cs.kieler.klighd.piccolo.test.freehep.FreeHEPSVGOffscreenRenderingTest.*
import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/** 
 * @author chsch
 */
 @FixMethodOrder(NAME_ASCENDING)
class FreeHEPSVGOffscreenRenderingWithEdgesFirstAndBackgroundTest {
	
	static extension KRenderingExtensions = new KRenderingExtensions()
	
	static extension KContainerRenderingExtensions = new KContainerRenderingExtensions()
	
	@BeforeClass
	def static void initDisplay() {
		Display.getDefault()
	}
	
	// Note: The diagrams are auto-arranged by ELK Layered with the root's padding being fixed to 'ElkPadding(10)'.
	// This way the additional space required by the oversized background figures exactly matches the padding, and
	// the left-most located node's drawing starts at (x,y) == (0,0). Thus, no whitespace is trimmed/compensated
	// on the left and top border by the exporter. (The exporter accumulates the nested transforms.)
	// Otherwise we would see unexpected transforms on the KEdges' drawings, as their points always refer to the
	//  coordinate system of the source and target nodes' common parent node.
	
	@Test
	def void test01_singleKNode() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ff0000" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="backgroundFigure"/>
			</g>
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			addNode(null, Colors.RED)
		]
	}
	
	@Test
	def void test01b_singleKNodeBGfigureInvisible() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			addNode(null, Colors.RED).KContainerRendering.children.head.invisible = true
		]
	}
	
	@Test
	def void test02_twoKNodes() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ff0000" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node1-backgroundFigure"/>
			</g>
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#0000ff" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node2-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node1-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node2-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			setLayoutOption(CoreOptions.SPACING_COMPONENT_COMPONENT, 25d)
			addNode("node1", Colors.RED)
			addNode("node2", Colors.BLUE)
		]
	}
	
	@Test
	def void test02b_twoKNodesWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ff0000" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node1-backgroundFigure"/>
			</g>
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#0000ff" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node2-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="node1">
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node1-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
			<g id="node2">
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node2-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			setLayoutOption(CoreOptions.SPACING_COMPONENT_COMPONENT, 25d)
			addNode("node1", Colors.RED).semanticData = KlighdSemanticDiagramData.of(it).putID("node1")
			addNode("node2", Colors.BLUE).semanticData = KlighdSemanticDiagramData.of(it).putID("node2")
		]
	}
	
	@Test
	def void test03_twoKNodesWithAnEdge() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ff0000" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node1-backgroundFigure"/>
			</g>
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#0000ff" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node2-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="theEdge">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 110 60 L 135 60"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node1-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node2-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			setLayoutOption(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 25d)
			val n1 = addNode("node1", Colors.RED)
			val n2 = addNode("node2", Colors.BLUE)
			
			createInitializedEdge() => [
				source = n1
				target = n2
				
				semanticData = KlighdSemanticDiagramData.of(it).putID("theEdge")
			];
		]
	}
	
	@Test
	def void test03b_twoKNodesWithAnEdgeAndSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ff0000" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node1-backgroundFigure"/>
			</g>
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#0000ff" stroke="none">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z" id="node2-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 125, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 119.5 0.5 L 119.5 119.5 L 0.5 119.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="theEdge">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 110 60 L 135 60"/>
			</g>
			</g>
			<g id="node1">
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node1-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 10, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
			<g id="node2">
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill-opacity="1" fill-rule="nonzero" fill="#ffffff" stroke="none">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z" id="node2-non-backgroundFigure"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 135, 10)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGwithEdgesFirstOf[
			setLayoutOption(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 25d)
			val n1 = addNode("node1", Colors.RED).semanticData = KlighdSemanticDiagramData.of(it).putID("node1")
			val n2 = addNode("node2", Colors.BLUE).semanticData = KlighdSemanticDiagramData.of(it).putID("node2")
			
			createInitializedEdge() => [
				source = n1
				target = n2
				
				semanticData = KlighdSemanticDiagramData.of(it).putID("theEdge")
			];
		]
	}
	
	
	def private static KNode addNode(KNode it, String id, Colors bgColor) {
		val extension contExts = _kContainerRenderingExtensions
		return addKNodeWithSizeOf(100, 100) => [
			addRectangle() => [
				invisible = true;
				addRectangle().setAreaPlacementData().from(LEFT, -10, 0, TOP, -10, 0).to(RIGHT, -10, 0, BOTTOM, -10, 0).drawAsBackgroundFigure().setBackground(bgColor).setSemanticData(
					KlighdSemanticDiagramData.of(it).putID((id !== null ? id + "-" : "") + "backgroundFigure")
				);
				addRectangle().setBackground(Colors.WHITE).setSemanticData(
					KlighdSemanticDiagramData.of(it).putID((id !== null ? id + "-" : "") + "non-backgroundFigure")
				);
			]
		]
	}
	
	def private static equalsSVGwithEdgesFirstOf(CharSequence expectation, (KNode) => void viewModelBuilder) {
		expectation.equalsSVGof(viewModelBuilder, true, false)
	}
}
