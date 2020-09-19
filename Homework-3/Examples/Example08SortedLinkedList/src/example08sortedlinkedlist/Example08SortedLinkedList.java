/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example08sortedlinkedlist;

/**
 *
 * @author Yang
 */

import java.util.Scanner;

public class Example08SortedLinkedList 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        int key;

        SortedLinkedList L = new SortedLinkedList();
        L.build();
        L.traverse();

        while (true)
        {
            System.out.println("Enter a key (-1 to stop) to be searched for: ");
            key = input.nextInt();
            if (key < 0)    break;

            ReferencePair rp = L.search(key);
            if(rp != null)
            {
                System.out.println("found data: " + rp.current.getKey() + " | " +
                                                    rp.current.getData());
                
                System.out.println("Delete it (Y/y for yes): ");
                String option = input.next();
                if (option.equals("Y") || option.equals("y"))
                {
                    L.delete(key);
                    System.out.println("Node for key " + key + " has been deleted.");
                }
                L.traverse();
                L.deleteFirst();
                L.traverse() ;
            }
            else
                System.out.println("Key is not found!");
        }
    }
    
}

/**
 * 
 * Node in linked list
 */
class Node
{
    private int     key;    // key of the node
    private String  data;   // data in the node
    private Node    next;   // reference to the next node in linked list

    /**
     * 
     * Constructor: create a node with k as its key, and d as its data 
     */
    public Node(int k, String d)     
    {
        key = k;
        data = d;
        next = null;        
    }

    public int getKey()
    {
        return key;
    }

    public String getData()
    {
        return data;
    }

    public Node getNext()
    {
        return next;
    }

    public void setKey(int k)
    {
        key = k;
    }

    public void setData(String d)
    {
        data = d;
    }

    public void setNext(Node n)
    {
        next = n;
    }
}

/**
 * 
 * Linked list: nodes are sorted in increasing order of keys
 * Assume: 
 * - No duplicated keys in linked list
 * - All keys are non-negative
 */
class SortedLinkedList        
{
    private Node head;          // reference to the first node

    /**
     * 
     * Constructor: create an empty linked list
     */ 
    SortedLinkedList()        
    {
        head = null;
    }

    /**
     * 
     * Build linked list by reading keys and data from user 
     */ 
    public void build()                   
    {
        Scanner input = new Scanner(System.in);
        int k;                      // key 
        String d;                   // data

        while (true)
        {
            System.out.println("Enter a key (negative to stop) and data: ");
            k = input.nextInt();
            if (k < 0)    break;
            
            d = input.next();
            
            insert(k, d);           // insert a new node
        }
    }
    
    /**
     * 
     * Traverse and display the key and data of every node
     */ 
    public void traverse()
    {
        System.out.println("The list contains: ");
        
        ReferencePair rp = new ReferencePair(null, head);   // start from beginning of list

        while (rp.current != null)                          // not the end of list yet
        {
            System.out.println(rp.current.getKey() + " | " + rp.current.getData());
            
            rp.moveNext();                                  // move to next node
        }
        
    }
    
    /**
     * 
     * Search for the given key k in the list
     * Return: references to the node and its predecessor, if k is found
     *         null if k is not found
     */
    public ReferencePair search(int k)
    {
        ReferencePair rp = new ReferencePair(null, head);   // start from beginning of  list
        while (rp.current != null)                          // not the end of list yet
        {
            if (rp.current.getKey() == k)                   // k is found
                return rp;                                  
            
            rp.moveNext();                                  // move to next node
        }

        return null;                                        // k is not found
    }

    /**
     * 
     * Insert a new node whose key is k and data is d
     */
    public void insert(int k, String d)
    {
        ReferencePair rp = new ReferencePair(null, head);   // start from beginning of  list
        
        while (rp.current != null)                          // not the end of list yet
        {
            if (k <= rp.current.getKey())                   // found the location to insert the new node
                break;
            
            rp.moveNext();                                  // move to next node
        }
        
        Node newNode = new Node(k, d);                      // create a new node
        
        newNode.setNext(rp.current);
        
        if (rp.previous != null)                            // will become the first node
            rp.previous.setNext(newNode);
        else                                                // will not become the first node
            head = newNode;
    }

    /**
     * 
     * Delete the node whose key is k 
     */
    public boolean delete(int k)   
    {
        ReferencePair rp = search(k);                       // search for the node whose key is k
        
        if (rp == null)                                     // k is not found. deletion fails
            return false;   
        
        if (rp.previous != null)                            // k is not in the first node
            rp.previous.setNext(rp.current.getNext());
        else                                                // k is in the first node
            head = rp.current.getNext();
                
        return true;
    }
    
    /**
     * 
     * Practice Problem 1:
     * Write a function that remove the 1st node (referred to by head) from linked list
     * 
     * Author: Kenneth Carroll
     * Date: 09-02-2020
     */
    public boolean deleteFirst() {
        
        // handle an empty list
        if(head == null) {
            
            // false signifies no deletion
            return false;
            
            
        } else {
            
            // set head to the next node
            // will always be null if head was the only node
            head = head.getNext();
        }
        
        // return true for a successful delete
        return true;
    }
    
    /**
     * 
     * Practice Problem 2:
     * Write a function that remove the last node from linked list
     */
    public boolean deleteLast() {
        
        // handle an already empty list
        if(head == null) {
            
            // allows differentiating a successful and failed delete
            return false;
        }
        // initialize a reference pair at the head
        ReferencePair rp = new ReferencePair(null, head);
        
        // traverse the list
        while(rp.current != null) {
            rp.moveNext();
        }
        
        // last element in list will be set as rp.previous
        // delete it using the key
        delete(rp.previous.getKey());
        
        // successful delete
        return true;
    }
}

/**
 * 
 * A pair of references, one to a node (current) and the other to its previous node (previous) in linked list
 */
class ReferencePair
{
    public Node previous;       // reference to the previous node
    public Node current;        // reference to the current node

    /**
     * 
     * Constructor: create a pair of references, with both initialized to null
     */
    public ReferencePair()                     
    {
        this(null, null);
    }

    /**
     * 
     * Constructor: create a pair of references initialized to p and c respectively
     */
    public ReferencePair(Node p, Node c)
    {
        previous = p;
        current = c;
    }

    /**
     * 
     * Set the pair of references initialized to p and c respectively
     */
    public void set(Node p, Node c)
    {
        previous = p;
        current = c;
    }

    /**
     * 
     * Update the references to move to the next node in linked list
     */
    public void moveNext()
    {
        if (current != null)
        {
            previous = current;
            current = current.getNext();
        }
    }
}