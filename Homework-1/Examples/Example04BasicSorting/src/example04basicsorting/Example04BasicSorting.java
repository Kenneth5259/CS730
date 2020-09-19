/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example04basicsorting;

/**
 *
 * @author Yang
 */

/**
 * 
 * Implement basic sorting algorithms
 * Assumptions:
 * - All elements in the array are non-negative
 */

import java.util.Scanner;
import java.util.Random;

public class Example04BasicSorting 
{
    public static void main(String[] args)
    {
        int arrSize = 100;                      // size of the array
        MyArray arr = new MyArray(arrSize);
        
        Scanner input = new Scanner(System.in);
        int option;
        do
        {
            System.out.println("Select from:\n1. Read Array\n2. Generate Array\n3. Print Array\n4. Sort\n0. Quit");
            option = input.nextInt();
            switch (option)
            {
                case 1:
                    arr.read();
                    break;
                case 2:
                    arr.randGenerate();
                    break;
                case 3:
                    arr.display();
                    break;
                case 4:
                    sortArray(arr);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option!  Try again: ");
            }
            
        } while (option != 0);
        
        System.out.println("Thanks for using my program.");
    }
    
    static void sortArray(MyArray ar)
    {
        Scanner input = new Scanner(System.in);
        int sortOption;
        do
        {
            System.out.println("Select from:\n1. Bubble sort\n2. Selection sort\n3. Insertion sort\n4. Odd-Even Sort\n0. Quit");
            sortOption = input.nextInt();
            switch (sortOption)
            {
                case 1:
                    ar.bubbleSort();
                    break;
                case 2:
                    ar.selectionSort();
                    break;
                case 3:
                    ar.insertionSort();
                    break;
                case 4:
                    ar.oddevenSort();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid sort option!  Try again: ");
            }
        } while (sortOption != 0);
    }
}

/**
 * 
 * MyArray class that includes basic sorting algorithms implemented
 */
class MyArray
{
    private int a[];            // a: reference to an array
    private int n;              // number of elements that are currently in the array
    
    /**
     *
     * Constructor: create an array with the specified size and 0 element
     */
    public MyArray(int size)
    {
        a = new int[size];
        n = 0;
    }
    
    /**
     * 
     * Read elements from user input into the array until a negative number is entered
     */ 
    public void read()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter elements (negative to end): ");
        
        int i;
        for (i = 0; i < a.length; i++)        // allow at most a.length elements to be stored into the array
        {
            int e = input.nextInt();
            
            if (e >= 0)
                a[i] = e;                   // store a non-negative number into the array 
            else                        
                break;
        }
        n = i;                              // record the total number of elements that have been stored into the array
        
    }
    
    /**
     * 
     * Randomly generate non-negative numbers and store them into the array
     */
    void randGenerate()
    {
        Random rand = new Random();
        
        n = rand.nextInt(a.length + 1);     // number of elements: [0, a.length]
        for (int i = 0; i < n; i++)
            a[i] = rand.nextInt(20);        // random integer: [0,19]
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
     * Use bubble sort algorithm to sort elements in non-decreasing order
     * 
     * 
     * Modified for Homework 1 Problem 1
     * Solution: Kenneth Carroll
     * Date: 8-17-2020
     */
    public void bubbleSort()
    {
        boolean swapped = false;                // false: no swap happened during the current round
        
        // last comparison incrementing left to right
        for (int i = 0; i < n; i++)             
        {   
            
            // starts from right most element moving left, terminates at last comparison
            for (int j = n-1; j > i; j--)         
            {
                // check if element to right is larger
                if (a[j] < a[j - 1])
                {
                    // perform swap and set swapped flag
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                    swapped = true;
                }
            }
            
            if (!swapped)                       // already sorted
                break;
            
            swapped = false;
        }
    }
    
    /**
     * 
     * Use selection sort algorithm to sort elements in non-decreasing order
     * 
     * 
     * Modified for Homework 1 Problem 2
     * Solution: Kenneth Carroll
     * Date: 8-17-2020
     */
    
    public void selectionSort()
    {
        // start from rightmost element
        for (int i = n-1; i > 0; i--)        
        {   
            
            // index of maximum value
            int idxMax = i;
            
            // sweep left to right for largest element
            for (int j = 0; j < i; j ++)
            {
                
                // larger element found, index recorded
                if (a[j] > a[idxMax])           
                    idxMax = j;
            }
            
            // swap a[i] and a[idxMax]
            int t = a[i];
            a[i] = a[idxMax];
            a[idxMax] = t;
        }
    }
    
    /**
     * 
     * Use insertion sort algorithm to sort elements into non-decreasing order
     * 
     * 
     * Modified for Homework 1 Problem 3
     * Solution: Kenneth Carroll
     * Date: 8-17-2020
     */
    
    public void insertionSort()
    {
        // sorted insert starting from rightmost element moving left
        for (int i = n-1; i >= 0; i--)           
        {   
            // temp store to be inserted
            int t = a[i];
            
            int j;
            
            // iterate through subarray, left to right
            for (j = i+1; j < n; j++)
            {
                // if insertion value is greater, shift value left, open slot for insertion
                if (t > a[j])
                    a[j-1] = a[j];
                else
                    break;
            }
            // insert into opened slot
            a[j-1] = t;
            
        }
    }
    
    /**
     * 
     * Use odd-even sort algorithm to sort elements into non-decreasing order
     * 
     * Homework 1 Problem 4
     * Solution: Kenneth Carroll
     * 8-17-2020
     */
    
    /**
     * 
     * Practice problem 1:
     * Write a function to remove duplicate from the array
     * For example, if the array contains 8 5 12 15 7 3 8 15 30 15
     * After calling the function, the array will become 3 5 7 8 12 15 30
     * 
     * 
     * Solution: Kenneth Carroll
     * Date: 8-17-2020
     */
    
    public void oddevenSort() {
        
        // swapped flag, initialized true to enter loop
        boolean swapped = true;
        
        // run until no swaps occur
        while(swapped) {
            
            //reset flag
            swapped = false;
            
            // first pass on odd, next pass on even
            for(int i = 1; i >= 0; i--) {
                
                // loop through all pairs of the given type
                for(int j = i; j < n-1; j += 2) {
                    
                    // if left is larger perform swap, set flag
                    if(a[j] > a[j+1]) {
                        int t = a[j];
                        a[j] = a[j+1];
                        a[j+1] = t;
                        swapped = true;
                    }
                }
            }
        }
    }
    
    public void filterDuplicates() {
        
        // sort before filter
        this.bubbleSort();
        
        // defines the size of the new array, will decrement at each duplicate
        int newSize = n;
       
        // will check each value for duplicates;
        for(int i = 0; i < n-1; i++) {
            
            // stores current value for check
            int currentVal = a[i];
            
            for(int j = i+1; j < n; j++) {
                
                //checks if there are no more duplicates
                if(a[j] > currentVal) {
                    
                    //moves the position of the parent loop to the next unique entry
                    i = (j-1);
                    break;
                } else {
                    
                    //sets a filter value of first array entry
                    //filter method will ignore first array entry
                    a[j] = a[0];
                    
                    //decrements new array size
                    newSize--;
                }
                
            }
                
        }
        
        // temp value store
        int[] b = new int[newSize];
        
        // index tracking for temp value store
        int idxB = 1;
        
        // ensure 1st value retained before filtering all other instances
        b[0] = a[0];
        
        // fill the temp value store
        for(int i = 1; i < n; i++) {
            
            //checks for defined filter value
            if(a[i] != a[0]) {
                b[idxB] = a[i];
                idxB++;
            }
        }
        
        // update size and array
        n = idxB;
        a = b;
    }
}
