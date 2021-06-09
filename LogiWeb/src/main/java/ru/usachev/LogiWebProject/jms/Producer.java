package ru.usachev.LogiWebProject.jms;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@LocalBean
public class Producer {

    @Resource(lookup = "java:/logiweb/MyConnectionFactory", type = ConnectionFactory.class)
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/logiweb/MyQueue")
    private Destination destination;

//    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void produceMessage(){
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);


            TextMessage textMessage = session.createTextMessage("UPDATE");
            System.out.println("-----------------------------------------");

            messageProducer.send(textMessage);
            connection.close();
            session.close();
            messageProducer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
