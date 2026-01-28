import java.util.Scanner;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- ADD ----------------
    static void add() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO names(name) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();

            System.out.println("Added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- VIEW ----------------
    static void view() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM names";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nID - NAME");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- EDIT ----------------
    static void edit() {
        view();

        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE names SET name=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();

            System.out.println("Updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- DELETE ----------------
    static void delete() {
        view();

        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM names WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
