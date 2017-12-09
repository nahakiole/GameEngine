package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Collidable;
import ch.robinglauser.gameengine.GameElement;
import ch.robinglauser.gameengine.Screen;
import ch.robinglauser.gameengine.Updateable;

import java.awt.geom.Point2D;

public abstract class Character extends GameElement implements Collidable, Updateable {
    Point2D.Double position = new Point2D.Double(50, 50);


    public void update(double time) {

        if (position.x > Screen.size.width+30){
            position.x = -30;
        }
        if (position.x < -31){
            position.x = Screen.size.width+30;
        }
        if (position.y > Screen.size.height+30){
            position.y = -30;
        }
        if (position.y < -31){
            position.y = Screen.size.height+30;
        }

    }

    @Override
    public boolean stillActive() {
        return true;
    }


}
