/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import BlockEfficiency.ComparisonPropagation;
import BlockEfficiency.ComparisonScheduling;
import DataStuctures.Entity;
import Experiments.SoundexExperiments.CanopyBuildingExperiment;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.Interfaces.CanopyIndexInterface;
import Indexes.Interfaces.EntityIndexInterface;
import Indexes.Interfaces.RIndexInterface;
import Indexes.RIndex;
import SimilarityFunctions.EntitySimilarity;
import Utilities.JaccardSimilarity;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    ComparisonScheduling CS;
     HashMap<String,Entity> Entities;
    
    public ArrayList<Entity> getSimilarRecords(CanopyIndex CIndex, EntityIndex EIndex, RIndex RIndex, String recordID, double t1){
        CI = CIndex;
        EI = EIndex;
        ri = RIndex;
        T1 = t1;
        recID = recordID;
        Entities = getEntities();
        ComparisonPropagation pp = new ComparisonPropagation();
        CS = new ComparisonScheduling();
       // Entity OriginalEntity = ri.getRecord(recID);
        Entity OriginalEntity = Entities.get(recID);
        ArrayList<Entity> results = new ArrayList<>();
        
        ArrayList<Integer> blocks = EI.getBlockList(recID);
        for (Integer block : blocks) {
            //ArrayList<Entity> entities = getEntityWithSceduling(blocks, block);
            ArrayList<Entity> entities = getEntities(block);
            for (Entity entity : entities) {
                if(pp.getLeastCommonIndex(OriginalEntity.getRecordID(), entity.getRecordID(), EIndex) >= block ){
                    if(EntitySimilarity.getSimilarity(OriginalEntity, entity, 0.6, 0.3, 0.1) > T1){
                        System.out.println(OriginalEntity.getRecordID() + " "+ entity.getRecordID() +" " + EntitySimilarity.getSimilarity(OriginalEntity, entity, 0.6, 0.3, 0.1));
                        results.add(entity);
                    }
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
            //entities.add(ri.getRecord(id));
            entities.add(Entities.get(id));
            
        }
        return entities;
    }
    
    public ArrayList<Entity> getEntityWithSceduling(ArrayList<Integer> blocks, int blockID){
        
        ArrayList<Entity> entities=new ArrayList<>();
        ArrayList<String> ids = CI.getEntityList(blockID);
        
        ArrayList<String> Scheduled = CS.scheduledRecords(blocks,ids, (EntityIndex) EI);
        
        for (String Scheduled1 : Scheduled) {
            entities.add(ri.getRecord(Scheduled1));
        }
        return entities;
    }
    public HashMap<String,Entity> getEntities(){
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
