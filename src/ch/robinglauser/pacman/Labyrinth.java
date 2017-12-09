package ch.robinglauser.pacman;

import java.awt.geom.Rectangle2D;

public class Labyrinth {

    public static void buildLabyrinth(Main main) {
        main.addElement(new Block(new Rectangle2D.Double(-60, 0, 80, 200)));
        main.addElement(new Block(new Rectangle2D.Double(-60, 280, 80, 200)));
        main.addElement(new Block(new Rectangle2D.Double(620, 0, 80, 200)));
        main.addElement(new Block(new Rectangle2D.Double(620, 280, 80, 200)));


        main.addElement(new Block(new Rectangle2D.Double(0, -30, 280, 60)));
        main.addElement(new Block(new Rectangle2D.Double(360, -30, 280, 60)));

        main.addElement(new Block(new Rectangle2D.Double(0, 460, 280, 20)));
        main.addElement(new Block(new Rectangle2D.Double(360, 460, 280, 20)));

        main.addElement(new Block(new Rectangle2D.Double(320-200, 200, 60, 30)));
        main.addElement(new Block(new Rectangle2D.Double(320+200-60, 200, 60, 30)));

        //Ghost Cage
        main.addElement(new Block(new Rectangle2D.Double(320-60, 200, 15, 60)));
        main.addElement(new Block(new Rectangle2D.Double(320+60-15, 200, 15, 60)));
        main.addElement(new Block(new Rectangle2D.Double(320-60, 260, 120, 15)));

        main.addElement(new Block(new Rectangle2D.Double(320-60, 185, 40, 15)));
        main.addElement(new Block(new Rectangle2D.Double(320+20, 185, 40, 15)));


        main.addElement(new Block(new Rectangle2D.Double(200, 100, 40, 50)));

        main.addElement(new Block(new Rectangle2D.Double(60, 400, 40, 80)));

    }
}
