package DSA.Lab;

import java.util.Scanner;

public class BrowserHistory {
    private String[] stack;
    private int top;
    private int capacity;

    public BrowserHistory(int size){
        this.capacity = size;
        this.stack = new String[capacity];
        this.top = -1;
    }

    public void push(String url){
        if(top == capacity - 1){
            System.out.println("[Error] History is full! Cannot add: " + url);
        }
        ++top;
        stack[top] = url;
        System.out.println("[Visited] -> " + url);
    }

    public void pop(){
        if(top == -1){
            System.out.println("[Alert] No history found. (Stack Underflow)");
        }
        System.out.println("[Back] Left page: " + stack[top--]);
        if (top != -1){
            System.out.println("[Current Page] -> " + stack[top]);
        } else {
            System.out.println("[Current Page] -> Blank Page (History is empty)");
        }
    }

    public void peek(){
        if(top == -1){
            System.out.println("[Status] No active page open.");
            return;
        }
        System.out.println("[Current Active Page] -> " + stack[top]);
    }

    public void display(){
        if(top == -1){
            System.out.println("[History] Browsing history is empty.");
            return;
        }
        System.out.println("\n--- Browsing History (Top to Bottom) ---");
        for(int i = top; i >= 0; i--){
            if (i == top) {
                System.out.println("-> [Top] " + stack[i] + " (Current)");
            } else {
                System.out.println(" [ " + i + " ] " + stack[i]);
            }
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BrowserHistory browser = new BrowserHistory(10);
        int choice;

        do{
            System.out.println("\n--- Browser History Menu ---");
            System.out.println("1. Visit New Page (Push)");
            System.out.println("2. Back Button (Pop)");
            System.out.println("3. Current Page (Peek)");
            System.out.println("4. Display History (Display)");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter avalid number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter URL: ");
                    String url = sc.next();
                    browser.push(url);
                }
                case 2 -> browser.pop();
                case 3 -> browser.peek();
                case 4 -> browser.display();
                case 5 -> System.out.println("Exiting browser simulation.");
                default -> System.out.println("Invalid selection! Choose between 1 and 5.");
            }
        }while(choice != 5);
        sc.close();
    }
}
