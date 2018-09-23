package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * King class
 * <p>
 * The king moves one square in any direction.
 */
public class King extends Pieces{

    /**
     * initialize king attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public King(int x, int y, Player player) {
        super(x, y, player);
        type = Type.King;
    }


    /**
     * check whether king is moved by its own rule;
     * king could only move one step but can in all directions
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

        return (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return since king only move one step, there's no square to leap over
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return null; //empty
    }

}
