/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example16heap;

/**
 *
 * @author Yang
 */

import java.util.Scanner;
import java.lang.Math;

public class Example16Heap 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Heap myheap = new Heap(100);
        
        boolean loop = true;
        
        while(loop) {
            
            System.out.println("Select from: ");
            System.out.println("1. Read items and build heap");
            System.out.println("2. Display heap");
            System.out.println("3. Insert a node");
            System.out.println("4. Remove the largest node");
            System.out.println("5. Search for a key");
            System.out.println("0. Exit");
            
            switch(input.next()) {
                case "1":
                   myheap.build();
                   break;
                case "2":
                    myheap.display();
                    break;
                case "3":
                    System.out.println("Enter a non-negative integer to be inserted: ");
                    myheap.insert(input.nextInt());
                    break;
                case "4":
                    myheap.deleteMax();
                    break;
                case "5":
                    System.out.println("Enter the key to be searched for: ");
                    myheap.search(input.nextInt());
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    break;
            }
            
        }
    }
    
}

/**
 * 
 * Assume all items in the heap are non-negative
 */
class Heap
{
    private int n;
    private int a[];

    public Heap(int size)
    {
        a = new int[size];
	n = 0;                      // initialized with an empty heap
                           // build the heap: bottom-up approach
    }

    public void build()
    {
        // Store a set of data items into the array. They will make a heap without any adjustment
        // Prompt user input
        System.out.println("Enter integers(negative to stop): ");
        
        // Build a heap without checking for the heap condition ( lines 90 - 114 )
        // initialize an input in the proper scope for the build function
        Scanner input = new Scanner(System.in);
        
        // initialize an index to track the input position into a[]
        int idxInput = 0;
        
        // initialize a loop that will only break on negative inputs
        while(true) {
            
            // read the next input
            int in = input.nextInt();
            
            // check for the break condition
            if(in < 0) {
                break;
            }
            
            // insert the value
            a[idxInput] = in;
            
            // increment the index
            idxInput++;
        }
        
        // assign appropriate index
        n = idxInput;
        
        // satisfy the heap condition for each subtree
        
        for(int i = 0; i < n/2; i++) {
            upheap(i);
        }
        
           
        
        // In the homework assignment, need to remove the above 11 statements and replace them with your own code that
        // 1) ask the user to enter non-negative intergers (negative to stop)
        // 2) use the entered non-negative integers to build a heap using the algorithm discussed in class
        
    }
    
    
    public void upheap(int i) {
        // calculate the last subtree
        int idxSubtree = ( (n / 2) - 1 );
            
        // increment over each subtree
        while(idxSubtree >= i) {
                
            // upheap the subtree
            heapRecovery(idxSubtree);
                
            // move to the next subtree
            idxSubtree--;
        }
    }
    
     /**
     * 
     * Check and recover the heap condition at a given parent index
     * 
     * 
     * Swap(a, b) is used in this function, see declaration below this function
     * @param i where i is the index of the parent node
     */
    public void heapRecovery(int i) {
        // index of the respective tree leaves
        int idxLeft = i*2+1;
        int idxRight = i*2+2;
        
        // check if both leaf index are valid
        if(idxLeft < n && idxRight < n) {
            
            // determine which child has the higher value, and store it as an idxMax
            int idxMax = a[idxLeft] > a[idxRight] ? idxLeft : idxRight;
            
            // if necessary, swap
            if(a[i] < a[idxMax]) {
                swap(i, idxMax);
            }
            
         // check if left is the valid leaf
        } else if(idxLeft < n) {
            
            // check if swap is necessary
            if(a[i] < a[idxLeft]) {
                swap(i, idxLeft);
            }
         // check if right is the valid leaf
        } else if (idxRight < n) {
            if(a[i] < a[idxRight]) {
                swap(i, idxRight);
            }
            
         // neither leaf is valid
        } else {
            
            // return the void method
            return;
        }
        
        
        
    }
    
    /**
     * Basic swap method
     * @param idxA index of item A
     * @param idxB index of item B
     */
    
    public void swap(int idxA, int idxB) {
        int temp = a[idxA];
        a[idxA] = a[idxB];
        a[idxB] = temp;
    }
    
    public void display() {
        
        // count the current number of entries on the existing line
        int counter = 0;
        
        // print the 
        int line = 0;
        
        // iterate over the array, print each value
        for(int i = 0; i < n; i++) {
            
            //value prints with a space
            System.out.print(a[i] + " ");
            
            //counter increment
            counter++;
            
            // check if the counter is within the capacity of the given line
            if(counter == (Math.pow(2, line)) ) {
                
                // start a new line
                System.out.println();
                
                //reset counter
                counter = 0;
                
                // update line number
                line++;
            }              
            
        }
        System.out.println();
    }
    
    public void search(int key) {
        int idxFound = -1;
        for(int i = 0; i < n; i++) {
            if(a[i] == key) {
                idxFound = i;
                break;
            }
        }
        
        if(idxFound > -1) {
            System.out.println(key + " found at index " + idxFound);
        } else {
            System.out.println(key + " NOT found");
        }
    }
    
    public void insert(int key) {
        
        // handle if the heap is at maximum size 
        if(n == a.length) {
            System.out.println("Heap at maximum size");
            return;
        }
        
        if(key < 0) {
            System.out.println("Non-negative integer required for insertion");
            return;
        }
        
        // insert the key at the next available slot in the array
        int idxKey = n;
        a[idxKey] = key;
        
        // update n for new size
        n++;
        
        // calculate the index of the parent
        int idxParent = (idxKey-1)/2;
        
        //loop over worst case scenario, breaking before then if possible
        while(idxParent >= 0) {
            
            // check for recovery
            if(a[idxParent] < a[idxKey]) {
                
                //perform the swap
                swap(idxParent, idxKey);
                
                //update the index as necessary
                idxKey = idxParent;
            } else {
                break;
            }
            
            // loop incrementation by parent level
            idxParent = (idxParent-1)/2;
        }
        
        
        
    }
    
    public void deleteMax() {
        swap(0, n-1);
        n--;
        for(int i = 0; i < n/2; i++) {
            upheap(i);
        }
    }
    
    public boolean isEmpty()
    {
        return (n == 0);
    }
    
    
    
    
   
    
    /**
     * 
     * Remove the root from the heap
     */ 
    public int remove()
    {
        int key = a[0];
        a[0] = a[n-1];
        n --;
        
        downHeap(0);

        return key;
    }

    /**
     * 
     * if node a[i] is smaller than some of its children, 
     * move it down the heap until the heap condition is recovered, or it reaches the leaf 
     */
    public void downHeap(int i)
    {
        int key = a[i];
        
        int ci = maxChild(i);       // index of the larger child of a[i], if exists
        while (ci != -1)            // has not arrived at a leaf node yet
        {
            if (key >= a[ci])       // no less than the bigger child
                break;              // stop down heap adjustment

            a[i] = a[ci];
            i = ci;
            ci = maxChild(i);
        }
        a[i] = key;
    }

    /**
     * 
     * Return the index of the larger child of node a[i]
     * Return -1 if a[i] has no children
     */
    public int maxChild(int i)
    {
        if (2 * i + 1 >= n)                     // no children
            return -1;
        else if (2 * i + 2 >= n)                // no right child
            return 2 * i + 1;
        else if (a[2 * i + 1] >= a[2 * i + 2])	// have two children and left child is larger
            return 2 * i + 1;
        else					// right child is larger
            return 2 * i + 2;
    }
}

