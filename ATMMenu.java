import java.util.LinkedList;
import java.util.Scanner;

public class ATMMenu {
    private LinkedList<BankAccount> accounts;
    private Scanner scanner;

    public ATMMenu(LinkedList<BankAccount> accounts) {
        this.accounts = accounts;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Balance enquiry");
            System.out.println("2. Withdraw");
            System.out.println("3. Back to main menu");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    balanceEnquiry();
                    break;
                case 2:
                    atmWithdraw();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void balanceEnquiry() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        BankAccount acc = findAccount(username);
        if (acc != null) {
            System.out.println("Balance for " + username + ": " + acc.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }

    private void atmWithdraw() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        BankAccount acc = findAccount(username);
        if (acc != null) {
            System.out.print("Amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (acc.withdraw(amount)) {
                System.out.println("Please take your cash. New balance: " + acc.getBalance());
            } else {
                System.out.println("Insufficient balance");
            }
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
}
