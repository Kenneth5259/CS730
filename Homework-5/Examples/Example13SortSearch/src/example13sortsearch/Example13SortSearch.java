/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example13sortsearch;

/**
 *
 * @author Yang
 */

import java.util.Scanner;
import java.util.Random;

public class Example13SortSearch 
{
    public static void main(String[] args)
    {
        int arrSize = 20;                      // size of the array
        MyArray arr = new MyArray(arrSize);
        
        Scanner input = new Scanner(System.in);
        int option;
        do
        {
            // menu of options for uses to choose from
            System.out.println("Select from:\n1. Read Array\n2. Generate Array\n3. Print Array\n4. Sort\n5. Search\n6. Greatest\n0. Quit");
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
                    arr.bubbleSort();
                    break;
                case 5: 
                    System.out.print("Enter a key: ");
                    int key = input.nextInt(); 
                    int idx = arr.binarySearch(key);
                    if (idx < 0)
                        System.out.println(key + " is not found!");
                    else
                        System.out.println(key + " is found at index " + idx);
                    break;
                case 6:
                    System.out.println(arr.greatestElement());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option!  Try again: ");
            }
        } while (option != 0);
        
        System.out.println("Thanks for using my program.");        
    }
}

/**
 * 
 * MyArray class implements basic searching and sorting algorithms on arrays
 * Assumptions:
 * - All elements in the array are non-negative
 */
class MyArray
{
    private int a[];            // a: reference to an array
    private int n;              // number of elements that are currently in the array
    
    /**
     *
     * Constructor: create an empty array with the specified size 
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
        for (i = 0; i < a.length; i++)      // allow at most a.length elements to be stored into the array
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
     * Randomly generate non-negative integers and store them into the array
     */
    public void randGenerate()
    {
        Random rand = new Random();
        
        n = rand.nextInt(a.length + 1);     // random integer as the number of elements: [0, a.length]
        for (int i = 0; i < n; i++)
            a[i] = rand.nextInt(15);        // random integer as value of an element: [0,14]
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
     * Implement a recursive approach to sorting array elements into non-decreasing order using insertion sort algorithm
     */
    public void insertionSort()
    {
        insertionSort(n - 1);           // sort elements a[0] ~ a[n-1]
    }
    
    /**
     * 
     * Recursive helper function 
     * Sort elements a[0] ~ a[right] into non-decreasing order using insertion sort algorithm
     */
    public void insertionSort(int right)
    {
        // base case ................................
        if (right == 0)             // Need to do nothing for sorting one single element: a[0]
            return;

        // general case .............................

	
        // First of all, sort elements a[0] ~ a[right-1] 
        insertionSort(right-1);      

        // Then insert a[right] to the appropriate place among a[0] ~ a[right-1]
        int t = a[right];         
        int i = right-1;
        while (i >= 0)
        {
            if (a[i] > t)
            {
                a[i+1] = a[i];
                i--;
            }
            else	break;
        }

        a[i+1] = t;
    }

    /**
     * 
     * Implement a recursive approach to search for the given key in a sorted array using binary search algorithm
     */    
    public int binarySearch(int key)
    {
        return binarySearch(key, 0, n - 1);
    }
    
    /**
     * 
     * Recursive helper function 
     * Search for the given key among elements a[left] ~ a[right]
     */
    public int binarySearch(int key, int left, int right)
    {
        if (left > right)
            return -1;
        
        int middle = (left + right) / 2;
        if (key == a[middle])
            return middle;
        else if (key < a[middle])
            return binarySearch(key, left, middle - 1);     // key may be found among a[left] ~ a[middle-1]
        else
            return binarySearch(key, middle + 1, right);    // key may be found among a[middle+1] ~ a[right]
    }

    /**
     * 
     * Practice problem:
     * Implement a recursive approach to sorting array elements into non-decreasing order using bubble sort algorithm
     */
    public void bubbleSort() {
        boolean swapped = false;
        for(int i = 0; i < n - 1; i++) {
            if(a[i] > a[i+1]) {
                int temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                swapped = true;
            }
        }
        if(swapped) {
            bubbleSort();
        }
    }
    
    
    /**
     * 
     * Practice problem:
     * Implement a recursive approach to sorting array elements into non-decreasing order using selection sort algorithm
     */
    
    public void selectionSort() {
        selectionSort(n-1);
    }
    public void selectionSort(int x) {
        for(int i = 0; i < x; i++) {
            if(a[i] > a[i+1]) {
                int temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
            }
        }
        if(x > 1) {
            selectionSort(x-1);
        }
    }
    
    
    /**
     * 
     * Practice problem:
     * Implement a recursive approach to finding the greatest element in the array without sorting the elements
     */
    
    public int greatestElement() {
        return greatestElement(0);
    }
    public int greatestElement(int lastIndex) {
        
        if(lastIndex < n-1) {
            int great = greatestElement(lastIndex + 1);
            return a[lastIndex] > great ? a[lastIndex] : great;
        } else {
            return a[lastIndex] > a[lastIndex + 1] ? a[lastIndex] : a[lastIndex + 1];
        }
        
    }
}

