

import java.util.ArrayList;

// Class representing a Bank Account
public class BankAccount {
    private String owner;
    private int passkey;
    private double balance;
    private ArrayList<String> transactionHistory; // Data structure from Java Collections Framework

    // Constructor
    public BankAccount(String owner, int passkey, double initialBalance) {
        this.owner = owner;
        this.passkey = passkey;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: $" + String.format("%.2f", initialBalance));
    }

    // Function to validate the passkey
    public boolean validatePasskey(int enteredKey) {
        return this.passkey == enteredKey;
    }

    // Function to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            String record = "Deposited: $" + String.format("%.2f", amount) + " | New Balance: $" + String.format("%.2f", balance);
            transactionHistory.add(record);
            System.out.println("\n✔ Success! " + record);
        } else {
            System.out.println("\n✘ Invalid deposit amount. Please enter a value greater than $0.");
        }
    }

    // Function to withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("\n✘ Invalid withdrawal amount. Please enter a value greater than $0.");
        } else if (amount > balance) {
            System.out.println("\n✘ Insufficient funds. Your current balance is: $" + String.format("%.2f", balance));
        } else {
            balance -= amount;
            String record = "Withdrew:  $" + String.format("%.2f", amount) + " | New Balance: $" + String.format("%.2f", balance);
            transactionHistory.add(record);
            System.out.println("\n✔ Success! " + record);
        }
    }

    // Function to check balance
    public void checkBalance() {
        System.out.println("\n💰 Current Balance: $" + String.format("%.2f", balance));
    }

    // Function to print transaction history using a loop
    public void printHistory() {
        System.out.println("\n📋 Transaction History for " + owner + ":");
        System.out.println("------------------------------------------");
        if (transactionHistory.isEmpty()) {
            System.out.println("  No transactions yet.");
        } else {
            // Loop through the ArrayList to display each transaction
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + transactionHistory.get(i));
            }
        }
        System.out.println("------------------------------------------");
    }

    // Getter for account owner name
    public String getOwner() {
        return owner;
    }
}