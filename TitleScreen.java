import greenfoot.*;

public class TitleScreen extends World
{
    Label title = new Label("Red Light, Green Light", 60);

    public TitleScreen()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("light.jpg");
        background.scale(600, 400);
        setBackground(background);

        addObject(title, 300, 150);
        Label instructions = new Label("Press 'space' to start", 40);
        addObject(instructions, 300, 225);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            ScoreManager.resetScore();
            Greenfoot.setWorld(new Countdown());
        }
    }
}
