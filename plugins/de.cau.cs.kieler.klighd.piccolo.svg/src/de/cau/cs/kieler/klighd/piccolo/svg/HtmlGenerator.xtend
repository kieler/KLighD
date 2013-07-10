package de.cau.cs.kieler.klighd.piccolo.svg

import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IResource

class HtmlGenerator {
    
    var IWorkspaceRoot root = null;
    
    def String toHtmlRoot(IWorkspaceRoot root) {
        this.root = root
        
        val html = root.projects.map [ project |
           "<li>" + project.name + "<ul>" + toHtml(project) + "</ul></li>" 
        ].join("\n")
        
        "<ul>" + html + "</ul>"
    }
    
    
    /**
     * IProject is a container
     */
    def dispatch String toHtml(IContainer container) {
                
        var html = container.members.map [ cont |
            "<li><a href='#' class='container' data-path='"+ cont.fullPath + "'>" + cont.name + "</a></li>"
        ].join("\n")
              
        html
    }
    
    def dispatch String toHtml(IFile file) {
        
        println("IFILE: " + file)
        
        ""
    }
    
    def dispatch String toHtml(IResource r) {
        println("tt " + r.getClass())
    }
    
    
    
}