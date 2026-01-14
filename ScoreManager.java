import greenfoot.*;

/**
 * Score manager that tracks the player's current and high scores across worlds.
 */
public class ScoreManager
{
    private static int score = 0;
    private static int highScore = 0;

    /**
     * Gets the current cumulative score.
     */
    public static int getScore()
    {
        return score;
    }

    public static int getHighScore()
    {
        return highScore;
    }

    /**
     * Adds points to current score and updates high score if necessary.
     */
    public static void addScore(int points)
    {
        score += points;
        if (score > highScore)
        {
            highScore = score;
        }
    }

    public static void resetScore()
    {
        score = 0;
    }
}
