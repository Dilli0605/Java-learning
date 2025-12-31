import java.util.Scanner;
public class math
{
    public static void main(String[]args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an First Number:");
        Double a = scan.nextDouble();
        System.out.println("Enter an Second Number:");
        Double b = scan.nextDouble();
        System.out.println("Addtion:"+(a+b));
        System.out.println("Subtraction:"+(a-b));
        System.out.println("Multiplication:"+(a*b));
        System.out.println("Division:"+(a/b));
        System.out.println("Modules:"+(a%b));
        
        System.out.println("Array");
        int[] c = {10, 20, 30, 40, 50};

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }
}