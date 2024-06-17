package me.redstoner2019.draw;

import java.util.ArrayList;
import java.util.List;

public class Mesh implements Cloneable{
    private List<Triangle> tris = new ArrayList<>();
    private Vertex2D vPos = new Vertex2D();
    private double scale = 1;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

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
        recalculate();
    }
    public void removeTriangle(Triangle t){
        tris.remove(t);
        recalculate();
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

    public void recalculate(){
        for(Triangle t : tris){
            for(Vertex2D v : t.getVerticies()){
                minX = Math.min(minX,v.getX());
                minY = Math.min(minY,v.getY());
                maxX = Math.max(maxX,v.getX());
                maxY = Math.max(maxY,v.getY());
            }
        }
    }

    public double getWidth() {
        return Math.abs(minX-maxX);
    }

    public double getHeight() {
        return Math.abs(minY-maxY);
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
