/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes.Interfaces;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author JanithaT
 */
public interface CanopyIndexInterface {
     public void createBlock(int blockID, String recordID);
     public void appendToBlock(int blockID,String recordID);
     public void appendToBlockAyyayList(int blockID,ArrayList<String> recordIDs );
     public void setLastIndex(int ID);
     public int getLastIndex();
     public Set keys();
     public String canopyRecordID(int blockID);
     public void printIndex();
}
