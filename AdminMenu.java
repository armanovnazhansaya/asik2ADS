import java.util.LinkedList;
import java.util.Scanner;

public class AdminMenu {
    private LinkedList<BankAccount> accounts;
    private TransactionManager transactionManager;
    private Scanner scanner;

    public AdminMenu(LinkedList<BankAccount> accounts, TransactionManager transactionManager) {
        this.accounts = accounts;
        this.transactionManager = transactionManager;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1.Process next account request");
            System.out.println("2. View pending account requests");
            System.out.println("3. View bill payment queue");
            System.out.println("4. Process next bill");
            System.out.println("5. Display all accounts");
            System.out.println("6. Back to main menu");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    transactionManager.processNextAccountRequest(accounts);
                    break;
                case 2:
                    transactionManager.displayPendingRequests();
                    break;
                case 3:
                    transactionManager.displayBillQueue();
                    break;
                case 4:
                    transactionManager.processNextBill();
                    break;
                case 5:
                    displayAllAccounts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void displayAllAccounts() {
        System.out.println("\nAccounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc);
        }
    }
}
