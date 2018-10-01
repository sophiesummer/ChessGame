package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Rook Class
 * <p>
 * The rook can move any number of squares along any rank or file,
 * but may not leap over other pieces.
 */
public class Rook extends Pieces {

    /**
     * initialize queen attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Rook(int x, int y, Player player) {
        super(x, y, player);
        type = Type.Rook;
    }


    /**
     * check whether rook is moved by its own rule;
     * rook could only move along file or rank
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        return newX == x || newY == y;

    }

    /**
     * @param newX
     * @param newY
     * @return store the positions that rook has been leaped over
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        if (!isValidMove(newX, newY)) {
            return null;
        }
        List<int[]> steps = new ArrayList<>();

        int currX = x;
        int currY = y;

        if (x == newX) {
            if (newY > y) {
                while (currY != newY) {
                    currY++;
                    steps.add(new int[]{x, currY});
                }
            } else {
                while (currY != newY) {
                    currY--;
                    steps.add(new int[]{x, currY});
                }
            }
        } else if (y == newY) {
            if (newX > x) {
                while (currX != newX) {
                    currX++;
                    steps.add(new int[]{currX, y});
                }
            } else {
                while (currX != newX) {
                    currX--;
                    steps.add(new int[]{currX, y});
                }
            }
        }
        steps.remove(steps.size() - 1);
        return steps;
    }

}
