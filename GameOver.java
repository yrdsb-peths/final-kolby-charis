import greenfoot.*;

public class GameOver extends World
{
    public GameOver(int finalScore)
    {    
        super(600, 400, 1); 
        Label gameOver = new Label("Game Over", 70);
        gameOver.setFillColor(Color.RED);
        gameOver.setLineColor(Color.BLACK);
        addObject(gameOver, 300, 100);
        
        Label scoreLabel = new Label("Final Score: " + finalScore, 40);
        scoreLabel.setFillColor(Color.YELLOW);
        addObject(scoreLabel, 300, 200);
        Button restartButton = new Button("Restart", 40);
        addObject(restartButton, 300, 300);
    }
}
