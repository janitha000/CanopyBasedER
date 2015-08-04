/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
       public void getData() throws SQLException{
           Connection conn = null;
           conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
           String query = "SELECT * FROM " +tableName;
              
             // create the java statement
      Statement st = conn.createStatement();
       
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
       
      // iterate through the java resultset
      while (rs.next()){ 
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String city = rs.getString("City");
        
         
        // print the results
        System.out.format("%s, %s, %s\n",  firstName, lastName, city);
      }
      
             conn.close();  
       }
       public static void main(String[] args) throws SQLException {
           mySqlConnection connecton = new mySqlConnection("researchtest", "root", "jibtennakoon", "person");
           connecton.getData();
            
       }
}
