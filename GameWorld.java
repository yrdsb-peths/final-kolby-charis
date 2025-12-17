import greenfoot.*;

public class GameWorld extends World
{
    Doll doll = new Doll();
    
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
        
    }
}
