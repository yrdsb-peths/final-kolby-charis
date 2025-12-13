import greenfoot.*;

public class BackButton extends Actor {
    public BackButton() {
        setImage(new GreenfootImage(
            "Back to menu",
            20,
            Color.WHITE,
            null));
    }
    
    public void act() {
        if(Greenfoot.mousePressed(this)) {
            Greenfoot.setWorld(new ListWorld());
        }
    }
}
