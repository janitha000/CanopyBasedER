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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author JanithaT
 */
public class mySqlConnection {

    String JDBC_DRIVER;
    String DB_URL;
    String USER;
    String PASS;
    String tableName;

    public mySqlConnection(String DB, String user, String pwd, String table) {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/" + DB;
        USER = user;
        PASS = pwd;
        tableName = "ferbiTest";
    }

    public HashMap<String, Entity> getInvertedIndexData() throws SQLException {
        HashMap<String, Entity> test = getFromDatabaseII();

//        Connection conn = null;
//        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
//        String query = "SELECT * FROM " + tableName + " LIMIT 0, 100000";
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(query);
//        while (rs.next()) {
//            test.put(rs.getString("RecID"), new Entity(rs.getString("RecID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));
//        }
//        conn.close();
//
////        test.put("AAA", new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
////        test.put("AAB", new Entity("AAB", "Vindya ", "Hemali", "Matara"));
////        test.put("AAC", new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
////        test.put("AAD", new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
////        test.put("AAE", new Entity("AAE", "Kavinda", "Herath", "Kandy"));
////        test.put("AAF", new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
////        test.put("AAG", new Entity("AAG", "Kosala", "Tennaakoon", "Kandy"));
////        test.put("AAI", new Entity("AAI", "Ganitha", "Tennaakoon", "Kandy"));
////        test.put("AAH", new Entity("AAH", "Janitha", "Tennakon", "Kandy"));
////        test.put("AAJ", new Entity("AAJ", "Janitha", "Tennakon", "Ambatenna"));
////        //test.put("CCC" , new Entity("CCC", "Saman", "Dassanayaka", "Kandy"));
        return test;

    }

    public List<Entity> getData() throws SQLException {
        List<Entity> test = getDataDatabase();

//        Connection conn = null;
//        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
//        String query = "SELECT * FROM " + tableName + " LIMIT 0, 100000";
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(query);
//        while (rs.next()) {
//            Boolean add = test.add(new Entity(rs.getString("RecID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));
//
//        }
//        conn.close();
//        
////        test.add(new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
////        test.add(new Entity("AAB", "Vindya ", "Hemali", "Matara"));
////        test.add(new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
////        test.add(new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
////        test.add(new Entity("AAE", "Kavinda", "Herath", "Kandy"));
////        test.add(new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
////        test.add(new Entity("AAG", "Kosala", "Tennaakoon", "Kandy"));
////        test.add(new Entity("AAI", "Ganitha", "Tennaakoon", "Kandy"));
////        test.add(new Entity("AAH", "Janitha", "Tennakon", "Kandy"));
////        test.add(new Entity("AAJ", "Janitha", "Tennakon", "Ambatenna"));
////        test.add(new Entity("CCC", "Saman", "Dassanayaka", "Kandy"));
        return test;
    }

    public HashMap<String, Entity> getFromDatabaseII() throws SQLException {
        HashMap<String, Entity> test = new HashMap<>();

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM " + tableName + " LIMIT 0, 1000";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            test.put(rs.getString("RecID"), new Entity(rs.getString("RecID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));
        }
        conn.close();

        return test;
    }

    public HashMap<String, Entity> getFromTestDatabaseII() throws SQLException {
        HashMap<String, Entity> test = new HashMap<>();

        test.put("AAA", new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
        test.put("AAB", new Entity("AAB", "Vindya ", "Hemali", "Matara"));
        test.put("AAC", new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
        test.put("AAD", new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
        test.put("AAE", new Entity("AAE", "Kavinda", "Herath", "Kandy"));
        test.put("AAF", new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
        test.put("AAG", new Entity("AAG", "Kosala", "Tennaakoon", "Kandy"));
        test.put("AAI", new Entity("AAI", "Ganitha", "Tennaakoon", "Kandy"));
        test.put("AAH", new Entity("AAH", "Janitha", "Tennakon", "Kandy"));
        test.put("AAJ", new Entity("AAJ", "Janitha", "Tennakon", "Ambatenna"));
        //test.put("CCC" , new Entity("CCC", "Saman", "Dassanayaka", "Kandy"));
        return test;

    }

    public List<Entity> getDataDatabase() throws SQLException {
        List<Entity> test = new ArrayList<Entity>();

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM " + tableName + " LIMIT 0, 1000";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Boolean add = test.add(new Entity(rs.getString("RecID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));

        }
        conn.close();

        return test;

    }
    
    public ArrayList<Entity> getQueries() throws SQLException {
        ArrayList<Entity> test = new ArrayList<Entity>();

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM " + tableName + " LIMIT 1000, 2050";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Boolean add = test.add(new Entity(rs.getString("RecID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("City")));

        }
        conn.close();

        return test;

    }

    public HashMap<String, String> getEvaluationDatabase() throws SQLException {
        HashMap<String, String> evaluationData = new HashMap<>();

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM " + tableName + " LIMIT 0, 2050";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            evaluationData.put(rs.getString("RecID"), rs.getString("RecordID"));

        }
        return evaluationData;
    }

    public HashMap<String, HashSet<String>> getEvaluationDatabaseResults() throws SQLException {
        HashMap<String, HashSet<String>> evaluationData = new HashMap<>();

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM " + tableName + " LIMIT 0, 2050";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String ID = rs.getString("RecordID");
            String IID = rs.getString("RecID");
            if (evaluationData.containsKey(ID)) {
                HashSet<String> list = evaluationData.get(ID);;
                list.add(IID);
                evaluationData.put(ID, list);

            } else {
                HashSet<String> list = new HashSet<>();
                list.add(IID);
                evaluationData.put(ID, list);

            }

        }
        return evaluationData;
    }

    public List<Entity> getDataTestDatabase() throws SQLException {
        List<Entity> test = new ArrayList<Entity>();

        test.add(new Entity("AAA", "Janitha", "Tennakoon", "Kandy"));
        test.add(new Entity("AAB", "Vindya ", "Hemali", "Matara"));
        test.add(new Entity("AAC", "Nadeeka", "Wickramasinghe", "Matara"));
        test.add(new Entity("AAD", "Nadeeka", "Wickramasinghe", "Galle"));
        test.add(new Entity("AAE", "Kavinda", "Herath", "Kandy"));
        test.add(new Entity("AAF", "Janith", "Tennakoon", "Kandy"));
        test.add(new Entity("AAG", "Kosala", "Tennaakoon", "Kandy"));
        test.add(new Entity("AAI", "Ganitha", "Tennaakoon", "Kandy"));
        test.add(new Entity("AAH", "Janitha", "Tennakon", "Kandy"));
        test.add(new Entity("AAJ", "Janitha", "Tennakon", "Ambatenna"));
        test.add(new Entity("CCC", "Saman", "Dassanayaka", "Kandy"));
        return test;

    }

    public Entity getRecord(String recordID) throws SQLException {

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        //String query = "SELECT * FROM " +tableName + " WHERE RecordID = 'AAA'";
        String query = "SELECT * FROM " + tableName + " WHERE RecordID = '" + recordID + "'";
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
