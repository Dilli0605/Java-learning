class Animal
{
    void eat()
    {
        System.out.println("Animals can Eat");
    }
}

class Dog extends Animal
{
    void bark()
    {
        System.out.println("Dog Barks");
    }   
}

class Cat extends Animal
{
    void meow()
    {
        System.out.println("Cat meows");
    }   
}

class Puppy extends Dog
{
    void weep()
    {
        System.out.println("Puppy Weeps");
    }   
}
public class inheritance
{
    public static void main(String[]args)
    {
        System.out.print("Animals:\n");
        Puppy p1 = new Puppy();
        Cat c1 = new Cat();
        p1.eat();
        System.out.print("Dog:\n");
        p1.bark();
        System.out.print("Cat:\n");
        c1.meow();
        System.out.print("Puppy:\n");
        p1.weep();
    }
}