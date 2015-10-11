/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments.SoundexExperiments;

import BlockBuilding.Soundex.InvertedIndexes;
import BlockBuilding.Soundex.InvertedSoundex;
import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Query.InvertedQuery;
import Utilities.Serialization;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class QueryExperiment {

    static ArrayList<Entity> results;
    public static int TruePositives;
    public static int FalsePositives;
    static int TrueNegatives;
    static int FalseNegatives;
    static double Precision;
    static HashMap<String, String> evaluationData;
    static HashMap<String, HashSet<String>> OtherData;
    static ArrayList<Entity> Queries;

    public static void main(String[] args) {
        Queries = new ArrayList<>();
        InvertedIndexes I = new InvertedIndexes();
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("firstname");
        attributes.add("lastname");
        HashMap<String, Entity> records = getRecords();

        CanopyIndex CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EntityIndex EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");

        ArrayList<InvertedSoundex> II = I.getInvertedIndexes(attributes, true);

        //Queries.add( new Entity("6", "mia", "antropov", ""));
        //Queries.add(new Entity("660", "jamest", "burton", ""));
        InvertedQuery IQ = new InvertedQuery();
        
        
        /* ******************************************************************************** */
        
        evaluationData = new HashMap<>();
        OtherData = new HashMap<>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person2");
        try {

            evaluationData = connecton.getEvaluationDatabase();
            OtherData = connecton.getEvaluationDatabaseResults();
            Queries = connecton.getQueries();

        } catch (SQLException ex) {
            Logger.getLogger(CanopyBuildingExperiment.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Entity query : Queries) {
            results = IQ.query(query, II, CI, EI, 0.6, 0.9, records);
            Set<Entity> resultSet = new HashSet<Entity>(results);
            for (Entity resultSet1 : resultSet) {
                System.out.println("Results: " + resultSet1.getRecordID());
            }
            
            Evaluation(resultSet, query);
        }
        


        /* -------------------------------------------------------------------------------*/
        

        // Evaluation(resultSet,query);
        

        System.out.println("TRUE POSITIVES: " + TruePositives);
        System.out.println("FALSE POSITIVES: " + FalsePositives);
        System.out.println("FALSE NEGATIVES: " + FalseNegatives);
        System.out.println("PRECISION: " + Precision);

    }

    public static void Evaluation(Set<Entity> resultSet, Entity QueryEntity) {

//        OtherData.entrySet().stream().forEach((entry) -> {
//            System.out.print("Key : " + entry.getKey() + "Value: " + entry.getValue());
//            System.out.println("");
//        });
        String other = evaluationData.get(QueryEntity.getRecordID());
        HashSet<String> otherDatas = new HashSet<>();

        for (Entity result : resultSet) {
            String id = evaluationData.get(result.getRecordID());
            otherDatas.add(result.getRecordID());

            if (id.equals(other)) {
                TruePositives++;
            }
            if (!id.equals(other)) {
                FalsePositives++;
            }
        }

        HashSet<String> Oresults = OtherData.get(other);
        int size = Oresults.size();
        Oresults.retainAll(otherDatas);
        FalseNegatives = size - Oresults.size();

        Precision = TruePositives / Double.valueOf(TruePositives + FalsePositives) * 100;

    }

    public static HashMap<String, Entity> getRecords() {
        HashMap<String, Entity> test = new HashMap<String, Entity>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person2");
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
