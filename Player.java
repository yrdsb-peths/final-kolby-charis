import greenfoot.*;

public class Player extends Actor
{
    public Player()
    {
        GreenfootImage player = new GreenfootImage("player.jpg");
        player.scale(40, 60);
        setImage(player);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            setLocation(getX(), getY() - 1);
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
        {
            setLocation(getX(), getY() + 1);
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            setLocation(getX() - 1, getY());
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            setLocation(getX() + 1, getY());
        }
    }
}
