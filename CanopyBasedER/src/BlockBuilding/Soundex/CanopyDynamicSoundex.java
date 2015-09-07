/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import DataStuctures.Entity;
import Experiments.SoundexExperiments.CanopyBuildingExperiment;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import SimilarityFunctions.CanopySimilarity;
import Utilities.Serialization;
import Utilities.Soundex;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class CanopyDynamicSoundex {
     CanopyIndex CI;
     EntityIndex EI;
     private final double t1;
     private final double t2;
     int BlockID;
     Map<String, ArrayList> IS;
     HashMap<String,Entity> records;
     
     public CanopyDynamicSoundex(Double T1, Double T2){
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        t1=T1;
        t2=T2;
        records = getRecords();
        
     }
     
     public void addToCanopy(Entity entity, ArrayList<String> attributes){
        InvertedSoundex soundexI;
        String[] attr = new String[attributes.size()];
        ArrayList<InvertedSoundex> II = new InvertedIndexes().getInvertedIndexes(attributes);
        BlockID = CI.getLastIndex();
        ArrayList<Integer> ids = new ArrayList<>();
        
        String key = null;
        soundexI = (InvertedSoundex) IS;
        String recordID = entity.getRecordID();
        
        for(int i=0; i< attr.length; i++){
            if(i ==0)
                key= Soundex.soundex(entity.getFirstName());
            if(i == 1)
                key= Soundex.soundex(entity.getLastName());
            
            soundexI = II.get(i);
            
            Boolean added = false;
            ArrayList<String> candidates = soundexI.getCandidates(key);
            for (String candidateEntite : candidates) {
                Entity currentEntity = records.get(candidateEntite);
                if(currentEntity == null){
                            continue;
                }
                        //System.out.println(currentEntity.getRecordID());
                if(candidateEntite.equals(recordID)){
                    continue;
                }
                
                String currentID = currentEntity.getRecordID();
                double similarity;
                similarity = CanopySimilarity.getSimilarity(entity, currentEntity, 0.7, 0.3,1);
                int currentBlockID = EI.getBlockList(currentID).indexOf(0);
                if (t1 <= similarity) {
                    
                    ids.add(currentBlockID);
                    //ids.add(currentEntity.getRecordID());
                }
                
                // Removal threshold:
                if (t2 <= similarity) {
                    CI.appendToBlock(currentBlockID, entity.getRecordID());
                    EI.appendToBlock(recordID, currentBlockID);
                    added = true;
                    break;
                }
                
                if(!added){
                for (int blocksID : ids) {
                    CI.appendToBlock(blocksID,entity.getRecordID());
                    EI.appendToBlock(entity.getRecordID(), blocksID);
                }
            }
                
                
                
            }
            
        }
        
        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser"); 
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser"); 
        
     }
     
     public HashMap<String,Entity> getRecords(){
          HashMap<String,Entity> test = new HashMap<String, Entity>();
         mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person");
                try {
                 
                    test = connecton.getInvertedIndexData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CanopyBuildingExperiment.class.getName()).log(Level.SEVERE, null, ex);
                }
                test.entrySet().stream().forEach((entry) -> {
                //System.out.print("Key : " + entry.getKey());
                Entity list = entry.getValue();
                
                //System.out.println(" " + list.getFirstName() );

            
            //System.out.println(entry.getKey().hashCode());
        });
                return test;
    }
     
}
