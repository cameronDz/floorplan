package org.md.events.floorplan.model;

import java.awt.*;

/*
 * RectLinkList.java                        @author(cameronMD)
 * Creates a linked list of rectangle objects. Specifically used in program
 *  where tables are inserted multiple times into a panel, represented as
 *  Rectangle objects. Doubly linked-list.
 * @version(1.7.2015)   Class created.
 * @version(5.7.2015)   Nested RecNode class removed and made public. Update
 *  made in order for easier accessability in traversing list without using
 *  iterator class.
 */

public class RecLinkList {
    
    private RecNode header;
    private RecNode trailer;
    private int size = 0;
    
    /*
     * Constructor with no parameters.
     */
    public RecLinkList() {        
        header = new RecNode(null, null, null);
        trailer = new RecNode(null, header, null);
        header.setNext(trailer);
    }
    
    /*
     * Traverses list and returns a it in String form.
     */
    public String toString() {
        if (isEmpty()) {
            return "List is Empty.";
        }
        String s = "";
        RecNode current = header.getNext();
        while (current != trailer) {
            s = s + "\n" + current.getRec();
            current = current.getNext();
        }        
        return s;
    }
    
    /*
     * Returns size of list.
     */
    public int size() {
        return size;
    }
    
    /*
     * Returns true if list is empty, otherwise false.
     */
    public boolean isEmpty() {
            return size == 0;
    }
    
    /*
     * Returns first rectangle in list.
     */
    public Rectangle first() {
        if (isEmpty()) {
            return null;
        }
        return header.getNext().getRec();
    }
    
    /*
     * Returns last Rectangle in list.
     */
    public Rectangle last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.getPrev().getRec();
    }
    
    /*
     * Returns the header node in the linked list.
     */
    public RecNode getHeader() {
        return header;
    }
    
    /*
     * Returns the trailer node in the linked list.
     */
    public RecNode getTrailer() {
        return trailer;
    }
    
    /*
     * Inserts a Rectangle at the beginning of a list.
     */
    public void insertFirst(Rectangle r) {
        addBetween(r, header, header.getNext());
    }
    
    /*
     * Inserts a Rectangle at the end of the list.
     */
    public void insertLast(Rectangle r) {
        addBetween(r, trailer.getPrev(), trailer);
    }
    
    /*
     * Removes first Rectangle in the list. Returns removed Rectangle.
     */
    public Rectangle removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(header.getNext());
    }
    
    /*
     * Removes last Rectangle in the list. Returns removed Rectangle.
     */
    public Rectangle removeLast() {
        if (isEmpty()) {
            return null;
        }        
        return remove(trailer.getPrev());
    }
    
    /*
     * Adds a Rectangle between 2 given nodes.
     */
    private void addBetween(Rectangle r, RecNode prev, RecNode next) {
        // Creates new node, links between predecessor and successor.
        RecNode newNode = new RecNode(r, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        size++;
    }
    
    /*
     * Removes a specific Rectangle from the list. Returns that Rectangle.
     */
    private Rectangle remove(RecNode node) {
        // Severs links from node's next and prev, links next and prev.
        RecNode prev = node.getPrev();
        RecNode next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        
        // Delete pointers to existing nodes.
        node.setPrev(null);
        node.setNext(null);
        // Decrements size and returns removed Rectangle.
        size--;
        return node.getRec();
    }
    
}
