interface playable
{
    void play();
}

class Guitar implements playable
{
    public void play()
    {
        System.out.println("playable");
    }
}

class Piano implements playable
{
    public void play()
    {
        System.out.println("playable");
    }
}

public class interfase 
{
    public static void main (String[]args)
    {
        Piano p1 = new Piano();
        System.out.println("Piano is:");
        p1.play();
        Guitar g1 = new Guitar();
        System.out.println("Guitar is");
        g1.play();
    }
}