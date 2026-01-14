import greenfoot.*;

/**
 * Enemy that moves side-to-side and triggers a loss when colliding with the player
 */
public class Enemy extends Actor
{
    private int speed;
    
    /**
     * Creates an enemy with a random starting direction and speed.
     */
    public Enemy()
    {
        GreenfootImage img = new GreenfootImage("guard.png");
        img.scale(24, 60); 
        setImage(img);
        speed = Greenfoot.getRandomNumber(2) + 1;
        if (Greenfoot.getRandomNumber(2) == 0) speed = -speed;
    }

    /**
     * Moves and checks for collisions with the player.
     */
    public void act()
    {
        setLocation(getX() + speed, getY());
        if (isAtEdge())
        {
            speed = -speed;
        }
        
        if (isTouching(Player.class))
        {
            GameWorld world = (GameWorld)getWorld();
            world.lose();
        }
    }
}
