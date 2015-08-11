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
public interface SimilarityIndexInterface {
    public void printIndex();
    public void appendToBlock(String ID, String recID ,double similarity);
    public void createBlock(String ID);
}
