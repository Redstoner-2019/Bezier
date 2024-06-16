package me.redstoner2019.draw;

import java.util.ArrayList;
import java.util.List;

public class Mesh implements Cloneable{
    private List<Triangle> tris = new ArrayList<>();
    private Vertex2D vPos = new Vertex2D();
    private double scale = 1;

    public Mesh(List<Triangle> tris, Vertex2D vPos) {
        this.tris = tris;
        this.vPos = vPos;
    }

    public Mesh(Vertex2D vPos) {
        this.vPos = vPos;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
    public double getScale(){
        return scale;
    }

    public Vertex2D getvPos() {
        return vPos;
    }

    public void setvPos(Vertex2D vPos) {
        this.vPos = vPos;
    }

    public Mesh(){

    }
    public void addTriangle(Triangle t){
        tris.add(t);
    }
    public void removeTriangle(Triangle t){
        tris.remove(t);
    }

    public List<Triangle> getTris() {
        return tris;
    }

    public List<Vertex2D> getVerticies() {
        List<Vertex2D> verticies = new ArrayList<>();
        for(Triangle t : tris){
            verticies.addAll(t.getVerticies());
        }
        return verticies;
    }

    @Override
    public Mesh clone() {
        try {
            Mesh clone = (Mesh) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
