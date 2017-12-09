package ch.robinglauser.gameengine;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class InputHandler implements EventHandler<KeyEvent>  {

    private ArrayList<KeyListener> keyListeners = new ArrayList<>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    @Override
    public void handle(KeyEvent event) {
        for (ListIterator<KeyListener> iterator = keyListeners.listIterator(); iterator.hasNext(); ) {
            KeyListener listener = iterator.next();
            if (listener instanceof GameElement){
                GameElement gameElement = (GameElement) listener;
                if (!gameElement.stillActive()){
                    keyListeners.remove(listener);
                }
            }

            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                listener.keyReleased(event);
            }
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                listener.keyPressed(event);
            }
            if (event.getEventType() == KeyEvent.KEY_TYPED) {
                listener.keyTyped(event);
            }
        }
    }
}