package de.cau.cs.kieler.klighd.examples.ecore

import javax.inject.Inject
import de.cau.cs.kieler.klighd.transformations.AbstractTransformation
import de.cau.cs.kieler.klighd.TransformationContext
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeType
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.TransformationOption
import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists

class EcoreDiagramSynthesis extends AbstractTransformation<EModelElementCollection, KNode> {
	
	@Inject
	extension KNodeExtensions
	
	@Inject
	extension KEdgeExtensions
	
    @Inject
    extension KRenderingExtensions
    
    @Inject
    extension KPolylineExtensions
    
	@Inject
	extension KColorExtensions
	
    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE

    private static val CHOSEN = "Chosen classes";
    private static val CHOSEN_AND_RELATED = "Chosen (highlighted) & related classes";
    private static val ALL = "All classes, selection highlighted";
    
    private static val String CLASS_FILTER_NAME = "Class filter";

    /**
     * The class filter option definition that allows the user to customize the class diagram.
     */
    private static val TransformationOption CLASS_FILTER = TransformationOption::createChoiceOption(CLASS_FILTER_NAME,
       ImmutableList::of(CHOSEN, CHOSEN_AND_RELATED, ALL), CHOSEN_AND_RELATED);

    
    /**
     * {@inheritDoc}
     * <br><br>
     * Registers the content filter option.
     */
    override public getTransformationOptions() {
        return ImmutableSet::of(CLASS_FILTER);
    }   


    /**
     * {@inheritDoc}
     */
	override KNode transform(EModelElementCollection choice, TransformationContext<EModelElementCollection, KNode> transformationContext) {
	    use(transformationContext);
		
		return KimlUtil::createInitializedNode => [
            it.addLayoutParam(LayoutOptions::ALGORITHM, "de.cau.cs.kieler.kiml.ogdf.planarization");
            it.addLayoutParam(LayoutOptions::SPACING, 75f);
            it.addLayoutParam(LayoutOptions::DIRECTION, Direction::UP);
		
            // The chosen (depicted) classifiers. This list will be supplemented with related classifiers,
            //  depending on the value of CLASS_FILTER.
            val depictedClasses = choice.filter(typeof(EClassifier)).toList;
	    
	        if (CLASS_FILTER.optionValue == CHOSEN) {
                
                depictedClasses.createElementFigures(it);
                
	        } else if (CLASS_FILTER.optionValue == CHOSEN_AND_RELATED) {
                
                // The chosen classes ...
                val chosenClasses = choice.filter(typeof(EClass)).toList => [
                   // ... are inspected, their related classes are put into the classifiers list as well!
	               it.forEach[depictedClasses.addAll(it.EStructuralFeatures.filter(typeof(EReference)).map[it.EType])];
                   it.forEach[depictedClasses.addAll(it.ESuperTypes)];
                ];
                
                depictedClasses.createElementFigures(it);

                chosenClasses.forEach[
                    it.node.KRendering.background = "lightPink".color;
                ];
                
	        } else {
                val chosenClasse = Lists::newArrayList(depictedClasses);
                
                depictedClasses += depictedClasses.head.EPackage.EClassifiers => [classes |
                    classes.createElementFigures(it)
                ];

                chosenClasse.forEach[
                    it.node.KRendering.background = "lightPink".color;
                ];
	        }
		
            choice.filter(typeof(EPackage)).forEach[ ePackage |
                ePackage.EClassifiers => [ clazz |
                    clazz.createElementFigures(it);
                ];
            ];
		];
	}
	
	def createElementFigures(Iterable<EClassifier> classes, KNode rootNode) {
	    classes.createClassifierFigures(rootNode);
	    classes.createAssociationConnections();
	    classes.createInheritanceConnections();
	}
	
	def createClassifierFigures(Iterable<EClassifier> classes, KNode rootNode) {
		classes.forEach[ EClassifier clazz |
            rootNode.children += clazz.createNode().putToLookUpWith(clazz) => [
                val boxWidth = if (clazz.name.length < 10) 180 else clazz.name.length*12+50;
                it.setNodeSize(boxWidth, 80);
                it.data += renderingFactory.createKRectangle() => [
                    it.setLineWidth(2);
                    it.background = "lemon".color;
                    it.children += renderingFactory.createKText().putToLookUpWith(clazz) => [
                        it.text = clazz.name;
                        it.setFontSize(20);
                        it.setFontBold(true);
                    ];
                ];         
            ];
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
	    ref.createEdge() => [
    		it.source = ref.eContainer.node;
	       	it.target = ref.EType.node;
	        it.data += renderingFactory.createKPolyline() => [
	            it.setLineWidth(2);
	            it.addArrowDecorator();
	        ];
	    ];
	}
	
	def createInheritanceConnections(Iterable<EClassifier> classes) {
		val list = classes.toList;
		list.filter(typeof(EClass)).forEach[
			child | child.ESuperTypes.filter[ list.contains(it) ]
				.forEach[ parent | child.createInheritanceConnection(parent) ];
		];
	}
	
	def createInheritanceConnection(EClass child, EClass parent) {
		new Pair(child, parent).createEdge() => [
            it.addLayoutParam(LayoutOptions::EDGE_TYPE, EdgeType::GENERALIZATION);
    	    it.source = child.node;
	        it.target = parent.node;
	        it.data += renderingFactory.createKPolyline() => [
                it.setLineWidth(2);
                it.addInheritanceTriangleArrowDecorator();
	        ];		    
		];
	}
	
}