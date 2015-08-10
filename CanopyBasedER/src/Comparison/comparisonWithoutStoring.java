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
import Indexes.RIndex;
import Utilities.JaccardSimilarity;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class comparisonWithoutStoring {
    CanopyIndexInterface CI;
    EntityIndexInterface EI;
    RIndexInterface ri;
    String recID;
    double T1;
    
    public ArrayList<Entity> getSimilarRecords(CanopyIndex CIndex, EntityIndex EIndex, RIndex RIndex, String recordID, double t1){
        CI = CIndex;
        EI = EIndex;
        ri = RIndex;
        T1 = t1;
        recID = recordID;
        Entity OriginalEntity = ri.getRecord(recID);
        ArrayList<Entity> results = new ArrayList<>();
        
        ArrayList<Integer> blocks = EI.getBlockList(recID);
        for (Integer block : blocks) {
            ArrayList<Entity> entities = getEntities(block);
            for (Entity entity : entities) {
                if(getSimilarity(OriginalEntity, entity) > T1){
                    results.add(entity);
                }
            }
        }
        
        
        
        
        
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
    
    public double getSimilarity(Entity entity1, Entity entity2){
        double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
        double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
        double sim3 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getCity().split(""), entity2.getCity().split(""));
        
            return 0.6 * sim1 + 0.3 * sim2 + 0.1 * sim3;
    }
}
