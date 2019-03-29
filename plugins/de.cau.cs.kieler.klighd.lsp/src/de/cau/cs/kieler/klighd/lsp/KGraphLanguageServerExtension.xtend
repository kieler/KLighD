/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.inject.Inject
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.model.GetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.SetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.ValuedSynthesisOption
import java.util.ArrayList
import java.util.Collection
import org.eclipse.sprotty.ActionMessage
import org.eclipse.sprotty.DiagramOptions
import org.eclipse.sprotty.RequestModelAction
import org.eclipse.sprotty.xtext.ls.SyncDiagramLanguageServer
import org.eclipse.xtext.util.CancelIndicator

/**
 * Language server extension that implements functionality for the generation of diagrams and handling of their diagram
 * servers.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLanguageServerExtension.xtend">
 *      YangLanguageServerExtension</a>
 */
@Singleton
class KGraphLanguageServerExtension extends SyncDiagramLanguageServer
    implements IDiagramOptionsLanguageServerExtension {
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
	
	// TODO: is non-existant. Need this code somewhere else?
//	override protected initializeDiagramServer(IDiagramServer server) {
//		super.initializeDiagramServer(server)
//		val kGraphDiagramServer = server as KGraphDiagramServer
//		kGraphDiagramServer.needsServerLayout = true
//		kGraphDiagramServer.needsClientLayout = false
//		LOG.info("Created diagram server for " + server.clientId)
//	}
	
	override didClose(String clientId) {
	    // clear the diagramState of this client id additional to the default use of this method.
	    synchronized (diagramState) {
	        diagramState.remove(clientId)
	    }
		super.didClose(clientId)
//		LOG.info("Removed diagram server for " + clientId)
	}
	
	override accept(ActionMessage message) {
//        val server = getDiagramServer(message.clientId)
        val server = diagramServerManager.getDiagramServer('keith-diagram', message.clientId) // TODO: This should not be hard coded!
        // if a diagram server is requested for the same client, but a different source file, then close the old server.
        if (message.action instanceof RequestModelAction
            && !server.options.empty // if the server does not have options yet, the server has not been used yet and
            // does not need to be relaunched.
            && !(message.action as RequestModelAction).options.get("sourceUri")
                .equals(server.options.get("sourceUri"))) {
                didClose(message.clientId)
            }
        super.accept(message)
    }
    
    override getOptions(GetOptionParam param) {
        return languageServerAccess.doRead(param.uri) [ context |
//        return param.uri.doRead [ context |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // A diagram for this file is currently not opened, so no options can be shown.
                    return null
                }
                // TODO: what about the displayedLayoutOptions and the displayedActions?
                val synthesisOptions = new ArrayList<ValuedSynthesisOption>
                val recentSynthesisOptions = diagramState.recentSynthesisOptions
                for (option : viewContext.displayedSynthesisOptions) {
                    val currentValue = recentSynthesisOptions.get(option)
                    synthesisOptions.add(new ValuedSynthesisOption(option, currentValue))
                }
                return synthesisOptions
            }
        ]
    }
    
    override setOptions(SetOptionParam param) {
        return languageServerAccess.doRead(param.uri) [ context |
//        return param.uri.doRead [ context |
            synchronized(diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // The diagram has already been closed
                    return "ERR"
                }
                val synthesisOptions = viewContext.displayedSynthesisOptions
                for (paramSynthesisOption : param.synthesisOptions) {
                    // The options in the parameter are a newly generated object, so it needs to be matched to the 
                    // option of the viewContext.
                    val synthesisOption = synthesisOptions.findFirst [
                        System.identityHashCode(it) === paramSynthesisOption.sourceHash
                    ]
                    if (synthesisOption === null) {
                        // A changed option cannot be found.
                        return "ERR"
                    }
                    else {
                        configureOption(synthesisOption, paramSynthesisOption.currentValue, viewContext)
                    }
                }
//                this.doUpdateDiagrams(#[context.resource.URI])
                if (diagramUpdater instanceof KGraphDiagramUpdater) {
                    // TODO: remove this reflection access when I find a better way to do this.
                    val callThisMethod = KGraphDiagramUpdater.getMethod("updateDiagrams", Collection)
                    callThisMethod.accessible = true
                    callThisMethod.invoke(#[context.resource.URI])
//                    (diagramUpdater as KGraphDiagramUpdater).updateDiagrams(#[context.resource.URI])
                    return "OK"
                }
                return "ERR"
            }
        ]
    }
    
    /**
     * configures the {@code option} of the {@code viewContext} with the new {@code value} while regarding some special
     * cases that arise when using an arbitrary Object as the value.
     */
    def void configureOption(SynthesisOption option, Object value, ViewContext viewContext) {
        if (option.isChoiceOption) {
            // Choice options are predefined with non-primitive types, so try to match the
            // paramSynthesisOption with one of the options available for this choice.
            if (option.values.contains(value)) {
                // If the synthesis option values directly contain the new one from the parameter, just
                // overwrite it.
                viewContext.configureOption(option, value)
                return
            }
            // If the string representation matches between an option value and the new value, use that.
            var newOption = option.values.findFirst[toString.equals(value.toString)]
            if (option.values.contains(newOption)) {
                viewContext.configureOption(option, newOption)
                return
            }
            if (newOption instanceof String) {
                println("Here")
            }
            // Every number (including int) will be represented as a double in a possible JavaScript server.
            // Because of that, try to match the new value to an int.
            if (value instanceof Double
                && Math.rint(value as Double) == value
            ) {
                val intValue = Math.round(value as Double)
                newOption = option.values.findFirst[toString.equals(intValue.toString)]
                if (option.values.contains(newOption)) {
                    viewContext.configureOption(option, newOption)
                    return
                }
                // try to view the option as an Enum. If the ordinal matches, take that option.
                for (optionValue: option.values) {
                    if (optionValue instanceof Enum<?>
                        && (optionValue as Enum<?>).ordinal == intValue
                    ) {
                        viewContext.configureOption(option, optionValue)
                        return
                    }
                }
            }
        } else if (option.isRangeOption) {
            val lowerBound = option.range.first
            val upperBound = option.range.second
            val stepSize = option.stepSize
            val initialValue = option.initialValue as Number
            if (lowerBound.equals(lowerBound.intValue())
                && upperBound.equals(upperBound.intValue())
                && stepSize.equals(stepSize.intValue())
                && initialValue.equals(initialValue.intValue())) {
                // The option contains an Integer
                viewContext.configureOption(option, Integer.parseInt(value as String))
                return
            } else {
                // The option contains a Float
                viewContext.configureOption(option, Float.parseFloat(value as String))
                return
            }
        } else {
            viewContext.configureOption(option, value)
            return
        }
    }
    
    /**
     * Creates and sends the diagram for an arbitrary snapshot object for any source model to the client.
     */
    def showSnapshot(String uri, String clientId, Object model, CancelIndicator cancelIndicator, boolean update) {
        if (!update) {
            // check if some diagram server already has a diagram for this uri.
            val closeClientIds = new ArrayList
            diagramServerManager.diagramServers.forEach[ diagramServer |
//            diagramServers.forEach[ cId, diagramServer |
                if (clientId.equals(diagramServer.clientId)) {
                    closeClientIds.add(diagramServer.clientId)
                }
            ]
            // if there is one, close it and open the diagram with a new, initialized diagram server
            closeClientIds.forEach[this.didClose(it)]
        }
        
//        val diagramServer = this.getDiagramServer(clientId)
        val diagramServer = diagramServerManager.getDiagramServer('keith-diagram', clientId)
        if (diagramServer instanceof KGraphDiagramServer) {
            synchronized(diagramState) {
                diagramState.putSnapshotModel(uri, model)
            }
            diagramServer.initializeOptions(#{
                DiagramOptions.OPTION_SOURCE_URI -> uri
            })
            // with that new diagram server, do a similar procedure to generate a diagram as for usual diagrams (except,
            // use the 'model' as its model.
            if (diagramUpdater instanceof KGraphDiagramUpdater) {
                val sGraph = (diagramUpdater as KGraphDiagramUpdater).createModel(diagramServer, model, uri, cancelIndicator)
                if (sGraph !== null) {
                    diagramServer.requestTextSizesAndUpdateModel(sGraph)
                }
                return "OK"
            }
        }
        return "ERR"
    }
}
