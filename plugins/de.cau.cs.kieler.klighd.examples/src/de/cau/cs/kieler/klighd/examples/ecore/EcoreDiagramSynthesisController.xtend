package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.klighd.ui.view.controller.AbstractViewUpdateController
import de.cau.cs.kieler.klighd.ui.view.controllers.EditorSaveAdapter
import de.cau.cs.kieler.klighd.ui.view.model.MessageModel
import java.lang.reflect.Method
import java.util.List
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.presentation.EcoreEditor
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.viewers.SelectionChangedEvent
import org.eclipse.ui.IEditorPart
import org.eclipse.ui.part.FileEditorInput

/**
 * View controller for ecore modeling editor.
 * 
 * @author als
 */
class EcoreDiagramSynthesisController extends AbstractViewUpdateController implements EditorSaveAdapter.EditorSafeListener {

	/** Controller ID. */
	private static final String ID = "de.cau.cs.kieler.klighd.examples.ecore.EcoreDiagramSynthesisController"

	/** The save adapter for the editor. */
	val EditorSaveAdapter saveAdapter

	private val List<EModelElement> selectedModelElements = newLinkedList()
	private Class<?> navItemClass
	private Method eObjectGetter
	private EcoreEditor editor

	/**
	 * Default Constructor.
	 */
	new() {
		saveAdapter = new EditorSaveAdapter(this)
		try {
			navItemClass = Class.forName("org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem")
			eObjectGetter = navItemClass.getMethod("getEObject")
		} catch (Throwable t) {
			// nothing
		}
	}

	/**
	 * {@inheritDoc}
	 */
	override String getID() {
		return ID
	}

    // -- Activation
    // -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	override void onActivate(IEditorPart editor) {
		if (editor instanceof EcoreEditor) {
			updateModel(editor.readModel)
			saveAdapter.activate(editor)
			editor.addSelectionChangedListener(this)
			this.editor = editor
		} else {
			throw new IllegalArgumentException("Incompatible Editor")
		}
	}

	/**
	 * {@inheritDoc}
	 */
	override void onDeactivate() {
		saveAdapter.deactivate()
		editor.removeSelectionChangedListener(this)
		editor = null
	}

    // -- Diagram Selection Change Event
    // -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	override void selectionChanged(SelectionChangedEvent event) {
		if (event.source instanceof EcoreEditor) {
			updateModel(editor.readModel)
		}
	}

	// -- Save Listener
	// -------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	override void onEditorSaved(IEditorPart editor) {
        if (diagramView.isLinkedWithActiveEditor) {
            updateModel(this.editor.readModel)
        }
    }
	
    /**
     * {@inheritDoc}
     */
    override refresh() {
        updateModel(this.editor.readModel)
    }

	// -- Utility
	// -------------------------------------------------------------------------
	/**
	 * Reads the selected model elements from given EcoreTreeEditor.
	 * 
	 * @param editor
	 *            IEditorPart containing model
	 * @return EObject model
	 */
	private def readModel(EcoreEditor editor) {
		val selectionElements = if (editor.selection instanceof IStructuredSelection) {
				(editor.selection as IStructuredSelection).toList
			} else {
				emptyList
			}

		if (selectionElements.empty) {
			return null
		}

		var EModelElementCollection modelElements
		var IPath editorInputPath

		if (typeof(FileEditorInput).isInstance(editor.getEditorInput())) {
			// e.g. by the Ecore tree editor
			val FileEditorInput fileEditorInput = typeof(FileEditorInput).cast(editor.getEditorInput())
			if (fileEditorInput.getFile() != null && fileEditorInput.getFile().getLocationURI() != null) {
				editorInputPath = fileEditorInput.getPath()
			}
		}
		if (editorInputPath != null) {
			modelElements = EModelElementCollection::of(
				selectionElements.filter(typeof(EModelElement)).toList
			)
		}
		if (modelElements.nullOrEmpty) {
			var List<EModelElement> elements

			if (selectionElements.forall[typeof(EPackage).isInstance(it) || typeof(EClass).isInstance(it)]) {
				// in case the elements to be depicted are given immediately,
				elements = selectionElements.filter(EModelElement).toList

			} else if (navItemClass != null && eObjectGetter != null) {
				// this case covers the situation of depicting classes selected in the Project Explorer
				if (selectionElements.forall[navItemClass.isInstance(it)]) {
					elements = selectionElements.filter(navItemClass).map [
						eObjectGetter.invoke(it)
					].filter(typeof(EModelElement)).toList
				}
			}

			if (elements != null) {
				// By means of this construction we reduced the conversion of nodes the result from the
				// insertion of further element between the head and tail of the selection list.
				// We retain all previous selected ones ...
				this.selectedModelElements.retainAll(elements)

				// ... determine the newly selected ones ...
				elements.removeAll(this.selectedModelElements)

				// ... and add them to the selected elements list
				this.selectedModelElements += elements

				modelElements = EModelElementCollection::of(selectedModelElements)
			}
		}
		if (modelElements.nullOrEmpty) {
			return new MessageModel("No model element selected")
		} else {
			return modelElements
		}
	}

}