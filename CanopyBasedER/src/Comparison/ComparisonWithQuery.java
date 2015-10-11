/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import BlockEfficiency.ComparisonScheduling;
import DataStuctures.Entity;
import Experiments.SoundexExperiments.CanopyBuildingExperiment;
import Indexes.Interfaces.CanopyIndexInterface;
import Indexes.Interfaces.EntityIndexInterface;
import Indexes.Interfaces.RIndexInterface;
import Indexes.SimilarityIndex;
import SimilarityFunctions.EntitySimilarity;
import Utilities.Serialization;
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
public class ComparisonWithQuery {
    CanopyIndexInterface CI;
    EntityIndexInterface EI;
    RIndexInterface ri;
    SimilarityIndex SI;
    String recID;
    double T1;
    ComparisonScheduling CS;
    HashMap<String, Entity> Entities;

    public ArrayList<Entity> getSimilarRecords(Entity queryEntity, ArrayList<String> CandidateResults, double t1) {

        T1 = t1;
        SI = (SimilarityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        Entities = getEntities();

        Entity OriginalEntity = queryEntity;
        if(SI.keys().contains(OriginalEntity.getRecordID())){
            
        }
        ArrayList<Entity> results = new ArrayList<>();

        for (String candidates : CandidateResults) {
            //System.out.println(candidates);

            Entity currentEntity = Entities.get(candidates);
            System.out.println(OriginalEntity.getRecordID() + " " + currentEntity.getRecordID() + " " + EntitySimilarity.getSimilarity(OriginalEntity, currentEntity, 0.6, 0.3, 0.1));
            if (EntitySimilarity.getSimilarity(OriginalEntity, currentEntity, 0.6, 0.3, 0.1) > T1) {

                results.add(currentEntity);
            }

        }
        //ArrayList<Entity> entities = getEntities(block);
        Serialization.storeSerializedObject(SI, "E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        return results;

    }

    public ArrayList<Entity> getEntities(int blockID) {
        ArrayList<Entity> entities = new ArrayList<>();
        ArrayList<String> ids = CI.getEntityList(blockID);
        if (ids.size() == 1) {
            System.out.println("Only one element");
        }
        for (String id : ids) {
            //System.out.println("Canopy ID is "+ id);
            //entities.add(ri.getRecord(id));
            entities.add(Entities.get(id));

        }
        return entities;
    }

    public HashMap<String, Entity> getEntities() {
        HashMap<String, Entity> test = new HashMap<String, Entity>();
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
