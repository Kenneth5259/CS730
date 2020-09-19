/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example14advancedsort;

/**
 *
 * @author yangm
 */

import java.util.Scanner;
import java.util.Random;

public class Example14AdvancedSort 
{

    public static void main(String[] args)
    {
        int arrSize = 20;                      // size of the array
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
            System.out.println("Select from:\n1. Merge sort\n2. Shell sort\n3. Quick sort\n0. Quit");
            sortOption = input.nextInt();
            switch (sortOption)
            {
                case 1:
                    ar.mergeSort();
                    break;
                case 2:
                    ar.shellSort();
                    break;
                case 3:
                    ar.quickSort();
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
 * MyArray class implements advanced sorting algorithms on arrays
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
     * Implement a recursive approach to sorting array elements in non-decreasing order using merge sort algorithm
     */
    public void mergeSort()
    {
        mergeSort(0, n - 1);
    }
    
    /**
     * 
     * Recursive helper function
     * Sort elements a[left] ~ a[right] in non-decreasing order using merge sort algorithm
     * 
     */
    public void mergeSort(int left, int right)
    {
        // base case ................................
        if (left == right)              // Need to do nothing for sorting one single element: a[left] (same as a[right])
            return;
        
        // general case .............................

        // First of all, sort both the first half and the second half 
        int middle = (left + right) / 2;
        mergeSort(left, middle);             // sort the first half
        mergeSort(middle + 1, right);        // sort the second half
        
        // Then merge two halves together while maintaining the non-decreasing order among elements in the resulting array
        merge(left, middle, right);
    }
    
    /**
     * 
     * Merge two halves between a[l]~a[r] together while maintaining the non-decreasing order among elements in the resulting array 
     * The result will copied back into a[l]~a[r]
     */
    public void merge(int l, int m, int r)
    {
        int workspace[] = new int[n];
        
        int j = 0;
        int idx1 = l;                   // index of the element that is currently be looking at in the 1st half
        int idx2 = m + 1;               // index of the element that is currently be looking at in the 2nd half
        
        while (idx1 <= m && idx2 <= r)  // none of the halves has been finished processing yet
        {
            if (a[idx1] < a[idx2])
            {
                workspace[j] = a[idx1];
                idx1 ++;
            }
            else
            {
                workspace[j] = a[idx2];
                idx2 ++;
            }            
            j = j + 1;
        }
        
        while (idx1 <= m)               // in the 1st half, there are still some elements need to be processed
        {
            workspace[j] = a[idx1];
            idx1 ++;
            j = j + 1;
        }
        while (idx2 <= r)               // in the 2nd half, there are still some elements need to be processed
        {
            workspace[j] = a[idx2];
            idx2 ++;
            j = j + 1;
        }
        
        // copy sorted elements back into a[l] ~ a[r]
        for (int i = 0; i < r - l + 1; i ++)
            a[l + i] = workspace[i];
    }
    
    /**
     * 
     * Use shell sort algorithm to sort elements in non-decreasing order
     * Assume increment factors k are 1, 2, 5, 14, 41, and so on
     */
    public void shellSort()
    {
        // compute the starting increment factor k (the largest that is less than n)
        int k = 1;
        while (k < n)
            k = 3 * k - 1;
        
        k = (k + 1) / 3;       
        
        // for each round of the algorithm (each round uses a different k)
        while (k > 0)
	{
            for (int j = 0; j < k; j++)     // among k subgroups of elements
                insertionSort(n, j, k);     // do insertion sort within the j-th subgroup 

            k = (k+1)/3;
	}
    }
    
    public void insertionSort(int n, int j, int k)
    {
        for (int i = j + k; i < n; i = i + k)                    
        {   
            int t = a[i];
            int idx;
            for (idx = i - k; idx >= 0; idx = idx - k)
            {
                if (t < a[idx])
                    a[idx + k] = a[idx];
                else
                    break;
            }
            a[idx + k] = t;
        }
    }
    
    /**
     * 
     * Use quick sort algorithm to sort elements a[0] ~ a[n-1] in non-decreasing order
     */
    public void quickSort()
    {
        quickSort(0, n - 1);
    }
    
    /**
     * 
     * Recursive helper function
     * Use quick sort algorithm to sort elements a[left] ~ a[right] in non-decreasing order
     */
    public void quickSort(int left, int right)
    {
	if (left < right)
        {	
            // partition a[left] ~ a[right] and move pivot into a[i], 
            // such that a[left] ~ a[i-1] are no greater than a[i] and 
            // a[i+1] ~ a[right] are no less than a[i]
            int i = partition(left, right);   
            
            // sort a[left] ~ a[i-1]
            quickSort(left, i-1);
            
            // sort a[i+1] ~ a[right]
            quickSort(i+1, right);
        }
    }

    public int partition(int left, int right)
    {	
        boolean lr = true;
        
	while (left < right)
	{
		if (a[left] > a[right])
		{	
                    int t = a[left];
                    a[left] = a[right];
                    a[right] = t;
                    
                    lr = !lr;
		}
                
		if (lr)	
                    right--;
		else		
                    left++;
	}
	return left;
    }    
    
    /**
     * 
     * Practice problem:
     * Implement a non-recursive approach (using loops) to sorting array elements in non-decreasing order using merge sort algorithm
     */
    
}


