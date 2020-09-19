/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example03sortedarray;

/**
 *
 * @author Yang
 */

import java.util.Scanner;

public class Example03SortedArray 
{
    public static void main(String[] args)
    {
        int arrSize = 10;
        SortedArray arr = new SortedArray(arrSize);
        
        // insert some elements
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(11);
        arr.insert(66);
        arr.insert(33);

        arr.display();                      // display all elements in the array
        
        int key = 55;
        int idx = arr.binarySearch(key);    // search for 66 in the array
        if (idx < 0)                
            System.out.println(key + "Cannot find " + key);
        else
            System.out.println(key + " was found at index: " + idx);

        key = 33;                           // delete 55 from the array
        if (!arr.delete(key))        
            System.out.println(key + " not found. Deletion failed!");
        else
            System.out.println(key + " has been removed!");
        
        arr.display(); 
        SortedArray temp = new SortedArray(4);
        temp.insert(21);
        temp.insert(35);
        temp.insert(9);
        temp.insert(11);
        temp.display();
        arr = arr.merge(temp);
        arr.display();
        

    }
}

/**
 * 
 * SortedArray class that implements sorted arrays
 * Assumptions:
 * - Elements are sorted in increasing order
 * - Elements in the arrays are non-negative
 * - All elements in an array are distinct 
 */
class SortedArray
{
    private int a[];            // a: reference to an array
    private int n;              // number of elements that are currently in the array
    
    /** 
     * 
     * Constructor: create an array with the specified size and 0 element
     */
    public SortedArray(int size)
    {
        a = new int[size];
        n = 0;
    }
    
    /**
     * 
     * Display all elements that are currently in the array
     */
    public void display()
    {
        for(int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
        
        System.out.println();
    }
    
    /**
     * 
     * Insert a given key into the array
     * Return: true if the insertion completes successfully
     *         false if the array is already full and has no room for a new element
     */
    public boolean insert(int key)
    {
        if (n == a.length)
        {
            System.out.println("Array is already full. Insertion failed!");
            return false;
        }
        
        int i;                           
        for (i = n - 1; i >= 0; i --)
        {   
            if (key < a[i])             
            {
                a[i + 1] = a[i];    // shift a[i] right for one position if it is greater than the key
            }
            else
                break;              
        }
        a[i + 1] = key;
                
        n ++;
        
        return true;
    }
    
    /**
     * 
     * Search for a given key using the binary search algorithm
     * Return: index (0 ~ n-1) of the key if it is found 
     *         -1 if the key is not found
     */
    public int binarySearch(int key)
    {
        int left = 0, right = n - 1;
        int middle;
        
        while (true)
        {   // search for the key among a[left] ~ a[right]
                        
            if (left > right)           // key cannot be found
                return -1;  
            
            middle = (left + right) / 2;
            if (key == a[middle])
                return middle;
            else if (key < a[middle])
                right = middle - 1;     // key may be found among a[left] ~ a[middle-1]
            else
                left = middle + 1;      // key may be found among a[middle+1] ~ a[right]
        }
    }
    
    /**
     * 
     * Delete a given key from the array
     * Return: true if the deletion completes successfully
     *         false if the key is not found and therefore cannot be removed from the array
     */
    public boolean delete(int key)
    {
        int i = binarySearch(key);      // i: index of the key if it is found
        
        if(i < 0)                      // key was not found 
            return false;
        
        // shift every element among a[i+1] ~ a[n-1] left for one position
        for(int j = i + 1; j < n; j++)        
            a[j - 1 ] = a[j];              
        
        n--;                            
        
        return true;
    }
    
    /**
     * 
     * Read elements from user input until a negative number is entered
     */
    public void read()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter elements (negative to end): ");
        int i;
        for (i = n; i<a.length; i++)        // allow at most a.length elements to be stored into the array
        {
            int e = input.nextInt();
            
            if (e >= 0)
                insert(e);                  // store a non-negative number into the array
            else
                break;
        }
    }
    
    /**
     * 
     * Practice problem 1:
     * Write a function to merge the array with another array
     * For example, the current array contains 2 5 7 9 12
     * Merging it with another array 1 3 9 15 will 
     * result in a new array 1 2 3 5 7 9 12 15
     */
    
    public SortedArray merge(SortedArray b) {
        
        // designates the total size for the temp SortedArray
        int total_size =  n + b.n; 
        
        // determines the maximum number of iterations needed
        int largest = (n > b.n) ? n : b.n;
        
        // temporary storage for values
        SortedArray c =  new SortedArray(total_size); 
        
        // allows for a resize flag to prevent unnecessary copy at end of method
        boolean needs_resize = false;
        
        int idxA = 0, idxB = 0;
        
        // iteration over largest array, with logic to safely iterate over the smaller array
        // prevents using 2 loops, 1 for each array
        for(int i = 0; i < largest; i++) {
            
            // i is still in range of both array sizes
            if(i < b.n && i < n) {
                c.insert(a[i]);
                
                // checks if the element in b exists already to ensure only distinct entries are merged
                if(binarySearch(b.a[i]) < 0) {
                    c.insert(b.a[i]);
                } else {
      
                    // if there is a duplicate removed, the calculated size needs to decrease
                    total_size--;
                    
                    // set resize flag
                    needs_resize = true;
                }
            
            // i is only in range for this 
            } else if (i < n) {
                c.insert(a[i]);
                
            // i is only in range for b
            } else {
                
                // checks if the element in b exists already to ensure only distinct entries are merged
                if(binarySearch(b.a[i]) < 0) {   
                    c.insert(b.a[i]);
                    
                } else {
                    
                    // if there is a duplicate removed, the calculated size needs to decrease
                    total_size--;
                    
                    // set resize flag
                    needs_resize = true;
                }
                
            }
        }
        
        if(needs_resize) {
            
            // copies the array onto itself at a smaller size, not doing
            // so allows additional insert() calls if any duplicates are removed
            System.arraycopy(c.a, 0, c.a, 0, total_size);
        }
        
        return c;
        
        
    }
    
}
