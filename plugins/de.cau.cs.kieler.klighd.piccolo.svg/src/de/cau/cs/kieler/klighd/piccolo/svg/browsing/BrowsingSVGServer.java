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
import java.io.IOException;
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
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.piccolo.svg.HtmlGenerator;

public class BrowsingSVGServer extends Server {

    private static BrowsingSVGServer INSTANCE;
    private static final int PORT = 8081;

    private boolean verbose;

    private ConcurrentLinkedQueue<SVGSendingWebSocket> broadcast =
            new ConcurrentLinkedQueue<SVGSendingWebSocket>();

    private Shell shell = new Shell();
    private PiccoloSVGBrowseViewer viewer = new PiccoloSVGBrowseViewer(shell);

    public BrowsingSVGServer(int port) {
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
        rHandler.setResourceBase("E:/Uni/ma/pragmatics/plugins/de.cau.cs.kieler.klighd.piccolo.svg/html");
        System.out.println(rHandler.getBaseResource().getURI());
        wsHandler.setHandler(rHandler);

        HandlerList hlist = new HandlerList();
        hlist.addHandler(wcHandler);
        hlist.addHandler(wsHandler);
        hlist.addHandler(rHandler);

        setHandler(hlist);

        verbose = true;

        shell.open();
    }

    public static BrowsingSVGServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BrowsingSVGServer(PORT);
            startServer();
        }
        return INSTANCE;
    }

    /**
     * @param viewer
     *            the viewer to set
     */
    public void setViewer(PiccoloSVGBrowseViewer viewer) {
        this.viewer = viewer;
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

    // public void setViewer(KlighdSVGGraphicsImpl theSvgGenerator, PiccoloSVGViewer viewer) {
    // svgGenerator = theSvgGenerator;
    // this.viewer = viewer;
    // }

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

            Display.getDefault().syncExec(new Runnable() {

                public void run() {
                    viewer.getCanvas().getCamera().invalidatePaint();
                    viewer.getCanvas().redraw();
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
//        System.out.println("sending data " + this + " " + data.length());

        if (data.length() < 1000) {
            return;
        }

//        System.out.println(data);

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
            if (target.startsWith("/content")) {

                String html = new HtmlGenerator().toHtmlRoot(root);

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
                final Resource r =
                        rs.getResource(
                                URI.createPlatformResourceURI(res.getFullPath().toString(), false),
                                true);

                final Object model = r.getContents().get(0);

                Display.getDefault().asyncExec(new Runnable() {

                    public void run() {
                        // MapPropertyHolder props = new MapPropertyHolder();
                        // props.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY,
                        // SimpleUpdateStrategy.ID);
                        //
                        // final ViewContext ctx =
                        // LightDiagramServices.getInstance().createViewContext(model, props);
                        // viewer.setModel((KNode) ctx.getViewModel());
                        //
                        // System.out.println(LightDiagramServices.getInstance().updateViewContext(ctx,
                        // model));

                        // LightDiagramServices.getInstance().layoutDiagram(ctx, false, true);

                        System.out.println(r.getContents().get(0));
                        
                        final KNode o = LightDiagramServices.translateModel(r.getContents().get(0));

                        // System.out.println(o);

                        new RecursiveGraphLayoutEngine().layout(o, new BasicProgressMonitor())  ;

                        // viewer.getCanvas().setVisible(true);
                        viewer.getCanvas().setBounds(0, 0, 600, 600);

                        viewer.setModel(o);
                        viewer.zoomToFit(0);
                        viewer.globalRedraw();

                        // c.redraw();
                        // shell.setVisible(false);

                        // LightDiagramServices.getInstance().layoutDiagram(ctx, true, true);
                        // WorkspaceContributor.getWorkspaceStructure();

                    }
                });

            }

        }

    }

}
