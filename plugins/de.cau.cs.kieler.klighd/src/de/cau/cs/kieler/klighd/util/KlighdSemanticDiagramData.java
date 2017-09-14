/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * Container for semantic data that are attached to the rendered diagram elements. Note that not all
 * rendering mechanisms support this kind of data.
 *
 * Internally, two lists are employed to store either a String as value or a Function that returns a
 * String as value. The key sets of the two maps are kept free of duplicates. Thus, if a key is
 * added to both maps, only the last insertion is retained.
 *
 * @author uru
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdSemanticDiagramData implements Iterable<Map.Entry<String, String>> {

    /** A convenience data record. */
    public static final class FunctionInput {

        // SUPPRESS CHECKSTYLE NEXT 6 Visibility

        /** may be <code>null</code>. */
        public final ViewContext viewContext;

        /** may be <code>null</code>. */
        public final EObject viewModelElement;

        private FunctionInput(final ViewContext viewContext, final EObject viewModelElement) {
            this.viewContext = viewContext;
            this.viewModelElement = viewModelElement;
        }
    }

    /** A convenience data record. */
    public static final class TextLineFunctionInput {

        // SUPPRESS CHECKSTYLE NEXT 9 Visibility|Javadoc

        /** may be <code>null</code>. */
        public final ViewContext viewContext;

        /** may be <code>null</code>. */
        public final EObject viewModelElement;

        public final String textLine;
        public final int noOfLine;

        private TextLineFunctionInput(final ViewContext viewContext, final EObject viewModelElement,
                final String textLine, final int noOfLine) {

            this.viewContext = viewContext;
            this.viewModelElement = viewModelElement;
            this.textLine = textLine;
            this.noOfLine = noOfLine;
        }
    }

    private ViewContext viewContext = null;
    private EObject viewModelElement = null;

    private Map<String, String> strStr = null;
    private Map<String, Function<FunctionInput, String>> strFun = null;
    private Map<String, Function<TextLineFunctionInput, String>> strFunTextLine = null;


    /**
     * @return a new empty instance of {@link KlighdSemanticDiagramData}.
     */
    public static KlighdSemanticDiagramData create() {
        return new KlighdSemanticDiagramData();
    }

    /**
     * @param keyValues
     *            an array of key/value pairs. The i-th entry is a key if i is even, a value if i is
     *            odd. A dangling key is dropped.
     * @return a {@link KlighdSemanticDiagramData} instance with the passed key value pairs.
     */
    public static KlighdSemanticDiagramData of(final String... keyValues) {
        final KlighdSemanticDiagramData data = new KlighdSemanticDiagramData();
        for (int i = 0; i < keyValues.length - 1; i += 2) {
            data.put(keyValues[i], keyValues[i + 1]);
        }
        return data;
    }

    /**
     * @param viewModelElement
     *            the {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} this semantic data
     *            record corresponds to, I don't want to enforce strictly the view element this
     *            record is attached to for flexibility reasons
     * @param keyValues
     *            an array of key/value pairs. The i-th entry is a key if i is even, a value if i is
     *            odd. A dangling key is dropped.
     * @return a {@link KlighdSemanticDiagramData} instance with the passed key value pairs.
     */
    public static KlighdSemanticDiagramData of(final EObject viewModelElement,
            final String... keyValues) {
        return of(keyValues).setViewModelElement(viewModelElement);
    }

    /**
     * @param viewContext
     *            the {@link ViewContext} of the diagram
     * @param keyValues
     *            an array of key/value pairs. The i-th entry is a key if i is even, a value if i is
     *            odd. A dangling key is dropped.
     * @return a {@link KlighdSemanticDiagramData} instance with the passed key value pairs.
     */
    public static KlighdSemanticDiagramData of(final ViewContext viewContext,
            final String... keyValues) {
        return of(keyValues).setViewContext(viewContext);
    }

    /**
     * @param viewContext
     *            the {@link ViewContext} of the diagram
     * @param viewModelElement
     *            the {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} this semantic data
     *            record corresponds to, I don't want to enforce strictly the view element this
     *            record is attached to for flexibility reasons
     * @param keyValues
     *            an array of key/value pairs. The i-th entry is a key if i is even, a value if i is
     *            odd. A dangling key is dropped.
     * @return a {@link KlighdSemanticDiagramData} instance with the passed key value pairs.
     */
    public static KlighdSemanticDiagramData of(final ViewContext viewContext,
            final EObject viewModelElement, final String... keyValues) {
        return of(keyValues).setViewContext(viewContext).setViewModelElement(viewModelElement);
    }


    /**
     * @return the corresponding viewContext, if configured
     */
    public ViewContext getViewContext() {
        return viewContext;
    }

    /**
     * @param theViewContext
     *            the viewContext to set
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData setViewContext(final ViewContext theViewContext) {
        this.viewContext = theViewContext;
        return this;
    }

    /**
     * @return the corresponding viewModelElement, if configured
     */
    public EObject getViewModelElement() {
        return viewModelElement;
    }

    /**
     * @param theViewModelElement
     *            the viewModelElement to set
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData setViewModelElement(final EObject theViewModelElement) {
        this.viewModelElement = theViewModelElement;
        return this;
    }

    /**
     * @param key
     *            the key
     * @param value
     *            the value as String
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData put(final String key, final String value) {
        if (strStr == null) {
            strStr = Maps.newHashMap();
        }
        if (strFun != null && strFun.containsKey(key)) {
            strFun.remove(key);
        }
        strStr.put(key, value);

        return this;
    }

    /**
     * Convenience method for attaching an id attribute to the corresponding vector graphics entry.
     * 
     * @param value
     *            the value as String
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData putID(final String value) {
        return put(KlighdConstants.SEMANTIC_DATA_ID, value);
    }

    /**
     * @param key
     *            the key
     * @param fun
     *            a function returning a String value. The function is evaluated every time the
     *            key's value is requested.<br>
     *            The input provided to the function is a record offering configured
     *            {@link ViewContext} and the configured view model element (
     *            {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}); both may be
     *            <code>null</code>!
     *
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData put(final String key, final Function<FunctionInput, String> fun) {
        if (strFun == null) {
            strFun =  Maps.newHashMap();
        }
        if (strStr != null && strStr.containsKey(key)) {
            strStr.remove(key);
        }
        strFun.put(key, fun);

        return this;
    }

    /**
     * Convenience method for attaching an id attribute to the corresponding vector graphics entry.
     * 
     * @param fun
     *            a function returning a String value. The function is evaluated every time the
     *            key's value is requested.<br>
     *            The input provided to the function is a record offering configured
     *            {@link ViewContext} and the configured view model element (
     *            {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}); both may be
     *            <code>null</code>!
     *
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData putID(final Function<FunctionInput, String> fun) {
        return put(KlighdConstants.SEMANTIC_DATA_ID, fun);
    }

    /**
     * @param key
     *            the key
     * @param fun
     *            a function returning a String value. The function is evaluated every time the
     *            key's value is requested.<br>
     *            The input provided to the function is a record offering the configured
     *            {@link ViewContext}, the configured view model element (
     *            {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}), the line of text
     *            to being exported, and the number of the line of text within the multi-line text;
     *            both the {@link ViewContext} and the view model element may be <code>null</code>!
     *
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData putAtTextLine(
            final String key, final Function<TextLineFunctionInput, String> fun) {

        if (strFunTextLine == null) {
            strFunTextLine = Maps.newHashMap();
        }

        strFunTextLine.put(key, fun);

        return this;
    }

    /**
     * Convenience method for attaching id attributes to the corresponding vector graphics text line
     * entries.
     * 
     * @param fun
     *            a function returning a String value. The function is evaluated every time the
     *            key's value is requested.<br>
     *            The input provided to the function is a record offering the configured
     *            {@link ViewContext}, the configured view model element (
     *            {@link de.cau.cs.kieler.klighd.kgraph.KGraphElement KGraphElement} or
     *            {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}), the line of text
     *            to being exported, and the number of the line of text within the multi-line text;
     *            both the {@link ViewContext} and the view model element may be <code>null</code>!
     * 
     * @return <code>this</code> {@link KlighdSemanticDiagramData} for convenience
     */
    public KlighdSemanticDiagramData putIDatTextLine(final Function<TextLineFunctionInput, String> fun) {
        return putAtTextLine(KlighdConstants.SEMANTIC_DATA_ID, fun);
    }


    /**
     * @return an {@link Iterator} over all stored key/value pairs. The function values is are
     *         evaluated upon the respective call to {@code next}.
     */
    public Iterator<Map.Entry<String, String>> iterator() {

        final Iterator<Map.Entry<String, String>> lazyIt =
                strFun == null ? null : Iterators.transform(strFun.entrySet().iterator(),
                        new TransformFunction(new FunctionInput(viewContext, viewModelElement)));

        if (strFun != null && strStr != null) {
            return Iterators.concat(strStr.entrySet().iterator(), lazyIt);
        } else if (strFun != null) {
            return lazyIt;
        } else if (strStr != null) {
            return strStr.entrySet().iterator();
        } else {
            return Collections.emptyIterator();
        }
    }

    /** A named class to reduce the generic mess in the method above. */
    private static final class TransformFunction implements
            Function<Map.Entry<String, Function<FunctionInput, String>>, Map.Entry<String, String>> {

        private final FunctionInput functionInput;

        private TransformFunction(final FunctionInput functionInput) {
            this.functionInput = functionInput;
        }

        public Map.Entry<String, String> apply(
                final Map.Entry<String, Function<FunctionInput, String>> e) {

            return Maps.immutableEntry(e.getKey(), e.getValue().apply(functionInput));
        }
    }


    /**
     * @param textLine
     *            the text line for determining the corresponding key value pairs
     * @param noOfLine
     *            the number of the text line within the multi-line text
     * @return a single pass {@link Iterable} returning all key/value pairs to be attached to a line
     *         of a multi-line text element
     */
    public Iterable<Map.Entry<String, String>> textLineIterable(final String textLine,
            final int noOfLine) {
        return Iterables2.toIterable(textLineIterator(textLine, noOfLine));
    }

    /**
     * @param textLine
     *            the text line for determining the corresponding key value pairs
     * @param noOfLine
     *            the number of the text line within the multi-line text
     * @return an {@link Iterator} returning all key/value pairs to be attached to a line of a
     *         multi-line text element
     */
    public Iterator<Map.Entry<String, String>> textLineIterator(final String textLine,
            final int noOfLine) {

        return strFunTextLine == null
                ? Collections.<Map.Entry<String, String>>emptyIterator()
                : Iterators.transform(strFunTextLine.entrySet().iterator(),
                        new TextLineTransformFunction(new TextLineFunctionInput(viewContext,
                                viewModelElement, textLine, noOfLine)));
    }

    /** A named class to reduce the generic mess in the method above. */
    private static final class TextLineTransformFunction implements Function<
                Map.Entry<String, Function<TextLineFunctionInput, String>>, Map.Entry<String, String>> {

        private final TextLineFunctionInput functionInput;

        private TextLineTransformFunction(final TextLineFunctionInput functionInput) {
            this.functionInput = functionInput;
        }

        public Map.Entry<String, String> apply(
                final Map.Entry<String, Function<TextLineFunctionInput, String>> e) {

            return Maps.immutableEntry(e.getKey(), e.getValue().apply(functionInput));
        }
    }
}
