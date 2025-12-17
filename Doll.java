import greenfoot.*;

public class Doll extends Actor
{
    GreenfootImage front = new GreenfootImage("front.png");
    front.scale(50, 75);

    GreenfootImage back = new GreenfootImage("back.png");
    back.scale(50, 75);
    
    public Doll()
    {
        setImage(front);
    }

    public void faceFront()
    {
        setImage(front);
    }

    public void faceBack()
    {
        setImage(back);
    }
}
