package fr.esipe.control.configs;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfig {

    @Value("${activemq.topic.name}")
    private String topicName;

    @Autowired
    private ConsumerMessageListener consumerMessageListener;
    
    
    // create producer
    @Bean
    public ProducerMessage getProducerMessage() {
        ProducerMessage producerMessage = new ProducerMessage(this.topicName);
        return producerMessage;
    }
    
    @Bean
    public MessageConsumer getConsumerMessageListener() throws JMSException {
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.setClientID(UUID.randomUUID().toString());
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = topicSession.createTopic(topicName);
        // default consumer subscribes to customerTopic
        MessageConsumer messageConsumer = topicSession.createSubscriber(topic);
        messageConsumer.setMessageListener(consumerMessageListener);
        topicConnection.start();

        return messageConsumer;
    }
    
}
