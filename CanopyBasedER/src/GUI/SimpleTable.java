/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author JanithaT
 */
public class SimpleTable {
    
    public SimpleTable(){
        String[] columnNames = {"First Name",
                        "Last Name",
                        "Sport",
                        "# of Years",
                        "Vegetarian"};
        Object[][] data = {
    {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
    {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}
};
    }


}
