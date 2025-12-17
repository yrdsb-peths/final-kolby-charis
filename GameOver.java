import greenfoot.*;

public class GameOver extends World
{
    public GameOver()
    {    
        super(600, 400, 1); 
        Label gameOver = new Label("Game Over", 60);
        addObject(gameOver, 300, 200);
    }
}
