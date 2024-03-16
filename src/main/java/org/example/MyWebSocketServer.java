package org.example;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 *   https://mvnrepository.com/artifact/org.java-websocket/Java-WebSocket
 *         <dependency>
 *             <groupId>org.java-websocket</groupId>
 *             <artifactId>Java-WebSocket</artifactId>
 *             <version>1.5.6</version>
 *         </dependency>
 */

public class MyWebSocketServer extends WebSocketServer {

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress() + " Reason: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + message);
        conn.send("Echo: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error occurred on connection " + conn.getRemoteSocketAddress() + ": " + ex);
    }

    @Override
    public void onStart() {

    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8887;
        MyWebSocketServer server = new MyWebSocketServer(new InetSocketAddress(host, port));
        server.start();
        System.out.println("WebSocket server started on ws://" + host + ":" + port);
    }
}

