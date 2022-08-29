/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.cau.cs.kieler.klighd.filtering.SemanticFilterRule;
import de.cau.cs.kieler.klighd.filtering.parser.SemanticFilterRuleParser;
import de.cau.cs.kieler.klighd.filtering.parser.SemanticFilterRuleParser.InvalidSyntaxException;

/**
 * Tests of classes in {@link de.cau.cs.kieler.klighd.filtering}
 * 
 * @author mka
 *
 */
public class SemanticFilteringTest {
    
    @Test
    public void testSimpleExpressions() {
        try {
            SemanticFilterRule rule1 = new SemanticFilterRuleParser().parse("#tag1 && #tag2 || #tag3");
            assertEquals("OR(AND((tag=tag1, num=null), (tag=tag2, num=null)), (tag=tag3, num=null))", rule1.toString());
            SemanticFilterRule rule2 = new SemanticFilterRuleParser().parse("( #tag1 )");
            assertEquals("(tag=tag1, num=null)", rule2.toString());
            SemanticFilterRule rule3 = new SemanticFilterRuleParser().parse("$tag1 + $tag2 > 4");
            assertEquals("GREATERTHAN(NUMERICADDITION((tag=tag1, num=null), (tag=tag2, num=null)), CONST(4.0))", rule3.toString());
        } catch (InvalidSyntaxException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
