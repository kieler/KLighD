package de.cau.cs.kieler.klighd.piccolo.svg.server

import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.IWorkspaceRoot
import java.io.File

class HtmlGenerator {
    
    var IWorkspaceRoot root = null;
    var File rootFile = null;
    
    def String toHtmlRoot(IWorkspaceRoot root) {
        this.root = root
        
        val html = root.projects.map [ project |
            toHtml(project) 
        ].join("\n")
        
        "<ul id='tree'>" + html + "</ul>"
    }
    
    def String toHtmlRoot(File root) {
        this.rootFile = root 
           
        val html = root.listFiles.map [ project |
            toHtml(project) 
        ].join("\n")
        
        "<ul id='tree'>" + html + "</ul>"
    }
    
    def dispatch String toHtml(File f) {
    	
    	if(f.directory) {
    		var String html = "<li><a href='#' class='folder' data-path='"+ f.relative + "'>" + f.name + "</a>" 
           
        html = html + "<ul>" + f.listFiles.map [ cont |
            toHtml(cont)
        ].join("\n")
        html = html + "</ul></li>";

        return html
    	} else {
    		 val html = "<li><a href='/resource" + f.relative + "' class='file' data-path='"+ f.relative + "'>" + f.name + "</a></li>"
              
        html
    	}
    	
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
    
    
    def String relative(File child) {
    	child.absolutePath.replace(rootFile.absolutePath, "").replace(File::separator, "/")
    }
    
    
    
    def String permaLink(String svgData) {
	'''
	<html encoding='UTF8'>
		<head>
			<meta charset='utf-8'>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
			<script src="/js/jquery.mousewheel.js"></script>
			<script src="/js/jquery.svg.js"></script>
			<script src="/js/jquery.svg.extras.js"></script>
			<script src="/js/jquery.event.drag-2.2.js"></script>
			<style type='text/css'> 
				svg { width:100%; height:100%; } body { width:100%; height:100%; } 
			</style>
		</head>
		<body>
			<div id="data">
				«svgData»
			</div>
			<script>
				$('#data').svg();
				$('#data').addScalability();
				$('#data').addDraggable();
			</script>
		</body>
	</html>
	'''
    }
    	
    
}