/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import Utilities.Serialization;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author JanithaT
 */
public class CanopyIndex implements Serializable{
    public Map<Integer,ArrayList> CI = new HashMap<Integer, ArrayList>();;
    int lastIndex;    
    private static final long serialVersionUID = 1113799434508676095L;

    public void createBlock(int blockID, String recordID){
       
        ArrayList<String> recordIDs = new ArrayList<>();
        recordIDs.add(recordID);
        CI.put(blockID, recordIDs);
    }
    
    public void appendToBlock(int blockID,String recordID){
        ArrayList<String> recordIDs = CI.get(blockID);
        recordIDs.add(recordID);
        CI.put(blockID, recordIDs);
    }
    
    public void appendToBlockAyyayList(int blockID,ArrayList<String> recordIDs ){
        ArrayList<String> OrecordIDs = CI.get(blockID);
        OrecordIDs.addAll(recordIDs);
        CI.put(blockID, OrecordIDs);
    }
    
    public void setLastIndex(int ID){
        lastIndex = ID;
    }
    
    public int getLastIndex(){
        return lastIndex;
    }
    
    public Set keys() {
        return CI.keySet();
    }
    
    public String canopyRecordID(int blockID){
        return (String) CI.get(blockID).get(0);
    }
    
    public void printIndex(){
        System.out.println("CANOPY INDEX");
        CI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            //System.out.println(entry.getKey().hashCode());
        });
    }
    
    

    
}
