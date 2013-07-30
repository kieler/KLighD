package de.cau.cs.kieler.klighd.examples.ecore

import java.util.List

import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.core.kivi.triggers.PartTrigger
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect
import de.cau.cs.kieler.klighd.examples.ecore.EModelElementCollection
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger$KlighdSelectionState
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.incremental.UpdateStrategy

import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem
import org.eclipse.ui.part.FileEditorInput;
import de.cau.cs.kieler.klighd.util.KlighdProperties

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
		
		val selection = selectionState.selection;
		if (!selection.nullOrEmpty) {
            if (selection.forall[typeof(EPackage).isInstance(it) || typeof(EClass).isInstance(it)]) {
                // in case the elements to be depicted are given immediately,
                //   e.g. by the Ecore tree editor

                var editorInputPath = null as IPath;
                if (typeof(FileEditorInput).isInstance(es.getEditorPart().getEditorInput())) {
                    val fileEditorInput = typeof(FileEditorInput).cast(es.getEditorPart().getEditorInput());
                    if (fileEditorInput.getFile() != null && fileEditorInput.getFile().getLocationURI() != null) {
                        editorInputPath = fileEditorInput.getPath();
                    }
                }
                if (editorInputPath != null) {
                    val id = editorInputPath.toPortableString().replace(":", "") as String;
                    this.schedule(new KlighdUpdateDiagramEffect(id, editorInputPath.lastSegment,
                            EModelElementCollection::of(selectionState.selection.filter(typeof(EModelElement)).toList),
                            es.editorPart
                        )
                    );
                }
            } else {
                // this case covers the situation of depicting classes selected in the Project Explorer               
                if (selection.forall[typeof(EcoreDomainNavigatorItem).isInstance(it)]) {
                    val elements = selectionState.selection.filter(typeof(EcoreDomainNavigatorItem)).map[
                        it.EObject
                    ].filter(typeof(EModelElement)).toList;
                    
                    // By means of this construction we reduced the conversion of nodes the result from the
                    //  insertion of further element between the head and tail of the selection list.
                    // We retain all previous selected ones ...
                    this.selectedModelElements.retainAll(elements);
                    // ... determine the newly selected ones ...
                    elements.removeAll(this.selectedModelElements);
                    // ... and add them to the selected elements list
                    this.selectedModelElements += elements;
                    
                    // in case Ecore elements are selected within the 'Project Explorer' view
                    this.schedule(new KlighdUpdateDiagramEffect("de.cau.cs.kieler.klighd.examples.ecore.explorer",
                            "KLighD Class Diagram", EModelElementCollection::of(selectedModelElements)
        		        ) => [
                        it.setProperty(LightDiagramServices::REQUESTED_UPDATE_STRATEGY, UpdateStrategy::ID);
                        it.setProperty(KlighdProperties::MODEL_ACCESS, new EcoreModelAccess(this.selectedModelElements));
                    ]);
                }
            } 
		}
	}
    
    private List<EModelElement> selectedModelElements = newLinkedList();
}