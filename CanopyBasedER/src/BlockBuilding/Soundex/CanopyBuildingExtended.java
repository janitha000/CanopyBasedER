package BlockBuilding.Soundex;

import DataStuctures.Entity;
import DataStuctures.SimilarityBlocks;
import Experiments.SoundexExperiments.CanopyBuildingExperiment;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import SimilarityFunctions.CanopySimilarity;
import Utilities.Serialization;
import Utilities.Soundex;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class CanopyBuildingExtended {
    CanopyIndex CI;
    EntityIndex EI;
    int noOfRecords;
    ArrayList<Map<String,ArrayList>> SoundexIndexes;
    ArrayList<InvertedQgram> QgramIndexes;
    private final double t1;
    private final double t2;
    int blockID;
    Map<String, ArrayList> IS;
    
    public CanopyBuildingExtended( Double T1, Double T2){
        
        CI = new CanopyIndex();
        EI = new EntityIndex();
        
        t1 = T1;
        t2 = T2;
        blockID=0;
    }
    
    public void CreateCanopies(HashMap<String,Entity> Erecords, ArrayList<String> attributes){
        //HashMap<String,Entity> records = Erecords;
        //noOfRecords = Erecords.size();
        String[] attr = new String[attributes.size()];
        ArrayList<InvertedSoundex> II = new InvertedIndexes().getInvertedIndexes(attributes);
        InvertedSoundex soundexI;
        int id = 1;
        blockID = 1;
        int attrID = 0;
        //InvertedSoundex soundexI = new InvertedSoundex();
        //IS = soundexI.getSoundexIndex("firstname");
        //SoundexIndexes.add(IS);
        
        for (InvertedSoundex IS : II) {
            soundexI = (InvertedSoundex) IS;
             String attribute = attr[attrID];
             HashMap<String,Entity> records = Erecords;
             noOfRecords = Erecords.size();
             HashMap<String,Entity> candidateEntitiyIndex = getcandidateEntities();
             long startTime = System.currentTimeMillis();
             
              while(!records.isEmpty()){
                  Iterator iter = records.entrySet().iterator();
                    Map.Entry pair = (Map.Entry)iter.next();
                    Entity firstEntity = (Entity) pair.getValue();
                    String recordID = firstEntity.getRecordID();
            
                    iter.remove();
                    CI.createBlock(blockID, recordID);
                    EI.appendToBlock(recordID, blockID);
                    
                    String key = Soundex.soundex(firstEntity.getFirstName());
                    soundexI.getCandidates(key).remove(recordID);
                    
                    ArrayList<String> candidates = soundexI.getCandidates(key);
                    ArrayList<Entity> candidateEntites = new ArrayList<>();
                    ArrayList<String> deletion = new ArrayList<>();
                    
                    for (String candidateEntite : candidates) {
                        Entity currentEntity = records.get(candidateEntite);
                        if(currentEntity == null){
                            continue;
                        }
                        //System.out.println(currentEntity.getRecordID());
                        if(candidateEntite.equals(recordID)){
                            continue;
                        }
                        String currentID = currentEntity.getRecordID();
                        double similarity;
                         similarity = CanopySimilarity.getSimilarity(firstEntity, currentEntity, 0.7, 0.3,2);

                         if (t1 <= similarity) {
                            CI.appendToBlock(blockID, currentID);
                            EI.appendToBlock(currentID, blockID);
                            EI.sort(currentID);
                            //ids.add(currentEntity.getRecordID());
                        }

                        // Removal threshold:
                        if (t2 <= similarity) {
                            //records.remove(20);

                            if(currentID.equals("000000007231")){
                                System.out.println("");
                            }
                            records.remove(currentID);
                            deletion.add(currentID);

                            }

                    }
                    for (String deletion1 : deletion) {
                        soundexI.getCandidates(key).remove(deletion1);
                    }  
                    
                    CI.setLastIndex(blockID);
                    blockID++;
                    long stopTime = System.currentTimeMillis();
                    long elapsedTime = stopTime - startTime;
                    if(id % 10000 == 0){
                        System.out.println(" Elapsed time is " +elapsedTime/1000);
                        startTime = stopTime;
                    }
                    id++;
                   }
              }
        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        
        CI.printIndex();
        EI.printIndex();
        
    }

        
    public HashMap<String,Entity> getcandidateEntities(){
        HashMap<String,Entity> test = new HashMap<String, Entity>();
         mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person");
                try {
                 
                    test = connecton.getInvertedIndexData();
                    //for (Entity en : test) {
                        //System.out.println("Record name " + en.getFirstName());
                    //}
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CanopyBuildingExperiment.class.getName()).log(Level.SEVERE, null, ex);
                }
                test.entrySet().stream().forEach((entry) -> {
                //System.out.print("Key : " + entry.getKey());
                Entity list = entry.getValue();
                
                //System.out.println(" " + list.getFirstName() );

            
            //System.out.println(entry.getKey().hashCode());
        });
                return test;
    }
}