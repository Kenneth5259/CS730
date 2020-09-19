/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example05stack;

/**
 *
 * @author Yang
 */

import java.util.Scanner;

public class Example05Stack {

    public static void main(String [] args)
    {
        reverseSequence();  // use stack to help reverse a sequence of data
        
        checkBrackets();    // use stack to help parse string and check delimiters
        
        postfixDemo();      // use stack to help convert from infix to postfix, and evaluate postfix expression
    
    }
    
    public static void reverseSequence()
    {
        int stackSize = 10;
        StackArr<Integer> stack = new StackArr<>(stackSize);    // create a stack with size 10

        // push 0, 1, 2, ,3, ..., 9 into stack
        for (int i = 0; i < 10; i ++)
            stack.push(i);

        // pop all items out of stack and print them
        while (!stack.isEmpty())
            System.out.print(stack.pop() + "  ");            
        System.out.println();
    }
    
    public static void checkBrackets()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a string with or without brackets: ");
        String str = input.next();
        
        BracketChecker bc = new BracketChecker();
        if (bc.isLegal(str))
            System.out.println(str + " is legal.");
        else
            System.out.println(str + " is illegal.");
    }
    
    public static void postfixDemo()
    {
        PostfixExpression postfix = new PostfixExpression();
        
        Scanner input = new Scanner(System.in);
        int option;
        do
        {
            System.out.println("\nSelect from: ");
            System.out.println("1. Read an expression in infix notation");
            System.out.println("2. Convert infix to postfix");
            System.out.println("3. Evaluate the postfix expression");
            System.out.println("0. Exit");
            
            option = input.nextInt();
            switch(option)
            {
                case 1:
                    System.out.print("Enter an infix expression: ");
                    String e = input.next();
                    postfix.setByInfix(e);
                    System.out.println("The entered infix expresion is: " + e);
                    break;
                case 2:
                    System.out.println("The corresponding postfix expresion is: "
                            + postfix.getPostfix());
                    break;
                case 3:
                    System.out.println(postfix.getPostfix() + " = " + postfix.evaluate());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);
        
        System.out.println("Thanks for using my program.");
    }
}

/**
 * 
 * Class that helps parse a string and determine if delimiters are used correctly
 */
class BracketChecker
{
    /**
     * 
     * Return true if the given string (str) uses brackets correctly 
     */
    public boolean isLegal(String str)
    {
        StackArr<Character> brackets = new StackArr<>(100);                 // create a stack with size 100

        for (int i = 0; i < str.length(); i++)                              // scan the string from left toward right
        {
            Character cur = str.charAt(i);  
            
            if (cur.equals('(') || cur.equals('{') || cur.equals('['))      // push openning brackets into stack
                brackets.push(cur);
            else
            {
                if (cur.equals(')') || cur.equals('}') ||cur.equals(']'))   // when reaching a closing bracket
                {
                    if (brackets.isEmpty())                                 // no openning bracket in the stack        
                        return false;
                    else                                                    
                    {
                        Character topItem = brackets.pop();                 // pop an opennig bracket from the stack
                        if(!isMatched(topItem, cur))                        // openning bracket does not match with the closing bracket 
                            return false;
                    }
                }
            }
        }

        return brackets.isEmpty();                                          // there are still some openning brackets left over in stack
    }
    
    /**
     * 
     * Return true if the delimiters a and b match 
     */
    private boolean isMatched(Character a, Character b)
    {
        return ((a.equals('(') && b.equals(')')) ||
                (a.equals('[') && b.equals(']')) ||
                (a.equals('{') && b.equals('}')));
    }
}

/**
 * 
 * Class that handles postfix expressions 
 * Assume: operands are single-digit numbers
 */
class PostfixExpression
{
    private String exp;                     // postfix expression

    /**
     * 
     * Constructor: create a postfix expression e 
     */
    public PostfixExpression(String e)
    {
        exp = e;
    }
    
    /**
     * 
     * Constructor: create an empty postfix expression
     */
    public PostfixExpression()
    {
        this("");
    }

    /**
     * 
     * Set a postfix expression e
     */
    public void setPostfix(String e)
    {
        exp = e;
    }
    
    /**
     * 
     * Return the postfix expression 
     */
    public String getPostfix()
    {
        return exp;
    }

    /**
     * 
     * Convert from the given infix expression to the equivalent postfix expression
     */
    public void setByInfix(String infix)
    {
        StackArr<Character> stack = new StackArr<>();
        exp = "";

        for (int i = 0; i < infix.length(); i++)        // scan the infix expression from left to right
        {
            char t = infix.charAt(i);                   // current character we are looking at in the infix expression
            
            switch(t)
            {
                case '(':
                    stack.push(t);
                    break;
                case ')':
                    while(!stack.isEmpty())
                    {
                        char temp = stack.pop();
                        if (temp == '(')   
                            break;
                        exp = exp + temp;
                    }  
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && !hasHigherPrecedence(t, stack.peekTop()))
                        exp = exp + stack.pop();

                    stack.push(t);
                    break;
                default:                                   // it's an operatand
                    exp = exp + t;
            }
        }

        while (!stack.isEmpty())                           // there are still some operators in stack
            exp = exp + stack.pop();
    }
        
    /**
     * 
     * Return true if operator a has higher precedence than operator b
     */
    private boolean hasHigherPrecedence(char a, char b)
    {
        if (b == '(')
            return true;
        else if ((a == '*' || a == '/') && (b == '+' || b == '-'))
            return true;
        else
            return false;
    }
    
    /**
     * 
     * Evaluation postfix expression 
     */
    public int evaluate()
    {
        StackArr<Integer> stack = new StackArr<>();

        for (int i = 0; i < exp.length(); i++)
        {
            char t = exp.charAt(i);
            if (t == '+' || t == '-' || t == '*' || t == '/')       // it's an operator
            {
                // pop two operands from stack
                int s2 = stack.pop();           
                int s1 = stack.pop();
                
                // do calculation
                int value;
                switch(t)
                {
                    case '+':   
                        value = s1 + s2;    
                        break;
                    case '-':   
                        value = s1 - s2;    
                        break;
                    case '*':   
                        value = s1 * s2;    
                        break;
                    default:   
                        value = s1 / s2;    
                }                    
                stack.push(value);
            }
            else                                                    // it's an operand
                stack.push(Character.getNumericValue(t));            
        }
        return stack.pop();                                 
    }
}

/**
 * 
 * Stacks implemented by using arrays whose element type is E 
 */
class StackArr<E>
{
    private E a[];                  // array used to store the stack         
    private int top;                // index of the stack top 

    /**
     * 
     * Constructor: create an empty stack with the specified size
     */
    public StackArr(int size)
    {
        a = (E[]) new Object[size];
        top = -1;                   // empty stack
    }

    /**
     * 
     * Constructor: create an empty stack with size 5
     */
    public StackArr()
    {        
        this(5);                    // create a stack that can store at most 5 elements
    }

    /**
     * 
     * Return true if the stack is already full 
     */
    public boolean isFull()
    {
        return (top == a.length - 1);
    }

    /**
     * 
     * Return true if the stack is empty
     */
    public boolean isEmpty()
    {
        return (top == -1);
    }

    /**
     * 
     * Put a given on top of the stack
     * Return true if the push operation completes successfully
     */
    public boolean push(E item)
    {
        if (isFull())   
        {
            System.out.println("PUSH (" + item + ") failed!");
            return false;
        }
        
        top += 1;
        a[top] = item;
        
        return true;
    }

    /**
     * 
     * Take item from top of the stack 
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public E pop()
    {
        if (isEmpty())
        {
            System.out.println("POP failed!");
            return null;
        }

        E item = a[top];
        top -= 1;
        return item;
    }

    /**
     * 
     * Peek at the top of the stack
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public E peekTop()
    {
        if (isEmpty())
        {
            System.out.println("PEEK failed!");
            return null;
        }
        
        E item = a[top];
        return item;
    }
}

