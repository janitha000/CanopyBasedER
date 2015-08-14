/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;


import BlockBuilding.CanopyClustering;
import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class BlockBuildongWithSQLCHECK {
     static CanopyIndex CI; 
     static EntityIndex EI;
     
     public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CI = new CanopyIndex();
        List<Entity> test = new ArrayList<Entity>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "csvimport");
                try {
                 
                    test = connecton.getData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(BlockBuildongWithSQLCHECK.class.getName()).log(Level.SEVERE, null, ex);
                }
                CanopyClustering CC = new CanopyClustering(test, 0.6, 0.9);
        CC.createCanopies();
        
         long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
                
                
               
    }
     
     
}
