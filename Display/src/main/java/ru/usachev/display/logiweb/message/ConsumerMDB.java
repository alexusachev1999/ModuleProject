package ru.usachev.display.logiweb.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {@ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "java:/logiweb/MyQueue"
)})
public class ConsumerMDB implements MessageListener {

    @Inject
    WebSocket webSocket;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            webSocket.sendMessage(textMessage.getText());
            System.out.println("From consumer:");
            System.out.println(textMessage.getText() + " " + this.getClass().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
