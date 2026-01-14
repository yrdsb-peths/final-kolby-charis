import greenfoot.*;

public class GameOver extends World
{
    public GameOver(int finalScore, int level)
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);

        Label gameOver = new Label("Game Over", 70);
        gameOver.setFillColor(Color.RED);
        gameOver.setLineColor(Color.BLACK);
        addObject(gameOver, 300, 100);
        
        Label scoreLabel = new Label("Final Score: " + finalScore, 40);
        scoreLabel.setFillColor(Color.YELLOW);
        addObject(scoreLabel, 300, 185);

        Label highScoreLabel = new Label("Best: " + ScoreManager.getHighScore(), 30);
        highScoreLabel.setFillColor(Color.WHITE);
        addObject(highScoreLabel, 300, 235);
        Button restartButton = new Button("Restart", 40, level);
        addObject(restartButton, 300, 300);
    }
}
