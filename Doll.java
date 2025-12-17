import greenfoot.*;

public class Doll extends Actor
{
    public Doll()
    {
        GreenfootImage front = new GreenfootImage("front.png");
        front.scale(50, 75);

        GreenfootImage back = new GreenfootImage("back.png");
        back.scale(50, 75);

        setImage(front);
    }
}
