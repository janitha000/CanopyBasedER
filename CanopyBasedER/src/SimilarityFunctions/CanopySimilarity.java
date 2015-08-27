/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimilarityFunctions;

import DataStuctures.Entity;
import Utilities.EditDistanceSimilarity;
import Utilities.JaccardSimilarity;

/**
 *
 * @author JanithaT
 */
public class CanopySimilarity {
    public static double getSimilarity(Entity entity1, Entity entity2, double weight1, double weight2){
            double sim1 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
            double sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
          
            return weight1 * sim1 + weight2 * sim2;
        }
    
     public static double getSimilarity(Entity entity1, Entity entity2, double weight1, double weight2, int DistanceMeasure){
         double sim1, sim2; 
         switch(DistanceMeasure){
                case 1:
                    sim1= JaccardSimilarity.getStringJaccardSimilarity(entity1.getFirstName().split(""), entity2.getFirstName().split(""));
                    sim2 = JaccardSimilarity.getStringJaccardSimilarity(entity1.getLastName().split(""), entity2.getLastName().split(""));
                    return weight1 * sim1 + weight2 * sim2;
                case 2:
                    sim1 = EditDistanceSimilarity.minDistance(entity1.getFirstName(), entity2.getFirstName());
                    sim2 = EditDistanceSimilarity.minDistance(entity1.getLastName(), entity2.getLastName());
                    return weight1 * sim1 + weight2 * sim2;
            }
            return 0;
            
        }
    
}
