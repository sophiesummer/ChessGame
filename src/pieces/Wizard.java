package pieces;

import game.Player;

import java.util.List;

/**
 * Wizard could only move one step in any directions, like a king
 * the opponent piece in Wizard's left, right, up or down position will be removed.
 */
public class Wizard implements Pieces{

    private int x;
    private int y;
    public Player player;
    public Type type = Type.Wizard;

    public Wizard(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }


    @Override
    public int[] getPosition() {
        return new int[]{x, y};
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        King k = new King(x, y, player);
        return k.isValidMove(newX, newY);
    }

    @Override
    public List<int[]> moving(int newX, int newY) {
        return null;
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
