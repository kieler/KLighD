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
package de.cau.cs.kieler.klighd.krendering.extensions

import com.google.inject.Injector
import com.google.inject.Scope
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.krendering.KColor
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import javax.inject.Inject
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeLabelPlacement
import org.eclipse.elk.core.options.NodeLabelPlacement
import org.eclipse.elk.core.options.PortLabelPlacement
import org.eclipse.elk.graph.properties.IProperty

/**
 * Provides some helpful extension methods for simplifying the composition of KGraph/KRendering-based view models.<br>
 * <br>
 * In order to employ them beyond KLighD diagram syntheses you best declare a field of type
 * {@link KNodeExtensions} in your class and annotate it with {@link Inject Inject}.<br>
 * <br>
 * Make sure to bind the {@link ViewSynthesisShared} annotation in the employed
 * {@link Injector Injector} to a {@link Scope}, e.g. by calling
 * {@code Guice.createInjector(KRenderingExtensionsPlugin.createSingletonScopeBindingModule());} or 
 * {@code Guice.createInjector(KRenderingExtensionsPlugin.createNoScopeBindingModule());}.<br>
 * <br>
 * By means of that {@link Injector Injector} you may get a new instance of your class,
 * or you may inject the above mentioned attribute within instances of your class, e.g. by calling
 * {@code injector.injectMembers(this)} in the constructor of your class.
 * 
 * @author chsch
 * @author ssm
 * 
 * @containsExtensions
 */
@ViewSynthesisShared
class KLabelExtensions {

    extension KRenderingFactory = KRenderingFactory::eINSTANCE

    @Inject
    extension KRenderingExtensions
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KLabelExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KLabel create node: KGraphUtil::createInitializedLabel(labeledElement) getLabel(Iterable<?> o,
            KLabeledGraphElement labeledElement) {
    }
    
    /**
     * A convenient getter preserving the element image relation by a create extension.
     */
    def KLabel getLabel(Object o, KLabeledGraphElement labeledElement) {
        return newArrayList(o).getLabel(labeledElement)
    }
    
    /**
     * Convenience creator of KLabels that are not related to semantic elements.
     * It is just syntactic sugar.  
     */
    def KLabel createLabel(KLabeledGraphElement labeledElement) {
        return KGraphUtil::createInitializedLabel(labeledElement)
    }
    
    /**
     * An alias of {@link #getLabel(Object, KLabeledGraphElement)} allowing to express in business
     *  that the KLabel will be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(Object o, KLabeledGraphElement labeledElement) {
        return newArrayList(o).getLabel(labeledElement)
    }
    
    /**
     * A convenient getter associating the label with to elements
     *  preserving the element image relation by a create extension.
     */
    def KLabel getLabel(Object o1, Object o2, KLabeledGraphElement labeledElement) {
        return newArrayList(o1, o2).getLabel(labeledElement)
    }
    
    /**
     * An alias of {@link #getLabel(Object, Object, KLabeledGraphElement)} allowing to express in business
     *  that the KLabel will be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(Object o1, Object o2, KLabeledGraphElement labeledElement) {
        return newArrayList(o1, o2).getLabel(labeledElement)
    }
    
    
    def KLabel setLabelSize(KLabel label, float width, float height) {
        label.setSize(width, height)
        return label;
    }
    
    def KLabel setLabelPos(KLabel label, float x, float y) {
        label.setPos(x, y)
        return label;
    }
    
    def <T> KLabel addLayoutParam(KLabel label, IProperty<? super T> property, T value) {
        label?.setProperty(property, value)
        return label;
    }


    /* --------------------------------- */
    /*  node label configurators/adders  */
    /* --------------------------------- */
    
    // inside configurators/adder

    /**
     * Configures an inside centrally-aligned node label!
     */
    def KLabel configureInsideCenteredNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.insideCenter
//            CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideCenter
        )
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside centrally-aligned node label!
     */
    def KLabel configureInsideCenteredNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideCenter)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside centrally-aligned node label!
     */
    def KLabel configureInsideCenteredNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideCenter)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideCenteredNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideCenteredNodeLabel(labelText);
    }

    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideCenteredNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideCenteredNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideCenteredNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideCenteredNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an inside bottom centrally-aligned node label!
     */
    def KLabel configureInsideBottomCenteredNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomCenter)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside bottom centrally-aligned node label!
     */
    def KLabel configureInsideBottomCenteredNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomCenter)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside bottom centrally-aligned node label!
     */
    def KLabel configureInsideBottomCenteredNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomCenter)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside bottom centrally-aligned node label!
     */
    def KLabel addInsideBottomCenteredNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideBottomCenteredNodeLabel(labelText);
    }
    
    /**
     * Adds an inside bottom centrally-aligned node label!
     */
    def KLabel addInsideBottomCenteredNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideBottomCenteredNodeLabel(labelText, fontSize);
    }
    
    /**
     * Adds an inside bottom centrally-aligned node label!
     */
    def KLabel addInsideBottomCenteredNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideBottomCenteredNodeLabel(labelText, fontSize, fontName);
    }
    
    /**
     * Configures an inside bottom left-aligned node label!
     */
    def KLabel configureInsideBottomLeftNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomLeft)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside bottom left-aligned node label!
     */
    def KLabel configureInsideBottomLeftNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomLeft)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside bottom left-aligned node label!
     */
    def KLabel configureInsideBottomLeftNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomLeft)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside bottom left-aligned node label!
     */
    def KLabel addInsideBottomLeftNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideBottomLeftNodeLabel(labelText);
    }
    
    /**
     * Adds an inside bottom left-aligned node label!
     */
    def KLabel addInsideBottomLeftNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideBottomLeftNodeLabel(labelText, fontSize);
    }
    
    /**
     * Adds an inside bottom left-aligned node label!
     */
    def KLabel addInsideBottomLeftNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideBottomLeftNodeLabel(labelText, fontSize, fontName);
    }
    
    
    /**
     * Configures an inside bottom right-aligned node label!
     */
    def KLabel configureInsideBottomRightNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomRight)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside bottom right-aligned node label!
     */
    def KLabel configureInsideBottomRightNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomRight)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside bottom right-aligned node label!
     */
    def KLabel configureInsideBottomRightNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideBottomRight)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideBottomRightNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideBottomRightNodeLabel(labelText);
    }
    
    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideBottomRightNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideBottomRightNodeLabel(labelText, fontSize);
    }
    
    /**
     * Adds an inside bottom right-aligned node label!
     */
    def KLabel addInsideBottomRightNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideBottomRightNodeLabel(labelText, fontSize, fontName);
    }
    
    /**
     * Configures an inside top centrally-aligned node label!
     */
    def KLabel configureInsideTopCenteredNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopCenter)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside top centrally-aligned node label!
     */
    def KLabel configureInsideTopCenteredNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopCenter)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside top centrally-aligned node label!
     */
    def KLabel configureInsideTopCenteredNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopCenter)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside top centrally-aligned node label!
     */
    def KLabel addInsideTopCenteredNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideTopCenteredNodeLabel(labelText);
    }

    /**
     * Adds an inside top centrally-aligned node label!
     */
    def KLabel addInsideTopCenteredNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideTopCenteredNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an inside top centrally-aligned node label!
     */
    def KLabel addInsideTopCenteredNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideTopCenteredNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an inside top left-aligned node label!
     */
    def KLabel configureInsideTopLeftNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopLeft)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside top left-aligned node label!
     */
    def KLabel configureInsideTopLeftNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopLeft)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside top left-aligned node label!
     */
    def KLabel configureInsideTopLeftNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopLeft)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside top left-aligned node label!
     */
    def KLabel addInsideTopLeftNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideTopLeftNodeLabel(labelText);
    }

    /**
     * Adds an inside top left-aligned node label!
     */
    def KLabel addInsideTopLeftNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideTopLeftNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an inside top left-aligned node label!
     */
    def KLabel addInsideTopLeftNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideTopLeftNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an inside top right-aligned node label!
     */
    def KLabel configureInsideTopRightNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopRight)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside top right-aligned node label!
     */
    def KLabel configureInsideTopRightNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopRight)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside top right-aligned node label!
     */
    def KLabel configureInsideTopRightNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::insideTopRight)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside top right-aligned node label!
     */
    def KLabel addInsideTopRightNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureInsideTopRightNodeLabel(labelText);
    }

    /**
     * Adds an inside top right-aligned node label!
     */
    def KLabel addInsideTopRightNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureInsideTopRightNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an inside top right-aligned node label!
     */
    def KLabel addInsideTopRightNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureInsideTopRightNodeLabel(labelText, fontSize, fontName);
    }


    // outside configurators/adder

    /**
     * Configures an outside bottom centrally-aligned node label!
     */
    def KLabel configureOutsideBottomCenteredNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomCenter)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an outside bottom centrally-aligned node label!
     */
    def KLabel configureOutsideBottomCenteredNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomCenter)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside bottom centrally-aligned node label!
     */
    def KLabel configureOutsideBottomCenteredNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomCenter)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Adds an outside bottom centrally-aligned node label!
     */
    def KLabel addOutsideBottomCenteredNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideBottomCenteredNodeLabel(labelText);
    }

    /**
     * Adds an outside bottom centrally-aligned node label!
     */
    def KLabel addOutsideBottomCenteredNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideBottomCenteredNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an outside bottom centrally-aligned node label!
     */
    def KLabel addOutsideBottomCenteredNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideBottomCenteredNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an outside bottom left-aligned node label!
     */
    def KLabel configureOutsideBottomLeftNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomLeft)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an outside bottom left-aligned node label!
     */
    def KLabel configureOutsideBottomLeftNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomLeft)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside bottom left-aligned node label!
     */
    def KLabel configureOutsideBottomLeftNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomLeft)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside bottom left-aligned node label!
     */
    def KLabel addOutsideBottomLeftNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideBottomLeftNodeLabel(labelText);
    }

    /**
     * Adds an outside bottom left-aligned node label!
     */
    def KLabel addOutsideBottomLeftNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideBottomLeftNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an outside bottom left-aligned node label!
     */
    def KLabel addOutsideBottomLeftNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideBottomLeftNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an outside bottom right-aligned node label!
     */
    def KLabel configureOutsideBottomRightNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomRight)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an outside bottom right-aligned node label!
     */
    def KLabel configureOutsideBottomRightNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomRight)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside bottom right-aligned node label!
     */
    def KLabel configureOutsideBottomRightNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideBottomRight)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside bottom right-aligned node label!
     */
    def KLabel addOutsideBottomRightNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideBottomRightNodeLabel(labelText);
    }

    /**
     * Adds an outside bottom right-aligned node label!
     */
    def KLabel addOutsideBottomRightNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideBottomRightNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an outside bottom right-aligned node label!
     */
    def KLabel addOutsideBottomRightNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideBottomRightNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an outside top centrally-aligned node label!
     */
    def KLabel configureOutsideTopCenteredNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopCenter)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an outside top centrally-aligned node label!
     */
    def KLabel configureOutsideTopCenteredNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopCenter)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside top centrally-aligned node label!
     */
    def KLabel configureOutsideTopCenteredNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopCenter)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside top centrally-aligned node label!
     */
    def KLabel addOutsideTopCenteredNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideTopCenteredNodeLabel(labelText);
    }

    /**
     * Adds an outside top centrally-aligned node label!
     */
    def KLabel addOutsideTopCenteredNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideTopCenteredNodeLabel(labelText, fontSize);
    }

    /**
     * Adds an outside top centrally-aligned node label!
     */
    def KLabel addOutsideTopCenteredNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideTopCenteredNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an outside top left-aligned node label!
     */
    def KLabel configureOutsideTopLeftNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopLeft)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an outside top left-aligned node label!
     */
    def KLabel configureOutsideTopLeftNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopLeft)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside top left-aligned node label!
     */
    def KLabel configureOutsideTopLeftNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement::outsideTopLeft)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside top left-aligned node label!
     */
    def KLabel addOutsideTopLeftNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideTopLeftNodeLabel(labelText);
    }    

    /**
     * Adds an outside top left-aligned node label!
     */
    def KLabel addOutsideTopLeftNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideTopLeftNodeLabel(labelText, fontSize);
    }    

    /**
     * Adds an outside top left-aligned node label!
     */
    def KLabel addOutsideTopLeftNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideTopLeftNodeLabel(labelText, fontSize, fontName);
    }    

    /**
     * Configures an outside top right-aligned node label!
     */
    def KLabel configureOutsideTopRightNodeLabel(KLabel label, String labelText) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement.outsideTopRight)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an outside top right-aligned node label!
     */
    def KLabel configureOutsideTopRightNodeLabel(KLabel label, String labelText, int fontSize) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement.outsideTopRight)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an outside top right-aligned node label!
     */
    def KLabel configureOutsideTopRightNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.setLayoutOption(CoreOptions::NODE_LABELS_PLACEMENT, NodeLabelPlacement.outsideTopRight)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside top right-aligned node label!
     */
    def KLabel addOutsideTopRightNodeLabel(KNode node, String labelText) {
        return node.createLabel().configureOutsideTopRightNodeLabel(labelText);
    }    

    /**
     * Adds an outside top right-aligned node label!
     */
    def KLabel addOutsideTopRightNodeLabel(KNode node, String labelText, int fontSize) {
        return node.createLabel().configureOutsideTopRightNodeLabel(labelText, fontSize);
    }    

    /**
     * Adds an outside top right-aligned node label!
     */
    def KLabel addOutsideTopRightNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideTopRightNodeLabel(labelText, fontSize, fontName);
    }    


    /* --------------------------------- */
    /*  port label configurators/adders  */
    /* --------------------------------- */

    /**
     * Configures an inside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureInsidePortLabel(KLabel label, String labelText) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.INSIDE)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures an inside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureInsidePortLabel(KLabel label, String labelText, int fontSize) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.INSIDE)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures an inside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureInsidePortLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.INSIDE)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an inside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addInsidePortLabel(KPort port, String labelText) {
        return port.createLabel().configureInsidePortLabel(labelText);
    }

    /**
     * Adds an inside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addInsidePortLabel(KPort port, String labelText, int fontSize) {
        return port.createLabel().configureInsidePortLabel(labelText, fontSize);
    }

    /**
     * Adds an inside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addInsidePortLabel(KPort port, String labelText, int fontSize, String fontName) {
        return port.createLabel().configureInsidePortLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures a outside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureOutsidePortLabel(KLabel label, String labelText) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.OUTSIDE)
        return label.basicConfigureLabel(labelText);
    }

    /**
     * Configures a outside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureOutsidePortLabel(KLabel label, String labelText, int fontSize) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.OUTSIDE)
        return label.basicConfigureLabel(labelText, fontSize);
    }

    /**
     * Configures a outside port label!<br>
     * Note that <code>label</code> must be already contained in a {@link KPort} that in turn is contained
     * in a {@link KNode} in order to let the configuration be performed properly.
     */
    def KLabel configureOutsidePortLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.parent?.parent.setLayoutOption(CoreOptions::PORT_LABELS_PLACEMENT, PortLabelPlacement.OUTSIDE)
        return label.basicConfigureLabel(labelText, fontSize, fontName);
    }

    /**
     * Adds an outside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addOutsidePortLabel(KPort port, String labelText) {
        return port.createLabel().configureOutsidePortLabel(labelText);
    }

    /**
     * Adds an outside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addOutsidePortLabel(KPort port, String labelText, int fontSize) {
        return port.createLabel().configureOutsidePortLabel(labelText, fontSize);
    }

    /**
     * Adds an outside label to {@link KPort} <code>port</code>!<br>
     * Note that <code>port</code> must be already contained in a {@link KNode} in order to let the
     * configuration be performed properly.
     */
    def KLabel addOutsidePortLabel(KPort port, String labelText, int fontSize, String fontName) {
        return port.createLabel().configureOutsidePortLabel(labelText, fontSize, fontName);
    }


    /* --------------------------------- */
    /*  edge label configurators/adders  */
    /* --------------------------------- */
    
    /**
     * Configures a central (main) edge label, e.g. a state transition guard/effect label!
     */
    def KLabel configureCenterEdgeLabel(KLabel label, String labelText) {
        return label.basicConfigureLabel(labelText).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::CENTER
        );
    }

    /**
     * Configures a central (main) edge label, e.g. a state transition guard/effect label!
     */
    def KLabel configureCenterEdgeLabel(KLabel label, String labelText, int fontSize) {
        return label.basicConfigureLabel(labelText, fontSize).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::CENTER
        );
    }

    /**
     * Configures a central (main) edge label, e.g. a state transition guard/effect label!
     */
    def KLabel configureCenterEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label.basicConfigureLabel(labelText, fontSize, fontName).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::CENTER
        );
    }

    /**
     * Adds a central (main) label to {@link KEdge} <code>edge</code>.
     */
    def KLabel addCenterEdgeLabel(KEdge edge, String labelText) {
        return edge.createLabel().configureCenterEdgeLabel(labelText);
    }

    /**
     * Adds a central (main) label to {@link KEdge} <code>edge</code>.
     */
    def KLabel addCenterEdgeLabel(KEdge edge, String labelText, int fontSize) {
        return edge.createLabel().configureCenterEdgeLabel(labelText, fontSize);
    }

    /**
     * Adds a central (main) label to {@link KEdge} <code>edge</code>.
     */
    def KLabel addCenterEdgeLabel(KEdge edge, String labelText, int fontSize, String fontName) {
        return edge.createLabel().configureCenterEdgeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures a head edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureHeadEdgeLabel(KLabel label, String labelText) {
        return label.basicConfigureLabel(labelText).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::HEAD
        );
    }

    /**
     * Configures a head edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureHeadEdgeLabel(KLabel label, String labelText, int fontSize) {
        return label.basicConfigureLabel(labelText, fontSize).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::HEAD
        );
    }

    /**
     * Configures a head edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureHeadEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label.basicConfigureLabel(labelText, fontSize, fontName).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::HEAD
        );
    }

    /**
     * Adds a head label (target side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addHeadEdgeLabel(KEdge edge, String labelText) {
        return edge.createLabel().configureHeadEdgeLabel(labelText);
    }

    /**
     * Adds a head label (target side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addHeadEdgeLabel(KEdge edge, String labelText, int fontSize) {
        return edge.createLabel().configureHeadEdgeLabel(labelText, fontSize);
    }

    /**
     * Adds a head label (target side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addHeadEdgeLabel(KEdge edge, String labelText, int fontSize, String fontName) {
        return edge.createLabel().configureHeadEdgeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures a tail edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureTailEdgeLabel(KLabel label, String labelText) {
        return label.basicConfigureLabel(labelText).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::TAIL
        );
    }

    /**
     * Configures a tail edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureTailEdgeLabel(KLabel label, String labelText, int fontSize) {
        return label.basicConfigureLabel(labelText, fontSize).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::TAIL
        );
    }

    /**
     * Configures a tail edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureTailEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label.basicConfigureLabel(labelText, fontSize, fontName).addLayoutParam(
            CoreOptions::EDGE_LABELS_PLACEMENT, EdgeLabelPlacement::TAIL
        );
    }

    /**
     * Adds a tail label (source side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addTailEdgeLabel(KEdge edge, String labelText) {
        return edge.createLabel().configureTailEdgeLabel(labelText);
    }

    /**
     * Adds a tail label (source side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addTailEdgeLabel(KEdge edge, String labelText, int fontSize) {
        return edge.createLabel().configureTailEdgeLabel(labelText, fontSize);
    }

    /**
     * Adds a tail label (source side) to {@link KEdge} <code>edge</code>.
     */
    def KLabel addTailEdgeLabel(KEdge edge, String labelText, int fontSize, String fontName) {
        return edge.createLabel().configureTailEdgeLabel(labelText, fontSize, fontName);
    }


    /* ----------------- */
    /*   other helpers   */
    /* ----------------- */

    /**
     * The least common denominator of all the 'configure...Label' methods.<br>
     * Is private as it's to be used internally only!
     */
    def private KLabel basicConfigureLabel(KLabel label, String labelText) {
        label.text = labelText;
        label.data += createKText();
        return label;
    }

    /**
     * The least common denominator of all the 'configure...Label' methods.<br>
     * Is private as it's to be used internally only!
     */
    def private KLabel basicConfigureLabel(KLabel label, String labelText, int fontSize) {
        label.text = labelText;
        label.data += createKText().setFontSize(fontSize);
        return label;
    }
    
    /**
     * The least common denominator of all the 'configure...Label' methods.<br>
     * Is private as it's to be used internally only!
     */
    def private KLabel basicConfigureLabel(KLabel label, String labelText, int fontSize, String fontName) {
        label.text = labelText;
        label.data += createKText().setFontSize(fontSize).setFontName(fontName);
        return label;
    }

    /**
     * Internal helper returning the parent KGraphElement if available, <code>null</code> otherwise.
     */
    def private KGraphElement getParent(KGraphElement kgraphElement) {
        val container = kgraphElement.eContainer;

        return switch(container) {
            KGraphElement: container
        }
    }  

    /**
     * Internal helper for setting layout options without the need to check for KNode, KEdge, ...
     */
    def private <S, T extends KGraphElement> T setLayoutOption(T kgraphElement, IProperty<S> option, S value) {
        kgraphElement?.setProperty(option, value)
        return kgraphElement
    }

    
    /**
     * Reveals the first KText element of a label KRendering, which is assumed to be the label text configuration.
     * This is useful for additionally linking it with the business element represented by the label.
     * 
     * Note: KLabelNodes of the Piccolo2D binding are configured to ignore the KText element while selecting them.
     * Thus, only the KLabel needs to be linked to the source element. 
     */
    def KText getFirstText(KLabel label) {
        return label?.getData(typeof(KText))?:(label?.getData(typeof(KRendering))?.eAllContents?.filter(typeof(KText))?.head);
    }

    /**
     * Shortcut for setting the label's foreground color directly.
     */
    def KLabel foreground(KLabel label, KColor color) {
        label => [it.KRendering.foreground = color]
    } 

    /**
     * Shortcut for setting the label's background color directly.
     */
    def KLabel background(KLabel label, KColor color) {
        label => [it.KRendering.background = color]
    } 
    
}
