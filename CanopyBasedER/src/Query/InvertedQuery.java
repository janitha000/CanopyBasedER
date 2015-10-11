/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import BlockBuilding.Soundex.InvertedSoundex;
import Comparison.ComparisonWithoutQuery;
import DataStuctures.Entity;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import SimilarityFunctions.CanopySimilarity;
import Utilities.Soundex;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author JanithaT
 */
public class InvertedQuery {

    public ArrayList<Entity> query(Entity queryEntity, ArrayList<InvertedSoundex> II, CanopyIndex CI, EntityIndex EI, Double T1, Double T2, HashMap<String, Entity> records) {
        String key = null;
        InvertedSoundex soundexI;
        ArrayList<String> CandidateResults = new ArrayList<>();

        for (int i = 0; i < II.size(); i++) {
            if (i == 0) {
                key = Soundex.soundex(queryEntity.getFirstName());
            }
            if (i == 1) {
                key = Soundex.soundex(queryEntity.getLastName());
            }

            soundexI = (InvertedSoundex) II.get(i);
            System.out.println("CANDIDATES");

            ArrayList<String> candidates = soundexI.getCandidates(key);
            if(candidates != null){
            for (String candidate : candidates) {
                System.out.print(candidate + ", ");
            }
            System.out.println("");
            }
            
            if (candidates == null) {
                continue;
            } else {
                for (String candidateEntite : candidates) {
                    Entity currentEntity = records.get(candidateEntite);
                    String currentID = currentEntity.getRecordID();
                    double similarity;
                    similarity = CanopySimilarity.getSimilarity(queryEntity, currentEntity, 0.6, 0.4, 1);
                    //System.out.println(currentEntity.getRecordID() + " " + similarity);
                    if (T1 <= similarity) {
                        CandidateResults.add(currentID);
                        //check for T2 also
                    }

                }
            }

        }
        ComparisonWithoutQuery CC = new ComparisonWithoutQuery();
        //for (String CandidateResult : CandidateResults) {
        ArrayList<Entity> results = CC.getSimilarRecords(queryEntity, CandidateResults, 0.75);
//        for (Entity result : results) {
//            System.out.println("results: " + result.getRecordID());
//        }
        //}
        return results;
    }

}
