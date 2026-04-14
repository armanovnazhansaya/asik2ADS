import java.util.LinkedList;
import java.util.Scanner;

public class BankingSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    BANKING SYSTEM ASSIGNMENT - ADS");
        System.out.println("=========================================");

        // Run all tasks sequentially
        runTask1();
        runTask2();
        runTask3();
        runTask4();
        runTask5();
        runTask6();

        // Run Part 3 - Mini Banking Menu
        runMiniBankingMenu();

        System.out.println("\n=========================================");
        System.out.println("Program completed successfully!");
        System.out.println("=========================================");
        scanner.close();
    }

    // ==================== TASK 1 ====================
    // Bank Account Storage Using LinkedList
    private static void runTask1() {
        System.out.println("\n========== TASK 1 ==========");
        System.out.println("Bank Account Storage Using LinkedList");
        System.out.println("=====================================");

        LinkedList<BankAccount> accounts = new LinkedList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Task 1 Menu ---");
            System.out.println("1. Add a new account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Search account by username");
            System.out.println("4. Exit Task 1");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addNewAccount(accounts);
                    break;
                case 2:
                    displayAllAccounts(accounts);
                    break;
                case 3:
                    searchAccountByUsername(accounts);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addNewAccount(LinkedList<BankAccount> accounts) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = getDoubleInput();

        accounts.add(new BankAccount(accNum, username, balance));
        System.out.println("✓ Account added successfully!");

        // Example output format as required
        System.out.println("\nAccounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc.getUsername() + " -- Balance: " + String.format("%.0f", acc.getBalance()));
        }
    }

    private static void displayAllAccounts(LinkedList<BankAccount> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\nAccounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc.getUsername() + " -- Balance: " + String.format("%.0f", acc.getBalance()));
        }
    }

    private static void searchAccountByUsername(LinkedList<BankAccount> accounts) {
        System.out.print("Enter username to search: ");
        String username = scanner.nextLine();

        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                System.out.println("✓ Account found: " + acc.getUsername() + " -- Balance: " + String.format("%.0f", acc.getBalance()));
                return;
            }
        }
        System.out.println("Account not found.");
    }

    // ==================== TASK 2 ====================
    // Deposit & Withdraw Operations
    private static void runTask2() {
        System.out.println("\n========== TASK 2 ==========");
        System.out.println("Deposit & Withdraw Operations");
        System.out.println("==============================");

        LinkedList<BankAccount> accounts = new LinkedList<>();
        accounts.add(new BankAccount("A001", "Ali", 150000));
        accounts.add(new BankAccount("A002", "Sara", 220000));

        System.out.println("\nInitial accounts:");
        displayAllAccounts(accounts);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 2 Menu ---");
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Display all accounts");
            System.out.println("4. Exit Task 2");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    depositMoney(accounts);
                    break;
                case 2:
                    withdrawMoney(accounts);
                    break;
                case 3:
                    displayAllAccounts(accounts);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void depositMoney(LinkedList<BankAccount> accounts) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        BankAccount account = findAccount(accounts, username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Deposit amount: ");
        double amount = getDoubleInput();
        account.deposit(amount);
        System.out.println("New balance: " + String.format("%.0f", account.getBalance()));
    }

    private static void withdrawMoney(LinkedList<BankAccount> accounts) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        BankAccount account = findAccount(accounts, username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Withdraw amount: ");
        double amount = getDoubleInput();
        if (account.withdraw(amount)) {
            System.out.println("New balance: " + String.format("%.0f", account.getBalance()));
        }
    }

    private static BankAccount findAccount(LinkedList<BankAccount> accounts, String username) {
        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    // ==================== TASK 3 ====================
    // Transaction History (Custom Stack - LIFO)
    private static void runTask3() {
        System.out.println("\n========== TASK 3 ==========");
        System.out.println("Transaction History (Custom Stack - LIFO)");
        System.out.println("=========================================");

        CustomStack stack = new CustomStack(5);

        System.out.println("\nExample transactions:");
        stack.push("Deposit 50000 to Ali");
        stack.push("Withdraw 20000 from Ali");
        stack.push("Bill payment 15000 to Electricity");

        System.out.println("\n--- Current State ---");
        stack.peek();
        stack.displayAll();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 3 Menu ---");
            System.out.println("1. Add transaction (push)");
            System.out.println("2. Undo last transaction (pop)");
            System.out.println("3. Display last transaction (peek)");
            System.out.println("4. Show all history");
            System.out.println("5. Exit Task 3");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter transaction description: ");
                    String transaction = scanner.nextLine();
                    stack.push(transaction);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.displayAll();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ==================== TASK 4 ====================
    // Bill Payment Queue (Custom Queue - FIFO)
    private static void runTask4() {
        System.out.println("\n========== TASK 4 ==========");
        System.out.println("Bill Payment Queue (Custom Queue - FIFO)");
        System.out.println("========================================");

        CustomQueue billQueue = new CustomQueue();

        System.out.println("\nAdding sample bills:");
        billQueue.enqueue("Electricity Bill");
        billQueue.enqueue("Internet Bill");
        billQueue.enqueue("Water Bill");

        System.out.println("\n--- Current Queue ---");
        billQueue.display();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 4 Menu ---");
            System.out.println("1. Add bill payment request");
            System.out.println("2. Process next bill payment (dequeue)");
            System.out.println("3. Display bill queue");
            System.out.println("4. Show remaining count");
            System.out.println("5. Exit Task 4");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter bill name (e.g., Electricity Bill): ");
                    String bill = scanner.nextLine();
                    billQueue.enqueue(bill);
                    break;
                case 2:
                    String processed = billQueue.dequeue();
                    if (processed != null) {
                        billQueue.displayRemaining();
                    }
                    break;
                case 3:
                    billQueue.display();
                    break;
                case 4:
                    System.out.println("Bills in queue: " + billQueue.size());
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

// ==================== TASK 5 ====================
// Account Opening Queue (Custom Queue for Admin)
private static void runTask5() {
    System.out.println("\n========== TASK 5 ==========");
    System.out.println("Account Opening Queue (Custom Queue for Admin)");
    System.out.println("==============================================");

    LinkedList<BankAccount> accounts = new LinkedList<>();
    CustomAccountQueue accountQueue = new CustomAccountQueue();

    System.out.println("\nSubmitting account requests:");
    accountQueue.enqueue("John", "J001");
    accountQueue.enqueue("Emma", "E001");
    accountQueue.enqueue("Michael", "M001");

    boolean running = true;
    while (running) {
        System.out.println("\n--- Task 5 Menu ---");
        System.out.println("1. Submit new account request (User)");
        System.out.println("2. Process request - move to LinkedList (Admin)");
        System.out.println("3. Display pending requests");
        System.out.println("4. Display all approved accounts");
        System.out.println("5. Exit Task 5");
        System.out.print("Choose option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter desired account number: ");
                String accNum = scanner.nextLine();
                accountQueue.enqueue(username, accNum);
                break;
            case 2:
                accountQueue.dequeueAndCreate(accounts);
                break;
            case 3:
                accountQueue.displayPending();
                break;
            case 4:
                displayAllAccounts(accounts);
                break;
            case 5:
                running = false;
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}

    // ==================== TASK 6 ====================
    // Physical Data Structures - Array
    private static void runTask6() {
        System.out.println("\n========== TASK 6 ==========");
        System.out.println("Physical Data Structures - Array");
        System.out.println("================================");

        // Creates array BankAccount[3]
        BankAccount[] fixedAccounts = new BankAccount[3];

        // Stores 3 predefined accounts
        fixedAccounts[0] = new BankAccount("P001", "PredefinedUser1", 1000.0);
        fixedAccounts[1] = new BankAccount("P002", "PredefinedUser2", 2000.0);
        fixedAccounts[2] = new BankAccount("P003", "PredefinedUser3", 3000.0);

        // Prints them
        System.out.println("\nArray of BankAccount[3] (Physical Data Structure):");
        System.out.println("Arrays store data in contiguous memory locations");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < fixedAccounts.length; i++) {
            System.out.println((i + 1) + ". " + fixedAccounts[i]);
        }

        System.out.println("\nNote: Array is a PHYSICAL data structure because:");
        System.out.println("- Elements are stored in contiguous memory blocks");
        System.out.println("- Fixed size, direct index access (O(1) time)");
        System.out.println("- Memory allocated at compile time (static)");
    }

    // ==================== PART 3 ====================
    // Mini Banking Menu (Integrates everything with custom structures)
    private static void runMiniBankingMenu() {
        System.out.println("\n========== PART 3 ==========");
        System.out.println("Mini Banking Menu");
        System.out.println("==================");

        LinkedList<BankAccount> accounts = new LinkedList<>();
        CustomStack transactionStack = new CustomStack(10);
        CustomQueue billQueue = new CustomQueue();
        CustomAccountQueue accountRequestQueue = new CustomAccountQueue();

        // Add some initial accounts for testing
        accounts.add(new BankAccount("A001", "Ali", 150000));
        accounts.add(new BankAccount("A002", "Sara", 220000));

        boolean running = true;

        while (running) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║      MAIN MENU             ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║  1 -- Enter Bank           ║");
            System.out.println("║  2 -- Enter ATM            ║");
            System.out.println("║  3 -- Admin Area           ║");
            System.out.println("║  4 -- Exit                 ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    bankMenu(accounts, transactionStack, billQueue, accountRequestQueue);
                    break;
                case 2:
                    atmMenu(accounts);
                    break;
                case 3:
                    adminMenu(accounts, accountRequestQueue, billQueue);
                    break;
                case 4:
                    System.out.println("Thank you for using our banking system!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Bank Menu (User operations)
    private static void bankMenu(LinkedList<BankAccount> accounts, CustomStack stack,
                                 CustomQueue billQueue, CustomAccountQueue accountQueue) {
        boolean running = true;

        while (running) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│           BANK MENU              │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│ 1. Submit account request       │");
            System.out.println("│ 2. Deposit money                │");
            System.out.println("│ 3. Withdraw money               │");
            System.out.println("│ 4. Check balance                │");
            System.out.println("│ 5. View transaction history     │");
            System.out.println("│ 6. Undo last transaction        │");
            System.out.println("│ 7. Add bill payment             │");
            System.out.println("│ 8. Back to main menu            │");
            System.out.println("└─────────────────────────────────┘");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter account number: ");
                    String accNum = scanner.nextLine();
                    accountQueue.enqueue(username, accNum);
                    System.out.println("Request submitted to admin queue.");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String depUser = scanner.nextLine();
                    BankAccount depAcc = findAccount(accounts, depUser);
                    if (depAcc != null) {
                        System.out.print("Amount to deposit: ");
                        double amount = getDoubleInput();
                        depAcc.deposit(amount);
                        stack.push("Deposit " + amount + " to " + depUser);
                        System.out.println("New balance: " + depAcc.getBalance());
                    } else {
                        System.out.println("Account not found.Please contact admin.");
                    }
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    String witUser = scanner.nextLine();
                    BankAccount witAcc = findAccount(accounts, witUser);
                    if (witAcc != null) {
                        System.out.print("Amount to withdraw: ");
                        double amount = getDoubleInput();
                        if (witAcc.withdraw(amount)) {
                            stack.push("Withdraw " + amount + " from " + witUser);
                            System.out.println("New balance: " + witAcc.getBalance());
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter username: ");
                    String balUser = scanner.nextLine();
                    BankAccount balAcc = findAccount(accounts, balUser);
                    if (balAcc != null) {
                        System.out.println("Balance for " + balUser + ": " + balAcc.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    stack.displayAll();
                    break;
                case 6:
                    stack.pop();
                    break;
                case 7:
                    System.out.print("Enter bill name (e.g., Electricity Bill): ");
                    String bill = scanner.nextLine();
                    billQueue.enqueue(bill);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ATM Menu
    private static void atmMenu(LinkedList<BankAccount> accounts) {
        boolean running = true;

        while (running) {
            System.out.println("\n┌─────────────────────────┐");
            System.out.println("│       ATM MENU          │");
            System.out.println("├─────────────────────────┤");
            System.out.println("│  1. Balance enquiry     │");
            System.out.println("│  2. Withdraw cash       │");
            System.out.println("│  3. Back to main menu   │");
            System.out.println("└─────────────────────────┘");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    BankAccount acc = findAccount(accounts, username);
                    if (acc != null) {
                        System.out.println("Your balance: " + String.format("%.0f", acc.getBalance()) + " tenge");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String witUser = scanner.nextLine();
                    BankAccount witAcc = findAccount(accounts, witUser);
                    if (witAcc != null) {
                        System.out.print("Amount to withdraw: ");
                        double amount = getDoubleInput();
                        if (witAcc.withdraw(amount)) {
                            System.out.println("Please take your cash.");
                            System.out.println("Remaining balance: " + witAcc.getBalance());
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Admin Menu
    private static void adminMenu(LinkedList<BankAccount> accounts,
                                  CustomAccountQueue accountQueue, CustomQueue billQueue) {
        boolean running = true;

        while (running) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│           ADMIN MENU             │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│ 1. View account requests        │");
            System.out.println("│ 2. Process next account request │");
            System.out.println("│ 3. View bill queue              │");
            System.out.println("│ 4. Process next bill            │");
            System.out.println("│ 5. View all accounts            │");
            System.out.println("│ 6. Back to main menu            │");
            System.out.println("└─────────────────────────────────┘");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    accountQueue.displayPending();
                    break;
                case 2:
                    accountQueue.dequeueAndCreate(accounts);
                    break;
                case 3:
                    billQueue.display();
                    break;
                case 4:
                    String processed = billQueue.dequeue();
                    if (processed != null) {
                        billQueue.displayRemaining();
                    }
                    break;
                case 5:
                    displayAllAccounts(accounts);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Helper methods for input handling
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}