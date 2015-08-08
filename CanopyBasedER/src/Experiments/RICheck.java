/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiments;
import DataStuctures.Entity;
import java.util.ArrayList;
import Indexes.RecordIndex;
/**
 *
 * @author JanithaT
 */
public class RICheck {
    static RecordIndex RI;
    public static void main(String[] args) {
        RI = new RecordIndex();
        ArrayList<Entity> test = new ArrayList<Entity>();
        test.add(new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
        test.add(new Entity("AAB", "Vindya ", "Hemali", "Matara"));
        test.add(new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
        test.add(new Entity("AAD", "Nadeeka", "Wickramasinghe", "Matara"));
        test.add(new Entity("AAE", "Kavinda", "Herath", "Kandy"));
        test.add(new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
        test.add(new Entity("AAG", "Janith", "Tenna", "Kandy"));
        
        for (Entity en : test) {
            //System.out.println(en.getFirstName());
                if(RI.hasEntity(en)){
                    RI.appendRecord(en, en.getRecordID());
                } else{
		RI.appendEntity(en);
		}
        
       
        
    }
         RI.printIndex();
    }
    
    
}
