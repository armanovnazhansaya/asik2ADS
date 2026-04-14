import java.util.LinkedList;

public class CustomAccountQueue {
    private Node front;
    private Node rear;
    private int size;

    private class Node {
        String username;
        String accountNumber;
        Node next;

        Node(String username, String accountNumber) {
            this.username = username;
            this.accountNumber = accountNumber;
            this.next = null;
        }
    }

    public CustomAccountQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add account request to queue
    public void enqueue(String username, String accountNumber) {
        Node newNode = new Node(username, accountNumber);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Account request submitted for: " + username);
    }

    // Process account request (move to main LinkedList)
    public BankAccount dequeueAndCreate(LinkedList<BankAccount> accounts) {
        if (isEmpty()) {
            System.out.println("No pending account requests");
            return null;
        }

        Node request = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }
        size--;

        BankAccount newAccount = new BankAccount(request.accountNumber, request.username, 0.0);
        accounts.add(newAccount);
        System.out.println("✓ Account created for " + request.username + " (Account #: " + request.accountNumber + ")");
        return newAccount;
    }
    public boolean isEmpty() {
        return front == null;
    }

    // Display pending requests
    public void displayPending() {
        if (isEmpty()) {
            System.out.println("No pending account requests");
            return;
        }

        System.out.print("Pending account requests: ");
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.print(count++ + "." + current.username + "(" + current.accountNumber + ")");
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}

// Check if queue is empty
