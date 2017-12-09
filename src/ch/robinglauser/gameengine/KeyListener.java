package ch.robinglauser.gameengine;

import javafx.scene.input.KeyEvent;

public interface KeyListener {

    public void keyReleased(KeyEvent keyEvent);
    public void keyPressed(KeyEvent keyEvent);
    public void keyTyped(KeyEvent keyEvent);
}
