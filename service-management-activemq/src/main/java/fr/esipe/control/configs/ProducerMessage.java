/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.configs;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author zouhairhajji
 */

public class ProducerMessage {
    
    
    private String topicName;

    private TopicSession topicSession;
    
    private Topic topic;

    public ProducerMessage(String topicName) {
        this.topicName = topicName;
    }
    
    
    
    @PostConstruct
    public void postConstruct() throws JMSException{
        // connection to broker
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.setClientID(UUID.randomUUID().toString());
        topicConnection.start();
        
        this.topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        this.topic = topicSession.createTopic(topicName);
    }
    
    public void send(String payload) throws JMSException{
        Message msg = topicSession.createTextMessage(payload);
        TopicPublisher publisher = topicSession.createPublisher(topic);
        System.out.println("Sending text '" + payload + "'");
        publisher.publish(msg);
    }
}
