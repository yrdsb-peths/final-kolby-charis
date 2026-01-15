import greenfoot.*;

/**
 * Player-controlled actor that can move in four directions.
 */
public class Player extends Actor
{
    public Player()
    {
        GreenfootImage player = new GreenfootImage("player.png");
        player.scale(60, 60);
        setImage(player);
    }

    /**
     * Handles keyboard input for movement.
     */
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
