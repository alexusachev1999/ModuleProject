package ru.usachev.LogiWebProject.jms;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Producer class which send message 'update' when it calls from UpdateAspect Class
 */
@Service
public class Producer{

    Logger logger = Logger.getLogger(Producer.class);
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
            logger.info(message.getText());

        } catch (JMSException ex) {
            logger.info("Exception from jms sender");
        }
    }
}
