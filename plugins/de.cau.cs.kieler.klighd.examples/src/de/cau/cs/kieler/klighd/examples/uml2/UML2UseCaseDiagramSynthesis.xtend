package de.cau.cs.kieler.klighd.examples.uml2

import org.eclipse.uml2.uml.Model
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.klighd.TransformationContext
import javax.inject.Inject
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import org.eclipse.uml2.uml.Actor
import org.eclipse.uml2.uml.UseCase
import org.eclipse.uml2.uml.Association
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions

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
    
    override transform(Model model, TransformationContext<Model,KNode> transformationContext) {
        use(transformationContext);
        // create the root node representing the diagram canvas ...
        return model.createNode() => [
            it.addLayoutParam(LayoutOptions::SPACING, 40f);
            // ... and add the diagram elements
            it.children += model.packagedElements.map[
                switch(it) {
                    Actor: it.createActorNode()
                    UseCase: it.createUseCaseNode()
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
            it.addOutsideCenteralBottomNodeLabel(actor.name,
                KlighdConstants::DEFAULT_FONT_SIZE, KlighdConstants::DEFAULT_FONT_NAME
            ).putToLookUpWith(actor);
            it.addRectangle() => [
                it.invisible = true;
                it.addEllipse().setAreaPlacementData
                    .from(LEFT, -17, 0.5f, TOP, 0, 0).to(RIGHT, -17, 0.5f, TOP, 34, 0);
                it.children += createActorFigureBody();
            ];
        ];
    }
    
    def KNode createUseCaseNode(UseCase useCase) {
        return useCase.createNode().putToLookUpWith(useCase) => [
            it.addEllipse() => [
                it.foreground = "gray".color;
                it.addText(useCase.name).putToLookUpWith(useCase) => [
                    it.background = "white".color
                    it.setSurroundingIndention(10, 0);
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