package ru.usachev.display.logiweb.message;

import ru.usachev.display.logiweb.service.RestService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "ConsumerFirst", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/logiweb/MyQueue")
})
public class Consumer implements MessageListener {

    @Inject
    private RestService restService;


    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String messageString = textMessage.getText();
            if (messageString.equals("UPDATE FROM LOGIWEB")){
                restService.getDriversForDisplay();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
