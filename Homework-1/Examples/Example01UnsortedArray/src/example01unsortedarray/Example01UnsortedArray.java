/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example01unsortedarray;

/**
 *
 * @author Yang
 */

/**
 *
 * Demonstrate basic operations of an unsorted array
 * Assumption:
 *   - The array is unsorted
 *   - All elements in the array are distinct (no duplicated elements exist)
 */

public class Example01UnsortedArray 
{
    public static void main(String[] args)
    {
        int size = 10;              // size of the array
        int a[] = new int[size];    // make an array with the specified size
        int n = 0;                  // number of elements that are currently in the array
        
        // Sequentially insert 6 elements
        a[0] = 44;
        a[1] = 55;
        a[2] = 22;
        a[3] = 11;
        a[4] = 66;
        a[5] = 33;
        n = 6;
        
        // Display all elements that are currently in the array
        int i;
        for(i = 0; i < n; i ++)
            System.out.print(a[i] + " ");
        System.out.println();
        
        // Search for a given key 66 
        int key = 66;
        for(i = 0; i < n; i++)
            if(a[i] == key) break;    // exit the loop if key is found
        
        if(i == n)                      // key was not found in the array
            System.out.println(key + " cannot be found.");
        else
            System.out.println(key + " is found at index: " + i);
        
        // Search for a given key 18 
        key = 18;
        for(i = 0; i < n; i++)
            if(a[i] == key) break;    // exit the loop if key is found
        
        if(i == n)                      // key was not found in the array
            System.out.println(key + " cannot be found.");
        else
            System.out.println(key + " is found at index: " + i);
        
        // Delete a given key 11
        key = 11;
        for(i = 0; i < n; i++)
            if(a[i] == key)   break;
        
        if(i == n)                      // key was not found in the array
            System.out.println(key + " cannot be found. Deletion failed!");
        else
        {
            // shift a[i+1] ~ a[n-1] left for one position
            for(int j = i+1; j < n; j++)
                a[j-1] = a[j];

            n--;
        }
        
        // Display all elements that are currently in the array
        for(i = 0; i < n; i ++) // display items
            System.out.print(a[i] + " ");
        System.out.println();
        
        /**
         * 
         * Practice problem 1:
         * Write statements to insert 200 as the 6th element in the array
         */
        
        System.out.println("Problem 1 Solution: ");
        
        //Changable Values for testing robustness
        key = 200; 
        int element = 6;
        
        //Safety check for Element being within the size of the array
        if(element < size) { 
            
            //Check if elements need to be shifted before insertion
            if(n>=element) { 
                
                //Shifts elements to the right by 1, starting from the end finishing at the desired element
                for(i = n; i > element-1; i--) { 
                    a[i] = a[i-1];
                }
            }
            
            //Inserts into open slot
            a[element-1] = key; 
            
            //Size Increase following insertion
            n++; 
        } else {
            
            //Size Error Message
            System.out.println("The element is outside the size of the array. Cannot be inserted."); 
        }
        
        for(i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        
        
        /**
         * 
         * Practice problem 2:
         * Write statements to insert 100 into the array so that it becomes the 1st element
         */
        System.out.println("Problem 2 Solution: ");
        
        
        //Definted in Problem 1
        key = 100;
        element = 1; 
        
        //Safety check for Element being within the size of the array
        if(element < size) { 
            
            //Check if elements need to be shifted before insertion
            if(n>=element) { 
                
                //Shifts elements to the right by 1, starting from the end finishing at the desired element
                for(i = n; i > element-1; i--) { 
                    a[i] = a[i-1];
                }
            }
            
            //Inserts into open slot
            a[element-1] = key; 
            
            //Size Increase following insertion
            n++; 
            
        } else {
            
            //Size Error Message
            System.out.println("The element is outside the size of the array. Cannot be inserted."); 
        }
        
        for(i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
