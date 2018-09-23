package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Bishop class
 * <p>
 * The bishop can move any number of squares diagonally,
 * but may not leap over other pieces.
 */
public class Bishop extends Pieces {

    /**
     * initialize bishop attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Bishop(int x, int y, Player player) {
        super(x, y, player);
        type = Type.Bishop;
    }

    /**
     * check whether bishop is moved by its own rule;
     * bishop could only move diagonally
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        return (Math.abs(x - newX) == Math.abs(y - newY));
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return a list of squares that the piece passing
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

}
