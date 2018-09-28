package pieces;

import game.Board;
import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Wizard Class
 * <p>
 * combined bishop and knight
 */
public class Wizard extends Pieces {

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
     * wizard move combines bishop and knight
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        Bishop bishop = new Bishop(x, y, player);
        Knight knight = new Knight(x, y, player);
        return bishop.isValidMove(newX, newY) || knight.isValidMove(newX, newY);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return the passing positions
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        Bishop bishop = new Bishop(x, y, player);
        Knight knight = new Knight(x, y, player);
        List<int[]> moves = new ArrayList<>();

        if (bishop.isValidMove(newX, newY)) {
            moves.addAll(bishop.moving(newX, newY));
        } else {
            moves.addAll(knight.moving(newX, newY));
        }
        return moves;
    }

}
