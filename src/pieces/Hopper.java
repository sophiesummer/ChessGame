package pieces;

import game.Player;

import java.util.List;

/**
 * Hopper class
 * <p>
 * Hopper moves by jumping over its direct piece,
 * Hopper only jump to its forward, backward, left and right direction,
 * Hopper can only jump once each time.
 */
public class Hopper extends Pieces {


    /**
     * initialize hopper attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Hopper(int x, int y, Player player) {
        super(x, y, player);
        type = Type.Hopper;
    }

    /**
     * check whether hopper is moved by its own rule,
     * hopper can at most move two squares each time
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
        return (x == newX && Math.abs(newY - y) == 2)
                || (y == newY && Math.abs(x - newX) == 2);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return a null array, since hopper must leap over one piece
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return null;
    }


}
