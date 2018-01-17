/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.controllers;

import fr.esipe.control.beans.TableContent;
import fr.esipe.control.components.BigTable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service
class BigTableService {
    
    @Autowired
    private BigTable bigTable;
    
    public TableContent pushContent(String key, String value) throws IOException {
        TableContent tableContent = this.bigTable.pushValue(key, value);
        return tableContent;
    }
    
    public String getContent(String key) throws IOException{
        return this.bigTable.getValue(key);
    }
    
    public Set<String> getAllKeys(){
        return this.bigTable.getBigtable().keySet();
    }
    
    public Map<String, LinkedList<TableContent>> getBigTable(){
        return this.bigTable.getBigtable();
    }
    
            
            
            
            
            
            
    
}
