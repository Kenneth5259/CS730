/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrollk.week5;

/**
 *
 * @author kennethcarroll
 */

import java.util.Random;
import java.util.Scanner;


public class Assignment4 {
    public static void main(String[] args) {
        
        // initialize an unsorted array object
        unsortedArr arr = new unsortedArr();
        
        // set loop 
        boolean loop = true;
        
        // initialize scanner
        Scanner input = new Scanner(System.in);
        
        // looped system menu
        while(loop) {
            System.out.println("Select From: ");
            System.out.println("1. Read Array");
            System.out.println("2. Generate Array");
            System.out.println("3. Print Array");
            System.out.println("4. Median");
            System.out.println("5. Run Test Cases");
            System.out.println("0. Quit");
            
            switch(input.nextLine()) {
                case "1":
                    arr.read();
                    break;
                case "2":
                    arr.generate();
                    break;
                case "3":
                    arr.display();
                    break;
                case "4":
                    System.out.println("Median: " + arr.recursiveMedian());
                    break;
                case "5":
                    
                    // initialize test pass/fail counters
                    int totalPass = 0, totalFail = 0;
                    
                    // loop for an arbitrarily large number
                    for(int i = 1; i <= 10000; i++) {
                        
                        // display counter
                        System.out.print("Test Case " + i + ": ");
                        
                        // generate an array, and copy to keep sort/partitioning separate
                        arr.generate();
                        unsortedArr testArr = arr;
                        
                        // check if the test passes, and increment the appropriate counter
                        if(arr.recursiveMedian() == testArr.sortedMedian()) {
                            System.out.print("pass");
                            totalPass++;
                         } else {
                            System.out.print("fail");
                            totalFail++;
                        }
                        
                        System.out.println();
                    }
                    System.out.println("Total Passed: " + totalPass + ", Total Failed: " + totalFail);
                    System.out.println();
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter 0, 1, 2, 3, 4, or 5.");
                    break;
            }
        }
    }
}

// creating a class to hold the array, allow for median to be a member function
class unsortedArr {
    
    // private array properties
    private int a[];
    public int n;
    
    // constructor for known size
    public unsortedArr(int x) {
        a = new int[x];
        n = x;
    }
    
    // constructor for unknown size
    public unsortedArr() {
        a = new int[10];
        n = 10;
    }
    
    // display all elements in the array
    public void display() {
        for(int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    // generation method to allow for quick testing
    public void generate() {
        Random rand = new Random();
        n = rand.nextInt(30);
        a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = rand.nextInt(30);
        }
    }
    
    
    // read in the information, copied directly from example 14
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
    
    
    // method to sort then return median. Used for testing
    public int sortedMedian() {
        if(n < 1) {
            return -1;
        }
        
        int idxMedian = n % 2 == 1 ? n/2 : (n-1)/2;
        
        for(int i = n-1; i > 0; i--) {
            
            for(int j = 0; j < i; j++) {
                
                if(a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
       return a[idxMedian];
        
    }
    
    
    // wrapper function for kthSmallest element, sets K to a calculated median index
    public int recursiveMedian() {
        
        // list generation can create an empty list, this handles it
        if(n < 1) {
            return -1;
        }
        
        // index of the expected median
        int idxMedian = n % 2 == 1 ? n/2 : (n-1)/2;
        
        // run kthSmallestElement, initial lower and upper bounds at full length
        return kthSmallestElement(0, n-1, idxMedian);
    }
    
    /**
     * 
     * kthSmallestElement returns the Kth smallest  value of a list without a full sort
     * This is achieved by partitioning the array and subsequent subarrays until
     * a pivot is placed at the index of the median if the array was sorted
     * 
     * 
     * @param lower defines the lower bound, and will be placed at pivot of partition
     * @param upper defines the upper bound 
     * @return will only return the median value or will recur itself
     */
    public int kthSmallestElement(int lower, int upper, int k) {
        
        // index of the lower partition value
        int idxPartition = partition(lower, upper);
        
        // indicated that the partition/pivot is the median index
        if(idxPartition == k) {
           
            // returns the median value
            return a[idxPartition];
            
          // indicated that the lower half contains the median index
        } else if(idxPartition > k) {
            
            // runs with the new upper bound set to the previous pivot
            return kthSmallestElement(0, idxPartition-1, k);
            
            // indicates that the upper half contains the median index
        } else {
            
            // runs with the new lower bound
            return kthSmallestElement(idxPartition+1, n-1, k);
        }
    }
    
    /**
     * Copied directly from this weeks example 14 for advanced sorting techniques
     * @param left
     * @param right
     * @return 
     */
    
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
}