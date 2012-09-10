package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KForegroundColor
import de.cau.cs.kieler.core.krendering.KBackgroundColor
import de.cau.cs.kieler.core.krendering.KRenderingFactory

class KColorExtensions {
	
    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE

    def KColor getColor(String name) {
            // since KColor is abstract
            return name.fgColor;
    }

	def KForegroundColor getFgColor(String name) {
		return renderingFactory.createKForegroundColor() => [
		    it.setColor(name);
		];
	}
	
	def KBackgroundColor getBgColor(String name) {
		return renderingFactory.createKBackgroundColor() => [
		    it.setColor(name);
		];
	}
	
    /**
     * Creation of the color elements
     *  allows to refer to colors by name
     *  adds them to the diagram on demand
     */
	def private setColor(KColor color, String name) {
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
            case "gray" : {
                color.setRed(190);
                color.setGreen(190);
                color.setBlue(190);
            }
            case "green" : {
                color.setRed(0);
                color.setGreen(255);
                color.setBlue(0);
            }
            case "lemon" : {
                color.setRed(255);
                color.setGreen(250);
                color.setBlue(205);
            }
            case "lightBlue" : {
                color.setRed(150);
                color.setGreen(150);
                color.setBlue(255);
            }
            case "lightPink" : {
                color.setRed(255);
                color.setGreen(182);
                color.setBlue(193);   
            }
            case "lightSkyBlue" : {
                color.setRed(135);
                color.setGreen(206);
                color.setBlue(250);
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
        }      
    }
}