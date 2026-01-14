import greenfoot.*; 

/**
 * Displayed when player reaches the finish line.
 */
public class Win extends World
{
    public Win(int finalScore, int nextLevel)
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
        addObject(scoreLabel, 300, 185);

        Label highScoreLabel = new Label("Best: " + ScoreManager.getHighScore(), 30);
        highScoreLabel.setFillColor(Color.WHITE);
        addObject(highScoreLabel, 300, 235);
        Button restartButton = new Button("Next Level", 40, nextLevel);
        addObject(restartButton, 300, 300);
    }
}
