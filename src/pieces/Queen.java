package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Pieces {

    private int x;
    private int y;
    public Player player;
    public Type type = Type.Queen;


    public Queen(int x, int y, Player player) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public int[] getPosition() {
        return new int[]{this.x, this.y};
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
        if (Math.abs(x - newX) == Math.abs(y - newY)
                || x == newX || y == newY) {
            return true;
        }
        return false;
    }

    @Override
    public List<int[]> moving(int newX, int newY) {
        List<int[]> steps = new ArrayList<>();
        if (Math.abs(x - newX) == Math.abs(y - newY)) { //diagonal
            Bishop b = new Bishop(x, y, player);
            steps.addAll(b.moving(newX, newY));
        } else {
            Rook r = new Rook(x, y, player);
            steps.addAll(r.moving(newX, newY));
        }
        return steps;
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
