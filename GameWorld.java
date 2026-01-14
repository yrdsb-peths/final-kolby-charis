import greenfoot.*;

public class GameWorld extends World
{
    Doll doll = new Doll();
    Player player = new Player();
    GreenfootSound startSound = new GreenfootSound("squid.mp3");
    GreenfootSound scanSound = new GreenfootSound("scan.mp3");
    boolean startPlayed = false;
    boolean allowMovement = false;
    int lastX;
    int lastY;
    Label scoreLabel = new Label("Score: 0", 30);
    SimpleTimer deductionTimer = new SimpleTimer();
    
    public GameWorld()
    {    
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        setBackground(background);
        addObject(doll, getWidth() / 2 - 5, 90);
        addObject(player, 400, 350);
        addObject(scoreLabel, 100, 30);
        scoreLabel.setFillColor(Color.YELLOW);
        scoreLabel.setLineColor(Color.BLACK);
        updateScoreLabel();
        deductionTimer.mark();
    }

    public void act()
    {
        if (startPlayed && !startSound.isPlaying() && !scanSound.isPlaying())
        {
            startSound.stop();
            scanSound.play();
            doll.faceFront();
            startPlayed = false;
            allowMovement = false;
            lastX = player.getX();
            lastY = player.getY();
            return;
        }

        if (!startPlayed && !startSound.isPlaying() && !scanSound.isPlaying())
        {
            scanSound.stop();
            startSound.play();
            doll.faceBack();
            startPlayed = true;
            allowMovement = true;
            return;
        }

        if (!allowMovement)
        {
            if (player.getX() != lastX || player.getY() != lastY)
            {
                startSound.stop();
                scanSound.stop();
                Greenfoot.setWorld(new GameOver(ScoreManager.getScore() + (350 - player.getY())));
            }
        }

        if (player.getY() <= 100)
        {
            startSound.stop();
            scanSound.stop();
            ScoreManager.addScore(350 - player.getY());
            Greenfoot.setWorld(new Win(ScoreManager.getScore()));
            return;
        }
        
        if (deductionTimer.millisElapsed() >= 200)
        {
            ScoreManager.addScore(-1);
            deductionTimer.mark();
        }

        updateScoreLabel();
    }

    private void updateScoreLabel()
    {
        int currentRoundScore = 350 - player.getY();
        if (currentRoundScore < 0) currentRoundScore = 0;
        scoreLabel.setValue("Score: " + (ScoreManager.getScore() + currentRoundScore));
    }
}
