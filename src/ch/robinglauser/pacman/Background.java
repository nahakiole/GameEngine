package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.Drawable;
import ch.robinglauser.gameengine.GameElement;
import ch.robinglauser.gameengine.Updateable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Background extends GameElement implements Drawable, Updateable {
    @Override
    public void draw(GraphicsContext paramGraphics) {
        paramGraphics.setFill(Color.BLACK);
        paramGraphics.fillRect(0, 0, (int) (paramGraphics.getCanvas().getWidth()), (int) paramGraphics.getCanvas().getHeight());

    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void update(double time) {

    }
}
