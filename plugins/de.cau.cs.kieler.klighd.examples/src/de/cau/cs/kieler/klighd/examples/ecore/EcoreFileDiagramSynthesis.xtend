package de.cau.cs.kieler.klighd.examples.ecore

import com.google.common.collect.Iterators
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import javax.inject.Inject
import org.eclipse.emf.ecore.EPackage

class EcoreFileDiagramSynthesis extends AbstractDiagramSynthesis<EPackage> {
    
    @Inject
    private EcoreDiagramSynthesis delegate

    override transform(EPackage model) {
        val col = EModelElementCollection::of(Iterators::singletonIterator(model))

        return delegate.transform(col, usedContext);
    }
    
    override getDisplayedSynthesisOptions() {
        return delegate.displayedSynthesisOptions;
    }
    
    override getDisplayedLayoutOptions() {
        return delegate.displayedLayoutOptions;
    }
}