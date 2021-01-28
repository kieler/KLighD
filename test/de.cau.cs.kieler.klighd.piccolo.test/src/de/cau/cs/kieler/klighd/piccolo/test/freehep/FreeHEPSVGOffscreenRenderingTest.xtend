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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.piccolo.test.freehep

import de.cau.cs.kieler.klighd.IOffscreenRenderer
import de.cau.cs.kieler.klighd.Klighd
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.piccolo.export.SVGOffscreenRenderer
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import java.io.ByteArrayOutputStream
import java.util.EnumSet
import java.util.regex.Pattern
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortLabelPlacement
import org.eclipse.elk.core.options.PortSide
import org.eclipse.swt.widgets.Display
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test

import static de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil.*
import static org.eclipse.core.runtime.Status.OK_STATUS
import static org.eclipse.elk.core.options.CoreOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension org.junit.Assert.assertEquals

/** 
 * @author chsch
 */
 @FixMethodOrder(JVM)
class FreeHEPSVGOffscreenRenderingTest {
	
	extension KRenderingFactory = KRenderingFactory.eINSTANCE
	
	@BeforeClass
	def static void initDisplay() {
		Display.getDefault()
	}
	
	@Test
	def void test01_singleKNodePlain() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
		'''.equalsSVGof[
			addKNodeWithSizeOf(100, 100)
		]
	}
	
	@Test
	def void test02a_singleKNodeWithEastPort() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGof[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
				]
			]
		]
	}
	
	@Test
	def void test02b_singleKNodeWithWestPort() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g transform="matrix(1, 0, 0, 1, 5, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 0, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGof[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.WEST)
					addKRectangleWithStrokeOnlyColoring
				]
			]
		]
	}
	
	@Test
	def void test03_singleKNodeWithLabel() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs">NodeLabel</text>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				createInitializedLabel(it) => [
					text = "NodeLabel"
					addKTextWithAssumedSizeOf(50, 10)
				]
			]
		]
	}
	
	@Test
	def void test04a_singleKNodeWithEastPortWithOutsideLabel() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 106, 45)">
			  <text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs">PortLabel</text>
			</g>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
				setProperty(PORT_LABELS_PLACEMENT, EnumSet.of(PortLabelPlacement.OUTSIDE,
				    PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE))
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
					createInitializedLabel(it) => [
						text = "PortLabel"
						addKTextWithAssumedSizeOf(50, 10)
					]
				]
			]
		]
	}
	
	@Test
	def void test04b_singleKNodeWithWestPortWithOutsideLabel() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g transform="matrix(1, 0, 0, 1, 56, 0)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 51, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g transform="matrix(1, 0, 0, 1, 0, 45)">
			  <text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs">PortLabel</text>
			</g>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
                setProperty(PORT_LABELS_PLACEMENT, EnumSet.of(PortLabelPlacement.OUTSIDE,
                    PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE))
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.WEST)
					addKRectangleWithStrokeOnlyColoring
					createInitializedLabel(it) => [
						text = "PortLabel"
						addKTextWithAssumedSizeOf(50, 10)
					]
				]
			]
		]
	}
	
	@Test
	def void test11_singleKNodeWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g id="MyNode" class="MyNodeClass" klighd:MyKey="MyValue" klighd:My2ndKey="KNode:0">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
		'''.equalsSVGof[
			addKNodeWithSizeOf(100, 100) => [
				semanticData = KlighdSemanticDiagramData.of(it).putID("MyNode").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyNodeClass").put("MyKey", "MyValue").put("My2ndKey") [
					viewModelElement.eClass.name + ":" + (viewModelElement as KNode).parent.children.indexOf(viewModelElement)
				]
			]
		]
	}
	
	@Test
	def void test12_singleKNodeWithPortWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyPort" class="MyPortClass" klighd:MyKey="MyValue" klighd:My2ndKey="KPort:0">
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGof[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyPort").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyPortClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KPort).node.ports.indexOf(viewModelElement)
					]
				]
			]
		]
	}
	
	@Test
	def void test13a_singleKNodeWithLabelWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyLabel" class="MyLabelClass" klighd:MyKey="MyValue" klighd:My2ndKey="KLabel:0">
			<text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs">NodeLabel</text>
			</g>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				createInitializedLabel(it) => [
					text = "NodeLabel"
					addKTextWithAssumedSizeOf(50, 10)
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyLabel").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyLabelClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KLabel).parent.labels.indexOf(viewModelElement)
					]
				]
			]
		]
	}
	
	@Test
	def void test13b_singleKNodeWithLabelWithSemanticDataWithMultiLineText() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g fill-opacity="1" font-style="normal" font-family="Helvetica" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" id="MyText" class="MyTextClass" klighd:MyKey="MyValue" klighd:My2ndKey="KText:0">
			  <text x="0" y="<Y>" style="white-space: pre">NodeLabel:</text>
			  <text x="0" y="<Y>" style="white-space: pre">Some additional info</text>
			</g>
			</g>
        '''.equalsSVGof[ // cs: deactivated generation of 'textLength' property settings on purpose, as we cannot inject reliable test size data per text line as of now
			addKNodeWithSizeOf(100, 100) => [
				createInitializedLabel(it) => [
					text = "NodeLabel:\nSome additional info"
					addKTextWithAssumedSizeOf(50, 10)
						.semanticData = KlighdSemanticDiagramData.of(it.data.head).putID("MyText").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyTextClass").put("MyKey", "MyValue").put("My2ndKey") [
							viewModelElement.eClass.name + ":" + (viewModelElement.eContainer as KGraphElement).data.indexOf(viewModelElement)
						]
				]
			]
		]
	}
	
	@Test
	def void test13c_singleKNodeWithLabelWithSemanticData_And_MultiLineTextWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyLabel" class="MyLabelClass" klighd:MyKey="MyValue" klighd:My2ndKey="KLabel:0">
			<g fill-opacity="1" font-style="normal" font-family="Helvetica" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" id="MyText" class="MyTextClass" klighd:MyKey="MyValue" klighd:My2ndKey="KText:0">
			  <text x="0" y="<Y>" style="white-space: pre" klighd:MyTextLineKey="0">NodeLabel:</text>
			  <text x="0" y="<Y>" style="white-space: pre" klighd:MyTextLineKey="1">Some additional info</text>
			</g>
			</g>
			</g>
        '''.equalsSVGof[ // cs: deactivated generation of 'textLength' property settings on purpose, as we cannot inject reliable test size data per text line as of now
			addKNodeWithSizeOf(100, 100) => [
				createInitializedLabel(it) => [
					text = "NodeLabel:\nSome additional info"
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyLabel").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyLabelClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KLabel).parent.labels.indexOf(viewModelElement)
					]
					addKTextWithAssumedSizeOf(50, 10)
						.semanticData = KlighdSemanticDiagramData.of(it.data.head).putID("MyText").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyTextClass").put("MyKey", "MyValue").put("My2ndKey") [
							viewModelElement.eClass.name + ":" + (viewModelElement.eContainer as KGraphElement).data.indexOf(viewModelElement)
						].putAtTextLine("MyTextLineKey")[
							noOfLine.toString
						]
				]
			]
		]
	}
	
	@Test
	def void test14a_singleKNodeWithEastPortWithSemanticData_And_WithOutsideLabelWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyPort" class="MyPortClass" klighd:MyKey="MyValue" klighd:My2ndKey="KPort:0">
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="MyLabel" class="MyLabelClass" klighd:MyKey="MyValue" klighd:My2ndKey="KLabel:0">
			<g transform="matrix(1, 0, 0, 1, 106, 45)">
			  <text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs">PortLabel</text>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
                setProperty(PORT_LABELS_PLACEMENT, EnumSet.of(PortLabelPlacement.OUTSIDE,
                    PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE))
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyPort").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyPortClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KPort).node.ports.indexOf(viewModelElement)
					]
					createInitializedLabel(it) => [
						text = "PortLabel"
						semanticData = KlighdSemanticDiagramData.of(it).putID("MyLabel").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyLabelClass").put("MyKey", "MyValue").put("My2ndKey") [
							viewModelElement.eClass.name + ":" + (viewModelElement as KLabel).parent.labels.indexOf(viewModelElement)
						]
						addKTextWithAssumedSizeOf(50, 10)
					]
				]
			]
		]
	}
	
	@Test
	def void test14b_singleKNodeWithEastPortWithSemanticData_And_WithOutsideLabelWithSemanticData_And_SingleLineTextWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyPort" class="MyPortClass" klighd:MyKey="MyValue" klighd:My2ndKey="KPort:0">
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="MyLabel" class="MyLabelClass" klighd:MyKey="MyValue" klighd:My2ndKey="KLabel:0">
			<g transform="matrix(1, 0, 0, 1, 106, 45)">
			  <text x="0" y="<Y>" fill-opacity="1" font-style="normal" font-family="Helvetica" style="white-space: pre" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" textLength="50.0px" lengthAdjust="spacingAndGlyphs" id="MyText" class="MyTextClass" klighd:MyKey="MyValue" klighd:My2ndKey="KText:0" klighd:MyTextLineKey="0">PortLabel</text>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGwithTextLengthsOf[
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
                setProperty(PORT_LABELS_PLACEMENT, EnumSet.of(PortLabelPlacement.OUTSIDE,
                    PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE))
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyPort").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyPortClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KPort).node.ports.indexOf(viewModelElement)
					]
					createInitializedLabel(it) => [
						text = "PortLabel"
						semanticData = KlighdSemanticDiagramData.of(it).putID("MyLabel").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyLabelClass").put("MyKey", "MyValue").put("My2ndKey") [
							viewModelElement.eClass.name + ":" + (viewModelElement as KLabel).parent.labels.indexOf(viewModelElement)
						]
						addKTextWithAssumedSizeOf(50, 10)
							.semanticData = KlighdSemanticDiagramData.of(it.data.head).putID("MyText").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyTextClass").put("MyKey", "MyValue").put("My2ndKey") [
								viewModelElement.eClass.name + ":" + (viewModelElement.eContainer as KGraphElement).data.indexOf(viewModelElement)
							].putAtTextLine("MyTextLineKey")[
								noOfLine.toString
							]
					]
				]
			]
		]
	}
	
	@Test
	def void test14c_singleKNodeWithEastPortWithSemanticData_And_WithOutsideLabelWithSemanticData_And_MultiLineTextWithSemanticData() {
		'''
			<g stroke-linejoin="miter" stroke-dashoffset="0" stroke-dasharray="none" stroke-width="1" stroke-miterlimit="10" stroke-linecap="butt">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 99.5 0.5 L 99.5 99.5 L 0.5 99.5 L 0.5 0.5 z"/>
			</g>
			<g id="MyPort" class="MyPortClass" klighd:MyKey="MyValue" klighd:My2ndKey="KPort:0">
			<g transform="matrix(1, 0, 0, 1, 100, 47.5)">
			<g fill="none" stroke-opacity="1" stroke="#000000">
			  <path d="M 0.5 0.5 L 4.5 0.5 L 4.5 4.5 L 0.5 4.5 L 0.5 0.5 z"/>
			</g>
			</g>
			<g id="MyLabel" class="MyLabelClass" klighd:MyKey="MyValue" klighd:My2ndKey="KLabel:0">
			<g transform="matrix(1, 0, 0, 1, 106, 45)" fill-opacity="1" font-style="normal" font-family="Helvetica" font-weight="normal" stroke="none" fill="#000000" font-size="<FONT_SIZE>" id="MyText" class="MyTextClass" klighd:MyKey="MyValue" klighd:My2ndKey="KText:0">
			  <text x="0" y="<Y>" style="white-space: pre" klighd:MyTextLineKey="0">PortLabel:</text>
			  <text x="0" y="<Y>" style="white-space: pre" klighd:MyTextLineKey="1">Some additional info</text>
			</g>
			</g>
			</g>
			</g>
		'''.equalsSVGof[ // cs: deactivated generation of 'textLength' property settings on purpose, as we cannot inject reliable test size data per text line as of now
			addKNodeWithSizeOf(100, 100) => [
				setProperty(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE)
                setProperty(PORT_LABELS_PLACEMENT, EnumSet.of(PortLabelPlacement.OUTSIDE,
                    PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE))
				
				addKPortWithSizeOf(5, 5) => [
					setProperty(PORT_SIDE, PortSide.EAST)
					addKRectangleWithStrokeOnlyColoring
					semanticData = KlighdSemanticDiagramData.of(it).putID("MyPort").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyPortClass").put("MyKey", "MyValue").put("My2ndKey") [
						viewModelElement.eClass.name + ":" + (viewModelElement as KPort).node.ports.indexOf(viewModelElement)
					]
					createInitializedLabel(it) => [
						text = "PortLabel:\nSome additional info"
						semanticData = KlighdSemanticDiagramData.of(it).putID("MyLabel").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyLabelClass").put("MyKey", "MyValue").put("My2ndKey") [
							viewModelElement.eClass.name + ":" + (viewModelElement as KLabel).parent.labels.indexOf(viewModelElement)
						]
						addKTextWithAssumedSizeOf(50, 10)
							.semanticData = KlighdSemanticDiagramData.of(it.data.head).putID("MyText").put(KlighdConstants.SEMANTIC_DATA_CLASS, "MyTextClass").put("MyKey", "MyValue").put("My2ndKey") [
								viewModelElement.eClass.name + ":" + (viewModelElement.eContainer as KGraphElement).data.indexOf(viewModelElement)
							].putAtTextLine("MyTextLineKey")[
								noOfLine.toString
							]
					]
				]
			]
		]
	}
	
	

	def equalsSVGof(CharSequence expectation, (KNode) => void viewModelBuilder) {
		expectation.equalsSVGof(viewModelBuilder, false)
	}
	
	def equalsSVGwithTextLengthsOf(CharSequence expectation, (KNode) => void viewModelBuilder) {
		expectation.equalsSVGof(viewModelBuilder, true)
	}
	
	def equalsSVGof(CharSequence expectation, (KNode) => void viewModelBuilder, boolean withTextLength) {
		val root = KGraphUtil.createInitializedNode()
		viewModelBuilder.apply(root)
		
		val output = new ByteArrayOutputStream()
		val status = LightDiagramServices.renderOffScreen(root, IOffscreenRenderer.SVG, output,
			KlighdSynthesisProperties.create()
				.setProperty(SVGOffscreenRenderer.GENERATOR, SVGOffscreenRenderer.GENERATOR_SVG_FREEHEP_EXTENDED)
				.setProperty(IOffscreenRenderer.TRANSPARENT_BACKGROUND, Boolean.TRUE)
				.setProperty(IOffscreenRenderer.SET_TEXT_LENGTHS, Boolean.valueOf(withTextLength))
		)
		
		if (status.exception !== null)
			throw status.exception
		
		OK_STATUS.assertEquals(status)
		
		expectation.toString.assertEquals(
			output.toString.trimMetaInfos.maskFontSizeAndTextYPos
		)
	}
	
	static val END_OF_HEADER_MARKER = '</desc>'
	
	def trimMetaInfos(String svg) {
		val trimmed = svg.trim
		
		switch endOfHeader : trimmed.indexOf(END_OF_HEADER_MARKER) + END_OF_HEADER_MARKER.length {
			case trimmed.length > endOfHeader + 6: trimmed.substring(endOfHeader, trimmed.length - 6).trim + Klighd.LINE_SEPARATOR
			case trimmed.length > endOfHeader:     trimmed.substring(endOfHeader).trim + Klighd.LINE_SEPARATOR
			default:                               trimmed
		}
	}
	
	static val P_FONT_SIZE = Pattern.compile('(font-size=")[^"]*(")')
	static val P_DY = Pattern.compile('(dy=")[^"]*(")')
	static val P_TEXT_Y = Pattern.compile('(<text[^y]*y=")[^"]*(")')
	
	def maskFontSizeAndTextYPos(String input) {
		val fontSizeMasked = P_FONT_SIZE.matcher(input).replaceAll('$1<FONT_SIZE>$2')
		val textYMasked = P_TEXT_Y.matcher(fontSizeMasked).replaceAll('$1<Y>$2')
		P_DY.matcher(textYMasked).replaceAll('$1<Y>$2')
	}
	
	def addKNodeWithSizeOf(KNode parent, float width, float height) {
		val node = createInitializedNode
		node.setSize(width, height)
		parent.children += node
		node
	}
	
	def addKPortWithSizeOf(KNode parent, float width, float height) {
		val port = createInitializedPort
		port.setSize(width, height)
		parent.ports += port
		port
	}
	
	def addKRectangleWithStrokeOnlyColoring(KGraphElement kge) {
		kge.data += createKRectangle
		kge
	}
	
	def addKTextWithAssumedSizeOf(KLabel label, float width, float height) {
		createKText => [
			label.data += it 
			setProperty(KlighdInternalProperties.KLIGHD_TESTING_WIDTH, width)
			setProperty(KlighdInternalProperties.KLIGHD_TESTING_HEIGHT, height)
			makePersistent // required as the PlacementUtil.getTestingTextSize(...) method inspects the list of persisted entries only
		]
	}
}
