

import java.util.Scanner;

// Main ATM Class — requires BankAccount.java to be in the same directory
public class ATM {

    // Function to display the main menu
    static void displayMenu(String accountHolder) {
        System.out.println("\n");
        System.out.println("   Welcome, " + accountHolder + "!");
        System.out.println("");
        System.out.println("  1. Check Balance");
        System.out.println("  2. Deposit Money");
        System.out.println("  3. Withdraw Money");
        System.out.println("  4. View Transaction History");
        System.out.println("  5. Logout");
        System.out.print("  Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the one built-in account
        BankAccount account = new BankAccount("Ben", 1234, 500.0);

        System.out.println("");
        System.out.println("Welcome to Bank ATM");
        System.out.println("");

        // Outer loop keeps the ATM running so a user can log in again after logging out
        boolean atmRunning = true;
        while (atmRunning) {

            // --- LOGIN SECTION ---
            boolean loggedIn = false;
            int attempts = 0;
            int maxAttempts = 3;

            // Loop to allow multiple login attempts
            while (!loggedIn && attempts < maxAttempts) {
                System.out.print("\nEnter your 4-digit passkey: ");

                // Conditional to check if input is a valid integer
                if (scanner.hasNextInt()) {
                    int enteredKey = scanner.nextInt();
                    attempts++;

                    if (account.validatePasskey(enteredKey)) {
                        loggedIn = true;
                        System.out.println("\n✔ Access Granted!");
                    } else {
                        int remaining = maxAttempts - attempts;
                        if (remaining > 0) {
                            System.out.println("✘ Incorrect passkey. " + remaining + " attempt(s) remaining.");
                        } else {
                            System.out.println("✘ Too many failed attempts. Card blocked. Please contact your bank.");
                        }
                    }
                } else {
                    System.out.println("✘ Invalid input. Please enter numbers only.");
                    scanner.next(); // Clear invalid input
                }
            }

            // --- ACCOUNT MENU SECTION ---
            if (loggedIn) {
                boolean sessionActive = true;

                // Loop keeps the menu running until the user logs out
                while (sessionActive) {
                    displayMenu(account.getOwner());

                    // Conditional to handle menu choice
                    if (scanner.hasNextInt()) {
                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            account.checkBalance();
                        } else if (choice == 2) {
                            System.out.print("\n  Enter deposit amount: $");
                            if (scanner.hasNextDouble()) {
                                double amount = scanner.nextDouble();
                                account.deposit(amount);
                            } else {
                                System.out.println("✘ Invalid amount entered.");
                                scanner.next();
                            }
                        } else if (choice == 3) {
                            System.out.print("\n  Enter withdrawal amount: $");
                            if (scanner.hasNextDouble()) {
                                double amount = scanner.nextDouble();
                                account.withdraw(amount);
                            } else {
                                System.out.println("✘ Invalid amount entered.");
                                scanner.next();
                            }
                        } else if (choice == 4) {
                            account.printHistory();
                        } else if (choice == 5) {
                            System.out.println("\nLogged out successfully. Thank you for using Bank ATM!");
                            sessionActive = false;
                        } else {
                            System.out.println("\n✘ Invalid choice. Please select 1-5.");
                        }
                    } else {
                        System.out.println("\n✘ Invalid input. Please enter a number.");
                        scanner.next();
                    }
                }

                // Ask if user wants to exit or use the ATM again
                System.out.print("\nReturn to login screen? (yes/no): ");
                String again = scanner.next();
                if (!again.equalsIgnoreCase("yes")) {
                    atmRunning = false;
                    System.out.println("\nThank you for using Bank ATM. Goodbye!\n");
                }

            } else {
                // Account is blocked after failed attempts — exit ATM
                atmRunning = false;
                System.out.println("\nATM session ended.\n");
            }
        }

        scanner.close();
    }
}