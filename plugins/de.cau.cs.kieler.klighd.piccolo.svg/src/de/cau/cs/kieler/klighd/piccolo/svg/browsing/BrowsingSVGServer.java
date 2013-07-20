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
import java.util.concurrent.ConcurrentLinkedQueue;

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
import org.eclipse.jetty.util.ajax.JSON.Convertible;
import org.eclipse.jetty.util.ajax.JSON.Output;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.ietf.jgss.Oid;
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
    private KlighdLayoutManager mng;

    private HtmlGenerator gen = new HtmlGenerator();

    // currently shown model per connection
    // TODO
    private Map<Connection, KNode> currentModels = Maps.newHashMap();
    private KNode currentModel;

    private Map<String, PiccoloSVGBrowseViewer> roomViewerMap = Maps.newConcurrentMap();
    private MultiMap<String> roomConnectionMap = new MultiMap<String>();

    private Map<Connection, PiccoloSVGBrowseViewer> individualConnectionMap = Maps
            .newConcurrentMap();

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

        // locate the bundle during runtime
        try {
            // File location = new File("html/");
            File location =
                    new File("../pragmatics/plugins/de.cau.cs.kieler.klighd.piccolo.svg/html/");

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
                    //viewer.getControl().dispose();
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
            roomConnectionMap.remove(currentRoom, connection);

            // if the room is empty delete the viewer as well
            if (roomConnectionMap.getValues(currentRoom).isEmpty()) {
                PiccoloSVGBrowseViewer viewer = roomViewerMap.get(currentRoom);
                viewer.getControl().dispose();
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

        /**
         * Make sure to call this method from the display thread!
         */
        private void layoutBroadcastSVG() {
            // TODO sent the svgs !! or translate to the other viewers!
            if (mng == null) {
                mng = new KlighdLayoutManager();
            }

            PiccoloSVGBrowseViewer viewer = getCurrentViewer();
            // build the layout mapping
            KNode model = viewer.getModel();
            LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(null, model);
            mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);

            // perform the layout
            DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
            mng.applyLayout(mapping, true, 0);

            // redraw
            viewer.globalRedraw();

            // get the new SVG
            String svg = getSVG(viewer);

            // assemble json
            Map<String, String> jsonMap = Maps.newHashMap();
            jsonMap.put("type", "SVG");
            jsonMap.put("data", svg);

            String json = JSON.toString(jsonMap);

            // send the new svg to all corresponding connections
            try {
                if (currentRoom != null) {
                    @SuppressWarnings("unchecked")
                    List<Connection> cons = roomConnectionMap.getValues(currentRoom);

                    // send svg to every connection
                    for (Connection c : cons) {
                        c.sendMessage(json);
                    }
                } else {
                    connection.sendMessage(json);
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
            // broadcast.add(this);

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

                    // TODO send current svg
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

                    shell.getDisplay().syncExec(new Runnable() {

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
                } else if (type.equals("TRANSLATE")) {
                    /*
                     * TRANSLATE -------------------------------------------------------------------
                     */
                } else if (type.equals("ZOOM")) {
                    /*
                     * ZOOM -------------------------------------------------------------------
                     */
                }

            } catch (Exception e) {
                e.printStackTrace();
                sendError("ERROR: " + e.getLocalizedMessage());
            }

            // int x = Integer.valueOf(data.substring(5, data.indexOf(',')));
            // int y =
            // Integer.valueOf(data.substring(data.lastIndexOf(':') + 1, data.lastIndexOf('}')));
            // System.out.println(x + " " + y);
            // viewer.getCanvas().getCamera().translateView(-x, -y);
            //
            // shell.getDisplay().asyncExec(new Runnable() {
            //
            // public void run() {
            // // viewer.getCanvas().getCamera().invalidatePaint();
            // // viewer.getCanvas().redraw();
            // viewer.globalRedraw();
            // }
            // });
            // broadcastSVG();
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

            // remove either from the room list, or from individual list
            if (currentRoom != null) {
                leaveCurrentRoom(false);
            } else {
                leaveIndividualConnections();
            }
        }

    }

    private String getSVG() {
        String data = viewer.getGraphics().getSVG();
        // System.out.println("sending data " + this + " " + data.length());

        // System.out.println(data);

        // insert an id for the first group element

        String res3 = data.substring(data.indexOf("<svg") - 1, data.length());
        String res4 = res3.replaceFirst("width=", "w=");
        String res5 = res4.replaceFirst("height=", "w=");

        StringBuffer sb = new StringBuffer(res5);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        System.out.println("Broadcast");

        return sb.toString();
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

    // public void broadcastSVG() {
    //
    // throw new RuntimeException("DONT USE");
    // String data = getSVG();
    //
    // if (data.length() < 1000) {
    // return;
    // }
    //
    // for (SVGSendingWebSocket ws : broadcast) {
    // try {
    // ws.getConnection().sendMessage(data);
    // } catch (IOException e) {
    // broadcast.remove(ws);
    // e.printStackTrace();
    // }
    // }
    // }

    class WorkspaceContentHandler extends AbstractHandler {
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
                // IResource res = root.findMember(path);

                // response.getWriter().println(res);

                response.setContentType("text/html;charset=utf8");
                response.setCharacterEncoding("utf8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);

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

                shell.getDisplay().syncExec(new Runnable() {

                    public void run() {
                        viewer.getCanvas().setBounds(0, 0, 870, 600);

                        // translate and set the model
                        try {
                            currentModel =
                                    LightDiagramServices.translateModel(new ViewContext(), r
                                            .getContents().get(0));
                            viewer.setModel(currentModel, true);
                            applyLayout();
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

                if (request != null) {
                    if (baseRequest.getMethod().equals("PUT")) {
                        // broadcastSVG();
                    } else if (request.getMethod().equals("GET")) {
                        response.getWriter().println(gen.permaLink(getSVG()));
                    }
                }

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
                        // broadcastSVG();
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
                            // broadcastSVG();
                        }
                    });
                } catch (NumberFormatException ex) {
                    // silent
                }
            }

        }
    }

    private void applyLayout() {

        if (mng == null) {
            mng = new KlighdLayoutManager();
        }

        LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(null, currentModel);
        // FIXME reactovate!
        mapping.setProperty(KlighdInternalProperties.VIEWER, viewer);

        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
        mng.applyLayout(mapping, true, 0);
        viewer.globalRedraw();
    }

    private static class JsonMessage implements Convertible {

        public enum Type {
            JOIN_ROOM, LEAVE_ROOM, TRANSLATE
        }

        private Type type;

        /**
         * {@inheritDoc}
         */
        public void fromJSON(final Map map) {
            map.get("type");
        }

        /**
         * {@inheritDoc}
         */
        public void toJSON(Output arg0) {
            // TODO Auto-generated method stub

        }
    }
}
