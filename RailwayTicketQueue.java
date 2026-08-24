package DSA.Lab;

import java.util.Scanner;

public class RailwayTicketQueue {
    private String[] queue;
    private int front;
    private int rear;
    private int capacity;

    public RailwayTicketQueue(int size) {
        this.capacity = size;
        this.queue = new String[capacity];
        this.front = -1;
        this.rear = -1;
    }

    public void enqueue(String customerName) {
        if (rear == capacity - 1) {
            System.out.println("[Error] Counter is full! Cannot add customer: " + customerName);
            return;
        }
        if (front == -1) {
            front = 0;
        }
        queue[++rear] = customerName;
        System.out.println("[Joined] -> " + customerName + " joined the ticket queue.");
    }

    public void dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("[Alert] No customers in line. (Queue Underflow)");
            return;
        }

        System.out.println("[Served] <- " + queue[front] + " purchased a ticket and left.");
        front++;

        if (front > rear) {
            front = -1;
            rear = -1;
        }
    }

    public void peek() {
        if (front == -1 || front > rear) {
            System.out.println("[Status] The queue is currently empty.");
            return;
        }
        System.out.println("[Next in Line] -> " + queue[front]);
    }

    public void display() {
        if (front == -1 || front > rear) {
            System.out.println("[Queue] No one is waiting in the reservation line.");
            return;
        }
        System.out.println("\n--- Current Railway Reservation Queue ---");
        for (int i = front; i <= rear; i++) {
            if (i == front) {
                System.out.println("-> [Front] " + queue[i] + " (Being Served Next)");
            } else if (i == rear) {
                System.out.println("   [Rear]  " + queue[i] + " (Last in line)");
            } else {
                System.out.println("   [ " + i + " ]   " + queue[i]);
            }
        }
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RailwayTicketQueue ticketCounter = new RailwayTicketQueue(10);
        int choice;

        do {
            System.out.println("\n=== RAILWAY TICKET RESERVATION ===");
            System.out.println("1. New Customer Arrives (Enqueue)");
            System.out.println("2. Serve Customer (Dequeue)");
            System.out.println("3. Check Next Customer (Peek)");
            System.out.println("4. View Entire Line (Display)");
            System.out.println("5. Close Counter (Exit)");
            System.out.print("Enter choice (1-5): ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    ticketCounter.enqueue(name);
                    break;
                case 2:
                    ticketCounter.dequeue();
                    break;
                case 3:
                    ticketCounter.peek();
                    break;
                case 4:
                    ticketCounter.display();
                    break;
                case 5:
                    System.out.println("Closing the ticket counter. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection! Choose between 1 and 5.");
            }
        } while (choice != 5);

        sc.close();
    }
}
