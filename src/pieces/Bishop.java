package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Pieces{

    private int x;
    private int y;
    public Type type =  Type.Bishop;
    private Player player;

    public Bishop(int x, int y, Player player) {
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

    /**
     * check whether bishop is moved by its own rule;
     * bishop could only move diagonally
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || x == newX || y == newY) {
            return false;
        }

        return (Math.abs(x - newX) == Math.abs(y - newY));
    }


    /**
     *  store the passing positions
     *  a helper function for later check leap over other pieces
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        if (!isValidMove(newX, newY)) {
            return null;
        }

        // go in which direction
        boolean addX = false;
        boolean addY = false;

        if (newX > x) {
            addX = true;
        }

        if (newY > y) {
            addY = true;
        }

        int currX = x;
        int currY = y;

        List<int[]> steps = new ArrayList<>();

        while (currX != newX && currY != newY) {
            if (addX) {
                currX++;
            } else {
                currX--;
            }

            if (addY) {
                currY++;
            } else {
                currY--;
            }
            steps.add(new int[]{currX, currY});
        }
        steps.remove(steps.size() - 1); // remove the destination position
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
