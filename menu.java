import java.util.ArrayList;
import java.util.Scanner;

public class menu {

    static ArrayList<String> names = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1: add(); break;
                case 2: view(); break;
                case 3: edit(); break;
                case 4: delete(); break;
                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ADD
    static void add() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        names.add(name);
        System.out.println("Added successfully!");
    }

    // VIEW
    static void view() {
        if (names.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\nID - NAME");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + " - " + names.get(i));
        }
    }

    // EDIT
    static void edit() {
        view();
        if (names.isEmpty()) return;

        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id <= 0 || id > names.size()) {
            System.out.println("Invalid ID!");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();
        names.set(id - 1, newName);
        System.out.println("Updated successfully!");
    }

    // DELETE
    static void delete() {
        view();
        if (names.isEmpty()) return;

        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        if (id <= 0 || id > names.size()) {
            System.out.println("Invalid ID!");
            return;
        }

        System.out.println("ID " + id + " (" + names.get(id - 1) + ") deleted.");
        names.remove(id - 1);
    }
}
