import greenfoot.*;

public class GameWorld extends World
{
    Doll doll = new Doll();
    Player player = new Player();
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
        addObject(player, 400, 350);
    }

    public void act()
    {
        if (startPlayed && !startSound.isPlaying() && !scanSound.isPlaying())
        {
            startSound.stop();
            scanSound.play();
            doll.faceFront();
            startPlayed = false;
            allowMovement = false;
            lastX = doll.getX();
            lastY = doll.getY();
            return;
        }

        if (!startPlayed && !startSound.isPlaying() && !scanSound.isPlaying())
        {
            scanSound.stop();
            startSound.play();
            doll.faceBack();
            startPlayed = true;
            allowMovement = true;
            return;
        }

        if (!allowMovement)
        {
            if (player.getX() != lastX || player.getY() != lastY)
            {
                Greenfoot.setWorld(new GameOver());
            }
        }

        if (player.getY() <= 100)
        {
            Greenfoot.setWorld(new Win());
        }
    }
}
