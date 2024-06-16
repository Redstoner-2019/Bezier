package me.redstoner2019.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RenderFrame {

    private JFrame frame = new JFrame();

    public RenderFrame(MeshProvider provider){
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        frame.add(label);

        long lastUpdate = System.currentTimeMillis()-900;
        int frames = 0;

        List<Mesh> meshes = provider.getMeshes();

        while (true) {
            BufferedImage image = new BufferedImage(frame.getWidth(),frame.getHeight(),1);
            Graphics2D g = image.createGraphics();

            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g.setColor(Color.GRAY);
            g.setStroke(new BasicStroke(2));

            g.drawLine(toScreenSpaceWidth(-1),toScreenSpaceHeight(0),toScreenSpaceWidth(1),toScreenSpaceHeight(0));
            g.drawLine(toScreenSpaceWidth(0),toScreenSpaceHeight(-1),toScreenSpaceWidth(0),toScreenSpaceHeight(1));

            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2));

            for (Mesh m : meshes) {
                List<Triangle> tris = m.getTris();
                for(Triangle t : tris){
                    g.setColor(Color.DARK_GRAY);

                    Vertex2D a = t.getvA().Scale(m.getScale()).Add(m.getvPos());
                    Vertex2D b = t.getvB().Scale(m.getScale()).Add(m.getvPos());
                    Vertex2D c = t.getvC().Scale(m.getScale()).Add(m.getvPos());

                    /*g.drawLine(toScreenSpaceWidth(a),toScreenSpaceHeight(a),toScreenSpaceWidth(b),toScreenSpaceHeight(b));
                    g.drawLine(toScreenSpaceWidth(b),toScreenSpaceHeight(b),toScreenSpaceWidth(c),toScreenSpaceHeight(c));
                    g.drawLine(toScreenSpaceWidth(c),toScreenSpaceHeight(c),toScreenSpaceWidth(a),toScreenSpaceHeight(a));*/

                    Path2D triangle = new Path2D.Float();
                    triangle.moveTo(toScreenSpaceWidth(a),toScreenSpaceHeight(a)); // First point
                    triangle.lineTo(toScreenSpaceWidth(b),toScreenSpaceHeight(b)); // Second point
                    triangle.lineTo(toScreenSpaceWidth(c),toScreenSpaceHeight(c)); // Third point
                    triangle.closePath(); // Close the path to connect the last point to the first

                    g.fill(triangle);

                    g.setPaint(Color.WHITE);

                    g.draw(triangle);
                }
            }

            label.setIcon(new ImageIcon(image));

            if(System.currentTimeMillis() - lastUpdate >= 1000){
                lastUpdate = System.currentTimeMillis();
                frame.setTitle(frames + " FPS");
                frames = 0;
            }
            frames++;
        }
    }

    public int toScreenSpaceWidth(double d){
        d = (d + 1) / 2;
        return (int) (frame.getWidth() * d);
    }
    public int toScreenSpaceHeight(double d){
        d = (d + 1) / 2;
        return (int) (frame.getHeight() * d);
    }
    public int toScreenSpaceWidth(Vertex2D v){
        double d = (v.getX() + 1) / 2;
        return (int) (frame.getWidth() * d);
    }
    public int toScreenSpaceHeight(Vertex2D v){
        double d = (v.getY() + 1) / 2;
        return (int) (frame.getHeight() * d);
    }
}
