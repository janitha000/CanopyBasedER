/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes.Interfaces;

/**
 *
 * @author JanithaT
 */
public interface EntityIndexInterface {
   // public void createBlock(String recordID, int blockID);
    public void appendToBlock(String recordID, int blockID);
    public Boolean hasBlock(String recordID);
    public void printIndex();
    
    
}
