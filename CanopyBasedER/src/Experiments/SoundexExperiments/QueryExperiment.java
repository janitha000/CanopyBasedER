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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class QueryExperiment {
    
    public static void main(String[] args) {
        InvertedIndexes I = new InvertedIndexes();
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("firstname");
        attributes.add("lastname");
        HashMap<String,Entity> records = getRecords();
        
        
        CanopyIndex CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EntityIndex EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        
        ArrayList<InvertedSoundex> II = I.getInvertedIndexes(attributes,true);
        
        Entity query = new Entity("QQQ", "ADAMS", "BON", "Kandy");
        
        InvertedQuery IQ = new InvertedQuery();
        IQ.query(query, II, CI, EI, 0.6, 0.9, records);
        
    }
    
    public static HashMap<String,Entity> getRecords(){
          HashMap<String,Entity> test = new HashMap<String, Entity>();
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
