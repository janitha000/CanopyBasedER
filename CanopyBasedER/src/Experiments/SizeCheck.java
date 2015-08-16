/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;

import DataStuctures.Comparison;
import DataStuctures.Entity;
import Utilities.Soundex;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class SizeCheck {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
         List<Entity> test = new ArrayList<Entity>();
         List<String> phonetics = new ArrayList<>();
         mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "csvimport");
                try {
                 
                    test = connecton.getData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(BlockBuildongWithSQLCHECK.class.getName()).log(Level.SEVERE, null, ex);
                }
//                for (Entity test1 : test) {
//                    String code = Soundex.soundex(test1.getFirstName());
//                        phonetics.add(code);
//                        System.out.println(code);
//                }
                int count =1;
                
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
    }
}
