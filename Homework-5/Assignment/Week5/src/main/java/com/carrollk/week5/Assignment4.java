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


public class Assignment4 {
    public static void main(String[] args) {
        unsortedArr arr = new unsortedArr(1000);
        
        for(int i = 1; i <= 10000; i++) {
            System.out.print("Test Case " + i + ": ");
            arr.generate();
            if(arr.recursiveMedian() == arr.sortedMedian()) {
                System.out.print("pass");
            } else {
                System.out.print("fail");
            }
            System.out.println();
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
    
    
    // method to sort then return median. Used for testing
    public int sortedMedian() {
        if(n < 1) {
            return -1;
        }
        
        int idxMedian = (n/2) ;
        
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
    
    public int recursiveMedian() {
        
        // list generation can create an empty list, this handles it
        if(n < 1) {
            return -1;
        }
        
        // run helper function, initial lower and upper bounds at full length
        return recursiveMedian(0, n-1);
    }
    
    /**
     * 
     * Recursive Median returns the median value of a list without a full sort
     * This is achieved by partitioning the array and subsequent subarrays until
     * a pivot is placed at the index of the median if the array was sorted
     * 
     * 
     * @param lower defines the lower bound, and will be placed at pivot of partition
     * @param upper defines the upper bound 
     * @return will only return the median value or will recur itself
     */
    public int recursiveMedian(int lower, int upper) {
        
        // index of the eventual median
        int idxMedian = n/2;
        
        // index of the lower partition value
        int idxPartition = partition(lower, upper);
        
        // indicated that the partition/pivot is the median index
        if(idxPartition == idxMedian) {
           
            // returns the median value
            return a[idxPartition];
            
          // indicated that the lower half contains the median index
        } else if(idxPartition > idxMedian) {
            
            // runs with the new upper bound set to the previous pivot
            return recursiveMedian(0, idxPartition-1);
            
            // indicates that the upper half contains the median index
        } else {
            
            //runs with the new lower bound
            return recursiveMedian(idxPartition+1, n-1);
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
}