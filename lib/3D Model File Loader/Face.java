/**
 * Holds an array of indices of vertices.
 */
public class Face {
    private int[] points;
    private Material material;
    
    public Face(int[] points, Material material) {
        this.points = points;
        this.material = material;
    }
    
    public int[] getPoints() {
        return points;
    }
    
    public Material getMaterial() {
        return material;
    }
}
