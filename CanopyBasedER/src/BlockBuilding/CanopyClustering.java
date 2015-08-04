/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding;

import DataStuctures.Entity;
import Indexes.CanopyIndex;
import java.util.Iterator;
import java.util.List;
import Utilities.JaccardSimilarity;


/**
 *
 * @author JanithaT
 */
public class CanopyClustering {
    CanopyIndex CI;
    int noOfRecords;
    List<Entity> records;
    private final double t1;
    private final double t2;
    
    public CanopyClustering(List<Entity> Erecords, Double T1, Double T2){
        
        records = Erecords;
        CI = new CanopyIndex();
        noOfRecords = Erecords.size();
        t1 = T1;
        t2 = T2;
    }
        public void createCanopies(){
        int blockID = 1;
        while(!records.isEmpty()){
            Iterator iter = records.iterator();
            Entity firstEntity = (Entity) iter.next();
            
            iter.remove();
            records.remove(firstEntity);
            System.out.println(firstEntity.getRecordID() + " " + blockID + " " + CI);
            CI.createBlock(blockID, firstEntity.getRecordID());
                       
            while (iter.hasNext()) {
                Entity currentEntity = (Entity) iter.next();
                double similarity = getSimilarity(firstEntity, currentEntity);
                
                if (t1 <= similarity) {
                    CI.appendToBlock(blockID, currentEntity.getRecordID());
                }

                // Removal threshold:
                if (t2 <= similarity) {
                    //CI.appendToBlock(blockID, currentEntity.getRecordID());
                    iter.remove();
                    records.remove(currentEntity);
                }
            }
            blockID++;
        }
        CI.printIndex();
        }
        
       
        public double getSimilarity(Entity entity1, Entity entity2){
            double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
            double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
          
            return 0.7 * sim1 + 0.3 * sim2;
        }
    
}