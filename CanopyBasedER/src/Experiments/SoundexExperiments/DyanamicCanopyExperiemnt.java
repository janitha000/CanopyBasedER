/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments.SoundexExperiments;

import BlockBuilding.Soundex.CanopyDynamicSoundex;
import DataStuctures.Entity;
import java.util.ArrayList;

/**
 *
 * @author JanithaT
 */
public class DyanamicCanopyExperiemnt {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ArrayList<String> attr = new ArrayList<>();
        attr.add("firstname");
        attr.add("lastname");
        
        CanopyDynamicSoundex CD = new CanopyDynamicSoundex(0.6, 0.9);
        Entity newEntity = new Entity("EEE", "AABL", "EVE", "GRAHAM");
        CD.addToCanopy(newEntity, attr);
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time is " +elapsedTime);
    }
}
