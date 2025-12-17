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

    public void act()
    {
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            setLocation(getX() + 5, getY());
        }
    }
}
