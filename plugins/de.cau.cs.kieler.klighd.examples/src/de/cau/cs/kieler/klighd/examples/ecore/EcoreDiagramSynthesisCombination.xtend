package de.cau.cs.kieler.klighd.examples.ecore

import java.util.Collection
import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger$KlighdSelectionState
import de.cau.cs.kieler.core.model.triggers.PartTrigger
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem


/**
 * Combination that triggers the synthesis of Ecore diagrams.
 * 
 * @author chsch
 */
class EcoreDiagramSynthesisCombination extends AbstractCombination {
	
	/**
	 * The 'execute()' method, see doc of {@link AbstractCombination}.
	 */
	def public void execute(PartTrigger$EditorState es, SelectionTrigger$SelectionState selectionState,
	    KlighdSelectionTrigger$KlighdSelectionState klighdSelectionState) {
		
		if (this.latestState() == es) {
		   //inputPath = es.getProperty(PartTrigger::EDITOR_INPUT_PATH) as IPath;
		   return; // do only react on selectionState
		}
				
		if (this.latestState() == klighdSelectionState && klighdSelectionState.selections.size > 1) {
		    // in case a selection has been performed in the diagram ...
		    //  (the case of selections.size == 1 is skipped as this interferes with the
		    //   'highlight in source functionality')
            this.schedule(
                new KlighdUpdateDiagramEffect("de.cau.cs.kieler.klighd.examples.ecore.selection",
                    EModelElementCollection::of(klighdSelectionState.selectedEModelElements
                        .filter(typeof(EClassifier)))
                )
            );
            return;
		}
		
		val selection = selectionState.selectedObjects;
		if (!selection.nullOrEmpty) {
            if (selection.forall[typeof(EPackage).isInstance(it) || typeof(EClass).isInstance(it)]) {
                // in case the elements to be depicted are given immediately,
                //   e.g. by the ecore tree editor

                val inputPath = es.getProperty(PartTrigger::EDITOR_INPUT_PATH) as IPath;
                if (inputPath != null) {
                    val id = inputPath.toPortableString().replace(":", "") as String;
                    this.schedule(new KlighdUpdateDiagramEffect(id, inputPath.lastSegment,
                            EModelElementCollection::of(selectionState.selectedObjects as Collection),
                            es.editorPart
                        )
                    );
                }
            } else {
                // this case covers the situation of depicting classes selected in the Project Explorer               
                if (selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
                    // in case ecore elements are selected within the 'Project Explorer' view
                    this.schedule(new KlighdUpdateDiagramEffect("de.cau.cs.kieler.klighd.examples.ecore.explorer",
                        "KLighD Class Diagram", EModelElementCollection::of(
                            selectionState.selectedObjects.map[
                                (it as EcoreDomainNavigatorItem).EObject as EModelElement
        		            ])
        		        )
                	);
                }
            } 
		}
	}
}