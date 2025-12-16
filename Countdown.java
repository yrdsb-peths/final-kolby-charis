import greenfoot.*;

public class Countdown extends World {
    SimpleTimer timer = new SimpleTimer();
    Label countdown = new Label(3, 80);

    public Countdown() {
        super(600, 400, 1);
        GreenfootImage background = new GreenfootImage("light.jpg");
        background.scale(600, 400);
        setBackground(background);
        
        addObject(countdown, getWidth() / 2, getHeight() / 2);
        timer.mark();
    }

    public void act() {
        int timeLeft = 3 - (timer.millisElapsed() / 1000);
        if (timeLeft <= 0) {
            Greenfoot.setWorld(new GameWorld());
        }
        countdown.setValue(timeLeft);
    }
}
