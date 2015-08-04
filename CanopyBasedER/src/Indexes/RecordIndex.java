/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;
import DataStuctures.Entity;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import DataStuctures.RecordIDs;
import java.util.ArrayList;
/**
 *
 * @author JanithaT
 */
public class RecordIndex {
    public Map<Entity,ArrayList> RI = new HashMap<Entity,ArrayList>();
    
    public void appendEntity(Entity entity){
        
        ArrayList<String> recordIDs = new ArrayList<>();
        recordIDs.add(entity.getRecordID());
        RI.put(entity, recordIDs);
    }
    
    public void appendRecord(Entity entity, String RecordID){
        ArrayList<String> recordIDs  = RI.get(entity);
        recordIDs.add(RecordID);
        RI.put(entity,recordIDs);
        
    }
    
    public Map<Entity,ArrayList> getRecordIndex(){
        return RI;
    }
    
    public void printIndex(){
        RI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey().getFirstName() + " Value : "
                    + entry.getValue());
        });
    }
    
    public boolean containsEntity(Entity entity){
        return RI.containsKey(entity);
    }
}
