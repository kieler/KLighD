package de.cau.cs.kieler.kiml.klayoutdata.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.text.services.KLayoutDataGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public abstract class AbstractKLayoutDataSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private KLayoutDataGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KGraphPackage.PERSISTENT_ENTRY:
				if(context == grammarAccess.getPersistentEntryRule()) {
					sequence_PersistentEntry(context, (PersistentEntry) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KLayoutDataPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
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
	 *     (
	 *         sourcePoint=KPoint 
	 *         targetPoint=KPoint 
	 *         (bendPoints+=KPoint bendPoints+=KPoint*)? 
	 *         (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?
	 *     )
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
	 *     (x=EFloat y=EFloat)
	 */
	protected void sequence_KPoint(EObject context, KPoint semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KLayoutDataPackage.Literals.KPOINT__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KLayoutDataPackage.Literals.KPOINT__X));
			if(transientValues.isValueTransient(semanticObject, KLayoutDataPackage.Literals.KPOINT__Y) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KLayoutDataPackage.Literals.KPOINT__Y));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0(), semanticObject.getX());
		feeder.accept(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0(), semanticObject.getY());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xpos=EFloat? 
	 *         ypos=EFloat? 
	 *         width=EFloat? 
	 *         height=EFloat? 
	 *         insets=KInsets? 
	 *         (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?
	 *     )
	 */
	protected void sequence_KShapeLayout(EObject context, KShapeLayout semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (key=EString value=EString?)
	 */
	protected void sequence_PersistentEntry(EObject context, PersistentEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
