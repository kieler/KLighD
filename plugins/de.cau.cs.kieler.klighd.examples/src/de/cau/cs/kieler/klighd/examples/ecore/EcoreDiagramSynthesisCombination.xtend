package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger
import org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import java.util.Collection
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass

/**
 * Combination that triggers the synthesis of ecore diagrams.
 * 
 * @author chsch
 */
class EcoreDiagramSynthesisCombination extends AbstractCombination {
	
	/**
	 * The 'execute()' method, see doc of {@link AbstractCombination}.
	 */
	def public void execute(SelectionTrigger$SelectionState triggerState) {
		
		val selection = triggerState.selectedObjects;
		
		if (!selection.nullOrEmpty) {
            if (selection.forall[typeof(EPackage).isInstance(it) || typeof(EClass).isInstance(it)]) {
                // in case the elements to be depicted are given immediately,
                //   e.g. by the ecore tree editor
                this.schedule(
                    new KlighdUpdateDiagramEffect(
                        EModelElementCollection::of(triggerState.selectedObjects as Collection)
                    )
                );
            } else if (selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
                // in case ecore elements are selected within the 'Project Explorer' view
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
}