/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStuctures;

/**
 *
 * @author JanithaT
 */
public class Comparison {
    public String recordID;
    public Double EnitiySimilarity;

    public Comparison(String rec, Double ES){
        recordID = rec;
        EnitiySimilarity = ES;
    }
    
    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public Double getEnitiySimilarity() {
        return EnitiySimilarity;
    }

    public void setEnitiySimilarity(Double EnitiySimilarity) {
        this.EnitiySimilarity = EnitiySimilarity;
    }
    
    
}
