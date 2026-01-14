import greenfoot.*; 

public class Win extends World
{
    public Win(int finalScore)
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);
        
        Label win = new Label("You win!", 70);
        win.setFillColor(Color.GREEN);
        win.setLineColor(Color.BLACK);
        addObject(win, 300, 100);
        
        Label scoreLabel = new Label("Final Score: " + finalScore, 40);
        scoreLabel.setFillColor(Color.YELLOW);
        addObject(scoreLabel, 300, 200);
        Button restartButton = new Button("Restart", 40);
        addObject(restartButton, 300, 300);
    }
}
