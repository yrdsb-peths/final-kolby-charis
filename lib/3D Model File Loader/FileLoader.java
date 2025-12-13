import greenfoot.Greenfoot;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FileLoader {
    /**
     * Parses an .obj file and returns a
     * ModelData object describing the 3D model
     * 
     * Supported:
     *   .obj file commands:
     *    - v x y z
     *    - f i1 i2 i3...
     *    - mtllib file
     *    - usemtl mat
     *   .mtl file commands:
     *    - newmtl mat
     *    - Kd r g b
     */
    public static ModelData readObjFile(String fileName) {
        fileName = "models/" + fileName;
        
        Map<String, Material> matsList = new HashMap<String, Material>();
        Material currMat = null;
        
        List<Vec3> vertices = new ArrayList<Vec3>();
        List<Face> faces = new ArrayList<Face>();
        try {
            InputStream is = FileLoader.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String line;
            while((line = br.readLine()) != null) {
                // Turn all whitespace into single spaces
                line = line.replaceAll("\\s+", " ");
     
                // Ensure there isn't any leading/trailing whitespace
                line = line.trim();
     
                // Split the line into pieces
                // (using a space as the separator)
                String[] parts = line.split(" ");
     
                // If there isn't anything on this line, skip it
                if(parts.length == 0) {
                    continue;
                }
     
                if("mtllib".equalsIgnoreCase(parts[0])) {
                    // If it starts with "mtllib", we need to parse a mtl file
                    
                    // Check that there is the right amount of pieces
                    if(parts.length < 2) {
                        continue;
                    }
                    
                    // Get the file name of the .mtl file referenced
                    String mtlFile = parts[1];
                    
                    // Parse that mtl file into our materials map
                    readMtlFile(mtlFile, matsList);
                } else if("v".equalsIgnoreCase(parts[0])) {
                    // If it starts with "v", it's a vertex
     
                    // Check that there is the right amount of pieces
                    if(parts.length < 4) {
                        continue;
                    }
     
                    // Attempt to get the x/y/z values
                    double x = 0.0, y = 0.0, z = 0.0;
                    try {
                        x = Double.parseDouble(parts[1]);
                        y = Double.parseDouble(parts[2]);
                        z = Double.parseDouble(parts[3]);
                    } catch(NumberFormatException ex) {
                        // If we got an error, print a
                        // message and skip this line.
                        System.err.println("Error while parsing \"" + line + "\" in the file \"" + fileName + "\".");
                        System.err.println("The file may not have parsed correctly.");
                        continue;
                    }
     
                    // Add this to the list of vertices
                    Vec3 vertex = new Vec3(x, y, z);
                    vertices.add(vertex);
                } else if("usemtl".equalsIgnoreCase(parts[0])) {
                    // If it starts with "usemtl", set the current mat
                    
                    // Check that there is the right amount of pieces
                    if(parts.length < 2) {
                        continue;
                    }
                    
                    // Get the material name
                    String matName = parts[1];
                    
                    // If we know about that material name
                    if(matsList.containsKey(matName)) {
                        // Update the current material
                        currMat = matsList.get(matName);
                    }
                } else if("f".equalsIgnoreCase(parts[0])) {
                    // If it starts with "f", it's a face
     
                    // Ensure it has at least 3 indexes
                    int len = parts.length;
                    if(len < 4) {
                        continue;
                    }
     
                    // Attempt to parse the indexes
                    int[] points = new int[len - 1];
                    try {
                        for(int i = 1; i < len; i++) {
                            points[i-1] = Integer.parseInt(parts[i]) - 1;
                        }
                    } catch(NumberFormatException ex) {
                        // If we got an error, print a
                        // message and skip this line.
                        System.err.println("Error while parsing \"" + line + "\" in the file \"" + fileName + "\".");
                        System.err.println("The file may not have parsed correctly.");
                        continue;
                    }
     
                    // Add this to the list of faces
                    // (for now just generates a random color)
                    Face face = new Face(points, currMat);
                    faces.add(face);
                }
            }
            br.close();
        } catch(Exception ex) {
            System.err.println("Error while parsing the file \"" + fileName + "\".");
            System.err.println("Error message: " + ex.getMessage());
        }
        return new ModelData(vertices, faces);
    }
    
    private static void readMtlFile(String fileName, Map<String, Material> matsList) {
        fileName = "models/" + fileName;
        
        try {
            InputStream is = FileLoader.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String line;
            Material lastMat = null;
            while((line = br.readLine()) != null) {
                // Turn all whitespace into single spaces
                line = line.replaceAll("\\s+", " ");
     
                // Ensure there isn't any leading/trailing whitespace
                line = line.trim();
     
                // Split the line into pieces
                // (using a space as the separator)
                String[] parts = line.split(" ");
     
                // If there isn't anything on this line, skip it
                if(parts.length == 0) {
                    continue;
                }
     
                if("newmtl".equalsIgnoreCase(parts[0])) {
                    // If it starts with "newmtl", make a new material
                    
                    // Check that there is the right amount of pieces
                    if(parts.length < 2) {
                        continue;
                    }
                    
                    // Get the new material's name
                    String matName = parts[1];
                    
                    // Add it into our materials map
                    lastMat = new Material();
                    matsList.put(matName, lastMat);
                } else if("Kd".equalsIgnoreCase(parts[0])) {
                    // If it starts with "Kd", it's diffuse colour
     
                    // Check that there is the right amount of pieces
                    // and that we've actual got a material to edit
                    if(parts.length < 4 || lastMat == null) {
                        continue;
                    }
     
                    // Attempt to get the r/g/b values
                    double r = 0.0, g = 0.0, b = 0.0;
                    try {
                        r = Double.parseDouble(parts[1]);
                        g = Double.parseDouble(parts[2]);
                        b = Double.parseDouble(parts[3]);
                    } catch(NumberFormatException ex) {
                        // If we got an error, print a
                        // message and skip this line.
                        System.err.println("Error while parsing \"" + line + "\" in the file \"" + fileName + "\".");
                        System.err.println("The file may not have parsed correctly.");
                        continue;
                    }
     
                    // Set the material's colour
                    lastMat.setDiffuseColor(new greenfoot.Color(
                        (int)(255 * r),
                        (int)(255 * g),
                        (int)(255 * b)));
                }
            }
            br.close();
        } catch(Exception ex) {
            System.err.println("Error while parsing the file \"" + fileName + "\".");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
