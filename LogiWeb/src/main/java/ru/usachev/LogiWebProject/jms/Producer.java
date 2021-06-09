package ru.usachev.LogiWebProject.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.*;

@Service
public class Producer{
    private static final long TIME_TO_LIVE_MILLISEC = 30_000; //messages older than that value won't be delivered to destination

    @Resource(lookup = "java:/logiweb/MyConnectionFactory")//inject with JNDI, resource
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/logiweb/MyQueue")//Queue
    private Destination destination;

    public void sendMessage() {
        try (
                QueueConnection connection = (QueueConnection) connectionFactory.createConnection();
                QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(destination)//connect to Queue's name
        ) {
            TextMessage message = session.createTextMessage("update");
            producer.setTimeToLive(TIME_TO_LIVE_MILLISEC);
            producer.send(message);
            System.out.println(message.getText());

        } catch (JMSException ex) {
            System.out.println("Exception from jms sender");
        }
    }
}
