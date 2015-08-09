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

/**
 *
 * @author JanithaT
 */
public class DynamicCanopy {
    CanopyIndex CI;
    RecordIndex RI;
    EntityIndex EI;
    private final double t1;
    private final double t2;
    Entity Entity;
    
    public DynamicCanopy(Entity entity, Double T1, Double T2){
        CI = new CanopyIndex();
        EI = new EntityIndex();
        RI = new RecordIndex();
        t1=T1;
        t2=T2;
        Entity = entity;
    }
    
    public void AddToCanopy(){
        if(RI.hasEntity(Entity)){
            RI.appendRecord(Entity, Entity.getRecordID());
        } else {
                
            RI.appendEntity(Entity);
            int blockID = CI.getLastIndex() +1;
            Iterator itr = CI.iterator();
            
                    
        
        
        }
    }
}
