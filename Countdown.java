import greenfoot.*;

/**
 * World that displays a 3-second countdown and level indicator before the game starts.
 */
public class Countdown extends World {
    SimpleTimer timer = new SimpleTimer();
    Label countdown = new Label(3, 80);
    Bar bar = new Bar("", "", 3000, 3000);

    int level;

    /**
     * Initializes the countdown with the specified level.
     */
    public Countdown(int level) {
        super(600, 400, 1);
        this.level = level;
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);
        
        Label levelLabel = new Label("Level " + level, 50);
        levelLabel.setFillColor(Color.WHITE);
        addObject(levelLabel, 300, 80);

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
            Greenfoot.setWorld(new GameWorld(level));
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
