import java.util.Scanner;
public class looping 
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("For Loop");
        System.out.print("Enter how many numbers: ");
        int n = sc.nextInt();

        for (int i=1; i <=n; i++) 
        {
            System.out.println(i);
        }

        System.out.println("While {oop");
        System.out.println("Enter an Starting Numbers:");
        double a = sc.nextDouble();
        System.out.println("Enter an Ending Number");
        double b = sc.nextDouble();
        while(a<=b)
        {
            System.out.println(a);
            a++;
        }
    }
}