/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.conversion.impl.AbstractIDValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter;
import org.eclipse.xtext.conversion.impl.INTValueConverter;
import org.eclipse.xtext.conversion.impl.STRINGValueConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Strings;

import com.google.inject.Inject;

/**
 * Value converter for the KGraph grammar.
 * 
 * @author msp
 */
public class KGraphValueConverters extends AbstractDeclarativeValueConverterService {

	@Inject
	private AbstractIDValueConverter idValueConverter;

	@ValueConverter(rule = "ID")
	public IValueConverter<String> ID() {
		return idValueConverter;
	}

	@Inject
	private STRINGValueConverter stringValueConverter;
	
	@ValueConverter(rule = "STRING")
	public IValueConverter<String> STRING() {
		return stringValueConverter;
	}

	@Inject
	private INTValueConverter intValueConverter;
	
	@ValueConverter(rule = "NATURAL")
	public IValueConverter<Integer> NATURAL() {
		return intValueConverter;
	}
	
	@ValueConverter(rule = "RED")
	public IValueConverter<Integer> RED() {
		return new IntUnitSuffixConverter("r");
	}
	
	@ValueConverter(rule = "GREEN")
	public IValueConverter<Integer> GREEN() {
		return new IntUnitSuffixConverter("g");
	}
	
	@ValueConverter(rule = "BLUE")
	public IValueConverter<Integer> BLUE() {
		return new IntUnitSuffixConverter("b");
	}
	
	@ValueConverter(rule = "ALPHA")
	public IValueConverter<Integer> ALPHA() {
		return new IntUnitSuffixConverter("a");
	}
	
	@ValueConverter(rule = "FSIZE")
	public IValueConverter<Integer> FSIZE() {
		return new IntUnitSuffixConverter("pt");
	}
	
	@ValueConverter(rule = "DEGREES")
	public IValueConverter<Float> DEGREES() {
		return new FloatUnitSuffixConverter(1, "deg");
	}
	
	@ValueConverter(rule = "PERCENT")
	public IValueConverter<Float> PERCENT() {
		return new FloatUnitSuffixConverter(100, "%");
	}
	
	private class IntUnitSuffixConverter extends AbstractNullSafeConverter<Integer> {
		
		private String suffix;
		
		IntUnitSuffixConverter(final String suffix) {
			this.suffix = suffix;
		}
		
		@Override
		protected String internalToString(final Integer value) {
			return intValueConverter.toString(value) + suffix;
		}
		
		@Override
		protected Integer internalToValue(final String string, final INode node)
				throws ValueConverterException {
			int i = (string == null) ? -1 : string.indexOf(suffix);
			if (i >= 0) {
				return intValueConverter.toValue(string.substring(0, i), node);
			}
			return intValueConverter.toValue(string, node);
		}
	}
	
	private class FloatUnitSuffixConverter extends AbstractNullSafeConverter<Float> {
		
		private float factor;
		private String suffix;
		
		FloatUnitSuffixConverter(final float factor, final String suffix) {
			this.factor = factor;
			this.suffix = suffix;
		}
		
		@Override
		protected String internalToString(final Float value) {
			if (value == null) {
				throw new ValueConverterException("Float value may not be null.", null, null);
			}
			return Float.toString(value * factor) + suffix;
		}
		
		@Override
		protected Float internalToValue(final String string, final INode node)
				throws ValueConverterException {
			if (Strings.isEmpty(string)) {
				throw new ValueConverterException("Couldn't convert empty string to a float value.",
						node, null);
			}
			int i = string.indexOf(suffix);
			try {
				if (i >= 0) {
					return Float.parseFloat(string.substring(0, i)) / factor;
				}
				return Float.parseFloat(string) / factor;
			} catch (NumberFormatException e) {
				throw new ValueConverterException("Couldn't convert '" + string + "' to a float value.",
						node, e);
			}
		}
	}
	
}
