package pieces;

import game.Player;

import java.util.List;

/**
 * Hopper moves by jumping over its direct piece,
 * Hopper only jump to its forward, backward, left and right direction,
 * Hopper can only jump once each time.
 */
public class Hopper implements Pieces{

    private int x;
    private int y;
    public Type type =  Type.Hopper;
    private Player player;

    public Hopper(int x, int y, Player player) {
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
        return (x == newX && Math.abs(newY - y) == 2)
                || (y == newY && Math.abs(x - newX) == 2);
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
