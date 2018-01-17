/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.components;

import fr.esipe.control.beans.TableContent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author zouhairhajji
 */
@Component
public class BigTable {

    private Map<String, LinkedList<TableContent>> bigtable;

    public BigTable() {
        this.bigtable = new HashMap<String, LinkedList<TableContent>>();
    }

    public String getValue(String key) throws IOException {
        TableContent tableContent = this.bigtable.get(key).getLast();
        String filename = tableContent.getFilename();
        Path pathFileName = Paths.get(key);
        byte[] bytes = Files.readAllBytes(pathFileName);
        return new String(bytes);
    }

    public TableContent pushValue(String key, String value) throws IOException {
        Path pathContent = Files.createTempFile(key, ".tmp");
        File fileContent = pathContent.toFile();

        TableContent tableContent = TableContent.builder()
                .filename(fileContent.getAbsolutePath())
                .insertionDate(new Timestamp(System.currentTimeMillis()))
                .key(key)
                .build();
        
        
        Files.write(pathContent, value.getBytes(StandardCharsets.UTF_8));
        
        // create new element in big table if the key does not exists
        if(this.isKeyExists(key) != true){
            this.bigtable.put(key, new LinkedList<TableContent>());
        }
        
        this.bigtable.get(key).add(tableContent);
        return null;
    }
    
    
    public boolean isKeyExists(String key){
        return this.bigtable.get(key) != null;
    }

    public Map<String, LinkedList<TableContent>> getBigtable() {
        return bigtable;
    }
    
    

}
