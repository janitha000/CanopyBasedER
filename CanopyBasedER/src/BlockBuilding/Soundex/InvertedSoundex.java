/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import DataStuctures.Entity;
import Experiments.BlockBuildongWithSQLCHECK;
import Utilities.Serialization;
import Utilities.Soundex;
import Utilities.mySqlConnection;
import java.io.File;
import java.io.Serializable;
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
public class InvertedSoundex implements Serializable {
    public Map<String,ArrayList> SoundexIndex = new HashMap<String, ArrayList>();
    private static final long serialVersionUID = 1113799434508678564L;
   
    public void /*Map<String, ArrayList>*/ getSoundexIndex(String attribute){
        List<Entity> Dataset = getRecords();
       
        ArrayList<String> temp = new ArrayList<>();
        switch(attribute){
            case "firstname":
                File f = new File("E:\\4th Year\\Research\\Imp\\Indexes\\SoundexFname.ser");
                if(f.exists())
                    SoundexIndex = (Map<String, ArrayList>) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\SoundexFname.ser");
                else {
                    for (Entity en : Dataset) {
                        String key = Soundex.soundex(en.getFirstName());
                        if(SoundexIndex.containsKey(key)){
                            temp= SoundexIndex.get(key);
                            temp.add(en.getRecordID());
                            SoundexIndex.put(key, temp);

                         }
                        else {
                            createNewBlock(key, en.getRecordID());
                        }
                    }
                }
                //Print();
                break;
        
                //return SoundexIndex;
            case "lastname":
                File ff = new File("E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser");
                if(ff.exists())
                    SoundexIndex = (Map<String, ArrayList>) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser");
                else {
                    for (Entity en : Dataset) {
                        String key = Soundex.soundex(en.getLastName());
                        if(SoundexIndex.containsKey(key)){
                            temp= SoundexIndex.get(key);
                            temp.add(en.getRecordID());
                            SoundexIndex.put(key, temp);

                         }
                        else {
                            createNewBlock(key, en.getRecordID());
                        }
                    }
                }
            //Print();
            break;
                //return SoundexIndex;
                }
        
         //return null;       
        }
        
    
    
    public void createNewBlock(String key, String recID){
        ArrayList<String> values = new ArrayList<>();
        values.add(recID);
        SoundexIndex.put(key, values);
    }
    
    
    public ArrayList<String> getCandidates(String key){
        return SoundexIndex.get(key);
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
    
//        test.add(new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
//        test.add(new Entity("AAB", "Vindya ", "Hemali", "Matara"));
//        test.add(new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
//        test.add(new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
//        test.add(new Entity("AAE", "Kavinda", "Herath", "Kandy"));
//        test.add(new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
//        test.add(new Entity("AAG", "Kosala", "Tennakoon", "Kandy"));
    
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
        IS.getSoundexIndex("lastname");
        IS.Print();
        Serialization.storeSerializedObject(IS, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser"); 
    }
}
