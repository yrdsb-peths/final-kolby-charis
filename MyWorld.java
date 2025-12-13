import greenfoot.*;
import lib.*;

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1);
        // Load the doll model and display it using ModelViewer
        ModelData data = FileLoader.readObjFile("doll_EXPORT.obj");
        ModelViewer viewer = new ModelViewer(data, getWidth(), getHeight(), 300, Color.BLACK);
        viewer.setControllable(true);
        addObject(viewer, getWidth() / 2, getHeight() / 2);
    }
}
