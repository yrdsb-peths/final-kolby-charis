import greenfoot.*;

public class TitleScreen extends World
{
    public TitleScreen()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);

        background.setColor(new Color(0, 0, 0, 160));
        background.fillRect(20, 35, 560, 330);
        
        setBackground(background);

        Label title = new Label("Red Light, Green Light", 65);
        title.setFillColor(Color.YELLOW);
        addObject(title, 300, 85);

        Label instructions = new Label("Move when she looks away (Green)", 32);
        instructions.setFillColor(Color.GREEN);
        addObject(instructions, 300, 165);
        
        Label instructions2 = new Label("STOP when she looks at you (Red)", 32);
        instructions2.setFillColor(Color.RED);
        addObject(instructions2, 300, 205);

        Label controls = new Label("Use WASD or ARROW KEYS to move", 32);
        controls.setFillColor(Color.CYAN);
        addObject(controls, 300, 255);

        Label startHint = new Label("Press 'SPACE' to start", 45);
        startHint.setFillColor(Color.WHITE);
        addObject(startHint, 300, 325);
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
