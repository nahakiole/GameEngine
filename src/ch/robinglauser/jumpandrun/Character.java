package ch.robinglauser.jumpandrun;

import ch.robinglauser.gameengine.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class Character extends GameElement implements Drawable, Updateable, Collidable {


    protected Color color;
    protected Color skin = new Color(0.7725, 0.6627, 0.1412, 1);
    Color eyes = Color.BLUE;

    double jumping = 0;
    boolean leftpressed = false;
    boolean rightpressed = false;
    final int defaultGround = 430;
    int ground = defaultGround;

    Point2D.Double acceleration = new Point2D.Double(0, 0);

    double walkanimation = 0;
    boolean punching = false;
    boolean punchDirection = false;

    int health = 100;

    Point2D.Double position = new Point2D.Double(0, 430);

    @Override
    public void draw(GraphicsContext paramGraphics) {
        if (health <= 0) {
            paramGraphics.setFill(Color.BLACK);
            paramGraphics.fillRect((int) (position.x), (int) ((int) position.y - 30), 16, 30);
            paramGraphics.setFill(Color.WHITE);
            paramGraphics.fillRect((int) (position.x) + 7, (int) ((int) position.y - 24), 2, 14);
            paramGraphics.fillRect((int) (position.x) + 4, (int) ((int) position.y - 20), 8, 2);
            return;
        }

        paramGraphics.setFill(new Color(1, 1, 1, 0.5));

        paramGraphics.fillRect((int) position.x - 6, (int) (position.y + -62), 26, 14);
        paramGraphics.setFill(Color.BLACK);
        if (health < 30) {
            paramGraphics.setFill(Color.RED);
        }
        paramGraphics.fillText(Integer.toString(health), (int) position.x - 3, (int) (position.y - 50));

        paramGraphics.setFill(skin);
        //Legs
        paramGraphics.fillRect((int) (position.x + walkanimation), (int) ((int) position.y - 20), 5, 20);
        paramGraphics.fillRect((int) (position.x + 10 - walkanimation), (int) ((int) position.y - 20), 5, 20);

        //Upper Body
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y - 30), 15, 20);

        //Head
        paramGraphics.fillOval((int) position.x, (int) ((int) position.y - 45), 15, 15);

        //Shoulder
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 20 - ((int) walkanimation / 2), 4);


        if (punching) {
            //Arms
            if (punchDirection) {
                paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 16);
                paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y - 30), 14, 3);
            } else {
                paramGraphics.fillRect((int) position.x - 14 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 14, 3);
                paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 16);
            }


        } else {
            //Arms
            paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 16);
            paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 16);
        }

        paramGraphics.setFill(color);
        //Legs
        paramGraphics.fillRect((int) (position.x + walkanimation), (int) ((int) position.y - 15), 5, 10);
        paramGraphics.fillRect((int) (position.x + 10 - walkanimation), (int) ((int) position.y - 15), 5, 10);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y - 20), 15, 10);

        //Shirt
        paramGraphics.setFill(Color.WHITE);
        paramGraphics.fillRect((int) position.x, (int) ((int) position.y - 30), 15, 12);
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 5);
        paramGraphics.fillRect((int) position.x + 16 - ((int) walkanimation / 2), (int) ((int) position.y - 30), 3, 5);
        paramGraphics.fillRect((int) position.x - 4 + ((int) walkanimation / 2), (int) ((int) position.y - 30), 20 - ((int) walkanimation / 2), 4);

        //Eyes
        paramGraphics.fillOval((int) position.x + 3, (int) ((int) position.y - 40), 5, 5);
        paramGraphics.fillOval((int) position.x + 8, (int) ((int) position.y - 40), 5, 5);

        paramGraphics.setFill(eyes);
        paramGraphics.fillOval((int) position.x + 4 + ((int) acceleration.x / 4), ((int) position.y - 39), 3, 3);
        paramGraphics.fillOval((int) position.x + 9 + ((int) acceleration.x / 4), ((int) position.y - 39), 3, 3);

    }

    @Override
    public void update(double time) {
        ground = 430;
        position.x += acceleration.x * time;
        if (acceleration.x != 0) {
            walkanimation = (walkanimation + 0.4) % 6;
        } else {
            walkanimation = 0;
        }

        position.y -= acceleration.y * time;
        if (position.y != defaultGround) {
            acceleration.y--;
        }
        if (position.y < defaultGround) {
            acceleration.y--;
        }

        if (position.y >= ground) {
            position.y = ground;
            jumping = 0;
        }

        if (position.x > Screen.size.width + 10) {
            position.x = -10;
        }
        if (position.x < -10) {
            position.x = Screen.size.width + 10;
        }
        if (Double.compare(position.y, (double) ground) == 0) {
            jumping = 0;
        }
    }

    @Override
    public Rectangle getBounds() {
        int width = 25;
        int punchingcorreection = 0;
        if (punching) {
            width += 10;
            if (!punchDirection) {
                punchingcorreection = -10;
            }
        }
        return new Rectangle((int) position.x - 5 + punchingcorreection, (int) position.y - 60, width, 60);
    }

    @Override
    public void onCollide(Collidable collider) {

        if (collider instanceof Character) {
            Character player = (Character) collider;
            if (player.punching) {
                health = health - 1;
                if (health < 0) {
                    health = 0;
                }
            }
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
