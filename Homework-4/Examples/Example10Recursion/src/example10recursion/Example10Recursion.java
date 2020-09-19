/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example10recursion;

/**
 *
 * @author yangm
 */

public class Example10Recursion 
{
    public static void main(String[] args) 
    {       
        MyRecursiveFunctions.printTriangle1(6);

        System.out.println();
        MyRecursiveFunctions.printTriangle2(6);
        System.out.println();
        
        MyRecursiveFunctions.printNumbers(4);
    }
}

class MyRecursiveFunctions
{
    /**
     * 
     * Print a triangle with n left-aligned rows  
     * For example, when n is 6, the triangle will look like as follows:
            ******
            *****
            ****
            ***
            **
            *
    */
    static void printTriangle1(int n)
    {
        if (n>0)
        {
            for (int i=0; i<n; i++)   
                System.out.print("*");
            
            System.out.println();

            printTriangle1(n-1);
        }
    }
    
    /**
     * 
     * Print a triangle with n right-aligned rows 
     * For example, when n is 6, the triangle will look like as follows:     
        ******
         *****
          ****
           ***
            **
             *
     */ 
    static void printTriangle2(int n)
    {
        printTriangle2(n, 0);
    }
    
    /**
     * 
     * Helper function of printTriangle2(int n)
     * Print a triangle with n right-aligned rows and s spaces at the beginning of its 1st row 
     */
    static void printTriangle2(int n, int s)
    {
        if (n>0)
        {
            for (int i=0; i<s; i++)	
                System.out.print(" ");
            
            for (int i=0; i<n; i++)	
                System.out.print("*");
            
            System.out.println();

            printTriangle2(n-1, s+1);
        }
    }
    
    /** 
     * 
     * Practice problem 3:
     * The function will print nX2 lines, each of which contains a number
     * The first n lines print numbers from n down to 1, 
     * with each line indented two more spaces than the previous line
     * The last n lines print numbers from 1 up to n, 
     * with each line indented two fewer spaces than the previous line
     * 
     * For example, when n is 4, the function will print
             4
               3
                 2
                   1
                   1
                 2
               3
             4
     */
    
    static void printNX2(int n, String s) {
        if(n == 0)
            return;
        System.out.print(s + n + "\n");
        printNX2(n-1, s+"  ");
        System.out.print(s + n + "\n");
        
 
    }
    
    static void printNumbers(int n) {
        if(n <= 0) {
            return;
        }
        for(int i = 0; i < n; i++) {
            System.out.print("  ");
        }
        System.out.print(n + "\n");
        printNumbers(n-1);
        for(int i = 0; i < n; i++) {
            System.out.print("  ");
        }
        System.out.print(n + "\n");
    }
}


