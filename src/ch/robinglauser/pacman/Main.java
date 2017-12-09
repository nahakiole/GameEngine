package ch.robinglauser.pacman;

import ch.robinglauser.gameengine.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Main extends Application {

    Screen screen;
    Game game;
    CollisionHandler collisionHandler;
    InputHandler inputHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(640, 480);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        screen = new Screen(canvas);
        inputHandler = new InputHandler();
        game = new Game(screen);
        collisionHandler = new CollisionHandler();
        Pacman player = new Pacman(KeyCode.RIGHT, KeyCode.LEFT, KeyCode.UP, KeyCode.DOWN);
        Ghost ghost = new Ghost(player, new Color(1, 0.0706, 0.1569, 1));
        Ghost ghost2 = new Ghost(player, new Color(1, 0.7608, 0.2118, 1));
        Ghost ghost3 = new Ghost(player, new Color(0.2863, 1, 0.2941, 1));
        Fruit fruit = new Fruit(new Point2D.Double(200,200));
        Background background = new Background();
        addElement(background);
        addElement(ghost);
        addElement(ghost2);
        addElement(ghost3);
        addElement(fruit);
        addElement(player);

        for (int i = 0; i < 20; i++) {
            addElement(new Point(new Point2D.Double(Math.random()*400+20, Math.random()*500+40)));

        }
        Labyrinth.buildLabyrinth(this);
        addElement(collisionHandler);
        primaryStage.getScene().setOnKeyPressed(inputHandler);
        primaryStage.getScene().setOnKeyReleased(inputHandler);
        primaryStage.getScene().setOnKeyTyped(inputHandler);

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();
        game.start();
    }

    public void addElement(GameElement gameElement) {
        if (gameElement instanceof Drawable) {
            screen.addDrawable(gameElement);
        }
        if (gameElement instanceof Updateable) {
            game.addElement(gameElement);
        }
        if (gameElement instanceof Collidable) {
            collisionHandler.addCollidable((Collidable) gameElement);
        }
        if (gameElement instanceof KeyListener) {
            inputHandler.addKeyListener((KeyListener) gameElement);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
