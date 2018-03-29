package org.md.events.floorplan.ui;

import javax.swing.JPanel;

import org.md.events.floorplan.model.RecLinkList;
import org.md.events.floorplan.model.RecNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * DragPanel.java                         @editor(cameronMD)
 * @version(27.5.2015)  Creates a panel to be used to drag objects on. Taken
 *  from class found online called ShapeMover on a stackExchange.com forum.
 * @version(29.5.2015)  This is a problem with seting this Panel in another
 *  panel and correctly allowing adjustments for size in this panel. Hosting
 *  panel size must be passed into this panel.
 * @version(5.7.2015)   paintComponent method changed to traverse linked list
 *  and add each Rectangle to area.
 * @version(6.7.2015)   Methods added to get/send Rectangles in/out of class.
 * @version(6.7.2015)   Loop which prints all Rectangles in the Rec
 *  linked-list is broken. Error occurs at commented-out line.
 * @version(7.7.2015)   Fixed Rectangle linked list.
 * @version(28.8.2015)  Draw methoded added to draw room.
 */
public class DragPanel extends JPanel {
    
    // Start location for unplaced Rectangle.
    private Rectangle rect = new Rectangle(0, 0, 30, 30);
    // Large Rect where small Rect is moved over.
    private Rectangle area;
    // Coordinates used in ActionListener.
    private int preX, preY;
    // Programs first time painting.
    private boolean isFirstTime = true;
    // Action listener for mouse click.
    private boolean pressOut = false;
    // Size of panel with movable Rectangles.
    private Dimension dim = new Dimension(700, 600);
    // List with location of all placed Rectangles.
    private RecLinkList recLL = new RecLinkList();
    
    /*
     * Constructor.
     */
    public DragPanel() {
        setBackground(Color.white);
        addMouseMotionListener(new MyMouseAdapter());
        addMouseListener(new MyMouseAdapter());
    }
    
    @Override
    public Dimension getPreferredSize() {
        return dim;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Initializes the moveable Rectangle and its field of movability.
        if (isFirstTime) {
            area = new Rectangle(dim);
            rect.setLocation(0, 0);
            isFirstTime = false;
        }
        
        // Cycles through rectangle linked list and puts them on panel.
        RecNode node = recLL.getHeader().getNext();
        g2d.setColor(Color.red);
        while (node != recLL.getTrailer()) {
            g2d.fill(node.getRec());
            node = node.getNext();
        }
        
        // Draws both WoodWinds room outline.
        drawBothWW(g2d);
        
        //Sets the movable Rec.*/
        g2d.setColor(Color.black);        
        g2d.fill(rect);
    }
    
    /*
     * Draws the outline for both rooms in WoodWinds.
     */
    private void drawBothWW(Graphics2D g2d) {
        // Building outline
        g2d.setColor(Color.blue);
        g2d.drawLine(20, 20, 620, 20);      // Top of building.
        g2d.drawLine(20, 20, 20, 400);      // Left wall.
        g2d.drawLine(620, 20, 620,400);     // Right wall.
        g2d.drawLine(20, 400, 620, 400);    // Front wall.
        g2d.drawLine(270, 300, 370, 300);   // Back wall lobby.
        g2d.drawLine(270, 300, 270, 400);   // Left wall lobby.
        g2d.drawLine(370, 300, 370, 400);   // Right wall lobby.
        
        // Garden room outline.
        //g2d.drawLine();
        //g2d.drawLine();
        // Firelight outline.
        //g2d.drawLine();
        //g2d.drawLine();
    }
    
    /*
     * Updates the RecLinkList that keeps track of all the placed Rectangles.
     */
    public void updateRecLL(RecLinkList r) {
        recLL = r;
        repaint();
    }
    
    /*
     * Gets current location of movable Rectangle.
     */
    public Rectangle getRec() {
        return rect;
    }
    
    /*
     * Does not allow movable Rectangle to be pulled off of area Rectangle.
     */
    public boolean checkRect() {        
        // No area for Recs to be placed established.
        if (area == null) {
            return false;
        }
        
        // Mouse moves Rec into a valid part of area.
        if (area.contains(rect.x, rect.y,
                          rect.getWidth(),
                          rect.getHeight() )) {
            return true;
        }

        // Mouse attempts to move Rec off area.
        int new_x = rect.x;
        int new_y = rect.y;
        
        if ((rect.x + rect.getWidth()) > area.getWidth()) {
            new_x = (int) area.getWidth() - (int) (rect.getWidth() - 1);
        }
        
        if (rect.x < 0) {
            new_x = -1;
        }
        
        if ((rect.y + rect.getHeight()) > area.getHeight()) {
            new_y = (int) area.getHeight() - (int) (rect.getHeight() - 1);
        }
        
        if (rect.y < 0) {
            new_y = -1;
        }
        
        rect.setLocation(new_x, new_y);
        return false;
    }
    
    /*
     * Returns current location of movable Rectangle.
     */
    public Rectangle getRect() {
        return rect;
    }
    
    /*
     * Adds a new Rectangle to the RecLinkList in this class.
     */
    public void addRect(Rectangle r) {
        recLL.insertLast(r);
    }

    /*
     * Class responsible for detecting mouse movement.
     */
    private class MyMouseAdapter extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            preX = rect.x - e.getX();
            preY = rect.y - e.getY();

            if ( rect.contains(e.getX(), e.getY()) ) {
                updateLocation(e);
            } else {
                pressOut = true;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!pressOut) {
                updateLocation(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if ( rect.contains(e.getX(), e.getY()) ) {
                updateLocation(e);
            } else {
                pressOut = false;
            }
        }

        public void updateLocation(MouseEvent e) {
            rect.setLocation(preX + e.getX(), preY + e.getY());
            checkRect();

            repaint();
        }
    }
}
