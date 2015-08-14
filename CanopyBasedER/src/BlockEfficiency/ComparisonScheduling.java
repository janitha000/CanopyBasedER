/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockEfficiency;

import DataStuctures.Comparison;
import Indexes.EntityIndex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author JanithaT
 */
public class ComparisonScheduling {
    EntityIndex EI;
    
    public ArrayList<String> scheduledRecords( ArrayList<Integer> originalBlocks, ArrayList<String> currentRecordIds, EntityIndex EII){
        EI = EII;
        ArrayList<Integer> blocks1 = originalBlocks;
        ArrayList<String> records = currentRecordIds;
        ArrayList<Comparison> temp =new ArrayList<>();
        ArrayList<String> Scheduled = new ArrayList<>();
        for (String record : records) {
            double ES = getES(blocks1,record);
            //System.out.println(ES + " " + record);
            temp.add(new Comparison(record, ES));
        }

        Collections.sort(temp, new Comparator<Comparison>(){
            @Override public int compare(Comparison p1, Comparison p2) {
                return Double.compare(p2.getEnitiySimilarity(), p1.getEnitiySimilarity());
            }
        });
        for (Comparison temp1 : temp) {
            Scheduled.add(temp1.getRecordID());
        }
        return Scheduled;
        
    }
    public double getES(ArrayList<Integer> originalBlocks, String currentRecord){
        double common = 0;
        double EntitySimilarity = 0;
        ArrayList<Integer> recordsInBlock = EI.getBlockList(currentRecord);
        for (Object blocks : originalBlocks) {
            for (Integer bblocks : recordsInBlock) {
                if(blocks == bblocks){
                    common++;
                }
                
            }
        }
        
        return common / (originalBlocks.size() + recordsInBlock.size() - common);
    }
}
