public class CustomStack {
    private String[] elements;
    private int top;
    private int capacity;

    public CustomStack(int capacity) {
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.top = -1;
    }

    // Push: Add element to top
    public void push(String item) {
        if (top == capacity - 1) {
            // Stack overflow - resize array
            resize();
        }
        elements[++top] = item;
        System.out.println("Added: " + item);
    }

    // Pop: Remove and return top element
    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow - No transactions to undo");
            return null;
        }
        String removed = elements[top];
        elements[top--] = null;
        System.out.println("Undo → " + removed + " removed");
        return removed;
    }

    // Peek: View top element without removing
    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty - No transactions");
            return null;
        }
        System.out.println("Last transaction: " + elements[top]);
        return elements[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Get size of stack
    public int size() {
        return top + 1;
    }

    // Display all transactions
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No transaction history");
            return;
        }
        System.out.println("Transaction History (most recent first):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  " + elements[i]);
        }
    }

    // Resize array when full
    private void resize() {
        int newCapacity = capacity * 2;
        String[] newElements = new String[newCapacity];
        for (int i = 0; i <= top; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        capacity = newCapacity;
        System.out.println("Stack resized to capacity: " + capacity);
    }
}
