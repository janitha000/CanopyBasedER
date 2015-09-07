/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;

import Comparison.ComparisonWithStoring;
import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.RIndex;
import Indexes.RecordIndex;
import Indexes.SimilarityIndex;
import Utilities.Serialization;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class ComparisonWithStroringCheck {
    static RecordIndex RI;
    static RIndex ri;
    static CanopyIndex CI;
    static EntityIndex EI;
    static SimilarityIndex SI;
    
    public static void main(String[] args) {
        SimilarityIndex SINew = new SimilarityIndex();
        Serialization.storeSerializedObject(SINew, "E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        long startTime = System.currentTimeMillis();
        
        RI = (RecordIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        ri = (RIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\rii.ser");
        SI = (SimilarityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        
        ComparisonWithStoring cc = new ComparisonWithStoring();
        ArrayList<Entity> results = cc.getSimilarRecords(CI, EI, ri, SI, "1" , 0.8);
        
        for (Entity result : results) {
            System.out.println(result.getRecordID() + " " +result.getFirstName()+ " " + result.getLastName()+ " " + result.getCity());
        }
        SI.printIndex();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
    }
}
