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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JanithaT
 */
public class EICheckwithCI {
    static CanopyIndex CI; 
    static EntityIndex EI;
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CI = new CanopyIndex();
        List<Entity> test = new ArrayList<Entity>();
        test.add(new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
        test.add(new Entity("AAB", "Vindya ", "Hemali", "Matara"));
        test.add(new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
        test.add(new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
        test.add(new Entity("AAE", "Kavinda", "Herath", "Kandy"));
        test.add(new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
        test.add(new Entity("AAG", "Janith", "Tenna", "Kandy"));
        
        CanopyClustering CC = new CanopyClustering(test, 0.6, 0.9);
        CC.createCanopies();
        
         long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
        
    }
}
