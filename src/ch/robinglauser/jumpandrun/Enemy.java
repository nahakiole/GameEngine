package ch.robinglauser.jumpandrun;

import javafx.scene.paint.Color;


public class Enemy extends Character {

    double timesincelastpunch = 0;
    private double speed = 2;

    private final Player[] players;

    public Enemy(Player[] player) {
        this.position.x = (int) (Math.random()*20)+600;
        this.players = player;
        this.eyes = Color.RED;
        this.skin = new Color(0.2824, 1, 0.1843, 1);
        this.speed = (Math.random()*2)+1;
    }

    @Override
    public void update(double time) {
        if (health == 0){
            return;
        }
        Player player = players[0];
        if (players[0].position.distance(position) > players[1].position.distance(position) || players[0].health == 0){
            player = players[1];
        }
        if (players[1].health == 0){
            player = players[0];
        }

        if (player != null){
            int distanceX = (int) (player.position.getX()-position.getX());
            int distanceY = (int) (player.position.getY()-position.getY());

            if (distanceX < -20){
                punching = false;
                acceleration.x = -speed;
                punchDirection = false;
            }
            else if (distanceX > 20) {
                punching = false;
                acceleration.x = speed;
                punchDirection = true;
            }
            else {
                timesincelastpunch+=time;
                if (timesincelastpunch > 10){
                    punching = !punching;
                    timesincelastpunch = 0;
                }
                acceleration.x = 0;
            }
        }
        super.update(time);
    }
}
