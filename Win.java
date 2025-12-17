import greenfoot.*; 

public class Win extends World
{
    public Win()
    {    
        super(600, 400, 1); 
        Label win = new Label("You win!", 60);
        addObject(win, 300, 200);

    }
}
