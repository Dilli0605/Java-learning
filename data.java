import java.util.Scanner;
public class data
{    
    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String name = scan.nextLine();
        System.out.println("Enter your Age:");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your DOB:");
        String dob = scan.nextLine();
        System.out.println("Enter your mobile Number:");
        int No = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your address:");
        String add = scan.nextLine();
        
    }
}