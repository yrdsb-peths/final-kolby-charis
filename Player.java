import greenfoot.*;

public class Player extends Actor
{
   public void act()
    {
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            setLocation(getX() + 5, getY());
        }
    }
}
