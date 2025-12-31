import java.util.Scanner;
class Student 
{
    int id;
    String name;
    int marks;

    void getData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        id = sc.nextInt();

        sc.nextLine(); 
        System.out.print("Enter Student Name: ");
        name = sc.nextLine();

        System.out.print("Enter Marks: ");
        marks = sc.nextInt();
    }

    void displayData() {
        System.out.println("\n--- Student Details ---");
        System.out.println("ID    : " + id);
        System.out.println("Name  : " + name);
        System.out.println("Marks : " + marks);
    }
}

public class oops {
    public static void main(String[] args) 
    {
        Student s1 = new Student();
        s1.getData();
        s1.displayData();
    }
}
