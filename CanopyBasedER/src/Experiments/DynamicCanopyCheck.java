/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;

import BlockBuilding.DynamicCanopy;
import DataStuctures.Entity;
import Indexes.RecordIndex;
import Utilities.Serialization;

/**
 *
 * @author JanithaT
 */
public class DynamicCanopyCheck {
    static RecordIndex RI;
    
    public static void main(String[] args) {
        RI = new RecordIndex();
        Entity entity = new Entity("AAH", "Janitha", "Tennakoon", "Kandy");
        DynamicCanopy cc = new DynamicCanopy( 0.6, 0.9);
        cc.AddToCanopy(entity);
        Entity entity2 = new Entity("AAI", "Jani", "Tennakoon", "Kandy");
        cc.AddToCanopy(entity2);
        RI = (RecordIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\RI.ser");
        RI.printIndex();
    }
    
    
}
