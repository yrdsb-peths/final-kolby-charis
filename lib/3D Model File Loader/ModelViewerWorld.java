import greenfoot.*;

public class ModelViewerWorld extends World {
    // The world's width
    private static final int WIDTH = ListWorld.WIDTH;
    // The world's height
    private static final int HEIGHT = ListWorld.HEIGHT;
    // The scale factor
    private static final int SCALE_FACTOR = 300;
    
    public ModelViewerWorld(String fileName) {
        super(WIDTH, HEIGHT, 1);
        // Load in the object's data from file
        ModelData data = FileLoader.readObjFile(fileName);
        // Add a model viewer to the
        // world with this file's data
        ModelViewer viewer = new ModelViewer(
            data,
            WIDTH,
            HEIGHT,
            SCALE_FACTOR,
            Color.BLACK);
        viewer.setControllable(true);
        addObject(viewer, WIDTH/2, HEIGHT/2);
        // Add a back button
        BackButton backBtn = new BackButton();
        addObject(
            backBtn,
            backBtn.getImage().getWidth()/2,
            backBtn.getImage().getHeight()/2);
    }
    
    public void act() {
        
    }
}
