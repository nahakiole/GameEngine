package ch.robinglauser.gameengine;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class Game extends Thread {

    private Screen screen;

    private boolean paused = false;

    private long gameTime;

    long lastLoopTime = 0;
    final int TARGET_FPS = 60;

    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    long lastFpsTime = 0;

    public void addElement(GameElement element) {
        elements.add(element);
    }

    GraphicsContext graphicsContext;

    public ArrayList<GameElement> elements = new ArrayList<>();

    public Game(Screen screen) {
        this.screen = screen;
    }

    public void run() {
        lastLoopTime = System.nanoTime();

        Collections.sort(elements);
        screen.sort();
        while (true) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);
            lastFpsTime += updateLength;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }
            screen.paint();
            for (ListIterator<GameElement> iterator = elements.listIterator(); iterator.hasNext(); ) {
                try {
                    Updateable updateable = (Updateable) iterator.next();
                    updateable.update(delta);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
