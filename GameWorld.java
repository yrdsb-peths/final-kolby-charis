import greenfoot.*;

public class GameWorld extends World
{
    public GameWorld()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("background.png");
        background.scale(600, 400);
        setBackground(background);
    }
}
