package org.md.events.floorplan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * ButtonPanel.java                         @author(cameronMD)
 * @version(3.7.2015)   Creates a panel with multiple buttons that
 *  will be used manipulating other elements in different panels.
 * @version(6.7.2015)   Getter methods added to detect JButton actions.
 * @version(7.7.2015)   Some buttons removed.
 * @version(25.8.2015)  Added void methods for adding and removing buttons.
 * @version(28.8.2015   Voids methods removed. Removed button added.
 */

public class ButtonPanel extends JPanel {
    
    private JButton table, removeLast;
    
    /*
     * Constructor.
     */
    ButtonPanel() {
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(700, 50));
        
        // Assigns all private buttons names.
        table = new JButton("New Table");
        removeLast = new JButton("Remove Last Table");
        
        // Puts default buttons on panel.
        add(table);
        add(removeLast);
    }
    
    /*
     * Button getters.
     */
    public JButton getTable() {
        return table;
    }
    
    public JButton getRemoveLast() {
        return removeLast;
    }
}
