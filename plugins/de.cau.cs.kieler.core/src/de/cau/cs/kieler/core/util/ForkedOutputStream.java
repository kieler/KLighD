/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * An output stream that can be used to send the same output to multiple
 * output streams.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class ForkedOutputStream extends OutputStream {

    /** the list of output streams to which this stream will write. */
    private List<OutputStream> outputStreams;
    
    /**
     * Creates a forked output stream that writes to all output streams
     * in the given list.
     * 
     * @param theoutputStreams list of output streams
     */
    public ForkedOutputStream(final List<OutputStream> theoutputStreams) {
        this.outputStreams = theoutputStreams;
    }
    
    /**
     * Creates a forked output stream that writes to the two given output
     * streams.
     * 
     * @param stream1 an output stream
     * @param stream2 an output stream
     */
    public ForkedOutputStream(final OutputStream stream1, final OutputStream stream2) {
        this.outputStreams = new LinkedList<OutputStream>();
        outputStreams.add(stream1);
        outputStreams.add(stream2);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void write(final int b) throws IOException {
        for (OutputStream stream : outputStreams) {
            stream.write(b);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void flush() throws IOException {
        for (OutputStream stream : outputStreams) {
            stream.flush();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        for (OutputStream stream : outputStreams) {
            stream.close();
        }
    }

}
