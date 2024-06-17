package me.redstoner2019;

import me.redstoner2019.draw.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static RenderFrame renderFrame;
    public static String title = "";
    public static void main(String[] args) {

        //System.out.println(Vertex2D.between(new Vertex2D(-1,-1),new Vertex2D(1,1),.5));
        //System.out.println(Vertex2D.intersection(new Vertex2D(-1,-1),new Vertex2D(1,1),new Vertex2D(-1,1),new Vertex2D(1,-1)));

        //if(true) return;

        //Mesh gly = TTFReader.getMeshForChar('A');

        final int[] resolution = {50};

        renderFrame = new RenderFrame(new MeshProvider() {
            @Override
            public List<Mesh> getMeshes() {
                List<Mesh> meshes = new ArrayList<>();
                /*{
                    Mesh m = new Mesh();
                    Triangle triangle = new Triangle(new Vertex2D(-1, -1), new Vertex2D(1, -1), new Vertex2D(1, 1));
                    //m.addTriangle(triangle);
                    List<Vertex2D> verticies = Bezier.bezier(triangle, resolution[0]);
                    int length = verticies.size();
                    int index = 0;

                    while (index < length-1) {
                        int beginVertex = index;
                        int middleVertex = index + 1;
                        int endVertex = index + 2;
                        index+=2;

                        Triangle t;
                        if(index < length) {
                            //3
                            t = new Triangle(verticies.get(beginVertex), verticies.get(middleVertex), verticies.get(endVertex));
                            m.addTriangle(new Triangle(verticies.get(beginVertex), verticies.getFirst(), verticies.get(endVertex)));
                        } else {
                            //2
                            t = new Triangle(verticies.get(beginVertex), verticies.get(middleVertex), verticies.getFirst());
                        }
                        m.addTriangle(t);
                    }
                    m.setScale(.5);

                    //meshes.add(m);
                }

                meshes.add(gly);*/

                double x = -0.99;
                double y = 0;

                title = "I don't have any hobbies renderer program made by redstoner-2019 why am I writing this much, I don't care, it's not my program. My day was like, I had 8 hours of school today and we had SAWATZKI, who made me really tired, I have to stop now otherwise lukas will kill me bye.";
                title = "ABC abc";

                int screenHeight = 720;

                double scale = 1.0 / screenHeight;

                for(char c : title.toCharArray()){
                    if(c == ' '){
                        x+=.1 * scale;
                        continue;
                    }
                    Glyph g = TTFReader.getMeshForChar(c);
                    Mesh m = g.getMesh().clone();
                    m.setScale(scale);
                    m.setvPos(new Vertex2D(x,y));
                    x+=g.getWidth() * m.getScale() + (0.15 * m.getScale());
                    meshes.add(m);

                    if(x >= 0.9){
                        x = -0.99;
                        y+= m.getScale() * 2;
                    }
                }
                return meshes;
            }
        });
    }
}