package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.klighd.transformations.AbstractTransformation
import de.cau.cs.kieler.klighd.TransformationContext
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import org.eclipse.emf.ecore.EClassifier
import de.cau.cs.kieler.klighd.examples.KRenderingUtil
import com.google.inject.Inject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.klighd.examples.KRenderingColors
import de.cau.cs.kieler.kiml.options.EdgeType
import de.cau.cs.kieler.core.util.Pair

class EcoreDiagramSynthesis extends AbstractTransformation<EModelElementCollection, KNode> {
	
	@Inject
	extension KRenderingUtil
	
	@Inject
	extension KRenderingColors
	
	override KNode transform(EModelElementCollection model, TransformationContext<EModelElementCollection, KNode> transformationContext) {
		
		val rootNode = KimlUtil::createInitializedNode;
		rootNode.KShapeLayout.setProperty(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.kiml.ogdf.planarization");
		rootNode.KShapeLayout.setProperty(LayoutOptions::SPACING, 75.float);
	    rootNode.KShapeLayout.setProperty(LayoutOptions::DIRECTION, Direction::UP);
		
		val classifier = model.filter(typeof(EClassifier)).toList;
		classifier.createClassifierFigures(rootNode);
		classifier.createAssociationConnections;
		classifier.createInheritanceConnections;
		
		model.filter(typeof(EPackage)).forEach[
			val classifiers = it.EClassifiers;
			classifiers.createClassifierFigures(rootNode);
			classifiers.createAssociationConnections;
			classifiers.createInheritanceConnections;
		];
		
		return rootNode;
	}
	
	def createClassifierFigures(Iterable<EClassifier> classes, KNode rootNode) {
		classes.forEach[
			val classNode = it.createRectangulareNode(80, 180);
			classNode.KRendering.add(
				factory.createKText.of(it.name).add(factory.createKFontSize.of(20))
					.add(factory.createKFontBold.setbold).add("lemon".bgColor)
			);
			classNode.KRendering.add(factory.createKLineWidth.of(2)).add("lemon".bgColor);
			rootNode.children.add(classNode);
		];
	}
	
	def createAssociationConnections(Iterable<EClassifier> classes) {
		val list = classes.toList;
		list.filter(typeof(EClass)).forEach[
			it.EStructuralFeatures.filter(typeof(EReference))
			    .filter[list.contains(it.EType)]
		        .forEach[it.createAssociationConnection];
		];
	}
	
	def createAssociationConnection(EReference ref) {
		val edge = ref.createPolyLineEdge;
		edge.KRendering.add(factory.createKLineWidth.of(2));
		(edge.KRendering as KPolyline).addConnectionArrow(2, true);
		edge.source = ref.eContainer.node;
		edge.target = ref.EType.node;
		ref.eContainer.node.outgoingEdges.add(edge);
	}
	
	def createInheritanceConnections(Iterable<EClassifier> classes) {
		val list = classes.toList;
		list.filter(typeof(EClass)).forEach[
			child | child.ESuperTypes.filter[ list.contains(it) ]
				.forEach[ parent | child.createInheritanceConnection(parent) ];
		];
	}
	
	def createInheritanceConnection(EClass child, EClass parent) {
		val edge = new Pair(child, parent).createPolyLineEdge;
		val line = edge.KRendering as KPolyline
		edge.KEdgeLayout.setProperty(LayoutOptions::EDGE_TYPE, EdgeType::GENERALIZATION)
		line.add(factory.createKLineWidth.of(2))
		line.addInheritanceConnectionArrow(2, true);
		edge.source = child.node;
		edge.target = parent.node;
		child.node.outgoingEdges.add(edge);
	}
	
}