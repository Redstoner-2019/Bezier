package me.redstoner2019;

import me.redstoner2019.draw.Glyph;
import me.redstoner2019.draw.Mesh;
import me.redstoner2019.draw.Triangle;
import me.redstoner2019.draw.Vertex2D;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TTFReader {

    public static String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,:;-_<>!\"§$%&/()=?";
    public static HashMap<Character, Glyph> charMeshes = new HashMap<>();

    public static Glyph getMeshForChar(char c) {

        if(charMeshes.containsKey(c)){
            return charMeshes.get(c).clone();
        }

        Mesh m = new Mesh();

        double width = 0;
        double height = 0;

        try {
            InputStream inputStream = new FileInputStream("src\\main\\resources\\Arial.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            font = new Font("Bahnschrift", Font.PLAIN,300);
            font = new Font("Arial", Font.PLAIN,300);

            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

            FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);

            Shape shape = font.createGlyphVector(fontRenderContext, c + "").getOutline();

            width = shape.getBounds2D().getWidth();
            height = shape.getBounds2D().getHeight();

            GeneralPath glyphVector = new GeneralPath();

            glyphVector.append(shape, false);

            PathIterator pathIterator = glyphVector.getPathIterator(null);

            Vertex2D currentPos = new Vertex2D();

            while (!pathIterator.isDone()) {
                int type = pathIterator.currentSegment(new float[6]);
                float[] coords = new float[6];
                pathIterator.currentSegment(coords);

                List<Vertex2D> preVerticies = new ArrayList<>();
                List<LineType> types = new ArrayList<>();

                switch (type) {
                    case PathIterator.SEG_MOVETO:
                        System.out.println("Move to: " + coords[0] + ", " + coords[1]);
                        currentPos = new Vertex2D(coords[0],coords[1]);
                        preVerticies.add(new Vertex2D(coords[0],coords[1]));
                        break;
                    case PathIterator.SEG_LINETO:
                        System.out.println("Line to: " + coords[0] + ", " + coords[1]);
                        m.addTriangle(new Triangle(new Vertex2D(currentPos.x,currentPos.y),new Vertex2D(coords[0],coords[1]),new Vertex2D(currentPos.x,currentPos.y)));
                        currentPos = new Vertex2D(coords[0],coords[1]);
                        break;
                    case PathIterator.SEG_QUADTO:
                        System.out.println("Quadratic curve to: " + coords[0] + ", " + coords[1] + " with control point " + coords[2] + ", " + coords[3]);
                        m.addTriangle(new Triangle(new Vertex2D(currentPos.x,currentPos.y),new Vertex2D(coords[0],coords[1]),new Vertex2D(coords[2],coords[3])));
                        currentPos = new Vertex2D(coords[0],coords[1]);
                        break;
                    case PathIterator.SEG_CUBICTO:
                        System.out.println("Cubic curve to: " + coords[0] + ", " + coords[1] + " with control points " + coords[2] + ", " + coords[3] + " and " + coords[4] + ", " + coords[5]);
                        break;
                    case PathIterator.SEG_CLOSE:
                        System.out.println("Close");
                        break;
                }

                pathIterator.next();
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        Glyph g = new Glyph(m,c,width * 1.2,height * 1.2);

        charMeshes.put(c,g.clone());

        return g.clone();
    }
}

enum LineType {
    LINE,
    BEZIER
}