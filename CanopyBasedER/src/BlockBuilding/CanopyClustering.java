/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding;

import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.RecordIndex;
import java.util.Iterator;
import java.util.List;
import Utilities.JaccardSimilarity;
import java.util.ArrayList;


/**
 *
 * @author JanithaT
 */
public class CanopyClustering {
    CanopyIndex CI;
    EntityIndex EI;
    RecordIndex RI;
    int noOfRecords;
    List<Entity> records;
    private final double t1;
    private final double t2;
    
    public CanopyClustering(List<Entity> Erecords, Double T1, Double T2){
        
        records = Erecords;
        CI = new CanopyIndex();
        EI = new EntityIndex();
        RI = new RecordIndex();
        noOfRecords = Erecords.size();
        t1 = T1;
        t2 = T2;
    }
        public void createCanopies(){
        int blockID = 1;
        while(!records.isEmpty()){
            Iterator iter = records.iterator();
            Entity firstEntity = (Entity) iter.next();
            String recordID = firstEntity.getRecordID();
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
            System.out.println("Entity: " + recordID);
            iter.remove();
            records.remove(firstEntity);
            
            //System.out.println(firstEntity.getRecordID() + " " + blockID + " " + CI);
            //RI.appendEntity(firstEntity);
            CI.createBlock(blockID, recordID);
            EI.appendToBlock(recordID, blockID);
            //ArrayList<String> ids = new ArrayList<String>();
                       
            while (iter.hasNext()) {
                Entity currentEntity = (Entity) iter.next();
                String currentID = currentEntity.getRecordID();
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
                        continue;                    
                    } else {
                        RI.appendEntity(currentEntity);
                    }
                }
                   
                double similarity = getSimilarity(firstEntity, currentEntity);
                System.out.println("\t " + currentID);
                
                if (t1 <= similarity) {
                    //RI.appendEntity(currentEntity);
                    CI.appendToBlock(blockID, currentID);
                    EI.appendToBlock(currentID, blockID);
                    //ids.add(currentEntity.getRecordID());
                }

                // Removal threshold:
                if (t2 <= similarity) {
                    iter.remove();
                    records.remove(currentEntity);
                    System.out.println("REMOVED " + currentID);
                }
            }
            RI.printIndex();
            //CI.appendToBlockAyyayList(blockID, ids);
            blockID++;
        }
        //RI.printIndex();
        //CI.printIndex();
        //EI.printIndex();
        }
        
       
        public double getSimilarity(Entity entity1, Entity entity2){
            double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
            double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
          
            return 0.7 * sim1 + 0.3 * sim2;
        }
    
}