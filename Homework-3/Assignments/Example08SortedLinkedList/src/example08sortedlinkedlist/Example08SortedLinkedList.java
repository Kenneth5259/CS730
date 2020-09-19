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
import java.util.Random;

public class Example08SortedLinkedList 
{
    
    /**
     * 
     * @param args 
     * 
     * author: Kenneth Carroll
     * date: 09-06-2020
     * 
     * User interface for demonstrating priority queue linked list
     */
    public static void main(String[] args) 
    {
        // Handle user input
        Scanner input = new Scanner(System.in);
        
        // initialize a priority queue linked list
        PriorityQueueLinkedList pqll = new PriorityQueueLinkedList();
        
        // array for holding input or generated values
        int[] unsorted = null;
        
        boolean loop = true;
        while (loop)
        {
            // user menu
            System.out.println("Select From: ");
            System.out.println("1. Read Array");
            System.out.println("2. Generate Array");
            System.out.println("3. Sort");
            System.out.println("0. Quit");
            
            // read the next line
            String s = input.nextLine();
            
            // switch over input
            switch(s) {
                
                case "1":
                    
                    System.out.println("Enter keys (negative to stop): ");
                   
                    // parse user input by whitespace
                    String[] inputs = (input.nextLine().split("\\s+"));
                    
                    // initialize the array for the number of inputs 
                    unsorted = new int[inputs.length];
                    //iterate over all inputs
                    for(int i = 0; i < inputs.length; i++) {

                        // convert input into int
                        int in = Integer.parseInt(inputs[i]);
     
                        // insert into the array (negative handled on sort)
                        unsorted[i] = in;
                    }
                    
                    // break the switch statement 
                    break;
                    
                case "2":
                    
                    // prompt user input
                    System.out.println("Enter the number of elements to generate");

                    // read an integer in 
                    int k = Integer.parseInt(input.nextLine());
                    
                    // Show generated elements
                    System.out.print("The following elements have been generated: ");
                    
                    // initialize unsorted at size k
                    unsorted = new int[k];
                    // loop for k iterations
                    Random ran = new Random();
                    for(int i = 0; i < k; i++) {

                        // insert into unsorted holder
                        unsorted[i] = (int)(ran.nextInt(k));
                        System.out.print(unsorted[i] + " ");
                        
                    }
                    // end line for show generated elements
                    System.out.println();
                    
                    break;
                    
                case "3":
                    
                    // instantiate a new Priority Queue
                    pqll = new PriorityQueueLinkedList();
                    
                    // enqueue each value in the unsorted
                    if(unsorted != null){
                        
                        // iterate over the unsorted values
                        for(int i = 0; i < unsorted.length; i++) {
                            
                            // check for the delimiter from option 1
                            if(unsorted[i] > -1) {
                                
                                // sorted insert for the priority queue
                                pqll.enqueue(unsorted[i]);
                            } 
                        }
                        // show the current queue
                        pqll.view();
                    
                    // handle sort without existing inputs from option 1 or 2
                    } else {
                        System.out.println("No values have been entered or stored. Please select 1 or 2");
                    }
                    
                    break;
                    
                default:
                    
                    // break the loop and end the program
                    loop = false;
                    break;
            }
        }
        System.out.println("Thanks for using my program.");
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

/**
 * 
 * Homework Assignment
 * PriorityQueueLinkedList class
 * 
 * Author: Kenneth Carroll
 * Date: 09-06-2020
 * 
 */

class PriorityQueueLinkedList {
    
    // create the front node
    private Node front;
    
    // class constructor
    public PriorityQueueLinkedList() {
        
        // initialized at null
        front = null;
    }
    
    // check if queue is empty
    public boolean isEmpty() {
        
        // null indicates queue is empty
        return (front == null);
    }
    
    public void enqueue(int k) {
        
        // defines a node with the given key
        Node n = new Node(k, "");
        
        // if it is empty, no logic needed other than assign
        if(isEmpty()) {
           front = n;
           return;
        }
        
        // generate new reference pair for iteration through list
        ReferencePair rp = new ReferencePair(null, front);
        
        // traverse through list until lesser key is found
        // original loop condition rp.current.getKey() > k && 
        while(rp.current != null) {
            
            // find the sorted position for insert
            if(rp.current.getKey() <= k) {
                break;
            }
            rp.moveNext();
        }
        
        // insert the node as next node for previous value
        // handles null error with only 1 element
        if(rp.previous != null) {
            
            // connect inserted node to current to restore the list
            rp.previous.setNext(n);
            n.setNext(rp.current);
        } else {
            Node temp = front;
            front = n;
            n.setNext(temp);
        }
        
       
    }
    
    public int peekFront() {
        
        // check for empty queue
        if(front == null) {
            return -1;
        }
        
        //return the associated key for the front
        return (front.getKey());
    }
    
    public int dequeue() {
        
        // check for empty queue
        if(isEmpty()) {
            return -1;
        }
        
        // get the key from the node
        int key = front.getKey();
        
        // move the head to the next node
        front = front.getNext();
        
        // return the key
        return key;
    }
    
    public void view() {
        
        //generate reference pair at fron
        ReferencePair rp = new ReferencePair(null, front);
        
        // iterate over the list
        while(rp.current != null) {
            
            // display values
            System.out.print(rp.current.getKey() + " ");
            
            // iteration
            rp.moveNext();
        }
        
        // move to new line for readability
        System.out.println();
    }
}