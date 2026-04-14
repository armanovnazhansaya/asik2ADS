public class CustomQueue {
    private Node front;  // Front of queue (dequeue from here)
    private Node rear;   // Rear of queue (enqueue to here)
    private int size;

    // Node class for linked list
    private class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public CustomQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Enqueue: Add element to the end of queue
    public void enqueue(String item) {
        Node newNode = new Node(item);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Added to queue: " + item);
    }

    // Dequeue: Remove and return element from front
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty - Nothing to process");
            return null;
        }

        String removed = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }
        size--;

        System.out.println("Processing: " + removed);
        return removed;
    }

    // Peek: View front element without removing
    public String peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return front.data;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Get size of queue
    public int size() {
        return size;
    }

    // Display all elements in queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue (front → rear): ");
        Node current = front;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println();
    }

    // Display remaining count
    public void displayRemaining() {
        if (isEmpty()) {
            System.out.println("No items remaining");
        } else {
            System.out.println("Remaining in queue: " + size + " item(s)");
            if (front != null && front.next != null) {
                System.out.println("Next in queue: " + front.next.data);
            }
        }
    }
}