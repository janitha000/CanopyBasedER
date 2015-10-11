/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import DataStuctures.Entity;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JanithaT
 */
public class CanopyDynamicSoundex {

    CanopyIndex CI;
    EntityIndex EI;
    private final double t1;
    private final double t2;
    int BlockID;
    Map<String, ArrayList> IS;
    HashMap<String, Entity> records;

    public CanopyDynamicSoundex(Double T1, Double T2) {

        t1 = T1;
        t2 = T2;
        records = getRecords();

    }

    public void addToCanopy(Entity entity, ArrayList<String> attributes) {
        //Get the Canopy Index and Entity Index
        CI = (CanopyIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        EI = (EntityIndex) Serialization.loadSerializedObject("E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");

        String[] attr = new String[attributes.size()];
        BlockID = CI.getLastIndex() + 1;

        InvertedSoundex soundexI; // = (InvertedSoundex) IS;

        ArrayList<InvertedSoundex> II = new InvertedIndexes().getInvertedIndexes(attributes, true);

        ArrayList<Integer> ids = new ArrayList<>();

        String key = null;
        int noCandidate = 0;

        String recordID = entity.getRecordID();

        for (int i = 0; i < attr.length; i++) {
            if (i == 0) {
                key = Soundex.soundex(entity.getFirstName());
            }
            if (i == 1) {
                key = Soundex.soundex(entity.getLastName());
            }

            soundexI = (InvertedSoundex) II.get(i);

            Boolean added = false;

            ArrayList<String> candidates = soundexI.getCandidates(key);

            if (candidates == null) {
                soundexI.createNewBlock(key, recordID);
                if (i == 0) {
                    Serialization.storeSerializedObject(soundexI, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexFname.ser");
                }
                if (i == 1) {
                    Serialization.storeSerializedObject(soundexI, "E:\\4th Year\\Research\\Imp\\Indexes\\SoundexLname.ser");
                }
                if (noCandidate == 1) {
                    CI.createBlock(BlockID, entity.getRecordID());
                    EI.appendToBlock(recordID, BlockID);
                    CI.setLastIndex(BlockID);

                    break;
                }
                noCandidate++;
                continue;
            } else {
                for (String candidateEntite : candidates) {
                    Entity currentEntity = records.get(candidateEntite);
                    if (currentEntity == null) {                                 //Item not inserted in to the database 
                        continue;
                    }
                    //System.out.println(currentEntity.getRecordID());
                    if (candidateEntite.equals(recordID)) {
                        continue;
                    }

                    String currentID = currentEntity.getRecordID();
                    double similarity;
                    similarity = CanopySimilarity.getSimilarity(entity, currentEntity, 0.7, 0.3, 1);
                    int currentBlockID = (int) EI.getBlockList(currentID).get(0);
                    if (t1 <= similarity) {

                        ids.add(currentBlockID);
                        //ids.add(currentEntity.getRecordID());
                    }

                    // Removal threshold:
                    if (t2 <= similarity) {
                        CI.appendToBlock(currentBlockID, entity.getRecordID());
                        EI.appendToBlock(recordID, currentBlockID);
                        added = true;
                        break;
                    }

                }
                if (!added) {                                   //Not sure, this was inside the for loop!! I changed it to outer loop. not tested
                    for (int blocksID : ids) {
                        CI.createBlock(blocksID, entity.getRecordID());
                        EI.appendToBlock(entity.getRecordID(), blocksID);
                        CI.setLastIndex(blocksID);
                    }

                }
            }
        }
        CI.printIndex();
        EI.printIndex();
        Serialization.storeSerializedObject(CI, "E:\\4th Year\\Research\\Imp\\Indexes\\CI.ser");
        Serialization.storeSerializedObject(EI, "E:\\4th Year\\Research\\Imp\\Indexes\\EI.ser");
        records.put(entity.getRecordID(), entity);

    }

    public HashMap<String, Entity> getRecords() {
        HashMap<String, Entity> test = new HashMap<String, Entity>();
        mySqlConnection connecton = new mySqlConnection("csvimport", "root", "jibtennakoon", "person2");
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

// problem when new soundex key is inserted
