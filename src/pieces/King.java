package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class King implements Pieces{

    private int x;
    private int y;
    public Type type = Type.King;
    Player player;

    public King(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }


    @Override
    public int[] getPosition() {
        return new int[]{this.x, this.y};
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
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        return (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1);
    }

    @Override
    public List<int[]> moving(int newX, int newY) {
        return null; //empty
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
