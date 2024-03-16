package org.example;

import java.net.URI;
import javax.websocket.*;

@ClientEndpoint
public class WebSocketClient {

    private Session session;

    public WebSocketClient() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI("ws://localhost:8080/websocket"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("WebSocket connection opened.");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @OnClose
    public void onClose(CloseReason reason) {
        System.out.println("WebSocket connection closed: " + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Throwable t) {
        System.err.println("WebSocket error: " + t.getMessage());
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.sendMessage("Hello, WebSocket!");
    }
}