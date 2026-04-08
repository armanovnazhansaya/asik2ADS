import java.util.LinkedList;
import java.util.Scanner;

public class BankMenu {
    private LinkedList<BankAccount> accounts;
    private TransactionManager transactionManager;
    private Scanner scanner;

    public BankMenu(LinkedList<BankAccount> accounts, TransactionManager transactionManager) {
        this.accounts = accounts;
        this.transactionManager = transactionManager;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Submit account opening request");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Display all accounts");
            System.out.println("5. Search account by username");
            System.out.println("6. Show transaction history");
            System.out.println("7. Undo last transaction");
            System.out.println("8. Add bill payment");
            System.out.println("9. Process next bill");
            System.out.println("10. Back to main menu");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    submitAccountRequest();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    displayAllAccounts();
                    break;
                case 5:
                    searchAccount();
                    break;
                case 6:
                    transactionManager.showLastTransaction();
                    break;
                case 7:
                    transactionManager.undoLastTransaction();
                    break;
                case 8:
                    addBillPayment();
                    break;
                case 9:
                    transactionManager.processNextBill();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void submitAccountRequest() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        transactionManager.addAccountRequest(username + ":" + accNum);
    }

    private void depositMoney() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        BankAccount acc = findAccount(username);
        if (acc != null) {
            System.out.print("Deposit amount: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
            transactionManager.addTransaction("Deposit " + amount + " to " + username);
            System.out.println("New balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        BankAccount acc = findAccount(username);
        if (acc != null) {
            System.out.print("Withdraw amount: ");
            double amount = scanner.nextDouble();
            if (acc.withdraw(amount)) {
                transactionManager.addTransaction("Withdraw " + amount + " from " + username);
                System.out.println("New balance: " + acc.getBalance());
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("Account not found");
        }
    }

    private void displayAllAccounts() {
        System.out.println("\nAccounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc);
        }
    }

    private void searchAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        BankAccount acc = findAccount(username);
        if (acc != null) {
            System.out.println("Account found: " + acc);
        } else {
            System.out.println("Account not found");
        }
    }

    private BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    private void addBillPayment() {
        System.out.print("Enter bill name (e.g., Electricity Bill): ");
        String bill = scanner.nextLine();
        transactionManager.addBillPayment(bill);
    }
}
