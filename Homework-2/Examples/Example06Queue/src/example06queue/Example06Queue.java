/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example06queue;

/**
 *
 * @author Yang
 */
import java.util.Random;
import java.util.Scanner;

public class Example06Queue 
{
    public static void main(String[] args) {
        queueOperationsDemo();              // demonstrate basic operations of queues
        
        trafficSimulationDemo();            // use a queue to similate the traffic at a T-intersection
        
        System.out.println(hasEqualHalves("abcabc"));
        System.out.println(hasEqualHalves("abcab"));
    }
    
    public static void queueOperationsDemo()
    {
        QueueArr<Integer> q = new QueueArr<>(5);
        
        // insert 4 items: 10, 20, 30, 40
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        
        // remove 3 items: 10, 20, 30
        System.out.print("Removed: ");
        for (int i=1; i <= 3; i++)
        {
            int t = q.dequeue();
            System.out.print(t + " ");
        }
        System.out.println();

        // insert 4 items: 50, 60, 70, 80
        q.enqueue(50);          // after 50 is inserted, wrap around!
        q.enqueue(60);          
        q.enqueue(70);
        q.enqueue(80);
        
        // access the items at front and rear ends of the queue
        System.out.println("Front item: " + q.peekFront());
        System.out.println("Rear item: " + q.peekRear());

        // remove all items from the queue and display them
        while(!q.isEmpty())
        {
            int t = q.dequeue();
            System.out.print(t + " ");
        }
        System.out.println();
    }
    
    public static void trafficSimulationDemo()
    {
        TrafficSimulator simulator = new TrafficSimulator(1000);
        simulator.simulateTraffic();
    }
    
    // Using QueueArr from Example06 and utilizing dequeue function
    public static boolean hasEqualHalves(String s) {
        
        // defines a known half for the strings length
        int halfSize = s.length() / 2;
        
        // sets queue at size half
        QueueArr<Character> q = new QueueArr<>(halfSize);
        
        // enqueue the first half of the string
        for(int i = 0; i< halfSize; i++) {
            q.enqueue(s.charAt(i));
        }
        
        // dequeue first half for comparison
        for(int i = halfSize; i < s.length(); i++) {
            
            // check that last half matches on a per character basis
            if(q.dequeue() != s.charAt(i)) {
                
                // at least 1 character conflicts
                return false;
            }
        }
        
        // entire string has been compared without being flagged false
        return true;
    }
}

/**
 * 
 * Class used to simulate the traffic at a T-intersection
 * Assume: 
 * - The top part of T is two way, with one lane in each direction
 * - The bottom part of T is one-lane and one-way toward the intersection
 */
class TrafficSimulator
{
    private int duration;           // duration of the simulation
    
    /**
     * 
     * Constructor: specify the duration of simulation: number of time units (e.g., seconds)
     */
    public TrafficSimulator(int num)
    {
        duration = num;
    }
    
    /**
     * With the specified probability, 
     * a car arrives at the intersection from the bottom part of T
     * 
     */
    private void hasCarArrivingBottomT(QueueArr q, float probability)
    {
        Random rand = new Random();             // rand.nextFloat() generates a float number in [0, 1) randomly
        
        if (rand.nextFloat() <= probability)    // a car arrives from the bottom part of T
        {
            char car;
            
            if (rand.nextFloat() <= 0.5)
                car = 'l';                      // with probability 50%, the car will turn left 
            else
                car = 'r';                      // with probability 50%, the car will turn right

            q.enqueue(car);                     // enqueue the car
        }
    }
    
    /**
     * 
     * Return true if there is a car currently driving through the intersection 
     * along the top part of T, with the specified probability
     */
    private  boolean hasCarDrivingTopT(float probability)
    {
        Random rand = new Random();
        return (rand.nextFloat() <= probability);    
    }

    /**
     * 
     * Simulate the cars waiting on the bottom part of T
     */
    public void simulateTraffic()
    {
        final float probCarArrives = 0.3f;  // probability that there is a car arriving at the intersection from the bottom part of T
        final float probLeft2Right = 0.65f; // probability that there is a car driving through the intersection from left to right on the top part of T 
        final float probRight2Left= 0.6f;   // probability that there is a car driving through the intersection from right to left on the top part of T 
        
        QueueArr<Character> q = new QueueArr<>(duration);   // use a queue to simulate the cars waiting on the bottom of T to make turns
        
        for(int t = 1; t <= duration; t++)
        {
            char car;

            hasCarArrivingBottomT(q, probCarArrives);           // enqueue a car if it arrives the intersection from the bottom of T            
            System.out.println("Current number of cars waiting to make turns: " + q.queueCount());

            if (!q.isEmpty())                                   // there are still some cars waiting to turn onto the top of T
            {
                switch (q.peekFront())                          // check the front car in the queue
                {
                    case 'r':                                   // it will a right turn
                        if (!hasCarDrivingTopT(probLeft2Right)) // no car is coming from left on top of T
                        {
                            car = q.dequeue();                  // turn right and leave the intersection
                            System.out.println("A car is turning right...");
                        }
                        break;
                    case 'l':
                        if (!hasCarDrivingTopT(probLeft2Right) && !hasCarDrivingTopT(probRight2Left)) // no car in both directions on the top of T
                        {
                            car = q.dequeue();                  // turn left and leave the intersection
                            System.out.println("A car is turning left...");
                        }
                        break;
                }
            }
            System.out.println("Current number of cars waiting to make turns: " + q.queueCount());
        }

        System.out.println("Simulation ends. Number of cars still waiting to make turns: " + q.queueCount());
    }
}

/**
 * 
 * Queues implemented by arrays whose element type is E  
 */
class QueueArr<E>
{
    private E a[];
    private int front, rear;

    /**
     * 
     * Constructor: create an empty queue with the specified size 
     */
    public QueueArr(int size)
    {
        a = (E[]) new Object[size];         // can store at most (size) number of elements
        front = rear = -1;                  // empty queue: no item in the queue yet
    }

    /**
     * 
     * Return true if the queue is already full 
     */
    public boolean isFull()
    {
        return ((rear + 1) % a.length == front);
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
    public boolean enqueue(E item)
    {
        if (!isFull())
        {
            a[(rear+1) % a.length] = item;
            rear = (rear+1) % a.length;

            if (front == -1)    front = 0;
            
            return true;
        }
        else
        {
            System.out.println("Queue is full. Enqueue failed.");
            return false;
        }
    }

    /**
     * Take item from the front 
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public E dequeue()
    {
        if (!isEmpty())
        {
            boolean flag = false;        // more than one item in the queue
            if (front == rear)  flag = true;

            E item = a[front];
            front = (front + 1) % a.length;

            if (flag)      front = rear = -1;
            return item;
        }
        else
        {
            System.out.println("Queue is empty. Dequeue failed.");
            return null;
        }
    }

    /**
     * 
     * Peek at the item at the front 
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public E peekFront()
    {
        if (!isEmpty())
            return a[front];
        else
        {
            System.out.println("Peek front failed! ");
            return null;
        }
    }

    /**
     * 
     * Peek at the item at the rear 
     * Return the item if the operation completes successfully
     *        null to indicate the operation failed
     */
    public E peekRear()
    {
        if (!isEmpty())
            return a[rear];
        else
        {
            System.out.println("Peek rear failed");
            return null;
        }
    }

    /**
     * 
     * Return the number of items that are currently in the queue 
     */
    public int queueCount()
    {
        if (!isEmpty())
        {
            if (rear >= front)
                return rear - front + 1;
            else
                return rear + 1 + (a.length - front);
        }
        else
             return 0;

    }
}

