/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import DataStuctures.Entity;
import Experiments.BlockBuildongWithSQLCHECK;
import Utilities.Soundex;
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
public class InvertedSoundex {
    public Map<String,ArrayList> SoundexIndex = new HashMap<String, ArrayList>();
   
    public Map<String, ArrayList> getSoundexIndex(){
        List<Entity> Dataset = getRecords();
        ArrayList<String> temp = new ArrayList<>();
        for (Entity en : Dataset) {
            String key = Soundex.soundex(en.getFirstName());
            if(SoundexIndex.containsKey(key)){
               temp= SoundexIndex.get(key);
//               if(en.getRecordID().equals("000000007231"))
//                    System.out.println("");
                temp.add(en.getRecordID());
                SoundexIndex.put(key, temp);
                
            }
            else {
                createNewBlock(key);
            }
        }
        //Print();
        return SoundexIndex;
        
    }
    
    
    public void createNewBlock(String key){
        ArrayList<String> values = new ArrayList<>();
        SoundexIndex.put(key, values);
    }
    
    
    public ArrayList<String> getCandidates(String key){
        return SoundexIndex.get(key);
    }
    
    
    public  List<Entity> getRecords(){
    List<Entity> test = new ArrayList<Entity>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "csvimport");
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
        System.out.println("Soundex INDEX");
      
        SoundexIndex.entrySet().stream().forEach((entry) -> {
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
        InvertedSoundex IS = new InvertedSoundex();
        IS.getSoundexIndex();
    }
}
