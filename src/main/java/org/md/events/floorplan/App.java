package org.md.events.floorplan;

import javax.swing.JFrame;

import org.md.events.floorplan.ui.MainPanel;

/*
 * FloorPlan.java                       @author(cameronMD)
 * Hosts the TableDesign panel in a JFrame.
 * @version(3.7.2015)   Initiated. 
 * @version(2015.12.19) Transferred and renamed main class.
 * @version(2018.03.29) Renamed to App.java
 */
public class App 
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Specific panel yet to be designed.
        MainPanel panel = new MainPanel();
        frame.getContentPane().add(panel);
                
        frame.pack();
        frame.setVisible(true);
    }
}
