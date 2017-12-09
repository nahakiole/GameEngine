package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.*;
import java.awt.geom.Point2D;


public class Pacman extends Character implements KeyListener, Updateable, Drawable {

    protected Color skin = new Color(0.9059, 0.8392, 0.1608, 1);

    boolean leftpressed = false;
    boolean rightpressed = false;
    boolean downpressed = false;
    boolean uppressed = false;

    double invinsible = 0;

    Point2D.Double acceleration = new Point2D.Double(0, 0);

    boolean walkanimation = true;
    double spenttime = 0;

    public final int UP = 1;
    public final int DOWN = 2;
    public final int LEFT = 3;
    public final int RIGHT = 4;
    int direction = UP;

    private KeyCode VK_RIGHT;
    private KeyCode VK_LEFT;
    private KeyCode VK_UP;
    private KeyCode VK_DOWN;

    public Pacman(KeyCode VK_RIGHT, KeyCode VK_LEFT, KeyCode VK_UP, KeyCode VK_DOWN) {
        this.position.x = 430;
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_UP = VK_UP;
        this.VK_DOWN = VK_DOWN;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getCode() == this.VK_RIGHT) {
            acceleration.x = -4;
            rightpressed = true;
            direction = RIGHT;
        }

        if (e.getCode() == this.VK_LEFT) {
            acceleration.x = 4;
            leftpressed = true;
            direction = LEFT;
        }

        if (e.getCode() == this.VK_UP) {
            acceleration.y = 4;
            uppressed = true;
            direction = UP;
        }

        if (e.getCode() == this.VK_DOWN) {
            acceleration.y = -4;
            downpressed = true;
            direction = DOWN;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getCode() == this.VK_RIGHT) {
            rightpressed = false;
            if (!leftpressed) {
                acceleration.x = 0;
            }
        }
        if (e.getCode() == this.VK_LEFT) {
            leftpressed = false;
            if (!rightpressed) {
                acceleration.x = 0;
            }
        }
        if (e.getCode() == this.VK_UP) {
            uppressed = false;
            if (!downpressed) {
                acceleration.y = 0;
            }
        }
        if (e.getCode() == this.VK_DOWN) {
            downpressed = false;
            if (!uppressed) {
                acceleration.y = 0;
            }
        }
    }


    @Override
    public int getIndex() {
        return 5;
    }

    @Override
    public void draw(GraphicsContext paramGraphics) {

        paramGraphics.setFill(skin);
        if (walkanimation) {
            int startAngle = 135;
            int arcExtend = 45 + 240;
            switch (direction) {
                case DOWN:
                    startAngle = 135 + 180;
                    break;
                case LEFT:
                    startAngle = 135 + 90;
                    break;
                case RIGHT:
                    startAngle = 45;
                    break;
            }
            paramGraphics.fillArc(position.x, position.y, 30, 30, startAngle, arcExtend, ArcType.ROUND);
        } else {
            if (invinsible > 0) {
                paramGraphics.setFill(Color.WHITE);
            }
            paramGraphics.fillOval(position.x, position.y, 30, 30);
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
        spenttime += time;
        position.y -= acceleration.y * time;
        position.x -= acceleration.x * time;
        if (acceleration.x != 0 || acceleration.y != 0) {
            if (spenttime > 6) {
                walkanimation = !walkanimation;
                spenttime = 0;
            }
        } else {
            walkanimation = true;
        }
        if (invinsible > 0) {
            invinsible = Math.max(invinsible - time, 0);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) position.x, (int) position.y, 30, 30);
    }

    @Override
    public void onCollide(Collidable collider) {
        if (collider instanceof Ghost) {
            Ghost ghost = (Ghost) collider;
            if (invinsible > 0){
                ghost.deadUntil = 100;
            }

        }

    }
}
