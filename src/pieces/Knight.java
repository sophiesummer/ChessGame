package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Pieces{

    public int x;
    public int y;
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
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || x == newX || y == newY) {
            return false;
        }

        return ((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1)
                || (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2) );
    }

    @Override
    public List<int[]> moving(int newX, int newY) {
        return new ArrayList<>(); //empty
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }
}
