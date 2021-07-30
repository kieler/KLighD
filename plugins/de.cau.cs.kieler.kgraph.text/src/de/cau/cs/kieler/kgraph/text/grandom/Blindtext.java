/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.grandom;

import java.util.Random;

/**
 * Utility class which provides a blindtext.
 * 
 * @author ybl
 */
public final class Blindtext {
    /** The blind text the generator is based on. */
    private static final String BLIND_TEXT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr,"
            + " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,"
            + " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
            + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    /** The generated text will have at least this many characters. */
    private static final int CHARACTER_MIN_LENGTH = 40;
    /** We randomly add up to this many characters onto the minimum text length. */
    private static final int CHARACTER_RANGE = 10;
    
    /**
     * As a utility class, this is not supposed to be instantiated.
     */
    private Blindtext() {        
    }

    /**
     * Generate a random excerpt of our blind text.
     * 
     * @return generated blindtext.
     */
    public static String getRandomText() {
        Random randomGenerator = new Random();
        
        // Choose the number of characters
        int numOfCharacters = randomGenerator.nextInt(CHARACTER_RANGE);
        numOfCharacters += CHARACTER_MIN_LENGTH;
        
        // Choose where to start extracting from our base text
        int offset = randomGenerator.nextInt(
                BLIND_TEXT.length() - (CHARACTER_RANGE + CHARACTER_MIN_LENGTH));

        return BLIND_TEXT.substring(offset, offset + numOfCharacters).trim();
    }

}
