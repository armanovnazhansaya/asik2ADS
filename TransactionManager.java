import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TransactionManager {
    private Stack<String> transactionHistory;
    private Queue<String> billQueue;
    private Queue<String> accountRequestQueue;

    public TransactionManager() {
        transactionHistory = new Stack<>();
        billQueue = new LinkedList<>();
        accountRequestQueue = new LinkedList<>();
    }

    // Stack operations
    public void addTransaction(String transaction) {
        transactionHistory.push(transaction);
        System.out.println("Added: " + transaction);
    }

    public void undoLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            String removed = transactionHistory.pop();
            System.out.println("Undo → " + removed + " removed");
        } else {
            System.out.println("No transactions to undo");
        }
    }

    public void showLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            System.out.println("Last transaction: " + transactionHistory.peek());
        } else {
            System.out.println("No transactions");
        }
    }

    // Bill Queue (FIFO)
    public void addBillPayment(String bill) {
        billQueue.add(bill);
        System.out.println("Added: " + bill);
    }

    public void processNextBill() {
        if (!billQueue.isEmpty()) {
            String bill = billQueue.poll();
            System.out.println("Processing: " + bill);
            System.out.println("Remaining: " + billQueue);
        } else {
            System.out.println("No bills in queue");
        }
    }

    public void displayBillQueue() {
        System.out.println("Bill Queue: " + billQueue);
    }

    // Account Request Queue (Admin)
    public void addAccountRequest(String request) {
        accountRequestQueue.add(request);
        System.out.println("Account request submitted: " + request);
    }

    public void processNextAccountRequest(LinkedList<BankAccount> accounts) {
        if (!accountRequestQueue.isEmpty()) {
            String request = accountRequestQueue.poll();
            // Format: "username:accountNumber"
            String[] parts = request.split(":");
            String username = parts[0];
            String accountNumber = parts[1];
            BankAccount newAccount = new BankAccount(accountNumber, username, 0.0);
            accounts.add(newAccount);
            System.out.println("Processed request → Account created for " + username);
        } else {
            System.out.println("No pending account requests");
        }
    }

    public void displayPendingRequests() {
        System.out.println("Pending account requests: " + accountRequestQueue);
    }

    public Stack<String> getTransactionHistory() {
        return transactionHistory;
    }
}