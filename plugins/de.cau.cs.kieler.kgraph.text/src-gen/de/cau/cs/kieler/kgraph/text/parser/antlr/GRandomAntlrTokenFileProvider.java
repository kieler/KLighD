/*
 * generated by Xtext 2.10.0
 */
package de.cau.cs.kieler.kgraph.text.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class GRandomAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("de/cau/cs/kieler/kgraph/text/parser/antlr/internal/InternalGRandom.tokens");
	}
}