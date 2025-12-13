import greenfoot.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ModelViewer extends Actor {
    // The width
    private int w;
    // The height
    private int h;
    // The scale factor
    private int sf;
    // The object's vertices
    private List<Vec3> vertices;
    // The object's faces
    // (faces link the vertices)
    private List<Face> faces;
    // The x-offset
    private double xOff;
    // The y-offset
    private double yOff;
    // The z-offset
    private double zOff;
    // The x-rotation
    private double xRot;
    // The y-rotation
    private double yRot;
    // The z-rotation
    private double zRot;
    // The background color
    private Color backgroundColor;
    // Faces comparator for sorting
    private Comparator<Face> facesComparator;
    // Is it controllable?
    private boolean controllable;
    // Move speed
    private double moveSpeed;
    
    public ModelViewer(ModelData model, int width, int height, int scaleFactor) {
        this(model, width, height, scaleFactor, new Color(0, 0, 0, 0));
    }
    
    public ModelViewer(ModelData model, int width, int height,
                       int scaleFactor, Color backgroundColor) {
        setData(model);
        
        w = width;
        h = height;
        
        sf = scaleFactor;
        
        this.backgroundColor = backgroundColor;
        
        xOff = 0;
        yOff = 0;
        zOff = 5;
        
        xRot = 0;
        yRot = 0;
        zRot = 0;
        
        controllable = false;
        
        moveSpeed = 0.1;
        
        facesComparator = new Comparator<Face>() {
            public int compare(Face a, Face b) {
                double diffZ = getZ(b) - getZ(a);
                if(diffZ < 0) return -1;
                if(diffZ == 0) return 0;
                return 1;
            }
            
            private double getZ(Face f) {
                int[] points = f.getPoints();
                int len = points.length;
                double sum = 0;
                for(int i = 0; i < len; i++) {
                    sum += vertices.get(points[i]).getZ();
                }
                return sum / len;
            }
        };
        
        render();
    }
    
    public void act() {
        if(controllable) {
            // Check for mouse drag
            checkMouseDrag();
            // Check for repositioning
            checkKeyboard();
        }
        // Do any rotation
        rotateVertices(xRot, yRot, zRot);
        // Update the image
        render();
    }
    
    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }
    
    public void setAutoRotation(double xRot, double yRot, double zRot) {
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
    }
    
    public void setData(ModelData data) {
        // We have to clone all the vertices
        // in case the same ModelData is given
        // to multiple ModelViewers.
        vertices = new ArrayList<Vec3>();
        for(Vec3 v : data.getVertices()) {
            vertices.add(new Vec3(v.getX(), v.getY(), v.getZ()));
        }
        faces = new ArrayList<Face>();
        for(Face f : data.getFaces()) {
            faces.add(new Face(f.getPoints(), f.getMaterial()));
        }
    }
    
    private int lastX = -1;
    private int lastY = -1;
    private void checkMouseDrag() {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m == null) return;
        if(Greenfoot.mousePressed(this)) {
            lastX = m.getX();
            lastY = m.getY();
        }
        if(Greenfoot.mouseDragged(null)) {
            if(lastX == -1 || lastY == -1) return;
            int dx = m.getX() - lastX;
            int dy = m.getY() - lastY;
            rotateVertices(dy, dx, 0);
            lastX = m.getX();
            lastY = m.getY();
        }
    }
    
    public void checkKeyboard() {
        if(Greenfoot.isKeyDown("a")) {
            xOff -= moveSpeed;
        }
        if(Greenfoot.isKeyDown("d")) {
            xOff += moveSpeed;
        }
        if(Greenfoot.isKeyDown("w")) {
            yOff += moveSpeed;
        }
        if(Greenfoot.isKeyDown("s")) {
            yOff -= moveSpeed;
        }
        if(Greenfoot.isKeyDown("q")) {
            zOff += moveSpeed;
        }
        if(Greenfoot.isKeyDown("e")) {
            zOff -= moveSpeed;
        }
    }
    
    /**
     * Rotates each of the vertices
     * by the given amounts.
     */
    public void rotateVertices(double xRot, double yRot, double zRot) {
        double x, y, z, mag, rot;
        for(Vec3 vertex : vertices) {
            // Get the vertex's position
            x = vertex.getX();
            y = vertex.getY();
            z = vertex.getZ();
            
            // Rotate around the z-axis
            mag = Math.hypot(x, y);
            rot = Math.atan2(y, x) + Math.toRadians(zRot);
            x = Math.cos(rot) * mag;
            y = Math.sin(rot) * mag;
            
            // Rotate around the y-axis
            mag = Math.hypot(x, z);
            rot = Math.atan2(z, x) + Math.toRadians(yRot);
            x = Math.cos(rot) * mag;
            z = Math.sin(rot) * mag;
            
            // Rotate around the x-axis
            mag = Math.hypot(y, z);
            rot = Math.atan2(y, z) + Math.toRadians(xRot);
            z = Math.cos(rot) * mag;
            y = Math.sin(rot) * mag;
            
            // Update the vertex's position
            vertex.setAll(x, y, z);
        }
    }
    
    /**
     * Renders the image.
     */
    private void render() {
        // Create the image
        GreenfootImage image = new GreenfootImage(w, h);
        // Draw the background
        image.setColor(backgroundColor);
        image.fill();
        // Sort the faces (biggest z value first)
        Collections.sort(faces, facesComparator);
        // Draw the faces
        outerLoop:
        for(Face face : faces) {
            // Get the indices of the vertices
            // that make up this face's points
            int[] points = face.getPoints();
            
            int len = points.length;
            int[] x = new int[len];
            int[] y = new int[len];
            // For each index, get the vertex
            // referenced and calculate its
            // x-y position on screen.
            // 3D effect is done by dividing
            // the x-y values of each vertex
            // by their z-value. This means
            // that points further away will
            // become closer to the centre.
            double avgZ = 0;
            for(int i = 0; i < len; i++) {
                double z = zOff + vertices.get(points[i]).getZ();
                avgZ += z;
                if(z <= 0.0) {
                    continue outerLoop;
                }
                x[i] = w/2 + (int)(sf*(vertices.get(points[i]).getX() + xOff)/z);
                y[i] = h/2 - (int)(sf*(vertices.get(points[i]).getY() + yOff)/z);
            }
            avgZ /= len;
            avgZ -= zOff;
            Material mat = face.getMaterial();
            // If the face has a material
            if(mat != null) {
                // Get it's diffuse colour
                Color diffCol = mat.getDiffuseColor();
                // Apply some very simple shading
                diffCol = new Color(
                    Math.min(255, Math.max(0, diffCol.getRed()   - (int)(50*avgZ))),
                    Math.min(255, Math.max(0, diffCol.getGreen() - (int)(50*avgZ))),
                    Math.min(255, Math.max(0, diffCol.getBlue()  - (int)(50*avgZ))));
                // Use that as the colour
                image.setColor(diffCol);
            } else {
                // Just use white instead
                image.setColor(Color.WHITE);
            }
            image.fillPolygon(x, y, len);
        }
        // Set this as the ModelViewer's image
        setImage(image);
    }
}
