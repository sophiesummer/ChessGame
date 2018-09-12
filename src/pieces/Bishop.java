package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Pieces{

    public int x;
    public int y;
    Type type =  Type.Bishop;
    Player player;

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
    public Player getPlayer() {
        return player;
    }

    /**
     * check whether bishop is moved by its own rule;
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || x == newX || y == newY) {
            return false;
        }

        return (Math.abs(x - newX) == Math.abs(y - newY));
    }


    /* store the passing positions */
    @Override
    public List<int[]> moving(int newX, int newY) {
        if (!isValidMove(newX, newY)) {
            return null;
        }

        boolean addX = false;
        boolean addY = false; // go in which direction

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
        return steps;
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

}
