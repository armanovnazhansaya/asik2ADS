import java.util.LinkedList;

public class Main {
    private static LinkedList<BankAccount> accounts;
    private static TransactionManager transactionManager;
    private static BankMenu bankMenu;
    private static ATMMenu atmMenu;
    private static AdminMenu adminMenu;

    public static void main(String[] args) {
        // Инициализация
        accounts = new LinkedList<>();
        transactionManager = new TransactionManager();
        bankMenu = new BankMenu(accounts, transactionManager);
        atmMenu = new ATMMenu(accounts);
        adminMenu = new AdminMenu(accounts, transactionManager);

        // Демонстрация Part 1 - Task 1, 2 (добавление аккаунтов в LinkedList)
        System.out.println("=== PART 1: Logical Data Structures ===\n");

        // Task 1 & 2: BankAccount + LinkedList + Deposit/Withdraw
        System.out.println("--- Task 1 & 2: BankAccount & LinkedList ---");
        accounts.add(new BankAccount("A001", "Ali", 150000));
        accounts.add(new BankAccount("A002", "Sara", 220000));
        displayAccounts();

        // Task 3: Stack (Transaction History)
        System.out.println("\n--- Task 3: Transaction History (Stack) ---");
        transactionManager.addTransaction("Deposit 50000 to Ali");
        transactionManager.addTransaction("Withdraw 20000 from Ali");
        transactionManager.showLastTransaction();
        transactionManager.undoLastTransaction();
        transactionManager.showLastTransaction();

        // Task 4: Queue (Bill Payment)
        System.out.println("\n--- Task 4: Bill Payment Queue ---");
        transactionManager.addBillPayment("Electricity Bill");
        transactionManager.addBillPayment("Internet Bill");
        transactionManager.processNextBill();
        transactionManager.displayBillQueue();

        // Task 5: Queue (Account Opening - Admin Simulation)
        System.out.println("\n--- Task 5: Account Opening Queue ---");
        transactionManager.addAccountRequest("John:J001");
        transactionManager.addAccountRequest("Emma:E001");
        transactionManager.displayPendingRequests();
        transactionManager.processNextAccountRequest(accounts);
        transactionManager.displayPendingRequests();
        displayAccounts();

        // Task 6: Physical Data Structure (Array)
        System.out.println("\n=== PART 2: Physical Data Structures ===");
        System.out.println("--- Task 6: Array of BankAccount (Fixed size 3) ---");
        BankAccount[] fixedAccounts = new BankAccount[3];
        fixedAccounts[0] = new BankAccount("P001", "Predefined1", 1000);
        fixedAccounts[1] = new BankAccount("P002", "Predefined2", 2000);
        fixedAccounts[2] = new BankAccount("P003", "Predefined3", 3000);
        for (int i = 0; i < fixedAccounts.length; i++) {
            System.out.println((i + 1) + ". " + fixedAccounts[i]);
        }

        // Part 3: Mini Banking Menu
        System.out.println("\n=== PART 3: Mini Banking Menu ===");
        runMainMenu();
    }

    private static void displayAccounts() {
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc);
        }
    }

    private static void runMainMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1 -- Enter Bank");
            System.out.println("2 -- Enter ATM");
            System.out.println("3 -- Admin Area");
            System.out.println("4 -- Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bankMenu.show();
                    break;
                case 2:
                    atmMenu.show();
                    break;
                case 3:
                    adminMenu.show();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}