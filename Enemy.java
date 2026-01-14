import greenfoot.*;

public class Enemy extends Actor
{
    private int speed;
    
    public Enemy()
    {
        GreenfootImage img = new GreenfootImage("guard.png");
        img.scale(24, 60); 
        setImage(img);
        speed = Greenfoot.getRandomNumber(2) + 1;
        if (Greenfoot.getRandomNumber(2) == 0) speed = -speed;
    }

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
