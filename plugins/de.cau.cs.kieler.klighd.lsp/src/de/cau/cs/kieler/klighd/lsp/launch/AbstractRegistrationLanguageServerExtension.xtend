/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2020 by
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
package de.cau.cs.kieler.klighd.lsp.launch

import com.google.inject.Inject
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.ide.server.concurrent.RequestManager

/**
 * Implements methods to extend the LSP to register languages and their keywords.
 * 
 * @author sdo, nre
 * 
 */
abstract class AbstractRegistrationLanguageServerExtension implements ILanguageServerExtension, CommandExtension {
    
    /**
     * The registered language extensions. Will be written to after the language server connects.
     */
    public static List<String> registeredLanguageExtensions = newArrayList
    
    @Inject
    @Accessors(PUBLIC_GETTER)
    RequestManager requestManager

    extension ILanguageServerAccess languageServerAccess
    
    override initialize(ILanguageServerAccess access) {
        this.languageServerAccess = access
    }
    def ILanguageServerAccess getLanguageServerAccess() {
        return languageServerAccess
    }
    
    override getLanguages() {
        val languages = languageExtensions
        for (language : languages) {
            registeredLanguageExtensions.add(language.id)
        }
        return requestManager.runRead[ cancelIndicator |
            languages
        ]
    }
    
    def abstract List<Language> getLanguageExtensions()
}

@Data
class Language {
    String id
    String name
    List<String> keywords
}