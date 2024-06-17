package me.redstoner2019.draw;

public class Glyph implements Cloneable{
    private Mesh mesh;
    private char glyph;
    private double width;
    private double height;

    public Glyph(Mesh mesh, char glyph, double width, double height) {
        this.mesh = mesh;
        this.glyph = glyph;
        this.width = width;
        this.height = height;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public char getGlyph() {
        return glyph;
    }

    public void setGlyph(char glyph) {
        this.glyph = glyph;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Glyph clone() {
        try {
            Glyph clone = (Glyph) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
