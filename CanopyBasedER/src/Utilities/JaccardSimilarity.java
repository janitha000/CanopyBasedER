/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author JanithaT
 */
public class JaccardSimilarity {
    
        public static double getJaccardSimilarity(int[] tokens1, int[] tokens2) {
        double commonTokens = 0.0;
        int noOfTokens1 = tokens1.length;
        int noOfTokens2 = tokens2.length;
        for (int i = 0; i < noOfTokens1; i++) {
            for (int j = 0; j < noOfTokens2; j++) {
                if (tokens2[j] < tokens1[i]) {
                    continue;
                }

                if (tokens1[i] < tokens2[j]) {
                    break;
                }

                if (tokens1[i] == tokens2[j]) {
                    commonTokens++;
                }
            }
        }
        return commonTokens / (noOfTokens1 + noOfTokens2 - commonTokens);
    }
}
