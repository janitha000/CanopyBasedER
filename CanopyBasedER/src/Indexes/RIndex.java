/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;

import DataStuctures.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JanithaT
 */
public class RIndex implements Serializable{
    public Map<String,Entity> RI = new HashMap<String,Entity>();
    private static final long serialVersionUID = 1113799434508679000L;
    
    public void appendEntity(Entity entity){
        
        RI.put(entity.getRecordID(),entity);
        System.out.println("CAME " + entity.getRecordID());
    }
    
    public Entity getRecord(String recordID){
        return RI.get(recordID);
    }
    
    public void printIndex(){
        System.out.println("R INDEX");
        RI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().getFirstName());
            //System.out.println(entry.getKey().hashCode());
        });
    }
}
