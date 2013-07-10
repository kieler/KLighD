package de.cau.cs.kieler.klighd.piccolo.svg

import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.IWorkspaceRoot

class HtmlGenerator {
    
    var IWorkspaceRoot root = null;
    
    def String toHtmlRoot(IWorkspaceRoot root) {
        this.root = root
        
        val html = root.projects.map [ project |
            toHtml(project) 
        ].join("\n")
        
        "<ul id='tree'>" + html + "</ul>"
    }
    
    
    /**
     * IProject is a container
     */
    def dispatch String toHtml(IContainer container) {
            
        var String html = "<li><a href='#' class='folder' data-path='"+ container.fullPath + "'>" + container.name + "</a>" 
           
        html = html + "<ul>" + container.members.map [ cont |
            toHtml(cont)
        ].join("\n")
        html = html + "</ul></li>";

        html
    }
    
    def dispatch String toHtml(IFile file) {
        
        val html = "<li><a href='#' class='file' data-path='"+ file.fullPath + "'>" + file.name + "</a></li>"
              
        html
    }
    
    def dispatch String toHtml(IResource r) {
        println("tt " + r.getClass())
    }
    
    
    
}