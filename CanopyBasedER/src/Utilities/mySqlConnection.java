/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DataStuctures.Entity;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JanithaT
 */
public class mySqlConnection {
    
        String JDBC_DRIVER ;  
        String DB_URL;
        String USER;
        String PASS ;
        String tableName ;
    
        public mySqlConnection(String DB,String user, String pwd, String table){
         JDBC_DRIVER = "com.mysql.jdbc.Driver";  
         DB_URL = "jdbc:mysql://localhost:3306/"+DB;
         USER = user;
         PASS = pwd;
         tableName = table;
    }
        
      
       public List<Entity> getData() throws SQLException{
           List<Entity> test = new ArrayList<Entity>();
           
           Connection conn = null;
           conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
           String query = "SELECT * FROM " +tableName + " LIMIT 0, 400000";
              
             // create the java statement
      Statement st = conn.createStatement();
       
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
       
      // iterate through the java resultset
      while (rs.next()){ 
//         en.setRecordID(rs.getString("RecordID"));
//         en.setFirstName(rs.getString("FirstName"));
//         en.setLastName(rs.getString("LastName"));
//         en.setCity(rs.getString("City"));
         
         Boolean add = test.add(new Entity(rs.getString("RecordID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));
         
        // print the results
       // System.out.format("%s, %s, %s\n",  firstName, lastName, city);
      }
               
             conn.close();  
           return test;
       }
       
       public Entity getRecord(String recordID) throws SQLException{
           
           Connection conn = null;
           conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
           //String query = "SELECT * FROM " +tableName + " WHERE RecordID = 'AAA'";
           String query = "SELECT * FROM " + tableName + " WHERE RecordID = '" + recordID + "'" ;
            PreparedStatement prepStmt = conn.prepareStatement(query);
            //prepStmt.setString(1, recordID);
            ResultSet rs = prepStmt.executeQuery(query);
            Entity entity = null;
            //while (rs.next()){ 
                entity.setFirstName(rs.getString("FirstName"));
                entity.setLastName(rs.getString("LastName"));
                entity.setCity(rs.getString("City"));
                entity.setRecordID(recordID);
        
         
            // print the results
           //System.out.format("%s, %s, %s\n",  firstName, lastName, city);
      
           
             conn.close();  
            return entity;
       }
       /*public static void main(String[] args) throws SQLException {
           mySqlConnection connecton = new mySqlConnection("researchtest", "root", "jibtennakoon", "person");
           connecton.getData();
            
       }*/
}
