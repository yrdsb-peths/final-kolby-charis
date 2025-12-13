import greenfoot.*;

public class ListItem extends Actor {
    private int width;
    private int height;
    private int viewerWidth;
    private int viewerHeight;
    private ModelViewer viewer;
    private String fileName;
    
    public ListItem(String fileName, int width, int height) {
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        // Load in the object's data from file
        ModelData data = FileLoader.readObjFile(fileName);
        // Add a model viewer to the
        // world with this file's data
        viewerWidth = 3 * height / 2;
        viewerHeight = height;
        viewer = new ModelViewer(
            data,
            viewerWidth,
            viewerHeight,
            Math.min(viewerWidth, viewerHeight),
            new Color(0, 0, 0, 100));
        viewer.setAutoRotation(0, 1, 0);
    }
    
    public void addedToWorld(World w) {
        w.addObject(viewer, 0, 0);
        updateViewerLocation();
        updateImage();
    }
    
    public void act() {
        // Update our image
        updateImage();
        // Check for mouse click
        checkForClick();
    }
    
    private void updateViewerLocation() {
        viewer.setLocation(
            getX() + width/2 - viewerWidth/2,
            getY());
    }
    
    private void updateImage() {
        // Check if the mouse is hovering on this
        MouseInfo m = Greenfoot.getMouseInfo();
        Color bgColor;
        if(m != null && isMouseOver(m.getX(), m.getY())) {
            bgColor = Color.YELLOW;
        } else {
            bgColor = Color.WHITE;
        }
        // Draw the background
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(bgColor);
        img.fill();
        // Draw the file name text
        GreenfootImage textImg = new GreenfootImage(
            fileName, height/2, null, null);
        img.drawImage(
            textImg,
            5,
            height/2 - textImg.getHeight()/2);
        // Draw the click to enlarge text
        GreenfootImage textImg2 = new GreenfootImage(
            "(Click to enlarge)", height/4, Color.RED, null);
        img.drawImage(
            textImg2,
            width - viewerWidth - textImg2.getWidth() - 6,
            height/2 - textImg2.getHeight()/2);
        // Draw a line at the bottom
        img.setColor(Color.BLACK);
        img.drawLine(0, height - 1, width - 1, height - 1);
        // Update our image
        setImage(img);
    }
    
    private void checkForClick() {
        if(isClickedOn()) {
            Greenfoot.setWorld(new ModelViewerWorld(fileName));
        }
    }
    
    private boolean isMouseOver(int x, int y) {
        return Math.abs(x - getX()) < width/2
            && Math.abs(y - getY()) < height/2;
    }
    
    private boolean isClickedOn() {
        return Greenfoot.mousePressed(this)
            || Greenfoot.mousePressed(viewer);
    }
}
