/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexes.Interfaces;

import DataStuctures.Entity;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author JanithaT
 */
public interface RecordIndexInterface {
    public void appendEntity(Entity entity);
    public void appendRecord(Entity entity, String RecordID);
    public ArrayList getDuplicates(Entity entity);
    public Boolean hasEntity(Entity entity);
    public Map<Entity,ArrayList> getRecordIndex();
    public void printIndex();
    
}
