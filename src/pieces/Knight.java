package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Pieces{

    private int x;
    private int y;
    public Type type = Type.Knight;
    public Player player;

    public Knight(int x, int y, Player p) {
        this.x = x;
        this.y = y;
        player = p;
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

    /**
     * check whether knight is moved by its own rule;
     * knight could only move in 'L' way
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || x == newX || y == newY) {
            return false;
        }

        return ((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1)
                || (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2) );
    }

    /**
     * @param newX
     * @param newY
     * @return knight can leap over other pieces
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return new ArrayList<>(); //empty
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
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
