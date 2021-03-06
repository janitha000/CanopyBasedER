/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding;

import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.RIndex;
import Indexes.RecordIndex;
import SimilarityFunctions.CanopySimilarity;
import java.util.Iterator;
import java.util.List;
import Utilities.JaccardSimilarity;
import Utilities.Serialization;
import java.util.ArrayList;


/**
 *
 * @author JanithaT
 */
public class CanopyClustering {
    CanopyIndex CI;
    EntityIndex EI;
    RecordIndex RI;
    RIndex ri;
    int noOfRecords;
    List<Entity> records;
    private final double t1;
    private final double t2;
    int blockID;
    
    public CanopyClustering(List<Entity> Erecords, Double T1, Double T2){
        
        records = Erecords;
        CI = new CanopyIndex();
        EI = new EntityIndex();
        RI = new RecordIndex();
        ri = new RIndex();
        noOfRecords = Erecords.size();
        t1 = T1;
        t2 = T2;
        blockID=0;
    }
        public void createCanopies(){
        blockID = 1;
        while(!records.isEmpty()){
            long startTime = System.currentTimeMillis();
            Iterator iter = records.iterator();
            Entity firstEntity = (Entity) iter.next();
            String recordID = firstEntity.getRecordID();
            ri.appendEntity(firstEntity);
//            if(RI.hasEntity(firstEntity)){
//                if(!RI.getDuplicates(firstEntity).contains(recordID)){
//                    RI.appendRecord(firstEntity, recordID);
//                    continue;
//                } 
//            }
//            else {
//                    RI.appendEntity(firstEntity);
//                }
            if(blockID ==1){
                RI.appendEntity(firstEntity);
            }
            
            iter.remove();
            //records.remove(firstEntity);
            
            
            CI.createBlock(blockID, recordID);
            EI.appendToBlock(recordID, blockID);
            //ArrayList<String> ids = new ArrayList<String>();
              int id=0;         
            while (iter.hasNext()) {
                
                Entity currentEntity = (Entity) iter.next();
                String currentID = currentEntity.getRecordID();
                id++;
//                if(RI.hasEntity(currentEntity)){
//                    System.out.println("CAMR TO EQUAL " + currentID);
//                    if(!RI.getDuplicates(currentEntity).contains(recordID)){
//                        RI.appendRecord(currentEntity, currentID);
//                        continue;
//                    }
//                    
//                }else {
//                        RI.appendEntity(currentEntity);
//                    }
                if(blockID ==1){
                    if(RI.hasEntity(currentEntity)){
                        RI.appendRecord(currentEntity, currentID);
                        iter.remove();
                        continue;                    
                    } else {
                        RI.appendEntity(currentEntity);
                    }
                }
                   
                double similarity;
                similarity = CanopySimilarity.getSimilarity(firstEntity, currentEntity, 0.7, 0.3);
                //System.out.println("Checking "+ currentEntity.getFirstName() + " "+ id);
                
                
                if (t1 <= similarity) {
                    CI.appendToBlock(blockID, currentID);
                    EI.appendToBlock(currentID, blockID);
                    EI.sort(currentID);
                    //ids.add(currentEntity.getRecordID());
                }

                // Removal threshold:
                if (t2 <= similarity) {
                    iter.remove();
                    ri.appendEntity(currentEntity);
                    //records.remove(currentEntity);
                    
                }
                
            }
            
            //CI.appendToBlockAyyayList(blockID, ids);
            CI.setLastIndex(blockID);
            long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        //System.out.println(" Elapsed time is " +elapsedTime);
            blockID++;
        }
        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        Serialization.storeSerializedObject(RI, "E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        Serialization.storeSerializedObject(ri, "E:\\4th Year\\Research\\Imp\\Indexes\\rii.ser");
        //ri.printIndex();
        //RI.printIndex();
        CI.printIndex();
        EI.printIndex();
        
        }
        
       
        public double getSimilarity(Entity entity1, Entity entity2){
            double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
            double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
          
            return 0.7 * sim1 + 0.3 * sim2;
        }
    
}