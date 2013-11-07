package de.cau.cs.kieler.klighd.examples.uml2

import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy
import de.cau.cs.kieler.klay.layered.properties.Properties
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import javax.inject.Inject
import org.eclipse.uml2.uml.Actor
import org.eclipse.uml2.uml.Association
import org.eclipse.uml2.uml.Model
import org.eclipse.uml2.uml.UseCase

/**
 * This exemplary diagram synthesis implementation generates KGraph/KRendering model
 * based on given UML2 models containing actors, use cases and related associations.
 * 
 * @author chsch
 */
class UML2UseCaseDiagramSynthesis extends AbstractDiagramSynthesis<Model> {
    
    @Inject
    extension KNodeExtensions
    
    @Inject
    extension KEdgeExtensions
    
    @Inject
    extension KLabelExtensions
    
    @Inject
    extension KRenderingExtensions
    
    @Inject
    extension KContainerRenderingExtensions
    
    @Inject
    extension KColorExtensions
    
    override transform(Model model) {
        // create the root node representing the diagram canvas ...
        return model.createNode() => [
            it.addLayoutParam(LayoutOptions::SPACING, 40f);
            it.addLayoutParam(Properties::NODE_PLACER, NodePlacementStrategy::LINEAR_SEGMENTS);
            // ... and add the diagram elements
            it.children += model.packagedElements.map[
                switch(it) {
                    Actor: it.createActorNode()
                    UseCase: it.createUseCaseNode()
                    default: null
                }
            ].filterNull;
            // create representations of the associations (edges)
            //  they attached to the diagram implicitly by setting their source node  
            model.packagedElements.filter(typeof(Association)).forEach[
                it.createAssociationEdge();
            ];
        ];
    }
    
    def KNode createActorNode(Actor actor) {
        return actor.createNode().putToLookUpWith(actor) => [
            it.setNodeSize(60, 100);
            it.addOutsideCentralBottomNodeLabel(actor.name,
                KlighdConstants::DEFAULT_FONT_SIZE, KlighdConstants::DEFAULT_FONT_NAME
            ).putToLookUpWith(actor);
            it.addRectangle() => [
                it.invisible = true;
                it.addChild(createActorFigureBody());
                it.addEllipse().setPointPlacementData(
                    createKPosition(LEFT, 0, 0.5f, TOP, 0, 0),
                    H_CENTRAL, V_TOP, 0, 0, 35, 35
                ).background = "white".color;
            ];
        ];
    }
    
    def KNode createUseCaseNode(UseCase useCase) {
        return useCase.createNode().putToLookUpWith(useCase) => [
            it.addEllipse() => [
                it.foreground = "darkGray".color;
                it.addText(useCase.name).putToLookUpWith(useCase) => [
                    it.setSurroundingSpace(10, 0.1f);
                    it.background = "white".color
                ];
            ];
        ];
    }
    
    def KEdge createAssociationEdge(Association asso) {
        return asso.createEdge().putToLookUpWith(asso) => [
            it.source = asso.ownedEnds.head?.type.node;
            it.target = asso.ownedEnds.last?.type.node;
            it.addPolyline();
        ];
    }
    
    def KPolyline createActorFigureBody() {
        RENDERING_FACTORY.createKPolyline() => [
            it.points += createKPosition(LEFT,  -2, 0.5f, TOP, 34, 0);
            it.points += createKPosition(LEFT,  -2, 0.5f, TOP, -2, 0.5f);
            it.points += createKPosition(LEFT,   0, 0f, TOP, -2, 0.5f);
            it.points += createKPosition(LEFT,   0, 0f, TOP, 2, 0.5f);
            it.points += createKPosition(LEFT,  -2, 0.5f, TOP, 2, 0.5f);
            it.points += createKPosition(LEFT,  -2, 0.5f, TOP, -1, 0.7f);
            it.points += createKPosition(LEFT,   0, 0, TOP, -3, 1);
            it.points += createKPosition(LEFT,   3, 0, TOP,  0, 1);
            it.points += createKPosition(LEFT,   0, 0.5f, TOP, 2, 0.7f);
            it.points += createKPosition(RIGHT,  3, 0, TOP, 0, 1);
            it.points += createKPosition(RIGHT,  0, 0, TOP, -3, 1);
            it.points += createKPosition(RIGHT, -2, 0.5f, TOP, -1, 0.7f);
            it.points += createKPosition(RIGHT, -2, 0.5f, TOP, 2, 0.5f);
            it.points += createKPosition(RIGHT,  0, 0, TOP, 2, 0.5f);
            it.points += createKPosition(RIGHT,  0, 0, TOP, -2, 0.5f);
            it.points += createKPosition(RIGHT, -2, 0.5f, TOP, -2, 0.5f);
            it.points += createKPosition(RIGHT, -2, 0.5f, TOP, 34, 0);
        ];
    }
}