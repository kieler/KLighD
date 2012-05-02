package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger
import org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect
import de.cau.cs.kieler.klighd.examples.EModelElementCollection
import java.util.Collection

class EcoreDiagramSynthesisCombination extends AbstractCombination {
	
	def public void execute(SelectionTrigger$SelectionState triggerState) {
		
		val selection = triggerState.selectedObjects;
		
		if (!selection.nullOrEmpty &&
			    selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
            this.schedule(
            	new KlighdUpdateDiagramEffect(
            		EModelElementCollection::of(
            		triggerState.selectedObjects.map[
            			(it as EcoreDomainNavigatorItem).EObject
            		] as Collection)
            	)
            );
		}
	}
}