package ru.usachev.display.logiweb.message;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Singleton
@ApplicationScoped
@Named
public class WebSocket {
    private static final String URI = "http://localhost:8099/logiweb/rest/drivers";
    @Inject
    @Push(channel = "websocket")
    private PushContext pushContext;

    public void sendMessage(String message) {
        pushContext.send(message);
    }

    @PostConstruct
    public void askData() {
        try {
            URL url = new URL(URI);
            HttpURLConnection connection = (HttpURLConnection) (url.openConnection());
            int status = connection.getResponseCode();
        } catch (IOException e) {
        }
    }
}
