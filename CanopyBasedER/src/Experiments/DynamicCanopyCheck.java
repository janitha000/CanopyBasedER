/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;

import BlockBuilding.DynamicCanopy;
import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.RIndex;
import Indexes.RecordIndex;
import Utilities.Serialization;

/**
 *
 * @author JanithaT
 */
public class DynamicCanopyCheck {
    static RecordIndex RI;
    static RIndex ri;
    static CanopyIndex CI;
    static EntityIndex EI;
    
    public static void main(String[] args) {
         long startTime = System.currentTimeMillis();
        RI = new RecordIndex();
        Entity entity = new Entity("AAH", "Janitha", "Tennakoon", "Kandy");
        DynamicCanopy cc = new DynamicCanopy( 0.6, 0.9);
        cc.AddToCanopy(entity);
        Entity entity2 = new Entity("AAI", "Jani", "Tennakoon", "Kandy");
        cc.AddToCanopy(entity2);
        Entity entity3 = new Entity("AAJ", "Janit", "Tennakoon", "Kandy");
        cc.AddToCanopy(entity3);
        RI = (RecordIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        ri = (RIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\rii.ser");
        ri.printIndex();
        RI.printIndex();
        CI.printIndex();
        EI.printIndex();
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
        
    }
    
    
}
