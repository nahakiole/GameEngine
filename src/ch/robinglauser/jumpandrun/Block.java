package ch.robinglauser.jumpandrun;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Block extends GameElement implements Drawable, Collidable {

    private Rectangle2D.Double block = new Rectangle2D.Double();

    @Override
    public Rectangle getBounds() {
        return block.getBounds();
    }

    public Block(Rectangle2D.Double block) {
        this.block = block;
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Character) {
            Character player = (Character) collider;
            if (player.getBounds().x + player.getBounds().width > getBounds().x
                    && player.getBounds().x < getBounds().x + getBounds().width
                    && player.getBounds().y + player.getBounds().height < getBounds().y + getBounds().height) {
                player.position.y = getBounds().y;

                if (getBounds().y == player.getBounds().y + player.getBounds().height) {
                    player.jumping = 0;
                }
                player.ground = getBounds().y + getBounds().height;
                if (player.acceleration.y < 0) {
                    player.acceleration.y = 0;
                }
            } else if (player.getBounds().x > getBounds().x
                    && player.getBounds().x < getBounds().x + getBounds().width &&
                    player.getBounds().y > (getBounds().y+getBounds().height)) {
                player.acceleration.y = -1;
                player.ground = player.defaultGround;
            } else {
                if (player.getBounds().x >= getBounds().x + getBounds().width / 2) {
                    player.position.x = getBounds().x + getBounds().width + 5;

                } else if (player.getBounds().x + player.getBounds().width >= getBounds().x && player.getBounds().x < getBounds().x + getBounds().width / 2) {
                    player.position.x = getBounds().x - player.getBounds().width + 4;

                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {
        paramGraphics.setFill(Color.WHITE);
        paramGraphics.fillRect((int) block.x, (int) block.y, (int) block.width, (int) block.height);
    }

    @Override
    public int getIndex() {
        return 10;
    }
}
