/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;

import Indexes.Interfaces.CanopyIndexInterface;
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
public class CanopyIndex implements Serializable, CanopyIndexInterface{
    public Map<Integer,ArrayList> CI = new HashMap<Integer, ArrayList>();;
    int lastIndex;    
    private static final long serialVersionUID = 1113799434508676095L;

    @Override
    public void createBlock(int blockID, String recordID){
       
        ArrayList<String> recordIDs = new ArrayList<>();
        recordIDs.add(recordID);
        CI.put(blockID, recordIDs);
    }
    
    @Override
    public void appendToBlock(int blockID,String recordID){
        ArrayList<String> recordIDs = CI.get(blockID);
        if(!recordIDs.contains(recordID)){
            recordIDs.add(recordID);
            CI.put(blockID, recordIDs);
        }
    }
    
    @Override
    public void appendToBlockAyyayList(int blockID,ArrayList<String> recordIDs ){
        ArrayList<String> OrecordIDs = CI.get(blockID);
        OrecordIDs.addAll(recordIDs);
        CI.put(blockID, OrecordIDs);
    }
    
    @Override
    public void setLastIndex(int ID){
        lastIndex = ID;
    }
    
    @Override
    public int getLastIndex(){
        return lastIndex;
    }
    
    @Override
    public Set keys() {
        return CI.keySet();
    }
    
    @Override
    public String canopyRecordID(int blockID){
        return (String) CI.get(blockID).get(0);
    }
    
    @Override
    public ArrayList getEntityList(int blockID){
        ArrayList<String> blockIDs = CI.get(blockID);
        return blockIDs;
    }
    
    @Override
    public void printIndex(){
        System.out.println("CANOPY INDEX");
        CI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            //System.out.println(entry.getKey().hashCode());
        });
    }
    
    public ArrayList<String> getPrintIndex(){
        ArrayList<String> records = new ArrayList<>();
        CI.entrySet().stream().forEach((entry) -> {
            records.add("Key : " + entry.getKey() + " Value : "
                    + entry.getValue().toString());
            //System.out.println(entry.getKey().hashCode());
        });
        return records;
    }

    
}
