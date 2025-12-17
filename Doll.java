import greenfoot.*;

public class Doll extends Actor
{
    
    public Doll()
    {
        faceFront();
    }

    public void faceFront()
    {
        GreenfootImage front = new GreenfootImage("front.png");
        front.scale(50, 75);
        setImage(front);
    }

    public void faceBack()
    {
        GreenfootImage back = new GreenfootImage("back.png");
        back.scale(50, 75);
        setImage(back);
    }
}
