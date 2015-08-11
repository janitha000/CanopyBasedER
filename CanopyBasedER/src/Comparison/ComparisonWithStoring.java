/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.Interfaces.CanopyIndexInterface;
import Indexes.Interfaces.EntityIndexInterface;
import Indexes.Interfaces.RIndexInterface;
import Indexes.Interfaces.SimilarityIndexInterface;
import Indexes.RIndex;
import Indexes.SimilarityIndex;
import SimilarityFunctions.EntitySimilarity;
import Utilities.Serialization;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class ComparisonWithStoring {
    CanopyIndexInterface CI;
    EntityIndexInterface EI;
    RIndexInterface ri;
    SimilarityIndexInterface SI;
    String recID;
    double T1;
    
    public ArrayList<Entity> getSimilarRecords(CanopyIndex CIndex, EntityIndex EIndex, RIndex RIndex, SimilarityIndex SIndex,String recordID, double t1){
        CI = CIndex;
        EI = EIndex;
        ri = RIndex;
        SI = SIndex;
        T1 = t1;
        double similarity = 0.0;
        recID = recordID;
        
        SI.createBlock(recID);
        Entity OriginalEntity = ri.getRecord(recID);
        ArrayList<Entity> results = new ArrayList<>();
        
        ArrayList<Integer> blocks = EI.getBlockList(recID);
        for (Integer block : blocks) {
            ArrayList<Entity> entities = getEntities(block);
            for (Entity entity : entities) {
                similarity = EntitySimilarity.getSimilarity(OriginalEntity, entity, 0.6, 0.3, 0.1);
                if( similarity> T1){
                    results.add(entity);
                    SI.appendToBlock(recID, entity.getRecordID(), similarity);
                }
            }
        }
        Serialization.storeSerializedObject(SI, "E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        return results;
        
    }
    
    public ArrayList<Entity> getEntities(int blockID){
        ArrayList<Entity> entities=new ArrayList<>();
        ArrayList<String> ids = CI.getEntityList(blockID);
        if(ids.size() ==1){
            System.out.println("Only one element");
        }
        for (String id : ids) {
            //System.out.println("Canopy ID is "+ id);
            entities.add(ri.getRecord(id));
            
        }
        return entities;
    }
}
