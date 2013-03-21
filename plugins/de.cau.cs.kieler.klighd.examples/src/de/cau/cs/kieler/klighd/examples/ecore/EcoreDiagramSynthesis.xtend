/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.ecore

import javax.inject.Inject
import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists
import static extension com.google.common.base.Strings.*
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeType
import de.cau.cs.kieler.klighd.TransformationOption
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference

/**
 * This diagram synthesis implementation demonstrates the usage of KLighD for the purpose of
 * representing content of models by means of graph-like diagrams.
 * 
 * This translation provides the following synthesis options:
 * <ol>
 *  <li>Depicting the selected classes only.</li>
 *  <li>Depicting the selected classes, and directly related ones.</li>
 *  <li>Depicting all classes of the Ecore model, the selected ones are highlighted.</li>
 * <ol>
 */
class EcoreDiagramSynthesis extends AbstractDiagramSynthesis<EModelElementCollection> {
	
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
     * The filter option definition that allows users to customize the constructed class diagrams.
     */
    private static val TransformationOption CLASS_FILTER = TransformationOption::createChoiceOption(CLASS_FILTER_NAME,
       ImmutableList::of(CHOSEN, CHOSEN_AND_RELATED, ALL), CHOSEN_AND_RELATED);

    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Registers the diagram filter option declared above, which allow users to tailor the constructed diagrams.
     */
    override public getTransformationOptions() {
        return ImmutableSet::of(CLASS_FILTER);
    }   


    /**
     * {@inheritDoc}<br>
     * <br>
     * This main method creates the root node that represents the canvas of the diagram.
     * It configures some layout options adds the diagram elements by distinguishing the three option cases.
     * 
     * Note that this translation added also the Classes contained in selected EPackages,
     * see the end of this method.
     */
	override KNode transform(EModelElementCollection choice) {		
		return createNode() => [
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
		classes.filterNull.forEach[ EClassifier clazz |
            rootNode.children += clazz.createNode().putToLookUpWith(clazz) => [
                it.setNodeSize(180, 80);
                it.data += renderingFactory.createKRoundedRectangle() => [
                	it.cornerHeight = 30;
                    it.cornerWidth = 30;
                    it.setLineWidth(2);
                    it.background = "lemon".color;
                    it.children += renderingFactory.createKText().putToLookUpWith(clazz) => [
                        it.text = clazz.name.nullToEmpty;
                        it.setFontSize(20);
                        it.setFontBold(true);
                        it.setSurroundingSpace(20, 0);
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