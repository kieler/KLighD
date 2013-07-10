package de.cau.cs.kieler.core.kgraph.text.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public class AbstractKGraphJavaValidator extends AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://kieler.cs.cau.de/KGraph"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://kieler.cs.cau.de/KLayoutData"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://kieler.cs.cau.de/KRendering"));
		return result;
	}

}
