/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example11recursion;

import java.lang.Character;

/**
 *
 * @author yangm
 */
public class Example11Recursion 
{
    public static void main(String[] args) 
    {
        String s = "abcde";
        System.out.println("Reverse " + s + ": " + MyRecursiveFunctions.reverse(s));
        
        s = "Welcome";
        char c = 'e';
        /*
        System.out.println("Remove " + c + " from : " + s + ": " + MyRecursiveFunctions.removeChar(s, c));
        
        System.out.println("abccba is a palindrome: " + MyRecursiveFunctions.isPalindrome1("abccba"));
        System.out.println("abcfa is a palindrome: " + MyRecursiveFunctions.isPalindrome1("abcfa"));
        System.out.println("abccba is a palindrome: " + MyRecursiveFunctions.isPalindrome2("abccba"));
        System.out.println("abcfa is a palindrome: " + MyRecursiveFunctions.isPalindrome2("abcfa"));
        
        System.out.println("Sum 1234: " + MyRecursiveFunctions.sumDigit(1234));
        System.out.println("Sum 5857632: " + MyRecursiveFunctions.sumDigit(5857632));
        System.out.println("Count CharAt: " + MyRecursiveFunctions.countUppercase("CharAt"));
        
        System.out.println("Count AnOtHeR TeStStRiNg: " + MyRecursiveFunctions.countUppercase("AnOtHeR TeStStRiNg"));
        */
        System.out.println("Number of a's in aabaa: " + MyRecursiveFunctions.count("aaaa", 'a'));
        System.out.println("Number of b's in aabaa: " + MyRecursiveFunctions.count("aabaa", 'b'));
        System.out.println("Number of a's in : " + MyRecursiveFunctions.count("", 'a'));
        
        System.out.println("Shoudl be False:" + MyRecursiveFunctions.isPostfix("“abc/*e”"));
        System.out.println("Should be True:" + MyRecursiveFunctions.isPostfix("abc/*e+"));
        System.out.println("Should be false:" + MyRecursiveFunctions.isPostfix("abc/*e+/"));
    }
}

class MyRecursiveFunctions
{   
    /**
     * 
     * Reverse the order of characters in a given string s  
     */
    static String reverse(String s)
    {
        if (s.length() <= 1)
            return s;
        
        return reverse(s.substring(1)) + s.charAt(0);
    }
    
    /**
     * 
     * Remove a specified character c from a given string s 
     */
    static String removeChar(String s, char c)
    {
        if (s.equals(""))
            return s;

        if (s.charAt(0) == c)
            return removeChar(s.substring(1), c);
        else
            return s.charAt(0) + removeChar(s.substring(1, s.length()), c);
    }
    
    /**
     * 
     * Check if a given string is a palindrome or not 
     * (Without a recursive helper function: create a new string for every recursive call)
     */
    static boolean isPalindrome1(String s)
    {
        int n = s.length();

        if (n <= 1)     
            return true;

        return ((s.charAt(0) == s.charAt(n - 1)) && isPalindrome1(s.substring(1, n - 1)));
    }
    
    /**
     * 
     * Check if a given string is a palindrome or not 
     * (With a recursive helper function)
     */
    static boolean isPalindrome2(String s)
    {
        return isPalindrome2(s, 0, s.length()-1);
    }

    // recursive helper method
    static boolean isPalindrome2(String s, int low, int high)
    {
        if (high <= low)    
            return true;

        return ((s.charAt(low) == s.charAt(high)) && isPalindrome2(s, low+1, high-1));
    }
    
    /**
     * 
     * Practice problem 1:
     * Write a recursive function that computes the sum of the digits in the given integer n
     */
    
    static int sumDigit(int n) {
        
        // convert int to string
        String nString = String.valueOf(n);
        
        // pull value of substring for single digit
        int digit = Integer.parseInt(String.valueOf(nString.charAt(0)));
        
        nString = nString.substring(1);
        
        if(nString.length() > 0) {
            return digit + sumDigit(Integer.parseInt(nString));
        } else {
            return digit;
        }
        
        
    }
    
    
    /**
     * 
     * Practice problem 2:
     * Implement a recursive approach to counting the occurrences of uppercase letters in a given string s
     * You should define the following two functions, the second of which is a recursive helper function
     *      int countUppercase(String s)
     *      int countUpppercase(String s, int high)
     */
    static int countUppercase(String s) {
        return countUppercase(s, 0);
    }
    
    static int countUppercase(String s, int high) {
        int val = 0;
        if(high > s.length() - 1) {
            return val;
        }
        char c = s.charAt(high);
        if(Character.isUpperCase(c)) {
            val = 1;
        }
        
        return val + countUppercase(s, high+1);
    }
    
    static int count(String s, char c) {
        if(s.length() == 0) {
            return 0;
        }
        int isChar = 0;
        
        if(s.charAt(0) == c) {
            isChar = 1;
        }
        return isChar + count(s.substring(1), c);
    }
    
    /*static boolean isPostfix(String e) {
        
        // check if last character is not operand
        switch (e.charAt(e.length() - 1)) {
            case '/':
            case '*':
            case '^':
            case '+':
            case '-':
                break;
            
            default:
                return false;
        }
        
        // check if there is only a character remaining
        if(e.length() == 1) {
            return true;
        }
        
        // initialize operator and character holder at unused value
        char o = '_', c = '_';
        
        // index tracking
        int index = 0;
        // loop until a match of each has been found within range of string length
        while((o == '_' || c== '_') && index != e.length()) {
            
        }
        
        return false == false;
    }*/
    
    boolean isPostfix(String e) {
        
        // cannot have even expressions under current constraints
        if(e.length() % 2 != 1) {
            return false;
        }
        char c = e.charAt(0);
        
        // ensures that the last remaining character is not an operator
        if(e.length() == 1) {
            if(c == '*' || c == '/' || c == '+' || c == '-') {
                return false;
            } else {
                return true;
            }
        }
        
        // makes sure loop doesn't go out of bounds of expression
        int index = 0;
        while(index < e.length()) {
            
            // pull character
            c = e.charAt(index);
            
            // check for operand, removed first operand and previous character
            if(c == '*' || c == '/' || c == '+' || c == '-') {
                String new_e ="";
                
                // adds from beginning to character before operand
                new_e += e.substring(0, index-1);
                
                // adds from character after operand to end
                new_e += e.substring(index+1, e.length());
                
                // recursive with 1 less charcter and operator
                return isPostfix(new_e);
                
            }
            index++;
        }
        
        // ensures there is only 1 more non-operator than operator
        return false;
    }
}


