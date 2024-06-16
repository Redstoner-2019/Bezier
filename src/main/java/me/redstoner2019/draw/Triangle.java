package me.redstoner2019.draw;

import java.util.List;

public class Triangle {
    private Vertex2D vA;
    private Vertex2D vB;
    private Vertex2D vC;
    private Vertex2D vPos;

    public Triangle(Vertex2D vA, Vertex2D vB, Vertex2D vC, Vertex2D vPos) {
        this.vA = vA;
        this.vB = vB;
        this.vC = vC;
        this.vPos = vPos;
    }

    public Triangle(Vertex2D vA, Vertex2D vB, Vertex2D vC) {
        this.vA = vA;
        this.vB = vB;
        this.vC = vC;
        this.vPos = new Vertex2D();
    }

    public Vertex2D getvPos() {
        return vPos;
    }

    public void setvPos(Vertex2D vPos) {
        this.vPos = vPos;
    }

    public Vertex2D getvA() {
        return vA;
    }

    public void setvA(Vertex2D vA) {
        this.vA = vA;
    }

    public Vertex2D getvB() {
        return vB;
    }

    public void setvB(Vertex2D vB) {
        this.vB = vB;
    }

    public Vertex2D getvC() {
        return vC;
    }

    public void setvC(Vertex2D vC) {
        this.vC = vC;
    }
    public void transform(double scale){
        vA.scale(scale);
        vB.scale(scale);
        vC.scale(scale);
    }
    public List<Vertex2D> getVerticies(){
        return List.of(vA,vB,vC);
    }
}
