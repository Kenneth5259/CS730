/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example09recursion;

/**
 *
 * @author Yang
 */
public class Example09Recursion 
{
    public static void main(String[] args) 
    {        
        System.out.println("2^4 = " + MyRecursiveFunctions.pow(2, 4));
        System.out.println("2^1 = " + MyRecursiveFunctions.pow(2, 1));
        System.out.println();

        System.out.println("1+2+3+4 = " + MyRecursiveFunctions.series(4));
        System.out.println("1+2+3+ ... +10 = " + MyRecursiveFunctions.series(10));
        System.out.println();

        System.out.println("7-th Fibonacci number = " + MyRecursiveFunctions.fibonacci(7));
        System.out.println("2-th Fibonacci number = " + MyRecursiveFunctions.fibonacci(2));
        System.out.println();

        System.out.println("GCD(30,75) = " + MyRecursiveFunctions.euclidGCD(30, 75));
        System.out.println("GCD(60,36) = " + MyRecursiveFunctions.euclidGCD(60, 36));
        System.out.println("GCD(50,0) = " + MyRecursiveFunctions.euclidGCD(50, 0));
        System.out.println("GCD(0,50) = " + MyRecursiveFunctions.euclidGCD(0, 50));
        System.out.println();

        // test practice problems here ...
        System.out.println("Mutliply 5 * 4 = " + MyRecursiveFunctions.multiply(5, 4));
        System.out.println("Mutliply 2 * 0 = " + MyRecursiveFunctions.multiply(2, 0));
        System.out.println("Mutliply 15 * 3 = " + MyRecursiveFunctions.multiply(3, 15));
        System.out.println();
        
        System.out.println("N = 5 is: " + MyRecursiveFunctions.recFunction(5));
        System.out.println("N = 10 is: " + MyRecursiveFunctions.recFunction(10));
        System.out.println("N = 100 is: " + MyRecursiveFunctions.recFunction(100));
        System.out.println();
        
        
    }
}

/**
 * 
 * Recursive functions to calculate some math problems
 */
class MyRecursiveFunctions
{
    /**
     * 
     * Compute and return x^n, where x is a non-zero real number and n is a non-negative integer 
     */
    static double pow(double x, int n)
    {
        // base case (stopping case): x^0=1
        if (n == 0)     
            return 1;
        
        // general case (recursive case): x^n = x * x^(n-1), when n>0
        return x * pow(x, n - 1);
    } 
    
    /**
     * 
     * Add the first n natural numbers together (1+2+3+...+n) and return the result, 
     * where n is an integer no less than 1
     */
    static int series(int n)
    {
        // base case (stopping case): 1, when n == 1
        if (n == 1)     
            return 1;
        
        // general case (recursive case): 1+2+3+...+(n-1)+n = (1+2+3+...+n-1) + n, when n>1
        return n + series(n - 1); 
    }
    
    
    /**
     * 
     * Calculate and return the n-th item in Fibonacci sequence 1, 1, 2 3, 5, 8, 13, 21, and so on,
     * where n is a positive integer
     */
    static int fibonacci(int n)
    {
        // base case (stopping case): 1, when n == 1 or 2
        if (n <= 2)
            return 1;
        
        // general case (recursive case): when n > 2
        return (fibonacci(n - 2) + fibonacci(n - 1));
    }
    
    /**
     * 
     * Compute the greatest common divisor (GCD) of n1 and n2 using Euclid's algorithm, 
     * where n1 and n2 are non-negative integers
     */
    static int euclidGCD(int n1, int n2)
    {
        // base case (stopping case): 1, GCD(n1, n2) = n1, when n2 == 0
        if (n2 == 0)
            return n1;
        
        // general case (recursive case): when n2 > 0
        return euclidGCD(n2, n1 % n2);
    }
    
    /**
     * 
     * Practice problem 1:
     * Write a recursive function to multiple a with b, 
     * where b is a non-negative integer
     */
    static int multiply(int a, int b) {
        if(b == 0)
            return 0;
        return a + multiply(a, --b);
    }
    /**
     * 
     * Practice problem 2:
     * Write a recursive function to calculate 1/3+2/5+3/7+ .... + n/(2n+1), 
     * where n is a positive integer
     */
    
    static double recFunction(int n) {
    double nn = (double)n;
    if(nn == 0) {
        return 0;
    }
    return nn/(2*nn+1) + recFunction(--n);
    }
    
}


