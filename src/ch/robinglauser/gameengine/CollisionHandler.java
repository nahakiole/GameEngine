package ch.robinglauser.gameengine;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.paint.Color;


public class CollisionHandler extends GameElement implements Updateable, Drawable {
    private ArrayList<Collidable> collidables = new ArrayList<>();

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    @Override
    public void update(double time) {
        for (Iterator<Collidable> iterator = collidables.iterator(); iterator.hasNext(); ) {
            Collidable collidable = iterator.next();
            for (Iterator<Collidable> innerIterator = collidables.iterator(); innerIterator.hasNext(); ) {
                Collidable collidable2 = innerIterator.next();
                if (collidable2 != collidable && collidable.getBounds().intersects(collidable2.getBounds())) {
                    collidable.onCollide(collidable2);
                    collidable2.onCollide(collidable);
                }
            }
        }
    }


    @Override
    public void draw(GraphicsContext paramGraphics) {
        for (Iterator<Collidable> iterator = collidables.iterator(); iterator.hasNext(); ) {
            Collidable collidable = iterator.next();
            Rectangle rectangle = collidable.getBounds();
           // paramGraphics.setStroke(Color.RED);
            paramGraphics.setLineWidth(1);
           // paramGraphics.strokeRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    @Override
    public int getIndex() {
        return 20;
    }

    @Override
    public boolean stillActive() {
        return true;
    }
}
