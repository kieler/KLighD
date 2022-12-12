package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import org.eclipse.sprotty.Action
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import javax.inject.Singleton

@Singleton
class ScchartStructuredProgrammingActionHandler implements ILanguageServerExtension {
    
    @Accessors KGraphLanguageClient client;

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer
    
    
    @Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
    HashMap<String, Class<?>> supportedMessages = newHashMap(
        DeleteAction.KIND -> DeleteAction
    )
    
    def handle(Action action, String clientId, KGraphDiagramServer server) {
        println(action.kind + (action as DeleteAction).toDelete.get(0))
    }
    
    override initialize(ILanguageServerAccess access) {
        // not needed ?    
    }
    
    
}