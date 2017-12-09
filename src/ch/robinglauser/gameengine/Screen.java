package ch.robinglauser.gameengine;

import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class Screen {

    public ArrayList<GameElement> elements = new ArrayList<>();

    public static Dimension size = new Dimension(640,480);


    Canvas canvas;
    Canvas buffer ;


    public Screen(Canvas canvas) {
        this.canvas = canvas;
        this.buffer = new Canvas(canvas.getWidth(),canvas.getHeight());
    }

    public void sort(){
        Collections.sort(elements);
    }

    public void paint() {
        size = new Dimension((int) buffer.getWidth(),(int) buffer.getHeight());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (ListIterator<GameElement> iterator = elements.listIterator(); iterator.hasNext(); ) {
                    try {
                        Drawable element = (Drawable) iterator.next();
                        canvas.getGraphicsContext2D().setFill(Color.BLACK);
                        element.draw(canvas.getGraphicsContext2D());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //canvas.getGraphicsContext2D().drawImage(buffer.snapshot(null, null),0,0);
            }
        });
    }

    public void addDrawable(GameElement drawable){
        elements.add(drawable);
    }
}
