/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author JanithaT
 */
public class SqlTable extends JPanel{
    public Connection con = null;
    public Statement requete = null;
    public ResultSet rs = null;
    JTable table;
 
    public SqlTable() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/csvimport";
            String userid = "root";
            String password = "jibtennakoon";
            String sql = "SELECT * FROM person  LIMIT 0, 40";
            
            con = DriverManager.getConnection(url,userid,password);
            requete = con.createStatement();
            
            rs = requete.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            
            Vector columns = new Vector(columnCount);
     
            //store column names
            for(int i=1; i<=columnCount; i++)
                columns.add(md.getColumnName(i));
            Vector data = new Vector();
            Vector row;
            
            while(rs.next())
            {
                row = new Vector(columnCount);
                for(int i=1; i<=columnCount; i++)
                {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
             JFrame frame = new JFrame(); 
             frame.setSize(500,120); 
             frame.setLocationRelativeTo(null);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
             JPanel panel = new JPanel(); 
             JTable table = new JTable(data,columns); 
             JScrollPane jsp = new JScrollPane(table); 
             panel.setLayout(new BorderLayout()); 
             panel.add(jsp,BorderLayout.CENTER); 
             frame.setContentPane(panel); 
             frame.setVisible(true);

        }
        
        catch(SQLException sqle){
            //cf Comment gérer les erreurs ? 
            System.out.println(sqle);
            sqle.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) throws SQLException {
        SqlTable table = new SqlTable();
        
    }
    

}
