import greenfoot.*;

public class TitleScreen extends World
{
    public TitleScreen()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);

        Label title = new Label("Red Light, Green Light", 60);
        title.setFillColor(Color.YELLOW);
        addObject(title, 300, 100);

        Label instructions = new Label("Move when she looks away (Green)", 30);
        instructions.setFillColor(Color.GREEN);
        addObject(instructions, 300, 180);
        
        Label instructions2 = new Label("STOP when she looks at you (Red)", 30);
        instructions2.setFillColor(Color.RED);
        addObject(instructions2, 300, 220);

        Label startHint = new Label("Press 'SPACE' to start", 40);
        startHint.setFillColor(Color.WHITE);
        addObject(startHint, 300, 300);
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
