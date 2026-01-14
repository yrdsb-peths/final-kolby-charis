import greenfoot.*;

/**
 * The start world displaying the title, instructions, and controls.
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * Initializes world with a background, title, instructions, controls,
     * and a hint to start the game.
     */
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

        Label instructions = new Label("Move when she looks away (Green)", 30);
        instructions.setFillColor(Color.GREEN);
        addObject(instructions, 300, 150);
        
        Label instructions2 = new Label("STOP when she looks at you (Red)", 30);
        instructions2.setFillColor(Color.RED);
        addObject(instructions2, 300, 190);

        Label instructions3 = new Label("Avoid the RED ENEMIES", 30);
        instructions3.setFillColor(Color.ORANGE);
        addObject(instructions3, 300, 230);

        Label controls = new Label("Use WASD or ARROW KEYS to move", 30);
        controls.setFillColor(Color.CYAN);
        addObject(controls, 300, 275);

        Label startHint = new Label("Press 'SPACE' to start", 45);
        startHint.setFillColor(Color.WHITE);
        addObject(startHint, 300, 335);
    }

    /**
     * Checks for user input to start the game.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            ScoreManager.resetScore();
            Greenfoot.setWorld(new Countdown(1));
        }
    }
}
