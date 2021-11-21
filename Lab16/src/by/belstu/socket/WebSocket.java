package by.belstu.socket;

import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ServerEndpoint("/websocket")
public class WebSocket extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        try {
            while (true) {
                remoteEndpointBasic.sendText(format.format(new Date()));
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}