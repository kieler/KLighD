/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kgraph.language.server

import com.google.inject.Inject
import com.google.inject.Singleton
import java.util.List
import org.apache.log4j.Logger
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.concurrent.RequestManager

/**
 * Implements methods to extend the LSP to allow language registration.
 * 
 * @author sdo
 * 
 */
 @Singleton
class RegistrationLanguageServerExtension implements LanguageRegistrationExtension {
    
    /**
     * Will be written to after the Language Server connects
     */
     
    public static List<String> registeredLanguageExtensions = newArrayList

    protected static val LOG = Logger.getLogger(RegistrationLanguageServerExtension)
    
    @Inject @Accessors(PUBLIC_GETTER) RequestManager requestManager

    protected extension ILanguageServerAccess languageServerAccess
    
    def initialize(ILanguageServerAccess access) {
        this.languageServerAccess = access
    }
    def ILanguageServerAccess getLanguageServerAccess() {
        return languageServerAccess
    }
    
    override getLanguages() {
        val kgtKeywords = #['absolutePos','actions','anchor','areaData','background','bevel','bold','bottom',
        'bottomRightAnchor','center','chord','clipShape','columns','custom','dash','dashOffset','dashPattern',
        'decoratorData','dot','double','doubleClick','error','flat','flexibleHeight','flexibleWidth','fontName',
        'fontSize','foreground','grid','gridData','hAlign','height','horizontalAlignment','horizontalMargin','insets',
        'invisible','italic','junction','karc','kchildArea','kcustomRendering','kedge','kellipse','kgraph','kimage',
        'klabel','knode','kpolygon','kpolyline','kport','krectangle','krendering','krenderingLibrary','kroundedPolyline',
        'kroundedRectangle','kspline','kstylesTemplate','ktext','left','lineCap','lineJoin','lineStyle','lineWidth',
        'link','middleDoubleClick','middleSingleClick','middleSingleOrMultiClick','minCellHeight','minCellWidth',
        'minimalHeight','minimalWidth','miter','modifier','none','null','open','pie','pointData','points','pos','propagate',
        'properties','reference','referencePoint','relativePos','right','rotateWithLine','rotation','round','scale',
        'selection','shadow','single','singleClick','singleOrMultiClick','size','solid','square','squiggle','styles',
        'top','topLeftAnchor','underline','vAlign','verticalAlignment','verticalMargin','width','x','xoffset','y',
        'yoffset']
        val elkKeywords = #["bends","edge","end","false","graph","incoming","label","layout","node","null","outgoing",
        "port","position","section","size","start","true"]
        val languages = newArrayList(new Language("kgt", "KGraph", kgtKeywords), new Language("elkt", "Elk Graph", elkKeywords))
        
        for (language : languages) {
            registeredLanguageExtensions.add(language.id)
        }
        return requestManager.runRead[ cancelIndicator |
            languages
        ]
    }
}

@Data
class Language {
    String id
    String name
    List<String> keywords
}