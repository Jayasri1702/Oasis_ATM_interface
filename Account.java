import java.util.ArrayList;
import java.util.List;

/*
Developed by Jaya Sri S
 */

public class Account {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit Amount: +" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal Amount: -" + amount);
        } else {
            System.out.println("Sorry! Insufficient balance.");
        }
    }

    public void transfer(Account receiver, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add("Transfer : -" + amount + " to " + receiver.getAccountNumber());
        } else {
            System.out.println("Sorry! Insufficient balance.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void run() {
    }
}
