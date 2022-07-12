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
package de.cau.cs.kieler.klighd.graphanalysis;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.klighd.krendering.KText;

/**
 * @author mka
 *
 */
public class Readability implements IZSampleable<Double>{
    
    private KText text;
    private double textScale;
    private double scaleLimit;
    
    public Readability(KText text, double textScale, double scaleLimit) {
        
        if (textScale < 0 || scaleLimit < 0) {
            throw new IllegalArgumentException("textScale and scaleLimit must be larger than 0");
        }
        this.text = text;
        this.textScale = textScale;
        this.scaleLimit = scaleLimit;
    }
    
    public Double getZSample(double z) {
        if (z < 0 || z > 1) {
            throw new IllegalArgumentException("z must be between 0 and 1");
        }
        double dampening = 1; // TODO: this value may need to be tweaked or automatically tweakable, useful for visualizing what readability represents 
        double steepness = this.normalizedScaleLimit() / dampening;
        double res = -steepness * Math.pow(z - zOpt(),2) + 1;
        //System.out.println("z: " + z + ", zOpt: " + zOpt() + " steepness: " + steepness + " ==> " + res);
        return Math.max(0, res);
    }
    
    private double zOpt() {
        double res;
        if (this.scaleLimit > 1) {
            res = (1/this.textScale - this.scaleLimit) / (1-this.scaleLimit);
        } else {
            res = (1/this.textScale - 1) / (this.scaleLimit - 1);
        }
        
//        System.out.println(this.scaleLimit + " " + this.textScale);
//        System.out.println(res);
        return res;
    }
    
    // returns the normalized scale limit, when we are only interested in the magnitude of the scaling range and not 
    // in the direction
    private double normalizedScaleLimit() {
        return Math.exp(Math.abs(Math.log(scaleLimit)));
    }
    
    public KText getText() {
        return this.text;
    }
    
    public String toString() {
        return "(" + text.getText() + ", " + textScale +", " + scaleLimit + ")";
    }
    
    public String plot(double stepSize) {
        List<Double> yPoints = new ArrayList<>();
        int sampleCount = (int) (1 / stepSize);
        for (int i = (int) 0; i <= sampleCount; i++) {
            double z = (float) i / sampleCount;
            yPoints.add(this.getZSample(z));
            
        }
        return yPoints.toString();
    }
    
    public double getTextScale() {
        return this.textScale;
    }

}
