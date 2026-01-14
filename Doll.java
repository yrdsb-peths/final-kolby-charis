import greenfoot.*;

/**
 * Doll that toggles between facing front and back.
 */
public class Doll extends Actor
{
    public Doll()
    {
        faceBack();
    }

    /**
     * Changes the doll's image to face front (scanning phase).
     */
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
