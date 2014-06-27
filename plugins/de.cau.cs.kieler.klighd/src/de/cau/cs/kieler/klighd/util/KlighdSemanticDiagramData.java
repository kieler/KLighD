/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.util.Pair;

/**
 * Container for semantic data that are attached to the rendered diagram elements. Note that not all
 * rendering mechanisms support this kind of data.
 * 
 * Internally, two maps are employed to store either a String as value or a Function that returns a
 * String as value. The key sets of the two maps are kept free of duplicates. Thus, if a key is
 * added to both maps, only the last insertion is retained.
 * 
 * @author uru
 */
public class KlighdSemanticDiagramData {

    private List<Pair<String, String>> strStr = null;
    private List<Pair<String, Function<Void, String>>> strFun = null;

    /**
     * @param keyValues
     *            an array of key/value pairs. The i-th entry is a key if i is even, a value if i is
     *            odd. A dangling key is dropped.
     * @return a {@link KlighdSemanticDiagramData} instance with the passed key value pairs.
     */
    public static KlighdSemanticDiagramData of(final String... keyValues) {
        KlighdSemanticDiagramData data = new KlighdSemanticDiagramData();
        for (int i = 0; i < keyValues.length - 1; i += 2) {
            data.put(keyValues[i], keyValues[i + 1]);
        }
        return data;
    }

    /**
     * @param key
     *            the key
     * @param value
     *            the value as String
     */
    public void put(final String key, final String value) {
        if (strStr == null) {
            strStr = Lists.newArrayList();
        }
        if (strFun != null && strFun.contains(key)) {
            strFun.remove(key);
        }
        strStr.add(Pair.of(key, value));
    }

    /**
     * @param key
     *            the key
     * @param fun
     *            a function returning a String value. The function is evaluated every time the
     *            key's value is requested.
     */
    public void put(final String key, final Function<Void, String> fun) {
        if (strFun == null) {
            strFun = Lists.newArrayList();
        }
        if (strStr != null && strStr.contains(key)) {
            strStr.remove(key);
        }
        strFun.add(Pair.of(key, fun));
    }

    /**
     * @return an iterator over all stored key/value pairs. The function values is are evaluated
     *         upon the respective call to {@code next}.
     */
    public Iterator<Pair<String, String>> iterator() {
        Iterator<Pair<String, String>> lazyIt = null;
        if (strFun != null) {
            lazyIt = new Iterator<Pair<String, String>>() {
                private int pos = 0;

                /**
                 * {@inheritDoc}
                 */
                public boolean hasNext() {
                    return pos < strFun.size();
                }

                /**
                 * {@inheritDoc}
                 */
                public Pair<String, String> next() {
                    Pair<String, Function<Void, String>> pair = strFun.get(pos);
                    return Pair.of(pair.getFirst(), pair.getSecond().apply(null));
                }

                /**
                 * {@inheritDoc}
                 */
                public void remove() {
                    throw new UnsupportedOperationException("Remove is not supported.");
                }
            };
        }

        if (strFun != null && strStr != null) {
            return Iterators.concat(strStr.iterator(), lazyIt);
        } else if (strFun != null) {
            return lazyIt;
        } else if (strStr != null) {
            return strStr.iterator();
        } else {
            return Collections.emptyIterator();
        }
    }
}
