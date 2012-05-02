package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.klighd.transformations.AbstractTransformation
import de.cau.cs.kieler.klighd.TransformationContext
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.examples.EModelElementCollection
import org.eclipse.emf.ecore.EClassifier
import de.cau.cs.kieler.klighd.examples.KRenderingUtil
import com.google.inject.Inject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.Direction

class EcoreDiagramSynthesis extends AbstractTransformation<EModelElementCollection, KNode> {
	
	@Inject
	extension KRenderingUtil
	
	override KNode transform(EModelElementCollection model, TransformationContext<EModelElementCollection, KNode> transformationContext) {
		
		val rootNode = KimlUtil::createInitializedNode;
		rootNode.KShapeLayout.setProperty(LayoutOptions::SPACING, Float::valueOf("75.0"));
	    rootNode.KShapeLayout.setProperty(LayoutOptions::DIRECTION, Direction::DOWN);
		
		val classifier = model.filter(typeof(EClassifier)).toList;
		classifier.createClassifierFigures(rootNode);
		classifier.createAssociationConnections;
		
		model.filter(typeof(EPackage)).forEach[
			val classifiers = it.EClassifiers;
			classifiers.createClassifierFigures(rootNode);
			classifiers.createAssociationConnections;
		];
		
		return rootNode;
	}
	
	def createClassifierFigures(Iterable<EClassifier> classes, KNode rootNode) {
		classes.forEach[
			val classNode = it.createRoundedRectangulareNode(100, 150);
			classNode.KRendering.add(factory.createKLineWidth.of(2));
			classNode.KRendering.add(factory.createKText.of(it.name));
			rootNode.children.add(classNode)
		];
	}
	
	def createAssociationConnections(Iterable<EClassifier> classes) {
		val list = classes.toList;
		list.filter(typeof(EClass)).forEach[
			it.EStructuralFeatures.filter(typeof(EReference))
			    .filter[list.contains(it.EType)]
		        .forEach[it.createAssociationConnection]
		]
	}
	
	def createAssociationConnection(EReference ref) {
		val edge = ref.createPolyLineEdge;
		edge.KRendering.add(factory.createKLineWidth.of(2));
		val ellipse = factory.createKEllipse;

		val dpd = factory.createKDecoratorPlacementData;
		dpd.location = Float::valueOf("0.99");
		dpd.height = 10;
		dpd.width = 10;
		dpd.XOffset = -10;
		dpd.YOffset = -5;
		ellipse.placementData = dpd;
		 
		edge.KRendering.add(ellipse);
		edge.source = ref.eContainer.node;
		edge.target = ref.EType.node;
		ref.eContainer.node.outgoingEdges.add(edge);
	}
}