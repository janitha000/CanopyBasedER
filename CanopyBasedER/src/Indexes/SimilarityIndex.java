/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes;

import DataStuctures.SimilarityBlocks;
import Indexes.Interfaces.SimilarityIndexInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JanithaT
 */
public class SimilarityIndex implements Serializable, SimilarityIndexInterface{
    public Map<String,ArrayList> SI = new HashMap<>();
    private static final long serialVersionUID = 1113799434508671000L;
    
    @Override
    public void createBlock(String ID){
       
        ArrayList<SimilarityBlocks> similarities = new ArrayList<>();
        SI.put(ID, similarities);
    }
    
    @Override
    public void appendToBlock(String ID, String recID ,double similarity){
        ArrayList<SimilarityBlocks> similarities = SI.get(ID);
        similarities.add(new SimilarityBlocks(recID, similarity));
        SI.put(ID, similarities);
    }
    
    @Override
    public void printIndex(){
        System.out.println("Similarity INDEX");
        SI.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey());
            ArrayList<SimilarityBlocks> list = entry.getValue();
            for (SimilarityBlocks record : list) {
                System.out.println(record.getRecID() + " " + record.getSimilarity() );
            }
            
            //System.out.println(entry.getKey().hashCode());
        });
    }
    
}
