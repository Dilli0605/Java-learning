import java.util.Scanner;

class Calculator
{
    int add(int a, int b)
    {
        return a + b;
    }

    int add(int a, int b, int c)
    {
        return a + b + c;
    }

    double add(double a, double b)
    {
        return a + b;
    }
}

public class MethOver
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Calculator c = new Calculator();

        System.out.print("Enter first integer: ");
        int a = sc.nextInt();

        System.out.print("Enter second integer: ");
        int b = sc.nextInt();

        System.out.print("Enter third integer: ");
        int c1 = sc.nextInt();

        System.out.print("Enter first decimal number: ");
        double x = sc.nextDouble();

        System.out.print("Enter second decimal number: ");
        double y = sc.nextDouble();

        System.out.println("\n--- Results ---");
        System.out.println("First 2 integers: " + c.add(a, b));
        System.out.println("First 3 integers: " + c.add(a, b, c1));
        System.out.println("First 2 decimals: " + c.add(x, y));
        
    }
}
