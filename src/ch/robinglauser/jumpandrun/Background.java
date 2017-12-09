package ch.robinglauser.jumpandrun;

import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import ch.robinglauser.gameengine.Updateable;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import javafx.scene.paint.Color;


public class Background extends GameElement implements Drawable, Updateable {

    public static final int height = 50;

    public Point2D.Double sunPosition = new Point2D.Double(-60, 0);
    public Point2D.Double moonPosition = new Point2D.Double(640, 0);
    private Color sky = new Color(0.2235, 0.6824, 1, 1);
    private Color skyColor = new Color(0.2235, 0.6824, 1, 1);

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(sky);
        context.fillRect(0, 0, (int) (context.getCanvas().getWidth()), (int) context.getCanvas().getHeight());
        context.setFill(new Color(0.9059, 0.8392, 0.1608, 1));
        context.fillOval((int) sunPosition.x, 300 - (int) getSunY((int) sunPosition.x + 40), 80, 80);
        context.setFill(new Color(0.6471, 0.651, 0.6431, 1));
        context.fillOval((int) moonPosition.x, 300 - (int) getSunY((int) moonPosition.x), 80, 80);
        context.setFill(new Color(0.2392, 0.6314, 0.2549, 1));
        context.fillOval(80, context.getCanvas().getHeight() - 120, 325, 140);
        context.setFill(new Color(0.2745, 0.7216, 0.2902, 1));
        context.fillOval(-80, context.getCanvas().getHeight() - 100, 325, 150);
        context.setFill(new Color(0.2706, 0.6784, 0.2863, 1));
        context.fillOval(300, context.getCanvas().getHeight() - 100, 260, 120);
        context.setFill(new Color(0.2392, 0.6314, 0.2549, 1));
        context.fillOval(500, context.getCanvas().getHeight() - 120, 325, 140);
        context.setFill(new Color(0.3137, 0.8863, 0.3373, 1));
        context.fillRect(0, (int) (context.getCanvas().getHeight() - height), (int) context.getCanvas().getWidth(), height);
    }

    @Override
    public void update(double time) {
        double colormodifier = Math.min(Math.abs(((moonPosition.x + 80) / 600) - 0.5), 1);
        System.out.println(colormodifier);
        if (sunPosition.x > 300 && sunPosition.x < 600 || sunPosition.x > 900 && sunPosition.x < 1300) {
            sky = new Color(Math.max(skyColor.getRed() * colormodifier, 0), Math.max(skyColor.getGreen() * colormodifier, 0), Math.max(skyColor.getBlue() * colormodifier, 0), skyColor.getOpacity());
        } else {
            sky = new Color(Math.min(skyColor.getRed() * colormodifier, 1), Math.min(skyColor.getGreen() * colormodifier, 1), Math.min(skyColor.getBlue() * colormodifier, 1), skyColor.getOpacity());
        }
        sunPosition.x += time;
        moonPosition.x += time;
        if (sunPosition.x > 1200) {
            sunPosition.x = sunPosition.x % 1200 - 80;
        }
        if (moonPosition.x > 1200) {
            moonPosition.x = moonPosition.x % 1200 - 80;
        }
    }


    public int getSunY(int x) {
        return (int) (-1 * (Math.pow(x - 320, 2) / 640) + 160);
    }

    @Override
    public int getIndex() {
        return 5;
    }
}
