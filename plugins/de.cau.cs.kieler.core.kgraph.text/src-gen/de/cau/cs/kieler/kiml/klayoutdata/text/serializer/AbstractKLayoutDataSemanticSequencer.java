package de.cau.cs.kieler.kiml.klayoutdata.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.text.services.KLayoutDataGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("restriction")
public class AbstractKLayoutDataSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected KLayoutDataGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
	}
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == KLayoutDataPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KLayoutDataPackage.KEDGE_LAYOUT:
				if(context == grammarAccess.getKEdgeLayoutRule()) {
					sequence_KEdgeLayout(context, (KEdgeLayout) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KINSETS:
				if(context == grammarAccess.getKInsetsRule()) {
					sequence_KInsets(context, (KInsets) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KPOINT:
				if(context == grammarAccess.getKPointRule()) {
					sequence_KPoint(context, (KPoint) semanticObject); 
					return; 
				}
				else break;
			case KLayoutDataPackage.KSHAPE_LAYOUT:
				if(context == grammarAccess.getKShapeLayoutRule()) {
					sequence_KShapeLayout(context, (KShapeLayout) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     ((bendPoints+=KPoint bendPoints+=KPoint*)? sourcePoint=KPoint targetPoint=KPoint)
	 */
	protected void sequence_KEdgeLayout(EObject context, KEdgeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (top=EFloat? bottom=EFloat? left=EFloat? right=EFloat?)
	 */
	protected void sequence_KInsets(EObject context, KInsets semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x=EFloat? y=EFloat?)
	 */
	protected void sequence_KPoint(EObject context, KPoint semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xpos=EFloat? ypos=EFloat? width=EFloat? height=EFloat? insets=KInsets?)
	 */
	protected void sequence_KShapeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
