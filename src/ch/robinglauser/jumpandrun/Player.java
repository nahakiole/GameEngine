package ch.robinglauser.jumpandrun;

import ch.robinglauser.gameengine.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


public class Player extends Character implements KeyListener {


    private KeyCode VK_RIGHT;
    private KeyCode VK_LEFT;
    private KeyCode VK_JUMP;
    private KeyCode VK_PUNCH;

    public Player(KeyCode VK_RIGHT, KeyCode VK_LEFT, KeyCode VK_JUMP, Color color, int startx, KeyCode VK_PUNCH) {
        this.VK_RIGHT = VK_RIGHT;
        this.VK_LEFT = VK_LEFT;
        this.VK_JUMP = VK_JUMP;
        this.VK_PUNCH = VK_PUNCH;
        this.color = color;
        this.position.x = startx;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (health != 0) {
            if (e.getCode() == this.VK_RIGHT) {
                punchDirection = true;
                acceleration.x = 4;
                rightpressed = true;
            }

            if (e.getCode() == this.VK_PUNCH) {
                punching = true;
            }

            if (e.getCode() == this.VK_LEFT) {
                punchDirection = false;
                acceleration.x = -4;
                leftpressed = true;
            }

            if (e.getCode() == this.VK_JUMP && jumping == 0) {
                acceleration.y = 30;
                jumping = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getCode() == this.VK_PUNCH) {
            punching = false;
        }

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
    }


}
