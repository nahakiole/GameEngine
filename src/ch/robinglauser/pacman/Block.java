package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Block extends GameElement implements Drawable, Collidable {
    private Rectangle2D.Double block;

    @Override
    public Rectangle getBounds() {
        return block.getBounds();
    }

    @Override
    public void onCollide(Collidable collider) {
        //System.out.println(collider);
        if (collider instanceof Character) {
            Character character = (Character) collider;
            int left = collider.getBounds().x - getBounds().x + collider.getBounds().width;
            int right = getBounds().x + getBounds().width - collider.getBounds().x;
            int bottom = getBounds().y + getBounds().height - collider.getBounds().y;
            int top = collider.getBounds().y + collider.getBounds().height - getBounds().y;

            if (left < 0){
                left = 1000;
            }
            if (top < 0){
                top = 1000;
            }
            if (right < 0){
                right = 1000;
            }
            if (bottom < 0){
                bottom = 1000;
            }

            if (left < right && left < bottom && left < top) {
                character.position.x = getBounds().x - character.getBounds().width;
            }
            if (right < left && right < bottom && right < top && right > 0) {
                character.position.x = getBounds().x + getBounds().width;

            }
            if (top < left && top < bottom && top < right && top > 0) {
                character.position.y = getBounds().y - collider.getBounds().height - 1;

            }
            if (bottom < left && bottom < top && bottom < right && bottom > 0) {
                character.position.y = getBounds().y + getBounds().height;

            }
        }

    }

    public Block(Rectangle2D.Double block) {
        this.block = block;
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {
        paramGraphics.setFill(Color.WHITE);
        paramGraphics.setLineWidth(5);
        paramGraphics.setStroke(Color.BLUE);
        paramGraphics.fillRoundRect((int) block.x, (int) block.y, (int) block.width, (int) block.height, 5,5);

    }

    @Override
    public int getIndex() {
        return 5;
    }

    @Override
    public boolean stillActive() {
        return true;
    }
}
