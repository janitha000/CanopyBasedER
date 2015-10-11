/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import DataStuctures.Entity;
import Experiments.BlockBuildongWithSQLCHECK;
import Utilities.NGram;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class InvertedQgram {
    public Map<String,ArrayList> QgramIndex = new HashMap<String, ArrayList>();
    
    public Map<String, ArrayList> getQgramIndex(){
        List<Entity> Dataset = getRecords();
        ArrayList<String> temp = new ArrayList<>();
        for (Entity en : Dataset) {
            List<String> keys = NGram.ngrams(4, en.getCity());
            for (String key : keys) {
                if(QgramIndex.containsKey(key)){
                    temp = QgramIndex.get(key);
                    temp.add(en.getRecordID());
                    QgramIndex.put(key, temp);
                }
                else {
                    createNewBlock(key);
                }
            }
        }
        return QgramIndex;
        
    }
    
    public void createNewBlock(String key){
        ArrayList<String> values = new ArrayList<>();
        QgramIndex.put(key, values);
    }
    
    public ArrayList<String> getCandidates(String key){
        return QgramIndex.get(key);
    }
    
     public  List<Entity> getRecords(){
    List<Entity> test = new ArrayList<Entity>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person");
                try {
                 
                    test = connecton.getData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(BlockBuildongWithSQLCHECK.class.getName()).log(Level.SEVERE, null, ex);
                }
          return test;      
    }
     
     public void Print(){
        System.out.println("QGram INDEX");
      
        QgramIndex.entrySet().stream().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey());//+ " " + entry.getValue().size());
            ArrayList<String> list = entry.getValue();
            for (String record : list) {
                System.out.print(" " + record);
            }
            System.out.println("");
            
            //System.out.println(entry.getKey().hashCode());
        });
    }
     
     public static void main(String[] args) {
        InvertedQgram IQ = new InvertedQgram();
        IQ.getQgramIndex();
        IQ.Print();
    }
}


