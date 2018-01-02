/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.controllers;

import fr.esipe.control.beans.TableContent;
import fr.esipe.control.configs.ProducerMessage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zouhairhajji
 */
@RestController
public class DefaultController {

    @Autowired
    private BigTableService bigTableService;

    @Autowired
    private ProducerMessage producerMessage;

    @RequestMapping(value = "broadcast", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestParam(value = "message", required = true) String message) {
        try {
            producerMessage.send(message);
            return new ResponseEntity(HttpStatus.OK);
        } catch (JMSException ex) {
            return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "keys", method = RequestMethod.GET)
    public Set<String> showAllKeys() {
        return this.bigTableService.getAllKeys();
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Map<String, LinkedList<TableContent>> getBigTable() {
        return this.bigTableService.getBigTable();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getValue(@RequestParam(value = "key", required = true) String key) {
        try {
            return new ResponseEntity(this.bigTableService.getContent(key), HttpStatus.EXPECTATION_FAILED);
        } catch (IOException ex) {
            return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity setValue(
            @RequestBody(required = true) String body,
            @RequestParam(value = "key", required = true) String key) {
        
        try {
            return new ResponseEntity(this.bigTableService.pushContent(key, body), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        
    }
}
