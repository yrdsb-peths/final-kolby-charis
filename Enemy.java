import greenfoot.*;

/**
 * Enemy that moves side-to-side and triggers a loss when colliding with the player
 */
public class Enemy extends Actor
{
    private int speed;
    
    private GreenfootImage[] leftImages = new GreenfootImage[4];
    private GreenfootImage[] rightImages = new GreenfootImage[4];
    private SimpleTimer animationTimer = new SimpleTimer();
    private int imageIndex = 0;
    
    /**
     * Creates an enemy with a random starting direction and speed.
     */
    public Enemy()
    {
        for (int i = 0; i < 4; i++) {
            leftImages[i] = new GreenfootImage("sprites/left" + (i + 1) + ".png");
            leftImages[i].scale(32, 75);
            rightImages[i] = new GreenfootImage("sprites/right" + (i + 1) + ".png");
            rightImages[i].scale(32, 75);
        }
        
        speed = Greenfoot.getRandomNumber(2) + 1;
        if (Greenfoot.getRandomNumber(2) == 0) speed = -speed;
        
        if (speed < 0) {
            setImage(leftImages[0]);
        } else {
            setImage(rightImages[0]);
        }
        
        animationTimer.mark();
    }

    /**
     * Moves and checks for collisions with the player.
     */
    public void act()
    {
        animate();
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
    
    /**
     * Animates enemy based on movement direction.
     */
    private void animate()
    {
        if (animationTimer.millisElapsed() < 100)
        {
            return;
        }
        
        if (speed < 0) 
        {
            setImage(leftImages[imageIndex]);
        } 

        else 
        {
            setImage(rightImages[imageIndex]);
        }
        
        imageIndex = (imageIndex + 1) % 4;
        animationTimer.mark();
    }
}
