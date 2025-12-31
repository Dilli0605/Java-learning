abstract class vehicle
{
    abstract void speed();
    void brand()
    {
        System.out.println("BMW");
    }
}

class bike extends vehicle
{
    void speed()
    {
        System.out.println("100KM/h");  
    }
}

public class abstract1
{
    public static void main(String[]args)
    {
        System.out.println("Name of Bike:");
        bike b1 = new bike();
        b1.brand();
        System.out.println("Speed of an Bike:");
        b1.speed();
    }
}