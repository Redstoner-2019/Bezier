package me.redstoner2019.draw;

public class Vertex2D {
    public double x;
    public double y;

    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex2D() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void scale(double factor){
        x = x * factor;
        y = y * factor;
    }
    public Vertex2D Scale(double factor){
        return new Vertex2D(x*factor,y*factor);
    }
    public Vertex2D Add(Vertex2D v){
        return new Vertex2D(x + v.getX(), y + v.getY());
    }

    public static Vertex2D between(Vertex2D v1, Vertex2D v2, double d){
        double dx = v2.getX() - v1.getX();
        double dy = v2.getY() - v1.getY();

        return new Vertex2D(v1.getX() + (dx * d), v1.getY() + (dy * d));
    }

    public static Vertex2D intersection(Vertex2D p1, Vertex2D p2, Vertex2D p3, Vertex2D p4) {
        double denominator = (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);

        if (denominator == 0) {
            return null;
        }

        double t = ((p1.x - p3.x) * (p3.y - p4.y) - (p1.y - p3.y) * (p3.x - p4.x)) / denominator;
        double u = -((p1.x - p2.x) * (p1.y - p3.y) - (p1.y - p2.y) * (p1.x - p3.x)) / denominator;

        if (t > 0 && t < 1 && u > 0 && u < 1) {
            return new Vertex2D(p1.x + t * (p2.x - p1.x), p1.y + t * (p2.y - p1.y));
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f", x, y);
    }
}
