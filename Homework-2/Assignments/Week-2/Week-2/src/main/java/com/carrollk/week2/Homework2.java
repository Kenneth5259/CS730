/**
 *
 * @author: Kenneth Carroll
 * date: 08-24-2020
 * 
 * testing updated: 08-29-2020
 */

package com.carrollk.week2;

import java.util.Scanner;
public class Homework2 {
    
    /**
     * 
     * The main class serves as a user interface for processing strings.
     * There is also the ability to call all existing test cases, which
     * allowed for quicker testing of each class function
     */
    public static void main(String args[]) {
        
        // initialize a scanner object
        Scanner input = new Scanner(System.in);
        
        // menu control
        boolean loop = true;
        
        // empty string to handle first input if 2-4 selected
        String str = "";
        
        // initialize a string processor object
        StringProcessor processor = new StringProcessor();
        
        // loop until exit condition in switch statment
        while(loop) {
            
            // generate the menu
            System.out.println("Welcome to the String Processor Application!");
            System.out.println("The Current String is: " + str);
            System.out.println("Please select a number below:");
            System.out.println("\t1. Input a new string");
            System.out.println("\t2. Process the string");
            System.out.println("\t3. Check if the string is a palindrome");
            System.out.println("\t4. Check if the string is majority 1s or 0s");
            System.out.println("\t5. Run existing test cases for the application");
            System.out.println("\tAny other input to exit");
            
            // handle user input
            String s = input.nextLine();
            
            // switch over input
            switch(s) {
                
                // handle new user input
                case "1":
                    System.out.println("Enter string followed by enter: ");
                    str = input.nextLine();
                    break;
                    
                // process the string within Problem 1 constraints
                case "2":
                    System.out.println("The unprocessed string was:\t" + str);
                    str = processor.processStringInput(str);
                    System.out.println("The processed string is:\t" + str);
                    break;
                    
                // check if the current string is a palindrome
                case "3":
                    
                    // convert the boolean to a readable response to user
                    if(processor.isPalindrome(str)) {
                        System.out.println(str + " is a palindrome");
                    } else {
                        System.out.println(str + " is not a palindrome");
                    }
                    break;
                
                // check for 0/1 distribution
                case "4":
                    
                    // get the return value
                    int value = processor.checkZeroOne(str);
                    
                    // return a readable response to the user 
                    if(value > 0) {
                        System.out.println(str + " is majority 1s");
                    } else if (value < 0) {
                        System.out.println(str + " is majority 0s");
                    } else {
                        System.out.println(str + "has an even distribution of 1s and 0s");
                    }
                    break;
                    
                // quick execution of test cases found in the other class method
                case "5":
                    executeTestCases();
                    break;
                
                // exit the loop
                default:
                    loop = false;
                    break;
            }
            
            // add new line to increase readability
            System.out.println();
        }
        
        
    }
    public static void executeTestCases() {
        // instantiate a new StringProcessor object
        StringProcessor processor = new StringProcessor();
        int failCount = 0;
        
        
        /**************** Problem 1 Testing ****************/
        
        // array of test string inputs for problem 1
        String[] testProcessor = {
            "Testing Stringln<< Iteration",
            "Bes<habb<<vior  <Test",
            "another test123<<<",
            "<<<<",
            "Dasc<<ta Strwfn<<<uctuw<res",
            "levtr<<eabc<<<l",
            "a111f0g11t0g10m"
        };
        
        // array of expected results
        String[] processorExpectedResults = {
            "Testing String Iteration",
            "Behavior Test",
            "another test",
            "",
            "Data Structures",
            "level",
            "a111f0g11t0g10m"
        };
        
        int testCount = testProcessor.length;
        
        // testing loop for the input strings
        for(int i = 0; i < testCount; i++) {
            
            // comparison for output to expected results
            if(!processorExpectedResults[i].equals(processor.processStringInput(testProcessor[i]))) {
                
                // indicates the failed test
                System.out.println(testProcessor[i] +" is not behaving as expected");
                
                // tracks failed tests
                failCount++;
            }
        }
        
        // output for failed tests
        System.out.println("Process String Tests:\t" + testCount + "\tFailed: " + failCount);
        
        /**************** Problem 2 Testing ****************/
        
        // same test structure for palindrome function
        String[] testPalindrome = {
            "tacocat", 
            "hannah", 
            "taccat", 
            "doggo", 
            "kenneth",
            "",
            "Dasc<<ta Strwfn<<<uctuw<res",
            "levtr<<eabc<<<l",
            "a111f0g11t0g10m"
        };
        
        Boolean[] palindromeExpectedResults = {
            true, 
            true, 
            true, 
            false, 
            false,
            true, 
            false, 
            true,
            false
        };
        
        
        // reset fail count for the next problem
        failCount = 0;
        
        // update test count
        testCount = testPalindrome.length;
        
        for(int i = 0; i < testCount; i++) {
            if(palindromeExpectedResults[i] != processor.isPalindrome(testPalindrome[i])) {
                System.out.println(testPalindrome[i] +" is not behaving as expected");
                failCount++;
            }
        }
        System.out.println("Palindrome Tests:\t" + testCount  + "\tFailed: "+ failCount);
        
        
        /**************** Problem 3 Testing ****************/
        
        // test structure for check zero one method
        String[] testCheckZO = {
            "00001",
            "11110",
            "test001101",
            "001test11",
            "1test",
            "te0st",
            "",
            "Dasc<<ta Strwfn<<<uctuw<res",
            "levtr<<eabc<<<l",
            "a111f0g11t0g10m"
        };
        
        int[] checkZOExpectedResults = {
            -1,
            1,
            0,
            1,
            1,
            -1,
            0,
            0,
            0,
            1
        };
        
        testCount = testCheckZO.length;
        
        failCount = 0;
        
        for(int i = 0; i < testCount; i++) {
            if(checkZOExpectedResults[i] != processor.checkZeroOne(testCheckZO[i])) {
                System.out.println(checkZOExpectedResults[i] + " is not behaving as expected");
                failCount++;
            }
        }
        
        System.out.println("Check Zero One Tests:\t"+ testCount +"\tFailed: " + failCount);
        
    }
    
}


// String Processor Class
class StringProcessor {
    
    /**
     *
     * Public Method to process backspace
     * character return updated string
     *
     */ 
    public String processStringInput(String str) {
        
        int stringLength = str.length();
        
        // create a new character stack at the length of the current string
        StackCharacter stackC = new StackCharacter(stringLength);
        
        // iterate over the whole string
        for(int i = 0; i < stringLength; i++) {
            
            // read in the character
            char c =  str.charAt(i);
            
            // check backspace character
            if(c != '<') {
                
                // push non-backspace
                stackC.push(c);
            } else {
                
                // pop previous character
                stackC.pop();
                                                 
            }
        }
           
        // store return value
        String returnValue = "";
        
        // pop each element from stack until empty
        while(!stackC.isEmpty()) {
            
            // appends character to front to account for last-in-first-out
            returnValue = stackC.pop() + returnValue;
        }
        return returnValue;
        
    }
    
    /**
     * 
     * public method to detect palindrome
     * empty stack will indicate a palindrome
     * 
     */
    public Boolean isPalindrome(String str) {
        
         // handle any backspaces since extra characters affect palindromes
         str = processStringInput(str);
   
         int stringLength = str.length();
         
         // create a new character stack with maximum size of string length
         StackCharacter stackC = new StackCharacter(stringLength);
         
         // store half value for looping purposes
         int half = stringLength / 2;
         
         // store first half in the stack
         for(int i = 0; i < half; i++) {
             stackC.push(str.charAt(i));
         }
         
         // omits the middle character in odd length strings, does nothing for even length strings
         half += (stringLength % 2);
         
         // pop matching characters
         for(int i = half; i < stringLength; i++) {
             if(stackC.pop() != str.charAt(i)) {
                 break;
             }
         }
         
         // stack should be empty if every character had a match and was subsiquently popped
         return stackC.isEmpty();
         
    }
    
    /**
     * 
     * public method for checking majority 0 or 1 characters
     * majority will always remain on the top of stack
     * equal distributions will leave the stack empty
     * 
     */
    public int checkZeroOne(String str) {
        
        // initialized variable outside of problem scope
        int value = -2;
        
        // create a new character stack with maximum size of string length
        StackCharacter stackC = new StackCharacter(str.length());
        
        // iterate over entire string
        for(int i = 0; i < str.length(); i++) {
            
            // store current character in temp variable
            char c = str.charAt(i);
            
            // ignore any non 1/0 character
            if(c == '0' || c == '1') {
                
               // handle empty stack
               if(stackC.isEmpty()) {
                   stackC.push(c);
                   
               // check if current character is in the stack
               } else if(stackC.peek() == c) {
                   stackC.push(c);
                   
               // otherwise pop
               } else {
                   stackC.pop();
               }
            }
        }
        
        // if empty, then values were even in the array
        if(stackC.isEmpty()) {
            value = 0;
        
        // else most popular character will be at top of stack
        } else if (stackC.peek() == '1') {
            value = 1;
        } else {
            value = -1;
        }
    
        return value;
    }
    
}

/**
 * 
 * create a stack of characters, prefer StackX naming where X is verbose
 * data type. Templating is valid for reusability, but for the scope of 
 * the assignment was implemented for only characters
 * 
 */ 
class StackCharacter {
    
    private int top;
    private int maxSize;
    private char[] stackArray; 
    
    // parameterized constructor
    public StackCharacter(int n) {
        top = -1;
        maxSize = n;
        stackArray =  new char[maxSize];
    }
    
    // stack character push
    public void push(char c) {
        stackArray[++top] = c;
    }
    
    // generic stack pop
    public char pop() {
        if(isEmpty()) {
            System.out.println("Pop Operation Failed, Stack is Empty");
            
            // Return ASCII NUL
            return 0;
        }
        return stackArray[top--];
    }
    
    //generic stack peek
    public char peek() {
        return stackArray[top];
    }
    
    //generic stack empty check
    public boolean isEmpty() {
        return top == -1;
    }
}
