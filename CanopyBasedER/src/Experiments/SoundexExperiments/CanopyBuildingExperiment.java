/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments.SoundexExperiments;

import BlockBuilding.CanopyClustering;
import BlockBuilding.Soundex.CanopyBuilding;
import BlockBuilding.Soundex.CanopyBuildingExtended;
import BlockBuilding.Soundex.CanopyDynamicSoundex;
import DataStuctures.Entity;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class CanopyBuildingExperiment {
    public static void main(String[] args) {
        
    
     long startTime = System.currentTimeMillis();
        //CI = new CanopyIndex();
        HashMap<String,Entity> test = new HashMap<>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person");
                try {
                 
                    test = connecton.getInvertedIndexData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CanopyBuildingExperiment.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                //CanopyBuilding CC = new CanopyBuilding(0.6, 0.9);
                //CC.CreateCanopies(test);
                CanopyBuildingExtended cc = new CanopyBuildingExtended(0.6, 0.9);
                ArrayList<String> attr = new ArrayList<>();
                attr.add("firstname");
                attr.add("lastname");
                cc.CreateCanopies(test, attr);
                
                CanopyDynamicSoundex CD = new CanopyDynamicSoundex(0.6, 0.9);
                Entity add = new Entity("BBB", "Kasun", "Silva", "Kandy");
                Entity add2 = new Entity("CCC", "Saman", "Dassanayaka", "Kandy");
                Entity add3 = new Entity("DDD", "Saman", "Dasanayaka", "Kandy");
                CD.addToCanopy(add, attr);
                CD.addToCanopy(add2, attr);
                CD.addToCanopy(add3, attr);
        
         long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
    }
}
