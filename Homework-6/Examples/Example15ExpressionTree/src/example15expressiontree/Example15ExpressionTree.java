/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example15expressiontree;

/**
 *
 * @author Yang
 */

import java.util.Scanner;

public class Example15ExpressionTree 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        ExpressionTree tree = new ExpressionTree();

            String exp;
            System.out.print("Enter an infix expression: ");
            exp = input.next();
            tree.build(exp);

            int option;
            do
            {
                System.out.println("\nChoose from: ");
                System.out.println("1: Prefix ");
                System.out.println("2: Infix ");
                System.out.println("3: Postfix ");
                System.out.println("Other to exit ");
                
                option = input.nextInt();
                if (tree.traverse(option) < 0)  break;
                System.out.println();
            } while (true);  
    }
}

/**
 * 
 * Node in an expression tree, assuming every operand in the algebraic expression has one single digit
 */
class TreeNode
{
    char data;                      // operator or operand
    TreeNode left, right;           // left child and right child

    TreeNode(char d)
    {
        data = d;
        left = right = null;
    }
}

/**
 * 
 * Expression tree for algebraic expression, assuming every operand has one single digit
 */
class ExpressionTree
{
    private TreeNode root;          // reference to the root of the tree

    /**
     * 
     * Constructor: create an empty tree
    */ 
    ExpressionTree()
    {
        root = null;
    }

    /**
     * 
     * Build an expression tree based on the given algebraic expression (specified by string exp)
     */
    void build(String exp)
    {
        root = build(exp, 0, exp.length()-1);
    }
    
    /**
     * 
     * Recursive helper function
     * Build an expression subtree based on the given subexpression (in string exp between indices left~right)
     * Return the reference of the subtree
     */
    TreeNode build(String exp, int left, int right)
    {
        if (left == right)                              // expression only contains an operand
            return (new TreeNode(exp.charAt(left)));

        TreeNode tn = null;                             // root of the subtree
        int loc = findAddSubtract(exp, left, right) ;   // try to find the right-most + or - that is outside the outer-most parentheses
        if(loc != -1)                                   // + or - is found
                tn = new TreeNode(exp.charAt(loc));     // create a node for + or -
        else                    
        {
            loc = findMulDiv(exp, left, right) ;        // try to find the right-most * or / that is outside the outer-most parentheses
            if(loc != -1)                               // * or / is found
                tn = new TreeNode(exp.charAt(loc));     // create a node for * or /
        }

        if (loc == -1)                                  // no +, -, * or / outside the outer-most parentheses
            return build(exp, left+1, right-1); 	// parentheses are eliminated
        else
        {
            tn.left = build(exp, left, loc-1);		// build the left subtree of the root
            tn.right = build(exp, loc+1, right);	// build the right subtree of the root
            return tn;
        }        
    }

    /**
     * 
     * Find the right-most + or - that is outside the outer-most parentheses, and return its index
     */
    int findAddSubtract(String exp, int left, int right)
    {
        int count = 0;                                  // 0: currently outside parentheses
        int loc = right;                                // scan the expression starting from the right end

        // scann the expression from right to left, and search for the first + or - outside parentheses
        while(loc >= left)
        {
            if(exp.charAt(loc) == ')')
                count++ ;
            else if(exp.charAt(loc) == '(')
                count-- ;
            else if(exp.charAt(loc) == '+' || exp.charAt(loc) == '-')
            {
                if (count == 0)                         // found a + or - outside parentheses
                    return loc; 
            }

            loc--;
        }
		
        return -1 ;		// not found
    }

    /**
     * 
     * Find the right-most * or / that is outside the outer-most parentheses, and return its index
     */
    int findMulDiv(String exp, int left, int right)
    {
        int count = 0;                                  // 0: currently outside parentheses
        int loc = right;                                // scan the expression starting from the right end

        while(loc >= left)
        {
            if(exp.charAt(loc) == ')')
                count++ ;
            else if(exp.charAt(loc) == '(')
                count-- ;
            else if(exp.charAt(loc) == '*' || exp.charAt(loc) == '/')
            {
                if (count == 0)		
                    return loc;
            }

            loc--;
        }
        return -1 ;
    }

    /**
     * 
     * Traverse the expression tree and generate prefix, infix or postfix expression
     * Parameter flag: 0 - prefix    1 - infix    2 - postfix
     */
    int traverse(int flag)	
    {
        switch(flag)
        {
            case 1:
                prefix(root);
                break;
            case 2:
                infix(root);
                break;
            case 3:
                postfix(root);
                break;
            default:
                flag = -1;
        }
        return flag;
    }

    /**
     * 
     * Traverse the expression tree and generate prefix expression 
     * Parameter tn: root of the subtree to be traversed
     */
    void prefix(TreeNode tn)
    {
        if (tn == null)
            return;
        
        System.out.print(tn.data + " ");
        prefix(tn.left);
        prefix(tn.right);
    }

    /**
     * 
     * Traverse the expression tree and generate postfix expression 
     * Parameter tn: root of the subtree to be traversed
     */
    void postfix(TreeNode tn)
    {
        if (tn == null)
            return;
        
        postfix(tn.left);
        postfix(tn.right);
        System.out.print(tn.data + " ");
    }
    
    /**
     * 
     * Traverse the expression tree and generate infix expression 
     * Parameter tn: root of the subtree to be traversed
     */
    void infix(TreeNode tn)
    {
        if (tn == null)
            return;
        
        if (tn.data != '+' && tn.data != '-' && tn.data != '*' && tn.data != '/')	// tn is an operend
            System.out.print(tn.data + " ");
        else
        {
            System.out.print("(");
            infix(tn.left);
            System.out.print(tn.data + " ");
            infix(tn.right);
            System.out.print(")");
        }
    }
    
    /**
     * 
     * Practice problem:
     * Write a function to evaluate the expression represented by the expression tree
     * Hint: borrow ideas from function infix(TreeNode tn)
     */
}
