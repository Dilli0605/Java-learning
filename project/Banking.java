import java.util.Scanner;
import java.sql.*;

/* ---------- DATABASE CONNECTION ---------- */
class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bank_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Dilli_06";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

/* ---------- USER CLASS ---------- */
class User {
    String name;
    int accountNo;
    int balance;
    String password;

    User(String name, int accountNo, String password, int balance) {
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.balance = balance;
    }
}

/* ---------- MAIN BANKING CLASS ---------- */
public class Banking {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // FIX: clear buffer

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

    /* ---------- CREATE ACCOUNT ---------- */
    static void createAccount() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Set Password: ");
            String pass = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users(name, password, balance) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, name);
            ps.setString(2, pass);
            ps.setInt(3, 10000);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Account created successfully!");
                System.out.println("Account Number: " + rs.getInt(010101));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ---------- LOGIN ---------- */
    static void login() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Enter Account Number: ");
            int acc = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM users WHERE account_no=? AND password=?"
            );
            ps.setInt(1, acc);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getString("name"),
                        rs.getInt("account_no"),
                        rs.getString("password"),
                        rs.getInt("balance")
                );
                dashboard(u);
            } else {
                System.out.println("Invalid login!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ---------- DASHBOARD ---------- */
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
            sc.nextLine();

            switch (ch) {
                case 1 -> System.out.println("Balance: ₹" + u.balance);
                case 2 -> deposit(u);
                case 3 -> transfer(u);
                case 4 -> showTransactions(u);

                case 5 -> {
                    System.out.print("Enter password to logout: ");
                    String pwd = sc.nextLine();

                    if (u.password.equals(pwd)) {
                        System.out.println("Logout successful");
                        return;
                    } else {
                        System.out.println("Wrong password!");
                    }
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ---------- DEPOSIT ---------- */
    static void deposit(User u) {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Enter amount: ");
            int amt = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE account_no=?"
            );
            ps.setInt(1, amt);
            ps.setInt(2, u.accountNo);
            ps.executeUpdate();

            PreparedStatement t = conn.prepareStatement(
                    "INSERT INTO transactions(account_no, transaction_detail) VALUES (?, ?)"
            );
            t.setInt(1, u.accountNo);
            t.setString(2, "Deposited ₹" + amt);
            t.executeUpdate();

            u.balance += amt;
            System.out.println("Deposit successful");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ---------- TRANSFER ---------- */
    static void transfer(User sender) {

        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Enter receiver account number: ");
            int receiverAcc = sc.nextInt();

            System.out.print("Enter amount: ");
            int amt = sc.nextInt();
            sc.nextLine();

            if (sender.balance < amt) {
                System.out.println("Insufficient balance");
                return;
            }

            conn.setAutoCommit(false); // IMPORTANT

            PreparedStatement check = conn.prepareStatement(
                    "SELECT account_no FROM users WHERE account_no=?"
            );
            check.setInt(1, receiverAcc);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                System.out.println("Receiver not found");
                conn.rollback();
                return;
            }

            PreparedStatement debit = conn.prepareStatement(
                    "UPDATE users SET balance = balance - ? WHERE account_no=?"
            );
            debit.setInt(1, amt);
            debit.setInt(2, sender.accountNo);
            debit.executeUpdate();

            PreparedStatement credit = conn.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE account_no=?"
            );
            credit.setInt(1, amt);
            credit.setInt(2, receiverAcc);
            credit.executeUpdate();

            PreparedStatement t1 = conn.prepareStatement(
                    "INSERT INTO transactions(account_no, transaction_detail) VALUES (?, ?)"
            );
            t1.setInt(1, sender.accountNo);
            t1.setString(2, "Sent ₹" + amt + " to " + receiverAcc);
            t1.executeUpdate();

            PreparedStatement t2 = conn.prepareStatement(
                    "INSERT INTO transactions(account_no, transaction_detail) VALUES (?, ?)"
            );
            t2.setInt(1, receiverAcc);
            t2.setString(2, "Received ₹" + amt + " from " + sender.accountNo);
            t2.executeUpdate();

            conn.commit();
            sender.balance -= amt;
            System.out.println("Transfer successful");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ---------- SHOW TRANSACTIONS ---------- */
    static void showTransactions(User u) {

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT transaction_detail FROM transactions WHERE account_no=?"
            );
            ps.setInt(1, u.accountNo);
            ResultSet rs = ps.executeQuery();

            System.out.println("Transactions:");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
