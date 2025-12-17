import greenfoot.*;

public class Countdown extends World {
    SimpleTimer timer = new SimpleTimer();
    Label countdown = new Label(3, 80);
    Bar bar = new Bar("Time", "", 3000, 3000);

    public Countdown() {
        super(600, 400, 1);
        GreenfootImage background = new GreenfootImage("light.jpg");
        background.scale(600, 400);
        setBackground(background);
        
        addObject(countdown, getWidth() / 2, getHeight() / 2);
        timer.mark();

        addObject(bar, getWidth() / 2, getHeight() / 2 + 50);
        bar.setShowTextualUnits(false);
    }

    public void act() {
        int millisElapsed = timer.millisElapsed();
        int timeLeftMillis = 3000 - millisElapsed;
        
        if (timeLeftMillis <= 0) {
            Greenfoot.setWorld(new GameWorld());
        }
        
        countdown.setValue(3 - (millisElapsed / 1000));
        bar.setValue(timeLeftMillis);
    }
}
