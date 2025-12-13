import greenfoot.*;

public class Countdown extends World {
    SimpleTimer timer = new SimpleTimer();
    Label countdown = new Label(3, 80);

    public Countdown() {
        super(600, 400, 1);
        addObject(countdown, getWidth() / 2, getHeight() / 2);
        timer.mark();
    }

    public void act() {
        int timeLeft = 3 - (timer.millisElapsed() / 1000);
        if (timeLeft <= 0) {
            removeObject(countdown);
        }
        countdown.setValue(timeLeft);
    }
}
