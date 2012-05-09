package de.cau.cs.kieler.klighd.examples

import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KForegroundColor
import de.cau.cs.kieler.core.krendering.KBackgroundColor
import com.google.inject.Inject

class KRenderingColors {
	
	@Inject
	extension KRenderingUtil
	
	def KForegroundColor getFgColor(String name) {
		val color = factory.createKForegroundColor;
		color.setColor(name);
		return color;
	}
	
	def KBackgroundColor getBgColor(String name) {
		val color = factory.createKBackgroundColor;
		color.setColor(name);
		return color;
	}
	
    /**
     * Creation of the color elements
     *  allows to refer to colors by name
     *  adds them to the diagram on demand
     */
	def setColor(KColor color, String name) {
    switch (name) {
       case "black" : {
         color.setRed(0);
         color.setGreen(0);
         color.setBlue(0);
       }
       case "blue" : {
        color.setRed(51);
        color.setGreen(51);
        color.setBlue(153);
       }
       case "lightBlue" : {
        color.setRed(150);
        color.setGreen(150);
        color.setBlue(255);
       }
       case "darkGray" : {
        color.setRed(105);
        color.setGreen(105);
        color.setBlue(105);
       }
       case "darkOrange" : {
        color.setRed(248);
        color.setGreen(179);
        color.setBlue(0);
       }
       case "error" : {
        color.setRed(255);
        color.setGreen(0);
        color.setBlue(0);
       }
       case "gray" : {
        color.setRed(190);
        color.setGreen(190);
        color.setBlue(190);
       }
       case "lemon" : {
        color.setRed(255);
        color.setGreen(250);
        color.setBlue(205);
       }
       case "red" : {
        color.setRed(255);
        color.setGreen(0);
        color.setBlue(0);
       }
       case "white" : {
        color.setRed(255);
        color.setGreen(255);
        color.setBlue(255);
       }
       case "yellow" : {
        color.setRed(255);
        color.setGreen(255);
        color.setBlue(0);
       }
       case "green" : {
        color.setRed(0);
        color.setGreen(255);
        color.setBlue(0);
       }
      }      
    }
	
}