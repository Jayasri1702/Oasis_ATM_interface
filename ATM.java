import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Developed by Jaya Sri S
 */

public class ATM {
    private Map<String, Account> accounts;
    private Map<String, User> users;
    private Account currentAccount;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.users = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void createAccount(String accountNumber, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            Account account = new Account(accountNumber, initialBalance);
            accounts.put(accountNumber, account);
        }
    }

    public void registerUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        if (users.containsKey(userId)) {
            System.out.println("User already exists! Please Log in.");
            return;
        }

        System.out.print("Enter Your password: ");
        String password = scanner.next();

        User user = new User(userId, password);
        users.put(userId, user);
        System.out.println("Yayy! User Registered Successfully.");
    }

    public void login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        if (!users.containsKey(userId)) {
            System.out.println("Sorry! User does not exist. Please Register First.");
            return;
        }
    
        System.out.print("Enter Your Password: ");
        String password = scanner.next();
    
        User user = users.get(userId);
        if (user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Yayy! Login successful.");
    
            // Associate the currentAccount with the logged-in user
            if (accounts.containsKey(currentUser.getUserId())) {
                currentAccount = accounts.get(currentUser.getUserId());
            } else {
                // If account not found, create a new account for the user
                currentAccount = new Account(currentUser.getUserId(), 0.0);
                accounts.put(currentUser.getUserId(), currentAccount);
            }
    
            run(); // Start the ATM menu after successful login
        } else {
            System.out.println("Sorry! Incorrect password. Please try again.");
        }
    }
    
    public void logout() {
        currentAccount = null;
        currentUser = null;
        System.out.println("Thank you! Visit Again ! Logged out successfully.");
    }

    public void displayOptions() {
        System.out.println("Welcome to the Jaya Sri ATM.");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Transaction History");
        System.out.println("5. Logout");
        System.out.print("Please choose an option: ");
    }
    public void run() {
        if (currentUser == null || currentAccount == null) {
            System.out.println("Please Log in first.");
            return;
        }
        while (true) {
            displayOptions();
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    // Deposit functionality
                    System.out.print("Enter Deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 2:
                    // Withdraw functionality
                    System.out.print("Enter Withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawalAmount);
                    break;
                case 3:
                    // Transfer functionality
                    System.out.print("Enter Receiver's account number: ");
                    String receiverAccountNumber = scanner.next();
                    System.out.print("Enter Transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    Account receiver = accounts.get(receiverAccountNumber);
                    if (receiver != null) {
                        currentAccount.transfer(receiver, transferAmount);
                    } else {
                        System.out.println("Receiver Account not found.");
                    }
                    break;
                case 4:
                    // View transaction history
                    currentAccount.printTransactionHistory();
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
    

    public static void main(String[] args) {
        ATM atm = new ATM();

        // Register users (You can add more users as needed)
        atm.createAccount("22222", 10000);
        atm.createAccount("11111", 5000);

        // User registration or login
        while (true) {
            System.out.println("1. Register User \n2. Login \n3. Exit");
            System.out.print("Please choose an option: ");
            int option = atm.scanner.nextInt();

            switch (option) {
                case 1:
                    atm.registerUser();
                    break;
                case 2:
                    atm.login();
                    if (atm.currentUser != null) {
                        atm.run();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using our ATM. Have a Nice Day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
