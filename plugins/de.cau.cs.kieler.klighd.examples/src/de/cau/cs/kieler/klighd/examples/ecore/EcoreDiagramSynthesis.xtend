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
package de.cau.cs.kieler.klighd.examples.ecore

import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists
import com.google.common.collect.Sets
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData
import java.util.List
import java.util.Set
import javax.inject.Inject
import org.eclipse.elk.core.klayoutdata.KLayoutData
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.EdgeType
import org.eclipse.elk.graph.KNode
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcorePackage

import static de.cau.cs.kieler.klighd.KlighdConstants.*

import static extension com.google.common.base.Strings.*

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
    
	private static val CHOSEN = "Chosen classes";
    private static val CHOSEN_AND_RELATED = "Chosen (highlighted) && related classes";
    private static val ALL = "All classes, selection highlighted";
    
    private static val String CLASS_FILTER_NAME = "Class filter";
    
    private static val Colors LINE_COLOR = Colors.GRAY_25

    /**
     * The filter option definition that allows users to customize the constructed class diagrams.
     */
    private static val SynthesisOption CLASS_FILTER = SynthesisOption::createChoiceOption(CLASS_FILTER_NAME,
       ImmutableList::of(CHOSEN, CHOSEN_AND_RELATED, ALL), CHOSEN_AND_RELATED);

    /**
     * Option to activate/deactivate the attribute lists.
     */
    private static val SynthesisOption RELATED_CLASSES_DEPTH = SynthesisOption::createRangeOption("Reference depth", 1, 3, 1, 1);
    
    /**
     * Option choose the reference depth while determining the classes related to the selected ones.
     */
    private static val SynthesisOption ATTRIBUTES = SynthesisOption::createCheckOption("Attributes/Literals", false);
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Registers the diagram filter option declared above, which allow users to tailor the constructed diagrams.
     */
    override public getDisplayedSynthesisOptions() {
        return ImmutableList::of(CLASS_FILTER, RELATED_CLASSES_DEPTH, ATTRIBUTES);
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * Registers reasonable layout options that are recommended to users to tailor the constructed diagram layouts.
     */
    override public getDisplayedLayoutOptions() {
        return ImmutableList::of(
            specifyLayoutOption(CoreOptions::DIRECTION, Direction::values().drop(1).sortBy[ it.name ]),
            specifyLayoutOption(CoreOptions::SPACING_NODE, newArrayList(0, 255))
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
            it.addLayoutParam(CoreOptions::ALGORITHM, "de.cau.cs.kieler.kiml.ogdf.planarization");
            it.addLayoutParam(CoreOptions::SPACING_NODE, 75f);
            it.addLayoutParam(CoreOptions::DIRECTION, Direction::UP);
		
            // The chosen (depicted) classifiers. This list will be supplemented with related classifiers,
            //  depending on the value of CLASS_FILTER.
            val depictedClasses = choice.filter(typeof(EClassifier)).toList;
	    
	        if (CLASS_FILTER.objectValue == CHOSEN) {
                
                // create class and relation figures for each of the elements in the collection
                depictedClasses.createElementFigures(it);
                
	        } else if (CLASS_FILTER.objectValue == CHOSEN_AND_RELATED) {
	            
                val ePackage = choice.filter(typeof(EClass))?.head?.eContainer as EPackage;
                
                val classesCopy = Sets::newHashSet(depictedClasses);
                
                (1..RELATED_CLASSES_DEPTH.intValue).forEach[
                    classesCopy.filter(typeof(EClass)) => [
                        // ... are inspected, and ...
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
                            ((ePackage?.EClassifiers as List<EClassifier>)?:emptyList).filter(typeof(EClass)).filter[
                                // look for candidates whose super types are in the choice
                                val sts = Lists::newArrayList(it.ESuperTypes as Iterable<EClass>);
                                // look for candidates whose reference types are in the choice
                                sts.retainAll(classesCopy);
                                val rts = Lists::newArrayList(it.EReferences as Iterable<EReference>).map[it.EType];
                                rts.retainAll(classesCopy);
                                !sts.empty || !rts.empty
                            ]
                        );
                    ];
                    classesCopy.addAll(depictedClasses);
                ];
                
                depictedClasses.createElementFigures(it);

                // each of the above given ones is highlighted in a special fashion
                choice.filter(typeof(EClass)).forEach[
                    it.node.KRendering.setBackgroundGradient(Colors.WHITE, ALPHA_FULL_OPAQUE, Colors.RED, 150, 90);
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
                    it.node.KRendering.setBackgroundGradient(Colors.WHITE, ALPHA_FULL_OPAQUE, Colors.RED, 150, 90);
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
	    classes.createInheritanceConnections();
	    classes.createAssociationConnections();
	}
	
	def createClassifierFigures(Iterable<EClassifier> classes, KNode rootNode) {
		classes.filterNull.forEach[ EClassifier clazz |
            rootNode.children += clazz.createNode().associateWith(clazz) => [
                // add semantic information
                it.getData(typeof(KLayoutData)).setProperty(KlighdProperties.SEMANTIC_DATA, 
                        KlighdSemanticDiagramData.of(KlighdConstants.SEMANTIC_DATA_CLASS, "classifier"))
                it.addRectangle => [
                    it.lineWidth = 1.5f;
                    it.foreground = LINE_COLOR
                    it.setBackgroundGradient(Colors.LEMON_CHIFFON, 100, Colors.LIGHT_GOLDENROD_1, 255, 90)
                    it.shadow = Colors.BLACK;
                    it.addRectangle => [
                        // this rectangle represents the grid cell ... 
                        it.invisible = true;
                        it.addRectangle => [
                            // ... and this one a "free floating" centrally aligned rendering container
                            //  hosting the actual title image and/or text(s)  
                            it.invisible = true;
                            it.setPointPlacementData(LEFT, 0, 0.5f, TOP, 0, 0.5f, H_CENTRAL, V_CENTRAL, 0, 0, 0, 0);
                            
                            if (EcorePackage::eINSTANCE.getEEnum.isInstance(clazz)) {
                                it.setGridPlacement(1)
                                it.addText("<<Enum>>") => [
                                    it.fontSize = 13;
                                    it.fontItalic = true;
                                    it.verticalAlignment = V_CENTRAL;
                                    it.setGridPlacementData.from(LEFT, 20, 0, TOP, 3, 0).to(RIGHT, 20, 0, BOTTOM, 0, 0f);
                                ];
                                it.addText(clazz.name.nullToEmpty).associateWith(clazz) => [
                                    it.fontSize = 15;
                                    it.fontBold = true;
                                    // it.cursorSelectable = true;
                                    it.setGridPlacementData.from(LEFT, 20, 0, TOP, 2, 0f).to(RIGHT, 20, 0, BOTTOM, 7, 0);
                                ];
                            } else {
                                it.addImage("de.cau.cs.kieler.klighd.examples", 
                                    if (clazz instanceof EClass && (clazz as EClass).interface) "icons/Interface.png" else "icons/Class.png")
                                    .setPointPlacementData(LEFT, 20, 0, TOP, 0, 0.5f, H_CENTRAL, V_CENTRAL, 10, 10, 20, 20)
                                    .addEllipticalClip; //.setAreaPlacementData.from(LEFT, 3, 0, TOP, 3, 0).to(RIGHT, 3, 0, BOTTOM, 3, 0);
                                it.addText(clazz.name.nullToEmpty).associateWith(clazz) => [
                                    // add semantic data to a rendering
                                    it.setProperty(KlighdProperties.SEMANTIC_DATA, 
                                        KlighdSemanticDiagramData.of(KlighdConstants.SEMANTIC_DATA_CLASS, "classifierText"))
                                    it.fontSize = 15;
                                    it.fontBold = true;
                                    it.fontItalic = clazz instanceof EClass && (clazz as EClass).isAbstract
                                    // it.cursorSelectable = true;
                                    it.setPointPlacementData(LEFT, 40, 0, TOP, 0, 0.5f, H_LEFT, V_CENTRAL, 10, 10, 0, 0);
                                ];
                            };
                        ];
                    ];
                    if (!ATTRIBUTES.booleanValue) {
                        return;
                    }
                    it.setGridPlacement(1).from(LEFT, 2, 0, TOP, 2, 0).to(RIGHT, 2, 0, BOTTOM, 2, 0);
                    if (EcorePackage::eINSTANCE.getEClass.isInstance(clazz) && !(clazz as EClass).EAttributes.empty) {
                        it.addHorizontalLine(1, 1.5f).addStyleRef = it; 
                        it.addRectangle => [
                            it.invisible = true;
                            it.setSurroundingSpaceGrid(5, 0)
                            it.setGridPlacement(1).from(LEFT, 0, 0, TOP, -2, 0);
                            
                            (clazz as EClass).EAttributes.forEach[ attr |
                                it.addRectangle => [
                                    it.invisible = true;
                                    // it.addImage("org.eclipse.emf.ecoretools.diagram", "icons/EAttribute.gif")
                                    // .setGridPlacementData(16, 16);
                                    it.addAttributeIcon()
                                        .setPointPlacementData(LEFT, 10, 0, TOP, 1.5f, 0.5f, H_CENTRAL, V_CENTRAL, 0, 0, 15f, 7.5f);
                                    val typeName = if (attr.EAttributeType instanceof EEnum)
                                            attr.EAttributeType.name else attr.EAttributeType.instanceTypeName
                                    it.addText(attr.name + " : " + typeName.replaceFirst("java.lang.","")) => [
                                        it.fontSize = 13;
                                        it.horizontalAlignment = H_LEFT
                                        it.verticalAlignment = V_CENTRAL
                                        // it.cursorSelectable = true;
                                        it.setPointPlacementData(LEFT, 25, 0, TOP, 0, 0.5f, H_LEFT, V_CENTRAL, 10, 3, 0, 0);
                                    ];
                                ];
                            ];
                        ];
                    }
                    if (EcorePackage::eINSTANCE.getEEnum.isInstance(clazz)) {
                        it.addHorizontalLine(1, 1.5f);
                        it.addRectangle => [ rect |
                            rect.invisible = true;
                            rect.foreground = Colors.RED;
                            rect.setSurroundingSpaceGrid(5, 0)
                            rect.setGridPlacement(1).to(RIGHT, 0, 0, BOTTOM, 0, 0);
                            (clazz as EEnum).ELiterals.forEach[
                                rect.addText(it.name /* + " (" + it.literal + ")" */) => [
                                    it.horizontalAlignment = H_CENTRAL
                                    it.verticalAlignment = V_CENTRAL
                                    // it.cursorSelectable = true;
                                    it.setSurroundingSpaceGrid(3, 0);
                                ];
                            ];
                        ];
                    }
                ];
            ];
		];
	}

    private Set<EReference> visitedRefs = newHashSet();
	
	def createAssociationConnections(Iterable<EClassifier> classes) {
		val list = classes.toList;
		list.filter(typeof(EClass)).forEach[
			it.EStructuralFeatures.filter(typeof(EReference))
			    .filter[list.contains(it.EType)]
			    .filter[
			        val opposite = it.EOpposite;
			        it.isContainment || opposite == null || !opposite.isContainment && !visitedRefs.contains(opposite)
			    ].forEach[it.createAssociationConnection];
		];
	}
	
	def createAssociationConnection(EReference ref) {
	    visitedRefs += ref;
	    ref.createEdge() => [
            it.addLayoutParam(CoreOptions::EDGE_TYPE, EdgeType::ASSOCIATION);
    		it.source = ref.eContainer.node;
	       	it.target = ref.EType.node;
	       	// add semantic data
	       	it.getData(typeof(KLayoutData)).setProperty(KlighdProperties.SEMANTIC_DATA, 
                        KlighdSemanticDiagramData.of(KlighdConstants.SEMANTIC_DATA_CLASS, "association"))
	        it.addPolyline() => [
	            it.lineWidth = 2;
	            it.foreground = LINE_COLOR;
	            if (ref.EOpposite == null) {
    	            it.addHeadArrowDecorator();
	            }
	            if (ref.containment) {
    	            it.addPolygon() => [
    	                it.points += createKPosition(LEFT, 0, 0, TOP, 0, 0.5f);
                        it.points += createKPosition(LEFT, 0, 0.5f, TOP, 0, 0);
                        it.points += createKPosition(RIGHT, 0, 0, TOP, 0, 0.5f);
                        it.points += createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0);
                        it.setDecoratorPlacementData(24, 12, 12, 0, true);
                        it.foreground = LINE_COLOR;
                        it.background = LINE_COLOR;
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
            it.addLayoutParam(CoreOptions::EDGE_TYPE, EdgeType::GENERALIZATION);
            // add semantic data
            it.getData(typeof(KLayoutData)).setProperty(KlighdProperties.SEMANTIC_DATA, 
                        KlighdSemanticDiagramData.of(KlighdConstants.SEMANTIC_DATA_CLASS, "inheritence"))
    	    it.source = child.node;
	        it.target = parent.node;
	        it.data addPolyline() => [
                it.lineWidth = 2;
                it.foreground = LINE_COLOR;
                it.addInheritanceTriangleArrowDecorator();
	        ];		    
		];
	}
	
	def addAttributeIcon(KContainerRendering parent) {
	    return parent.addRectangle() => [
	        it.lineWidth = 1.75f;
	        it.setForegroundGradient(Colors.GOLDENROD_4, 255, Colors.DIM_GRAY, 255, 90);
	        it.background = Colors.LEMON_CHIFFON;
	    ];
	}
}