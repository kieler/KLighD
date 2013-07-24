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
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.eclipse.swt.widgets.Shell;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.piccolo.svg.HtmlGenerator;

@SuppressWarnings("restriction")
public class BrowsingSVGServer extends Server {

    private static BrowsingSVGServer INSTANCE;

    private Shell shell;
    private File docRoot;

    private boolean verbose;

    // KlighD and Layout facilities
    private PiccoloSVGBrowseViewer getViewer;
    private KlighdLayoutManager mng;

    private HtmlGenerator gen = new HtmlGenerator();

    // room mappings
    private Map<String, PiccoloSVGBrowseViewer> roomViewerMap = Maps.newConcurrentMap();
    private MultiMap<String> roomConnectionMap = new MultiMap<String>();

    // single browsing mapping
    private Map<Connection, PiccoloSVGBrowseViewer> individualConnectionMap = Maps
            .newConcurrentMap();

    public BrowsingSVGServer(final Shell shell, final String docRoot, int port) {

        if (INSTANCE != null) {
            throw new IllegalStateException("Only one server instance allowed.");
        }
        INSTANCE = this;

        this.shell = shell;
        this.docRoot = new File(docRoot);
        this.getViewer = new PiccoloSVGBrowseViewer(shell);

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

        // locate the bundle during runtime
        try {
            File location = new File("html/");
            // FIXME required during local development
//             File location =
//             new File("../pragmatics/plugins/de.cau.cs.kieler.klighd.piccolo.svg/html/");

            System.out.println(location.getAbsolutePath());
            rHandler.setResourceBase(location.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    static enum Broadcast {
        All, OnlyThis, AllButThis
    }

    private void layout(final PiccoloSVGBrowseViewer viewer) {
        if (mng == null) {
            mng = new KlighdLayoutManager();
        }

        // build the layout mapping
        KNode model = viewer.getModel();
        // initially the viewer might not have a model set
        if (model == null) {
            return;
        }
        LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(null, model);
        mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);

        // perform the layout
        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
        mng.applyLayout(mapping, true, 0);

        // redraw
        viewer.globalRedraw();
    }

    class SVGSendingWebSocket implements WebSocket, WebSocket.OnTextMessage {

        private String currentRoom = null;
        private Connection connection;

        /**
         * @return the connection
         */
        public Connection getConnection() {
            return connection;
        }

        private void leaveIndividualConnections() {
            shell.getDisplay().syncExec(new Runnable() {

                public void run() {
                    // remove from the individual list
                    PiccoloSVGBrowseViewer viewer = individualConnectionMap.get(connection);
                    // TODO dispose!
                    // viewer.getControl().dispose();
                    individualConnectionMap.remove(connection);
                }
            });
        }

        private void joinRoom(final String room) {

            // remove from the individual list
            leaveIndividualConnections();

            // join the new room
            currentRoom = room;

            // check if there exists a viewer for this room
            if (!roomViewerMap.containsKey(room)) {
                roomViewerMap.put(room, createViewer());
            }

            // add this connection to the room map
            roomConnectionMap.add(room, connection);
        }

        private void leaveCurrentRoom(boolean addToIndividuals) {
            // remove this connection from the room
            roomConnectionMap.removeValue(currentRoom, connection);

            // if the room is empty delete the viewer as well
            if (roomConnectionMap.getValues(currentRoom) == null
                    || roomConnectionMap.getValues(currentRoom).isEmpty()) {
                PiccoloSVGBrowseViewer viewer = roomViewerMap.get(currentRoom);
                // TODO dispose
                // viewer.getControl().dispose();
                roomViewerMap.remove(currentRoom);
            }

            currentRoom = null;

            // add this connection to the individual ones
            if (addToIndividuals) {
                individualConnectionMap.put(connection, createViewer());
            }
        }

        private PiccoloSVGBrowseViewer getCurrentViewer() {
            if (currentRoom != null) {
                return roomViewerMap.get(currentRoom);
            } else {
                return individualConnectionMap.get(connection);
            }
        }

        private void layoutBroadcastSVG() {
            layoutBroadcastSVG(Broadcast.All);
        }

        /**
         * Make sure to call this method from the display thread!
         * 
         * @param onlyThis
         *            whether the svg is broadcasted to all matching connections or just this one.
         */
        private void layoutBroadcastSVG(final Broadcast broadcastType) {

            // get viewer and layout
            PiccoloSVGBrowseViewer viewer = getCurrentViewer();
            layout(viewer);

            // get the new SVG
            String svg = getSVG(viewer);

            // send the new svg to all corresponding connections
            broadcastJson(broadcastType, "type", "SVG", "data", svg);
        }

        @SuppressWarnings("unused")
        private void broadcastJson(final String... pairs) {
            broadcastJson(Broadcast.All, pairs);
        }

        private void broadcastJson(final Broadcast broadcastType, final String... pairs) {
            // transform args to a map
            Map<String, String> jsonMap = Maps.newHashMap();
            for (int i = 0; i < pairs.length; i += 2) {
                jsonMap.put(pairs[i], pairs[i + 1]);
            }

            // check if a type exists
            if (!jsonMap.containsKey("type")) {
                throw new IllegalArgumentException("The json object requires a 'type' field.");
            }

            String json = JSON.toString(jsonMap);
            broadcastJson(json, broadcastType);
        }

        private void broadcastJson(final String json, final Broadcast broadcastType) {
            try {

                if (broadcastType != Broadcast.OnlyThis && currentRoom != null) {
                    @SuppressWarnings("unchecked")
                    List<Connection> cons = roomConnectionMap.getValues(currentRoom);

                    // send svg to every connection
                    for (Connection c : cons) {
                        if (c != connection || broadcastType != Broadcast.AllButThis) {
                            c.sendMessage(json);
                        }
                    }
                } else {
                    if (broadcastType != Broadcast.AllButThis) {
                        connection.sendMessage(json);
                    }
                }
            } catch (IOException e) {
                // TODO check why on F5 no close signal is send, remove the connection here
                e.printStackTrace();
            }
        }

        private void sendError(String error) {
            // assemble json
            Map<String, String> jsonMap = Maps.newHashMap();
            jsonMap.put("type", "ERROR");
            jsonMap.put("data", error);

            String json = JSON.toString(jsonMap);

            try {
                connection.sendMessage(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private PiccoloSVGBrowseViewer createViewer() {
            final Maybe<PiccoloSVGBrowseViewer> viewer = new Maybe<PiccoloSVGBrowseViewer>();
            shell.getDisplay().syncExec(new Runnable() {

                public void run() {
                    viewer.set(new PiccoloSVGBrowseViewer(shell));
                }
            });
            return viewer.get();
        }

        /**
         * {@inheritDoc}
         */
        public void onOpen(Connection connection) {

            if (verbose) {
                System.err.printf("%s#onOpen %s\n", this.getClass().getSimpleName(), connection);
            }
            this.connection = connection;

            // initially add to the individual list
            individualConnectionMap.put(connection, createViewer());
        }

        /**
         * {@inheritDoc}
         */
        public void onMessage(String data) {
            if (verbose) {
                System.err.printf("%s#onMessage     %s\n", this.getClass().getSimpleName(), data);
            }

            try {
                // we expect json
                @SuppressWarnings("unchecked")
                Map<String, Object> json = (Map<String, Object>) JSON.parse(data);
                String type = (String) json.get("type");

                if (type.equals("JOIN")) {
                    /*
                     * JOIN -------------------------------------------------------------------
                     */
                    // if already in a room
                    if (currentRoom != null) {
                        throw new IllegalStateException(
                                "Already in a room, cannot join another one.");
                    }

                    // add to the room
                    String room = (String) json.get("room");
                    joinRoom(room);

                    // send current svg
                    shell.getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            layoutBroadcastSVG(Broadcast.OnlyThis);
                        }
                    });

                } else if (type.equals("LEAVE")) {
                    /*
                     * LEAVE -------------------------------------------------------------------
                     */
                    leaveCurrentRoom(true);

                } else if (type.equals("RESOURCE")) {
                    /*
                     * RESOURCE -------------------------------------------------------------------
                     */
                    String path = (String) json.get("path");

                    ResourceSet rs = new ResourceSetImpl();

                    // MOML
                    Map<String, Boolean> parserFeatures = Maps.newHashMap();
                    parserFeatures.put("http://xml.org/sax/features/validation", //$NON-NLS-1$
                            Boolean.FALSE);
                    parserFeatures.put(
                            "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                            Boolean.FALSE);
                    parserFeatures.put(
                            "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                            Boolean.FALSE);

                    rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
                    rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                    rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                            .put("xml", new MomlResourceFactoryImpl());

                    final Resource r =
                            rs.getResource(
                                    URI.createFileURI(new File(docRoot, path).getAbsolutePath()),
                                    true);

                    System.out.println(r);

                    shell.getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            PiccoloSVGBrowseViewer viewer = getCurrentViewer();
                            viewer.getCanvas().setBounds(0, 0, 870, 600);

                            // translate and set the model
                            try {
                                KNode currentModel =
                                        LightDiagramServices.translateModel(new ViewContext(), r
                                                .getContents().get(0));
                                viewer.setModel(currentModel, true);
                                // applyLayout();

                                layoutBroadcastSVG();
                            } catch (Exception e) {
                                e.printStackTrace();
                                // TODO pass the exception to the outside
                                // catch any error
                                // response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                // try {
                                // response.getWriter().println(
                                // "ERROR: Unable to handle the selected model.");
                                // } catch (IOException e1) {
                                // e1.printStackTrace();
                                // }
                            }
                        }
                    });

                } else if (type.equals("EXPAND")) {
                    /*
                     * EXPAND -------------------------------------------------------------------
                     */
                    final String id = (String) json.get("id");
                    shell.getDisplay().asyncExec(new Runnable() {

                        public void run() {
                            // expand and layout
                            getCurrentViewer().toggleExpansion(id);
                            // applyLayout();
                            // broadcastSVG();

                            layoutBroadcastSVG();
                        }
                    });
                } else if (type.equals("TRANSFORM")) {
                    /*
                     * TRANSLATE -------------------------------------------------------------------
                     */
                    String transform = (String) json.get("transform");

                    broadcastJson(Broadcast.AllButThis, "type", "TRANSFORM", "transform", transform);

                }

            } catch (Exception e) {
                e.printStackTrace();
                sendError("ERROR: " + e.getLocalizedMessage());
            }
        }

        /**
         * {@inheritDoc}
         */
        public void onClose(int code, String message) {
            if (verbose) {
                System.err.printf("%s#onDisonnect %d %s\n", this.getClass().getSimpleName(), code,
                        message);
            }

            // remove either from the room list, or from individual list
            if (currentRoom != null) {
                leaveCurrentRoom(false);
            } else {
                leaveIndividualConnections();
            }
        }

    }

    private String getSVG(final PiccoloSVGBrowseViewer viewer) {
        String data = viewer.getGraphics().getSVG();

        // insert an id for the first group element
        String res3 = data.substring(data.indexOf("<svg") - 1, data.length());
        String res4 = res3.replaceFirst("width=", "w=");
        String res5 = res4.replaceFirst("height=", "w=");

        StringBuffer sb = new StringBuffer(res5);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        return sb.toString();
    }

    /**
     * Usual HTTP Handler
     */
    private class WorkspaceContentHandler extends AbstractHandler {
        /**
         * {@inheritDoc}
         */
        public void handle(String target, final Request baseRequest,
                final HttpServletRequest request, final HttpServletResponse response)
                throws IOException, ServletException {
            System.out.println(target + " " + request);

            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            System.out.println(docRoot.getPath());

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
                parserFeatures.put(
                        "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                        Boolean.FALSE);

                rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
                rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                        .put("xml", new MomlResourceFactoryImpl());

                final Resource r =
                        rs.getResource(
                                URI.createFileURI(new File(docRoot, path).getAbsolutePath()), true);

                System.out.println(r);

                shell.getDisplay().syncExec(new Runnable() {

                    public void run() {
                        getViewer.getCanvas().setBounds(0, 0, 870, 600);

                        // translate and set the model
                        try {
                            KNode model =
                                    LightDiagramServices.translateModel(new ViewContext(), r
                                            .getContents().get(0));
                            getViewer.setModel(model, true);

                            layout(getViewer);

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

                        System.out.println(request);

                    }
                });

                // answer the request
                response.setContentType("text/html;charset=utf8");
                response.setCharacterEncoding("utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);

                // pass the svg
                response.getWriter().println(gen.permaLink(getSVG(getViewer)));
            }

        }
    }
}
