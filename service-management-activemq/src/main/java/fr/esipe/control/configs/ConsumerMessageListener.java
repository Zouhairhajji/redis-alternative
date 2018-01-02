package fr.esipe.control.configs;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMessageListener implements MessageListener {
    
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(" received " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
