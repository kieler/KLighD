/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.conversion.impl.AbstractIDValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter;
import org.eclipse.xtext.conversion.impl.IDValueConverter;
import org.eclipse.xtext.conversion.impl.INTValueConverter;
import org.eclipse.xtext.conversion.impl.STRINGValueConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Strings;


/**
 * Value converter for the KGraph grammar.
 * 
 * @author msp
 */
public class KGraphValueConverters extends AbstractDeclarativeValueConverterService {
    
    /** precision of float outputs below which they are converted to integers. */
    private static final float FLOAT_PREC = 1e-4f;

    @Inject
    private AbstractIDValueConverter idValueConverter;

    // CHECKSTYLEOFF MethodName
    
    /**
     * Create a converter for the ID rule.
     * 
     * @return a value converter for ID
     */
    @ValueConverter(rule = "ID")
    public IValueConverter<String> ID() {
        return idValueConverter;
    }
    
    private QualifiedIDValueConverter qualifiedIdValueConverter = new QualifiedIDValueConverter();

    /**
     * Create a converter for the QualifiedID rule.
     * 
     * @return a value converter for QualifiedID
     */
    @ValueConverter(rule = "QualifiedID")
    public IValueConverter<String> QualifiedID() {
        return qualifiedIdValueConverter;
    }

    @Inject
    private STRINGValueConverter stringValueConverter;

    /**
     * Create a converter for the STRING rule.
     * 
     * @return a value converter for STRING
     */
    @ValueConverter(rule = "STRING")
    public IValueConverter<String> STRING() {
        return stringValueConverter;
    }
    
    private PropertyValueConverter propertyValueConverter = new PropertyValueConverter();

    /**
     * Create a converter for the PropertyValue rule.
     * 
     * @return a value converter for PropertyValue
     */
    @ValueConverter(rule = "PropertyValue")
    public IValueConverter<String> propertyValue() {
        return propertyValueConverter;
    }

    @Inject
    private INTValueConverter intValueConverter;

    /**
     * Create a converter for the NATURAL rule.
     * 
     * @return a value converter for NATURAL
     */
    @ValueConverter(rule = "NATURAL")
    public IValueConverter<Integer> NATURAL() {
        return intValueConverter;
    }

    /**
     * Create a converter for the Float rule.
     * 
     * @return a value converter for Float
     */
    @ValueConverter(rule = "Float")
    public IValueConverter<Float> Float() {
        return new FloatValueConverter();
    }

    /**
     * Create a converter for the RED rule.
     * 
     * @return a value converter for RED
     */
    @ValueConverter(rule = "RED")
    public IValueConverter<Integer> RED() {
        return new IntUnitSuffixConverter("r");
    }

    /**
     * Create a converter for the GREEN rule.
     * 
     * @return a value converter for GREEN
     */
    @ValueConverter(rule = "GREEN")
    public IValueConverter<Integer> GREEN() {
        return new IntUnitSuffixConverter("g");
    }

    /**
     * Create a converter for the BLUE rule.
     * 
     * @return a value converter for BLUE
     */
    @ValueConverter(rule = "BLUE")
    public IValueConverter<Integer> BLUE() {
        return new IntUnitSuffixConverter("b");
    }

    /**
     * Create a converter for the ALPHA rule.
     * 
     * @return a value converter for ALPHA
     */
    @ValueConverter(rule = "ALPHA")
    public IValueConverter<Integer> ALPHA() {
        return new IntUnitSuffixConverter("a");
    }

    /**
     * Create a converter for the FSIZE rule.
     * 
     * @return a value converter for FSIZE
     */
    @ValueConverter(rule = "FSIZE")
    public IValueConverter<Integer> FSIZE() {
        return new IntUnitSuffixConverter("pt");
    }

    /**
     * Create a converter for the DEGREES rule.
     * 
     * @return a value converter for DEGREES
     */
    @ValueConverter(rule = "DEGREES")
    public IValueConverter<Float> DEGREES() {
        return new FloatUnitSuffixConverter(1, "deg");
    }

    /**
     * Create a converter for the PERCENT rule.
     * 
     * @return a value converter for PERCENT
     */
    @ValueConverter(rule = "PERCENT")
    public IValueConverter<Float> PERCENT() {
        return new FloatUnitSuffixConverter(100, "%"); // SUPPRESS CHECKSTYLE MagicNumber
    }
    
    
    /**
     * Value converter for qualified identifiers.
     */
    private class QualifiedIDValueConverter extends IDValueConverter {

        @Override
        public String toString(final String s) {
            // Escape each ID with the converter for identifiers
            StringBuilder result = new StringBuilder();
            String[] idarray = s.split("\\.");
            for (int i = 0; i < idarray.length; i++) {
                if (i > 0) {
                    result.append('.');
                }
                result.append(idValueConverter.toString(idarray[i]));
            }
            return result.toString();
        }
        
        @Override
        public String toValue(final String string, final INode node) {
            if (string == null) {
                return null;
            }
            return string.replace("^", "");
        }
    }
    
    /**
     * Value converter for integer values with a unit suffix.
     */
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
        protected Integer internalToValue(final String string, final INode node) {
            int i = (string == null) ? -1 : string.indexOf(suffix);
            if (i >= 0) {
                return intValueConverter.toValue(string.substring(0, i), node);
            }
            return intValueConverter.toValue(string, node);
        }
    }
    
    /**
     * Value converter for floating point values with a unit suffix.
     */
    private class FloatUnitSuffixConverter extends AbstractNullSafeConverter<Float> {
        private float factor;
        private String suffix;
        
        FloatUnitSuffixConverter(final float factor, final String suffix) {
            this.factor = factor;
            this.suffix = suffix;
        }
        
        @Override
        protected String internalToString(final Float value) {
            float outputValue = value * factor;
            int intOutputValue = Math.round(outputValue);
            if (Math.abs(outputValue - intOutputValue) < FLOAT_PREC) {
                return Integer.toString(intOutputValue) + suffix;
            } else {
                return Float.toString(outputValue) + suffix;
            }
        }
        
        @Override
        protected Float internalToValue(final String string, final INode node) {
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
    
    /**
     * Value converter for floating point values.
     */
    private class FloatValueConverter extends AbstractNullSafeConverter<Float> {
        
        @Override
        protected String internalToString(final Float value) {
            int intValue = Math.round(value);
            if (Math.abs(value - intValue) < FLOAT_PREC) {
                return Integer.toString(intValue);
            } else {
                return Float.toString(value);
            }
        }
        
        @Override
        protected Float internalToValue(final String string, final INode node) {
            if (Strings.isEmpty(string)) {
                throw new ValueConverterException("Couldn't convert empty string to a float value.",
                        node, null);
            }
            try {
                return Float.parseFloat(string);
            } catch (NumberFormatException e) {
                throw new ValueConverterException("Couldn't convert '" + string + "' to a float value.",
                        node, e);
            }
        }
    }
    
    /**
     * Value converter for {@code PropertyValue} instances. To convert a value to a string, the following
     * conditions are tested in exactly this order:
     * <ol>
     *   <li>The value is a boolean literal (that is, it equals either "true" or "false"). In this case,
     *       the value is returned as is.</li>
     *   <li>The value can be parsed as a floating point number. In this case, the value is returned
     *       as is.</li>
     *   <li>The value is an element of the language induced by the {@code QualifiedName} rule. In this
     *       case, the {@link QualifiedIDValueConverter} is invoked and its result returned.</li>
     * </ol>
     * <p>If non of these conditions apply, the result is the value surrounded by quotation marks.</p>
     * 
     * @author cds
     */
    private class PropertyValueConverter extends AbstractNullSafeConverter<String> {
        /**
         * Regular expression pattern that matches instances of the QualifiedName rule,
         * without keyword escapes.
         */
        private Pattern qualifiedNamePattern = Pattern.compile(
                "[a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*");
        
        /**
         * Regular expression pattern that matches instances of the QualifiedName rule,
         * including keyword escapes (^).
         */
        private Pattern keywordEscapedQualifiedNamePattern = Pattern.compile(
                "\\^?[a-zA-Z_][a-zA-Z0-9_]*(\\.\\^?[a-zA-Z_][a-zA-Z0-9_]*)*");
        
        @Override
        protected String internalToString(final String value) {
            // Check if the value is a Boolean literal
            if (value.equals("true") || value.equals("false")) {
                return value;
            }
            
            // Check if the value can be parsed as a Float
            try {
                Float.parseFloat(value);
                return value;
            } catch (NumberFormatException e) {
                // Simply resume execution
            }
            
            // Check if the value is an instance of the QualifiedName rule
            if (qualifiedNamePattern.matcher(value).matches()) {
                // Apply the qualified ID value converter
                return qualifiedIdValueConverter.toString(value);
            }
            
            // It's a String; surround with quotation marks
            return '"' + Strings.convertToJavaString(value, true) + '"';
        }

        @Override
        protected String internalToValue(final String string, final INode node) {
            // Strip leading and trailing quotation mark, if any (this can be simplified if we assume
            // that the string passed to the method either has both, a leading and a trailing quotation
            // mark, or none; but even though we should be able to assume that, we decide to program
            // defensively)
            boolean stringStart = string.startsWith("\"");
            boolean stringEnd = string.endsWith("\"");
            if (stringStart || stringEnd) {
                int begin = stringStart ? 1 : 0;
                int end = string.length() - (stringEnd ? 1 : 0);
                return Strings.convertFromJavaString(string.substring(begin, end), true);
            } else if (keywordEscapedQualifiedNamePattern.matcher(string).matches()) {
                return qualifiedIdValueConverter.toValue(string, node);
            } else {
                return string;
            }
        }
    }
}
