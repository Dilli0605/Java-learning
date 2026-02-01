import java.util.*;

class User {
    String name;
    int accountNo;
    int balance;
    String password;
    ArrayList<String> transactions = new ArrayList<>();

    User(String name, int accountNo, String password) {
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.balance = 10000;
    }
}

public class Banking {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static int accNumber = 10101;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void createAccount() {
        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Set Password: ");
        String pass = sc.nextLine();

        User user = new User(name, accNumber++, pass);
        users.add(user);

        System.out.println("Account created!");
        System.out.println("Account Number: " + user.accountNo);
    }

    static void login() {
        System.out.print("Enter Account Number: ");
        int acc = sc.nextInt();

        System.out.print("Enter Password: ");
        sc.nextLine();
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.accountNo == acc && u.password.equals(pass)) {
                dashboard(u);
                return;
            }
        }
        System.out.println("Invalid login!");
    }

    static void dashboard(User u) {
        while (true) {
            System.out.println("\n--- DASHBOARD ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> System.out.println("Balance: ₹" + u.balance);

                case 2 -> {
                    System.out.print("Enter amount: ");
                    int amt = sc.nextInt();
                    u.balance += amt;
                    u.transactions.add("Deposited ₹" + amt);
                    System.out.println("Deposit successful");
                }

                case 3 -> transfer(u);

                case 4 -> {
                    System.out.println("Transactions:");
                    for (String t : u.transactions)
                        System.out.println(t);
                }

                case 5 -> {
                    System.out.print("Enter password to logout: ");
                    sc.nextLine(); 
                    String pwd = sc.nextLine();

                    if (u.password.equals(pwd)) {
                        System.out.println("Logout successful");
                        return;
                    } else {
                        System.out.println("Wrong password! Logout cancelled.");
                        }
                    }

                default -> System.out.println("Invalid option");
            }
        }
    }

    static void transfer(User sender) {
        System.out.print("Enter receiver account number: ");
        int acc = sc.nextInt();

        System.out.print("Enter amount: ");
        int amt = sc.nextInt();

        if (sender.balance < amt) {
            System.out.println("Insufficient balance");
            return;
        }

        for (User receiver : users) {
            if (receiver.accountNo == acc) {
                sender.balance -= amt;
                receiver.balance += amt;

                sender.transactions.add("Sent ₹" + amt + " to " + acc);
                receiver.transactions.add("Received ₹" + amt + " from " + sender.accountNo);

                System.out.println("Transfer successful");
                return;
            }
        }
        System.out.println("Receiver not found");
    }
}
