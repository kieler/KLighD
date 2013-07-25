/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.swt.widgets.Shell;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.svg.PiccoloSVGBrowseViewer;

/**
 * @author uru
 * 
 */
public class KlighdningHTTPHandler extends AbstractHandler {

    private Shell shell;

    private File docRoot;

    private PiccoloSVGBrowseViewer getViewer;

    private HtmlGenerator gen = new HtmlGenerator();

    /**
     * 
     */
    public KlighdningHTTPHandler(final Shell shell, final File docRoot) {
        this.shell = shell;
        this.docRoot = docRoot;
        this.getViewer = new PiccoloSVGBrowseViewer(shell);
    }

    /**
     * {@inheritDoc}
     */
    public void handle(String target, final Request baseRequest, final HttpServletRequest request,
            final HttpServletResponse response) throws IOException, ServletException {

        // System.out.println(target + " " + request);

        // IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        // System.out.println(docRoot.getPath());

        if (target.startsWith("/content")) {

            // String html = new HtmlGenerator().toHtmlRoot(root);
            String html = gen.toHtmlRoot(docRoot);

            response.setContentType("text/html;charset=utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println(html);

        } else if (target.startsWith("/resource")) {

            String path = target.replace("/resource", "");

            ResourceSet rs = new ResourceSetImpl();

            // MOML
            Map<String, Boolean> parserFeatures = Maps.newHashMap();
            parserFeatures.put("http://xml.org/sax/features/validation", //$NON-NLS-1$
                    Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                    Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                    Boolean.FALSE);

            rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
            rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
            rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .put("xml", new MomlResourceFactoryImpl());

            System.out.println(path);
            final Resource r =
                    rs.getResource(URI.createFileURI(new File(docRoot, path).getAbsolutePath()),
                            true);

            System.out.println(r);

            System.out.println("PRE");
            shell.getDisplay().syncExec(new Runnable() {

                public void run() {
                    System.out.println("INNER");
                    getViewer.getCanvas().setBounds(0, 0, 870, 600);

                    // translate and set the model
                    try {
                        KNode model =
                                LightDiagramServices.translateModel(new ViewContext(), r
                                        .getContents().get(0));
                        getViewer.setModel(model, true);

                        // apply the perma link infos
                        String perma = request.getParameterMap().get("perma")[0];
                        Set<String> fragments = Sets.newHashSet(Splitter.on("$").split(perma));
                        System.out.println(fragments);
                        getViewer.applyPermalink(fragments);
                        // FIXME perma is not coming through
                        // request:
                        // /resource/ptolemy/ci/ci_queuetest1.moml?perma=baseModel.kgraph%23//@children.0$baseModel.kgraph%23//@children.2

                        String transform = request.getParameterMap().get("transform")[0];
                        System.out.println(transform);
                        getViewer.setSvgTransform(transform);

                        SVGLayoutProvider.getInstance().layout(getViewer);

                    } catch (Exception e) {
                        // catch any error
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        try {
                            response.getWriter().println(
                                    "ERROR: Unable to handle the selected model.");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            });
            System.out.println("AFTER");

            // answer the request
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            // pass the svg
            String svg = SVGLayoutProvider.getInstance().getSVG(getViewer);
            // response.getWriter().println(gen.permaLink(getSVG(getViewer)));
            response.getWriter().println(gen.permaLink(svg));

        } else if (target.startsWith("/refreshGit")) {

            // create a process that executes git pull
            // the git bin has to be within the system path
            ProcessBuilder pb = new ProcessBuilder("git", "pull");
            pb.directory(docRoot);

            Process run = pb.start();
            try {
                // wait for the git process to finish
                run.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // answer the request
            String success = CharStreams.toString(new InputStreamReader(run.getInputStream()));
            if (!Strings.isNullOrEmpty(success)) {
                // send the error
                response.getWriter().println(success);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                String error = CharStreams.toString(new InputStreamReader(run.getErrorStream()));
                response.getWriter().println(error);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            // general info
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf8");
            baseRequest.setHandled(true);
        }

    }
}
