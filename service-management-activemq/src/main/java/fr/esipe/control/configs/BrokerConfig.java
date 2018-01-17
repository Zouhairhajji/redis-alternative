/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.configs;

import fr.esipe.control.components.ConsumerMessageListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zouhairhajji
 */
@Configuration
public class BrokerConfig {

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private ConsumerMessageListener consumerMessageListener;

    
    
    @Value("${activemq.topic.name}")
    private String topicName;

    // create server
    @Bean
    public BrokerService createBroker() throws URISyntaxException, Exception {
        BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        broker.setPersistent(true);
        broker.start();
        return broker;
    }
    
    
    // create consumerï
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

    // create producer
    @Bean
    public ProducerMessage getProducerMessage() {
        ProducerMessage producerMessage = new ProducerMessage(this.topicName);
        return producerMessage;
    }

}
