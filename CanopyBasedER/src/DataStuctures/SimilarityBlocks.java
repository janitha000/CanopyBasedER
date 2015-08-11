/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStuctures;

import java.io.Serializable;

/**
 *
 * @author JanithaT
 */
public class SimilarityBlocks implements Serializable {
    public String recID;
    public double similarity;
    
    public SimilarityBlocks(String ID, double sim){
        recID = ID;
        similarity = sim;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    
    
}
