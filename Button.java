import greenfoot.*;

public class Button extends Actor
{
    private String text;
    private int fontSize;
    private Color fillColor;
    private Color lineColor;
    private int targetLevel;
    
    public Button(String text, int fontSize, int targetLevel)
    {
        this.text = text;
        this.fontSize = fontSize;
        this.targetLevel = targetLevel;
        this.fillColor = Color.WHITE;
        this.lineColor = Color.BLACK;
        updateImage();
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (getWorld() instanceof GameOver)
            {
                ScoreManager.resetScore();
            }
            Greenfoot.setWorld(new Countdown(targetLevel));
        }
        
        if (Greenfoot.mouseMoved(this)) {
            fillColor = Color.GREEN;
            updateImage();
        } else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && fillColor == Color.GREEN) {
            fillColor = Color.WHITE;
            updateImage();
        }
    }
    
    private void updateImage()
    {
        GreenfootImage textImage = new GreenfootImage(text, fontSize, fillColor, new Color(0,0,0,0), lineColor);
        GreenfootImage background = new GreenfootImage(textImage.getWidth() + 20, textImage.getHeight() + 20);
        background.setColor(Color.DARK_GRAY);
        background.fill();
        background.setColor(lineColor);
        background.drawRect(0, 0, background.getWidth()-1, background.getHeight()-1);
        background.drawImage(textImage, 10, 10);
        setImage(background);
    }
}
