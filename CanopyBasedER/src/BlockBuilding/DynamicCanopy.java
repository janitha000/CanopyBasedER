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
import Utilities.JaccardSimilarity;
import Utilities.Serialization;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class DynamicCanopy {
    CanopyIndex CI;
    RecordIndex RI;
    EntityIndex EI;
    RIndex ri;
    private final double t1;
    private final double t2;
    Entity Entity;
    
    public DynamicCanopy( Double T1, Double T2){
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        RI = (RecordIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        ri = (RIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\rii.ser");
        t1=T1;
        t2=T2;
        
    }
    
    public void AddToCanopy(Entity entity){
        ri.appendEntity(entity);
        Entity = entity;
        if(RI.hasEntity(Entity)){
            if(!RI.getDuplicates(Entity).contains(Entity.getRecordID())){       //not needed if same record ID not comes again
                RI.appendRecord(Entity, Entity.getRecordID());
                Serialization.storeSerializedObject(RI, "E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
            }
        } else {
            RI.appendEntity(Entity);
            int blockID = CI.getLastIndex() +1;
            ArrayList<Integer> ids = new ArrayList<>();
            Boolean added = false;
            
            Iterator itr = CI.keys().iterator();
            while(itr.hasNext()){
                int currentBlockID = (int) itr.next();
                String recID =  CI.canopyRecordID(currentBlockID);
                Entity currentEntity = ri.getRecord(recID);
                double similarity = getSimilarity(Entity, currentEntity);
                
                if (t1 <= similarity) {
                    ids.add(currentBlockID);
                    //ids.add(currentEntity.getRecordID());
                }

                // Removal threshold:
                if (t2 <= similarity) {
                    CI.appendToBlock(blockID, entity.getRecordID());
                    EI.createBlock(recID, blockID);
                    added = true;
                    break;
                }
                
                
                
                //*********************************
                
                /*mySqlConnection connecton = new mySqlConnection("researchtest", "root", "jibtennakoon", "person");
                try {
                 
                    Entity en = connecton.getRecord(recID);
                    System.out.println("Record name " + en.getFirstName());
                    
                } catch (SQLException ex) {
                    Logger.getLogger(DynamicCanopy.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Entity currentEntity =
                    //double similarity = getSimilarity(Entity, currentEntity);
                
                //****************************************
                
                
                */
            }
            if(!added){
                for (int blocksID : ids) {
                    CI.appendToBlock(blocksID,entity.getRecordID());
                    EI.appendToBlock(entity.getRecordID(), blocksID);
                }
            }
            
        }
        
        
           Serialization.storeSerializedObject(RI, "E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");         
           Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser"); 
           Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser"); 
           Serialization.storeSerializedObject(ri, "E:\\4th Year\\Research\\Imp\\Indexes\\rri.ser"); 
        
        
    }
    
    public double getSimilarity(Entity entity1, Entity entity2){
            double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
            double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
          
            return 0.7 * sim1 + 0.3 * sim2;
        }
}
