import greenfoot.*;

public class ScoreManager
{
    private static int score = 0;
    private static int highScore = 0;

    public static int getScore()
    {
        return score;
    }

    public static int getHighScore()
    {
        return highScore;
    }

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
