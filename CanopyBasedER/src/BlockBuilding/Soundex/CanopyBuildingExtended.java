package BlockBuilding.Soundex;

import DataStuctures.Entity;
import Experiments.SoundexExperiments.CanopyBuildingExperiment;
import Indexes.CanopyIndex;
import Indexes.EntityIndex;
import Indexes.SimilarityIndex;
import SimilarityFunctions.CanopySimilarity;
import Utilities.Serialization;
import Utilities.Soundex;
import Utilities.mySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    SimilarityIndex SI;
    
    ArrayList<Map<String, ArrayList>> SoundexIndexes;
    ArrayList<InvertedQgram> QgramIndexes;
    Map<String, ArrayList> IS;
    private final double t1;
    private final double t2;
    int noOfRecords;
    int blockID;
    

    public CanopyBuildingExtended(Double T1, Double T2) {

        CI = new CanopyIndex();
        EI = new EntityIndex();
        SI = new SimilarityIndex();

        t1 = T1;
        t2 = T2;
        blockID = 0;
    }

    public void CreateCanopies(HashMap<String, Entity> Erecords, ArrayList<String> attributes) {
        
        HashMap<String, Entity> records = Erecords;

        String[] attr = new String[attributes.size()];
        ArrayList<InvertedSoundex> II = new InvertedIndexes().getInvertedIndexes(attributes, false);
        
        //First time save both inverted indexes
        int saveCount = 0;
        for (InvertedSoundex SoundexIndexes : II) {
            if (saveCount == 0) {
                Serialization.storeSerializedObject(SoundexIndexes, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexFname.ser");

            }
            if (saveCount == 1) {
                Serialization.storeSerializedObject(SoundexIndexes, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser");

            }
            saveCount++;
        }
        
        InvertedSoundex soundexI;
        int id = 1;
        blockID = 1;
        


        String key = null;
        //soundexI = (InvertedSoundex) IS;

        
        noOfRecords = Erecords.size();
       

        while (!records.isEmpty()) {
            long startTime = System.nanoTime();
            Iterator iter = records.entrySet().iterator();
            Map.Entry pair = (Map.Entry) iter.next();
            Entity firstEntity = (Entity) pair.getValue();
            String recordID = firstEntity.getRecordID();

            iter.remove();
            CI.createBlock(blockID, recordID);
            EI.appendToBlock(recordID, blockID);

            for (int i = 0; i < attr.length; i++) {
                if (i == 0) {
                    key = Soundex.soundex(firstEntity.getFirstName());
                }
                if (i == 1) {
                    key = Soundex.soundex(firstEntity.getLastName());
                }
                soundexI = II.get(i);
                soundexI.getCandidates(key).remove(recordID);

                ArrayList<String> candidates = soundexI.getCandidates(key);
                
                ArrayList<String> deletion = new ArrayList<>();

                for (String candidateEntite : candidates) {
                    Entity currentEntity = records.get(candidateEntite);
                    if (currentEntity == null) {
                        continue;
                    }
                    //System.out.println(currentEntity.getRecordID());
                    if (candidateEntite.equals(recordID)) {
                        continue;
                    }
                    String currentID = currentEntity.getRecordID();
                    double similarity = CanopySimilarity.getSimilarity(firstEntity, currentEntity, 0.7, 0.3, 1);

                    if (t1 <= similarity) {
                        if (CI.getEntityList(blockID).contains(currentID)) {
                            continue;
                        }
                        CI.appendToBlock(blockID, currentID);
                        EI.appendToBlock(currentID, blockID);
                        EI.sort(currentID);
                        //ids.add(currentEntity.getRecordID());
                    }

                    // Removal threshold:
                    if (t2 <= similarity) {
                      

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
            long stopTime = System.nanoTime();
            long elapsedTime = stopTime - startTime;

            System.out.println(" Elapsed time is per record " + elapsedTime / 1000 + " ps");

            id++;
        }
        

        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        Serialization.storeSerializedObject(SI, "E:\\4th Year\\Research\\Imp\\Indexes\\SI.ser");
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        CI.setLastIndex(id);
        CI.printIndex();
        EI.printIndex();

    }

    public HashMap<String, Entity> getcandidateEntities() {
        HashMap<String, Entity> test = new HashMap<String, Entity>();
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
