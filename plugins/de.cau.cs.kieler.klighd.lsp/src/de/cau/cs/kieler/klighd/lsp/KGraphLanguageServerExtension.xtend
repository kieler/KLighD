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
import com.google.inject.Provider
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.model.GetOptionParam
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import de.cau.cs.kieler.klighd.lsp.model.SetOptionParam
import io.typefox.sprotty.api.ActionMessage
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.api.RequestModelAction
import io.typefox.sprotty.server.xtext.ILanguageAwareDiagramServer
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer
import io.typefox.sprotty.server.xtext.ide.IdeLanguageServerExtension
import java.util.ArrayList
import java.util.List
import java.util.concurrent.CompletableFuture
import org.eclipse.xtext.util.CancelIndicator

import static io.typefox.sprotty.api.ServerStatus.Severity.*

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
class KGraphLanguageServerExtension extends IdeLanguageServerExtension 
    implements IDiagramOptionsLanguageServerExtension {
    
    /**
     * The {@link Provider} to call an injected {@link KGraphDiagramGenerator} to generate {@link KNode KGraphs} and 
     * {@link SKGraph}s from that.
     */
    @Inject
    Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    /**
     * Stores data for the generation of diagrams.
     */
    @Inject
    KGraphDiagramState diagramState
	
	override protected initializeDiagramServer(IDiagramServer server) {
		super.initializeDiagramServer(server)
		val kGraphAware = server as KGraphAwareDiagramServer
		kGraphAware.needsServerLayout = true
		kGraphAware.needsClientLayout = false
		LOG.info("Created diagram server for " + server.clientId)
	}
	
	override didClose(String clientId) {
	    // clear the diagramState of this client id additional to the default use of this method.
	    synchronized (diagramState) {
	        diagramState.remove(clientId)
	    }
		super.didClose(clientId)
		LOG.info("Removed diagram server for " + clientId)
	}
	
	override accept(ActionMessage message) {
        val server = getDiagramServer(message.clientId)
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
	
	override protected doUpdateDiagrams(String path, List<? extends ILanguageAwareDiagramServer> diagramServers) {
        if (diagramServers.empty) {
            return CompletableFuture.completedFuture(null)
        }
        return path.doRead [ context |
            val status = context.resource.shouldGenerate(context.cancelChecker)
            val uri = context.resource.URI.toString
            var Object snapshotModel = null
            synchronized(diagramState) {
                snapshotModel = diagramState.getSnapshotModel(path)
            }
            val model = if (snapshotModel === null) {
                            context.resource.contents.head
                        } else {
                            snapshotModel
                        }
            val cancelChecker = context.cancelChecker
            
            return (diagramServers as List<KGraphAwareDiagramServer>).map [ server |
                server -> {
                    server.status = status
                    if (status.severity !== ERROR) {
                        createModel(server, model, uri, cancelChecker)
                    } else {
                        null
                    }
                }
            ]
        ].thenAccept [ resultList |
            // call the text size estimation on the diagram server for which a new diagram got created.
            resultList.filter[value !== null].forEach[key.requestTextSizesAndUpdateModel(value)]
        ].exceptionally [ throwable |
            LOG.error('Error while processing build results', throwable)
            return null
        ]
    }
    
    protected def createModel(KGraphAwareDiagramServer server, Object model, String id, CancelIndicator cancelChecker) {
        // retrieve the view context that may contain updated options for the KGraphDiagramGenerator.
        var ViewContext oldVC = null
        synchronized(diagramState) {
            oldVC = diagramState.getKGraphContext(id)    
        }
        // translate the resource to the KGraph model and store it in the diagram state.
        val kGraphContext = KGraphDiagramGenerator.translateModel(model, oldVC)
        synchronized (diagramState) {
            diagramState.putURIString(server.clientId, id)
            diagramState.putKGraphContext(id, kGraphContext)
        }
        
        // generate the SGraph model from the KGraph model and store every later relevant part in the
        // diagram state.
        val diagramGenerator = diagramGeneratorProvider.get
        val sGraph = diagramGenerator.toSGraph(
            kGraphContext.viewModel, id, cancelChecker)
        synchronized (diagramState) {
            diagramState.putKGraphToSModelElementMap(id, diagramGenerator.getKGraphToSModelElementMap)
            diagramState.putTexts(id, diagramGenerator.getModelLabels)
            diagramState.putTextMapping(id, diagramGenerator.getTextMapping)
        }
        // finally, match the diagram server with the generated SGraph by returning the SGraph.
        sGraph
    }
    
    override getOptions(GetOptionParam param) {
        return param.uri.doRead [ context |
            synchronized (diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // A diagram for this file is currently not opened, so no options can be shown.
                    return null
                }
                return viewContext.displayedSynthesisOptions
            }
        ]
    }
    
    override setOptions(SetOptionParam param) {
        return param.uri.doRead [ context |
            synchronized(diagramState) {
                val ViewContext viewContext = diagramState.getKGraphContext(context.resource.URI.toString)
                if (viewContext === null) {
                    // The diagram has already been closed
                    return "ERR"
                }
                val synthesisOptions = viewContext.displayedSynthesisOptions
                for (paramSynthesisOption : param.synthesisOptions) {
                    // Translated type strings for the types Separator (3) and Category (4). Do not try to write these options. 
                    if (!("3".equals(paramSynthesisOption.type) || "4".equals(paramSynthesisOption.type))) {
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
                }
                this.doUpdateDiagrams(#[context.resource.URI])
                return "OK"
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
            if (newOption instanceof SynthesisOption) {
                viewContext.configureOption(option, value)
                return
            }
            // Every number (including int) will be represented as a double in a possible JavaScript server.
            // Because of that, try to match the new value to an int.
            if (value instanceof Double
                && Math.rint(value as Double) == value
            ) {
                val intValue = Math.round(value as Double)
                newOption = option.values.findFirst[toString.equals(intValue.toString)]
                if (newOption instanceof SynthesisOption) {
                    viewContext.configureOption(option, value)
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
            // Range options are always declared as numbers, but floating point numbers are always stored as floats.
            // If a double value comes back, convert it to a float.
            if (value instanceof Double) {
                viewContext.configureOption(option, value.floatValue)
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
    def showSnapshot(String uri, Object model, CancelIndicator cancelIndicator) {
        val clientId = 'widget-diagram' // TODO: send this with the request
        if (diagramServers.empty) {
            return "ERR"
        }
        // check if some diagram server already has a diagram for this uri.
        val closeClientIds = new ArrayList
        diagramServers.forEach[ cId, diagramServer |
            if (clientId.equals(cId)) {
                closeClientIds.add(cId)
            }
        ]
        // if there is one, close it and open the diagram with a new, initialized diagram server
        closeClientIds.forEach[this.didClose(it)]
        val diagramServer = this.getDiagramServer(clientId)
        if (diagramServer instanceof KGraphAwareDiagramServer) {
            synchronized(diagramState) {
                diagramState.putSnapshotModel(uri, model)
            }
            diagramServer.initializeOptions(#{
                LanguageAwareDiagramServer.OPTION_SOURCE_URI -> uri
            })
            // with that new diagram server, do a similar procedure to generate a diagram as for usual diagrams (except,
            // use the 'model' as its model.
            val sGraph = this.createModel(diagramServer, model, uri, cancelIndicator)
            if (sGraph !== null) {
                diagramServer.requestTextSizesAndUpdateModel(sGraph)
            }
            return "OK"
        }
        return "ERR"
    }
}
