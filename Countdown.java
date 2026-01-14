import greenfoot.*;

public class Countdown extends World {
    SimpleTimer timer = new SimpleTimer();
    Label countdown = new Label(3, 80);
    Bar bar = new Bar("", "", 3000, 3000);

    public Countdown() {
        super(600, 400, 1);
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);
        
        addObject(countdown, 300, 180);
        countdown.setFillColor(Color.YELLOW);
        countdown.setLineColor(Color.BLACK);
        timer.mark();

        addObject(bar, 300, 260);
        bar.setShowTextualUnits(false);
        bar.setBarWidth(250);
        bar.setBarHeight(25);
        bar.setSafeColor(Color.YELLOW);
        bar.setDangerColor(Color.RED);
    }

    public void act() {
        int millisElapsed = timer.millisElapsed();
        int timeLeftMillis = 3000 - millisElapsed;
        
        if (timeLeftMillis <= 0) {
            Greenfoot.setWorld(new GameWorld());
        }
        
        int secondsLeft = 3 - (millisElapsed / 1000);
        countdown.setValue(secondsLeft);
        
        if (secondsLeft == 3) {
            countdown.setFillColor(Color.GREEN);
            bar.setSafeColor(Color.GREEN);
        } else if (secondsLeft == 2) {
            countdown.setFillColor(Color.YELLOW);
            bar.setSafeColor(Color.YELLOW);
        } else if (secondsLeft == 1) {
            countdown.setFillColor(Color.RED);
            bar.setSafeColor(Color.RED);
            bar.setDangerColor(Color.RED);
        }
        bar.setValue(timeLeftMillis);
    }
}
