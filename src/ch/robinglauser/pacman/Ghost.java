package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import ch.robinglauser.gameengine.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.*;
import java.awt.geom.Point2D;

public class Ghost extends Character implements Updateable, Drawable, Collidable {

    private final Character pacman;

    Color color = new Color(0.1137, 0.6, 0.9686, 1);
    Point2D.Double acceleration = new Point2D.Double(0, 0);
    double deadUntil = 0;
    double timeUntilDirectionChange = 0;

    public Ghost(Character pacman, Color color) {
        this.pacman = pacman;
        this.color = color;
        acceleration.y = Math.random()*4-2;
        acceleration.x = Math.random()*4-2;
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {
        if (deadUntil > 0){
            return;
        }
        paramGraphics.setFill(color);
        paramGraphics.fillArc(position.x, position.y, 30, 30, 360, 180, ArcType.ROUND);
        paramGraphics.fillRect(position.x, position.y + 10, 30, 30);
        paramGraphics.setFill(Color.WHITE);
        paramGraphics.fillOval(position.x + 6, position.y + 7, 8, 8);
        paramGraphics.fillOval(position.x + 16, position.y + 7, 8, 8);
        paramGraphics.setFill(color);
        paramGraphics.fillOval(position.x + 9, position.y + 10, 2, 2);
        paramGraphics.fillOval(position.x + 19, position.y + 10, 2, 2);
    }

    @Override
    public int getIndex() {
        return 3;
    }

    @Override
    public void update(double time) {
        if (timeUntilDirectionChange > 0){
            timeUntilDirectionChange = timeUntilDirectionChange-time;
        }
        else {
            acceleration.y = (int) (4-(Math.random()*8));
            acceleration.x = (int) (4-(Math.random()*8));
            timeUntilDirectionChange = Math.random()*50+30;
        }

        if (deadUntil > 0){
            deadUntil = Math.max(0, deadUntil-time);
            position.x = 290;
            position.y = 210;
            return;
        }
        super.update(time);
        position.y -= acceleration.y * time;
        position.x -= acceleration.x * time;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) position.x,(int) position.y, 30,40);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Block){
            acceleration.y = (int) (8-(Math.random()*16));
            acceleration.x = (int) (8-(Math.random()*16));
        }
        if (collider instanceof Pacman){
            Pacman pacman = (Pacman) collider;
        }

    }
}
