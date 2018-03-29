package org.md.events.floorplan.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.md.events.floorplan.model.RecLinkList;
import org.md.events.floorplan.model.RecNode;

/*
 * MainPanel.java                       @author(cameronMD)
 * Create a main window for floor layout program.
 * @version(27.5.2015)
 * @version(3.7.2015)   Turned from a JFrame to a JPanel.
 * @version(6.7.2015)   ActionListener added. JPanel getters added.
 * @version(7.7.2015)   Fixed ActionListener to correctly add a rect to
 *  the LL based on the position and size of the rect in the drag panel.
 * @version(21.8.2015)  Attempt to fix overlap Rect problem. The base
 *  logic is set up for the problem to be fixed, actually testing if an
 *  overlap will occur has to be implemented.
 * @version(22.8.2015)  Overlap logic implemented. Modularity added.
 * @version(28.8.2015)  Added remove button.
 */
public class MainPanel extends JPanel {
    
    private ButtonPanel buttP;
    private DragPanel dragP;
    private RecLinkList recLLmP = new RecLinkList();
    
    public MainPanel() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(800, 800));
        
        // Window and buttons created.
        buttP = new ButtonPanel();
        dragP = new DragPanel();        
        add(buttP);
        add(dragP);
        
        // Button listeners for the ButtonPanel.
        ButtonListener listener = new ButtonListener();
        buttP.getTable().addActionListener(listener);
        buttP.getRemoveLast().addActionListener(listener);
    }
    
    public ButtonPanel getButt() {
        return buttP;
    }
    
    public JPanel getDrag() {
        return dragP;
    }
    
    /*
     * Detects buttons being pressed in the ButtonPanel.
     */
    private class ButtonListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            
            // Adds a rectangle to the LL based on the position of
            //  the movable rectangle in the drag panel.
            if (source == getButt().getTable()) {
                // Rec to be added to LL.
                Rectangle newRec = new Rectangle(dragP.getRect());
                
                // Cycles through LL in logic method, if no overlapping
                //  occurs, new Rec added.*/
                if (whileCheck(newRec)) {
                    recLLmP.insertLast(new Rectangle(newRec));
                    // Sends updated LL to DragPanel.
                    dragP.updateRecLL(recLLmP);
                }
            // Removes the last Rectangle from the panel if remove
            //  button is pressed.
            } else if (source == getButt().getRemoveLast()) {
                recLLmP.removeLast();
                dragP.updateRecLL(recLLmP);
            }
        }
        
        /*
         * Method looks at each Rec in LL and checks that it does not
         * overlap with parameter Rec.
         */
        private boolean whileCheck(Rectangle r) {
            // Beginning of LL.
            RecNode node = recLLmP.getHeader().getNext();
            while (node != recLLmP.getTrailer()) {
                Rectangle current = node.getRec();
                node = node.getNext();
                
                if (overlapCheck(current, r)) {
                    // Current Rec falls on a Rec already in LL.
                    return false;
                }
            }
            return true;
        }
        
        /*
         * Checks if 2 Recs overlap.
         */
        private boolean overlapCheck(Rectangle r, Rectangle newR) {
            // Used for readability in logic statements.
            double r1x1 = r.getX();
            double r1x2 = r1x1 + r.getWidth();
            
            double r1y1 = r.getY();
            double r1y2 = r1y1 + r.getHeight();
            
            double r2x1 = newR.getX();
            double r2x2 = r2x1 + newR.getWidth();
            
            double r2y1 = newR.getY();
            double r2y2 = r2y1 + newR.getHeight();
            
            // Checks to see if the X boundaries of the two Recs intersect.
            if ((r1x1 >= r2x1 && r1x1 <= r2x2) ||
                (r1x2 >= r2x1 && r1x2 <= r2x2)) {
                // Checks to see if the Y boundaries intersect.*
                if ((r1y1 >= r2y1 && r1y1 <= r2y2) ||
                    (r1y2 >= r2y1 && r1y2 <= r2y2)) {
                    // Overlap occurs.
                    return true;
                }
            }
            
            // No overlap.
            return false;
        }
    }
}
