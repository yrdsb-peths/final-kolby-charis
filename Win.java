import greenfoot.*; 

public class Win extends World
{
    public Win(int finalScore)
    {    
        super(600, 400, 1); 
        Label win = new Label("You win!", 60);
        addObject(win, 300, 100);
        Label scoreLabel = new Label("Final Score: " + finalScore, 40);
        addObject(scoreLabel, 300, 200);
        Button restartButton = new Button("Restart", 40);
        addObject(restartButton, 300, 300);
    }
}
