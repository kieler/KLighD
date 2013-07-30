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

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import com.google.common.collect.Lists
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.kiml.options.Direction
import de.cau.cs.kieler.kiml.options.EdgeType
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.TransformationOption
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis

import java.util.List
import java.util.Collection
import javax.inject.Inject

import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference

import static extension com.google.common.base.Strings.*
import org.eclipse.emf.ecore.EAttribute

/**
 * This diagram synthesis implementation demonstrates the usage of KLighD for the purpose of
 * representing content of models by means of graph-like diagrams.
 * 
 * This translation provides the following synthesis options:
 * <ol>
 *  <li>Depicting the selected classes only.</li>
 *  <li>Depicting the selected classes, and directly related ones.</li>
 *  <li>Depicting all classes of the Ecore model, the selected ones are highlighted.</li>
 *  <li>Depicting the attributes of the classes</li>
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
    extension KContainerRenderingExtensions
    
    @Inject
    extension KPolylineExtensions
    
	@Inject
	extension KColorExtensions
	
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
     * Option to activate/deactivate the attribute lists.
     */
    private static val TransformationOption ATTRIBUTES = TransformationOption::createCheckOption("Class Attributes", false);
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Registers the diagram filter option declared above, which allow users to tailor the constructed diagrams.
     */
    override public getTransformationOptions() {
        return ImmutableSet::of(CLASS_FILTER, ATTRIBUTES);
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Registers reasonable layout options that are recommended to users to tailor the constructed diagram layouts.
     */
    override public getRecommendedLayoutOptions() {
        return ImmutableMap::<IProperty<?>, Collection<?>>of(
            LayoutOptions::DIRECTION, Direction::values().drop(1).sortBy[ it.name ],
            LayoutOptions::SPACING, newArrayList(0, 255)
        );
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
                
                // create class and relation figures for each of the elements in the collection
                depictedClasses.createElementFigures(it);
                
	        } else if (CLASS_FILTER.optionValue == CHOSEN_AND_RELATED) {
                
                // The chosen classes ...
                val chosenClasses = choice.filter(typeof(EClass)).toList => [
                   // ... are inspected, and ...
                   val ePackage = choice.filter(typeof(EClass))?.head?.eContainer as EPackage;
	               it.forEach[
	                   // ... their referenced classes ...
                       depictedClasses.addAll(it.EStructuralFeatures.filter(typeof(EReference)).map[it.EType])
                       // ... the classes referenced by their attributes (those contained in the current package)
                       depictedClasses.addAll(it.EStructuralFeatures.filter(typeof(EAttribute)).map[it.EType]
                                                .filter[it.eContainer == ePackage]
                       )
	               ];
                   it.forEach[
                       // ... as well as there super classes are put into the list of depicted classes, too.
                       depictedClasses.addAll(it.ESuperTypes)
                   ];

                   depictedClasses.addAll(
                       (ePackage?.EClassifiers?:emptyList).filter(typeof(EClass)).filter[
                           // look for candidates whose super types are in the choice
                           val l = Lists::newArrayList(it.ESuperTypes);
                           // look for candidates whose reference types are in the choice
                           l.retainAll(choice);
                           val l2 = Lists::newArrayList(it.EReferences).map[it.EType];
                           l2.retainAll(choice);
                           !l.empty || !l2.empty
                       ]
                   );
                ];

                depictedClasses.createElementFigures(it);

                // each of the above given ones is highlighted in a special fashion
                chosenClasses.forEach[
                    it.node.KRendering.setBackgroundGradient("white".color,
                        KlighdConstants::ALPHA_FULL_OPAQUE, "red".color, 150, 0
                    );
                ];
                
	        } else { // (CLASS_FILTER.optionValue == ALL)
	            // depict all classes within the package of the first class in choice

                val chosenClasse = Lists::newArrayList(depictedClasses);
                
                (depictedClasses?.head?.EPackage?.EClassifiers as List<EClassifier>)?:emptyList => [classes |
                    // ... are depicted (it denotes the root node introduced above in this case)
                    classes.createElementFigures(it)
                    depictedClasses += classes;
                ];

                // each of the above given ones is highlighted in a special fashion
                chosenClasse.forEach[
                    it.node.KRendering.setBackgroundGradient("white".color,
                        KlighdConstants::ALPHA_FULL_OPAQUE, "red".color, 150, 0
                    );
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
                it.addRectangle => [
                    it.gridPlacement = 1;
                    it.lineWidth = 2;
                    it.setBackgroundGradient("white".color, KlighdConstants::ALPHA_FULL_OPAQUE, "lemon".color, 255, 0)
                    it.shadow = "black".color;
                    it.addRectangle => [
                        it.lineWidth = 2;
                        val typeText = if (EcorePackage::eINSTANCE.getEEnum.isInstance(clazz))
                            it.addText("<<Enum>>") => [
                                it.fontSize = 13;
                                it.fontItalic = true;
                                it.verticalAlignment = V_BOTTOM; 
                                it.setGridPlacementData().from(LEFT, 20, 0, TOP, 20, 0)
                                                         .to(RIGHT, 20, 0, BOTTOM, 40, 0);
                            ];
                        
                        it.addText(clazz.name.nullToEmpty).putToLookUpWith(clazz) => [
                            it.fontSize = 15;
                            it.fontBold = true;
                            it.setSurroundingSpace(20, 0);
                            if (typeText != null) {
                                it.setGridPlacementData().from(LEFT, 20, 0, TOP, 40, 0)
                                                         .to(RIGHT, 20, 0, BOTTOM, 20, 0);
                                
                            }
                        ];
                        
                    ];
                    if (!EcorePackage::eINSTANCE.getEClass.isInstance(clazz)
                        || !ATTRIBUTES.optionBooleanValue) {
                        return;
                    }
                    it.addRectangle => [ rect |
                        rect.invisible = true;
                        rect.setSurroundingSpaceGrid(5, 0)
                        rect.gridPlacement = 1;
                        (clazz as EClass).EAttributes.forEach[
                            rect.addText(it.name + " : " + it.EAttributeType.name) => [
                                it.horizontalAlignment = H_LEFT
                                it.setSurroundingSpaceGrid(3, 0);
                            ]
                        ];
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
	        it.addPolyline() => [
	            it.lineWidth = 2;
	            it.foreground = "gray25".color
	            it.addArrowDecorator();
	            if (ref.containment) {
    	            it.addPolygon() => [
    	                it.points += createKPosition(LEFT, 0, 0, TOP, 0, 0.5f);
                        it.points += createKPosition(LEFT, 0, 0.5f, TOP, 0, 0);
                        it.points += createKPosition(RIGHT, 0, 0, TOP, 0, 0.5f);
                        it.points += createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0);
                        it.setDecoratorPlacementData(24, 12, 12, 0, true);
                        it.foreground = "gray25".color
                        it.background = "gray25".color;
    	            ];
	            }
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
	        it.data addPolyline() => [
                it.lineWidth = 2;
                it.foreground = "gray25".color
                it.addInheritanceTriangleArrowDecorator();
	        ];		    
		];
	}
}