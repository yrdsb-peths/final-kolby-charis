import greenfoot.*;

/**
 * Main game world
 */
public class GameWorld extends World
{
    Doll doll = new Doll();
    Player player = new Player();
    GreenfootSound startSound = new GreenfootSound("squid.mp3");
    GreenfootSound scanSound = new GreenfootSound("scan.mp3");
    GreenfootSound explosionSound = new GreenfootSound("explosion.mp3");
    boolean startPlayed = false;
    boolean allowMovement = false;
    int lastX;
    int lastY;
    Label scoreLabel = new Label("Score: 0", 30);
    SimpleTimer deductionTimer = new SimpleTimer();
    SimpleTimer delayTimer = new SimpleTimer();
    boolean isWaitingBetweenSounds = false;
    int currentRandomDelay = 0;
    int level;
    Enemy[] enemies;
    
    /**
     * Initializes world with player, doll, and enemies.
     */
    public GameWorld(int level)
    {    
        super(600, 400, 1); 
        this.level = level;
        GreenfootImage background = new GreenfootImage("game setting.png");
        background.scale(600, 400);
        background.setColor(Color.RED);
        background.drawLine(0, 100, 600, 100);
        setBackground(background);
        addObject(doll, getWidth() / 2 - 5, 90);
        addObject(player, 400, 350);
        addObject(scoreLabel, 100, 30);
        
        int enemyCount = level - 1;
        if (enemyCount < 0) enemyCount = 0;
        
        enemies = new Enemy[enemyCount];
        for (int i = 0; i < enemies.length; i++)
        {
            enemies[i] = new Enemy();
            int x = 400;
            int y = 350;
            while (Math.abs(x - 400) < 60 && Math.abs(y - 350) < 60)
            {
                x = Greenfoot.getRandomNumber(getWidth());
                y = 120 + Greenfoot.getRandomNumber(200);
            }
            
            addObject(enemies[i], x, y);
        }

        scoreLabel.setFillColor(Color.YELLOW);
        scoreLabel.setLineColor(Color.BLACK);
        updateScoreLabel();
        deductionTimer.mark();
    }

    /**
     * Handles audio logic, win conditions, and movement detection.
     */
    public void act()
    {
        if (startPlayed && !startSound.isPlaying() && !scanSound.isPlaying() && !isWaitingBetweenSounds)
        {
            isWaitingBetweenSounds = true;
            delayTimer.mark();
            currentRandomDelay = 1000 + Greenfoot.getRandomNumber(1001);
            return;
        }

        if (isWaitingBetweenSounds && delayTimer.millisElapsed() >= currentRandomDelay)
        {
            isWaitingBetweenSounds = false;
            startSound.stop();
            scanSound.play();
            doll.faceFront();
            startPlayed = false;
            allowMovement = false;
            lastX = player.getX();
            lastY = player.getY();
            return;
        }

        if (!startPlayed && !startSound.isPlaying() && !scanSound.isPlaying() && !isWaitingBetweenSounds)
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
                explosionSound.play();
                int currentRoundScore = 350 - player.getY();
                if (currentRoundScore < 0) currentRoundScore = 0;
                ScoreManager.addScore(currentRoundScore);
                Greenfoot.setWorld(new GameOver(ScoreManager.getScore(), 1));
            }
        }

        if (player.getY() <= 100)
        {
            startSound.stop();
            scanSound.stop();
            ScoreManager.addScore(350 - player.getY());
            Greenfoot.setWorld(new Win(ScoreManager.getScore(), level + 1));
            return;
        }
        
        if (deductionTimer.millisElapsed() >= 200)
        {
            ScoreManager.addScore(-1);
            deductionTimer.mark();
        }
        updateScoreLabel();
    }

    /**
     * Handles the transition to the game over screen
     */
    public void lose()
    {
        startSound.stop();
        scanSound.stop();
        explosionSound.play();
        int currentRoundScore = 350 - player.getY();
        if (currentRoundScore < 0) currentRoundScore = 0;
        ScoreManager.addScore(currentRoundScore);
        Greenfoot.setWorld(new GameOver(ScoreManager.getScore(), 1));
    }

    private void updateScoreLabel()
    {
        int currentRoundScore = 350 - player.getY();
        if (currentRoundScore < 0) currentRoundScore = 0;
        scoreLabel.setValue("Score: " + (ScoreManager.getScore() + currentRoundScore));
    }
}
