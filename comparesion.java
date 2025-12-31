import java.util.Scanner;
public class comparesion
{
    public static void main(String[]args)
    {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the First value:");
    Double a =sc.nextDouble();
    System.out.println("Enter the Second value:");
    Double b = sc.nextDouble();
    if(a>b)
    {
        System.out.println("A is the Greater Number");
    }
    else if(a<b)
    {
        System.out.println("B is Greater Number");
    }
    else
    {
        System.out.println("Both numbers are equal");
    }
}

}
