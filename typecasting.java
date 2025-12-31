class typecasting 
{
    public static void main (String []args)
    {
        int a = 10;
        double b = a;  

        System.out.println("Int value: " + a);
        System.out.println("Double value: " + b);

        double x = 10.75;
        int y = (int) x; 

        System.out.println("Double value: " + x);
        System.out.println("Int value: " + y);

        String name = "Java Full Stack";
        System.out.println("Length: " + name.length());
        System.out.println("Length: " + name.toUpperCase());
        System.out.println("Length: " + name.toLowerCase());
    }
}