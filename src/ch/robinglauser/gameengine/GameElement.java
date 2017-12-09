package ch.robinglauser.gameengine;

public abstract class GameElement implements Comparable<GameElement> {

    @Override
    public int compareTo(GameElement o) {
        return getIndex() - o.getIndex();
    }

    public abstract int getIndex();

    public boolean stillActive() {
        return true;
    }

}
