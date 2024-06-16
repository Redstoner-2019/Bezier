package me.redstoner2019;

import me.redstoner2019.draw.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static RenderFrame renderFrame;
    public static void main(String[] args) {

        //System.out.println(Vertex2D.between(new Vertex2D(-1,-1),new Vertex2D(1,1),.5));
        //System.out.println(Vertex2D.intersection(new Vertex2D(-1,-1),new Vertex2D(1,1),new Vertex2D(-1,1),new Vertex2D(1,-1)));

        //if(true) return;

        final int[] resolution = {7};

        renderFrame = new RenderFrame(new MeshProvider() {
            @Override
            public List<Mesh> getMeshes() {
                List<Mesh> meshes = new ArrayList<>();
                {
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

                    meshes.add(m);
                }

                return meshes;
            }
        });
    }
}