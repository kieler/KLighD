/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.graphanalysis;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author mka
 *
 */
public class ReadabilityPyPlotter {
    
    public static void plotAllReadabilities(List<Readability> readabilities, double stepSize, Writer writer) throws IOException {
        
        List<Double> xPoints = new ArrayList<>();
        HashSet<Double> uniqueTextScales = new HashSet<>();
        
        int sampleCount = (int) (1 / stepSize);
        for (int i = (int) 0; i <= sampleCount; i++) {
            double z = (float) i / sampleCount;
            xPoints.add(z);            
        }
        
        writer.write("import matplotlib.pyplot as plt\n");
        
        writer.write("plt.xlabel('z Level')\n");
        writer.write("plt.ylabel('Readability')\n");
        
        writer.write("xPoints = ");
        writer.write(xPoints.toString());
        writer.write("\n");
        
        int i = 0;
        for (Readability readability : readabilities) {
            if (uniqueTextScales.contains(readability.getTextScale())) {
                // no need to draw identical plots more than once
                continue;
            } else {
                uniqueTextScales.add(readability.getTextScale());
                writer.write("y" + i + " = ");
                writer.write(readability.plot(stepSize));
                writer.write("\n");
                writer.write("plt.plot(xPoints,y" + i +")\n");
                i++;
            }
        }
        
        writer.write("plt.show()");
        System.out.println("Unique Text Scales: " + uniqueTextScales.size());
        
    }

}
