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
        Entity = entity;
        if(RI.hasEntity(Entity)){
            if(!RI.getDuplicates(Entity).contains(Entity.getRecordID())){       //not needed if same record ID not comes again
                RI.appendRecord(Entity, Entity.getRecordID());
                Serialization.storeSerializedObject(RI, "E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
            }
        } else {
            RI.appendEntity(Entity);
            int blockID = CI.getLastIndex();
            ArrayList<String> ids = new ArrayList<>();
            
            Iterator itr = CI.keys().iterator();
            while(itr.hasNext()){
                String recID =  CI.canopyRecordID((int) itr.next());
                Entity currentEntity = ri.getRecord(recID);
                System.out.println(currentEntity.getFirstName());
                
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
        }
                    
        
        
        
    }
}
