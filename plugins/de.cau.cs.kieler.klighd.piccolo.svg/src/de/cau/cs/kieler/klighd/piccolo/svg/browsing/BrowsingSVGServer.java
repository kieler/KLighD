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
package de.cau.cs.kieler.klighd.piccolo.svg.browsing;

/**
 * @author uru
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.svg.HtmlGenerator;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdSVGGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.svg.PiccoloSVGViewer;
import edu.umd.cs.piccolo.PCamera;

@SuppressWarnings("restriction")
public class BrowsingSVGServer extends Server {

    private static BrowsingSVGServer INSTANCE;

    private Shell shell;
    private File docRoot;

    private boolean verbose;

    // WebSocket Queue
    private ConcurrentLinkedQueue<SVGSendingWebSocket> broadcast =
            new ConcurrentLinkedQueue<SVGSendingWebSocket>();

    // KlighD and Layout facilities
    private PiccoloSVGBrowseViewer viewer;
    private KlighdLayoutManager mng = new KlighdLayoutManager();

    // currently shown model per connection
    // TODO
    private Map<Connection, KNode> currentModels = Maps.newHashMap();
    private KNode currentModel;

    public BrowsingSVGServer(final Shell shell, final String docRoot, int port) {

        if (INSTANCE != null) {
            throw new IllegalStateException("Only one server instance allowed.");
        }
        INSTANCE = this;

        this.shell = shell;
        this.docRoot = new File(docRoot);
        viewer = new PiccoloSVGBrowseViewer(shell);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        addConnector(connector);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
                WebSocket ws = null;
                if ("protocol.svgws".equals(protocol)) {
                    ws = new SVGSendingWebSocket();
                }
                return ws;
            }
        };
        WorkspaceContentHandler wcHandler = new WorkspaceContentHandler();

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        // rHandler.setResourceBase("html");
        // local html folder
        rHandler.setResourceBase("E:/Uni/ma/pragmatics/plugins/de.cau.cs.kieler.klighd.piccolo.svg/html");
        wsHandler.setHandler(rHandler);

        HandlerList hlist = new HandlerList();
        hlist.addHandler(wcHandler);
        hlist.addHandler(wsHandler);
        hlist.addHandler(rHandler);

        setHandler(hlist);

        verbose = true;

        // finally start the server
        startServer();
    }

    public static BrowsingSVGServer getInstance() {
        return INSTANCE;
    }


    private static void startServer() {
        System.out.println("Starting browsing server ...");
        new Thread("Jetty") {
            public void run() {
                try {
                    INSTANCE.start();
                    INSTANCE.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();

    }

    class SVGSendingWebSocket implements WebSocket, WebSocket.OnTextMessage {

        private Connection connection;

        /**
         * @return the connection
         */
        public Connection getConnection() {
            return connection;
        }

        /**
         * {@inheritDoc}
         */
        public void onOpen(Connection connection) {
            if (verbose) {
                System.err.printf("%s#onOpen %s\n", this.getClass().getSimpleName(), connection);
            }
            this.connection = connection;
            broadcast.add(this);

            broadcastSVG();
        }

        /**
         * {@inheritDoc}
         */
        public void onMessage(String data) {
            if (verbose) {
                System.err.printf("%s#onMessage     %s\n", this.getClass().getSimpleName(), data);
            }

            int x = Integer.valueOf(data.substring(5, data.indexOf(',')));
            int y =
                    Integer.valueOf(data.substring(data.lastIndexOf(':') + 1, data.lastIndexOf('}')));
            System.out.println(x + " " + y);
            viewer.getCanvas().getCamera().translateView(-x, -y);

            shell.getDisplay().syncExec(new Runnable() {

                public void run() {
                    // viewer.getCanvas().getCamera().invalidatePaint();
                    // viewer.getCanvas().redraw();
                    viewer.globalRedraw();
                }
            });
            broadcastSVG();
        }

        /**
         * {@inheritDoc}
         */
        public void onClose(int code, String message) {
            if (verbose) {
                System.err.printf("%s#onDisonnect %d %s\n", this.getClass().getSimpleName(), code,
                        message);
            }
            broadcast.remove(this);
        }

    }

    public void broadcastSVG() {

        String data = viewer.getGraphics().getSVG();
        // System.out.println("sending data " + this + " " + data.length());

        if (data.length() < 1000) {
            return;
        }

        // System.out.println(data);

        for (SVGSendingWebSocket ws : broadcast) {
            try {
                ws.getConnection().sendMessage(data);
            } catch (IOException e) {
                broadcast.remove(ws);
                e.printStackTrace();
            }
        }
    }

    class WorkspaceContentHandler extends AbstractHandler {
        /**
         * {@inheritDoc}
         */
        public void handle(String target, Request baseRequest, HttpServletRequest request,
                HttpServletResponse response) throws IOException, ServletException {
            System.out.println(target + " " + request);

            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

            System.out.println(docRoot.getPath());

            if (target.startsWith("/content")) {

                // String html = new HtmlGenerator().toHtmlRoot(root);
                String html = new HtmlGenerator().toHtmlRoot(docRoot);

                response.setContentType("text/html;charset=utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println(html);

            } else if (target.startsWith("/resource")) {

                String path = target.replace("/resource", "");
                IResource res = root.findMember(path);

                response.setContentType("text/html;charset=utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println(res);

                ResourceSet rs = new ResourceSetImpl();

                // MOML
                Map<String, Boolean> parserFeatures = Maps.newHashMap();
                parserFeatures.put("http://xml.org/sax/features/validation", //$NON-NLS-1$
                        Boolean.FALSE);
                parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                        Boolean.FALSE);
                parserFeatures.put(
                        "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                        Boolean.FALSE);

                rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
                rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                        .put("xml", new MomlResourceFactoryImpl());

                // final Resource r =
                // rs.getResource(
                // URI.createPlatformResourceURI(res.getFullPath().toString(), false),
                // true);

                final Resource r =
                        rs.getResource(
                                URI.createFileURI(new File(docRoot, path).getAbsolutePath()), true);

                System.out.println(r);

                shell.getDisplay().asyncExec(new Runnable() {

                    public void run() {
                        viewer.getCanvas().setBounds(0, 0, 870, 600);

                        // translate and set the model
                        currentModel =
                                LightDiagramServices.translateModel(new ViewContext(), r
                                        .getContents().get(0));
                        viewer.setModel(currentModel, true);

                        applyLayout();
                    }
                });

            } else if (target.startsWith("/expand/")) {

                response.setContentType("text/html;charset=utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);

                final String id = target.replace("/expand/", "");
                System.out.println("Expand: " + id);

                Display.getDefault().asyncExec(new Runnable() {

                    public void run() {
                        // expand and layout
                        viewer.toggleExpansion(id);
                        applyLayout();
                    }
                });
            } else if (target.startsWith("/zoom/")) {
                response.setContentType("text/html;charset=utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);

                try {
                    final Integer delta = Integer.valueOf(target.replace("/zoom/", ""));

                    shell.getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            final PCamera camera = viewer.getCanvas().getCamera();
                            double scaleDelta = 1.0 + 0.05 * delta;

                            // final double currentScale = camera.getViewScale();
                            // final double newScale = currentScale * scaleDelta;

                            // if (newScale < minScale) {
                            // scaleDelta = minScale / currentScale;
                            // }
                            // if (maxScale > 0 && newScale > maxScale) {
                            // scaleDelta = maxScale / currentScale;
                            // }
                            // Point2D viewZoomPoint = event.getPosition();
                            // camera.scaleViewAboutPoint(scaleDelta, viewZoomPoint.getX(),
                            // viewZoomPoint.getY());
                            camera.scaleViewAboutPoint(scaleDelta, 0, 0);

                            viewer.globalRedraw();

                        }
                    });
                } catch (NumberFormatException ex) {
                    // silent
                }
            }

        }
    }

    private void applyLayout() {

        LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(null, currentModel);
        // FIXME reactovate!
        mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);

        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
        mng.applyLayout(mapping, true, 0);
        viewer.globalRedraw();
    }

}
