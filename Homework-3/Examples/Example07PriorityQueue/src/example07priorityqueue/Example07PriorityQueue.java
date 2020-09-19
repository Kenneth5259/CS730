/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example07priorityqueue;

/**
 *
 * @author Yang
 */
public class Example07PriorityQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        PriorityQueueArr pq = new PriorityQueueArr(5);
        
        // insert five items
        pq.enqueue(30);
        pq.enqueue(50);
        pq.enqueue(10);
        pq.enqueue(40);
        pq.enqueue(20);
        
        System.out.println("Item at the front: " + pq.peekFront());

        System.out.println("Remove: " + pq.dequeue() + " " + pq.dequeue() + " " + pq.dequeue());
        
        // insert three items
        pq.enqueue(45);
        pq.enqueue(15);
        pq.enqueue(25);

        // remove all items from the priority queue
        System.out.print("Remove: ");
        while(!pq.isEmpty())
        {
            int t = pq.dequeue();
            System.out.print(t + " ");
        }
        System.out.println();
    }
}

/**
 * 
 * Priority queues implemented by simple arrays  
 * Assume:
 * - All elements in the priority queue will be non-negative integers
 * - The item with the lowest key is always at the front 
 */
class PriorityQueueArr
{
    private int a[];
    private int front;

    /**
     * 
     * Constructor: create an empty queue with the specified size 
     */
    public PriorityQueueArr(int size)
    {
        a = new int[size];          // can store at most (size) number of elements
        front = -1;                 // empty queue: no item in the queue yet
    }

    /**
     * 
     * Return true if the queue is full 
     */
    public boolean isFull()
    {
        return (front == a.length - 1);
    }

    /**
     * 
     * Return true if the queue is empty
     */
    public boolean isEmpty()
    {
        return (front == -1);
    }

    /**
     * 
     * Return true if the enqueue operation completes successfully
     */
    public boolean enqueue(int item)
    {
        if (!isFull())
        {
            int i;
            for (i = front; i >= 0; i --)
            {
                if (item > a[i])
                {
                    a[i + 1] = a[i];
                }
                else
                    break;
            }
            a[i + 1] = item;
            front ++;
                        
            return true;
        }
        else
        {
            System.out.println("Queue is full. Enqueue failed.");
            return false;
        }
    }

    /**
     * 
     * Take item from the front
     * Return the item if the operation completes successfully
     *        -1 to indicate the operation failed
     */
    public int dequeue()
    {
        if (!isEmpty())
        {
            int item = a[front];
            front --;
            return item;
        }
        else
        {
            System.out.println("Queue is empty. Dequeue failed.");
            return -1;
        }
    }

    /**
     * 
     * Peek at the item at the front 
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public int peekFront()
    {
        if (!isEmpty())
            return a[front];
        else
        {
            System.out.println("Peek front failed! ");
            return -1;
        }
    }

    /**
     * 
     * Return the number of items that are currently in the queue 
     */
    public int queueCount()
    {
        return front + 1;

    }
}


