import greenfoot.*;

public class GameWorld extends World
{
    Doll doll = new Doll();
    GreenfootSound startSound = new GreenfootSound("squid.mp3");
    GreenfootSound scanSound = new GreenfootSound("scan.mp3");
    boolean startPlayed = false;
    boolean allowMovement = false;
    int lastX;
    int lastY;
    
    public GameWorld()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);
        addObject(doll, getWidth() / 2 - 5, 90);
    }

    public void act()
    {
        if (!startSound.isPlaying())
        {
            startSound.play();
            startPlayed = true;
            allowMovement = true;
            return;
        }
        if (startPlayed && !startSound.isPlaying() && !scanSound.isPlaying())
        {
            scanSound.play();
            allowMovement = false;
            lastX = doll.getX();
            lastY = doll.getY();
        }
        if (!allowMovement)
        {
            if (doll.getX() != lastX || doll.getY() != lastY)
            {
                Greenfoot.setWorld(new GameOver());
            }
        }
    }
}
