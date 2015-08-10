/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes.Interfaces;

import DataStuctures.Entity;

/**
 *
 * @author JanithaT
 */
public interface RIndexInterface {
     public void appendEntity(Entity entity);
     public Entity getRecord(String recordID);
     public void printIndex();
     
}
