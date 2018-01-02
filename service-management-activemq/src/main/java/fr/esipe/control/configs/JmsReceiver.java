/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.configs;

import javax.jms.Message;
import javax.jms.MessageListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author zouhairhajji
 */
@Component
public class JmsReceiver implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        System.out.println("recu un message : " + msg);
    }
    
}
