import greenfoot.*;

public class ListWorld extends World {
    // The world's width
    public static final int WIDTH = 400;
    // The world's height
    public static final int HEIGHT = 300;
    
    public ListWorld() {
        super(WIDTH, HEIGHT, 1);
        addListItems();
    }
    
    public void act() {
        
    }
    
    private void addListItems() {
        String[] files = {
            "cube.obj",
            "head.obj",
            "torus.obj",
            "simple_cup.obj"
        };
        int width = WIDTH;
        int height = 50;
        int x = width/2;
        int y = height/2;
        for(String file : files) {
            addObject(new ListItem(file, width, height), x, y);
            y += height;
        }
    }
}
