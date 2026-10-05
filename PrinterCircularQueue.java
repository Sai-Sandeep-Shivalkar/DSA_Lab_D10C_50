package DSA.Lab;

import java.util.Scanner;

public class PrinterCircularQueue {
    private String[] queue;
    private int front;
    private int rear;
    private int capacity;

    public PrinterCircularQueue(int size) {
        this.capacity = size;
        this.queue = new String[capacity];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void enqueue(String jobName) {
        if (isFull()) {
            System.out.println("[Error] Printer memory full! Cannot accept: " + jobName);
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = jobName;
        System.out.println("[Queued] -> Print job added: " + jobName);
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("[Alert] No jobs to print. (Queue Underflow)");
            return;
        }

        System.out.println("[Printed] <- Completed print job: " + queue[front]);

        if (front == rear) {
            // Queue had only one element, now reset
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
    }

    public void peek() {
        if (isEmpty()) {
            System.out.println("[Status] Printer is idle. No pending jobs.");
            return;
        }
        System.out.println("[Current Printing Job] -> " + queue[front]);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("[Queue] No active print requests.");
            return;
        }

        System.out.println("\n--- Current Printer Spooler Queue ---");
        int i = front;
        while (true) {
            if (i == front && i == rear) {
                System.out.println("-> [Only Job]  " + queue[i]);
            } else if (i == front) {
                System.out.println("-> [Printing]  " + queue[i]);
            } else if (i == rear) {
                System.out.println("   [Last Job]  " + queue[i]);
            } else {
                System.out.println("   [Pending]   " + queue[i]);
            }

            if (i == rear) break;
            i = (i + 1) % capacity;
        }
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Small capacity to clearly observe circular wrap-around
        PrinterCircularQueue printer = new PrinterCircularQueue(4);
        int choice;

        do {
            System.out.println("\n=== PRINTER SPOOLER (CIRCULAR QUEUE) ===");
            System.out.println("1. Submit Print Job (Enqueue)");
            System.out.println("2. Process Print Job (Dequeue)");
            System.out.println("3. Check Next Job (Peek)");
            System.out.println("4. Display All Jobs (Display)");
            System.out.println("5. Shutdown Printer (Exit)");
            System.out.print("Enter choice (1-5): ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter document name (e.g., Thesis.pdf): ");
                    String doc = sc.nextLine();
                    printer.enqueue(doc);
                }
                case 2 -> printer.dequeue();
                case 3 -> printer.peek();
                case 4 -> printer.display();
                case 5 -> System.out.println("Printer shutdown complete. Goodbye!");
                default -> System.out.println("Invalid selection! Choose between 1 and 5.");
            }
        } while (choice != 5);

        sc.close();
    }
}