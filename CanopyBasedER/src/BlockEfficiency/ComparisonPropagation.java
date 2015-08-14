/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockEfficiency;

import Indexes.EntityIndex;
import Utilities.Serialization;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class ComparisonPropagation {
    EntityIndex EI ;
   
    public int getLeastCommonIndex(String rec1, String rec2, EntityIndex EII){
        EI = EII;
        ArrayList blocks1 = EI.getBlockList(rec1);
        ArrayList blocks2 = EI.getBlockList(rec2);
        
        for (Object blocks : blocks1) {
            for (Object bblocks : blocks2) {
                if(blocks == bblocks){
                    System.out.println(rec1 + " " + rec2 + " " + blocks);
                    return (int) blocks;
                }
            }
        }
        return 0;
    }
}
