package ch.robinglauser.jumpandrun;

import ch.robinglauser.gameengine.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        Player player = new Player(KeyCode.RIGHT, KeyCode.LEFT, KeyCode.UP, Color.RED, 10, KeyCode.SHIFT);
        Player player2 = new Player(KeyCode.D, KeyCode.A, KeyCode.W, Color.BLUE, 30, KeyCode.Q);
        Player[] players = {player,player2};
        Enemy enemy = new Enemy(players);
        Block block = new Block(new Rectangle2D.Double(300, 350, 40, 40));
        Block block2 = new Block(new Rectangle2D.Double(100, 200, 100, 40));
        Background stage = new Background();
        addElement(block);
        addElement(block2);
        addElement(stage);
        addElement(player);
        addElement(player2);
        addElement(enemy);
        //addElement(player2);
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
