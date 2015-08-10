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
import Indexes.Interfaces.RecordIndexInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author JanithaT
 */
public class RecordIndex implements Serializable, RecordIndexInterface{
    public Map<Entity,ArrayList> RI = new HashMap<Entity,ArrayList>();
    private static final long serialVersionUID = 1113799434508678000L;
    
    @Override
    public void appendEntity(Entity entity){
        
        ArrayList<String> recordIDs = new ArrayList<>();
        recordIDs.add(entity.getRecordID());
        RI.put(entity, recordIDs);
    }
    
    @Override
    public void appendRecord(Entity entity, String RecordID){
        System.out.println("CAAAMEEE HERE");
        ArrayList<String> recordIDs  = RI.get(entity);
        recordIDs.add(RecordID);
        RI.put(entity,recordIDs);
        
    }
    
    @Override
    public ArrayList getDuplicates(Entity entity){
        ArrayList<String> IDs = RI.get(entity);
        return IDs;
    }
    
    @Override
    public Boolean hasEntity(Entity entity){
         return RI.containsKey(entity);
    }
    
    
    
    @Override
    public Map<Entity,ArrayList> getRecordIndex(){
        return RI;
    }
    
    @Override
    public void printIndex(){
        System.out.println("RECORD INDEX");
        RI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey().getFirstName() + " Value : "
                    + entry.getValue().toString());
            //System.out.println(entry.getKey().hashCode());
        });
    }
    
    
    
    
}
