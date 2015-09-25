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
        ArrayList<InvertedSoundex> II = new InvertedIndexes().getInvertedIndexes(attributes,false);
        int saveCount = 0;
        for (InvertedSoundex SoundexIndexes : II) {
            if(saveCount == 0){
                Serialization.storeSerializedObject(SoundexIndexes, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexFname.ser");
                
            }
            if(saveCount == 1){
                Serialization.storeSerializedObject(SoundexIndexes, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser");
               
            }
            saveCount++;
        }
        InvertedSoundex soundexI;
        int id = 1;
        blockID = 1;
        int attrID = 0;
        //InvertedSoundex soundexI = new InvertedSoundex();
        //IS = soundexI.getSoundexIndex("firstname");
        //SoundexIndexes.add(IS);
        
        
            String key = null;
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
                    
                    for(int i=0; i< attr.length; i++){
                        if(i ==0)
                            key= Soundex.soundex(firstEntity.getFirstName());
                        if(i == 1)
                            key= Soundex.soundex(firstEntity.getLastName());
                        soundexI = II.get(i);
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
                         similarity = CanopySimilarity.getSimilarity(firstEntity, currentEntity, 0.7, 0.3,1);

                         if (t1 <= similarity) {
                            if(CI.getEntityList(blockID).contains(currentID))
                                continue;
                            CI.appendToBlock(blockID, currentID);
                            EI.appendToBlock(currentID, blockID);
                            EI.sort(currentID);
                            //ids.add(currentEntity.getRecordID());
                        }

                        // Removal threshold:
                        if (t2 <= similarity) {
                            //records.remove(20);

                            
                            records.remove(currentID);
                            deletion.add(currentID);

                            }

                    }
                        for (String deletion1 : deletion) {
                            soundexI.getCandidates(key).remove(deletion1);
                        }
                        
                    }
                    //System.out.println(id);
                    CI.setLastIndex(blockID);
                    blockID++;
                    long stopTime = System.currentTimeMillis();
                    long elapsedTime = stopTime - startTime;
                    if(id % 1000 == 0){
                        System.out.println(" Elapsed time is " +elapsedTime/1000);
                        startTime = stopTime;
                    }
                    id++;
                   }
              attrID++;
        
        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        CI.setLastIndex(id);
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
