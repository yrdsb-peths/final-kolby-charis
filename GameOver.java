import greenfoot.*;

public class GameOver extends World
{
    public GameOver()
    {    
        super(600, 400, 1); 
        Label gameOver = new Label("Game Over", 60);
        addObject(gameOver, 300, 150);
        Button restartButton = new Button("Restart", 40);
        addObject(restartButton, 300, 275);
    }
}
