package pieces;

import game.Board;
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

    /** the board this piece is moving on */
    private Board playBoard;

    /**
     * initialize hopper attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Hopper(int x, int y, Player player, Board playBoard) {
        super(x, y, player);
        type = Type.Hopper;
        this.playBoard = playBoard;
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
        if (!((x == newX && Math.abs(newY - y) == 2)
                || (y == newY && Math.abs(x - newX) == 2))) {
            return false;
        }
        return playBoard.board[(newX + x) / 2][(newY + y) / 2] != null;
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
