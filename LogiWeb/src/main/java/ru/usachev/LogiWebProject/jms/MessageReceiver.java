//package ru.usachev.LogiWebProject.jms;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
//import javax.jms.JMSException;
//
//@Component
//public class MessageReceiver {
//    private static final String ORDER_RESPONSE_QUEUE = "java:/logiweb/MyQueue";
//
//    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
//    public void receiveMessage(final Message message) throws JMSException {
//
//        System.out.println(message.getPayload().toString());
//
////        orderService.updateOrder(response);
//    }
//}
