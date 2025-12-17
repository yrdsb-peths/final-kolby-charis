import greenfoot.*;

public class Player extends Actor
{
    public Player()
    {
        GreenfootImage player = new GreenfootImage("player.png");
        player.scale(50, 75);
        setImage(player);
    }
   public void act()
    {
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            setLocation(getX() + 5, getY());
        }
    }
}
