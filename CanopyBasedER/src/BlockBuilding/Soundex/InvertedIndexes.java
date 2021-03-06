/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockBuilding.Soundex;

import Utilities.Serialization;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author JanithaT
 */
public class InvertedIndexes {

    public InvertedIndexes() {

    }

    public ArrayList<InvertedSoundex> getInvertedIndexes(ArrayList<String> attributes, Boolean saved) {
        ArrayList<InvertedSoundex> II = new ArrayList<>();
        for (String attribute : attributes) {
            InvertedSoundex aa = new InvertedSoundex();

            if (saved) {
                II.add(aa.getSoundexIndex(attribute, true));
            } else {
                aa.getSoundexIndex(attribute);
                II.add(aa);
            }
            
           // II.add(aa.getSoundexIndex(attribute, true));
        }

        return II;
    }
}
