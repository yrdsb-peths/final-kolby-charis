import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TitleScreen extends World
{
    Label title = new Label("Red Light, Green Light", 50);
    public TitleScreen()
    {    
        super(600, 400, 1); 
        addObject(title, 300, 200);
        Label instructions = new Label("Press 'space' to start", 24);
        addObject(instructions, 300, 250);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new GameWorld());
        }
    }
}
