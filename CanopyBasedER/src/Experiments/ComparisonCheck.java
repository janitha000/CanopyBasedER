/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;

import Comparison.comparisonWithoutStoring;
import DataStuctures.Entity;
import static Experiments.DynamicCanopyCheck.RI;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.RIndex;
import Indexes.RecordIndex;
import Utilities.Serialization;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class ComparisonCheck {
    static RecordIndex RI;
    static RIndex ri;
    static CanopyIndex CI;
    static EntityIndex EI;
    
    public static void main(String[] args) {
       
        RI = (RecordIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        ri = (RIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\rii.ser");
        
         long startTime = System.currentTimeMillis();
        comparisonWithoutStoring cc = new comparisonWithoutStoring();
        ArrayList<Entity> results = cc.getSimilarRecords(CI, EI, "1",0.8);
        for (Entity result : results) {
            System.out.println("results "+ result.getRecordID() + " " + result.getFirstName()+ " " + result.getLastName());
        }
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
    }
    
    
}
