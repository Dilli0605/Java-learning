import java.util.Scanner;
public class hello
{    
    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an integer value:");
        int a=scan.nextInt();
        System.out.println("Enter an Float/Double value:");
        double b=scan.nextDouble();
        System.out.println("Enter an Charecter value:");
        char c=scan.next().charAt(0);
        scan.nextLine();
        System.out.println("Enter an String value:");
        String name =scan.nextLine();
    }
}