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
package de.cau.cs.kieler.klighd.krendering;

import java.util.Arrays;
import java.util.List;

import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Some convenience methods supporting the proper handling of KText renderings.
 * 
 * @author chsch
 */
public final class KTextUtil {
    
    /**
     * Hidden default constructor.
     */
    private KTextUtil() {
    }
    
    /**
     * Method is supposed to realize the platform independent splitting of the (potentially)
     * multi-lined strings of KText renderings into lists of the separate lines.<br>
     * In addition, leading or trailing spaces of each line are removed.
     * Delegates to {@link #getTextLines(String)}.
     * 
     * @param kText
     *            the KText rendering whose separate lines are queried
     * @return the lines split up at all known valid line separators
     */
    public static List<String> getTextLines(final KText kText) {
        if (kText == null || Strings.isNullOrEmpty(kText.getText())) {
            return Arrays.asList("");
        }
        return getTextLines(kText.getText());
    }
    
    /**
     * Method is supposed to realize the platform independent splitting of
     * (potentially) multi-lined strings into lists of the separate lines.<br>
     * In addition, leading or trailing spaces of each line are removed.
     * 
     * @param text the String whose separate lines are queried
     * @return the lines split up at all known valid line separators 
     */
    public static List<String> getTextLines(final String text) {
        if (Strings.isNullOrEmpty(text)) {
            return Arrays.asList("");
        }
        return Lists.newArrayList(IterableExtensions.map(
                Arrays.asList(text.split("\\r?\\n|\\r")),
                new Functions.Function1<String, String>() {
                    public String apply(final String line) {
                        return line.trim();
                    }
                }));
    }

    /**
     * This helper determines the width of the longest {@link de.cau.cs.kieler.core.kgraph.KLabel
     * KLabel} of the {@link KPort KPorts} of the given {@link KNode} wrt. the given
     * {@link PortSide}. For north and south ports this value denotes the height of the port label
     * area.
     * 
     * @param node
     *            the node to compute the required port label width
     * @param side
     *            the side on the node
     * @return the maximal width of the port labels of 'node' on 'side'
     */
    public static Float getLabelAreaWidth(final KNode node, final PortSide side) {
        return IterableExtensions.fold(Iterables.filter(node.getPorts(), new Predicate<KPort>() {
            public boolean apply(final KPort port) {
                return port.getData(KShapeLayout.class).getProperty(LayoutOptions.PORT_SIDE) == side;
            }
        }), 0f, new Functions.Function2<Float, KPort, Float>() {
            public Float apply(final Float length, final KPort port) {
                return Math.max(length,
                        PlacementUtil.estimateTextSize(new KPortExtensions().getFirstText(port))
                                .getWidth());
            }
        });
    }
}
