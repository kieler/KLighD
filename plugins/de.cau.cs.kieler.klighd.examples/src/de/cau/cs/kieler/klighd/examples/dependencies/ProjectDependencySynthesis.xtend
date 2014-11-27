package de.cau.cs.kieler.klighd.examples.dependencies

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klay.layered.properties.Properties

/**
 * Very basic synthesis that presents a dependency graph for the passed project information.
 * 
 * @author uru
 */
class ProjectDependencySynthesis extends AbstractDiagramSynthesis<ProjectDependencyInformation> {
    
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    //@Inject extension KPortExtensions
    //@Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    
    private static val SynthesisOption EXT_DEPS = SynthesisOption::createCheckOption("External Dependencies", false);
    
    override public getDisplayedSynthesisOptions() {
        return ImmutableList.of(EXT_DEPS);
    }
    
    override public getDisplayedLayoutOptions() {
        return ImmutableList.of(
            specifyLayoutOption(LayoutOptions.ALGORITHM, null),
            specifyLayoutOption(LayoutOptions.EDGE_ROUTING, EdgeRouting.values().drop(1).sortBy[ it.name ]),
            specifyLayoutOption(Properties.MERGE_EDGES, null)
        );
    }
    
    override transform(ProjectDependencyInformation model) {
       
        return model.createNode => [ root |
            
            // first create all project nodes
            model.projects.forEach[ project |

                project.createNode.associateWith(project) => [ projectNode |
                    
                    projectNode.addRectangle => [ rect |
                        rect.addText(project.name).addPadding(5)
                        
                        rect.addSingleClickAction("de.cau.cs.kieler.klighd.examples.dependencies.hightlightEdges")
                        
                        if (project.name.contains("de.cau.cs.kieler")) {
                        	rect.background = "lightgray".color
                        }
                    ]
                    
                    root.children += projectNode
                ]
            ]
            
            // now add the links between the projects
            model.projects.forEach[ project |
                
                val deps = model.getDependencies(project)
                println(deps)
                // internal dependencies
                deps.key.forEach [ dep |
                    // create an dependency edge           
                    createEdge => [ edge |
                        edge.source = project.node
                        edge.target = dep.node
                        edge.addPolyline => [ pl |
                            pl.addHeadArrowDecorator
                            pl.addJunctionPointDecorator
                        ]
                    ]
                ]
                
                // external deps
                if (EXT_DEPS.booleanValue) {
                    deps.value.forEach [ dep |
                        
                        // first create a node for this
                        dep.createNode.associateWith(dep) => [ extProject |
                            
                            extProject.addRectangle => [ rect |
                                rect.addText(dep).addPadding(5)
                                
                                  rect.addSingleClickAction("de.cau.cs.kieler.klighd.examples.dependencies.hightlightEdges")
                                  
                                  if (dep.contains("de.cau.cs.kieler")) {
			                        rect.background = "lightgray".color
			                      } 
			                      
			                      if (dep.contains("gmf") || dep.contains("kivi")) {
			                        rect.background = "red".color
			                      }
			                      
			                      if (dep.contains("ui")) {
			                        rect.background = "brown".color
			                      }
                            ]
                            
                            root.children += extProject
                        ]
                        
                        // create an dependency edge           
                        createEdge => [ edge |
                            edge.source = project.node
                            edge.target = dep.node
                             edge.addPolyline => [ pl |
                                pl.addHeadArrowDecorator
                                pl.addJunctionPointDecorator
                            ]
                        ]
                    ]
                }
            ]
            
        ]
       
    }
    
    def addPadding(KRendering r, int size) {
        r.setAreaPlacementData.from(LEFT, size, 0, TOP, size, 0).to(RIGHT, size, 0, BOTTOM, size, 0)
    }
    
}