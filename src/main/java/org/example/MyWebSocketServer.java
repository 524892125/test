package org.example;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyWebSocketServer extends WebSocketServer {

    private Set<WebSocket> clients;

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
        this.clients = Collections.synchronizedSet(new HashSet<>());
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
        clients.add(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress() + " Reason: " + reason);
        clients.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + message);
        sendMessageToAll("Client " + conn.getRemoteSocketAddress() + " says: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error occurred on connection " + conn.getRemoteSocketAddress() + ": " + ex);
    }

    @Override
    public void onStart() {

    }

    private void sendMessageToAll(String message) {
        synchronized (clients) {
            for (WebSocket client : clients) {
                client.send(message);
            }
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8887;
        MyWebSocketServer server = new MyWebSocketServer(new InetSocketAddress(host, port));
        server.start();
        System.out.println("WebSocket server started on ws://" + host + ":" + port);
    }
}

