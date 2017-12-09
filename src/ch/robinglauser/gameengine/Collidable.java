package ch.robinglauser.gameengine;

import java.awt.*;

public interface Collidable {

    public Rectangle getBounds();

    public void onCollide(Collidable collider);

}
