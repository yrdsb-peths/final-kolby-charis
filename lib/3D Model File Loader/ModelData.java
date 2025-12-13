import java.util.List;
public class ModelData {
    private List<Vec3> vertices;
    private List<Face> faces;
    
    public ModelData(List<Vec3> vertices, List<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
    }
    
    public List<Vec3> getVertices() {
        return vertices;
    }
    
    public List<Face> getFaces() {
        return faces;
    }
}
