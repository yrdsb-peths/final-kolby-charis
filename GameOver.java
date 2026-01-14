import greenfoot.*;

public class GameOver extends World
{
    public GameOver(int finalScore)
    {    
        super(600, 400, 1); 
        Label gameOver = new Label("Game Over", 60);
        addObject(gameOver, 300, 100);
        Label scoreLabel = new Label("Final Score: " + finalScore, 40);
        addObject(scoreLabel, 300, 200);
        Button restartButton = new Button("Restart", 40);
        addObject(restartButton, 300, 300);
    }
}
