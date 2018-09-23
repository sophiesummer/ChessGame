package pieces;

import game.Player;

import java.util.List;

/**
 * Wizard Class
 * <p>
 * Wizard could only move one step in any directions, like a king
 * the opponent piece in Wizard's left, right, up or down position will be removed.
 */
public class Wizard extends Pieces{

    /**
     * initialize wizard attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Wizard(int x, int y, Player player) {
        super(x, y, player);
        type = Type.Wizard;
    }


    /**
     * check whether wizard is moved by its own rule;
     * wizard move similar to king
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        King k = new King(x, y, player);
        return k.isValidMove(newX, newY);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return null, since wizard only move one squares each time
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return null;
    }

}
