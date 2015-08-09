/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JanithaT
 */
public class EntityIndex implements Serializable{
    public Map<String,ArrayList> EI = new HashMap<String, ArrayList>();
    private static final long serialVersionUID = 1113799434508677000L;
    
    public void createBlock(String recordID, int blockID){
       
        ArrayList<Integer> blockIDs = new ArrayList<>();
        blockIDs.add(blockID);
        EI.put(recordID, blockIDs);
    }
    
    public void appendToBlock(String recordID, int blockID){
        if(!EI.containsKey(recordID)){
            ArrayList<Integer> blockIDs = new ArrayList<>();
            blockIDs.add(blockID);
            EI.put(recordID, blockIDs);
        } else {
            ArrayList<Integer> blockIDs = EI.get(recordID);
            blockIDs.add(blockID);
            EI.put(recordID,blockIDs);
        }
    
    }
    
    public Boolean hasBlock(String recordID){
        return EI.containsKey(recordID);
    }
    
    public void printIndex(){
        System.out.println("ENTITY INDEX");
        EI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            //System.out.println(entry.getKey().hashCode());
        });
    }
}
