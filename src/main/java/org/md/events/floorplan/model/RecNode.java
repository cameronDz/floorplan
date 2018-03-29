package org.md.events.floorplan.model;

import java.awt.*;

/*
 * RecNode.java                             @author(cameronMD)
 * Node class used in RecLinkList.
 * @version(5.7.2015)   Class removed as private nested
 *  class of RecLinkList and made a public class.
 */
public class RecNode {
    private Rectangle rec;
    private RecNode next;
    private RecNode prev;
    
    /*
     * Constructor.
     */
    public RecNode(Rectangle r, RecNode p, RecNode n) {
        this.rec = r;
        this.prev = p;
        this.next = n;
    }
    
    @Override
    public String toString() {
        if (rec == null) {
            return "NULL";
        }
        
        return rec.toString();
    }
    
    public Rectangle getRec() {
        return rec;
    }
    
    public RecNode getPrev() {
        return prev;
    }
    
    public RecNode getNext() {
        return next;
    }
    
    public void setPrev(RecNode p) {
        this.prev = p;
    }
    
    public void setNext(RecNode n) {
        this.next = n;
    }
}
