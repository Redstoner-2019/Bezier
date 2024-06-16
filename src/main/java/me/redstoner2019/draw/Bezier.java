package me.redstoner2019.draw;

import java.util.ArrayList;
import java.util.List;

public class Bezier {
    public static List<Vertex2D> bezier(Vertex2D vA, Vertex2D vB, Vertex2D vC, int resolution){
        if(resolution < 1){
            throw new RuntimeException("Resolution must be greater or equal to 1.");
        }

        List<Vertex2D> verticies = new ArrayList<>();

        verticies.add(vA);

        double stepSize = (double) 1 / resolution;

        for (double d = stepSize; d < 1-stepSize; d+=stepSize) {

            double d1 = d;
            double d2 = d + stepSize;
            double d3 = 1 - d;
            double d4 = 1 - (d + stepSize);

            Vertex2D off1 = Vertex2D.between(vA,vC,d1);
            Vertex2D off2 = Vertex2D.between(vA,vC,d2);
            Vertex2D off3 = Vertex2D.between(vB,vC,d3);
            Vertex2D off4 = Vertex2D.between(vB,vC,d4);

            Vertex2D inter = Vertex2D.intersection(off1, off3, off2, off4);

            verticies.add(inter);
        }

        verticies.add(vB);

        return verticies;
    }
    public static List<Vertex2D> bezier(Triangle t, int resolution){
        return bezier(t.getvA(),t.getvB(),t.getvC(),resolution);
    }
}
