package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Point2D;


public class Point extends Fruit implements Drawable {

    public Point(Point2D.Double position) {
        super(position);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) position.x, (int) position.y, 5, 5);
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {
        paramGraphics.setFill(Color.WHITE);
        paramGraphics.fillOval(position.x, position.y, 5, 5);
    }
}
