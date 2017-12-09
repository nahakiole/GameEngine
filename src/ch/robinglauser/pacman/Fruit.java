package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Point2D;

public class Fruit extends Character implements Drawable, Updateable {
    public Color color = new Color(1, 0.8902, 0.3333, 1);
    double timeUntilActive = 0;


    public Fruit(Point2D.Double position) {
        this.position = position;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) position.x, (int) position.y, 10, 10);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Pacman && timeUntilActive == 0) {
            timeUntilActive = 10;
            Pacman pacman = (Pacman) collider;
            pacman.invinsible = 1000;
        }
    }

    @Override
    public int getIndex() {
        return 3;
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {
        if (timeUntilActive == 0) {
            paramGraphics.setFill(color);
            paramGraphics.fillOval(position.x, position.y, 10, 10);
        }
    }

    @Override
    public void update(double time) {
        if (timeUntilActive - time <= 0 && timeUntilActive != 0) {
            position.x = Math.random()*300+10;
            position.y = Math.random()*300+10;
        }
        timeUntilActive = Math.max(0, timeUntilActive - time);
    }

    @Override
    public boolean stillActive() {
        return timeUntilActive == 0;
    }
}
